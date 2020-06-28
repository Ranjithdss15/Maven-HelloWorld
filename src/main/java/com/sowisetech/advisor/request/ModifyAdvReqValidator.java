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
public class ModifyAdvReqValidator {

	@Autowired
	AppMessages appMessages;
	
	@Autowired
	Common common;

	public HashMap<String, HashMap<String, String>> validate(ModifyAdvRequest modifyAdvRequest) {
		HashMap<String, HashMap<String, String>> allErrors = new HashMap<String, HashMap<String, String>>();
		// List<String> validErrors = new ArrayList<>();
		HashMap<String, String> error = new HashMap<String, String>();
		error.put("EMPTY", appMessages.getAdvisor_detail_empty());
		if (modifyAdvRequest == null) {
			allErrors.put("NULL", error);
		} else {
			if (modifyAdvRequest != null && modifyAdvRequest.getName() != null) {
				error = validateName(modifyAdvRequest.getName());
				if (error.isEmpty() == false) {
					allErrors.put("NAME", error);
				}
			}
			if (modifyAdvRequest != null && modifyAdvRequest.getDesignation() != null) {
				error = validateDesignation(modifyAdvRequest.getDesignation());
				if (error.isEmpty() == false) {
					allErrors.put("DESIGNATION", error);
				}
			}
			if (modifyAdvRequest != null && modifyAdvRequest.getEmailId() != null) {
				error = validateEmailid(modifyAdvRequest.getEmailId());
				if (error.isEmpty() == false) {
					allErrors.put("EMAILID", error);
				}
			}
			if (modifyAdvRequest != null && modifyAdvRequest.getPhoneNumber() != null) {
				error = validatePhoneNumber(modifyAdvRequest.getPhoneNumber());
				if (error.isEmpty() == false) {
					allErrors.put("PHONENUMBER", error);
				}
			}
			if (modifyAdvRequest != null && modifyAdvRequest.getDob() != null) {
				error = validateDob(modifyAdvRequest.getDob());
				if (error.isEmpty() == false) {
					allErrors.put("DOB", error);
				}
			}
			if (modifyAdvRequest != null && modifyAdvRequest.getGender() != null) {
				error = validateGender(modifyAdvRequest.getGender());
				if (error.isEmpty() == false) {
					allErrors.put("GENDER", error);
				}
			}
			if (modifyAdvRequest != null && modifyAdvRequest.getPanNumber() != null) {
				error = validatePan(modifyAdvRequest.getPanNumber());
				if (error.isEmpty() == false) {
					allErrors.put("PAN", error);
				}
			}
			if (modifyAdvRequest != null && modifyAdvRequest.getState() != null) {
				error = validateState(modifyAdvRequest.getState());
				if (error.isEmpty() == false) {
					allErrors.put("STATE", error);
				}
			}
			if (modifyAdvRequest != null && modifyAdvRequest.getCity() != null) {
				error = validateCity(modifyAdvRequest.getCity());
				if (error.isEmpty() == false) {
					allErrors.put("CITY", validateCity(modifyAdvRequest.getCity()));
				}
			}
			if (modifyAdvRequest != null && modifyAdvRequest.getPincode() != null) {
				error = validatePincode(modifyAdvRequest.getPincode());
				if (error.isEmpty() == false) {
					allErrors.put("PINCODE", validatePincode(modifyAdvRequest.getPincode()));
				}
			}

			/*
			 * Add the validation for each advisor attributes or column.
			 * 
			 * allErrors.addAll(validateRateOfInterest(modifyAdvRequest.getDisplayName()));
			 * allErrors.addAll(validateTenure(modifyAdvRequest.getDob()));
			 * allErrors.addAll(validateLoanDate(modifyAdvRequest.getEmailId())));
			 */
		}

		// for (String error : allErrors) {
		// if (StringUtils.isEmpty(error) == false) {
		// validErrors.add(error);
		// }
		//
		// }
		return allErrors;

	}

