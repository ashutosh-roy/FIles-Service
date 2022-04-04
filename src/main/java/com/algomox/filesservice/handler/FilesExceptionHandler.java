package com.algomox.filesservice.handler;
import java.nio.file.FileSystemException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.algomox.filesservice.constants.Constants;
import com.algomox.filesservice.models.EventErrorStatus;
import com.algomox.filesservice.models.FilesApiResponse;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class FilesExceptionHandler extends ResponseEntityExceptionHandler {

	public static final String LINE_NUM_STRING = "LineNum: ";
	private static final Logger LOG = LoggerFactory.getLogger(FilesExceptionHandler.class);
	/**
	 * Handle NullpointerException over this service
	 * 
	 * @param NullPointerException
	 * @param WebRequest
	 * @return ResponseEntity
	 */
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {
		String errorFieldPath = ex.getStackTrace()[0].getClassName() + Constants.DOTSTRING
				+ ex.getStackTrace()[0].getMethodName() + Constants.DOTSTRING + LINE_NUM_STRING
				+ ex.getStackTrace()[0].getLineNumber();
		String message = "NullPointerException raised at above line " + ex.getMessage();
		return new ResponseEntity<>(setResponse(ex, message, errorFieldPath, Constants.TECHNICAL,Constants.T_002),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	/**
	 * Handle FileSystemException over this service
	 * 
	 * @param FileSystemException
	 * @param WebRequest
	 * @return ResponseEntity
	 */
	@ExceptionHandler(FileSystemException.class)
	public ResponseEntity<Object> handleFilesSystemException(FileSystemException ex, WebRequest request) {
		String errorFieldPath = ex.getStackTrace()[0].getClassName() + Constants.DOTSTRING
				+ ex.getStackTrace()[0].getMethodName() + Constants.DOTSTRING + LINE_NUM_STRING
				+ ex.getStackTrace()[0].getLineNumber();
		String message = "FilesSystemException raised at above line " + ex.getMessage();
		return new ResponseEntity<>(setResponse(ex, message, errorFieldPath, Constants.TECHNICAL,Constants.T_003),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Handle Runtime Exceptions over this service
	 * 
	 * @param TransformationBusinessException
	 * @param WebRequest
	 * @return ResponseEntity
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleRuntimeException(Exception ex, WebRequest request) {
		String errorFieldPath = ex.getStackTrace()[0].getClassName() + Constants.DOTSTRING
				+ ex.getStackTrace()[0].getMethodName() + Constants.DOTSTRING + LINE_NUM_STRING
				+ ex.getStackTrace()[0].getLineNumber();
		String message = ex.getMessage();
		return new ResponseEntity<>(setResponse(ex, message, errorFieldPath, Constants.TECHNICAL,Constants.T_001),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Constructing response object
	 * 
	 * @param exception
	 * @param message
	 * @param errorFieldPath
	 * @param request
	 * @param errorCategory
	 * @param errorCode
	 * @return
	 */
	public FilesApiResponse setResponse(Exception exception, String message, String errorFieldPath, String errorCategory, String errorCode) 
	{
		FilesApiResponse response = new FilesApiResponse();
		EventErrorStatus error = new EventErrorStatus(errorCategory,errorCode,message,errorFieldPath);
		response.setEventError(error);
		response.setStatus(Constants.FAILURE);
		LOG.error(message);
		return response;
	}

}