package com.sowisetech.calc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sowisetech.calc.dao.CalcDao;
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

@Service("CalcService")
@Transactional(readOnly = true)
public class CalcServiceImpl implements CalcService {

	@Autowired
	private CalcDao calcDao;

	// FutureCost
	@Transactional
	public double calculateGoalFutureCost(double goalAmt, double inflationRate, int tenure) {
		double futureCost = goalAmt * Math.pow((1 + inflationRate), tenure);
		return futureCost;
	}

	// Current Investment
	@Transactional
	public double calculateGoalCurrentInvestment(double currentAmt, double growthRate, int tenure) {
		double futureValue = currentAmt * Math.pow((1 + growthRate), tenure);
		return futureValue;
	}

	// Final Corpus
	@Transactional
	public double calculateGoalFinalCorpus(double futureCost, double futureValue) {
		double finalCorpus = futureCost - futureValue;
		return finalCorpus;
	}

	@Transactional
	public double calculateGoalMonthlyInvestment(double growthRate, double annualInvRate, double finalCorpus,
			int tenure, double returnRate) {
		double monthlyInvestment = 0;
		if (growthRate == annualInvRate) {
			monthlyInvestment = finalCorpus / (12 * tenure * Math.pow((1 + returnRate), (tenure * 12)));
		} else {
			monthlyInvestment = (finalCorpus * (returnRate - (annualInvRate / 12)))
					/ ((1 + returnRate) * ((Math.pow((1 + returnRate), (tenure * 12)))
							- (Math.pow((1 + (annualInvRate / 12)), (tenure * 12)))));
		}
		return monthlyInvestment;

	}

	@Transactional
	public double calculateGoalAnnualyInvestment(double growthRate, double annualInvRate, double finalCorpus,
			int tenure) {
		double annualInv = 0;
		if (growthRate == annualInvRate) {
			annualInv = finalCorpus / tenure * Math.pow((1 + growthRate), tenure);
		} else {
			annualInv = (finalCorpus * (growthRate - annualInvRate))
					/ ((1 + growthRate) * (Math.pow((1 + growthRate), tenure) - Math.pow((1 + annualInvRate), tenure)));
		}
		return annualInv;
	}

	@Transactional
	public void addGoalInfo(Goal goal) {
		calcDao.addGoalInfo(goal);
	}

	@Transactional
	public int fetchCashFlowItemTypeIdByItemId(long cashFlowItemId) {
		return calcDao.fetchCashFlowItemTypeIdByItemId(cashFlowItemId);
	}

	@Transactional
	public void addCashFlow(CashFlow cashFlow) {
		calcDao.addCashFlow(cashFlow);

	}

	@Transactional
	public List<CashFlow> fetchCashFlowByPartyIdAndTypeId(long partyId, int typeId) {
		return calcDao.fetchCashFlowByPartyIdAndTypeId(partyId, typeId);
	}

	@Transactional
	public List<Integer> fetchRecurringExpenditureItemType() {
		return calcDao.fetchRecurringExpenditureItemType();
	}

	@Transactional
	public List<CashFlow> fetchNonRecurringExpenditureByPartyId(long partyId) {
		return calcDao.fetchNonRecurringExpenditureByPartyId(partyId);
	}

	@Transactional
	public List<CashFlow> fetchRecurringIncomeByPartyId(long partyId) {
		return calcDao.fetchRecurringIncomeByPartyId(partyId);
	}

	@Transactional
	public List<CashFlow> fetchNonRecurringIncomeByPartyId(long partyId) {
		return calcDao.fetchNonRecurringIncomeByPartyId(partyId);
	}

	@Transactional
	public void addCashFlowSummary(CashFlowSummary cashFlowSummary) {
		calcDao.addCashFlowSummary(cashFlowSummary);
	}

	@Transactional
	public void addNetworth(Networth networth) {
		calcDao.addNetworth(networth);

	}

	@Transactional
	public int fetchAccountTypeIdByEntryId(int accountEntryId) {
		return calcDao.fetchAccountTypeIdByEntryId(accountEntryId);
	}

	@Transactional
	public List<Networth> fetchNetworthByAssets(long partyId) {
		return calcDao.fetchNetworthByAssets(partyId);
	}

	@Transactional
	public List<Networth> fetchNetworthByLiabilities(long partyId) {
		return calcDao.fetchNetworthByLiabilities(partyId);
	}

	@Transactional
	public void addNetworthSummary(NetworthSummary networthSummary) {
		calcDao.addNetworthSummary(networthSummary);

	}

	@Transactional
	public void addInsurance(Insurance insurance) {
		calcDao.addInsurance(insurance);
	}

	@Transactional
	public void addPriorities(Priority priority) {
		calcDao.addPriorities(priority);
	}

	@Transactional
	public List<Priority> fetchPriorityByPartyId(long partyId) {
		return calcDao.fetchPriorityByPartyId(partyId);
	}

