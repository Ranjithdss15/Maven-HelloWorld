package com.sowisetech.calc.model;

public class CashFlow {

	long cashFlowId;
	long partyId;
	int cashFlowItemId;
	String cashFlowItem;
	int cashFlowItemTypeId;
	String cashFlowItemType;
	double budgetAmt;
	double actualAmt;
	String date;

	public long getCashFlowId() {
		return cashFlowId;
	}

	public void setCashFlowId(long cashFlowId) {
		this.cashFlowId = cashFlowId;
	}

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

	public int getCashFlowItemId() {
		return cashFlowItemId;
	}

	public void setCashFlowItemId(int cashFlowItemId) {
		this.cashFlowItemId = cashFlowItemId;
	}

	public String getCashFlowItem() {
		return cashFlowItem;
	}

	public void setCashFlowItem(String cashFlowItem) {
		this.cashFlowItem = cashFlowItem;
	}

	public int getCashFlowItemTypeId() {
		return cashFlowItemTypeId;
	}

	public void setCashFlowItemTypeId(int cashFlowItemTypeId) {
		this.cashFlowItemTypeId = cashFlowItemTypeId;
	}

	public String getCashFlowItemType() {
		return cashFlowItemType;
	}

	public void setCashFlowItemType(String cashFlowItemType) {
		this.cashFlowItemType = cashFlowItemType;
	}

	public double getBudgetAmt() {
		return budgetAmt;
	}

	public void setBudgetAmt(double budgetAmt) {
		this.budgetAmt = budgetAmt;
	}

	public double getActualAmt() {
		return actualAmt;
	}

	public void setActualAmt(double actualAmt) {
		this.actualAmt = actualAmt;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
