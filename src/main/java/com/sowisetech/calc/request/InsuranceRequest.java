package com.sowisetech.calc.request;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class InsuranceRequest {

	long partyId;
	String annualIncome;
	String stability;
	String predictability;
	String existingInsurance;

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

	public String getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	public String getStability() {
		return stability;
	}

	public void setStability(String stability) {
		this.stability = stability;
	}

	public String getPredictability() {
		return predictability;
	}

	public void setPredictability(String predictability) {
		this.predictability = predictability;
	}

	public String getExistingInsurance() {
		return existingInsurance;
	}

	public void setExistingInsurance(String existingInsurance) {
		this.existingInsurance = existingInsurance;
	}

}
