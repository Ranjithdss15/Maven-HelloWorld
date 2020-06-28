package com.sowisetech.calc.request;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sowisetech.calc.util.CalcAppMessages;
import com.sowisetech.calc.util.CalcConstants;
import com.sowisetech.calc.util.CalcCommon;

@Component
public class PlanRequestValidator {
	
	@Autowired
	CalcAppMessages appmessages;
	
	@Autowired
	CalcCommon common;

	public HashMap<String, HashMap<String, String>> validate(PlanRequest planRequest) {
		HashMap<String, HashMap<String, String>> allErrors = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> error = new HashMap<String, String>();
		error.put("EMPTY", appmessages.getValue_empty());
		if (planRequest == null) {
			allErrors.put("NULL", error);
		} else {
			if (planRequest != null && planRequest.getName() != null) {
				error = validateName(planRequest.getName());
				if (error.isEmpty() == false) {
					allErrors.put("NAME", error);
				}
			}
			if (planRequest != null && planRequest.getAge() != null) {
				error = validateAge(planRequest.getAge());
				if (error.isEmpty() == false) {
					allErrors.put("AGE", error);
				}
			}
			if (planRequest != null && planRequest.getSelectedPlan().size() != 0) {
				for (String plan : planRequest.getSelectedPlan()) {
					error = validateSelectedPlan(plan);
					if (error.isEmpty() == false) {
						allErrors.put("SELECTED_PLAN", error);
					}
				}
			}
			if (planRequest != null && planRequest.getSpouse() != null) {
				error = validateSpouse(planRequest.getSpouse());
				if (error.isEmpty() == false) {
					allErrors.put("SPOUSE", error);
				}
			}
			if (planRequest != null && planRequest.getParents() != null) {
				error = validateParents(planRequest.getParents());
				if (error.isEmpty() == false) {
					allErrors.put("PARENTS", error);
				}
			}
			if (planRequest != null && planRequest.getChildren() != null) {
				error = validateChildren(planRequest.getChildren());
				if (error.isEmpty() == false) {
					allErrors.put("CHILDREN", error);
				}
			}
			if (planRequest != null && planRequest.getGrandParent() != null) {
				error = validateGrandParent(planRequest.getGrandParent());
				if (error.isEmpty() == false) {
					allErrors.put("GRAND_PARENT", error);
				}
			}
			if (planRequest != null && planRequest.getSibilings() != null) {
				error = validateSibilings(planRequest.getSibilings());
				if (error.isEmpty() == false) {
					allErrors.put("SIBILINGS", error);
				}
			}
		}
		return allErrors;

	}

	private HashMap<String, String> validateSibilings(String sibilings) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.SIBILINGS;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(sibilings, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(sibilings, inputParamName));
		}
		if (common.isAlpha(sibilings, inputParamName).isEmpty() == false) {
			errors.put("NON_ALPHA", common.isAlpha(sibilings, inputParamName));
		}
		if (common.checkYesOrNo(sibilings, inputParamName).isEmpty() == false) {
			errors.put("YES_OR_NO", common.checkYesOrNo(sibilings, inputParamName));
		}
		return errors;
	}

	private HashMap<String, String> validateGrandParent(String grandParent) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.GRAND_PARENT;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(grandParent, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(grandParent, inputParamName));
		}
		if (common.isAlpha(grandParent, inputParamName).isEmpty() == false) {
			errors.put("NON_ALPHA", common.isAlpha(grandParent, inputParamName));
		}
		if (common.checkYesOrNo(grandParent, inputParamName).isEmpty() == false) {
			errors.put("YES_OR_NO", common.checkYesOrNo(grandParent, inputParamName));
		}
		return errors;
	}

	private HashMap<String, String> validateChildren(String children) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.CHILDREN;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(children, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(children, inputParamName));
		}
		if (common.isNumericValues(children, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isNumericValues(children, inputParamName));
		}
		return errors;
	}

	private HashMap<String, String> validateParents(String parents) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.PARENTS;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(parents, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(parents, inputParamName));
		}
		if (common.isAlpha(parents, inputParamName).isEmpty() == false) {
			errors.put("NON_ALPHA", common.isAlpha(parents, inputParamName));
		}
		if (common.checkYesOrNo(parents, inputParamName).isEmpty() == false) {
			errors.put("YES_OR_NO", common.checkYesOrNo(parents, inputParamName));
		}
		return errors;
	}

	private HashMap<String, String> validateSpouse(String spouse) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.SPOUSE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(spouse, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(spouse, inputParamName));
		}
		if (common.isAlpha(spouse, inputParamName).isEmpty() == false) {
			errors.put("NON_ALPHA", common.isAlpha(spouse, inputParamName));
		}
		if (common.checkYesOrNo(spouse, inputParamName).isEmpty() == false) {
			errors.put("YES_OR_NO", common.checkYesOrNo(spouse, inputParamName));
		}
		return errors;
	}

	private HashMap<String, String> validateSelectedPlan(String selectedPlan) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.SELECTED_PLAN;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(selectedPlan, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(selectedPlan, inputParamName));
		}
		if (common.isAlpha(selectedPlan, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isAlpha(selectedPlan, inputParamName));
		}
		return errors;
	}

	private HashMap<String, String> validateAge(String age) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.AGE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(age, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(age, inputParamName));
		}
		if (common.isNumericValues(age, inputParamName).isEmpty() == false) {
			errors.put("NON_NUMERIC", common.isNumericValues(age, inputParamName));
		}
		return errors;
	}

	private HashMap<String, String> validateName(String name) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.NAME;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.nonEmptyCheck(name, inputParamName).isEmpty() == false) {
			errors.put("EMPTY", common.nonEmptyCheck(name, inputParamName));
		}
		if (common.isAlpha(name, inputParamName).isEmpty() == false) {
			errors.put("NON_ALPHA", common.isAlpha(name, inputParamName));
		}
		return errors;
	}
}
