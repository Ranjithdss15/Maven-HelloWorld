package com.sowisetech.advisor.request;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AdvProductInfoRequest implements IValidator {

	private List<AdvProductRequest> advProducts;
	private String advId;


	public String getAdvId() {
		return advId;
	}

	public void setAdvId(String advId) {
		this.advId = advId;
	}

	public List<AdvProductRequest> getAdvProducts() {
		return advProducts;
	}

	public void setAdvProducts(List<AdvProductRequest> advProducts) {
		this.advProducts = advProducts;
	}


}
