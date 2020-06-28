package com.sowisetech.calc.request;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sowisetech.calc.util.CalcAppMessages;
import com.sowisetech.calc.util.CalcConstants;
import com.sowisetech.calc.util.CalcCommon;

@Component
public class EmiCalculatorRequestValidator {

	@Autowired
	CalcAppMessages appMessages;
	
	@Autowired
	CalcCommon common;

	public HashMap<String, HashMap<String, String>> validate(EmiCalculatorRequest emiCalculatorRequest) {
		HashMap<String, HashMap<String, String>> allErrors = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> error = new HashMap<String, String>();
		error.put("EMPTY", appMessages.getValue_empty());
		if (emiCalculatorRequest == null) {
			allErrors.put("NULL", error);
		} else {
			if (emiCalculatorRequest != null && emiCalculatorRequest.getLoanAmount() != null) {
				error = validateLoanAmount(emiCalculatorRequest.getLoanAmount());
				if (error.isEmpty() == false) {
					allErrors.put("LOAN_AMOUNT", error);
				}
			}
			if (emiCalculatorRequest != null && emiCalculatorRequest.getTenure() != null) {
				error = validateTenure(emiCalculatorRequest.getTenure());
				if (error.isEmpty() == false) {
					allErrors.put("TENURE", error);
				}
			}
			if (emiCalculatorRequest != null && emiCalculatorRequest.getInterestRate() != null) {
				error = validateInterestRate(emiCalculatorRequest.getInterestRate());
				if (error.isEmpty() == false) {
					allErrors.put("INTEREST_RATE", error);
				}
			}

		}

		return allErrors;
	}

	protected HashMap<String, String> validateInterestRate(String interestRate) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.INTERESTRATE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(interestRate, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(interestRate, inputParamName));
		}
		if (common.isValidDoubleNumber(interestRate, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(interestRate, inputParamName));
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

	protected HashMap<String, String> validateLoanAmount(String loanAmount) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.LOANAMOUNT;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(loanAmount, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(loanAmount, inputParamName));
		}
		if (common.isValidDoubleNumber(loanAmount, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(loanAmount, inputParamName));
		}
		return errors;
	}

}
