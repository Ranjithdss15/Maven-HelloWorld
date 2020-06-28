package com.sowisetech.calc.model;

public class EmiInterestChange {

	long emiIntChangeId;
	long partyId;
	double loanAmount;
	double interestRate;
	int tenure;
	String loanDate;
	double increasedEmi;
	double changedRate;
	String changedDate;

	public String getChangedDate() {
		return changedDate;
	}

	public void setChangedDate(String changedDate) {
		this.changedDate = changedDate;
	}

	public long getEmiIntChangeId() {
		return emiIntChangeId;
	}

	public void setEmiIntChangeId(long emiIntChangeId) {
		this.emiIntChangeId = emiIntChangeId;
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

	public double getIncreasedEmi() {
		return increasedEmi;
	}

	public void setIncreasedEmi(double increasedEmi) {
		this.increasedEmi = increasedEmi;
	}

	public double getChangedRate() {
		return changedRate;
	}

	public void setChangedRate(double changedRate) {
		this.changedRate = changedRate;
	}

}
