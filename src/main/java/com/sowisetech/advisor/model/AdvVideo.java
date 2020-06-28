package com.sowisetech.advisor.model;

public class AdvVideo {
	private String advId;
	private long videoId;
	private String title;
	private String aboutVideo;
	private String video;

	public String getAdvId() {
		return advId;
	}

	public void setAdvId(String advId) {
		this.advId = advId;
	}

	public String getTitle() {
		return title;
	}

	public long getVideoId() {
		return videoId;
	}

	public void setVideoId(long videoId) {
		this.videoId = videoId;
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
