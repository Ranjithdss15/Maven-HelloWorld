//package com.sowisetech.advisor.request;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import org.apache.commons.lang3.StringUtils;
//import org.junit.Before;
//import org.junit.Test;
//
//public class AdvVideoRequestValidatorTest {
//
//	
//	AdvVideoRequestValidator advVideoRequestValidator;
//	
//
//	@Before
//	public void setUp() throws Exception {
//		advVideoRequestValidator = new AdvVideoRequestValidator();
//	}
//	
//	@Test
//	public void validateVideoTest() {
//	List<VideoReq> videoReq=new ArrayList<VideoReq>();
//	VideoReq video=new VideoReq();
//	video.setTitle("advisor");
//	video.setAboutVideo("Intro");
//	video.setVideo("video.mp4");
//	videoReq.add(video);
//	HashMap<String, String> errors = new HashMap<String, String>();
//	errors = advVideoRequestValidator.validateVideos(videoReq);
//	assertTrue(errors.size()==0);
//	}
//
//}
