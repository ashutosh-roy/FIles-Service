package com.algomox.filesservice.util;
import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algomox.filesservice.constants.Constants;
import com.algomox.filesservice.models.LicensedData;
import com.algomox.filesservice.repository.FilesRepository;
import com.algomox.filesservice.service.FilesServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
@Component
public class GenricUtil {
	
	private static Logger LOG = LoggerFactory.getLogger(FilesServiceImpl.class);
	ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
	

	@Autowired
	FilesRepository files;
	
	public String saveFileContents(byte[] bytes) throws Exception
	{
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
		}
		catch(Exception e)
		{
			String exception = "Error resolved in saveFileContents() method in Class FilesServiceImpl "+e.getMessage();
			message = e.getMessage();
			LOG.error(exception,e);
			throw e;
		}
	}
	public void saveInCassandra(String jsonData) throws Exception
	{
		try
		{
			JSONArray jsonArray = new JSONArray(jsonData); 
		    List<LicensedData> finalList = new ArrayList<LicensedData>();
		    LOG.info("Content of data Fetched using JSON List :- "+jsonData);
		    for(int i=0;i<jsonArray.length();i++)
		    {
		    	JSONObject ob = jsonArray.getJSONObject(i);
		    	LicensedData value = mapper.readValue(ob.toString(), LicensedData.class);
		    	finalList.add(value);
//		    	files.save(value);
		    }
		    files.saveAll(finalList);
		}
		catch(Exception e)
		{
			String exception = "Error resolved in saveInCassandra() method in Class FilesServiceImpl "+e.getMessage();
			LOG.error(exception,e);
			throw e;
		}
	}
	public String convertJsonToYaml(String jsonString) throws Exception {
        // parse JSON
        JsonNode jsonNodeTree = new ObjectMapper().readTree(jsonString);
        // save it as YAML
        String jsonAsYaml = new YAMLMapper().writeValueAsString(jsonNodeTree);
        LOG.info("Value after JSON to Yaml conversion :- "+jsonAsYaml);
        return jsonAsYaml;
    }
}
