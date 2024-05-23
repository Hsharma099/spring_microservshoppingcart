package com.springudemy.config;

import java.awt.PageAttributes.MediaType;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*
 * This class is for content negotiation whether data wants to get in XML or JSON format
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{

	/**
	 * Configure content negotiation options.
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		
		configurer.favorParameter(true).parameterName("mediaType").
		defaultContentType(org.springframework.http.MediaType.APPLICATION_JSON)
		.mediaType("xml", org.springframework.http.MediaType.APPLICATION_XML)
		.mediaType("json", org.springframework.http.MediaType.APPLICATION_JSON);
		
	}
}
