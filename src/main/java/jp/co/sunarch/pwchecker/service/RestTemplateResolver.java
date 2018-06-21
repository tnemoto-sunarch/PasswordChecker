package jp.co.sunarch.pwchecker.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateResolver {

	@Bean
	public RestTemplate maServiceRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2XmlHttpMessageConverter messageConverter = new MappingJackson2XmlHttpMessageConverter();

        List<MediaType> supportedMediaTypes = new ArrayList<>(messageConverter.getSupportedMediaTypes());
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        messageConverter.setSupportedMediaTypes(supportedMediaTypes);
        restTemplate.setMessageConverters(Collections.singletonList(messageConverter));
        return restTemplate;
	}

	@Bean
	public RestTemplate dejizoServiceRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2XmlHttpMessageConverter messageConverter = new MappingJackson2XmlHttpMessageConverter();

        List<MediaType> supportedMediaTypes = new ArrayList<>(messageConverter.getSupportedMediaTypes());
        supportedMediaTypes.add(MediaType.TEXT_XML);
        messageConverter.setSupportedMediaTypes(supportedMediaTypes);
        restTemplate.setMessageConverters(Collections.singletonList(messageConverter));
        return restTemplate;
	}
}
