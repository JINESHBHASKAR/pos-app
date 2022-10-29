package com.pos.app.vo;

public class StatusResponse {
	
	
	private static final long serialVersionUID = -4033937348246299745L;
	private Boolean status;
	private String message;
	private Object data;
	
	
	
	public StatusResponse(Boolean status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public StatusResponse() {				
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}	

}