	@Transactional
	public Priority fetchPriorityByPartyIdAndItemId(long partyId, int priorityItemId) {
		return calcDao.fetchPriorityByPartyIdAndItemId(partyId, priorityItemId);
	}

	@Transactional
	public void updatePriorityOrder(long partyId, int priorityItemId, int order) {
		calcDao.updatePriorityOrder(partyId, priorityItemId, order);
	}

	@Transactional
	public Party fetchParty(long partyId) {
		return calcDao.fetchParty(partyId);
	}

	@Transactional
	public void addRiskProfile(RiskProfile riskProfile) {		
		int score = calcDao.fetchScoreByAnswerId(riskProfile);
		riskProfile.setScore(score);
		calcDao.addRiskProfile(riskProfile);
	}

	@Transactional
	public List<RiskProfile> fetchRiskProfileByPartyId(long partyId) {
		return calcDao.fetchRiskProfileByPartyId(partyId);
	}

	@Transactional
	public RiskPortfolio fetchRiskPortfolioByPoints(String points) {
		return calcDao.fetchRiskPortfolioByPoints(points);
	}

	@Transactional
	public void addRiskSummary(RiskSummary riskSummary) {
		calcDao.addRiskSummary(riskSummary);
	}

	@Transactional
	public List<AccountType> fetchAccountTypeList() {
		return calcDao.fetchAccountTypeList();
	}

	@Transactional
	public List<Account> fetchAccountList() {
		return calcDao.fetchAccountList();
	}

	@Transactional
	public List<CashFlowItemType> fetchCashFlowItemTypeList() {
		return calcDao.fetchCashFlowItemTypeList();
	}

	@Transactional
	public List<CashFlowItem> fetchCashFlowItemList() {
		return calcDao.fetchCashFlowItemList();
	}

	@Transactional
	public List<PriorityItem> fetchPriorityItemList() {
		return calcDao.fetchPriorityItemList();
	}

	@Transactional
	public List<Urgency> fetchUrgencyList() {
		return calcDao.fetchUrgencyList();
	}

	@Transactional
	public List<RiskPortfolio> fetchRiskPortfolioList() {
		return calcDao.fetchRiskPortfolioList();
	}

	@Transactional
	public List<RiskQuestionaire> fetchRiskQuestionaireList() {
		return calcDao.fetchRiskQuestionaireList();
	}

	@Transactional
	public Party fetchPartyIdByRoleBasedId(String id) {
		return calcDao.fetchPartyIdByRoleBasedId(id);
	}

	@Transactional
	public List<Goal> fetchGoalByPartyId(long partyId) {
		return calcDao.fetchGoalByPartyId(partyId);
	}

	@Transactional
	public List<CashFlow> fetchCashFlowByPartyId(long partyId) {
		return calcDao.fetchCashFlowByPartyId(partyId);
	}

	@Transactional
	public CashFlowSummary fetchCashFlowSummaryByPartyId(long partyId) {
		return calcDao.fetchCashFlowSummaryByPartyId(partyId);
	}

	@Transactional
	public List<Networth> fetchNetworthByPartyId(long partyId) {
		return calcDao.fetchNetworthByPartyId(partyId);
	}

	@Transactional
	public NetworthSummary fetchNetworthSummaryByPartyId(long partyId) {
		return calcDao.fetchNetworthSummaryByPartyId(partyId);
	}

	@Transactional
	public Insurance fetchInsuranceByPartyId(long partyId) {
		return calcDao.fetchInsuranceByPartyId(partyId);
	}

	@Transactional
	public RiskSummary fetchRiskSummaryByPartyId(long partyId) {
		return calcDao.fetchRiskSummaryByPartyId(partyId);
	}

	@Transactional
	public void addEmiCalculator(EmiCalculator emiCalculator) {
		calcDao.addEmiCalculator(emiCalculator);
	}

	@Transactional
	public void addEmiCapacity(EmiCapacity emiCapacity) {
		calcDao.addEmiCapacity(emiCapacity);
	}

	@Transactional
	public void addPartialPayment(PartialPayment partialPayment) {
		calcDao.addPartialPayment(partialPayment);
	}

	@Transactional
	public void addInterestChange(InterestChange interestChange) {
		calcDao.addInterestChange(interestChange);
	}

	@Transactional
	public void addEmiChange(EmiChange emiChange) {
		calcDao.addEmiChange(emiChange);

	}

	@Transactional
	public void addEmiInterestChange(EmiInterestChange emiInterestChange) {
		calcDao.addEmiInterestChange(emiInterestChange);
	}

	@Transactional
	public EmiCalculator fetchEmiCalculatorByPartyId(long partyId) {
		return calcDao.fetchEmiCalculatorByPartyId(partyId);
	}

	@Transactional
	public EmiCapacity fetchEmiCapacityByPartyId(long partyId) {
		return calcDao.fetchEmiCapacityByPartyId(partyId);
	}

	@Transactional
	public List<PartialPayment> fetchPartialPaymentByPartyId(long partyId) {
		return calcDao.fetchPartialPaymentByPartyId(partyId);
	}

