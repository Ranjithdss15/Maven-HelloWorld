package com.sowisetech.calc.request;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sowisetech.calc.util.CalcAppMessages;
import com.sowisetech.calc.util.CalcConstants;
import com.sowisetech.calc.util.CalcCommon;

@Component
public class EmiCapacityRequestValidator {

	@Autowired
	CalcAppMessages appmessages;

	@Autowired
	CalcCommon common;

	public HashMap<String, HashMap<String, String>> validate(EmiCapacityRequest emiCapacityRequest) {
		HashMap<String, HashMap<String, String>> allErrors = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> error = new HashMap<String, String>();
		error.put("EMPTY", appmessages.getValue_empty());
		if (emiCapacityRequest == null) {
			allErrors.put("NULL", error);
		} else {
			if (emiCapacityRequest != null && emiCapacityRequest.getCurrentAge() != null) {
				error = validateCurrentAge(emiCapacityRequest.getCurrentAge());
				if (error.isEmpty() == false) {
					allErrors.put("CURRENT_AGE", error);
				}
			}
			if (emiCapacityRequest != null && emiCapacityRequest.getRetirementAge() != null) {
				error = validateRetirementAge(emiCapacityRequest.getRetirementAge());
				if (error.isEmpty() == false) {
					allErrors.put("RETIREMENT_AGE", error);
				}
			}
			if (emiCapacityRequest != null && emiCapacityRequest.getStability() != null) {
				error = validateStability(emiCapacityRequest.getStability());
				if (error.isEmpty() == false) {
					allErrors.put("STABILITY", error);
				}
			}
			if (emiCapacityRequest != null && emiCapacityRequest.getBackUp() != null) {
				error = validateBackUp(emiCapacityRequest.getBackUp());
				if (error.isEmpty() == false) {
					allErrors.put("BACKUP", error);
				}
			}
			if (emiCapacityRequest != null && emiCapacityRequest.getNetFamilyIncome() != null) {
				error = validateNetFamilyIncome(emiCapacityRequest.getNetFamilyIncome());
				if (error.isEmpty() == false) {
					allErrors.put("NET_FAMILY_INCOME", error);
				}
			}
			if (emiCapacityRequest != null && emiCapacityRequest.getExistingEmi() != null) {
				error = validateExistingEmi(emiCapacityRequest.getExistingEmi());
				if (error.isEmpty() == false) {
					allErrors.put("EXISTING_EMI", error);
				}
			}
			if (emiCapacityRequest != null && emiCapacityRequest.getHouseHoldExpense() != null) {
				error = validateHouseHoldExpense(emiCapacityRequest.getHouseHoldExpense());
				if (error.isEmpty() == false) {
					allErrors.put("HOUSE_HOLD_EXPENSE", error);
				}
			}
			if (emiCapacityRequest != null && emiCapacityRequest.getAdditionalIncome() != null) {
				error = validateAdditionalIncome(emiCapacityRequest.getAdditionalIncome());
				if (error.isEmpty() == false) {
					allErrors.put("ADDITIONAL_INCOME", error);
				}
			}
			if (emiCapacityRequest != null && emiCapacityRequest.getInterestRate() != null) {
				error = validateInterestRate(emiCapacityRequest.getInterestRate());
				if (error.isEmpty() == false) {
					allErrors.put("INTEREST_RATE", error);
				}
			}

		}

		return allErrors;
	}

	protected HashMap<String, String> validateInterestRate(String rateOfInterestPerAnnum) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.INTERESTRATE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(rateOfInterestPerAnnum, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(rateOfInterestPerAnnum, inputParamName));
		}
		if (common.isValidDoubleNumber(rateOfInterestPerAnnum, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(rateOfInterestPerAnnum, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateAdditionalIncome(String additionalIncome) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.ADDITIONALINCOME;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(additionalIncome, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(additionalIncome, inputParamName));
		}
		if (common.isValidDoubleNumber(additionalIncome, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(additionalIncome, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateHouseHoldExpense(String houseHoldExpense) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.HOUSEHOLDEXPENSE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(houseHoldExpense, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(houseHoldExpense, inputParamName));
		}
		if (common.isValidDoubleNumber(houseHoldExpense, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(houseHoldExpense, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateExistingEmi(String existingEmi) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.EXISTINGEMI;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(existingEmi, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(existingEmi, inputParamName));
		}
		if (common.isValidDoubleNumber(existingEmi, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(existingEmi, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateNetFamilyIncome(String netFamilyIncome) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.NETFAMILYINCOME;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(netFamilyIncome, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(netFamilyIncome, inputParamName));
		}
		if (common.isValidDoubleNumber(netFamilyIncome, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(netFamilyIncome, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateBackUp(String backUp) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.BACKUP;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(backUp, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(backUp, inputParamName));
		}
		if (common.checkYesOrNo(backUp, inputParamName).isEmpty() == false) {
			errors.put("BACKUP_ERROR", common.checkYesOrNo(backUp, inputParamName));
		}
		return errors;

	}

	protected HashMap<String, String> validateStability(String stability) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.STABILITY;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(stability, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(stability, inputParamName));
		}
		if (common.checkCapacityStability(stability, inputParamName).isEmpty() == false) {
			errors.put("STABILITY_ERROR", common.checkCapacityStability(stability, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateRetirementAge(String retirementAge) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.RETIREMENTAGE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(retirementAge, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(retirementAge, inputParamName));
		}
		if (common.isNumericValues(retirementAge, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isNumericValues(retirementAge, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateCurrentAge(String currentAge) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.CURRENTAGE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(currentAge, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(currentAge, inputParamName));
		}
		if (common.isNumericValues(currentAge, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isNumericValues(currentAge, inputParamName));
		}
		return errors;
	}
}
