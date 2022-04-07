package com.algomox.filesservice.util;
import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.algomox.filesservice.constants.Constants;
import com.algomox.filesservice.service.FilesServiceImpl;
@Component
public class GenricUtil {
	
	private static Logger LOG = LoggerFactory.getLogger(FilesServiceImpl.class);
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
			String exception = "Error resolved in saveFileContents() method in Class saveFileContents()"+e.getMessage();
			message = e.getMessage();
			LOG.error(exception,e);
			throw e;
		}
	}
}
