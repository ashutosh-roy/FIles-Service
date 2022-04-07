package com.algomox.filesservice.service;
import org.springframework.stereotype.Service;
import com.algomox.filesservice.models.FilesApiResponse;

@Service
public interface FilesService {
	public FilesApiResponse readFile(String fileName) throws Exception;
	public FilesApiResponse saveFile(String fileData) throws Exception;
}
