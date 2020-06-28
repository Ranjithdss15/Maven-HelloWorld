package com.sowisetech.calc.request;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sowisetech.calc.util.CalcAppMessages;
import com.sowisetech.calc.util.CalcConstants;
import com.sowisetech.calc.util.CalcCommon;

@Component
public class CashFlowRequestValidator implements CalcIValidator {

	@Autowired
	CalcAppMessages appMessages;
	
	@Autowired
	CalcCommon common;
	
	public HashMap<String, HashMap<String, String>> validate(CashFlowRequest cashFlowRequest) {
		HashMap<String, HashMap<String, String>> allErrors = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> error = new HashMap<String, String>();
		error.put("EMPTY", appMessages.getValue_empty());
		if (cashFlowRequest == null) {
			allErrors.put("NULL", error);
		} else {
			for (CashFlowItemReq cashFlowItemReq : cashFlowRequest.getCashFlowItemReq()) {
				if (cashFlowItemReq != null && cashFlowItemReq.getBudgetAmt() != null) {
					error = validateBudgetAmount(cashFlowItemReq.getBudgetAmt());
					if (error.isEmpty() == false) {
						allErrors.put("BUDGETAMOUNT", error);
					}
				}
				if (cashFlowItemReq != null && cashFlowItemReq.getActualAmt() != null) {
					error = validateActualAmount(cashFlowItemReq.getActualAmt());
					if (error.isEmpty() == false) {
						allErrors.put("ACTUALAMOUNT", error);
					}
				}
			}
			if (cashFlowRequest != null && cashFlowRequest.getDate() != null) {
				error = validateDate(cashFlowRequest.getDate());
				if (error.isEmpty() == false) {
					allErrors.put("DATE", error);
				}
			}
		}

		return allErrors;

	}

	protected HashMap<String, String> validateDate(String date) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.DATE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(date, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(date, inputParamName));
		}
		if (common.monthYearCheck(date, inputParamName).isEmpty() == false) {
			errors.put("DATE_FORMAT", common.monthYearCheck(date, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateActualAmount(String actualAmt) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.ACTUALAMOUNT;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.isValidDoubleNumber(actualAmt, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(actualAmt, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateBudgetAmount(String budgetAmt) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.BUDGETAMOUNT;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.isValidDoubleNumber(budgetAmt, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isValidDoubleNumber(budgetAmt, inputParamName));
		}
		return errors;
	}

}