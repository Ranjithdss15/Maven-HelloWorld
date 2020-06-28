package com.sowisetech.calc.model;

public class InterestChange {

	long interestChangeId;
	long partyId;
	double loanAmount;
	double interestRate;
	int tenure;
	String loanDate;
	double changedRate;
	String interestChangedDate;

	public long getInterestChangeId() {
		return interestChangeId;
	}

	public void setInterestChangeId(long interestChangeId) {
		this.interestChangeId = interestChangeId;
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

	public double getChangedRate() {
		return changedRate;
	}

	public void setChangedRate(double changedRate) {
		this.changedRate = changedRate;
	}

	public String getInterestChangedDate() {
		return interestChangedDate;
	}

	public void setInterestChangedDate(String interestChangedDate) {
		this.interestChangedDate = interestChangedDate;
	}

}
