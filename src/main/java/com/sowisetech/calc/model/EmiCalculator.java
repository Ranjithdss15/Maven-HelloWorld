package com.sowisetech.calc.model;

public class EmiCalculator {

	long emiCalculatorId;
	long partyId;
	double loanAmount;
	int tenure;
	double interestRate;
	String date;

	public long getEmiCalculatorId() {
		return emiCalculatorId;
	}

	public void setEmiCalculatorId(long emiCalculatorId) {
		this.emiCalculatorId = emiCalculatorId;
	}

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
