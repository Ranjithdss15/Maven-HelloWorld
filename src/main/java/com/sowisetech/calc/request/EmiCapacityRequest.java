package com.sowisetech.calc.request;

import org.springframework.stereotype.Component;

@Component
public class EmiCapacityRequest {

	String currentAge;
	String retirementAge;
	String stability;
	String backUp;
	String netFamilyIncome;
	String existingEmi;
	String houseHoldExpense;
	String additionalIncome;
	String interestRate;
	long partyId;

	public String getCurrentAge() {
		return currentAge;
	}

	public void setCurrentAge(String currentAge) {
		this.currentAge = currentAge;
	}

	public String getRetirementAge() {
		return retirementAge;
	}

	public void setRetirementAge(String retirementAge) {
		this.retirementAge = retirementAge;
	}

	public String getStability() {
		return stability;
	}

	public void setStability(String stability) {
		this.stability = stability;
	}

	public String getBackUp() {
		return backUp;
	}

	public void setBackUp(String backUp) {
		this.backUp = backUp;
	}

	public String getNetFamilyIncome() {
		return netFamilyIncome;
	}

	public void setNetFamilyIncome(String netFamilyIncome) {
		this.netFamilyIncome = netFamilyIncome;
	}

	public String getExistingEmi() {
		return existingEmi;
	}

	public void setExistingEmi(String existingEmi) {
		this.existingEmi = existingEmi;
	}

	public String getHouseHoldExpense() {
		return houseHoldExpense;
	}

	public void setHouseHoldExpense(String houseHoldExpense) {
		this.houseHoldExpense = houseHoldExpense;
	}

	public String getAdditionalIncome() {
		return additionalIncome;
	}

	public void setAdditionalIncome(String additionalIncome) {
		this.additionalIncome = additionalIncome;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

}
