package com.sowisetech.calc.request;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sowisetech.calc.util.CalcAppMessages;
import com.sowisetech.calc.util.CalcConstants;
import com.sowisetech.calc.util.CalcCommon;

@Component
public class PartialPaymentRequestValidator implements CalcIValidator {

	@Autowired
	CalcAppMessages appmessages;

	@Autowired
	CalcCommon common;

	public HashMap<String, HashMap<String, String>> validate(PartialPaymentRequest partialPaymentRequest) {
		HashMap<String, HashMap<String, String>> allErrors = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> error = new HashMap<String, String>();
		error.put("EMPTY", appmessages.getValue_empty());
		if (partialPaymentRequest == null) {
			allErrors.put("NULL", error);
		} else {
			if (partialPaymentRequest != null && partialPaymentRequest.getLoanAmount() != null) {
				error = validateLoanAmount(partialPaymentRequest.getLoanAmount());
				if (error.isEmpty() == false) {
					allErrors.put("LOAN_AMOUNT", error);
				}
			}
			if (partialPaymentRequest != null && partialPaymentRequest.getInterestRate() != null) {
				error = validateInterestRate(partialPaymentRequest.getInterestRate());
				if (error.isEmpty() == false) {
					allErrors.put("INTEREST_RATE", error);
				}
			}
			if (partialPaymentRequest != null && partialPaymentRequest.getTenure() != null) {
				error = validateTenure(partialPaymentRequest.getTenure());
				if (error.isEmpty() == false) {
					allErrors.put("TENURE", error);
				}
			}
			if (partialPaymentRequest != null && partialPaymentRequest.getLoanDate() != null) {
				error = validateLoanDate(partialPaymentRequest.getLoanDate());
				if (error.isEmpty() == false) {
					allErrors.put("LOAN_DATE", error);
				}
			}
			for (PartialPaymentReq partialPaymentReq : partialPaymentRequest.getPartialPaymentReq()) {
				if (partialPaymentReq != null && partialPaymentReq.getPartPayDate() != null) {
					error = validatePartPayDate(partialPaymentReq.getPartPayDate());
					if (error.isEmpty() == false) {
						allErrors.put("PART_PAY_DATE", error);
					}
				}
				if (partialPaymentReq != null && partialPaymentReq.getPartPayAmount() != null) {
					error = validatePartPayAmount(partialPaymentReq.getPartPayAmount());
					if (error.isEmpty() == false) {
						allErrors.put("PART_PAY_AMOUNT", error);
					}
				}
			}

		}

		return allErrors;

	}

	protected HashMap<String, String> validatePartPayAmount(String partPayAmount) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.PARTPAYAMOUNT;
		HashMap<String, String> errors = new HashMap<String, String>();

		if (common.nonEmptyCheck(partPayAmount, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(partPayAmount, inputParamName));
		}
		if (common.isValidDoubleNumber(partPayAmount, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(partPayAmount, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateLoanDate(String loanDate) {
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

	protected HashMap<String, String> validatePartPayDate(String partPayDate) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.PARTPAYDATE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(partPayDate, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(partPayDate, inputParamName));
		}
		if (common.dateFormatCheck(partPayDate, inputParamName).isEmpty() == false) {
			errors.put("DATE_FORMAT", common.dateFormatCheck(partPayDate, inputParamName));
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

	protected HashMap<String, String> validateLoanAmount(String loanAmt) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.LOANAMOUNT;
		HashMap<String, String> errors = new HashMap<String, String>();

		if (common.nonEmptyCheck(loanAmt, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(loanAmt, inputParamName));
		}
		if (common.isValidDoubleNumber(loanAmt, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(loanAmt, inputParamName));
		}
		return errors;
	}
}
