package com.sowisetech.calc.model;

public class RiskProfile {

	long riskProfileId;
	long partyId;
	String questionId;
	int answerId;
	int score;

	public long getRiskProfileId() {
		return riskProfileId;
	}

	public void setRiskProfileId(long riskProfileId) {
		this.riskProfileId = riskProfileId;
	}

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
