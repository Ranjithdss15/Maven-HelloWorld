package com.sowisetech.advisor.request;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sowisetech.advisor.util.AdvisorConstants;
import com.sowisetech.advisor.util.AppMessages;
import com.sowisetech.advisor.util.Common;

@Component
public class AdvisorRequestValidator implements IValidator {
	
	@Autowired
	AppMessages appMessages;
	
	@Autowired
	Common common;

	/**
	 * Basic validation on HomeLoanRequest input values.
	 * 
	 * @param homeLoanRequest
	 * @return - Empty list or list of errors in string format.
	 */
	public HashMap<String, HashMap<String, String>> validate(AdvisorRequest advisorRequest) {
		HashMap<String, HashMap<String,String>> allErrors = new HashMap<String,HashMap<String,String>>();
//		List<String> validErrors = new ArrayList<>();
		HashMap<String,String> error = new HashMap<String,String>();
		error.put("EMPTY",appMessages.getAdvisor_detail_empty());
		if (advisorRequest == null) {
			allErrors.put("NULL",error);
		} else {
			if (advisorRequest != null && advisorRequest.getName() != null) {
				error=validateName(advisorRequest.getName());
				if(error.isEmpty()==false) {
				allErrors.put("NAME",error);}
			}
			if (advisorRequest != null && advisorRequest.getEmailId() != null) {
				error=validateEmailid(advisorRequest.getEmailId());
				if(error.isEmpty()==false) {
				allErrors.put("EMAILID",error);}
			}
			if (advisorRequest != null && advisorRequest.getPanNumber() != null) {
				error=validatePan(advisorRequest.getPanNumber());
				if(error.isEmpty()==false) {
				allErrors.put("PAN",error);}
			}
			if (advisorRequest != null && advisorRequest.getPhoneNumber() != null) {
				error=validatePhoneNumber(advisorRequest.getPhoneNumber());
				if(error.isEmpty()==false) {
				allErrors.put("PHONENUMBER",error);}
			}
			if (advisorRequest != null && advisorRequest.getPassword() != null) {
				error=validatePassword(advisorRequest.getPassword());
				if(error.isEmpty()==false) {
				allErrors.put("PASSWORD",error);}
			}

			/*
			 * Add the validation for each advisor attributes or column.
			 * 
			 * allErrors.addAll(validateRateOfInterest(advisorRequest.getDisplayName()));
			 * allErrors.addAll(validateTenure(advisorRequest.getDob()));
			 * allErrors.addAll(validateLoanDate(advisorRequest.getEmailId())));
			 */
		}

//		for (String error : allErrors) {
//			if (StringUtils.isEmpty(error) == false) {
//				validErrors.add(error);
//			}
//
//		}
//		return validErrors;
		return allErrors;
	}
	
	

	// VALIDATION OF NAME
	protected HashMap<String, String> validateName(String name) {
		String inputParamName = AdvisorConstants.SPACE_WTIH_COLON + AdvisorConstants.NAME;
		HashMap<String,String> errors = new HashMap<String,String>();
		if(common.nonEmptyCheck(name, inputParamName).isEmpty()==false) {
		errors.put("EMPTY",common.nonEmptyCheck(name, inputParamName));}
		if(common.isAlpha(name, inputParamName).isEmpty()==false) {
		errors.put("NON_ALPHA",common.isAlpha(name, inputParamName));}
		return errors;

	}
	// VALIDATION OF EMAILID
	protected HashMap<String, String> validateEmailid(String mailid) {
		String inputParamMailid = AdvisorConstants.SPACE_WTIH_COLON + AdvisorConstants.EMAILID;
		HashMap<String,String> errors = new HashMap<String,String>();
		if(common.nonEmptyCheck(mailid, inputParamMailid).isEmpty()==false) {
		errors.put("EMPTY",common.nonEmptyCheck(mailid, inputParamMailid));}
		if(common.isValidEmailAddress(mailid, inputParamMailid).isEmpty()==false) {
		errors.put("FORMAT_ERROR",common.isValidEmailAddress(mailid, inputParamMailid));}
		return errors;

	}
	// PAN number validation
		protected HashMap<String, String> validatePan(String pan) {
			String inputParamName = AdvisorConstants.SPACE_WTIH_COLON + AdvisorConstants.PAN;
			HashMap<String, String> errors = new HashMap<String, String>();
			if(common.validPan(pan, inputParamName).isEmpty()==false) {
			errors.put("FORMAT_ERROR",common.validPan(pan, inputParamName));}
			return errors;
		}

	// VALIDATION OF MOBILENUMBER

	protected HashMap<String, String> validatePhoneNumber(String phoneNumber) {
		String inputParamPhoneNumber = AdvisorConstants.SPACE_WTIH_COLON + AdvisorConstants.PHONENUMBER;
		HashMap<String,String> errors = new HashMap<String,String>();
		if(common.nonEmptyCheck(phoneNumber, inputParamPhoneNumber).isEmpty()==false) {
		errors.put("EMPTY",common.nonEmptyCheck(phoneNumber, inputParamPhoneNumber));}
		if(common.isNumericValues(phoneNumber, inputParamPhoneNumber).isEmpty()==false) {
		errors.put("NON_NUMERIC",common.isNumericValues(phoneNumber, inputParamPhoneNumber));}
		if(common.phoneNumberlengthCheck(phoneNumber, inputParamPhoneNumber).isEmpty()==false) {
		errors.put("LENGTH_ERROR",common.phoneNumberlengthCheck(phoneNumber, inputParamPhoneNumber));}
		return errors;

	}

	// VALIDATION OF PASSWORD

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
