package com.sowisetech.advisor.request;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sowisetech.advisor.model.AdvVideo;

@Component
public class AdvVideoReq {

	private List<VideoReq> videos;
	private String advId;

	public String getAdvId() {
		return advId;
	}

	public void setAdvId(String advId) {
		this.advId = advId;
	}

	public List<VideoReq> getVideos() {
		return videos;
	}

	public void setVideos(List<VideoReq> videos) {
		this.videos = videos;
	}

}
