package com.fur.world_db_demo.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ErrorResponse {

	private String devMessage;
	private String errorMessage;
	private HttpStatus httpStatus;
	private String errorCode;
	private StackTraceElement[] stackTraceElements;
}
