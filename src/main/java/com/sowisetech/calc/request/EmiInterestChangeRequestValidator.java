package com.sowisetech.calc.request;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sowisetech.calc.util.CalcAppMessages;
import com.sowisetech.calc.util.CalcConstants;
import com.sowisetech.calc.util.CalcCommon;

@Component
public class EmiInterestChangeRequestValidator {

	@Autowired
	CalcAppMessages appmessages;
	
	@Autowired
	CalcCommon common;

	public HashMap<String, HashMap<String, String>> validate(EmiInterestChangeRequest emiInterestChangeRequest) {
		HashMap<String, HashMap<String, String>> allErrors = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> error = new HashMap<String, String>();
		error.put("EMPTY", appmessages.getValue_empty());
		if (emiInterestChangeRequest == null) {
			allErrors.put("NULL", error);
		} else {
			for (EmiInterestChangeReq emiInterestChangeReq : emiInterestChangeRequest.getEmiInterestChangeReq()) {
				if (emiInterestChangeReq != null && emiInterestChangeReq.getChangedRate() != null) {
					error = validateChangedRate(emiInterestChangeReq.getChangedRate());
					if (error.isEmpty() == false) {
						allErrors.put("CHANGED_RATE", error);
					}
				}
				if (emiInterestChangeReq != null && emiInterestChangeReq.getChangedDate() != null) {
					error = validateChangedDate(emiInterestChangeReq.getChangedDate());
					if (error.isEmpty() == false) {
						allErrors.put("CHANGED_DATE", error);
					}
				}
				if (emiInterestChangeReq != null && emiInterestChangeReq.getIncreasedEmi() != null) {
					error = validateIncreasedEmi(emiInterestChangeReq.getIncreasedEmi());
					if (error.isEmpty() == false) {
						allErrors.put("INCREASED_EMI", error);
					}
				}
			}
			if (emiInterestChangeRequest != null && emiInterestChangeRequest.getLoanAmount() != null) {
				error = validateLoanAmount(emiInterestChangeRequest.getLoanAmount());
				if (error.isEmpty() == false) {
					allErrors.put("LOAN_AMOUNT", error);
				}
			}
			if (emiInterestChangeRequest != null && emiInterestChangeRequest.getInterestRate() != null) {
				error = validateInterestRate(emiInterestChangeRequest.getInterestRate());
				if (error.isEmpty() == false) {
					allErrors.put("INTEREST_RATE", error);
				}
			}
			if (emiInterestChangeRequest != null && emiInterestChangeRequest.getTenure() != null) {
				error = validateTenure(emiInterestChangeRequest.getTenure());
				if (error.isEmpty() == false) {
					allErrors.put("TENURE", error);
				}
			}
			if (emiInterestChangeRequest != null && emiInterestChangeRequest.getLoanDate() != null) {
				error = validateLoanDate(emiInterestChangeRequest.getLoanDate());
				if (error.isEmpty() == false) {
					allErrors.put("LOAN_DATE", error);
				}
			}
		}

		return allErrors;

	}

	private HashMap<String, String> validateIncreasedEmi(String increasedEmi) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.INCREASEDEMI;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(increasedEmi, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(increasedEmi, inputParamName));
		}
		if (common.isValidDoubleNumber(increasedEmi, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(increasedEmi, inputParamName));
		}
		return errors;
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

	private HashMap<String, String> validateChangedDate(String changedDate) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.CHANGEDDATE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(changedDate, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(changedDate, inputParamName));
		}
		if (common.dateFormatCheck(changedDate, inputParamName).isEmpty() == false) {
			errors.put("DATE_FORMAT", common.dateFormatCheck(changedDate, inputParamName));
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
