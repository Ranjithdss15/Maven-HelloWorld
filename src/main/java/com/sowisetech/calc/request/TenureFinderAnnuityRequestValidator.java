package com.sowisetech.calc.request;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sowisetech.calc.util.CalcAppMessages;
import com.sowisetech.calc.util.CalcConstants;
import com.sowisetech.calc.util.CalcCommon;

@Component
public class TenureFinderAnnuityRequestValidator {
	
	@Autowired
	CalcAppMessages appmessages;
	
	@Autowired
	CalcCommon common;

	public HashMap<String, HashMap<String, String>> validate(TenureFinderAnnuityRequest tenureFinderAnnuityRequest) {
		HashMap<String, HashMap<String, String>> allErrors = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> error = new HashMap<String, String>();
		error.put("EMPTY", appmessages.getValue_empty());
		if (tenureFinderAnnuityRequest == null) {
			allErrors.put("NULL", error);
		} else {
			if (tenureFinderAnnuityRequest != null && tenureFinderAnnuityRequest.getFutureValue() != null) {
				error = validateFutureValue(tenureFinderAnnuityRequest.getFutureValue());
				if (error.isEmpty() == false) {
					allErrors.put("FUTUREVALUE", error);
				}
			}
			if (tenureFinderAnnuityRequest != null && tenureFinderAnnuityRequest.getPresentValue() != null) {
				error = validatePresentValue(tenureFinderAnnuityRequest.getPresentValue());
				if (error.isEmpty() == false) {
					allErrors.put("PRESENTVALUE", error);
				}
			}
			if (tenureFinderAnnuityRequest != null && tenureFinderAnnuityRequest.getRateOfInterest() != null) {
				error = validateRateOfInterest(tenureFinderAnnuityRequest.getRateOfInterest());
				if (error.isEmpty() == false) {
					allErrors.put("RATE_OF_INTEREST", error);
				}
			}
		}
		return allErrors;
	}

	protected HashMap<String, String> validateRateOfInterest(String rateOfInterest) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.RATE_OF_INTEREST;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(rateOfInterest, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(rateOfInterest, inputParamName));
		}
		if (common.isValidDoubleNumber(rateOfInterest, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(rateOfInterest, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validatePresentValue(String presentValue) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.PRESENTVALUE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(presentValue, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(presentValue, inputParamName));
		}
		if (common.isValidDoubleNumber(presentValue, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(presentValue, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateFutureValue(String futureValue) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.FUTUREVALUE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(futureValue, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(futureValue, inputParamName));
		}
		if (common.isValidDoubleNumber(futureValue, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(futureValue, inputParamName));
		}
		return errors;
	}
}
