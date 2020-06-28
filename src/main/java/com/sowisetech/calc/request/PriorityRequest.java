package com.sowisetech.calc.request;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PriorityRequest {

	long partyId;
	List<PriorityReq> priorityReq;

	public List<PriorityReq> getPriorityReq() {
		return priorityReq;
	}

	public void setPriorityReq(List<PriorityReq> priorityReq) {
		this.priorityReq = priorityReq;
	}

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

}
