package com.sowisetech.calc.request;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CashFlowRequest {

	long partyId;
	String date;

	List<CashFlowItemReq> cashFlowItemReq;

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<CashFlowItemReq> getCashFlowItemReq() {
		return cashFlowItemReq;
	}

	public void setCashFlowItemReq(List<CashFlowItemReq> cashFlowItemReq) {
		this.cashFlowItemReq = cashFlowItemReq;
	}

}
