package com.sowisetech.calc.request;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sowisetech.calc.util.CalcAppMessages;
import com.sowisetech.calc.util.CalcConstants;
import com.sowisetech.calc.util.CalcCommon;

@Component
public class InterestChangeRequestValidator {
	
	@Autowired
	CalcAppMessages appmessages;
	
	@Autowired
	CalcCommon common;

	public HashMap<String, HashMap<String, String>> validate(InterestChangeRequest interestChangeRequest) {
		HashMap<String, HashMap<String, String>> allErrors = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> error = new HashMap<String, String>();
		error.put("EMPTY", appmessages.getValue_empty());
		if (interestChangeRequest == null) {
			allErrors.put("NULL", error);
		} else {
			for (InterestChangeReq interestChangeReq : interestChangeRequest.getInterestChangeReq()) {
				if (interestChangeReq != null && interestChangeReq.getChangedRate() != null) {
					error = validateChangedRate(interestChangeReq.getChangedRate());
					if (error.isEmpty() == false) {
						allErrors.put("CHANGED_RATE", error);
					}
				}
				if (interestChangeReq != null && interestChangeReq.getInterestChangedDate() != null) {
					error = validateInterestChangedDate(interestChangeReq.getInterestChangedDate());
					if (error.isEmpty() == false) {
						allErrors.put("INTEREST_CHANGED_DATE", error);
					}
				}
			}
			if (interestChangeRequest != null && interestChangeRequest.getLoanAmount() != null) {
				error = validateLoanAmount(interestChangeRequest.getLoanAmount());
				if (error.isEmpty() == false) {
					allErrors.put("LOAN_AMOUNT", error);
				}
			}
			if (interestChangeRequest != null && interestChangeRequest.getInterestRate() != null) {
				error = validateInterestRate(interestChangeRequest.getInterestRate());
				if (error.isEmpty() == false) {
					allErrors.put("INTEREST_RATE", error);
				}
			}
			if (interestChangeRequest != null && interestChangeRequest.getTenure() != null) {
				error = validateTenure(interestChangeRequest.getTenure());
				if (error.isEmpty() == false) {
					allErrors.put("TENURE", error);
				}
			}
			if (interestChangeRequest != null && interestChangeRequest.getLoanDate() != null) {
				error = validateLoanDate(interestChangeRequest.getLoanDate());
				if (error.isEmpty() == false) {
					allErrors.put("LOAN_DATE", error);
				}
			}
		}

		return allErrors;

	}

	private HashMap<String, String> validateChangedRate(String changedRate) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.CHANGEDRATE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(changedRate, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(changedRate, inputParamName));
		}
		if (common.isValidDoubleNumber(changedRate, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(changedRate, inputParamName));
		}
		return errors;
	}
	private HashMap<String, String> validateInterestChangedDate(String interestChangedDate) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.INTERESTCHANGEDDATE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(interestChangedDate, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(interestChangedDate, inputParamName));
		}
		if (common.dateFormatCheck(interestChangedDate, inputParamName).isEmpty() == false) {
			errors.put("DATE_FORMAT", common.dateFormatCheck(interestChangedDate, inputParamName));
		}
		return errors;
	}

	private HashMap<String, String> validateTenure(String tenure) {
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

	private HashMap<String, String> validateLoanAmount(String loanAmount) {
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

	private HashMap<String, String> validateLoanDate(String loanDate) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.LOANDATE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(loanDate, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(loanDate, inputParamName));
		}
		if (common.dateFormatCheck(loanDate, inputParamName).isEmpty() == false) {
			errors.put("DATE_FORMAT", common.dateFormatCheck(loanDate, inputParamName));
		}
		if (common.futureDateOrCurrentDateCheck(loanDate, inputParamName).isEmpty() == false) {
			errors.put("FUTURE_DATE_ERROR", common.futureDateOrCurrentDateCheck(loanDate, inputParamName));
		}
		return errors;
	}

	private HashMap<String, String> validateInterestRate(String interestRate) {
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
}
