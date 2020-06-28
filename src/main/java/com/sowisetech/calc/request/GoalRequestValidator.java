package com.sowisetech.calc.request;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sowisetech.calc.request.CalcIValidator;
import com.sowisetech.calc.util.CalcAppMessages;
import com.sowisetech.calc.util.CalcConstants;
import com.sowisetech.calc.util.CalcCommon;

@Component
public class GoalRequestValidator implements CalcIValidator {
	
	@Autowired
	CalcAppMessages appmessages;
	
	@Autowired
	CalcCommon common;

	public HashMap<String, HashMap<String, String>> validate(GoalRequest goalRequest) {
		HashMap<String, HashMap<String, String>> allErrors = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> error = new HashMap<String, String>();
		error.put("EMPTY", appmessages.getValue_empty());
		if (goalRequest == null) {
			allErrors.put("NULL", error);
		} else {
			if (goalRequest != null && goalRequest.getGoalName() != null) {
				error = validateGoalName(goalRequest.getGoalName());
				if (error.isEmpty() == false) {
					allErrors.put("GOALNAME", error);
				}
			}
			if (goalRequest != null && goalRequest.getTenure() != null) {
				error = validateTenure(goalRequest.getTenure());
				if (error.isEmpty() == false) {
					allErrors.put("TENURE", error);
				}
			}
			if (goalRequest != null && goalRequest.getTenureType() != null) {
				error = validateTenureType(goalRequest.getTenureType());
				if (error.isEmpty() == false) {
					allErrors.put("TENURETYPE", error);
				}
			}
			if (goalRequest != null && goalRequest.getGoalAmount() != null) {
				error = validateGoalAmount(goalRequest.getGoalAmount());
				if (error.isEmpty() == false) {
					allErrors.put("GOALAMOUNT", error);
				}
			}
			if (goalRequest != null && goalRequest.getInflationRate() != null) {
				error = validateInflationRate(goalRequest.getInflationRate());
				if (error.isEmpty() == false) {
					allErrors.put("INFLATIONRATE", error);
				}
			}
			if (goalRequest != null && goalRequest.getCurrentAmount() != null) {
				error = validateCurrentAmount(goalRequest.getCurrentAmount());
				if (error.isEmpty() == false) {
					allErrors.put("CURRENTAMOUNT", error);
				}
			}
			if (goalRequest != null && goalRequest.getGrowthRate() != null) {
				error = validateGrowthRate(goalRequest.getGrowthRate());
				if (error.isEmpty() == false) {
					allErrors.put("GROWTHRATE", error);
				}
			}
			if (goalRequest != null && goalRequest.getAnnualInvestmentRate() != null) {
				error = validateAnnualInvestmentRate(goalRequest.getAnnualInvestmentRate());
				if (error.isEmpty() == false) {
					allErrors.put("ANNUALINVESTMENTRATE", error);
				}
			}
		}

		return allErrors;

	}

	protected HashMap<String, String> validateAnnualInvestmentRate(String annualInvestmentRate) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.ANNUALINVESTMENTRATE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(annualInvestmentRate, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(annualInvestmentRate, inputParamName));
		}
		if (common.isValidDoubleNumber(annualInvestmentRate, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(annualInvestmentRate, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateGrowthRate(String growthRate) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.GROWTHRATE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(growthRate, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(growthRate, inputParamName));
		}
		if (common.isValidDoubleNumber(growthRate, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(growthRate, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateCurrentAmount(String currentAmount) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.CURRENTAMOUNT;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(currentAmount, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(currentAmount, inputParamName));
		}
		if (common.isValidDoubleNumber(currentAmount, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(currentAmount, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateInflationRate(String inflationRate) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.INFLATIONRATE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(inflationRate, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(inflationRate, inputParamName));
		}
		if (common.isValidDoubleNumber(inflationRate, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(inflationRate, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateGoalAmount(String goalAmount) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.GOALAMOUNT;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(goalAmount, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(goalAmount, inputParamName));
		}
		if (common.isValidDoubleNumber(goalAmount, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(goalAmount, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateTenureType(String tenureType) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.TENURETYPE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(tenureType, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(tenureType, inputParamName));
		}
		if (common.tenureTypeCheck(tenureType, inputParamName).isEmpty() == false) {
			errors.put("TENURETYPE_ERROR", common.tenureTypeCheck(tenureType, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateTenure(String tenure) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.TENURE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(tenure, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(tenure, inputParamName));
		}
		if (common.isNumericValues(tenure, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isNumericValues(tenure, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateGoalName(String goalName) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.GOALNAME;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(goalName, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(goalName, inputParamName));
		}
		if (common.isAlpha(goalName, inputParamName).isEmpty() == false) {
			errors.put("NON_ALPHA", common.isAlpha(goalName, inputParamName));
		}
		return errors;
	}

}
