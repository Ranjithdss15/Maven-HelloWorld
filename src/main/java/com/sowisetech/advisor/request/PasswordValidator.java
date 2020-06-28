package com.sowisetech.advisor.request;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sowisetech.advisor.util.AdvisorConstants;
import com.sowisetech.advisor.util.AppMessages;
import com.sowisetech.advisor.util.Common;

@Component
public class PasswordValidator {

	@Autowired
	AppMessages appMessages;
	
	@Autowired
	Common common;
	
	public HashMap<String, HashMap<String, String>> validate(PasswordChangeRequest passwordChangeRequest) {
		HashMap<String, HashMap<String, String>> allErrors = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> error = new HashMap<String, String>();
		error.put("EMPTY", appMessages.getAdvisor_detail_empty());
		if (passwordChangeRequest == null) {
			allErrors.put("NULL", error);
		} else {
			if (passwordChangeRequest != null && passwordChangeRequest.getNewPassword() != null) {
				error = validatePassword(passwordChangeRequest.getNewPassword());
				if (error.isEmpty() == false) {
					allErrors.put("PASSWORD", error);
				}
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

	protected HashMap<String, String> validatePassword(String password) {
		String inputParamPassword = AdvisorConstants.SPACE_WTIH_COLON + AdvisorConstants.PASSWORD;
		HashMap<String,String> errors = new HashMap<String,String>();
		if(common.nonEmptyCheck(password, inputParamPassword).isEmpty()==false) {
		errors.put("EMPTY",common.nonEmptyCheck(password, inputParamPassword));}
		if(common.validPasswordCheck(password, inputParamPassword).isEmpty()==false) {
		errors.put("FORMAT_ERROR",common.validPasswordCheck(password, inputParamPassword));}
		return errors;
	}
}