	@Transactional
	public List<InterestChange> fetchInterestChangeByPartyId(long partyId) {
		return calcDao.fetchInterestChangeByPartyId(partyId);
	}

	@Transactional
	public List<EmiChange> fetchEmiChangeByPartyId(long partyId) {
		return calcDao.fetchEmiChangeByPartyId(partyId);
	}

	@Transactional
	public List<EmiInterestChange> fetchEmiInterestChangeByPartyId(long partyId) {
		return calcDao.fetchEmiInterestChangeByPartyId(partyId);
	}

	@Transactional
	public RiskProfile fetchRiskProfileByPartyIdAndAnswerId(long partyId, long answerId) {
		return calcDao.fetchRiskProfileByPartyIdAndAnswerId(partyId, answerId);
	}

	@Transactional
	public Networth fetchNetworthByPartyIdAndEntryId(long partyId, long accountEntryId) {
		return calcDao.fetchNetworthByPartyIdAndEntryId(partyId, accountEntryId);
	}

	@Transactional
	public AccountType fetchAccountTypeByTypeId(long accountTypeId) {
		return calcDao.fetchAccountTypeByTypeId(accountTypeId);
	}

	@Transactional
	public CashFlow fetchCashFlowByPartyIdAndItemId(long partyId, long cashFlowItemId) {
		return calcDao.fetchCashFlowByPartyIdAndItemId(partyId, cashFlowItemId);
	}

	@Transactional
	public CashFlowItemType fetchCashFlowItemTypeByTypeId(long cashFlowItemTypeId) {
		return calcDao.fetchCashFlowItemTypeByTypeId(cashFlowItemTypeId);
	}

	@Transactional
	public InsuranceItem fetchInsuranceItemByPartyId(long partyId) {
		return calcDao.fetchInsuranceItemByPartyId(partyId);
	}

	@Transactional
	public void addPlanInfo(Plan plan) {
		calcDao.addPlanInfo(plan);
	}

	@Transactional
	public String fetchRoleBasedIdByPartyId(long partyId) {
		return calcDao.fetchRoleBasedIdByPartyId(partyId);
	}

	@Transactional
	public String fetchRoleBasedIdByParentPartyId(long parentPartyId) {
		return calcDao.fetchRoleBasedIdByParentPartyId(parentPartyId);
	}

	@Transactional
	public List<Plan> fetchPlanByReferenceId(String id) {
		return calcDao.fetchPlanByReferenceId(id);
	}

	@Transactional
	public void updateCashFlow(CashFlow cashFlow) {
		calcDao.updateCashFlow(cashFlow);
	}

	@Transactional
	public void removeCashFlowSummary(long partyId) {
		calcDao.removeCashFlowSummary(partyId);
	}

	@Transactional
	public void updateNetworth(Networth networth) {
		calcDao.updateNetworth(networth);
	}

	@Transactional
	public void removeNetworthSummary(long partyId) {
		calcDao.removeNetworthSummary(partyId);
	}

	@Transactional
	public void updatePriority(Priority priority) {
		calcDao.updatePriority(priority);
	}

	@Transactional
	public void updateInsurance(Insurance insurance) {
		calcDao.updateInsurance(insurance);
	}

	@Transactional
	public void updateRiskProfile(RiskProfile riskProfile) {
		int score = calcDao.fetchScoreByAnswerId(riskProfile);
		riskProfile.setScore(score);
		calcDao.updateRiskProfile(riskProfile);
	}

	@Transactional
	public void removeRiskSummary(long partyId) {
		calcDao.removeRiskSummary(partyId);
	}

	@Transactional
	public void updateEmiCalculator(EmiCalculator emiCalculator) {
		calcDao.updateEmiCalculator(emiCalculator);
	}

	@Transactional
	public void updateEmiCalculator(EmiCapacity emiCapacity) {
		calcDao.updateEmiCalculator(emiCapacity);
	}

	@Transactional
	public RiskProfile fetchRiskProfileByPartyIdAndQuestionId(long partyId, String questionId) {
		return calcDao.fetchRiskProfileByPartyIdAndQuestionId(partyId, questionId);
	}

	@Transactional
	public List<String> fetchQuestionIdFromRiskQuestionaire() {
		return calcDao.fetchQuestionIdFromRiskQuestionaire();
	}

	@Transactional
	public List<RiskQuestionaire> fetchRiskQuestionaireByQuestionId(String questionId) {
		return calcDao.fetchRiskQuestionaireByQuestionId(questionId);
	}

	@Transactional
	public String fetchQuestionByQuestionId(String questionId) {
		return calcDao.fetchQuestionByQuestionId(questionId);
	}

	@Transactional
	public Goal fetchGoalByPartyIdAndGoalName(long partyId, String goalName) {
		return calcDao.fetchGoalByPartyIdAndGoalName(partyId, goalName);
	}

	@Transactional
	public void updateGoalInfo(Goal goal) {
		calcDao.updateGoalInfo(goal);
	}

}
