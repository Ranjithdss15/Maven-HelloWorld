package com.sowisetech.calc.model;

public class Networth {

	long networthId;
	int accountEntryId;
	String accountEntry;
	int accountTypeId;
	String accountType;
	double value;
	double futureValue;
	long partyId;

	public long getNetworthId() {
		return networthId;
	}

	public void setNetworthId(long networthId) {
		this.networthId = networthId;
	}

	public int getAccountEntryId() {
		return accountEntryId;
	}

	public void setAccountEntryId(int accountEntryId) {
		this.accountEntryId = accountEntryId;
	}

	public String getAccountEntry() {
		return accountEntry;
	}

	public void setAccountEntry(String accountEntry) {
		this.accountEntry = accountEntry;
	}

	public int getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(int accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getFutureValue() {
		return futureValue;
	}

	public void setFutureValue(double futureValue) {
		this.futureValue = futureValue;
	}

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

}
