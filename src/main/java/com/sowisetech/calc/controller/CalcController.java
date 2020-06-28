package com.sowisetech.calc.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sowisetech.calc.model.Account;
import com.sowisetech.calc.model.AccountType;
import com.sowisetech.calc.model.CashFlow;
import com.sowisetech.calc.model.CashFlowItem;
import com.sowisetech.calc.model.CashFlowItemType;
import com.sowisetech.calc.model.CashFlowSummary;
import com.sowisetech.calc.model.EmiCalculator;
import com.sowisetech.calc.model.EmiCapacity;
import com.sowisetech.calc.model.EmiChange;
import com.sowisetech.calc.model.EmiInterestChange;
import com.sowisetech.calc.model.FinancialPlanning;
import com.sowisetech.calc.model.Goal;
import com.sowisetech.calc.model.Insurance;
import com.sowisetech.calc.model.InsuranceItem;
import com.sowisetech.calc.model.InterestChange;
import com.sowisetech.calc.model.LoanPlanning;
import com.sowisetech.calc.model.Networth;
import com.sowisetech.calc.model.NetworthSummary;
import com.sowisetech.calc.model.PartialPayment;
import com.sowisetech.calc.model.Party;
import com.sowisetech.calc.model.Plan;
import com.sowisetech.calc.model.Priority;
import com.sowisetech.calc.model.PriorityItem;
import com.sowisetech.calc.model.RiskPortfolio;
import com.sowisetech.calc.model.RiskProfile;
import com.sowisetech.calc.model.RiskQuestionaire;
import com.sowisetech.calc.model.RiskSummary;
import com.sowisetech.calc.model.Urgency;
import com.sowisetech.calc.request.CashFlowItemReq;
import com.sowisetech.calc.request.CashFlowRequest;
import com.sowisetech.calc.request.CashFlowRequestValidator;
import com.sowisetech.calc.request.EmiCalculatorRequest;
import com.sowisetech.calc.request.EmiCalculatorRequestValidator;
import com.sowisetech.calc.request.EmiCapacityRequest;
import com.sowisetech.calc.request.EmiCapacityRequestValidator;
import com.sowisetech.calc.request.EmiChangeReq;
import com.sowisetech.calc.request.EmiChangeRequest;
import com.sowisetech.calc.request.EmiChangeRequestValidator;
import com.sowisetech.calc.request.EmiInterestChangeReq;
import com.sowisetech.calc.request.EmiInterestChangeRequest;
import com.sowisetech.calc.request.EmiInterestChangeRequestValidator;
import com.sowisetech.calc.request.FutureValueAnnuityDueRequestValidator;
import com.sowisetech.calc.request.FutureValueAnnuityRequestValidator;
import com.sowisetech.calc.request.FutureValueRequest;
import com.sowisetech.calc.request.FutureValueRequestValidator;
import com.sowisetech.calc.request.GoalRequest;
import com.sowisetech.calc.request.GoalRequestValidator;
import com.sowisetech.calc.request.CalcIdRequest;
import com.sowisetech.calc.request.InsuranceRequest;
import com.sowisetech.calc.request.InsuranceRequestValidator;
import com.sowisetech.calc.request.InterestChangeReq;
import com.sowisetech.calc.request.InterestChangeRequest;
import com.sowisetech.calc.request.InterestChangeRequestValidator;
import com.sowisetech.calc.request.NetworthReq;
import com.sowisetech.calc.request.NetworthRequest;
import com.sowisetech.calc.request.NetworthRequestValidator;
import com.sowisetech.calc.request.PartialPaymentReq;
import com.sowisetech.calc.request.PartialPaymentRequest;
import com.sowisetech.calc.request.PartialPaymentRequestValidator;
import com.sowisetech.calc.request.PlanRequest;
import com.sowisetech.calc.request.PlanRequestValidator;
import com.sowisetech.calc.request.PresentValueAnnuityDueReqValidator;
import com.sowisetech.calc.request.PresentValueAnnuityRequestValidator;
import com.sowisetech.calc.request.PriorityReq;
import com.sowisetech.calc.request.PriorityRequest;
import com.sowisetech.calc.request.PriorityRequestValidator;
import com.sowisetech.calc.request.RateFinderAnnuityRequestValidator;
import com.sowisetech.calc.request.RateFinderRequest;
import com.sowisetech.calc.request.RateFinderRequestValidator;
import com.sowisetech.calc.request.RiskProfileReq;
import com.sowisetech.calc.request.RiskProfileRequest;
import com.sowisetech.calc.request.TargetValueRequest;
import com.sowisetech.calc.request.TargetValueRequestValidator;
import com.sowisetech.calc.request.TenureFinderAnnuityRequestValidator;
import com.sowisetech.calc.request.TenureFinderRequest;
import com.sowisetech.calc.request.TenureFinderRequestValidator;
import com.sowisetech.calc.response.AmortisationResponse;
import com.sowisetech.calc.response.AnswerRes;
import com.sowisetech.calc.response.CashFlowResponse;
import com.sowisetech.calc.response.EmiCalculatorResponse;
import com.sowisetech.calc.response.EmiCapacityResponse;
import com.sowisetech.calc.response.EmiChangeRes;
import com.sowisetech.calc.response.EmiChangeResponse;
import com.sowisetech.calc.response.EmiIntChangeResponse;
import com.sowisetech.calc.response.CalcErrorResponse;
import com.sowisetech.calc.response.InterestChangeResponse;
import com.sowisetech.calc.response.NetworthResponse;
import com.sowisetech.calc.response.PartialPaymentResponse;
import com.sowisetech.calc.response.RateFinderResponse;
import com.sowisetech.calc.response.Response;
import com.sowisetech.calc.response.ResponseData;
import com.sowisetech.calc.response.ResponseMessage;
import com.sowisetech.calc.response.RiskProfileResponse;
import com.sowisetech.calc.response.RiskQuestionaireResponse;
import com.sowisetech.calc.response.TotalValueResponse;
import com.sowisetech.calc.response.CalcSuccessResponse;
import com.sowisetech.calc.response.TenureFinderResponse;
import com.sowisetech.calc.service.CalcService;
import com.sowisetech.calc.util.CalcAppMessages;
import com.sowisetech.calc.util.CalcConstants;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CalcController {

	@Autowired
	CalcAppMessages appMessages;

	@Autowired
	PlanRequestValidator planRequestValidator;
	@Autowired
	GoalRequestValidator goalRequestValidator;
	@Autowired
	CashFlowRequestValidator cashFlowRequestValidator;
	@Autowired
	NetworthRequestValidator networthRequestValidator;
	@Autowired
	PriorityRequestValidator priorityRequestValidator;
	@Autowired
	InsuranceRequestValidator insuranceRequestValidator;
	@Autowired
	FutureValueRequestValidator futureValueRequestValidator;
	@Autowired
	FutureValueAnnuityRequestValidator futureValueAnnuityRequestValidator;
	@Autowired
	FutureValueAnnuityDueRequestValidator futureValueAnnuityDueRequestValidator;
	@Autowired
	TargetValueRequestValidator targetValueRequestValidator;
	@Autowired
	PresentValueAnnuityRequestValidator presentValueAnnuityRequestValidator;
	@Autowired
	PresentValueAnnuityDueReqValidator presentValueAnnuityDueReqValidator;
	@Autowired
	RateFinderRequestValidator rateFinderRequestValidator;
	@Autowired
	RateFinderAnnuityRequestValidator rateFinderAnnuityRequestValidator;
	@Autowired
	TenureFinderRequestValidator tenureFinderRequestValidator;
	@Autowired
	TenureFinderAnnuityRequestValidator tenureFinderAnnuityRequestValidator;
	@Autowired
	EmiCalculatorRequestValidator emiCalculatorRequestValidator;
	@Autowired
	EmiCapacityRequestValidator emiCapacityRequestValidator;
	@Autowired
	InterestChangeRequestValidator interestChangeRequestValidator;
	@Autowired
	PartialPaymentRequestValidator partialPaymentRequestValidator;
	@Autowired
	EmiChangeRequestValidator emiChangeRequestValidator;
	@Autowired
	EmiInterestChangeRequestValidator emiInterestChangeRequestValidator;
	@Autowired
	CalcErrorResponse errorResponse;
	@Autowired
	CalcSuccessResponse successResponse;
	@Autowired
	CalcService calcService;

//	@RequestMapping(value = "/ecv", method = RequestMethod.GET)
//	public ResponseEntity getEcv() {
//		return new ResponseEntity<>(HttpStatus.OK);
//	}

	@RequestMapping(value = "/addPlan", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> addPlan(@RequestBody PlanRequest planRequest) {
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();

		// Get partyId from request
		long partyId = planRequest.getPartyId();
		// check whether the party is available in party table or not by partyId
		if (calcService.fetchParty(partyId) == null) {
			// If party is not available, returning the response message as no record found
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.NO_RECORD_FOUND);
			responseMessage.setResponseDescription(appMessages.getNo_record_found());

			Response response = new Response();
			response.setResponseMessage(responseMessage);
			return ResponseEntity.ok().body(response);
		} else {
			// Validating the request field values
			errors = planRequestValidator.validate(planRequest);
			String referenceId;
			if (errors.isEmpty() == true) {
				if (planRequest.getParentPartyId() == 0) {
					// If reference Id is null, then the own party's id will be reference Id
					referenceId = calcService.fetchRoleBasedIdByPartyId(partyId);
				} else {
					referenceId = calcService.fetchRoleBasedIdByParentPartyId(planRequest.getParentPartyId());
				}
				// Changing the values from request to model by calling getValuePlanInfo
				Plan plan = getValuePlanInfo(planRequest);
				plan.setReferenceId(referenceId);
				// Add the plan into table
				calcService.addPlanInfo(plan);
				// Returning the response
				ResponseMessage responseMessage = new ResponseMessage();
				responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
				responseMessage.setResponseDescription(appMessages.getPlan_added_successfully());
				Response response = new Response();
				response.setResponseMessage(responseMessage);
				return ResponseEntity.ok().body(response);
			} else if (errors.isEmpty() == false) {
				// if there is a error while validating request,return the errors
				ResponseMessage responseMessage = new ResponseMessage();
				responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
				responseMessage.setResponseDescription(appMessages.getError());
				ResponseData responseData = new ResponseData();
				responseData.setData(errors);
				Response response = new Response();
				response.setResponseMessage(responseMessage);
				response.setResponseData(responseData);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/fetchPlan", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> fetchPlanByReferenceId(@RequestBody CalcIdRequest idRequest) {
		String id = idRequest.getId();
		// Fetch the plan from plan table by referenceId
		List<Plan> plan = calcService.fetchPlanByReferenceId(id);
		if (plan.size() == 0) {
			// If plan is null, return message as no record found
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.NO_RECORD_FOUND);
			responseMessage.setResponseDescription(appMessages.getNo_record_found());
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			return ResponseEntity.ok().body(response);
		} else {
			// If plan is available then return plan
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
			responseMessage.setResponseDescription(appMessages.getSuccess());
			ResponseData responseData = new ResponseData();
			responseData.setData(plan);
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			response.setResponseData(responseData);
			return ResponseEntity.ok().body(response);
		}
	}

	private Plan getValuePlanInfo(PlanRequest planRequest) {
		Plan plan = new Plan();
		if (planRequest != null && planRequest.getPartyId() != 0) {
			plan.setPartyId(planRequest.getPartyId());
		}
		if (planRequest != null && planRequest.getParentPartyId() != 0) {
			plan.setParentPartyId(planRequest.getParentPartyId());
		}
		if (planRequest != null && planRequest.getName() != null) {
			plan.setName(planRequest.getName());
		}
		if (planRequest != null && planRequest.getAge() != null) {
			plan.setAge(Integer.parseInt(planRequest.getAge()));
		}
		if (planRequest != null && planRequest.getSelectedPlan().size() != 0) {
			String selectedPlan = String.join(",", planRequest.getSelectedPlan());
			plan.setSelectedPlan(selectedPlan);
		}
		if (planRequest != null && planRequest.getSpouse() != null) {
			plan.setSpouse(planRequest.getSpouse());
		}
		if (planRequest != null && planRequest.getParents() != null) {
			plan.setParents(planRequest.getParents());
		}
		if (planRequest != null && planRequest.getChildren() != null) {
			plan.setChildren(Integer.parseInt(planRequest.getChildren()));
		}
		if (planRequest != null && planRequest.getGrandParent() != null) {
			plan.setGrandParent(planRequest.getGrandParent());
		}
		if (planRequest != null && planRequest.getSibilings() != null) {
			plan.setSibilings(planRequest.getSibilings());
		}
		return plan;
	}

	// FINANCIAL PLANNING
	// My Goal
	@RequestMapping(value = "/calculateGoal", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> calculateGoal(@RequestBody GoalRequest goalRequest) {
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		long partyId = goalRequest.getPartyId();
		if (calcService.fetchParty(partyId) == null) {
			// If party is not available return no record found
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.NO_RECORD_FOUND);
			responseMessage.setResponseDescription(appMessages.getNo_record_found());
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			return ResponseEntity.ok().body(response);
		} else {
			// Validating goalRequest
			errors = goalRequestValidator.validate(goalRequest);
			if (errors.isEmpty() == true) {
				if (goalRequest.getGoalName() != null && goalRequest.getTenure() != null
						&& goalRequest.getTenureType() != null && goalRequest.getGoalAmount() != null
						&& goalRequest.getCurrentAmount() != null && goalRequest.getInflationRate() != null
						&& goalRequest.getGrowthRate() != null && goalRequest.getAnnualInvestmentRate() != null
						&& goalRequest.getPartyId() != 0) {
					int tenure = 0;
					if (goalRequest.getTenureType().equals(CalcConstants.MONTH)) {
						tenure = (Integer.parseInt(goalRequest.getTenure())) / 12;
					} else {
						tenure = Integer.parseInt(goalRequest.getTenure());
					}
					double goalAmt = Double.parseDouble(goalRequest.getGoalAmount());
					double inflationRate = (Double.parseDouble(goalRequest.getInflationRate())) / 100;
					double currentAmt = Double.parseDouble(goalRequest.getCurrentAmount());
					double growthRate = (Double.parseDouble(goalRequest.getGrowthRate())) / 100;
					double returnRate = (Double.parseDouble(goalRequest.getGrowthRate()) / 100) / 12;
					double annualInvRate = (Double.parseDouble(goalRequest.getAnnualInvestmentRate())) / 100;

					// FutureCost
					double futureCost = calcService.calculateGoalFutureCost(goalAmt, inflationRate, tenure);
					// Current Investment
					double futureValue = calcService.calculateGoalCurrentInvestment(currentAmt, growthRate, tenure);
					// FinalCorpus
					double finalCorpus = calcService.calculateGoalFinalCorpus(futureCost, futureValue);
					// Monthly Required Investment
					double monthlyInv = calcService.calculateGoalMonthlyInvestment(growthRate, annualInvRate,
							finalCorpus, tenure, returnRate);
					// Yearly Required Investment
					double annualInv = calcService.calculateGoalAnnualyInvestment(growthRate, annualInvRate,
							finalCorpus, tenure);

					// Changing values from request to model by calling getValueGoalInfo
					Goal goal = getValueGoalInfo(goalRequest);
					goal.setPartyId(goalRequest.getPartyId());
					goal.setParentPartyId(goalRequest.getParentPartyId());
					goal.setRateOfReturn(returnRate * 100);
					goal.setFinalCorpus(finalCorpus);
					goal.setFutureCost(roundingNumber(futureCost));
					goal.setFutureValue(roundingNumber(futureValue));
					goal.setMonthlyInv(roundingNumber(monthlyInv));
					goal.setAnnualInv(roundingNumber(annualInv));
					if (calcService.fetchGoalByPartyIdAndGoalName(partyId, goalRequest.getGoalName()) == null) {
						// If the goal is not available for the party, Then add as new record
						calcService.addGoalInfo(goal);
					} else {
						// If the goal is already is present in table, update the goal
						calcService.updateGoalInfo(goal);
					}
					// Response
					// Fetch all goals by partyId
					List<Goal> goalRes = calcService.fetchGoalByPartyId(partyId);
					// Return the response with all goals added by party
					ResponseMessage responseMessage = new ResponseMessage();
					responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
					responseMessage.setResponseDescription(appMessages.getGoal_calculated_successfully());
					ResponseData responseData = new ResponseData();
					responseData.setData(goalRes);
					Response response = new Response();
					response.setResponseMessage(responseMessage);
					response.setResponseData(responseData);
					return ResponseEntity.ok().body(response);
				} else {
					// Return the validation errors
					ResponseMessage responseMessage = new ResponseMessage();
					responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
					responseMessage.setResponseDescription(appMessages.getFields_cannot_be_empty());
					Response response = new Response();
					response.setResponseMessage(responseMessage);
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
				}
			} else if (errors.isEmpty() == false) {
				ResponseMessage responseMessage = new ResponseMessage();
				responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
				responseMessage.setResponseDescription(appMessages.getError());
				ResponseData responseData = new ResponseData();
				responseData.setData(errors);
				Response response = new Response();
				response.setResponseMessage(responseMessage);
				response.setResponseData(responseData);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	private Goal getValueGoalInfo(GoalRequest goalRequest) {
		Goal goal = new Goal();
		goal.setGoalName(goalRequest.getGoalName());
		goal.setTenure(Integer.parseInt(goalRequest.getTenure()));
		goal.setTenureType(goalRequest.getTenureType());
		goal.setGoalAmount(roundingNumber(Double.parseDouble(goalRequest.getGoalAmount())));
		goal.setInflationRate(Double.parseDouble(goalRequest.getInflationRate()));
		goal.setCurrentAmount(roundingNumber(Double.parseDouble(goalRequest.getCurrentAmount())));
		goal.setGrowthRate(Double.parseDouble(goalRequest.getGrowthRate()));
		goal.setAnnualInvestmentRate(Double.parseDouble(goalRequest.getAnnualInvestmentRate()));
		return goal;
	}

	// CashFlow
	@RequestMapping(value = "/calculateCashFlow", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> calculateCashFlow(@RequestBody CashFlowRequest cashFlowRequest) {
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		long partyId = cashFlowRequest.getPartyId();
		if (calcService.fetchParty(partyId) == null) {
			// If party is not available return no record found
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.NO_RECORD_FOUND);
			responseMessage.setResponseDescription(appMessages.getNo_record_found());
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			return ResponseEntity.ok().body(response);
		} else {
			// Validating cashFlow Request
			errors = cashFlowRequestValidator.validate(cashFlowRequest);
			if (errors.isEmpty() == true) {
				for (CashFlowItemReq cashFlowItemReq : cashFlowRequest.getCashFlowItemReq()) {
					int cashFlowItemTypeId = calcService
							.fetchCashFlowItemTypeIdByItemId(cashFlowItemReq.getCashFlowItemId());
					CashFlow cashFlow = getValueCashFlowInfo(cashFlowItemReq);
					cashFlow.setCashFlowItemTypeId(cashFlowItemTypeId);
					cashFlow.setPartyId(cashFlowRequest.getPartyId());
					cashFlow.setCashFlowItemId(cashFlowItemReq.getCashFlowItemId());
					cashFlow.setDate(cashFlowRequest.getDate());
					if (calcService.fetchCashFlowByPartyIdAndItemId(partyId,
							cashFlowItemReq.getCashFlowItemId()) == null) {
						// if partyId and cashFlowItemId combination is not available,then add as new
						// record
						calcService.addCashFlow(cashFlow);
					} else {
						// if partyId and cashFlowItemId combination is already present,then update it.
						calcService.updateCashFlow(cashFlow);
					}
				}
				// cash flow summary

				// if cash flow summary is already present for this partyId then remove it, and
				// calculate again and add as new record
				if (calcService.fetchCashFlowSummaryByPartyId(partyId) != null) {
					calcService.removeCashFlowSummary(partyId);
				}

				// Recurring Expenditure
				List<Integer> recurringExpItemType = calcService.fetchRecurringExpenditureItemType();
				List<CashFlow> cashFlowRecurringExp = new ArrayList<CashFlow>();
				for (int typeId : recurringExpItemType) {
					cashFlowRecurringExp.addAll(calcService.fetchCashFlowByPartyIdAndTypeId(partyId, typeId));
				}
				double recurringExpenditure = 0;
				for (CashFlow cashFlow : cashFlowRecurringExp) {
					recurringExpenditure = recurringExpenditure + cashFlow.getActualAmt();
				}
				// Non-Recurring Expenditure
				List<CashFlow> cashFlowNonRecurringExp = new ArrayList<CashFlow>();
				cashFlowNonRecurringExp = calcService.fetchNonRecurringExpenditureByPartyId(partyId);
				double nonRecurringExpenditure = 0;
				for (CashFlow cashFlow : cashFlowNonRecurringExp) {
					nonRecurringExpenditure = nonRecurringExpenditure + cashFlow.getActualAmt();
				}
				// Recurring Income
				List<CashFlow> cashFlowRecurringIncome = new ArrayList<CashFlow>();
				cashFlowRecurringIncome = calcService.fetchRecurringIncomeByPartyId(partyId);
				double recurringIncome = 0;
				for (CashFlow cashFlow : cashFlowRecurringIncome) {
					recurringIncome = recurringIncome + cashFlow.getActualAmt();
				}
				// Non Recurring Income
				List<CashFlow> cashFlowNonRecurringIncome = new ArrayList<CashFlow>();
				cashFlowNonRecurringIncome = calcService.fetchNonRecurringIncomeByPartyId(partyId);
				double nonRecurringIncome = 0;
				for (CashFlow cashFlow : cashFlowNonRecurringIncome) {
					nonRecurringIncome = nonRecurringIncome + cashFlow.getActualAmt();
				}
				// CashFlow Recurring
				double cashFlowRecurring = 0;
				cashFlowRecurring = recurringExpenditure - recurringIncome;
				// CashFlow Non Recurring
				double cashFlowNonRecurring = 0;
				cashFlowNonRecurring = nonRecurringExpenditure - nonRecurringIncome;

				CashFlowSummary cashFlowSummary = new CashFlowSummary();
				cashFlowSummary.setMonthlyRecurExpense(roundingNumber(recurringExpenditure));
				cashFlowSummary.setYearlyRecurExpense(roundingNumber(recurringExpenditure * 12));
				cashFlowSummary.setNonRecurExpense(roundingNumber(nonRecurringExpenditure));
				cashFlowSummary.setMonthlyRecurIncome(roundingNumber(recurringIncome));
				cashFlowSummary.setYearlyRecurIncome(roundingNumber(recurringIncome * 12));
				cashFlowSummary.setNonRecurIncome(roundingNumber(nonRecurringIncome));
				cashFlowSummary.setMonthlyRecurCashFlow(roundingNumber(cashFlowRecurring));
				cashFlowSummary.setYearlyRecurCashFlow(roundingNumber(cashFlowRecurring * 12));
				cashFlowSummary.setNonRecurCashflow(roundingNumber(cashFlowNonRecurring));
				cashFlowSummary.setPartyId(partyId);
				// Add cashflow summary into Table
				calcService.addCashFlowSummary(cashFlowSummary);

				// Response

				// Response with all cashflow record for this partyId and also cashflow summary
				List<CashFlow> cashFlowListRes = fetchAllCashFlowByPartyId(partyId);
				CashFlowSummary cashFlowSummaryRes = calcService.fetchCashFlowSummaryByPartyId(partyId);

				CashFlowResponse cashFlowResponse = new CashFlowResponse();
				cashFlowResponse.setCashFlowList(cashFlowListRes);
				cashFlowResponse.setCashFlowSummary(cashFlowSummaryRes);

				ResponseMessage responseMessage = new ResponseMessage();
				responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
				responseMessage.setResponseDescription(appMessages.getCashflow_calculated_successfully());
				ResponseData responseData = new ResponseData();
				responseData.setData(cashFlowResponse);
				Response response = new Response();
				response.setResponseMessage(responseMessage);
				response.setResponseData(responseData);
				return ResponseEntity.ok().body(response);

			} else if (errors.isEmpty() == false) {
				// return validation errors
				ResponseMessage responseMessage = new ResponseMessage();
				responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
				responseMessage.setResponseDescription(appMessages.getError());
				ResponseData responseData = new ResponseData();
				responseData.setData(errors);
				Response response = new Response();
				response.setResponseMessage(responseMessage);
				response.setResponseData(responseData);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}

		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	private CashFlow getValueCashFlowInfo(CashFlowItemReq cashFlowItemReq) {
		CashFlow cashFlow = new CashFlow();
		if (cashFlowItemReq != null && cashFlowItemReq.getActualAmt() != null) {
			cashFlow.setActualAmt(roundingNumber(Double.parseDouble(cashFlowItemReq.getActualAmt())));
		}
		if (cashFlowItemReq != null && cashFlowItemReq.getBudgetAmt() != null) {
			cashFlow.setBudgetAmt(roundingNumber(Double.parseDouble(cashFlowItemReq.getBudgetAmt())));
		}
		if (cashFlowItemReq != null && cashFlowItemReq.getCashFlowItemId() != 0) {
			cashFlow.setCashFlowItemId(cashFlowItemReq.getCashFlowItemId());
		}

		return cashFlow;
	}

	// Networth
	@RequestMapping(value = "/calculateNetworth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> calculateNetworth(@RequestBody NetworthRequest networthRequest) {
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		long partyId = networthRequest.getPartyId();
		if (calcService.fetchParty(partyId) == null) {
			// If party is not available return no record found
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.NO_RECORD_FOUND);
			responseMessage.setResponseDescription(appMessages.getNo_record_found());
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			return ResponseEntity.ok().body(response);
		} else {
			// Validating networth Request
			errors = networthRequestValidator.validate(networthRequest);
			if (errors.isEmpty() == true) {

				for (NetworthReq networthReq : networthRequest.getNetworthReq()) {
					// Changing from request to model
					Networth networth = getValueNetwothInfo(networthReq);
					networth.setPartyId(networthRequest.getPartyId());
					int accountTypeId = calcService.fetchAccountTypeIdByEntryId(networthReq.getAccountEntryId());
					networth.setAccountTypeId(accountTypeId);
					if (calcService.fetchNetworthByPartyIdAndEntryId(partyId,
							networthReq.getAccountEntryId()) == null) {
						// if partyId and accountEntryId combination is not available, then add as new
						// record
						calcService.addNetworth(networth);
					} else {
						// if partyId and accountEntryId combination is already present, then update it
						calcService.updateNetworth(networth);
					}
				}
				// Networth Summary

				// If networth summary already present for partyId, then remove it and calculate
				// again
				if (calcService.fetchNetworthSummaryByPartyId(partyId) != null) {
					calcService.removeNetworthSummary(partyId);
				}

				List<Networth> networthAssetsList = calcService.fetchNetworthByAssets(networthRequest.getPartyId());
				double totalCurrentAssets = 0;
				double totalFutureAssets = 0;
				// Calculate total assests for current and future
				for (Networth networth : networthAssetsList) {
					totalCurrentAssets = totalCurrentAssets + networth.getValue();
					totalFutureAssets = totalFutureAssets + networth.getFutureValue();
				}

				List<Networth> networthLiabilitiesList = calcService
						.fetchNetworthByLiabilities(networthRequest.getPartyId());
				double totalCurrentLiabilities = 0;
				double totalFutureLiabilities = 0;
				// Calculate total liabilities for current and future
				for (Networth networth : networthLiabilitiesList) {
					totalCurrentLiabilities = totalCurrentLiabilities + networth.getValue();
					totalFutureLiabilities = totalFutureLiabilities + networth.getFutureValue();
				}
				// Calculate networth for current and future
				double currentNetworth = totalCurrentAssets - totalCurrentLiabilities;
				double futureNetworth = totalFutureAssets - totalFutureLiabilities;

				NetworthSummary networthSummary = new NetworthSummary();
				networthSummary.setPartyId(networthRequest.getPartyId());
				networthSummary.setCurrent_assetValue(roundingNumber(totalCurrentAssets));
				networthSummary.setCurrent_liability(roundingNumber(totalCurrentLiabilities));
				networthSummary.setFuture_assetValue(roundingNumber(totalFutureAssets));
				networthSummary.setFuture_liability(roundingNumber(totalFutureLiabilities));
				networthSummary.setNetworth(roundingNumber(currentNetworth));
				networthSummary.setFuture_networth(roundingNumber(futureNetworth));
				// Add networth summary
				calcService.addNetworthSummary(networthSummary);

				// Response
				// Response with all networth record for this partyId and also networth summary
				List<Networth> networthListRes = fetchAllNetworthByPartyId(partyId);
				NetworthSummary networthSummaryRes = calcService.fetchNetworthSummaryByPartyId(partyId);

				NetworthResponse networthResponse = new NetworthResponse();
				networthResponse.setNetworthList(networthListRes);
				networthResponse.setNetworthSummary(networthSummaryRes);

				ResponseMessage responseMessage = new ResponseMessage();
				responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
				responseMessage.setResponseDescription(appMessages.getNetworth_calculated_successfully());
				ResponseData responseData = new ResponseData();
				responseData.setData(networthResponse);
				Response response = new Response();
				response.setResponseMessage(responseMessage);
				response.setResponseData(responseData);
				return ResponseEntity.ok().body(response);
			} else if (errors.isEmpty() == false) {
				// Return validation errors
				ResponseMessage responseMessage = new ResponseMessage();
				responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
				responseMessage.setResponseDescription(appMessages.getError());
				ResponseData responseData = new ResponseData();
				responseData.setData(errors);
				Response response = new Response();
				response.setResponseMessage(responseMessage);
				response.setResponseData(responseData);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}

		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	private Networth getValueNetwothInfo(NetworthReq networthReq) {
		Networth networth = new Networth();
		if (networthReq != null && networthReq.getAccountEntryId() != 0) {
			networth.setAccountEntryId(networthReq.getAccountEntryId());
		}
		if (networthReq != null && networthReq.getValue() != null) {
			networth.setValue(roundingNumber(Double.parseDouble(networthReq.getValue())));
		}
		if (networthReq != null && networthReq.getFutureValue() != null) {
			networth.setFutureValue(roundingNumber(Double.parseDouble(networthReq.getFutureValue())));
		}
		return networth;
	}

	// Priorities
	@RequestMapping(value = "/calculatePriorities", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> calculatePriorities(@RequestBody PriorityRequest priorityRequest) {
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		long partyId = priorityRequest.getPartyId();
		if (calcService.fetchParty(partyId) == null) {
			// If party is not available return no record found
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.NO_RECORD_FOUND);
			responseMessage.setResponseDescription(appMessages.getNo_record_found());
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			return ResponseEntity.ok().body(response);
		} else {
			// Validating priorityRequest
			errors = priorityRequestValidator.validate(priorityRequest);
			if (errors.isEmpty() == true) {
				LinkedHashMap<Integer, Integer> priorityOrder = new LinkedHashMap<Integer, Integer>();

				for (PriorityReq priorityReq : priorityRequest.getPriorityReq()) {
					// Changing from request to model by calling getValuePriorityInfo
					Priority priority = getValuePriorityInfo(priorityReq);
					priority.setPartyId(priorityRequest.getPartyId());
					if (calcService.fetchPriorityByPartyIdAndItemId(partyId, priorityReq.getPriorityItemId()) == null) {
						// If priorityItemId and partyId combination is not present, then add as new
						// record
						calcService.addPriorities(priority);
					} else {
						// If priorityItemId and partyId combination is present, then update it
						calcService.updatePriority(priority);
					}
				}
				// Priority Order
				// Order the priority by urgency and timeline
				List<Priority> priorityList = calcService.fetchPriorityByPartyId(partyId);
				// fetch all priority by PartyId
				HashMap<Integer, Integer> itemIdAndUrgency = new HashMap<>();
				for (Priority priority : priorityList) {
					itemIdAndUrgency.put(priority.getPriorityItemId(), priority.getUrgencyId());
				}
				// first order by urgency
				LinkedHashMap<Integer, Integer> sortedByUrgency = new LinkedHashMap<Integer, Integer>();
				itemIdAndUrgency.entrySet().stream().sorted(Map.Entry.comparingByValue())
						.forEachOrdered(x -> sortedByUrgency.put(x.getKey(), x.getValue()));
				// fetch priority for urgency
				Iterator<Map.Entry<Integer, Integer>> iterator = sortedByUrgency.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry<Integer, Integer> entry = iterator.next();
					List<Integer> urgencyKeys = new ArrayList<>();
					for (Entry<Integer, Integer> entryKey : sortedByUrgency.entrySet()) {
						if (Objects.equals(entry.getValue(), entryKey.getValue())) {
							urgencyKeys.add(entryKey.getKey());
						}
					}
					// fetch timeline by priorityItemId
					HashMap<Integer, Integer> itemIdAndTimeline = new HashMap<>();
					for (int itemId : urgencyKeys) {
						Priority priority = calcService.fetchPriorityByPartyIdAndItemId(partyId, itemId);
						itemIdAndTimeline.put(priority.getPriorityItemId(), priority.getTimeLine());
					}
					// sorting by timeline
					LinkedHashMap<Integer, Integer> sortedByTimeline = new LinkedHashMap<Integer, Integer>();
					itemIdAndTimeline.entrySet().stream().sorted(Map.Entry.comparingByValue())
							.forEachOrdered(x -> sortedByTimeline.put(x.getKey(), x.getValue()));
					for (Entry<Integer, Integer> entryByTimeline : sortedByTimeline.entrySet()) {
						priorityOrder.put(entryByTimeline.getKey(), entryByTimeline.getValue());
					}
					for (int i = 1; i < urgencyKeys.size(); i++) {
						iterator.next();
					}
				}
				// Set priority order and added into table
				int order = 0;
				for (Entry<Integer, Integer> priorityEntry : priorityOrder.entrySet()) {
					int priorityItemId = priorityEntry.getKey();
					order = order + 1;
					calcService.updatePriorityOrder(partyId, priorityItemId, order);
				}

				// Response
				// Response with all priority for this partyId
				List<Priority> priorityListRes = fetchAllPriorityByPartyId(partyId);
				ResponseMessage responseMessage = new ResponseMessage();
				responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
				responseMessage.setResponseDescription(appMessages.getPriority_added_successfully());
				ResponseData responseData = new ResponseData();
				responseData.setData(priorityListRes);
				Response response = new Response();
				response.setResponseMessage(responseMessage);
				response.setResponseData(responseData);
				return ResponseEntity.ok().body(response); // return ResponseEntity.ok()
				// .body(successResponse.createSuccessResponse(AppMessages.PRIORITY_ADDED_SUCCESSFULLY));

			} else if (errors.isEmpty() == false) {
				// return validation errors
				ResponseMessage responseMessage = new ResponseMessage();
				responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
				responseMessage.setResponseDescription(appMessages.getError());
				ResponseData responseData = new ResponseData();
				responseData.setData(errors);
				Response response = new Response();
				response.setResponseMessage(responseMessage);
				response.setResponseData(responseData);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}

		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	private Priority getValuePriorityInfo(PriorityReq priorityReq) {
		Priority priority = new Priority();
		priority.setPriorityItemId(priorityReq.getPriorityItemId());
		priority.setTimeLine(Integer.parseInt(priorityReq.getTimeline()));
		priority.setValue(roundingNumber(Double.parseDouble(priorityReq.getValue())));
		priority.setUrgencyId(priorityReq.getUrgencyId());
		return priority;
	}

	// Insurance
	@RequestMapping(value = "/calculateInsurance", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> calculateInsurance(@RequestBody InsuranceRequest insuranceRequest) {
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		long partyId = insuranceRequest.getPartyId();
		if (calcService.fetchParty(partyId) == null) {
			// If party is not available return no record found
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.NO_RECORD_FOUND);
			responseMessage.setResponseDescription(appMessages.getNo_record_found());
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			return ResponseEntity.ok().body(response);
		} else {
			// Validate insurance request
			errors = insuranceRequestValidator.validate(insuranceRequest);
			if (errors.isEmpty() == true) {
				// Changing from request to model
				Insurance insurance = getValueInsuranceInfo(insuranceRequest);
				double requiredInsurance = 0;
				double annualIncome = Double.parseDouble(insuranceRequest.getAnnualIncome());
				double existingInsurance = Double.parseDouble(insuranceRequest.getExistingInsurance());

				if (insuranceRequest.getStability().equals(CalcConstants.STABLE)
						&& insuranceRequest.getPredictability().equals(CalcConstants.PREDICTABLE)) {
					requiredInsurance = annualIncome * 10;
				} else if (insuranceRequest.getStability().equals(CalcConstants.STABLE)
						&& insuranceRequest.getPredictability().equals(CalcConstants.UNPREDICTABLE)) {
					requiredInsurance = annualIncome * 15;
				} else if (insuranceRequest.getStability().equals(CalcConstants.FLUCTUATING)
						&& insuranceRequest.getPredictability().equals(CalcConstants.PREDICTABLE)) {
					requiredInsurance = annualIncome * 10;
				} else if (insuranceRequest.getStability().equals(CalcConstants.FLUCTUATING)
						&& insuranceRequest.getPredictability().equals(CalcConstants.UNPREDICTABLE)) {
					requiredInsurance = annualIncome * 15;
				}
				double additionalInsurance = requiredInsurance - existingInsurance;

				insurance.setPartyId(partyId);
				insurance.setRequiredInsurance(roundingNumber(requiredInsurance));
				insurance.setAdditionalInsurance(roundingNumber(additionalInsurance));
				if (calcService.fetchInsuranceByPartyId(partyId) == null) {
					// If Insuarnce is not present then add as new record
					calcService.addInsurance(insurance);
				} else {
					// If Insurance is already present for this partyId, then update it
					calcService.updateInsurance(insurance);
				}

				// Response
				// response with insurance added by this partyId
				ResponseMessage responseMessage = new ResponseMessage();
				responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
				responseMessage.setResponseDescription(appMessages.getInsurance_calculated_successfully());

				InsuranceItem insuranceRes = calcService.fetchInsuranceItemByPartyId(partyId);

				ResponseData responseData = new ResponseData();
				responseData.setData(insuranceRes);

				Response response = new Response();
				response.setResponseMessage(responseMessage);
				response.setResponseData(responseData);

				return ResponseEntity.ok().body(response);
			} else if (errors.isEmpty() == false) {
				// return validation errors
				ResponseMessage responseMessage = new ResponseMessage();
				responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
				responseMessage.setResponseDescription(appMessages.getError());
				ResponseData responseData = new ResponseData();
				responseData.setData(errors);
				Response response = new Response();
				response.setResponseMessage(responseMessage);
				response.setResponseData(responseData);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}

		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	private Insurance getValueInsuranceInfo(InsuranceRequest insuranceRequest) {
		Insurance insurance = new Insurance();
		insurance.setAnnualIncome(roundingNumber(Double.parseDouble(insuranceRequest.getAnnualIncome())));
		insurance.setStability(insuranceRequest.getStability());
		insurance.setPredictability(insuranceRequest.getPredictability());
		insurance.setExistingInsurance(roundingNumber(Double.parseDouble(insuranceRequest.getExistingInsurance())));
		return insurance;
	}

	// Risk Profile
	@RequestMapping(value = "/calculateRiskProfile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> calculateRiskProfile(@RequestBody RiskProfileRequest riskProfileRequest) {
		long partyId = riskProfileRequest.getPartyId();
		if (calcService.fetchParty(partyId) == null) {
			// If party is not available return no record found
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.NO_RECORD_FOUND);
			responseMessage.setResponseDescription(appMessages.getNo_record_found());
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			return ResponseEntity.ok().body(response);
		} else {
			for (RiskProfileReq riskProfileReq : riskProfileRequest.getRiskProfileReq()) {
				// Changing from request to model
				RiskProfile riskProfile = getValueRiskProfileInfo(riskProfileReq);
				riskProfile.setPartyId(riskProfileRequest.getPartyId());
				if (calcService.fetchRiskProfileByPartyIdAndQuestionId(partyId, riskProfile.getQuestionId()) == null) {
					// If partyId and questionId combination is not present, then add as new record
					calcService.addRiskProfile(riskProfile);
				} else {
					// If partyId and questionId combination is already present, then update it
					calcService.updateRiskProfile(riskProfile);
				}
			}
			// RiskProfile Summary
			// If risk summary is already present then remove it, and calculate again
			if (calcService.fetchRiskSummaryByPartyId(partyId) != null) {
				calcService.removeRiskSummary(partyId);
			}
			// calculate risksummary
			List<RiskProfile> riskProfileList = calcService.fetchRiskProfileByPartyId(partyId);
			int score = 0;
			for (RiskProfile riskProfile : riskProfileList) {
				score = score + riskProfile.getScore();
			}
			String points = "";
			if (score <= 30) {
				points = "30 or less";
			} else if (score >= 31 && score <= 40) {
				points = "31 - 40";
			} else if (score >= 41 && score <= 51) {
				points = "41 - 51";
			} else if (score >= 52 && score <= 61) {
				points = "52 - 61";
			} else if (score >= 62) {
				points = "62 or more";
			}
			RiskPortfolio riskPortFolio = calcService.fetchRiskPortfolioByPoints(points);
			RiskSummary riskSummary = new RiskSummary();
			riskSummary.setPartyId(partyId);
			riskSummary.setBehaviour(riskPortFolio.getBehaviour());
			riskSummary.setEqty_alloc(riskPortFolio.getEquity());
			riskSummary.setDebt_alloc(riskPortFolio.getDebt());
			riskSummary.setCash_alloc(riskPortFolio.getCash());
			// Add risksummary into table
			calcService.addRiskSummary(riskSummary);

			// Response
			List<RiskProfile> riskProfileListRes = calcService.fetchRiskProfileByPartyId(partyId);
			RiskSummary riskSummaryRes = calcService.fetchRiskSummaryByPartyId(partyId);

			// Return response with all risk profile and risk summary for this partyId
			RiskProfileResponse riskProfileResponse = new RiskProfileResponse();
			riskProfileResponse.setRiskProfileList(riskProfileListRes);
			riskProfileResponse.setRiskSummary(riskSummaryRes);

			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
			responseMessage.setResponseDescription(appMessages.getRiskprofile_added_successfully());
			ResponseData responseData = new ResponseData();
			responseData.setData(riskProfileResponse);
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			response.setResponseData(responseData);
			return ResponseEntity.ok().body(response);
		}
	}

	private RiskProfile getValueRiskProfileInfo(RiskProfileReq riskProfileReq) {
		RiskProfile riskProfile = new RiskProfile();
		riskProfile.setQuestionId(riskProfileReq.getQuestionId());
		riskProfile.setAnswerId(riskProfileReq.getAnswerId());
		return riskProfile;
	}

	private double roundingNumber(double value) {
		String str = String.format("%.2f", value);
		double roundedValue = Double.valueOf(str);
		return roundedValue;
	}

	// INVESTMENT PLANNING
	// Future Value
	@RequestMapping(value = "/IP-FutureValue", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> calculateFutureValue(@RequestBody FutureValueRequest futureValueRequest) {
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		// Validating futureValue Request
		errors = futureValueRequestValidator.validate(futureValueRequest);
		if (errors.isEmpty() == true) {
			TotalValueResponse totResponse = new TotalValueResponse();
			// If invType is LUMSUM
			if (futureValueRequest.getInvType().equals(CalcConstants.LUMSUM)) {
				double invAmount = Double.parseDouble(futureValueRequest.getInvAmount());
				double annualGrowth = (Double.parseDouble(futureValueRequest.getAnnualGrowth())) / 100;
				int duration = Integer.parseInt(futureValueRequest.getDuration());
				double yearlyIncrease = (Double.parseDouble(futureValueRequest.getYearlyIncrease())) / 100;
				double totalPayment = invAmount
						* (Math.pow((1 + annualGrowth), duration) - Math.pow((1 + yearlyIncrease), duration))
						/ (annualGrowth - yearlyIncrease);
				totResponse.setTotalPayment(roundingNumber(totalPayment));
			} else if (futureValueRequest.getInvType().equals(CalcConstants.SIP)) {
				// If invType is SIP
				double invAmount = Double.parseDouble(futureValueRequest.getInvAmount());
				double annualGrowth = (Double.parseDouble(futureValueRequest.getAnnualGrowth())) / 100;
				int duration = Integer.parseInt(futureValueRequest.getDuration());
				double yearlyIncrease = (Double.parseDouble(futureValueRequest.getYearlyIncrease())) / 100;
				double totalPayment = invAmount
						* (Math.pow((1 + annualGrowth), duration) - Math.pow((1 + yearlyIncrease), duration))
						/ (annualGrowth - yearlyIncrease);
				totResponse.setTotalPayment(roundingNumber(totalPayment));
			}
			// Response with result total response
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
			responseMessage.setResponseDescription(appMessages.getValue_calculated_successfully());
			ResponseData responseData = new ResponseData();
			responseData.setData(totResponse);
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			response.setResponseData(responseData);
			return ResponseEntity.ok().body(response);

		} else if (errors.isEmpty() == false) {
			// return the validation errors
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
			responseMessage.setResponseDescription(appMessages.getError());
			ResponseData responseData = new ResponseData();
			responseData.setData(errors);
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			response.setResponseData(responseData);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}

		return new ResponseEntity<String>(HttpStatus.OK);

	}

	// // Future Value Annuity
	// @RequestMapping(value = "/IP-FutureValue-SIP", method = RequestMethod.POST,
	// produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	// public ResponseEntity<?> calculateFutureValueAnnuity(
	// @RequestBody FutureValueAnnuityRequest futureValueAnnuityRequest) {
	// HashMap<String, HashMap<String, String>> errors = new HashMap<String,
	// HashMap<String, String>>();
	// errors =
	// futureValueAnnuityRequestValidator.validate(futureValueAnnuityRequest);
	// if (errors.isEmpty() == true) {
	// double invAmount =
	// Double.parseDouble(futureValueAnnuityRequest.getInvAmount());
	// double annualGrowth =
	// (Double.parseDouble(futureValueAnnuityRequest.getAnnualGrowth())) / 100;
	// int duration = Integer.parseInt(futureValueAnnuityRequest.getDuration());
	// double yearlyIncrease =
	// (Double.parseDouble(futureValueAnnuityRequest.getYearlyIncrease())) / 100;
	// double totalPayment = invAmount
	// * (Math.pow((1 + annualGrowth), duration) - Math.pow((1 + yearlyIncrease),
	// duration))
	// / (annualGrowth - yearlyIncrease);
	// TotalValueResponse totResponse = new TotalValueResponse();
	// totResponse.setTotalPayment(roundingNumber(totalPayment));
	// ResponseMessage responseMessage = new ResponseMessage();
	// responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
	// responseMessage.setResponseDescription(AppMessages.VALUE_CALCULATED_SUCCESSFULLY);
	// ResponseData responseData = new ResponseData();
	// responseData.setData(totResponse);
	// Response response = new Response();
	// response.setResponseMessage(responseMessage);
	// response.setResponseData(responseData);
	// return ResponseEntity.ok().body(response);
	//
	// } else if (errors.isEmpty() == false) {
	// ResponseMessage responseMessage = new ResponseMessage();
	// responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
	// responseMessage.setResponseDescription(AppMessages.ERROR);
	// ResponseData responseData = new ResponseData();
	// responseData.setData(errors);
	// Response response = new Response();
	// response.setResponseMessage(responseMessage);
	// response.setResponseData(responseData);
	// return
	// ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	// }
	//
	// return new ResponseEntity<String>(HttpStatus.OK);
	//
	// }
	//
	// // Future Value Annuity Due
	// @RequestMapping(value = "/calculateFutureValueAnnuityDue", method =
	// RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes =
	// MediaType.APPLICATION_JSON)
	// public ResponseEntity<?> calculateFutureValueAnnuityDue(
	// @RequestBody FutureValueAnnuityDueRequest futureValueAnnuityDueRequest) {
	// HashMap<String, HashMap<String, String>> errors = new HashMap<String,
	// HashMap<String, String>>();
	// errors =
	// futureValueAnnuityDueRequestValidator.validate(futureValueAnnuityDueRequest);
	// if (errors.isEmpty() == true) {
	// double invAmount =
	// Double.parseDouble(futureValueAnnuityDueRequest.getInvAmount());
	// double annualGrowth =
	// (Double.parseDouble(futureValueAnnuityDueRequest.getAnnualGrowth())) / 100;
	// int duration = Integer.parseInt(futureValueAnnuityDueRequest.getDuration());
	// double yearlyIncrease =
	// (Double.parseDouble(futureValueAnnuityDueRequest.getYearlyIncrease())) / 100;
	// double totalPayment = invAmount
	// * (Math.pow((1 + annualGrowth), duration) - Math.pow((1 + yearlyIncrease),
	// duration))
	// / (annualGrowth - yearlyIncrease);
	// TotalValueResponse totResponse = new TotalValueResponse();
	// totResponse.setTotalPayment(roundingNumber(totalPayment));
	// ResponseMessage responseMessage = new ResponseMessage();
	// responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
	// responseMessage.setResponseDescription(AppMessages.VALUE_CALCULATED_SUCCESSFULLY);
	// ResponseData responseData = new ResponseData();
	// responseData.setData(totResponse);
	// Response response = new Response();
	// response.setResponseMessage(responseMessage);
	// response.setResponseData(responseData);
	// return ResponseEntity.ok().body(response);
	// } else if (errors.isEmpty() == false) {
	// ResponseMessage responseMessage = new ResponseMessage();
	// responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
	// responseMessage.setResponseDescription(AppMessages.ERROR);
	// ResponseData responseData = new ResponseData();
	// responseData.setData(errors);
	// Response response = new Response();
	// response.setResponseMessage(responseMessage);
	// response.setResponseData(responseData);
	// return
	// ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	// }
	//
	// return new ResponseEntity<String>(HttpStatus.OK);
	//
	// }

	// Target Value
	@RequestMapping(value = "/IP-TargetValue", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> calculateTargetValue(@RequestBody TargetValueRequest targetValueRequest) {
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		// Vallidating target value request
		errors = targetValueRequestValidator.validate(targetValueRequest);
		if (errors.isEmpty() == true) {
			TotalValueResponse totResponse = new TotalValueResponse();
			// If invType is LUMSUM
			if (targetValueRequest.getInvType().equals(CalcConstants.LUMSUM)) {
				double futureValue = Double.parseDouble(targetValueRequest.getFutureValue());
				double rateOfIntrest = Double.parseDouble(targetValueRequest.getRateOfInterest()) / 100;
				int duration = Integer.parseInt(targetValueRequest.getDuration());
				double totalPayment = futureValue / (Math.pow((1 + rateOfIntrest), duration));
				totResponse.setTotalPayment(roundingNumber(totalPayment));
			} else if (targetValueRequest.getInvType().equals(CalcConstants.SIP)) {
				// If invType is SIP
				double periodicAmt = Double.parseDouble(targetValueRequest.getFutureValue());
				double growthRate = Double.parseDouble(targetValueRequest.getRateOfInterest()) / 100;
				double duration = Double.parseDouble(targetValueRequest.getDuration());
				double monthlyGrowthRate = growthRate / 12;
				double durationInMonth = duration * 12;
				double annuity = periodicAmt * ((1 - (Math.pow((1 + monthlyGrowthRate), (-durationInMonth)))))
						/ monthlyGrowthRate;
				totResponse.setTotalPayment(roundingNumber(annuity));
			}
			// Response with total payment result
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
			responseMessage.setResponseDescription(appMessages.getValue_calculated_successfully());
			ResponseData responseData = new ResponseData();
			responseData.setData(totResponse);
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			response.setResponseData(responseData);
			return ResponseEntity.ok().body(response);
		} else if (errors.isEmpty() == false) {
			// return validation errors
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
			responseMessage.setResponseDescription(appMessages.getError());
			ResponseData responseData = new ResponseData();
			responseData.setData(errors);
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			response.setResponseData(responseData);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	// // Present value Annuity
	// @RequestMapping(value = "/IP-TargetValue-SIP", method = RequestMethod.POST,
	// produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	// public ResponseEntity<?> calculatePresentValueAnnuity(
	// @RequestBody PresentValueAnnuityRequest presentValueAnnuityRequest) {
	// HashMap<String, HashMap<String, String>> errors = new HashMap<String,
	// HashMap<String, String>>();
	// errors =
	// presentValueAnnuityRequestValidator.validate(presentValueAnnuityRequest);
	// if (errors.isEmpty() == true) {
	// double periodicAmt =
	// Double.parseDouble(presentValueAnnuityRequest.getPeriodicAmount());
	// double growthRate =
	// Double.parseDouble(presentValueAnnuityRequest.getAnnualGrowthRate()) / 100;
	// double duration =
	// Double.parseDouble(presentValueAnnuityRequest.getDuration());
	// double monthlyGrowthRate = growthRate / 12;
	// double durationInMonth = duration * 12;
	// double annuity = periodicAmt * ((1 - (Math.pow((1 + monthlyGrowthRate),
	// (-durationInMonth)))))
	// / monthlyGrowthRate;
	// TotalValueResponse totResponse = new TotalValueResponse();
	// totResponse.setTotalPayment(roundingNumber(annuity));
	// ResponseMessage responseMessage = new ResponseMessage();
	// responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
	// responseMessage.setResponseDescription(AppMessages.VALUE_CALCULATED_SUCCESSFULLY);
	// ResponseData responseData = new ResponseData();
	// responseData.setData(totResponse);
	// Response response = new Response();
	// response.setResponseMessage(responseMessage);
	// response.setResponseData(responseData);
	// return ResponseEntity.ok().body(response);
	// } else if (errors.isEmpty() == false) {
	// ResponseMessage responseMessage = new ResponseMessage();
	// responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
	// responseMessage.setResponseDescription(AppMessages.ERROR);
	// ResponseData responseData = new ResponseData();
	// responseData.setData(errors);
	// Response response = new Response();
	// response.setResponseMessage(responseMessage);
	// response.setResponseData(responseData);
	// return
	// ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	// }
	//
	// return new ResponseEntity<String>(HttpStatus.OK);
	//
	// }
	//
	// // Present Value Annuity Due
	// @RequestMapping(value = "/calculatePresentValueAnnuityDue", method =
	// RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes =
	// MediaType.APPLICATION_JSON)
	// public ResponseEntity<?> calculatePresentValueDueAnnuity(
	// @RequestBody PresentValueAnnuityDueRequest presentValueAnnuityDueRequest) {
	// HashMap<String, HashMap<String, String>> errors = new HashMap<String,
	// HashMap<String, String>>();
	// errors =
	// presentValueAnnuityDueReqValidator.validate(presentValueAnnuityDueRequest);
	// if (errors.isEmpty() == true) {
	// double periodicAmt =
	// Double.parseDouble(presentValueAnnuityDueRequest.getPeriodicAmount());
	// double growthRate =
	// Double.parseDouble(presentValueAnnuityDueRequest.getAnnualGrowthRate()) /
	// 100;
	// double duration =
	// Double.parseDouble(presentValueAnnuityDueRequest.getDuration());
	// double monthlyGrowthRate = growthRate / 12;
	// double durationInMonth = duration * 12;
	// double annuityDue = periodicAmt
	// * ((1 - Math.pow((1 + monthlyGrowthRate), (-durationInMonth))) /
	// monthlyGrowthRate)
	// * (1 + monthlyGrowthRate);
	// TotalValueResponse totResponse = new TotalValueResponse();
	// totResponse.setTotalPayment(roundingNumber(annuityDue));
	// ResponseMessage responseMessage = new ResponseMessage();
	// responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
	// responseMessage.setResponseDescription(AppMessages.VALUE_CALCULATED_SUCCESSFULLY);
	// ResponseData responseData = new ResponseData();
	// responseData.setData(totResponse);
	// Response response = new Response();
	// response.setResponseMessage(responseMessage);
	// response.setResponseData(responseData);
	// return ResponseEntity.ok().body(response);
	// } else if (errors.isEmpty() == false) {
	// ResponseMessage responseMessage = new ResponseMessage();
	// responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
	// responseMessage.setResponseDescription(AppMessages.ERROR);
	// ResponseData responseData = new ResponseData();
	// responseData.setData(errors);
	// Response response = new Response();
	// response.setResponseMessage(responseMessage);
	// response.setResponseData(responseData);
	// return
	// ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	// }
	//
	// return new ResponseEntity<String>(HttpStatus.OK);
	//
	// }

	// RATE FINDER
	@RequestMapping(value = "/IP-RateFinder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> rateFinder(@RequestBody RateFinderRequest rateFinderRequest) {
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		// Validating rateFinder request
		errors = rateFinderRequestValidator.validate(rateFinderRequest);
		if (errors.isEmpty() == true) {
			RateFinderResponse rateResponse = new RateFinderResponse();
			// If invType is LUMSUM
			if (rateFinderRequest.getInvType().equals(CalcConstants.LUMSUM)) {
				double presentValue = Double.parseDouble(rateFinderRequest.getPresentValue());
				double futureValue = Double.parseDouble(rateFinderRequest.getFutureValue());
				double duration = Double.parseDouble(rateFinderRequest.getDuration());
				double rate = (Math.pow((futureValue / presentValue), (1 / duration)) - 1) * 100;
				rateResponse.setRateOfInterest(roundingNumber(rate));
			} else if (rateFinderRequest.getInvType().equals(CalcConstants.SIP)) {
				// If invType is SIP
				double presentValue = Double.parseDouble(rateFinderRequest.getPresentValue());
				double futureValue = Double.parseDouble(rateFinderRequest.getFutureValue());
				double duration = Double.parseDouble(rateFinderRequest.getDuration());
				double rate = (Math.pow((futureValue / presentValue), (1 / duration)) - 1) * 100;
				rateResponse.setRateOfInterest(roundingNumber(rate));
			}
			// Return response with rateOfInterest
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
			responseMessage.setResponseDescription(appMessages.getRate_calculated_successfully());
			ResponseData responseData = new ResponseData();
			responseData.setData(rateResponse);
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			response.setResponseData(responseData);
			return ResponseEntity.ok().body(response);
		} else if (errors.isEmpty() == false) {
			// return validation errors
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
			responseMessage.setResponseDescription(appMessages.getError());
			ResponseData responseData = new ResponseData();
			responseData.setData(errors);
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			response.setResponseData(responseData);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	// // Rate Finder Annuity
	// @RequestMapping(value = "/IP-RateFinder-SIP", method = RequestMethod.POST,
	// produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	// public ResponseEntity<?> rateFinderAnnuity(@RequestBody
	// RateFinderAnnuityRequest rateFinderAnnuityRequest) {
	// HashMap<String, HashMap<String, String>> errors = new HashMap<String,
	// HashMap<String, String>>();
	//
	// errors =
	// rateFinderAnnuityRequestValidator.validate(rateFinderAnnuityRequest);
	// if (errors.isEmpty() == true) {
	// double presentValue =
	// Double.parseDouble(rateFinderAnnuityRequest.getPresentValue());
	// double futureValue =
	// Double.parseDouble(rateFinderAnnuityRequest.getFutureValue());
	// double duration = Double.parseDouble(rateFinderAnnuityRequest.getDuration());
	// double rate = (Math.pow((futureValue / presentValue), (1 / duration)) - 1) *
	// 100;
	// RateFinderResponse rateResponse = new RateFinderResponse();
	// rateResponse.setRateOfInterest(roundingNumber(rate));
	// ResponseMessage responseMessage = new ResponseMessage();
	// responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
	// responseMessage.setResponseDescription(AppMessages.RATE_FOUND_SUCCESSFULLY);
	// ResponseData responseData = new ResponseData();
	// responseData.setData(rateResponse);
	// Response response = new Response();
	// response.setResponseMessage(responseMessage);
	// response.setResponseData(responseData);
	// return ResponseEntity.ok().body(response);
	// } else if (errors.isEmpty() == false) {
	// ResponseMessage responseMessage = new ResponseMessage();
	// responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
	// responseMessage.setResponseDescription(AppMessages.ERROR);
	// ResponseData responseData = new ResponseData();
	// responseData.setData(errors);
	// Response response = new Response();
	// response.setResponseMessage(responseMessage);
	// response.setResponseData(responseData);
	// return
	// ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	// }
	// return new ResponseEntity<String>(HttpStatus.OK);
	// }

	// Tenure Finder
	@RequestMapping(value = "/IP-TenureFinder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> tenureFinder(@RequestBody TenureFinderRequest tenureFinderRequest) {
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		// Validating tenureFinder request
		errors = tenureFinderRequestValidator.validate(tenureFinderRequest);
		if (errors.isEmpty() == true) {
			TenureFinderResponse tenureResponse = new TenureFinderResponse();
			// If invType is LUMSUM
			if (tenureFinderRequest.getInvType().equals(CalcConstants.LUMSUM)) {
				double presentValue = Double.parseDouble(tenureFinderRequest.getPresentValue());
				double futureValue = Double.parseDouble(tenureFinderRequest.getFutureValue());
				double rate = Double.parseDouble(tenureFinderRequest.getRateOfInterest()) / 100;
				double tenure = Math.log(futureValue / presentValue) / Math.log(1 + rate);
				tenureResponse.setTenure(roundingNumber(tenure));
				// If invType is SIP
			} else if (tenureFinderRequest.getInvType().equals(CalcConstants.SIP)) {
				double presentValue = Double.parseDouble(tenureFinderRequest.getPresentValue());
				double futureValue = Double.parseDouble(tenureFinderRequest.getFutureValue());
				double rate = Double.parseDouble(tenureFinderRequest.getRateOfInterest()) / 100;
				double tenure = Math.log(futureValue / presentValue) / Math.log(1 + rate);
				tenureResponse.setTenure(roundingNumber(tenure));
			}
			// Return response with tenure
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
			responseMessage.setResponseDescription(appMessages.getTenure_calculated_successfully());
			ResponseData responseData = new ResponseData();
			responseData.setData(tenureResponse);
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			response.setResponseData(responseData);
			return ResponseEntity.ok().body(response);
		} else if (errors.isEmpty() == false) {
			// return validation errors
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
			responseMessage.setResponseDescription(appMessages.getError());
			ResponseData responseData = new ResponseData();
			responseData.setData(errors);
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			response.setResponseData(responseData);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	// // Tenure Finder Annuity
	// @RequestMapping(value = "/IP-TenureFinder-SIP", method = RequestMethod.POST,
	// produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	// public ResponseEntity<?> tenureFinderAnnuity(@RequestBody
	// TenureFinderAnnuityRequest tenureFinderAnnuityRequest) {
	// HashMap<String, HashMap<String, String>> errors = new HashMap<String,
	// HashMap<String, String>>();
	//
	// errors =
	// tenureFinderAnnuityRequestValidator.validate(tenureFinderAnnuityRequest);
	// if (errors.isEmpty() == true) {
	// double presentValue =
	// Double.parseDouble(tenureFinderAnnuityRequest.getPresentValue());
	// double futureValue =
	// Double.parseDouble(tenureFinderAnnuityRequest.getFutureValue());
	// double rate =
	// Double.parseDouble(tenureFinderAnnuityRequest.getRateOfInterest()) / 100;
	// double tenure = Math.log(futureValue / presentValue) / Math.log(1 + rate);
	// TenureFinderResponse tenureResponse = new TenureFinderResponse();
	// tenureResponse.setTenure(roundingNumber(tenure));
	// ResponseMessage responseMessage = new ResponseMessage();
	// responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
	// responseMessage.setResponseDescription(AppMessages.TENURE_CALCULATED_SUCCESSFULLY);
	// ResponseData responseData = new ResponseData();
	// responseData.setData(tenureResponse);
	// Response response = new Response();
	// response.setResponseMessage(responseMessage);
	// response.setResponseData(responseData);
	// return ResponseEntity.ok().body(response);
	// } else if (errors.isEmpty() == false) {
	// ResponseMessage responseMessage = new ResponseMessage();
	// responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
	// responseMessage.setResponseDescription(AppMessages.ERROR);
	// ResponseData responseData = new ResponseData();
	// responseData.setData(errors);
	// Response response = new Response();
	// response.setResponseMessage(responseMessage);
	// response.setResponseData(responseData);
	// return
	// ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	// }
	// return new ResponseEntity<String>(HttpStatus.OK);
	// }

	// LOAN PLANNING
	// Emi Calculator
	@RequestMapping(value = "/calculateEmi", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> emiCalculator(@RequestBody EmiCalculatorRequest emiCalculatorRequest) {
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		long partyId = emiCalculatorRequest.getPartyId();
		if (calcService.fetchParty(partyId) == null) {
			// If party is not available return no record found
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.NO_RECORD_FOUND);
			responseMessage.setResponseDescription(appMessages.getNo_record_found());
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			return ResponseEntity.ok().body(response);
		} else {
			// Validating emiCalculator request
			errors = emiCalculatorRequestValidator.validate(emiCalculatorRequest);
			if (errors.isEmpty() == true) {

				double loanAmt = Double.parseDouble(emiCalculatorRequest.getLoanAmount());
				double tenure = Integer.parseInt(emiCalculatorRequest.getTenure());
				double interestRate = Double.parseDouble(emiCalculatorRequest.getInterestRate());
				double monthlyInterestRate = interestRate / (12 * 100);
				double noOfInstallments = tenure * 12;
				double emi = (loanAmt * monthlyInterestRate * Math.pow((1 + monthlyInterestRate), noOfInstallments))
						/ (Math.pow((1 + monthlyInterestRate), noOfInstallments) - 1);
				double interestPayable = 0;
				double tempAmt = loanAmt;
				double totalPrincipal = 0;
				double closing = 0;
				double loanPaid = 0;
				// Amortisation
				List<AmortisationResponse> amortisationResponse = new ArrayList<AmortisationResponse>();

				for (int i = 0; i < noOfInstallments; i++) {
					// OPENING//
					int months = i + 1;
					double opening = tempAmt;
					// INTEREST//
					double interest = opening * monthlyInterestRate;
					interestPayable = interestPayable + interest;
					// PRINCIPAL//
					if (i >= noOfInstallments) {
						totalPrincipal = 0;
						closing = 0;
						loanPaid = 0;
					} else {
						// PRINCIPAL//
						totalPrincipal = emi - interest;
						// CLOSING//
						closing = opening - totalPrincipal;
						// LOANPAID//
						loanPaid = (interest / (interest + totalPrincipal)) * 100;
						tempAmt = closing;
					}
					AmortisationResponse amortisation = new AmortisationResponse();
					amortisation.setMonths(months);
					amortisation.setOpening(roundingNumber(opening));
					amortisation.setInterest(roundingNumber(interest));
					amortisation.setTotalPrincipal(roundingNumber(totalPrincipal));
					amortisation.setClosing(roundingNumber(closing));
					amortisation.setLoanPaid(roundingNumber(loanPaid));
					amortisationResponse.add(amortisation);
				}
				double total = interestPayable + loanAmt;
				// system date
				Date date = new Date();
				DateFormat formatter = new SimpleDateFormat("MM/YYYY");
				String currentDate = formatter.format(date);
				// Add into table
				EmiCalculator emiCalculator = getValueEmiCalculatorInfo(emiCalculatorRequest);
				emiCalculator.setPartyId(emiCalculatorRequest.getPartyId());
				emiCalculator.setDate(currentDate);
				if (calcService.fetchEmiCalculatorByPartyId(partyId) == null) {
					// If emiCalculator is not present with this partyId, then add as new record
					calcService.addEmiCalculator(emiCalculator);
				} else {
					// If emiCalculator is present with this partyId, then update it
					calcService.updateEmiCalculator(emiCalculator);
				}
				// Response
				// Response with Emi calculator result and amortisation
				EmiCalculatorResponse emiResponse = new EmiCalculatorResponse();
				emiResponse.setLoanAmount(roundingNumber(loanAmt));
				emiResponse.setTenure(tenure);
				emiResponse.setEmi(roundingNumber(emi));
				emiResponse.setInterestPayable(roundingNumber(interestPayable));
				emiResponse.setTotal(roundingNumber(total));
				emiResponse.setAmortisationResponse(amortisationResponse);

				ResponseMessage responseMessage = new ResponseMessage();
				responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
				responseMessage.setResponseDescription(appMessages.getEmi_calculated_successfully());
				ResponseData responseData = new ResponseData();
				responseData.setData(emiResponse);
				Response response = new Response();
				response.setResponseMessage(responseMessage);
				response.setResponseData(responseData);
				return ResponseEntity.ok().body(response);

			} else if (errors.isEmpty() == false) {
				// return validation errors
				ResponseMessage responseMessage = new ResponseMessage();
				responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
				responseMessage.setResponseDescription(appMessages.getError());
				ResponseData responseData = new ResponseData();
				responseData.setData(errors);
				Response response = new Response();
				response.setResponseMessage(responseMessage);
				response.setResponseData(responseData);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}

		}
		return new ResponseEntity<String>(HttpStatus.OK);

	}

	private EmiCalculator getValueEmiCalculatorInfo(EmiCalculatorRequest emiCalculatorRequest) {
		EmiCalculator emiCalculator = new EmiCalculator();
		if (emiCalculatorRequest != null && emiCalculatorRequest.getLoanAmount() != null) {
			emiCalculator.setLoanAmount(roundingNumber(Double.parseDouble(emiCalculatorRequest.getLoanAmount())));
		}
		if (emiCalculatorRequest != null && emiCalculatorRequest.getInterestRate() != null) {
			emiCalculator.setInterestRate(roundingNumber(Double.parseDouble(emiCalculatorRequest.getInterestRate())));
		}
		if (emiCalculatorRequest != null && emiCalculatorRequest.getTenure() != null) {
			emiCalculator.setTenure(Integer.parseInt(emiCalculatorRequest.getTenure()));
		}
		return emiCalculator;
	}

	// Emi Capacity
	@RequestMapping(value = "/calculateEmiCapacity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> emiCapacity(@RequestBody EmiCapacityRequest emiCapacityRequest) {
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		long partyId = emiCapacityRequest.getPartyId();
		if (calcService.fetchParty(partyId) == null) {
			// If party is not available return no record found
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.NO_RECORD_FOUND);
			responseMessage.setResponseDescription(appMessages.getNo_record_found());
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			return ResponseEntity.ok().body(response);
		} else {
			// Validating emiCapacityRequest
			errors = emiCapacityRequestValidator.validate(emiCapacityRequest);
			if (errors.isEmpty() == true) {

				double currentAge = Integer.parseInt(emiCapacityRequest.getCurrentAge());
				double retirementAge = Integer.parseInt(emiCapacityRequest.getRetirementAge());
				double termOfLoan = retirementAge - currentAge;
				String stability = emiCapacityRequest.getStability();
				String backUp = emiCapacityRequest.getBackUp();
				double netFamilyIncome = Double.parseDouble(emiCapacityRequest.getNetFamilyIncome());
				double existingEmi = Double.parseDouble(emiCapacityRequest.getExistingEmi());
				double houseHoldExp = Double.parseDouble(emiCapacityRequest.getHouseHoldExpense());
				double surplusMoney = netFamilyIncome - existingEmi - houseHoldExp;
				double surplus = 0;
				if (stability.equals(CalcConstants.HIGH) && backUp.equals(CalcConstants.YES)) {
					surplus = surplusMoney * 100 / 100;
				} else if (stability.equals(CalcConstants.MEDIUM) && backUp.equals(CalcConstants.YES)) {
					surplus = surplusMoney * 90 / 100;

				} else if (stability.equals(CalcConstants.HIGH) && backUp.equals(CalcConstants.NO)) {
					surplus = surplusMoney * 90 / 100;
				} else if (stability.equals(CalcConstants.MEDIUM) && backUp.equals(CalcConstants.NO)) {
					surplus = surplusMoney * 80 / 100;
				}

				double additionalIncome = Double.parseDouble(emiCapacityRequest.getAdditionalIncome());
				double emiCapacity1 = surplus + additionalIncome;

				// Advisable Loan Amount

				double emiPayable = emiCapacity1;
				double rateOfInterestPerAnnum = Double.parseDouble(emiCapacityRequest.getInterestRate());
				double monthlyInterestRate = rateOfInterestPerAnnum / (12 * 100);
				double termInYear = termOfLoan;
				double nfInstallments = termInYear * 12;
				double advisableLoanAmount = emiPayable / ((Math.pow((1 + monthlyInterestRate), nfInstallments)
						/ ((Math.pow((1 + monthlyInterestRate), nfInstallments) - 1)) * monthlyInterestRate));

				// Add into Table
				EmiCapacity emiCapacity = getValueEmiCapacityInfo(emiCapacityRequest);
				emiCapacity.setPartyId(emiCapacityRequest.getPartyId());
				if (calcService.fetchEmiCapacityByPartyId(partyId) == null) {
					// If emicapacity is not present, then add as new record
					calcService.addEmiCapacity(emiCapacity);
				} else {
					// If emicapacity is already present, then update it
					calcService.updateEmiCalculator(emiCapacity);
				}

				// Response
				EmiCapacityResponse emiResponse = new EmiCapacityResponse();
				emiResponse.setTermOfLoan(roundingNumber(termOfLoan));
				emiResponse.setSurplusMoney(roundingNumber(surplusMoney));
				emiResponse.setSurplus(roundingNumber(surplus));
				emiResponse.setEmiCapacity(roundingNumber(emiCapacity1));
				emiResponse.setEmiPayable(roundingNumber(emiPayable));
				emiResponse.setAdvisableLoanAmount(roundingNumber(advisableLoanAmount));

				ResponseMessage responseMessage = new ResponseMessage();
				responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
				responseMessage.setResponseDescription(appMessages.getEmi_calculated_successfully());
				ResponseData responseData = new ResponseData();
				responseData.setData(emiResponse);
				Response response = new Response();
				response.setResponseMessage(responseMessage);
				response.setResponseData(responseData);
				return ResponseEntity.ok().body(response);
			} else if (errors.isEmpty() == false) {
				// return valildation errors
				ResponseMessage responseMessage = new ResponseMessage();
				responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
				responseMessage.setResponseDescription(appMessages.getError());
				ResponseData responseData = new ResponseData();
				responseData.setData(errors);
				Response response = new Response();
				response.setResponseMessage(responseMessage);
				response.setResponseData(responseData);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}

		}
		return new ResponseEntity<String>(HttpStatus.OK);

	}

	private EmiCapacity getValueEmiCapacityInfo(EmiCapacityRequest emiCapacityRequest) {
		EmiCapacity emiCapacity = new EmiCapacity();
		if (emiCapacityRequest != null && emiCapacityRequest.getCurrentAge() != null) {
			emiCapacity.setCurrentAge(Integer.parseInt(emiCapacityRequest.getCurrentAge()));
		}
		if (emiCapacityRequest != null && emiCapacityRequest.getRetirementAge() != null) {
			emiCapacity.setRetirementAge(Integer.parseInt(emiCapacityRequest.getRetirementAge()));
		}
		if (emiCapacityRequest != null && emiCapacityRequest.getStability() != null) {
			emiCapacity.setStability(emiCapacityRequest.getStability());
		}
		if (emiCapacityRequest != null && emiCapacityRequest.getBackUp() != null) {
			emiCapacity.setBackUp(emiCapacityRequest.getBackUp());
		}
		if (emiCapacityRequest != null && emiCapacityRequest.getNetFamilyIncome() != null) {
			emiCapacity.setNetFamilyIncome(roundingNumber(Double.parseDouble(emiCapacityRequest.getNetFamilyIncome())));
		}
		if (emiCapacityRequest != null && emiCapacityRequest.getExistingEmi() != null) {
			emiCapacity.setExistingEmi(roundingNumber(Double.parseDouble(emiCapacityRequest.getExistingEmi())));
		}
		if (emiCapacityRequest != null && emiCapacityRequest.getHouseHoldExpense() != null) {
			emiCapacity
					.setHouseHoldExpense(roundingNumber(Double.parseDouble(emiCapacityRequest.getHouseHoldExpense())));
		}
		if (emiCapacityRequest != null && emiCapacityRequest.getAdditionalIncome() != null) {
			emiCapacity
					.setAdditionalIncome(roundingNumber(Double.parseDouble(emiCapacityRequest.getAdditionalIncome())));
		}
		if (emiCapacityRequest != null && emiCapacityRequest.getInterestRate() != null) {
			emiCapacity.setInterestRate(roundingNumber(Double.parseDouble(emiCapacityRequest.getInterestRate())));
		}

		return emiCapacity;
	}

	// Partial payment
	@RequestMapping(value = "/calculatePartialPayment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> calculatePartialPayment(@RequestBody PartialPaymentRequest partialPaymentRequest) {
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		long partyId = partialPaymentRequest.getPartyId();
		if (calcService.fetchParty(partyId) == null) {
			// If party is not available return no record found
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.NO_RECORD_FOUND);
			responseMessage.setResponseDescription(appMessages.getNo_record_found());
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			return ResponseEntity.ok().body(response);
		} else {
			// validating partialPaymentRequest
			errors = partialPaymentRequestValidator.validate(partialPaymentRequest);
			if (errors.isEmpty() == true) {
				double loanAmt = Double.parseDouble(partialPaymentRequest.getLoanAmount());
				double annualRate = Double.parseDouble(partialPaymentRequest.getInterestRate()) / 100;
				double monthlyRate = annualRate / 12;
				int tenure = Integer.parseInt(partialPaymentRequest.getTenure());
				double noOfInstall = tenure * 12;
				double noOfInstallYear = 12;
				double emi = (loanAmt * monthlyRate * (Math.pow((1 + monthlyRate), noOfInstall)))
						/ ((Math.pow((1 + monthlyRate), noOfInstall) - 1));
				String loanDate = partialPaymentRequest.getLoanDate();
				List<PartialPaymentReq> partialPaymentReq = partialPaymentRequest.getPartialPaymentReq();
				double loanAmtTemp = loanAmt;

				for (int i = 0; i < partialPaymentReq.size(); i++) {
					int noOfPaid = 0;
					int year1, month1, day1;
					String partPayDate = partialPaymentReq.get(i).getPartPayDate();

					// Difference between dates
					if (i == 0) {
						noOfPaid = calculateMonth(loanDate, partPayDate);
					} else {
						String prevDate = partialPaymentReq.get(i - 1).getPartPayDate();
						noOfPaid = calculateMonth(prevDate, partPayDate);
					}
					// Partial Payment Result
					double partPayment = Double.parseDouble(partialPaymentReq.get(i).getPartPayAmount());
					double outPrincipal = (loanAmtTemp - (emi * noOfInstallYear / annualRate))
							* (Math.pow((1 + (annualRate / noOfInstallYear)), noOfPaid))
							+ (emi * noOfInstallYear / annualRate);
					double remainPrincipal = outPrincipal - partPayment;
					double noOfYearNeed = -Math.log(1 - (remainPrincipal * annualRate / (emi * noOfInstallYear)))
							/ (noOfInstallYear * Math.log((1 + (annualRate / noOfInstallYear))));
					double noOfMonthNeed = noOfYearNeed * 12;
					loanAmtTemp = remainPrincipal;
					// Add Partial Payment Into Table
					PartialPayment partialPayment = getPartialPaymentValue(partialPaymentReq.get(i));
					partialPayment.setPartyId(partyId);
					partialPayment.setLoanAmount(roundingNumber(loanAmt));
					partialPayment.setInterestRate(Double.parseDouble(partialPaymentRequest.getInterestRate()));
					partialPayment.setTenure(tenure);
					partialPayment.setLoanDate(loanDate);
					calcService.addPartialPayment(partialPayment);
				}

				// Amortisation Schedule
				List<AmortisationResponse> amortisation = new ArrayList<AmortisationResponse>();

				double opening = 0;
				double interestPay = 0;
				double totalPrincipal = 0;
				double closing = 0;
				double loanPaid = 0;
				double temp = loanAmt;
				List<Integer> noOfPaids = new ArrayList<>();
				List<Double> partialPayment = new ArrayList<>();
				for (int i = 0; i < partialPaymentReq.size(); i++) {
					double partPayment = Double.parseDouble(partialPaymentReq.get(i).getPartPayAmount());
					String partPayDate = partialPaymentReq.get(i).getPartPayDate();
					int noOfPaid;
					noOfPaid = calculateMonth(loanDate, partPayDate);
					partialPayment.add(partPayment);
					noOfPaids.add(noOfPaid);
				}
				Iterator noOfPaidItr = noOfPaids.iterator();
				Iterator partPayItr = partialPayment.iterator();
				int noOfPaid = (int) noOfPaidItr.next();
				double partPayment = (double) partPayItr.next();
				for (int j = 0; j < noOfInstall; j++) {
					if (j == noOfPaid) {
						opening = temp - partPayment;
						if (noOfPaidItr.hasNext() && partPayItr.hasNext()) {
							noOfPaid = (int) noOfPaidItr.next();
							partPayment = (double) partPayItr.next();
						}
					} else {
						if (temp < 0) {
							opening = 0;
						} else {
							opening = temp;
						}
					}
					double interest = opening * monthlyRate;
					interestPay = interestPay + interest;

					if (j >= noOfInstall) {
						totalPrincipal = 0;
						closing = 0;
					} else {
						totalPrincipal = emi - interest;
						closing = opening - totalPrincipal;
						loanPaid = (interest / (interest + totalPrincipal)) * 100;
						temp = closing;
					}
					AmortisationResponse amortisationRes = new AmortisationResponse();
					amortisationRes.setMonths(j + 1);
					amortisationRes.setOpening(roundingNumber(opening));
					amortisationRes.setInterest(roundingNumber(interest));
					amortisationRes.setTotalPrincipal(roundingNumber(totalPrincipal));
					amortisationRes.setClosing(roundingNumber(closing));
					amortisationRes.setLoanPaid(roundingNumber(loanPaid));
					amortisation.add(amortisationRes);

				}
				double total = (interestPay + loanAmt);
				// Response with result and amortisation schedule
				PartialPaymentResponse partialPayResponse = new PartialPaymentResponse();
				partialPayResponse.setLoanAmount(roundingNumber(loanAmt));
				partialPayResponse.setTenure(tenure);
				partialPayResponse.setEmi(roundingNumber(emi));
				partialPayResponse.setInterestPayable(roundingNumber(interestPay));
				partialPayResponse.setTotal(roundingNumber(total));
				partialPayResponse.setAmortisation(amortisation);

				ResponseMessage responseMessage = new ResponseMessage();
				responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
				responseMessage.setResponseDescription(appMessages.getPartpayment_calculated_successfully());
				ResponseData responseData = new ResponseData();
				responseData.setData(partialPayResponse);
				Response response = new Response();
				response.setResponseMessage(responseMessage);
				response.setResponseData(responseData);
				return ResponseEntity.ok().body(response);
			} else if (errors.isEmpty() == false) {
				// return validation errors
				ResponseMessage responseMessage = new ResponseMessage();
				responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
				responseMessage.setResponseDescription(appMessages.getError());
				ResponseData responseData = new ResponseData();
				responseData.setData(errors);
				Response response = new Response();
				response.setResponseMessage(responseMessage);
				response.setResponseData(responseData);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	private PartialPayment getPartialPaymentValue(PartialPaymentReq partialPaymentReq) {
		PartialPayment partPayment = new PartialPayment();
		if (partialPaymentReq != null && partialPaymentReq.getPartPayDate() != null) {
			partPayment.setPartPayDate(partialPaymentReq.getPartPayDate());
		}
		if (partialPaymentReq != null && partialPaymentReq.getPartPayAmount() != null) {
			partPayment.setPartPayAmount(Double.parseDouble(partialPaymentReq.getPartPayAmount()));
		}
		return partPayment;
	}

	// Change in Emi
	@RequestMapping(value = "/calculateEmiChange", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> emiChange(@RequestBody EmiChangeRequest emiChangeRequest) {
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		long partyId = emiChangeRequest.getPartyId();
		if (calcService.fetchParty(partyId) == null) {
			// If party is not available return no record found
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.NO_RECORD_FOUND);
			responseMessage.setResponseDescription(appMessages.getNo_record_found());
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			return ResponseEntity.ok().body(response);
		} else {
			// validating emiChange Request
			errors = emiChangeRequestValidator.validate(emiChangeRequest);
			if (errors.isEmpty() == true) {
				if (emiChangeRequest.getLoanAmount() != null && emiChangeRequest.getInterestRate() != null
						&& emiChangeRequest.getTenure() != null && emiChangeRequest.getPartyId() != 0
						&& emiChangeRequest.getLoanDate() != null) {

					double loanAmount = Double.parseDouble(emiChangeRequest.getLoanAmount());
					double annualInterestRate = Double.parseDouble(emiChangeRequest.getInterestRate()) / 100;
					double monthlyInterestRate = annualInterestRate / 12;
					int tenure = Integer.parseInt(emiChangeRequest.getTenure());
					double noOfInstallments = tenure * 12;
					double noOfInstallmentsPerYear = 12;
					// system date
					Date date = new Date();
					DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
					String currentDate = formatter.format(date);

					String loanDate = emiChangeRequest.getLoanDate();
					int noOfPaymentsPaid = calculateMonth(loanDate, currentDate);

					// Find EMI
					double emi = (loanAmount * monthlyInterestRate
							* Math.pow(1 + monthlyInterestRate, noOfInstallments))
							/ (Math.pow(1 + monthlyInterestRate, noOfInstallments) - 1);
					// OutStanding Amount
					double afterNPayments = (loanAmount - (emi * noOfInstallmentsPerYear / annualInterestRate))
							* Math.pow((1 + (annualInterestRate / noOfInstallmentsPerYear)), noOfPaymentsPaid)
							+ (emi * noOfInstallmentsPerYear) / annualInterestRate;
					// Years for Pay Off
					double noOfYearsNeed = -Math
							.log(1 - (loanAmount * annualInterestRate / (emi * noOfInstallmentsPerYear)))
							/ (noOfInstallmentsPerYear
									* (Math.log(1 + (annualInterestRate / noOfInstallmentsPerYear))));

					double emiCalc = emi; // Temp Variables
					double temp = afterNPayments;

					List<Double> emis = new ArrayList<>();
					List<Integer> dates = new ArrayList<>();
					emis.add(emi);

					List<EmiChangeRes> emiChangeResList = new ArrayList<EmiChangeRes>();

					for (EmiChangeReq emiChangeReq : emiChangeRequest.getEmiChangeReq()) {

						double prevEmi = emiCalc;
						double remLoanAmount = temp;
						double increaseEmi = Double.parseDouble(emiChangeReq.getIncreasedEmi());
						double revisedEmi = increaseEmi + prevEmi;
						String emiChangedDate = emiChangeReq.getEmiChangedDate();
						int paymentsPaid = calculateMonth(loanDate, emiChangedDate);

						// After increase Years for Pay Off
						double yearsNeedToPayOff = -Math
								.log(1 - (remLoanAmount * annualInterestRate / (revisedEmi * noOfInstallmentsPerYear)))
								/ (noOfInstallmentsPerYear
										* (Math.log(1 + (annualInterestRate / noOfInstallmentsPerYear))));
						// FinalOutStanding Amount
						double afterNPayRemLoanAmt = (remLoanAmount
								- (revisedEmi * noOfInstallmentsPerYear / annualInterestRate))
								* Math.pow((1 + (annualInterestRate / noOfInstallmentsPerYear)), paymentsPaid)
								+ (revisedEmi * noOfInstallmentsPerYear) / annualInterestRate;

						emiCalc = revisedEmi;
						temp = afterNPayRemLoanAmt;

						emis.add(revisedEmi);
						dates.add(paymentsPaid); // add

						// add into table
						EmiChange emiChange = getValueEmiChange(emiChangeRequest);
						emiChange.setIncreasedEmi(increaseEmi);
						emiChange.setEmiChangedDate(emiChangedDate);
						calcService.addEmiChange(emiChange);

					}

					// amortisation
					List<AmortisationResponse> amortisation = new ArrayList<AmortisationResponse>();

					double opening = loanAmount;
					double interestPay = 0;
					double totalPrincipal = 0;
					double closing = 0;
					double loanPaid = 0;
					double tempAmt = loanAmount;
					double total = 0;
					Iterator nPaidItr = dates.iterator();
					Iterator emiItr = emis.iterator();
					int noOfPaid = (int) nPaidItr.next();
					double emi1 = (double) emiItr.next();
					for (int i = 0; i < noOfInstallments; i++) {
						if (i == noOfPaid) {
							opening = tempAmt;
							if (emiItr.hasNext()) {
								emi1 = (double) emiItr.next();
							}
							if (nPaidItr.hasNext()) {
								noOfPaid = (int) nPaidItr.next();
							}
						} else {
							if (tempAmt < 0) {
								opening = 0;
							} else {
								opening = tempAmt;
							}
						}
						double interest = opening * monthlyInterestRate;
						interestPay = interestPay + interest;

						if (i >= noOfInstallments) {
							totalPrincipal = 0;
							closing = 0;
						} else {
							totalPrincipal = emi1 - interest;
							closing = opening - totalPrincipal;
							loanPaid = (interest / (interest + totalPrincipal)) * 100;
							tempAmt = closing;
						}
						total = (interestPay + loanAmount);

						AmortisationResponse amort = new AmortisationResponse();
						amort.setMonths(i + 1);
						amort.setOpening(roundingNumber(opening));
						amort.setInterest(roundingNumber(interest));
						amort.setTotalPrincipal(roundingNumber(totalPrincipal));
						amort.setClosing(roundingNumber(closing));
						amort.setLoanPaid(roundingNumber(loanPaid));
						amortisation.add(amort);
					}

					EmiChangeResponse emiResponse = new EmiChangeResponse();
					emiResponse.setLoanAmount(loanAmount);
					emiResponse.setTenure(tenure);
					emiResponse.setEmi(roundingNumber(emi));
					emiResponse.setInterestPayable(roundingNumber(interestPay));
					emiResponse.setTotal(roundingNumber(total));
					emiResponse.setAmortisation(amortisation);

					ResponseMessage responseMessage = new ResponseMessage();
					responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
					responseMessage.setResponseDescription(appMessages.getEmi_calculated_successfully());
					ResponseData responseData = new ResponseData();
					responseData.setData(emiResponse);
					Response response = new Response();
					response.setResponseMessage(responseMessage);
					response.setResponseData(responseData);
					return ResponseEntity.ok().body(response);

				} else {
					ResponseMessage responseMessage = new ResponseMessage();
					responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
					responseMessage.setResponseDescription(appMessages.getFields_cannot_be_empty());
					Response response = new Response();
					response.setResponseMessage(responseMessage);
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
				}
			} else if (errors.isEmpty() == false) {
				// return validation errors

				ResponseMessage responseMessage = new ResponseMessage();
				responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
				responseMessage.setResponseDescription(appMessages.getError());
				ResponseData responseData = new ResponseData();
				responseData.setData(errors);
				Response response = new Response();
				response.setResponseMessage(responseMessage);
				response.setResponseData(responseData);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}
		}
		return new ResponseEntity<String>(HttpStatus.OK);

	}

	private EmiChange getValueEmiChange(EmiChangeRequest emiChangeRequest) {
		EmiChange emiChange = new EmiChange();
		if (emiChangeRequest != null && emiChangeRequest.getLoanAmount() != null) {
			emiChange.setLoanAmount(Double.parseDouble(emiChangeRequest.getLoanAmount()));
		}
		if (emiChangeRequest != null && emiChangeRequest.getPartyId() != 0) {
			emiChange.setPartyId(emiChangeRequest.getPartyId());
		}
		if (emiChangeRequest != null && emiChangeRequest.getInterestRate() != null) {
			emiChange.setInterestRate(Double.parseDouble(emiChangeRequest.getInterestRate()));
		}
		if (emiChangeRequest != null && emiChangeRequest.getTenure() != null) {
			emiChange.setTenure(Integer.parseInt(emiChangeRequest.getTenure()));
		}
		if (emiChangeRequest != null && emiChangeRequest.getLoanDate() != null) {
			emiChange.setLoanDate(emiChangeRequest.getLoanDate());
		}
		return emiChange;
	}

	// Change in Interest Rate
	@RequestMapping(value = "/calculateInterestChange", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> interestChange(@RequestBody InterestChangeRequest interestChangeRequest) {
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		long partyId = interestChangeRequest.getPartyId();
		if (calcService.fetchParty(partyId) == null) {
			// If party is not available return no record found
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.NO_RECORD_FOUND);
			responseMessage.setResponseDescription(appMessages.getNo_record_found());
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			return ResponseEntity.ok().body(response);
		} else {
			// validating interestChangeRequest
			errors = interestChangeRequestValidator.validate(interestChangeRequest);
			if (errors.isEmpty() == true) {

				double loanAmount = Double.parseDouble(interestChangeRequest.getLoanAmount());
				double annualInterestRate = Double.parseDouble(interestChangeRequest.getInterestRate()) / 100;
				double monthlyInterestRate = annualInterestRate / 12;

				int tenure = Integer.parseInt(interestChangeRequest.getTenure());
				double noOfInstallments = tenure * 12;

				double emi = (loanAmount * monthlyInterestRate * (Math.pow((1 + monthlyInterestRate), noOfInstallments))
						/ (Math.pow((1 + monthlyInterestRate), noOfInstallments) - 1));
				double noOfInstallmentsPerYear = 12;
				// system date
				Date date = new Date();
				DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				String currentDate = formatter.format(date);

				String loanDate = interestChangeRequest.getLoanDate();
				int noOfPaymentsPaid = calculateMonth(loanDate, currentDate);
				double afterNPayments = (loanAmount - (emi * noOfInstallmentsPerYear / annualInterestRate))
						* Math.pow((1 + (annualInterestRate / noOfInstallmentsPerYear)), noOfPaymentsPaid)
						+ (emi * noOfInstallmentsPerYear / annualInterestRate);
				double noOfYearsNeed = -Math
						.log(1 - (loanAmount * annualInterestRate / (emi * noOfInstallmentsPerYear)))
						/ (noOfInstallmentsPerYear * (Math.log(1 + (annualInterestRate / noOfInstallmentsPerYear))));
				double temp = afterNPayments;

				List<AmortisationResponse> amortisationResponse = new ArrayList<AmortisationResponse>();

				List<Double> rates = new ArrayList<>();
				rates.add(monthlyInterestRate);
				List<Integer> dates = new ArrayList<>();

				// Change Interest
				for (InterestChangeReq interestChangeReq : interestChangeRequest.getInterestChangeReq()) {

					double previousEmi = emi;
					double remLoanAmount = temp;
					double interestRate = Double.parseDouble(interestChangeReq.getChangedRate()) / 100;
					double mInterestRate = interestRate / 12;
					double revisedEmi = previousEmi;
					String interestChangedDate = interestChangeReq.getInterestChangedDate();
					int paymentsPaid = calculateMonth(loanDate, interestChangedDate);
					double yearsNeedToPayOff = -Math
							.log(1 - (remLoanAmount * interestRate / (revisedEmi * noOfInstallmentsPerYear)))
							/ (noOfInstallmentsPerYear * (Math.log(1 + (interestRate / noOfInstallmentsPerYear))));
					double afterNPayRemLoanAmt = (remLoanAmount - (revisedEmi * noOfInstallmentsPerYear / interestRate))
							* Math.pow((1 + (interestRate / noOfInstallmentsPerYear)), paymentsPaid)
							+ (revisedEmi * noOfInstallmentsPerYear / interestRate);

					temp = afterNPayRemLoanAmt;
					rates.add(mInterestRate);
					dates.add(paymentsPaid);

					// Add into Table
					InterestChange interestChange = getValueInterestChangeInfo(interestChangeRequest);
					interestChange.setPartyId(interestChangeRequest.getPartyId());
					interestChange.setChangedRate(Double.parseDouble(interestChangeReq.getChangedRate()));
					interestChange.setInterestChangedDate(interestChangeReq.getInterestChangedDate());
					calcService.addInterestChange(interestChange);

				}
				// Amortisation Schedule
				double opening = loanAmount;
				double interestPay = 0;
				double totalPrincipal = 0;
				double closing = 0;
				double loanPaid = 0;
				double tempAmt = loanAmount;
				double total = 0;
				Iterator nPaidItr = dates.iterator();
				Iterator rateItr = rates.iterator();
				int noOfPaid = (int) nPaidItr.next();
				double mRate = (double) rateItr.next();

				for (int i = 0; i < noOfInstallments; i++) {
					if (i == noOfPaid) {
						opening = tempAmt;
						if (rateItr.hasNext()) {
							mRate = (double) rateItr.next();
						}
						if (nPaidItr.hasNext()) {
							noOfPaid = (int) nPaidItr.next();
						}
					} else {
						if (tempAmt < 0) {
							opening = 0;
						} else {
							opening = tempAmt;
						}
					}
					double interest = opening * mRate;
					interestPay = interestPay + interest;

					if (i >= noOfInstallments) {
						totalPrincipal = 0;
						closing = 0;
					} else {
						totalPrincipal = emi - interest;
						closing = opening - totalPrincipal;
						loanPaid = (interest / (interest + totalPrincipal)) * 100;
						tempAmt = closing;
					}
					total = (interestPay + loanAmount);

					AmortisationResponse amortisation = new AmortisationResponse();
					amortisation.setMonths(i + 1);
					amortisation.setOpening(roundingNumber(opening));
					amortisation.setInterest(roundingNumber(interest));
					amortisation.setTotalPrincipal(roundingNumber(totalPrincipal));
					amortisation.setClosing(roundingNumber(closing));
					amortisation.setLoanPaid(roundingNumber(loanPaid));
					amortisationResponse.add(amortisation);
				}
				// response with result and amortisation schedule

				InterestChangeResponse intResponse = new InterestChangeResponse();
				intResponse.setLoanAmount(loanAmount);
				intResponse.setTenure(tenure);
				intResponse.setEmi(roundingNumber(emi));
				intResponse.setInterestPayable(roundingNumber(interestPay));
				intResponse.setTotal(roundingNumber(total));
				intResponse.setAmortisation(amortisationResponse);

				ResponseMessage responseMessage = new ResponseMessage();
				responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
				responseMessage.setResponseDescription(appMessages.getInt_change_calculated_successfully());
				ResponseData responseData = new ResponseData();
				responseData.setData(intResponse);
				Response response = new Response();
				response.setResponseMessage(responseMessage);
				response.setResponseData(responseData);
				return ResponseEntity.ok().body(response);
			} else if (errors.isEmpty() == false) {
				// return validation errors
				ResponseMessage responseMessage = new ResponseMessage();
				responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
				responseMessage.setResponseDescription(appMessages.getError());
				ResponseData responseData = new ResponseData();
				responseData.setData(errors);
				Response response = new Response();
				response.setResponseMessage(responseMessage);
				response.setResponseData(responseData);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}

		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	private InterestChange getValueInterestChangeInfo(InterestChangeRequest interestChangeRequest) {
		InterestChange interestChange = new InterestChange();
		if (interestChangeRequest != null && interestChangeRequest.getLoanAmount() != null) {
			interestChange.setLoanAmount(roundingNumber(Double.parseDouble(interestChangeRequest.getLoanAmount())));
		}
		if (interestChangeRequest != null && interestChangeRequest.getInterestRate() != null) {
			interestChange.setInterestRate(Double.parseDouble(interestChangeRequest.getInterestRate()));
		}
		if (interestChangeRequest != null && interestChangeRequest.getTenure() != null) {
			interestChange.setTenure(Integer.parseInt(interestChangeRequest.getTenure()));
		}
		if (interestChangeRequest != null && interestChangeRequest.getLoanDate() != null) {
			interestChange.setLoanDate(interestChangeRequest.getLoanDate());
		}
		return interestChange;
	}

	@RequestMapping(value = "/calculateEmiInterestChange", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> emiInterestChange(@RequestBody EmiInterestChangeRequest emiInterestChangeRequest) {
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		long partyId = emiInterestChangeRequest.getPartyId();
		if (calcService.fetchParty(partyId) == null) {
			// If party is not available return no record found
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.NO_RECORD_FOUND);
			responseMessage.setResponseDescription(appMessages.getNo_record_found());
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			return ResponseEntity.ok().body(response);
		} else {
			// validating emiInterestChange Request
			errors = emiInterestChangeRequestValidator.validate(emiInterestChangeRequest);
			if (errors.isEmpty() == true) {
				if (emiInterestChangeRequest.getLoanAmount() != null
						&& emiInterestChangeRequest.getInterestRate() != null
						&& emiInterestChangeRequest.getTenure() != null && emiInterestChangeRequest.getPartyId() != 0
						&& emiInterestChangeRequest.getLoanDate() != null) {

					double loanAmount = Double.parseDouble(emiInterestChangeRequest.getLoanAmount());
					double annualInterestRate = Double.parseDouble(emiInterestChangeRequest.getInterestRate()) / 100;
					double monthlyInterestRate = annualInterestRate / 12;
					double tenure = Double.parseDouble(emiInterestChangeRequest.getTenure());
					double noOfInstallments = tenure * 12;
					double noOfInstallmentsPerYear = 12;
					// system date
					Date date = new Date();
					DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
					String currentDate = formatter.format(date);
					String loanDate = emiInterestChangeRequest.getLoanDate();
					int noOfPaymentsPaid = calculateMonth(loanDate, currentDate);
					// Find EMI
					double emi = (loanAmount * monthlyInterestRate
							* Math.pow(1 + monthlyInterestRate, noOfInstallments))
							/ (Math.pow(1 + monthlyInterestRate, noOfInstallments) - 1);
					// OutStanding Amount
					double afterNPayments = (loanAmount - (emi * noOfInstallmentsPerYear / annualInterestRate))
							* Math.pow((1 + (annualInterestRate / noOfInstallmentsPerYear)), noOfPaymentsPaid)
							+ (emi * noOfInstallmentsPerYear) / annualInterestRate;
					// Years for Pay Off
					double noOfYearsNeed = -Math
							.log(1 - (loanAmount * annualInterestRate / (emi * noOfInstallmentsPerYear)))
							/ (noOfInstallmentsPerYear
									* (Math.log(1 + (annualInterestRate / noOfInstallmentsPerYear))));

					double emiCalc = emi; // Temp Variables
					double temp = afterNPayments;

					List<Double> emis = new ArrayList<>();
					List<Integer> dates = new ArrayList<>();
					emis.add(emi);
					List<Double> rates = new ArrayList<>();
					rates.add(monthlyInterestRate);

					for (EmiInterestChangeReq emiIntChangeReq : emiInterestChangeRequest.getEmiInterestChangeReq()) {
						double prevEmi = emiCalc;
						double remLoanAmount = temp;
						double changedRate = Double.parseDouble(emiIntChangeReq.getChangedRate()) / 100;
						double mRate = changedRate / 12.0;
						double increaseEmi = Double.parseDouble(emiIntChangeReq.getIncreasedEmi());
						double revisedEmi = increaseEmi + prevEmi;
						String changedDate = emiIntChangeReq.getChangedDate();
						int paymentsPaid = calculateMonth(loanDate, changedDate);

						double yearsNeedToPayOff = -Math
								.log(1 - (remLoanAmount * changedRate / (revisedEmi * noOfInstallmentsPerYear)))
								/ (noOfInstallmentsPerYear * (Math.log(1 + (changedRate / noOfInstallmentsPerYear))));
						double afterNPayRemLoanAmt = (remLoanAmount
								- (revisedEmi * noOfInstallmentsPerYear / changedRate))
								* Math.pow((1 + (changedRate / noOfInstallmentsPerYear)), paymentsPaid)
								+ (revisedEmi * noOfInstallmentsPerYear) / changedRate;
						emiCalc = revisedEmi;
						temp = afterNPayRemLoanAmt;

						emis.add(revisedEmi);
						dates.add((int) paymentsPaid);
						rates.add(mRate);

						// Add into Table
						EmiInterestChange emiInterestChange = getValueEmiInterestChange(emiInterestChangeRequest);
						emiInterestChange.setChangedRate(Double.parseDouble(emiIntChangeReq.getChangedRate()));
						emiInterestChange.setIncreasedEmi(Double.parseDouble(emiIntChangeReq.getIncreasedEmi()));
						emiInterestChange.setChangedDate(emiIntChangeReq.getChangedDate());
						calcService.addEmiInterestChange(emiInterestChange);

					}

					// amortisation
					List<AmortisationResponse> amortisation = new ArrayList<AmortisationResponse>();

					double opening = loanAmount;
					double interestPay = 0;
					double totalPrincipal = 0;
					double closing = 0;
					double loanPaid = 0;
					double tempAmt = loanAmount;
					double total = 0;
					Iterator nPaidItr = dates.iterator();
					Iterator rateItr = rates.iterator();
					Iterator emiItr = emis.iterator();
					int nPaid = (int) nPaidItr.next();
					double mRate = (double) rateItr.next();
					double emi1 = (double) emiItr.next();
					for (int i = 0; i < noOfInstallments; i++) {
						if (i == nPaid) {
							opening = tempAmt;
							if (rateItr.hasNext()) {
								mRate = (double) rateItr.next();
							}
							if (emiItr.hasNext()) {
								emi1 = (double) emiItr.next();
							}
							if (nPaidItr.hasNext()) {
								nPaid = (int) nPaidItr.next();
							}
						} else {
							if (tempAmt < 0) {
								opening = 0;
							} else {
								opening = tempAmt;
							}
						}
						double interest = opening * mRate;
						interestPay = interestPay + interest;

						if (i >= noOfInstallments) {
							totalPrincipal = 0;
							closing = 0;
						} else {
							totalPrincipal = emi1 - interest;
							closing = opening - totalPrincipal;
							loanPaid = (interest / (interest + totalPrincipal)) * 100;
							tempAmt = closing;
						}
						total = (interestPay + loanAmount);

						AmortisationResponse amort = new AmortisationResponse();
						amort.setMonths(i + 1);
						amort.setOpening(roundingNumber(opening));
						amort.setInterest(roundingNumber(interest));
						amort.setTotalPrincipal(roundingNumber(totalPrincipal));
						amort.setClosing(roundingNumber(closing));
						amort.setLoanPaid(roundingNumber(loanPaid));
						amortisation.add(amort);
					}

					EmiIntChangeResponse emiIntResponse = new EmiIntChangeResponse();
					emiIntResponse.setLoanAmount(loanAmount);
					emiIntResponse.setTenure(tenure);
					emiIntResponse.setEmi(roundingNumber(emi));
					emiIntResponse.setInterestPayable(roundingNumber(interestPay));
					emiIntResponse.setTotal(roundingNumber(total));
					emiIntResponse.setAmortisation(amortisation);

					// Response with result and amortisation schedule
					ResponseMessage responseMessage = new ResponseMessage();
					responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
					responseMessage.setResponseDescription(appMessages.getValue_calculated_successfully());
					ResponseData responseData = new ResponseData();
					responseData.setData(emiIntResponse);
					Response response = new Response();
					response.setResponseMessage(responseMessage);
					response.setResponseData(responseData);
					return ResponseEntity.ok().body(response);
				} else {
					ResponseMessage responseMessage = new ResponseMessage();
					responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
					responseMessage.setResponseDescription(appMessages.getFields_cannot_be_empty());
					Response response = new Response();
					response.setResponseMessage(responseMessage);
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
				}
			} else if (errors.isEmpty() == false) {
				// return validation errors
				ResponseMessage responseMessage = new ResponseMessage();
				responseMessage.setResponseCode(CalcConstants.ERROR_CODE);
				responseMessage.setResponseDescription(appMessages.getError());
				ResponseData responseData = new ResponseData();
				responseData.setData(errors);
				Response response = new Response();
				response.setResponseMessage(responseMessage);
				response.setResponseData(responseData);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}
		}
		return new ResponseEntity<String>(HttpStatus.OK);

	}

	private EmiInterestChange getValueEmiInterestChange(EmiInterestChangeRequest emiInterestChangeRequest) {
		EmiInterestChange emiInterestChange = new EmiInterestChange();

		if (emiInterestChangeRequest != null && emiInterestChangeRequest.getPartyId() != 0) {
			emiInterestChange.setPartyId(emiInterestChangeRequest.getPartyId());
		}
		if (emiInterestChangeRequest != null && emiInterestChangeRequest.getLoanAmount() != null) {
			emiInterestChange.setLoanAmount(Double.parseDouble(emiInterestChangeRequest.getLoanAmount()));
		}
		if (emiInterestChangeRequest != null && emiInterestChangeRequest.getTenure() != null) {
			emiInterestChange.setTenure(Integer.parseInt(emiInterestChangeRequest.getTenure()));
		}
		if (emiInterestChangeRequest != null && emiInterestChangeRequest.getInterestRate() != null) {
			emiInterestChange.setInterestRate(Double.parseDouble(emiInterestChangeRequest.getInterestRate()));
		}
		if (emiInterestChangeRequest != null && emiInterestChangeRequest.getLoanDate() != null) {
			emiInterestChange.setLoanDate(emiInterestChangeRequest.getLoanDate());
		}
		return emiInterestChange;

	}

	private int calculateMonth(String date1, String date2) {
		int nPaid = 0;
		String[] loanDate1 = date1.split("-");
		int year1 = Integer.parseInt(loanDate1[2]);
		int month1 = Integer.parseInt(loanDate1[1]);
		int day1 = Integer.parseInt(loanDate1[0]);

		// String partPayDate = partialPaymentReq.get(i).getDate();
		String[] out2 = date2.split("-");
		int year2 = Integer.parseInt(out2[2]);
		int month2 = Integer.parseInt(out2[1]);
		int day2 = Integer.parseInt(out2[0]);
		if (day2 >= day1) {
			nPaid = 0 + (year2 - year1) * 12 + month2 - month1;
		} else {
			nPaid = -1 + (year2 - year1) * 12 + month2 - month1;
		}
		return nPaid;
	}

	// fetch Financial Planning Table
	@RequestMapping(value = "/fetch-financialPlanning", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> fetchFinancialPlanningById(@RequestBody CalcIdRequest idRequest) {
		String id = idRequest.getId();
		Party party = calcService.fetchPartyIdByRoleBasedId(id);
		if (party == null) {
			// If party is not available return no record found
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.NO_RECORD_FOUND);
			responseMessage.setResponseDescription(appMessages.getNo_record_found());
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			return ResponseEntity.ok().body(response);
		} else {
			long partyId = party.getPartyId();
			// Fetch goal, cashflow, cashflowsummary, networth,
			// networthsummary,priority,insurance, riskprofile, risksummary
			// For this partyId
			FinancialPlanning financialPlan = new FinancialPlanning();
			financialPlan.setGoal(calcService.fetchGoalByPartyId(partyId));
			List<CashFlow> cashFlowList = new ArrayList<CashFlow>();
			cashFlowList = fetchAllCashFlowByPartyId(partyId);
			List<Networth> networthList = new ArrayList<Networth>();
			networthList = fetchAllNetworthByPartyId(partyId);
			List<Priority> priorityList = new ArrayList<Priority>();
			priorityList = fetchAllPriorityByPartyId(partyId);
			List<RiskProfile> riskProfileList = new ArrayList<RiskProfile>();
			riskProfileList = calcService.fetchRiskProfileByPartyId(partyId);
			financialPlan.setCashFlow(cashFlowList);
			financialPlan.setCashFlowSummary(calcService.fetchCashFlowSummaryByPartyId(partyId));
			financialPlan.setNetworth(networthList);
			financialPlan.setNetworthSummary(calcService.fetchNetworthSummaryByPartyId(partyId));
			financialPlan.setPriority(priorityList);
			financialPlan.setInsurance(calcService.fetchInsuranceItemByPartyId(partyId));
			financialPlan.setRiskProfile(riskProfileList);
			financialPlan.setRiskSummary(calcService.fetchRiskSummaryByPartyId(partyId));

			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
			responseMessage.setResponseDescription(appMessages.getSuccess());
			ResponseData responseData = new ResponseData();
			responseData.setData(financialPlan);
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			response.setResponseData(responseData);
			return ResponseEntity.ok().body(response);
		}
	}

	// fetch Loan Planning

	// fetch Financial Planning Table
	@RequestMapping(value = "/fetch-loanPlanning", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> fetchLoanPlanningById(@RequestBody CalcIdRequest idRequest) {
		String id = idRequest.getId();
		Party party = calcService.fetchPartyIdByRoleBasedId(id);
		if (party == null) {
			// If party is not available return no record found
			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.NO_RECORD_FOUND);
			responseMessage.setResponseDescription(appMessages.getNo_record_found());
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			return ResponseEntity.ok().body(response);
		} else {
			// Fetch Emicalculator, emicapacity, partialpayment,emichange,intererstcahnge,
			// emiinterestchange
			long partyId = party.getPartyId();
			LoanPlanning loanPlanning = new LoanPlanning();
			loanPlanning.setEmiCalculator(calcService.fetchEmiCalculatorByPartyId(partyId));
			loanPlanning.setEmiCapacity(calcService.fetchEmiCapacityByPartyId(partyId));
			loanPlanning.setPartialPayment(calcService.fetchPartialPaymentByPartyId(partyId));
			loanPlanning.setEmiChange(calcService.fetchEmiChangeByPartyId(partyId));
			loanPlanning.setInterestChange(calcService.fetchInterestChangeByPartyId(partyId));
			loanPlanning.setEmiInterestChange(calcService.fetchEmiInterestChangeByPartyId(partyId));

			ResponseMessage responseMessage = new ResponseMessage();
			responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
			responseMessage.setResponseDescription(appMessages.getSuccess());
			ResponseData responseData = new ResponseData();
			responseData.setData(loanPlanning);
			Response response = new Response();
			response.setResponseMessage(responseMessage);
			response.setResponseData(responseData);
			return ResponseEntity.ok().body(response);
		}
	}

	// private List<RiskProfile> fetchAllRiskProfileByPartyId(long partyId) {
	// List<RiskProfile> riskProfileList = new ArrayList<RiskProfile>();
	// List<RiskQuestionaire> riskQuestionaireList =
	// calcService.fetchRiskQuestionaireList();
	// for (RiskQuestionaire riskQuestionaire : riskQuestionaireList) {
	// RiskProfile riskProfile =
	// calcService.fetchRiskProfileByPartyIdAndAnswerId(partyId,
	// riskQuestionaire.getAnswerId());
	// if (riskProfile == null) {
	// RiskProfile riskProfile1 = new RiskProfile();
	// riskProfile1.setPartyId(partyId);
	// riskProfile1.setAnswerId(riskQuestionaire.getAnswerId());
	// riskProfile1.setAnswer(riskQuestionaire.getAnswer());
	// riskProfile1.setQuestionId(riskQuestionaire.getQuestionId());
	// riskProfile1.setQuestion(riskQuestionaire.getQuestion());
	// riskProfileList.add(riskProfile1);
	// } else {
	// riskProfileList.add(riskProfile);
	// }
	// }
	// return riskProfileList;
	// }

	private List<Networth> fetchAllNetworthByPartyId(long partyId) {
		List<Networth> networthList = new ArrayList<Networth>();
		List<Account> accountList = calcService.fetchAccountList();
		for (Account account : accountList) {
			Networth networth = calcService.fetchNetworthByPartyIdAndEntryId(partyId, account.getAccountEntryId());
			if (networth == null) {
				Networth networth1 = new Networth();
				networth1.setPartyId(partyId);
				networth1.setAccountEntryId(account.getAccountEntryId());
				networth1.setAccountEntry(account.getAccountEntry());
				networth1.setAccountTypeId(account.getAccountTypeId());
				AccountType accountType = calcService.fetchAccountTypeByTypeId(account.getAccountTypeId());
				networth1.setAccountType(accountType.getAccountType());
				networthList.add(networth1);
			} else {
				networthList.add(networth);
			}
		}
		return networthList;

	}

	private List<CashFlow> fetchAllCashFlowByPartyId(long partyId) {
		List<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		List<CashFlowItem> cashFlowItemList = calcService.fetchCashFlowItemList();
		for (CashFlowItem cashFlowItem : cashFlowItemList) {
			CashFlow cashFlow = calcService.fetchCashFlowByPartyIdAndItemId(partyId, cashFlowItem.getCashFlowItemId());
			if (cashFlow == null) {
				CashFlow cashFlow1 = new CashFlow();
				cashFlow1.setPartyId(partyId);
				cashFlow1.setCashFlowItemId(cashFlowItem.getCashFlowItemId());
				cashFlow1.setCashFlowItem(cashFlowItem.getCashFlowItem());
				cashFlow1.setCashFlowItemTypeId(cashFlowItem.getCashFlowItemTypeId());
				CashFlowItemType cashFlowItemType = calcService
						.fetchCashFlowItemTypeByTypeId(cashFlowItem.getCashFlowItemTypeId());
				cashFlow1.setCashFlowItemType(cashFlowItemType.getCashFlowItemType());
				cashFlowList.add(cashFlow1);
			} else {
				cashFlowList.add(cashFlow);
			}
		}
		return cashFlowList;
	}

	private List<Priority> fetchAllPriorityByPartyId(long partyId) {
		List<Priority> priorityList = new ArrayList<Priority>();
		List<PriorityItem> priorityItemList = calcService.fetchPriorityItemList();
		List<Urgency> urgencyList = calcService.fetchUrgencyList();
		Urgency urgency = new Urgency();
		for (Urgency urgency1 : urgencyList) {
			urgency.setUrgencyId(urgency1.getUrgencyId());
			urgency.setValue(urgency1.getValue());
		}
		for (PriorityItem priorityItem : priorityItemList) {
			Priority priority = calcService.fetchPriorityByPartyIdAndItemId(partyId, priorityItem.getPriorityItemId());
			if (priority == null) {
				Priority priority1 = new Priority();
				priority1.setPartyId(partyId);
				priority1.setPriorityItemId(priorityItem.getPriorityItemId());
				priority1.setPriorityItem(priorityItem.getPriorityItem());
				priorityList.add(priority1);
			} else {
				priorityList.add(priority);
			}
		}
		return priorityList;
	}

	// LookUp Table Fetch Services
	@RequestMapping(value = "/fetch-all-accountType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> fetchAccountTypeList() {
		List<AccountType> accountTypeList = calcService.fetchAccountTypeList();
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
		responseMessage.setResponseDescription(appMessages.getSuccess());
		ResponseData responseData = new ResponseData();
		responseData.setData(accountTypeList);
		Response response = new Response();
		response.setResponseMessage(responseMessage);
		response.setResponseData(responseData);
		return ResponseEntity.ok().body(response);
	}

	@RequestMapping(value = "/fetch-all-account", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> fetchAccountList() {
		List<Account> accountList = calcService.fetchAccountList();
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
		responseMessage.setResponseDescription(appMessages.getSuccess());
		ResponseData responseData = new ResponseData();
		responseData.setData(accountList);
		Response response = new Response();
		response.setResponseMessage(responseMessage);
		response.setResponseData(responseData);
		return ResponseEntity.ok().body(response);
	}

	@RequestMapping(value = "/fetch-all-cashFlowItemType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> fetchCashFlowItemTypeList() {
		List<CashFlowItemType> cashFlowItemTypeList = calcService.fetchCashFlowItemTypeList();
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
		responseMessage.setResponseDescription(appMessages.getSuccess());
		ResponseData responseData = new ResponseData();
		responseData.setData(cashFlowItemTypeList);
		Response response = new Response();
		response.setResponseMessage(responseMessage);
		response.setResponseData(responseData);
		return ResponseEntity.ok().body(response);
	}

	@RequestMapping(value = "/fetch-all-cashFlowItem", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> fetchCashFlowItemList() {
		List<CashFlowItem> cashFlowItemList = calcService.fetchCashFlowItemList();
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
		responseMessage.setResponseDescription(appMessages.getSuccess());
		ResponseData responseData = new ResponseData();
		responseData.setData(cashFlowItemList);
		Response response = new Response();
		response.setResponseMessage(responseMessage);
		response.setResponseData(responseData);
		return ResponseEntity.ok().body(response);
	}

	@RequestMapping(value = "/fetch-all-priorityItem", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> fetchPriorityItemList() {
		List<PriorityItem> priorityItemList = calcService.fetchPriorityItemList();
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
		responseMessage.setResponseDescription(appMessages.getSuccess());
		ResponseData responseData = new ResponseData();
		responseData.setData(priorityItemList);
		Response response = new Response();
		response.setResponseMessage(responseMessage);
		response.setResponseData(responseData);
		return ResponseEntity.ok().body(response);
	}

	@RequestMapping(value = "/fetch-all-urgency", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> fetchUrgencyList() {
		List<Urgency> urgencyList = calcService.fetchUrgencyList();
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
		responseMessage.setResponseDescription(appMessages.getSuccess());
		ResponseData responseData = new ResponseData();
		responseData.setData(urgencyList);
		Response response = new Response();
		response.setResponseMessage(responseMessage);
		response.setResponseData(responseData);
		return ResponseEntity.ok().body(response);
	}

	@RequestMapping(value = "/fetch-all-riskPortfolio", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> fetchRiskPortfolioList() {
		List<RiskPortfolio> riskPortfolioList = calcService.fetchRiskPortfolioList();
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
		responseMessage.setResponseDescription(appMessages.getSuccess());
		ResponseData responseData = new ResponseData();
		responseData.setData(riskPortfolioList);
		Response response = new Response();
		response.setResponseMessage(responseMessage);
		response.setResponseData(responseData);
		return ResponseEntity.ok().body(response);
	}

	@RequestMapping(value = "/fetch-all-riskQuestionaire", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> fetchRiskQuestionaireList() {
		List<String> questionIds = calcService.fetchQuestionIdFromRiskQuestionaire();
		List<RiskQuestionaireResponse> riskQuestionaireResponseList = new ArrayList<>();
		for (String questionId : questionIds) {
			List<RiskQuestionaire> riskQuestionaireList = calcService.fetchRiskQuestionaireByQuestionId(questionId);
			String question = calcService.fetchQuestionByQuestionId(questionId);
			RiskQuestionaireResponse riskQuestionaireResponse = new RiskQuestionaireResponse();
			riskQuestionaireResponse.setQuestionId(questionId);
			riskQuestionaireResponse.setQuestion(question);
			List<AnswerRes> answer = new ArrayList<>();
			for (RiskQuestionaire riskQuestionaire : riskQuestionaireList) {
				AnswerRes ans = new AnswerRes();
				ans.setAnswerId(riskQuestionaire.getAnswerId());
				ans.setAnswer(riskQuestionaire.getAnswer());
				answer.add(ans);
			}
			riskQuestionaireResponse.setAnswerRes(answer);
			riskQuestionaireResponseList.add(riskQuestionaireResponse);
		}
		// Response like question and their options
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setResponseCode(CalcConstants.SUCCESS_CODE);
		responseMessage.setResponseDescription(appMessages.getSuccess());
		ResponseData responseData = new ResponseData();
		responseData.setData(riskQuestionaireResponseList);
		Response response = new Response();
		response.setResponseMessage(responseMessage);
		response.setResponseData(responseData);
		return ResponseEntity.ok().body(response);
	}
}
