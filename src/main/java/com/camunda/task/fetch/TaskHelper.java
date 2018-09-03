package com.camunda.task.fetch;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TaskHelper {

	public void fetchTask(RestTemplate restTemplate){
		ResponseEntity<List<Task>> rateResponse = restTemplate.exchange("http://localhost:8080/engine-rest/task",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Task>>() {
				});
		if (rateResponse.getStatusCode() == HttpStatus.OK) {
			List<Task> taskList = rateResponse.getBody();
			taskList.forEach(task -> System.out.println(task.getId() + " " + task.getName().replace("\n", " ")));
		}
	}
}
