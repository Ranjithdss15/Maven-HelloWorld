package com.sowisetech.calc.model;

public class EmiCapacity {

	long emiCapacityId;
	long partyId;
	int currentAge;
	int retirementAge;
	String stability;
	String backUp;
	double netFamilyIncome;
	double existingEmi;
	double houseHoldExpense;
	double additionalIncome;
	double interestRate;

	public long getEmiCapacityId() {
		return emiCapacityId;
	}

	public void setEmiCapacityId(long emiCapacityId) {
		this.emiCapacityId = emiCapacityId;
	}

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

	public int getCurrentAge() {
		return currentAge;
	}

	public void setCurrentAge(int currentAge) {
		this.currentAge = currentAge;
	}

	public int getRetirementAge() {
		return retirementAge;
	}

	public void setRetirementAge(int retirementAge) {
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

	public double getNetFamilyIncome() {
		return netFamilyIncome;
	}

	public void setNetFamilyIncome(double netFamilyIncome) {
		this.netFamilyIncome = netFamilyIncome;
	}

	public double getExistingEmi() {
		return existingEmi;
	}

	public void setExistingEmi(double existingEmi) {
		this.existingEmi = existingEmi;
	}

	public double getHouseHoldExpense() {
		return houseHoldExpense;
	}

	public void setHouseHoldExpense(double houseHoldExpense) {
		this.houseHoldExpense = houseHoldExpense;
	}

	public double getAdditionalIncome() {
		return additionalIncome;
	}

	public void setAdditionalIncome(double additionalIncome) {
		this.additionalIncome = additionalIncome;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

}
