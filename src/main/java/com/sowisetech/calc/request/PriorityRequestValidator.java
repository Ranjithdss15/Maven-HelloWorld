package com.sowisetech.calc.request;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sowisetech.calc.util.CalcAppMessages;
import com.sowisetech.calc.util.CalcConstants;
import com.sowisetech.calc.util.CalcCommon;

@Component
public class PriorityRequestValidator implements CalcIValidator {
	
	@Autowired
	CalcAppMessages appmessages;
	
	@Autowired
	CalcCommon common;

	public HashMap<String, HashMap<String, String>> validate(PriorityRequest priorityRequest) {
		HashMap<String, HashMap<String, String>> allErrors = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> error = new HashMap<String, String>();
		error.put("EMPTY", appmessages.getValue_empty());
		if (priorityRequest == null) {
			allErrors.put("NULL", error);
		} else {
			for (PriorityReq priorityReq : priorityRequest.getPriorityReq()) {
				if (priorityReq != null && priorityReq.getTimeline() != null) {
					error = validateTimeLine(priorityReq.getTimeline());
					if (error.isEmpty() == false) {
						allErrors.put("TIMELINE", error);
					}
				}
				if (priorityReq != null && priorityReq.getValue() != null) {
					error = validateValue(priorityReq.getValue());
					if (error.isEmpty() == false) {
						allErrors.put("VALUE", error);
					}
				}

			}

		}

		return allErrors;

	}

	protected HashMap<String, String> validateTimeLine(String timeline) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.TIMELINE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.isNumericValues(timeline, inputParamName).isEmpty() == false) {
			errors.put("VALUE", common.isNumericValues(timeline, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateValue(String value) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.VALUE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.isValidDoubleNumber(value, inputParamName).isEmpty() == false) {
			errors.put("VALUE", common.isValidDoubleNumber(value, inputParamName));
		}
		return errors;
	}
}
