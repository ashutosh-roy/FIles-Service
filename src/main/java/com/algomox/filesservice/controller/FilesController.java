package com.algomox.filesservice.controller;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algomox.filesservice.constants.Constants;
import com.algomox.filesservice.exception.WEBPersistenceException;
import com.algomox.filesservice.models.FilesApiResponse;
import com.algomox.filesservice.service.FilesService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = Constants.V1 + Constants.FILES)
public class FilesController 
{	
	@Autowired 
	FilesService files;
	
	ObjectMapper ob = new ObjectMapper();
	
	@GetMapping()
	public FilesApiResponse readFile(@RequestParam String name) throws Exception
	{
		if(!StringUtils.isEmpty(name)) 
		{
			return files.readFile(name);
		}
		else
		{
			throw new WEBPersistenceException(Constants.FILE_NAME_MISSING_ERROR);
		}
		
	}
	
	@PostMapping()
	public FilesApiResponse saveFile(@RequestBody String fileData) throws Exception
	{
		if(!StringUtils.isEmpty(fileData)) 
		{
			return files.saveFile(fileData);
		}
		else
		{
			throw new WEBPersistenceException(Constants.FILE_NAME_MISSING_ERROR);
		}
	}

}
