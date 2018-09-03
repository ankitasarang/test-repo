package com.camunda.task.fetch;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class RestTemplateErrorHandler implements ResponseErrorHandler {

	Logger LOG= LoggerFactory.getLogger(RestTemplateErrorHandler.class);

	@Override
	public void handleError(ClientHttpResponse httpResponse) throws IOException {
		if(httpResponse.getStatusCode().series()== Series.SERVER_ERROR){
			LOG.error("Unable to fetch due to ",Series.SERVER_ERROR.value());
		}else if(httpResponse.getStatusCode().series()== Series.CLIENT_ERROR){
			if(httpResponse.getStatusCode()==HttpStatus.NOT_FOUND)
			{
				LOG.error("Unable to fetch tasks due to {}",HttpStatus.NOT_FOUND );
			}
		}
	}

	@Override
	public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
		return (httpResponse.getStatusCode().series() == Series.CLIENT_ERROR
				|| httpResponse.getStatusCode().series() == Series.SERVER_ERROR);
	}

	
}
