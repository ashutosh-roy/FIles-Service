package com.algomox.filesservice.models;

import com.fasterxml.jackson.annotation.JsonInclude;

public class FilesApiResponse 
{
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String status;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String data;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private EventErrorStatus eventError;
	public FilesApiResponse() 
	{
	}

	public FilesApiResponse(String message, String status, String data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public FilesApiResponse(String message, String status, String data,EventErrorStatus event) {
		this.status = status;
		this.message = message;
		this.data = data;
		this.eventError = event;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	public EventErrorStatus getEventError() {
		return eventError;
	}

	public void setEventError(EventErrorStatus eventError) {
		this.eventError = eventError;
	}

}