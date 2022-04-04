package com.algomox.filesservice.models;

public class FilesApiResponse 
{
	private String status;
	private String message;
	private String data;
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