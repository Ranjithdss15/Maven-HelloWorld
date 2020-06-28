package com.sowisetech.calc.request;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sowisetech.calc.util.CalcAppMessages;
import com.sowisetech.calc.util.CalcConstants;
import com.sowisetech.calc.util.CalcCommon;

@Component
public class EmiChangeRequestValidator implements CalcIValidator {
	
	@Autowired
	CalcAppMessages appmessages;
	
	@Autowired
	CalcCommon common;

	public HashMap<String, HashMap<String, String>> validate(EmiChangeRequest emiChangeRequest) {
		HashMap<String, HashMap<String, String>> allErrors = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> error = new HashMap<String, String>();
		error.put("EMPTY", appmessages.getValue_empty());
		if (emiChangeRequest == null) {
			allErrors.put("NULL", error);
		} else {
			for (EmiChangeReq emiChangeReq : emiChangeRequest.getEmiChangeReq()) {
				if (emiChangeReq != null && emiChangeReq.getIncreasedEmi() != null) {
					error = validateIncreasedEmi(emiChangeReq.getIncreasedEmi());
					if (error.isEmpty() == false) {
						allErrors.put("INCREASED_EMI", error);
					}
				}
				if (emiChangeReq != null && emiChangeReq.getEmiChangedDate() != null) {
					error = validateEmiChangedDate(emiChangeReq.getEmiChangedDate());
					if (error.isEmpty() == false) {
						allErrors.put("EMI_CHANGED_DATE", error);
					}
				}

			}

			if (emiChangeRequest != null && emiChangeRequest.getLoanAmount() != null) {
				error = validateLoanAmount(emiChangeRequest.getLoanAmount());
				if (error.isEmpty() == false) {
					allErrors.put("LOANAMOUNT", error);
				}
			}
			if (emiChangeRequest != null && emiChangeRequest.getInterestRate() != null) {
				error = validateInterestRate(emiChangeRequest.getInterestRate());
				if (error.isEmpty() == false) {
					allErrors.put("INTEREST_RATE", error);
				}
			}
			if (emiChangeRequest != null && emiChangeRequest.getTenure() != null) {
				error = validateTenure(emiChangeRequest.getTenure());
				if (error.isEmpty() == false) {
					allErrors.put("TENURE", error);
				}
			}
			if (emiChangeRequest != null && emiChangeRequest.getLoanDate() != null) {
				error = validateLoanDate(emiChangeRequest.getLoanDate());
				if (error.isEmpty() == false) {
					allErrors.put("LOAN_DATE", error);
				}
			}
		}
		return allErrors;
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

	private HashMap<String, String> validateEmiChangedDate(String emiChangedDate) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.EMICHANGEDDATE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(emiChangedDate, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(emiChangedDate, inputParamName));
		}
		if (common.dateFormatCheck(emiChangedDate, inputParamName).isEmpty() == false) {
			errors.put("DATE_FORMAT", common.dateFormatCheck(emiChangedDate, inputParamName));
		}
		return errors;
	}

}
