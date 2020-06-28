package com.sowisetech.calc.request;

import org.springframework.stereotype.Component;

@Component
public class GoalRequest {

	String goalName;
	String tenure;
	String tenureType;
	String goalAmount;
	String inflationRate;
	String currentAmount;
	String growthRate;
	String annualInvestmentRate;
	long partyId;
	long parentPartyId;

	public String getGoalName() {
		return goalName;
	}

	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}

	public String getTenure() {
		return tenure;
	}

	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	public String getTenureType() {
		return tenureType;
	}

	public void setTenureType(String tenureType) {
		this.tenureType = tenureType;
	}

	public String getGoalAmount() {
		return goalAmount;
	}

	public void setGoalAmount(String goalAmount) {
		this.goalAmount = goalAmount;
	}

	public String getInflationRate() {
		return inflationRate;
	}

	public void setInflationRate(String inflationRate) {
		this.inflationRate = inflationRate;
	}

	public String getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(String currentAmount) {
		this.currentAmount = currentAmount;
	}

	public String getGrowthRate() {
		return growthRate;
	}

	public void setGrowthRate(String growthRate) {
		this.growthRate = growthRate;
	}

	public String getAnnualInvestmentRate() {
		return annualInvestmentRate;
	}

	public void setAnnualInvestmentRate(String annualInvestmentRate) {
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

}
