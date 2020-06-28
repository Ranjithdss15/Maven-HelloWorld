package com.sowisetech.calc.model;

public class CashFlowSummary {

	long cashFlowSummaryId;
	long partyId;
	double monthlyRecurExpense;
	double yearlyRecurExpense;
	double nonRecurExpense;
	double monthlyRecurIncome;
	double yearlyRecurIncome;
	double nonRecurIncome;
	double monthlyRecurCashFlow;
	double yearlyRecurCashFlow;
	double nonRecurCashflow;

	public long getCashFlowSummaryId() {
		return cashFlowSummaryId;
	}

	public void setCashFlowSummaryId(long cashFlowSummaryId) {
		this.cashFlowSummaryId = cashFlowSummaryId;
	}

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

	public double getMonthlyRecurExpense() {
		return monthlyRecurExpense;
	}

	public void setMonthlyRecurExpense(double monthlyRecurExpense) {
		this.monthlyRecurExpense = monthlyRecurExpense;
	}

	public double getYearlyRecurExpense() {
		return yearlyRecurExpense;
	}

	public void setYearlyRecurExpense(double yearlyRecurExpense) {
		this.yearlyRecurExpense = yearlyRecurExpense;
	}

	public double getNonRecurExpense() {
		return nonRecurExpense;
	}

	public void setNonRecurExpense(double nonRecurExpense) {
		this.nonRecurExpense = nonRecurExpense;
	}

	public double getMonthlyRecurIncome() {
		return monthlyRecurIncome;
	}

	public void setMonthlyRecurIncome(double monthlyRecurIncome) {
		this.monthlyRecurIncome = monthlyRecurIncome;
	}

	public double getYearlyRecurIncome() {
		return yearlyRecurIncome;
	}

	public void setYearlyRecurIncome(double yearlyRecurIncome) {
		this.yearlyRecurIncome = yearlyRecurIncome;
	}

	public double getNonRecurIncome() {
		return nonRecurIncome;
	}

	public void setNonRecurIncome(double nonRecurIncome) {
		this.nonRecurIncome = nonRecurIncome;
	}

	public double getMonthlyRecurCashFlow() {
		return monthlyRecurCashFlow;
	}

	public void setMonthlyRecurCashFlow(double monthlyRecurCashFlow) {
		this.monthlyRecurCashFlow = monthlyRecurCashFlow;
	}

	public double getYearlyRecurCashFlow() {
		return yearlyRecurCashFlow;
	}

	public void setYearlyRecurCashFlow(double yearlyRecurCashFlow) {
		this.yearlyRecurCashFlow = yearlyRecurCashFlow;
	}

	public double getNonRecurCashflow() {
		return nonRecurCashflow;
	}

	public void setNonRecurCashflow(double nonRecurCashflow) {
		this.nonRecurCashflow = nonRecurCashflow;
	}

}
