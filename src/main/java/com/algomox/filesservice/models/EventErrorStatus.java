package com.algomox.filesservice.models;

public class EventErrorStatus {
	private String errorType;
	private String errorCode;
	private String errorDesc;
	private String errorDetail;

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}
	public EventErrorStatus()
	{
		
	}
	public EventErrorStatus (String errorType, String errorCode, String errorDesc, String errorDetail)
	{
		this.errorType = errorType;
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
		this.errorDetail = errorDetail;
	}

}
