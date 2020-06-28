package com.sowisetech.advisor.request;

import org.springframework.stereotype.Component;

@Component
public class VideoReq {
	private String title;
	private String aboutVideo;
	private String video;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAboutVideo() {
		return aboutVideo;
	}
	public void setAboutVideo(String aboutVideo) {
		this.aboutVideo = aboutVideo;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	
}
