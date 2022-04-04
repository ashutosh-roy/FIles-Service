package com.algomox.filesservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.algomox.filesservice.models.FilesApiResponse;

@Service
public interface FilesService {
	public FilesApiResponse readFile(String fileName) throws Exception;
	public FilesApiResponse saveFile(List<Object> fileData);
}
