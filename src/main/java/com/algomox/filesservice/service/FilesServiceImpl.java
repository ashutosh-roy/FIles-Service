package com.algomox.filesservice.service;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.YamlJsonParser;
import org.springframework.stereotype.Service;
import com.algomox.filesservice.constants.Constants;
import com.algomox.filesservice.models.FilesApiResponse;

@Service
public class FilesServiceImpl implements FilesService{
	
	private static Logger LOG = LoggerFactory.getLogger(FilesServiceImpl.class);
	
	public FilesApiResponse readFile(String fileName) throws Exception
	{
		YamlJsonParser ob =new YamlJsonParser();
		List<Object> jsonList = new ArrayList<>();
		try(FileInputStream inputStream = new FileInputStream(fileName)) 
		{     
		    String everything = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
		    jsonList = ob.parseList(everything);
			LOG.info("Content of data Fetched using JSON List :- "+jsonList.toString());
			LOG.info("Size of data Fetched using JSON List :- "+jsonList.size());
		}
		String data = jsonList.toString();
		FilesApiResponse response = new FilesApiResponse("", Constants.SUCCESS, data);
		return response;
	}
	
	@Override
	public FilesApiResponse saveFile(List<Object> fileData) {
		// TODO Auto-generated method stub
		return null;
	}
}
