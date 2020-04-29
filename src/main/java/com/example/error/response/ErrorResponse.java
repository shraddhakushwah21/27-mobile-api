package com.example.error.response;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
public class ErrorResponse {

	private String message;
	private Map<String, String> details;
	public ErrorResponse(String message, Map<String, String> details) {
		super();
		this.message = message;
		this.details = details;
	}

	
	
}
