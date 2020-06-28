package com.sowisetech.calc.request;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class RiskProfileRequest {

	long partyId;
	List<RiskProfileReq> riskProfileReq;

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

	public List<RiskProfileReq> getRiskProfileReq() {
		return riskProfileReq;
	}

	public void setRiskProfileReq(List<RiskProfileReq> riskProfileReq) {
		this.riskProfileReq = riskProfileReq;
	}

}
