package com.target.flow.processor;

import java.io.IOException;

import org.apache.camel.Converter;
import org.apache.camel.TypeConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Component
public class NotificationConverter implements TypeConverters 
{
	@Autowired
	private ObjectMapper mapper;

	@Converter
	public byte[] myPackageToByteArray(String source) {
		try {
			return mapper.writeValueAsBytes(source);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@Converter
	public String byteArrayToMyPackage(byte[] source) {
		try {
			return mapper.readValue(source, String.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
