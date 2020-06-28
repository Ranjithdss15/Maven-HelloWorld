package com.sowisetech.calc.request;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sowisetech.calc.util.CalcAppMessages;
import com.sowisetech.calc.util.CalcConstants;
import com.sowisetech.calc.util.CalcCommon;

@Component
public class FutureValueAnnuityDueRequestValidator {
	
	@Autowired
	CalcAppMessages appmessages;
	
	@Autowired
	CalcCommon common;
	
	public HashMap<String, HashMap<String, String>> validate(FutureValueAnnuityDueRequest futureValueAnnuityDueRequest) {
		HashMap<String, HashMap<String, String>> allErrors = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> error = new HashMap<String, String>();
		error.put("EMPTY", appmessages.getValue_empty());
		if (futureValueAnnuityDueRequest == null) {
			allErrors.put("NULL", error);
		} else {
			if (futureValueAnnuityDueRequest != null && futureValueAnnuityDueRequest.getInvAmount() != null) {
				error = validateInvAmount(futureValueAnnuityDueRequest.getInvAmount());
				if (error.isEmpty() == false) {
					allErrors.put("INVESTMENT_AMOUNT", error);
				}
			}
			if (futureValueAnnuityDueRequest != null && futureValueAnnuityDueRequest.getDuration() != null) {
				error = validateDuration(futureValueAnnuityDueRequest.getDuration());
				if (error.isEmpty() == false) {
					allErrors.put("DURATION", error);
				}
			}
			if (futureValueAnnuityDueRequest != null && futureValueAnnuityDueRequest.getAnnualGrowth() != null) {
				error = validateAnnualGrowth(futureValueAnnuityDueRequest.getAnnualGrowth());
				if (error.isEmpty() == false) {
					allErrors.put("ANNUALGROWTH", error);
				}
			}
			if (futureValueAnnuityDueRequest != null && futureValueAnnuityDueRequest.getYearlyIncrease() != null) {
				error = validateYearlyIncrease(futureValueAnnuityDueRequest.getYearlyIncrease());
				if (error.isEmpty() == false) {
					allErrors.put("YEARLYINCREASE", error);
				}
			}
			// if (futureValueRequest != null && futureValueRequest.getInvAmountType() !=
			// null) {
			// error = validateInvAmountType(futureValueRequest.getInvAmountType());
			// if (error.isEmpty() == false) {
			// allErrors.put("INVAMOUNTTYPE", error);
			// }
			// }
			// if (futureValueRequest != null && futureValueRequest.getDurationType() !=
			// null) {
			// error = validateDurationType(futureValueRequest.getDurationType());
			// if (error.isEmpty() == false) {
			// allErrors.put("DURATIONTYPE", error);
			// }
			// }
		}

		return allErrors;
	}

	// protected HashMap<String, String> validateDurationType(String durationType) {
	// String inputParamName = CalcConstants.SPACE_WTIH_COLON +
	// CalcConstants.INVAMOUNTTYPE;
	// HashMap<String, String> errors = new HashMap<String, String>();
	// if (Common.nonEmptyCheck(durationType, inputParamName).isEmpty() == false) {
	// errors.put("EMPTY", Common.nonEmptyCheck(durationType, inputParamName));
	// }
	// if (Common.tenureTypeCheck(durationType, inputParamName).isEmpty() == false)
	// {
	// errors.put("AMOUNT_TYPE", Common.tenureTypeCheck(durationType,
	// inputParamName));
	// }
	// return errors;
	// }

	// protected HashMap<String, String> validateInvAmountType(String invAmountType)
	// {
	// String inputParamName = CalcConstants.SPACE_WTIH_COLON +
	// CalcConstants.INVAMOUNTTYPE;
	// HashMap<String, String> errors = new HashMap<String, String>();
	// if (Common.nonEmptyCheck(invAmountType, inputParamName).isEmpty() == false) {
	// errors.put("EMPTY", Common.nonEmptyCheck(invAmountType, inputParamName));
	// }
	// if (Common.checkAmountType(invAmountType, inputParamName).isEmpty() == false)
	// {
	// errors.put("AMOUNT_TYPE", Common.checkAmountType(invAmountType,
	// inputParamName));
	// }
	// return errors;
	// }

	protected HashMap<String, String> validateYearlyIncrease(String yearlyIncrease) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.YEARLYINCREASE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(yearlyIncrease, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(yearlyIncrease, inputParamName));
		}
		if (common.isValidDoubleNumber(yearlyIncrease, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(yearlyIncrease, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateAnnualGrowth(String annualGrowth) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.ANNUALGROWTH;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(annualGrowth, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(annualGrowth, inputParamName));
		}
		if (common.isValidDoubleNumber(annualGrowth, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(annualGrowth, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateDuration(String duration) {
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

	protected HashMap<String, String> validateInvAmount(String invAmount) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.INVESTMENTAMOUNT;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(invAmount, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(invAmount, inputParamName));
		}
		if (common.isValidDoubleNumber(invAmount, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(invAmount, inputParamName));
		}
		return errors;
	}

}
