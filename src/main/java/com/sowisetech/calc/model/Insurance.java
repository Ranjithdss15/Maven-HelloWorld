package com.sowisetech.calc.model;

public class Insurance {

	long insuranceId;
	long partyId;
	double annualIncome;
	String stability;
	String predictability;
	double existingInsurance;
	double requiredInsurance;
	double additionalInsurance;

	public long getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(long insuranceId) {
		this.insuranceId = insuranceId;
	}

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

	public double getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(double annualIncome) {
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

	public double getExistingInsurance() {
		return existingInsurance;
	}

	public void setExistingInsurance(double existingInsurance) {
		this.existingInsurance = existingInsurance;
	}

	public double getRequiredInsurance() {
		return requiredInsurance;
	}

	public void setRequiredInsurance(double requiredInsurance) {
		this.requiredInsurance = requiredInsurance;
	}

	public double getAdditionalInsurance() {
		return additionalInsurance;
	}

	public void setAdditionalInsurance(double additionalInsurance) {
		this.additionalInsurance = additionalInsurance;
	}

}
