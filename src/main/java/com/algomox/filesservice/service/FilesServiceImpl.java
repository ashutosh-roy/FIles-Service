package com.algomox.filesservice.service;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.YamlJsonParser;
import org.springframework.stereotype.Service;
import com.algomox.filesservice.constants.Constants;
import com.algomox.filesservice.models.FilesApiResponse;
import com.algomox.filesservice.util.GenricUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

@Service
public class FilesServiceImpl implements FilesService{
	
	private static Logger LOG = LoggerFactory.getLogger(FilesServiceImpl.class);
	ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
	
	@Autowired
	GenricUtil gen;
	
	public FilesApiResponse readFile(String fileName) throws Exception
	{
		String jsonData;
		try(FileInputStream inputStream = new FileInputStream(fileName)) 
		{     
		    jsonData = IOUtils.toString(inputStream, StandardCharsets.US_ASCII.name());
		    LOG.info("Content of data Fetched using JSON List :- "+jsonData);
		}
		catch(Exception e)
		{
			String exception = "Error occurred in location :- METHOD readFile() in CLASS FilesServiceImpl(). Error Description :- " + e;
			LOG.error(exception,e);
			throw e;
		}
		FilesApiResponse response = new FilesApiResponse("", Constants.SUCCESS, jsonData);
		return response;
	}
	
	public String convertJsonToYaml(String jsonString) throws Exception {
        // parse JSON
        JsonNode jsonNodeTree = new ObjectMapper().readTree(jsonString);
        // save it as YAML
        String jsonAsYaml = new YAMLMapper().writeValueAsString(jsonNodeTree);
        LOG.info("Value after JSON to Yaml conversion :- "+jsonAsYaml);
        return jsonAsYaml;
    }
	@Override
	public FilesApiResponse saveFile(String fileData) throws Exception {
		FilesApiResponse response = new FilesApiResponse("", Constants.SUCCESS, "");
		String yamlValue = convertJsonToYaml(fileData);
		byte bytesContent[] = yamlValue.getBytes();
		response.setMessage(gen.saveFileContents(bytesContent));
		response.setStatus(Constants.SUCCESS);
		return response;
	}
}
