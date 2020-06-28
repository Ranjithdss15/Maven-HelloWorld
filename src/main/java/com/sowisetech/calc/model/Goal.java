package com.sowisetech.calc.model;

public class Goal {

	long goalId;
	String goalName;
	int tenure;
	String tenureType;
	double goalAmount;
	double inflationRate;
	double currentAmount;
	double growthRate;
	double rateOfReturn;
	double annualInvestmentRate;
	long partyId;
	long parentPartyId;
	double futureCost;
	double futureValue;
	double finalCorpus;
	double monthlyInv;
	double annualInv;

	public long getGoalId() {
		return goalId;
	}

	public void setGoalId(long goalId) {
		this.goalId = goalId;
	}

	public String getGoalName() {
		return goalName;
	}

	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public String getTenureType() {
		return tenureType;
	}

	public void setTenureType(String tenureType) {
		this.tenureType = tenureType;
	}

	public double getGoalAmount() {
		return goalAmount;
	}

	public void setGoalAmount(double goalAmount) {
		this.goalAmount = goalAmount;
	}

	public double getInflationRate() {
		return inflationRate;
	}

	public void setInflationRate(double inflationRate) {
		this.inflationRate = inflationRate;
	}

	public double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}

	public double getGrowthRate() {
		return growthRate;
	}

	public void setGrowthRate(double growthRate) {
		this.growthRate = growthRate;
	}

	public double getRateOfReturn() {
		return rateOfReturn;
	}

	public void setRateOfReturn(double rateOfReturn) {
		this.rateOfReturn = rateOfReturn;
	}

	public double getAnnualInvestmentRate() {
		return annualInvestmentRate;
	}

	public void setAnnualInvestmentRate(double annualInvestmentRate) {
		this.annualInvestmentRate = annualInvestmentRate;
	}

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

	public long getParentPartyId() {
		return parentPartyId;
	}

	public void setParentPartyId(long parentPartyId) {
		this.parentPartyId = parentPartyId;
	}

	public double getFutureCost() {
		return futureCost;
	}

	public void setFutureCost(double futureCost) {
		this.futureCost = futureCost;
	}

	public double getFutureValue() {
		return futureValue;
	}

	public void setFutureValue(double futureValue) {
		this.futureValue = futureValue;
	}

	public double getFinalCorpus() {
		return finalCorpus;
	}

	public void setFinalCorpus(double finalCorpus) {
		this.finalCorpus = finalCorpus;
	}

	public double getMonthlyInv() {
		return monthlyInv;
	}

	public void setMonthlyInv(double monthlyInv) {
		this.monthlyInv = monthlyInv;
	}

	public double getAnnualInv() {
		return annualInv;
	}

	public void setAnnualInv(double annualInv) {
		this.annualInv = annualInv;
	}

}
