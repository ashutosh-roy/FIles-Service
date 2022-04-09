package com.algomox.filesservice.util;
import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Component;

import com.algomox.filesservice.constants.Constants;
import com.algomox.filesservice.service.FilesServiceImpl;
import com.datastax.oss.driver.api.core.CqlSession;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

@Component
public class GenricUtil {

	private static Logger LOG = LoggerFactory.getLogger(FilesServiceImpl.class);
	ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

	@Value("${spring.data.cassandra.keyspace-name}")
	private String keySpace;

	CqlSession cqlSession = CqlSession.builder().withKeyspace(keySpace).build();

	CassandraOperations template = new CassandraTemplate(cqlSession);

	public String saveFileContents(byte[] bytes) throws Exception {
		String message = "";
		File s = new File(Constants.FILENAME);
		Path root = Paths.get(Constants.FILENAME);
		s.createNewFile();
		try (OutputStream os = Files.newOutputStream(root)) {
			os.write(bytes, 0, bytes.length);
			os.flush();
			os.close();
			message = "Writing into File Successful.";
			LOG.info(message);
			return message;
		} catch (Exception e) {
			String exception = "Error resolved in saveFileContents() method in Class FilesServiceImpl "
					+ e.getMessage();
			message = e.getMessage();
			LOG.error(exception, e);
			throw e;
		}
	}



	public void insertIntoLicensedData(JSONObject value) throws JSONException {
		StringBuilder tableColumnNames = new StringBuilder("").append("INSERT INTO ").append(keySpace).append(".")
				.append(Constants.TABLE_NAME).append("(");

		StringBuilder tableColumnValues = new StringBuilder("VALUES (");
		for (int i = 0; i < value.names().length(); i++) {
			String key = value.names().getString(i);
			String entryForDb = "";
			entryForDb = value.get(key).toString();
			if (!StringUtils.isEmpty(entryForDb)) {
				if (key.equals("name") || key.equals("id") || key.equals("superseded_by")) {
					entryForDb = "'" + value.get(key).toString() + "'";
				} else {
					entryForDb = entryForDb.replaceAll("\"(\\w+)\":", "$1:");
					entryForDb = entryForDb.replaceAll("\"", "'");
				}

				tableColumnValues = tableColumnValues.append(entryForDb);
				tableColumnNames = tableColumnNames.append(key);
				if (i == value.names().length() - 1) {
					tableColumnNames = tableColumnNames.append(")");
					tableColumnValues = tableColumnValues.append(");");
				} else {
					tableColumnNames = tableColumnNames.append(", ");
					tableColumnValues = tableColumnValues.append(", ");
				}
			}
		}
		String query = tableColumnNames.toString() + tableColumnValues.toString();
		LOG.info("Final Query to be executed :- " + query);
		template.getCqlOperations().execute(query);
	}

	public void saveInCassandra(String jsonData) throws Exception {
		try {
			JSONArray jsonArray = new JSONArray(jsonData);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject ob = jsonArray.getJSONObject(i);
				insertIntoLicensedData(ob);
			}
		} catch (Exception e) {
			String exception = "Error resolved in saveInCassandra() method in Class FilesServiceImpl " + e.getMessage();
			LOG.error(exception, e);
			throw e;
		}
	}

	public String convertJsonToYaml(String jsonString) throws Exception {
		// parse JSON
		JsonNode jsonNodeTree = new ObjectMapper().readTree(jsonString);
		// save it as YAML
		String jsonAsYaml = new YAMLMapper().writeValueAsString(jsonNodeTree);
		LOG.info("Value after JSON to Yaml conversion :- " + jsonAsYaml);
		return jsonAsYaml;
	}
}
