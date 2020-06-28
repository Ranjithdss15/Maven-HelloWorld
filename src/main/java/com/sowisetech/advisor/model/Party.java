package com.sowisetech.advisor.model;

import java.sql.Timestamp;

public class Party {

	private long partyId;
	private long partyStatusId;
	private long roleId;
	private long roleBasedId;
	private String delete_flag;

	private Timestamp created;
	private Timestamp updated;

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public String getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(String delete_flag) {
		this.delete_flag = delete_flag;
	}

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

	public long getPartyStatusId() {
		return partyStatusId;
	}

	public void setPartyStatusId(long partyStatusId) {
		this.partyStatusId = partyStatusId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getRoleBasedId() {
		return roleBasedId;
	}

	public void setRoleBasedId(long roleBasedId) {
		this.roleBasedId = roleBasedId;
	}

}
