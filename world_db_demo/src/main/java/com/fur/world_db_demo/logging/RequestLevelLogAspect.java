package com.fur.world_db_demo.logging;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/*@Aspect
@Configuration*/
@Slf4j
public class RequestLevelLogAspect {

	@Autowired
	private ObjectMapper mapper;
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void restControllerPointCut() {

	}

	@Before("restControllerPointCut()")
	public void logMethod(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		RequestMapping mapping = signature.getMethod().getAnnotation(RequestMapping.class);

		Map<String, Object> parameters = getParameters(joinPoint);

		try {
			log.info("==> path(s): {}, method(s): {}, arguments: {} ", mapping.path(), mapping.method(),
					mapper.writeValueAsString(parameters));
		} catch (JsonProcessingException e) {
			log.error("Error while converting", e);
		}
	}

	@AfterReturning(pointcut = "restControllerPointCut()", returning = "entity")
	public void logMethodAfter(JoinPoint joinPoint, ResponseEntity<?> entity) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		RequestMapping mapping = signature.getMethod().getAnnotation(RequestMapping.class);

		try {
			log.info("<== path(s): {}, method(s): {}, retuning: {}", mapping.path(), mapping.method(),
					mapper.writeValueAsString(entity));
		} catch (JsonProcessingException e) {
			log.error("Error while converting", e);
		}
	}

	private Map<String, Object> getParameters(JoinPoint joinPoint) {
		CodeSignature signature = (CodeSignature) joinPoint.getSignature();

		HashMap<String, Object> map = new HashMap<>();

		String[] parameterNames = signature.getParameterNames();

		for (int i = 0; i < parameterNames.length; i++) {
			map.put(parameterNames[i], joinPoint.getArgs()[i]);
		}

		return map;
	}
}
