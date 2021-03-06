package com.bbj.base.responseconverter;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.bbj.base.domain.BBJResponse;

public class BBJMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter{

	@Override
	protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		if(object instanceof BBJResponse){
		} else {
			object = new BBJResponse(BBJResponse.code_success,"", object);
		}
		System.out.println(object);
		super.writeInternal(object, type, outputMessage);
	}

	
}
