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
public class AdvPersonalInfoRequestValidator implements IValidator {

	@Autowired
	AppMessages appMessages;
	
	@Autowired
	Common common;
	
	public HashMap<String, HashMap<String, String>> validate(AdvPersonalInfoRequest advPersonalInfoRequest) {
		HashMap<String, HashMap<String, String>> allErrors = new HashMap<String, HashMap<String, String>>();
		// List<String> validErrors = new ArrayList<>();
		HashMap<String, String> error = new HashMap<String, String>();
		error.put("EMPTY", appMessages.getAdvisor_detail_empty());
		if (advPersonalInfoRequest == null) {
			allErrors.put("NULL", error);
		} else {
			// if (advPersonalInfoRequest != null &&
			// advPersonalInfoRequest.getDisplayName() != null) {
			// allErrors.addAll(validateDisplayName(advPersonalInfoRequest.getDisplayName()));
			// }
			if (advPersonalInfoRequest != null && advPersonalInfoRequest.getDesignation() != null) {
				error = validateDesignation(advPersonalInfoRequest.getDesignation());
				if (error.isEmpty() == false) {
					allErrors.put("DESIGNATION", error);
				}
			}
			if (advPersonalInfoRequest != null && advPersonalInfoRequest.getDob() != null) {
				error = validateDob(advPersonalInfoRequest.getDob());
				if (error.isEmpty() == false) {
					allErrors.put("DOB", error);
				}
			}
			if (advPersonalInfoRequest != null && advPersonalInfoRequest.getGender() != null) {
				error = validateGender(advPersonalInfoRequest.getGender());
				if (error.isEmpty() == false) {
					allErrors.put("GENDER", error);
				}
			}

			if (advPersonalInfoRequest != null && advPersonalInfoRequest.getState() != null) {
				error = validateState(advPersonalInfoRequest.getState());
				if (error.isEmpty() == false) {
					allErrors.put("STATE", error);
				}
			}
			if (advPersonalInfoRequest != null && advPersonalInfoRequest.getCity() != null) {
				error = validateCity(advPersonalInfoRequest.getCity());
				if (error.isEmpty() == false) {
					allErrors.put("CITY", validateCity(advPersonalInfoRequest.getCity()));
				}
			}
			if (advPersonalInfoRequest != null && advPersonalInfoRequest.getPincode() != null) {
				error = validatePincode(advPersonalInfoRequest.getPincode());
				if (error.isEmpty() == false) {
					allErrors.put("PINCODE", validatePincode(advPersonalInfoRequest.getPincode()));
				}
			}
		}
		// for (String error : allErrors) {
		// if (StringUtils.isEmpty(error) == false) {
		// validErrors.add(error);
		// }
		//
		// }
		// return validErrors;
		return allErrors;

	}

	// // VALIDATION OF DISPLAYNAME
	// protected List<String> validateDisplayName(String displayname) {
	// String inputParamDisplayName = AdvisorConstants.SPACE_WTIH_COLON +
	// AdvisorConstants.DISPLAYNAME;
	// List<String> errors = new ArrayList<>();
	// errors.add(Common.nonEmptyCheck(displayname, inputParamDisplayName));
	// errors.add(Common.isAlpha(displayname, inputParamDisplayName));
	// return errors;
	// }

	// VALIDATION OF DESIGNATION
	HashMap<String, String> validateDesignation(String designation) {
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

	// VALIDATION OF STATE
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

	// VALIDATION OF CITY
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
