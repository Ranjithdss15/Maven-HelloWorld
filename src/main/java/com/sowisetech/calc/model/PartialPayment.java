package com.sowisetech.calc.model;

public class PartialPayment {

	long partyId;
	long partialPaymentId;
	double loanAmount;
	double interestRate;
	int tenure;
	String loanDate;
	String partPayDate;
	double partPayAmount;

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

	public long getPartialPaymentId() {
		return partialPaymentId;
	}

	public void setPartialPaymentId(long partialPaymentId) {
		this.partialPaymentId = partialPaymentId;
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

	public String getPartPayDate() {
		return partPayDate;
	}

	public void setPartPayDate(String partPayDate) {
		this.partPayDate = partPayDate;
	}

	public double getPartPayAmount() {
		return partPayAmount;
	}

	public void setPartPayAmount(double partPayAmount) {
		this.partPayAmount = partPayAmount;
	}

}
