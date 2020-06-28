package com.sowisetech.calc.request;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PlanRequest {

	long partyId;
	long parentPartyId;
	String name;
	String age;
	List<String> selectedPlan;
	String spouse;
	String parents;
	String children;
	String grandParent;
	String sibilings;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public List<String> getSelectedPlan() {
		return selectedPlan;
	}

	public void setSelectedPlan(List<String> selectedPlan) {
		this.selectedPlan = selectedPlan;
	}

	public String getSpouse() {
		return spouse;
	}

	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}

	public String getParents() {
		return parents;
	}

	public void setParents(String parents) {
		this.parents = parents;
	}

	public String getChildren() {
		return children;
	}

	public void setChildren(String children) {
		this.children = children;
	}

	public String getGrandParent() {
		return grandParent;
	}

	public void setGrandParent(String grandParent) {
		this.grandParent = grandParent;
	}

	public String getSibilings() {
		return sibilings;
	}

	public void setSibilings(String sibilings) {
		this.sibilings = sibilings;
	}

}
