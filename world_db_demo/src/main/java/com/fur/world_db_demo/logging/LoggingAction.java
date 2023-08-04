package com.fur.world_db_demo.logging;

import java.util.Date;
import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class LoggingAction {

	private String uri;
	private Map<String, String> headers;
	private String payload;
	private String methodSignature;
	private HttpStatus httpStatus;	

	private Date timeIn;
	private Date timeOut;
	private long executionTime;	
	private String operation;



}
