package com.sowisetech.calc.request;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PartialPaymentRequest {

	long partyId;
	String loanAmount;
	String interestRate;
	String tenure;
	String loanDate;

	List<PartialPaymentReq> partialPaymentReq;

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

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getTenure() {
		return tenure;
	}

	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public List<PartialPaymentReq> getPartialPaymentReq() {
		return partialPaymentReq;
	}

	public void setPartialPaymentReq(List<PartialPaymentReq> partialPaymentReq) {
		this.partialPaymentReq = partialPaymentReq;
	}

}
