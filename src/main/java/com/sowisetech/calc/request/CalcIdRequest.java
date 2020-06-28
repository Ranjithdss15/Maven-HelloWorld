package com.sowisetech.calc.request;

import org.springframework.stereotype.Component;

@Component
public class CalcIdRequest {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
