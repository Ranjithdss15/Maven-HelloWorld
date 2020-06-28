package com.sowisetech.calc.model;

public class Priority {

	long priorityId;
	int priorityItemId;
	String priorityItem;
	long partyId;
	double value;
	int timeLine;
	int urgencyId;
	String urgency;
	int priorityOrder;

	public long getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(long priorityId) {
		this.priorityId = priorityId;
	}

	public int getPriorityItemId() {
		return priorityItemId;
	}

	public void setPriorityItemId(int priorityItemId) {
		this.priorityItemId = priorityItemId;
	}

	public String getPriorityItem() {
		return priorityItem;
	}

	public void setPriorityItem(String priorityItem) {
		this.priorityItem = priorityItem;
	}

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getTimeLine() {
		return timeLine;
	}

	public void setTimeLine(int timeLine) {
		this.timeLine = timeLine;
	}

	public int getUrgencyId() {
		return urgencyId;
	}

	public void setUrgencyId(int urgencyId) {
		this.urgencyId = urgencyId;
	}

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	public int getPriorityOrder() {
		return priorityOrder;
	}

	public void setPriorityOrder(int priorityOrder) {
		this.priorityOrder = priorityOrder;
	}

}
