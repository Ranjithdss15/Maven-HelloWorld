package com.sowisetech.calc.model;

public class RiskSummary {

	long riskSummaryId;
	long partyId;
	String behaviour;
	int eqty_alloc;
	int debt_alloc;
	int cash_alloc;

	public long getRiskSummaryId() {
		return riskSummaryId;
	}

	public void setRiskSummaryId(long riskSummaryId) {
		this.riskSummaryId = riskSummaryId;
	}

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

	public String getBehaviour() {
		return behaviour;
	}

	public void setBehaviour(String behaviour) {
		this.behaviour = behaviour;
	}

	public int getEqty_alloc() {
		return eqty_alloc;
	}

	public void setEqty_alloc(int eqty_alloc) {
		this.eqty_alloc = eqty_alloc;
	}

	public int getDebt_alloc() {
		return debt_alloc;
	}

	public void setDebt_alloc(int debt_alloc) {
		this.debt_alloc = debt_alloc;
	}

	public int getCash_alloc() {
		return cash_alloc;
	}

	public void setCash_alloc(int cash_alloc) {
		this.cash_alloc = cash_alloc;
	}

}
