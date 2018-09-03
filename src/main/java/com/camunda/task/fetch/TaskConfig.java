package com.camunda.task.fetch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TaskConfig {
	
	@Autowired
	TaskHelper taskHelper;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.errorHandler(new RestTemplateErrorHandler()).build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		taskHelper.fetchTask(restTemplate);
		return null;
	}
}
