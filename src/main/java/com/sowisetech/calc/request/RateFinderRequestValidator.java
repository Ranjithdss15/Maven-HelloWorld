package com.sowisetech.calc.request;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sowisetech.calc.util.CalcAppMessages;
import com.sowisetech.calc.util.CalcConstants;
import com.sowisetech.calc.util.CalcCommon;

@Component
public class RateFinderRequestValidator {

	@Autowired
	CalcAppMessages appmessages;
	
	@Autowired
	CalcCommon common;
	
	public HashMap<String, HashMap<String, String>> validate(RateFinderRequest rateFinderRequest) {
		HashMap<String, HashMap<String, String>> allErrors = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> error = new HashMap<String, String>();
		error.put("EMPTY", appmessages.getValue_empty());
		if (rateFinderRequest == null) {
			allErrors.put("NULL", error);
		} else {
			if (rateFinderRequest != null && rateFinderRequest.getInvType() != null) {
				error = validateInvType(rateFinderRequest.getInvType());
				if (error.isEmpty() == false) {
					allErrors.put("INVESTMENT_TYPE", error);
				}
			}
			if (rateFinderRequest != null && rateFinderRequest.getPresentValue() != null) {
				error = validatePresentValue(rateFinderRequest.getPresentValue());
				if (error.isEmpty() == false) {
					allErrors.put("PRESENTVALUE", error);
				}
			}
			if (rateFinderRequest != null && rateFinderRequest.getDuration() != null) {
				error = validateDuration(rateFinderRequest.getDuration());
				if (error.isEmpty() == false) {
					allErrors.put("DURATION", error);
				}
			}
			if (rateFinderRequest != null && rateFinderRequest.getFutureValue() != null) {
				error = validateFutureValue(rateFinderRequest.getFutureValue());
				if (error.isEmpty() == false) {
					allErrors.put("FUTUREVALUE", error);
				}
			}
		}

		return allErrors;
	}

	protected HashMap<String, String> validateInvType(String invType) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.INV_TYPE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(invType, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(invType, inputParamName));
		}
		if (common.isValidInvestmentType(invType, inputParamName).isEmpty() == false) {
			errors.put("NOT_VALID", common.isValidInvestmentType(invType, inputParamName));
		}
		return errors;
	}

	private HashMap<String, String> validateFutureValue(String futureValue) {
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

	private HashMap<String, String> validateDuration(String duration) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.DURATION;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(duration, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(duration, inputParamName));
		}
		if (common.isNumericValues(duration, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isNumericValues(duration, inputParamName));
		}
		return errors;
	}

	private HashMap<String, String> validatePresentValue(String presentValue) {
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

}
