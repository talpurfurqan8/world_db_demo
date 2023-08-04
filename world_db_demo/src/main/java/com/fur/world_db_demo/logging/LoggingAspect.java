package com.fur.world_db_demo.logging;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void restControllerPointCut() {

	}

	@Around("restControllerPointCut()")
	public Object restControllerAroundAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		HttpServletRequest request = getCurrentRequest();

		long start = 0;

		LoggingAction inBoundRequest = new LoggingAction();
		inBoundRequest.setUri(request.getRequestURI());
		inBoundRequest.setTimeIn(new Date());
		inBoundRequest.setOperation(request.getMethod());
		inBoundRequest.setHeaders(getHeaderMap(request));
		inBoundRequest.setPayload(proceedingJoinPoint.getArgs().toString());

		start = System.currentTimeMillis();
		Object value = proceedingJoinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;

		LoggingAction inBoundRespose = new LoggingAction();
		inBoundRespose.setUri(request.getRequestURI());
		inBoundRespose.setTimeOut(new Date());
		inBoundRespose.setOperation(request.getMethod());
		inBoundRespose.setExecutionTime(executionTime);

		return value;
	}

	private HttpServletRequest getCurrentRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	}

	private Map<String, String> getHeaderMap(HttpServletRequest request) {
		Enumeration<String> requestHeadersName = request.getHeaderNames();
		Map<String, String> headers = new HashMap();
		if (requestHeadersName != null) {
			while (requestHeadersName.hasMoreElements()) {
				String headerName = (String) requestHeadersName.nextElement();
				headers.put(headerName, request.getHeader(headerName));
			}
		}
		return headers;
	}
}