	protected HashMap<String, String> validateDesignation(String designation) {
		String inputParamDesignation = AdvisorConstants.SPACE_WTIH_COLON + AdvisorConstants.DESIGNATION;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(designation, inputParamDesignation).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(designation, inputParamDesignation));
		}
		if (common.isAlpha(designation, inputParamDesignation).isEmpty() == false) {
			errors.put("NON_ALPHA", common.isAlpha(designation, inputParamDesignation));
		}
		return errors;
	}

	// VALIDATION OF NAME
	protected HashMap<String, String> validateName(String name) {
		String inputParamName = AdvisorConstants.SPACE_WTIH_COLON + AdvisorConstants.NAME;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(name, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(name, inputParamName));
		}
		if (common.isAlpha(name, inputParamName).isEmpty() == false) {
			errors.put("NON_ALPHA", common.isAlpha(name, inputParamName));
		}
		return errors;

	}

	// VALIDATION OF EMAILID
	protected HashMap<String, String> validateEmailid(String mailid) {
		String inputParamMailid = AdvisorConstants.SPACE_WTIH_COLON + AdvisorConstants.EMAILID;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(mailid, inputParamMailid).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(mailid, inputParamMailid));
		}
		if (common.isValidEmailAddress(mailid, inputParamMailid).isEmpty() == false) {
			errors.put("FORMAT_ERROR", common.isValidEmailAddress(mailid, inputParamMailid));
		}
		return errors;

	}

	// VALIDATION OF MOBILENUMBER

	protected HashMap<String, String> validatePhoneNumber(String phoneNumber) {
		String inputParamPhoneNumber = AdvisorConstants.SPACE_WTIH_COLON + AdvisorConstants.PHONENUMBER;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(phoneNumber, inputParamPhoneNumber).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(phoneNumber, inputParamPhoneNumber));
		}
		if (common.isNumericValues(phoneNumber, inputParamPhoneNumber).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isNumericValues(phoneNumber, inputParamPhoneNumber));
		}
		if (common.phoneNumberlengthCheck(phoneNumber, inputParamPhoneNumber).isEmpty() == false) {
			errors.put("LENGTH_ERROR", common.phoneNumberlengthCheck(phoneNumber, inputParamPhoneNumber));
		}
		return errors;

	}

	// VALIDATION OF DOB
	protected HashMap<String, String> validateDob(String dob) {
		String inputParamDob = AdvisorConstants.SPACE_WTIH_COLON + AdvisorConstants.DOB;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(dob, inputParamDob).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(dob, inputParamDob));
		}
		if (common.dobCheck(dob, inputParamDob).isEmpty() == false) {
			errors.put("FORMAT_ERROR", common.dobCheck(dob, inputParamDob));
		}
		return errors;
	}

	// VALIDATION OF GENDER
	protected HashMap<String, String> validateGender(String gender) {
		String inputParamGender = AdvisorConstants.SPACE_WTIH_COLON + AdvisorConstants.GENDER;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(gender, inputParamGender).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(gender, inputParamGender));
		}
		if (common.genderCheck(gender, inputParamGender).isEmpty() == false) {
			errors.put("FORMAT_ERROR", common.genderCheck(gender, inputParamGender));
		}
		return errors;
	}

	// PAN number validation
	protected HashMap<String, String> validatePan(String pan) {
		String inputParamName = AdvisorConstants.SPACE_WTIH_COLON + AdvisorConstants.PAN;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.validPan(pan, inputParamName).isEmpty() == false) {
			errors.put("FORMAT_ERROR", common.validPan(pan, inputParamName));
		}
		return errors;
	}

	// VALIDATION OF DISPLAYNAME
	protected HashMap<String, String> validateState(String state) {
		String inputParamState = AdvisorConstants.SPACE_WTIH_COLON + AdvisorConstants.STATE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(state, inputParamState).isEmpty() == false) {
			errors.put("Empty", common.nonEmptyCheck(state, inputParamState));
		}
		if (common.isAlpha(state, inputParamState).isEmpty() == false) {
			errors.put("NON_ALPHA", common.isAlpha(state, inputParamState));
		}
		return errors;
	}

	// VALIDATION OF DISPLAYNAME
	protected HashMap<String, String> validateCity(String city) {
		String inputParamCity = AdvisorConstants.SPACE_WTIH_COLON + AdvisorConstants.STATE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(city, inputParamCity).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(city, inputParamCity));
		}
		if (common.isAlpha(city, inputParamCity).isEmpty() == false) {
			errors.put("NON_ALPHA", common.isAlpha(city, inputParamCity));
		}
		return errors;
	}
	// VALIDATION OF PINCODE

	protected HashMap<String, String> validatePincode(String pincode) {
		String inputParamPincode = AdvisorConstants.SPACE_WTIH_COLON + AdvisorConstants.PINCODE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(pincode, inputParamPincode).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(pincode, inputParamPincode));
		}
		if (common.isNumericValues(pincode, inputParamPincode).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isNumericValues(pincode, inputParamPincode));
		}
		if (common.pincodelengthCheck(pincode, inputParamPincode).isEmpty() == false) {
			errors.put("LENGTH_ERROR", common.pincodelengthCheck(pincode, inputParamPincode));
		}
		return errors;
	}
}
