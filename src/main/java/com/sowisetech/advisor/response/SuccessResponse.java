package com.sowisetech.advisor.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SuccessResponse {

	public String createSuccessResponse(String text)
	{
		String response="";
		try {
			response =  new ObjectMapper().writeValueAsString(text);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
}
