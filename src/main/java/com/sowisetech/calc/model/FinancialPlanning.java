package com.sowisetech.calc.model;

import java.util.List;

public class FinancialPlanning {

	List<Goal> goal;
	List<CashFlow> cashFlow;
	CashFlowSummary cashFlowSummary;
	List<Networth> networth;
	NetworthSummary networthSummary;
	List<Priority> priority;
	InsuranceItem insurance;
	List<RiskProfile> riskProfile;
	RiskSummary riskSummary;

	public List<Goal> getGoal() {
		return goal;
	}

	public void setGoal(List<Goal> goal) {
		this.goal = goal;
	}

	public List<CashFlow> getCashFlow() {
		return cashFlow;
	}

	public void setCashFlow(List<CashFlow> cashFlow) {
		this.cashFlow = cashFlow;
	}

	public CashFlowSummary getCashFlowSummary() {
		return cashFlowSummary;
	}

	public void setCashFlowSummary(CashFlowSummary cashFlowSummary) {
		this.cashFlowSummary = cashFlowSummary;
	}

	public List<Networth> getNetworth() {
		return networth;
	}

	public void setNetworth(List<Networth> networth) {
		this.networth = networth;
	}

	public NetworthSummary getNetworthSummary() {
		return networthSummary;
	}

	public void setNetworthSummary(NetworthSummary networthSummary) {
		this.networthSummary = networthSummary;
	}

	public List<Priority> getPriority() {
		return priority;
	}

	public void setPriority(List<Priority> priority) {
		this.priority = priority;
	}

	public InsuranceItem getInsurance() {
		return insurance;
	}

	public void setInsurance(InsuranceItem insurance) {
		this.insurance = insurance;
	}

	public List<RiskProfile> getRiskProfile() {
		return riskProfile;
	}

	public void setRiskProfile(List<RiskProfile> riskProfile) {
		this.riskProfile = riskProfile;
	}

	public RiskSummary getRiskSummary() {
		return riskSummary;
	}

	public void setRiskSummary(RiskSummary riskSummary) {
		this.riskSummary = riskSummary;
	}

}
