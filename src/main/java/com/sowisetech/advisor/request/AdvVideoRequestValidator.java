package com.sowisetech.advisor.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sowisetech.advisor.util.AdvisorConstants;
import com.sowisetech.advisor.util.AppMessages;
import com.sowisetech.advisor.util.Common;

@Component
public class AdvVideoRequestValidator {
	
	@Autowired
	AppMessages appMessages;
	
	@Autowired
	Common common;

	public HashMap<String, HashMap<String, String>> validate(AdvVideoReq advVideoReq) {
		HashMap<String, HashMap<String, String>> allErrors = new HashMap<String, HashMap<String, String>>();
		// List<String> validErrors = new ArrayList<>();
		HashMap<String, String> error = new HashMap<String, String>();
		error.put("EMPTY", appMessages.getAdvisor_detail_empty());
		if (advVideoReq == null) {
			allErrors.put("NULL",error);
		} else {
			if (advVideoReq != null && advVideoReq.getVideos() != null) {
				error=validateVideos(advVideoReq.getVideos());
				if(error.isEmpty()==false) {
				allErrors.put("VIDEO",error);}
			}

		}

//		for (String error : allErrors) {
//			if (StringUtils.isEmpty(error) == false) {
//				validErrors.add(error);
//			}
//
//		}
		return allErrors;

	}

	public HashMap<String, String> validateVideos(List<VideoReq> videoReq) {
		String inputParamName = AdvisorConstants.SPACE_WTIH_COLON + AdvisorConstants.VIDEO;
		HashMap<String, String> errors = new HashMap<String, String>();
		for (VideoReq video : videoReq) {
			if(common.isAlpha(video.getTitle(), inputParamName).isEmpty()==false) {
			errors.put("TITLE",common.isAlpha(video.getTitle(), inputParamName));}
//			if(Common.isAlpha(video.getAboutVideo(), inputParamName).isEmpty()==false) {
//			errors.put("ABOUT_VIDEO",Common.isAlpha(video.getAboutVideo(), inputParamName));}
//			if(Common.isVideo(video.getVideo(), inputParamName).isEmpty()==false) {
//			errors.put("VIDEO",Common.isVideo(video.getVideo(), inputParamName));}

		}
		return errors;
	}
}
