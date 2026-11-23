package com.wipro.coupon_service.reponse;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

	private String message;
	private HttpStatus status;
	public String getMessgae() {
		return message;
	}
	public void setMessage(String message) {
		this.message=message;
	}
	public ErrorResponse()
	{super();
	
	}
	public HttpStatus getStatus()
	{
		return status;
	}
	public void setStatus(HttpStatus status)
	{this.status=status;
		
	}
	
	
}
