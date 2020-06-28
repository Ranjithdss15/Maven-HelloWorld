package com.sowisetech.calc.request;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class NetworthRequest {

	long partyId;
	List<NetworthReq> networthReq;

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

	public List<NetworthReq> getNetworthReq() {
		return networthReq;
	}

	public void setNetworthReq(List<NetworthReq> networthReq) {
		this.networthReq = networthReq;
	}

}
