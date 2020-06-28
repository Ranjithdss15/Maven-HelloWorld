package com.sowisetech.calc.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CalcErrorResponse {
	private List<String> errors;

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public String createResponse()
	{
		String response="";
		try {
			response =  new ObjectMapper().writeValueAsString(getErrors());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	public String createResponse(String error)
	{
		String response="";
		try {
			List errorList = new ArrayList();
			errorList.add(error);
			setErrors(errorList);
			response =  new ObjectMapper().writeValueAsString(getErrors());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	
	public String createResponse(List<String> errors)
	{
		String response="";
		try {
			response =  new ObjectMapper().writeValueAsString(errors);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	public String createResponse(HashMap<String, HashMap<String, String>> errors2) {
		String response="";
		try {
			response =  new ObjectMapper().writeValueAsString(errors2);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
}
