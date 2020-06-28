package com.sowisetech.calc.request;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class EmiInterestChangeRequest {
	long partyId;
	String loanAmount;
	String tenure;
	String interestRate;
	String loanDate;
	List<EmiInterestChangeReq> emiInterestChangeReq;

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

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getTenure() {
		return tenure;
	}

	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public List<EmiInterestChangeReq> getEmiInterestChangeReq() {
		return emiInterestChangeReq;
	}

	public void setEmiInterestChangeReq(List<EmiInterestChangeReq> emiInterestChangeReq) {
		this.emiInterestChangeReq = emiInterestChangeReq;
	}

}