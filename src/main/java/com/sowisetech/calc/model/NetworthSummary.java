package com.sowisetech.calc.model;

public class NetworthSummary {

	long networthSummaryId;
	long partyId;
	double current_assetValue;
	double current_liability;
	double networth;
	double future_assetValue;
	double future_liability;
	double future_networth;

	public long getNetworthSummaryId() {
		return networthSummaryId;
	}

	public void setNetworthSummaryId(long networthSummaryId) {
		this.networthSummaryId = networthSummaryId;
	}

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

	public double getCurrent_assetValue() {
		return current_assetValue;
	}

	public void setCurrent_assetValue(double current_assetValue) {
		this.current_assetValue = current_assetValue;
	}

	public double getCurrent_liability() {
		return current_liability;
	}

	public void setCurrent_liability(double current_liability) {
		this.current_liability = current_liability;
	}

	public double getNetworth() {
		return networth;
	}

	public void setNetworth(double networth) {
		this.networth = networth;
	}

	public double getFuture_assetValue() {
		return future_assetValue;
	}

	public void setFuture_assetValue(double future_assetValue) {
		this.future_assetValue = future_assetValue;
	}

	public double getFuture_liability() {
		return future_liability;
	}

	public void setFuture_liability(double future_liability) {
		this.future_liability = future_liability;
	}

	public double getFuture_networth() {
		return future_networth;
	}

	public void setFuture_networth(double future_networth) {
		this.future_networth = future_networth;
	}

}
