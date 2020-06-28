package com.sowisetech.advisor.security;

import java.io.Serializable;

public class JwtResponse implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final String advId;
	private final long partyId;

	public JwtResponse(String jwttoken, String advId, long partyId) {
		this.jwttoken = jwttoken;
		this.advId = advId;
		this.partyId = partyId;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public String getAdvId() {
		return advId;
	}

	public long getPartyId() {
		return partyId;
	}
}