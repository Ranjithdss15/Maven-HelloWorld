package com.sowisetech.calc.model;

public class EmiChange {

	long emiChangeId;
	long partyId;
	double loanAmount;
	double interestRate;
	int tenure;
	String loanDate;
	double increasedEmi;
	String emiChangedDate;

	public double getIncreasedEmi() {
		return increasedEmi;
	}

	public void setIncreasedEmi(double increasedEmi) {
		this.increasedEmi = increasedEmi;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public String getEmiChangedDate() {
		return emiChangedDate;
	}

	public void setEmiChangedDate(String emiChangedDate) {
		this.emiChangedDate = emiChangedDate;
	}

	public long getEmiChangeId() {
		return emiChangeId;
	}

	public void setEmiChangeId(long emiChangeId) {
		this.emiChangeId = emiChangeId;
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

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

}
