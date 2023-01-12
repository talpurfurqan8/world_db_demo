package com.fur.world_db_demo.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fur.world_db_demo.exception.ErrorResponse;

@Component
public class SecurityExceptionHandler implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -7858869558953243875L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setDevMessage(authException.getMessage());
		errorResponse.setErrorMessage("Invalid");
		errorResponse.setHttpStatus(HttpStatus.UNAUTHORIZED);

		response.setContentType("application/json");
		response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

	}
}
