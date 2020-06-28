package com.sowisetech.calc.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
import com.sowisetech.calc.model.Goal;
import com.sowisetech.calc.model.Insurance;
import com.sowisetech.calc.model.InsuranceItem;
import com.sowisetech.calc.model.InterestChange;
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

@Service
public interface CalcService {

	double calculateGoalFutureCost(double goalAmt, double inflationRate, int tenure);

	double calculateGoalCurrentInvestment(double currentAmt, double growthRate, int tenure);

	double calculateGoalFinalCorpus(double futureCost, double futureValue);

	double calculateGoalMonthlyInvestment(double growthRate, double annualInvRate, double finalCorpus, int tenure,
			double returnRate);

	double calculateGoalAnnualyInvestment(double growthRate, double annualInvRate, double finalCorpus, int tenure);

	void addGoalInfo(Goal goal);

	int fetchCashFlowItemTypeIdByItemId(long cashFlowItemId);

	void addCashFlow(CashFlow cashFlow);

	List<CashFlow> fetchCashFlowByPartyIdAndTypeId(long partyId, int typeId);

	List<Integer> fetchRecurringExpenditureItemType();

	List<CashFlow> fetchNonRecurringExpenditureByPartyId(long partyId);

	List<CashFlow> fetchRecurringIncomeByPartyId(long partyId);

	List<CashFlow> fetchNonRecurringIncomeByPartyId(long partyId);

	void addCashFlowSummary(CashFlowSummary cashFlowSummary);

	void addNetworth(Networth networth);

	int fetchAccountTypeIdByEntryId(int accountEntryId);

	List<Networth> fetchNetworthByAssets(long partyId);

	List<Networth> fetchNetworthByLiabilities(long partyId);

	void addNetworthSummary(NetworthSummary networthSummary);

	void addInsurance(Insurance insurance);

	void addPriorities(Priority priority);

	List<Priority> fetchPriorityByPartyId(long partyId);

	Priority fetchPriorityByPartyIdAndItemId(long partyId, int priorityItemId);

	void updatePriorityOrder(long partyId, int priorityItemId, int order);

	Party fetchParty(long partyId);

	void addRiskProfile(RiskProfile riskProfile);

	List<RiskProfile> fetchRiskProfileByPartyId(long partyId);

	RiskPortfolio fetchRiskPortfolioByPoints(String points);

	void addRiskSummary(RiskSummary riskSummary);

	List<AccountType> fetchAccountTypeList();

	List<Account> fetchAccountList();

	List<CashFlowItemType> fetchCashFlowItemTypeList();

	List<CashFlowItem> fetchCashFlowItemList();

	List<PriorityItem> fetchPriorityItemList();

	List<Urgency> fetchUrgencyList();

	List<RiskPortfolio> fetchRiskPortfolioList();

	List<RiskQuestionaire> fetchRiskQuestionaireList();

	Party fetchPartyIdByRoleBasedId(String id);

	List<Goal> fetchGoalByPartyId(long partyId);

	List<CashFlow> fetchCashFlowByPartyId(long partyId);

	CashFlowSummary fetchCashFlowSummaryByPartyId(long partyId);

	List<Networth> fetchNetworthByPartyId(long partyId);

	Insurance fetchInsuranceByPartyId(long partyId);

	NetworthSummary fetchNetworthSummaryByPartyId(long partyId);

	RiskSummary fetchRiskSummaryByPartyId(long partyId);

	void addEmiCalculator(EmiCalculator emiCalculator);

	void addEmiCapacity(EmiCapacity emiCapacity);

	void addPartialPayment(PartialPayment partialPayment);

	void addInterestChange(InterestChange interestChange);

	void addEmiChange(EmiChange emiChange);

	void addEmiInterestChange(EmiInterestChange emiInterestChange);

	EmiCalculator fetchEmiCalculatorByPartyId(long partyId);

	EmiCapacity fetchEmiCapacityByPartyId(long partyId);

	List<PartialPayment> fetchPartialPaymentByPartyId(long partyId);

	List<InterestChange> fetchInterestChangeByPartyId(long partyId);

	List<EmiChange> fetchEmiChangeByPartyId(long partyId);
	
	List<EmiInterestChange> fetchEmiInterestChangeByPartyId(long partyId);

	RiskProfile fetchRiskProfileByPartyIdAndAnswerId(long partyId, long answerId);

	Networth fetchNetworthByPartyIdAndEntryId(long partyId, long accountEntryId);

	AccountType fetchAccountTypeByTypeId(long accountTypeId);

	CashFlow fetchCashFlowByPartyIdAndItemId(long partyId, long cashFlowItemId);

	CashFlowItemType fetchCashFlowItemTypeByTypeId(long cashFlowItemTypeId);

	InsuranceItem fetchInsuranceItemByPartyId(long partyId);

	void addPlanInfo(Plan plan);

	String fetchRoleBasedIdByPartyId(long partyId);

	String fetchRoleBasedIdByParentPartyId(long parentPartyId);

	List<Plan> fetchPlanByReferenceId(String id);

	void updateCashFlow(CashFlow cashFlow);

	void removeCashFlowSummary(long partyId);

	void updateNetworth(Networth networth);

	void removeNetworthSummary(long partyId);

	void updatePriority(Priority priority);

	void updateInsurance(Insurance insurance);

	void updateRiskProfile(RiskProfile riskProfile);

	void removeRiskSummary(long partyId);

	void updateEmiCalculator(EmiCalculator emiCalculator);

	void updateEmiCalculator(EmiCapacity emiCapacity);

	RiskProfile fetchRiskProfileByPartyIdAndQuestionId(long partyId, String questionId);

	List<String> fetchQuestionIdFromRiskQuestionaire();

	List<RiskQuestionaire> fetchRiskQuestionaireByQuestionId(String questionId);

	String fetchQuestionByQuestionId(String questionId);

	Goal fetchGoalByPartyIdAndGoalName(long partyId, String goalName);

	void updateGoalInfo(Goal goal);

}
