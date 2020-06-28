package com.sowisetech.calc.request;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sowisetech.calc.util.CalcAppMessages;
import com.sowisetech.calc.util.CalcConstants;
import com.sowisetech.calc.util.CalcCommon;

@Component
public class PresentValueAnnuityRequestValidator {
	
	@Autowired
	CalcAppMessages appmessages;
	
	@Autowired
	CalcCommon common;

	public HashMap<String, HashMap<String, String>> validate(PresentValueAnnuityRequest presentValueAnnuityRequest) {
		HashMap<String, HashMap<String, String>> allErrors = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> error = new HashMap<String, String>();
		error.put("EMPTY", appmessages.getValue_empty());
		if (presentValueAnnuityRequest == null) {
			allErrors.put("NULL", error);
		} else {
			if (presentValueAnnuityRequest != null && presentValueAnnuityRequest.getPeriodicAmount() != null) {
				error = validatePeriodicAmount(presentValueAnnuityRequest.getPeriodicAmount());
				if (error.isEmpty() == false) {
					allErrors.put("PERIODICAMOUNT", error);
				}
			}
			if (presentValueAnnuityRequest != null && presentValueAnnuityRequest.getAnnualGrowthRate() != null) {
				error = validateAnnualGrowthRate(presentValueAnnuityRequest.getAnnualGrowthRate());
				if (error.isEmpty() == false) {
					allErrors.put("ANNUALGROWTH", error);
				}
			}
		}
		if (presentValueAnnuityRequest != null && presentValueAnnuityRequest.getDuration() != null) {
			error = validateDuration(presentValueAnnuityRequest.getDuration());
			if (error.isEmpty() == false) {
				allErrors.put("DATE", error);
			}
		}
		return allErrors;

	}

	protected HashMap<String, String> validateDuration(String duration) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.ANNUALGROWTH;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(duration, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(duration, inputParamName));
		}
		if (common.isNumericValues(duration, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isNumericValues(duration, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateAnnualGrowthRate(String annualGrowthRate) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.ANNUALGROWTH;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(annualGrowthRate, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(annualGrowthRate, inputParamName));
		}
		if (common.isValidDoubleNumber(annualGrowthRate, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(annualGrowthRate, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validatePeriodicAmount(String periodicAmount) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.PERIODICAMOUNT;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(periodicAmount, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(periodicAmount, inputParamName));
		}
		if (common.isValidDoubleNumber(periodicAmount, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(periodicAmount, inputParamName));
		}
		return errors;
	}
}
