package com.sowisetech.advisor.request;

import org.springframework.stereotype.Component;

@Component
public class IdRequest {
	public long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;

}
}
