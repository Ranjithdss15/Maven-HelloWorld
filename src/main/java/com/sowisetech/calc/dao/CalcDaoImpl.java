package com.sowisetech.calc.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
import com.sowisetech.calc.model.InsuranceAmountItem;
import com.sowisetech.calc.model.InsuranceItem;
import com.sowisetech.calc.model.InsuranceStringItem;
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

@Repository
@Transactional
public class CalcDaoImpl implements CalcDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	DataSource dataSource;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@PostConstruct
	public void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void addGoalInfo(Goal goal) {
		String sql = "INSERT INTO `goal` (`partyId`,`parentPartyId`,`goalName`, `tenure`,`tenureType`, `goalAmount`, `inflationRate`,`currentAmount`,`growthRate`,`rateOfReturn`,`annualInvestmentRate`,`futureCost`,`futureValue`,`finalCorpus`,`monthlyInv`,`annualInv`) VALUES (?,?,?,?,?,?,?,?, ?, ?, ?, ?,?,?,?,?)";
		jdbcTemplate.update(sql, goal.getPartyId(), goal.getParentPartyId(), goal.getGoalName(), goal.getTenure(),
				goal.getTenureType(), goal.getGoalAmount(), goal.getInflationRate(), goal.getCurrentAmount(),
				goal.getGrowthRate(), goal.getRateOfReturn(), goal.getAnnualInvestmentRate(), goal.getFutureCost(),
				goal.getFutureValue(), goal.getFinalCorpus(), goal.getMonthlyInv(), goal.getAnnualInv());
	}

	@Override
	public int fetchCashFlowItemTypeIdByItemId(long cashFlowItemId) {
		try {
			String sql = "SELECT `cashFlowItemTypeId` FROM `cashflowitem` WHERE `cashFlowItemId`= ?";
			return jdbcTemplate.queryForObject(sql, Integer.class, cashFlowItemId);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}

	@Override
	public void addCashFlow(CashFlow cashFlow) {
		String sql = "INSERT INTO `cashflow` (`partyId`,`cashFlowItemId`, `budgetAmt`,`actualAmt`, `date`, `cashFlowItemTypeId`) VALUES (?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, cashFlow.getPartyId(), cashFlow.getCashFlowItemId(), cashFlow.getBudgetAmt(),
				cashFlow.getActualAmt(), cashFlow.getDate(), cashFlow.getCashFlowItemTypeId());
	}

	@Override
	public List<CashFlow> fetchCashFlowByPartyIdAndTypeId(long partyId, int typeId) {
		try {
			String sql = "SELECT * FROM `cashflow` WHERE `partyId`=? AND `cashFlowItemTypeId`=?";
			RowMapper<CashFlow> rowMapper = new BeanPropertyRowMapper<CashFlow>(CashFlow.class);
			List<CashFlow> cashFlow = jdbcTemplate.query(sql, rowMapper, partyId, typeId);
			return cashFlow;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Integer> fetchRecurringExpenditureItemType() {
		int typeId1, typeId2, typeId3, typeId4;
		try {
			String sqlCashFlowTypeId1 = "SELECT `cashFlowItemTypeId` FROM `cashflowitemtype` WHERE `cashFlowItemType`= ?";
			typeId1 = jdbcTemplate.queryForObject(sqlCashFlowTypeId1, Integer.class, "Mandatory Household Expense");
		} catch (EmptyResultDataAccessException e) {
			typeId1 = 0;
		}
		try {
			String sqlCashFlowTypeId2 = "SELECT `cashFlowItemTypeId` FROM `cashflowitemtype` WHERE `cashFlowItemType`= ?";
			typeId2 = jdbcTemplate.queryForObject(sqlCashFlowTypeId2, Integer.class, "Life Style Expenses");
		} catch (EmptyResultDataAccessException e) {
			typeId2 = 0;
		}
		try {
			String sqlCashFlowTypeId3 = "SELECT `cashFlowItemTypeId` FROM `cashflowitemtype` WHERE `cashFlowItemType`= ?";
			typeId3 = jdbcTemplate.queryForObject(sqlCashFlowTypeId3, Integer.class, "Recurring Loan Repayments");
		} catch (EmptyResultDataAccessException e) {
			typeId3 = 0;
		}
		try {
			String sqlCashFlowTypeId4 = "SELECT `cashFlowItemTypeId` FROM `cashflowitemtype` WHERE `cashFlowItemType`= ?";
			typeId4 = jdbcTemplate.queryForObject(sqlCashFlowTypeId4, Integer.class, "Recurring Investments");
		} catch (EmptyResultDataAccessException e) {
			typeId4 = 0;
		}
		List<Integer> cashFlowItemTypeId = new ArrayList<Integer>();
		cashFlowItemTypeId.add(typeId1);
		cashFlowItemTypeId.add(typeId2);
		cashFlowItemTypeId.add(typeId3);
		cashFlowItemTypeId.add(typeId4);
		return cashFlowItemTypeId;
	}

	@Override
	public List<CashFlow> fetchNonRecurringExpenditureByPartyId(long partyId) {

		String sql = "SELECT `cashFlowItemTypeId` FROM `cashflowitemtype` WHERE `cashFlowItemType`= ?";
		int cashFlowItemTypeId = jdbcTemplate.queryForObject(sql, Integer.class, "Non Recurring Expenditures");
		try {
			String sqlCashFlow = "SELECT * FROM `cashflow` WHERE `partyId`=? AND `cashFlowItemTypeId`=?";
			RowMapper<CashFlow> rowMapper = new BeanPropertyRowMapper<CashFlow>(CashFlow.class);
			List<CashFlow> cashFlow = jdbcTemplate.query(sqlCashFlow, rowMapper, partyId, cashFlowItemTypeId);
			return cashFlow;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	@Override
	public List<CashFlow> fetchRecurringIncomeByPartyId(long partyId) {

		String sql = "SELECT `cashFlowItemTypeId` FROM `cashflowitemtype` WHERE `cashFlowItemType`= ?";
		int cashFlowItemTypeId = jdbcTemplate.queryForObject(sql, Integer.class, "Recurring Income");
		try {
			String sqlCashFlow = "SELECT * FROM `cashflow` WHERE `partyId`=? AND `cashFlowItemTypeId`=?";
			RowMapper<CashFlow> rowMapper = new BeanPropertyRowMapper<CashFlow>(CashFlow.class);
			List<CashFlow> cashFlow = jdbcTemplate.query(sqlCashFlow, rowMapper, partyId, cashFlowItemTypeId);
			return cashFlow;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<CashFlow> fetchNonRecurringIncomeByPartyId(long partyId) {

		String sql = "SELECT `cashFlowItemTypeId` FROM `cashflowitemtype` WHERE `cashFlowItemType`= ?";
		int cashFlowItemTypeId = jdbcTemplate.queryForObject(sql, Integer.class, "Non Recurring Income");
		try {
			String sqlCashFlow = "SELECT * FROM `cashflow` WHERE `partyId`=? AND `cashFlowItemTypeId`=?";
			RowMapper<CashFlow> rowMapper = new BeanPropertyRowMapper<CashFlow>(CashFlow.class);
			List<CashFlow> cashFlow = jdbcTemplate.query(sqlCashFlow, rowMapper, partyId, cashFlowItemTypeId);
			return cashFlow;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void addCashFlowSummary(CashFlowSummary cashFlowSummary) {
		String sql = "INSERT INTO `cashflowsummary` (`partyId`,`monthlyRecurExpense`,`yearlyRecurExpense`,`nonRecurExpense`,`monthlyRecurIncome`,`yearlyRecurIncome`,`nonRecurIncome`,`monthlyRecurCashFlow`,`yearlyRecurCashFlow`,`nonRecurCashFlow`) VALUES(?, ?, ?, ?, ?, ?, ?,?,?,?)";
		jdbcTemplate.update(sql, cashFlowSummary.getPartyId(), cashFlowSummary.getMonthlyRecurExpense(),
				cashFlowSummary.getYearlyRecurExpense(), cashFlowSummary.getNonRecurExpense(),
				cashFlowSummary.getMonthlyRecurIncome(), cashFlowSummary.getYearlyRecurIncome(),
				cashFlowSummary.getNonRecurIncome(), cashFlowSummary.getMonthlyRecurCashFlow(),
				cashFlowSummary.getYearlyRecurCashFlow(), cashFlowSummary.getNonRecurCashflow());

	}

	@Override
	public void addNetworth(Networth networth) {
		String sql = "INSERT INTO `networth` (`accountEntryId`,`value`,`futureValue`,`partyId`,`accountTypeId`) VALUES(?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, networth.getAccountEntryId(), networth.getValue(), networth.getFutureValue(),
				networth.getPartyId(), networth.getAccountTypeId());
	}

	@Override
	public int fetchAccountTypeIdByEntryId(int accountEntryId) {
		try {
			String sql = "SELECT `accountTypeId` FROM `account` WHERE `accountEntryId`= ?";
			return jdbcTemplate.queryForObject(sql, Integer.class, accountEntryId);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}

	@Override
	public List<Networth> fetchNetworthByAssets(long partyId) {
		String sql = "SELECT `accountTypeId` FROM `accounttype` WHERE `accountType`= ?";
		Long accountTypeId = jdbcTemplate.queryForObject(sql, Long.class, "Assets");
		try {
			String sqlNetworth = "SELECT * FROM `networth` WHERE `partyId` = ? AND `accountTypeId`=?";
			RowMapper<Networth> rowMapper = new BeanPropertyRowMapper<Networth>(Networth.class);
			List<Networth> networth = jdbcTemplate.query(sqlNetworth, rowMapper, partyId, accountTypeId);
			return networth;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Networth> fetchNetworthByLiabilities(long partyId) {

		String sql = "SELECT `accountTypeId` FROM `accounttype` WHERE `accountType`= ?";
		Long accountTypeId = jdbcTemplate.queryForObject(sql, Long.class, "Liabilities");
		try {
			String sqlNetworth = "SELECT * FROM `networth` WHERE `partyId` = ? AND `accountTypeId`=?";
			RowMapper<Networth> rowMapper = new BeanPropertyRowMapper<Networth>(Networth.class);
			List<Networth> networth = jdbcTemplate.query(sqlNetworth, rowMapper, partyId, accountTypeId);
			return networth;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void addNetworthSummary(NetworthSummary networthSummary) {
		String sql = "INSERT INTO `networthsummary` (`partyId`,`current_assetValue`,`current_liability`,`networth`,`future_assetValue`,`future_liability`,`future_networth`) VALUES(?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, networthSummary.getPartyId(), networthSummary.getCurrent_assetValue(),
				networthSummary.getCurrent_liability(), networthSummary.getNetworth(),
				networthSummary.getFuture_assetValue(), networthSummary.getFuture_liability(),
				networthSummary.getFuture_networth());
	}

	@Override
	public void addInsurance(Insurance insurance) {
		String sql = "INSERT INTO `insurance` (`partyId`,`annualIncome`,`stability`,`predictability`,`requiredInsurance`,`existingInsurance`,`additionalInsurance`) VALUES(?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, insurance.getPartyId(), insurance.getAnnualIncome(), insurance.getStability(),
				insurance.getPredictability(), insurance.getRequiredInsurance(), insurance.getExistingInsurance(),
				insurance.getAdditionalInsurance());

	}

	@Override
	public void addPriorities(Priority priority) {
		String sql = "INSERT INTO `priority` (`partyId`,`priorityItemId`,`value`,`timeline`,`urgencyId`,`priorityOrder`) VALUES(?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, priority.getPartyId(), priority.getPriorityItemId(), priority.getValue(),
				priority.getTimeLine(), priority.getUrgencyId(), priority.getPriorityOrder());
	}

	@Override
	public List<Priority> fetchPriorityByPartyId(long partyId) {
		List<Priority> priorityList = new ArrayList<Priority>();
		try {
			String sql = "SELECT * FROM `priority` WHERE `partyId`= ?";
			RowMapper<Priority> rowMapper = new BeanPropertyRowMapper<Priority>(Priority.class);
			priorityList = jdbcTemplate.query(sql, rowMapper, partyId);
			for (Priority priority : priorityList) {
				String sql1 = "SELECT `priorityItem` FROM `priorityitem` WHERE `priorityItemId`= ?";
				priority.setPriorityItem(
						(jdbcTemplate.queryForObject(sql1, String.class, priority.getPriorityItemId())));
				String sql2 = "SELECT `value` FROM `urgency` WHERE `urgencyId`= ?";
				priority.setUrgency((jdbcTemplate.queryForObject(sql2, String.class, priority.getUrgencyId())));
			}
			return priorityList;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Priority fetchPriorityByPartyIdAndItemId(long partyId, int itemId) {
		try {
			String sql = "SELECT * FROM `priority` WHERE `partyId`= ? AND `priorityItemId`=?";
			RowMapper<Priority> rowMapper = new BeanPropertyRowMapper<Priority>(Priority.class);
			Priority priority = jdbcTemplate.queryForObject(sql, rowMapper, partyId, itemId);
			String sql1 = "SELECT `priorityItem` FROM `priorityitem` WHERE `priorityItemId`= ?";
			priority.setPriorityItem((jdbcTemplate.queryForObject(sql1, String.class, priority.getPriorityItemId())));
			String sql2 = "SELECT `value` FROM `urgency` WHERE `urgencyId`= ?";
			priority.setUrgency((jdbcTemplate.queryForObject(sql2, String.class, priority.getUrgencyId())));
			return priority;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void updatePriorityOrder(long partyId, int priorityItemId, int order) {
		String sql1 = "UPDATE `priority` SET `priorityOrder`=? WHERE `partyId`=? AND `priorityItemId`=?";
		jdbcTemplate.update(sql1, order, partyId, priorityItemId);
	}

	@Override
	public Party fetchParty(long partyId) {
		try {
			String sql = "SELECT * FROM `party` WHERE `partyId`= ? AND `delete_flag`=?";
			RowMapper<Party> rowMapper = new BeanPropertyRowMapper<Party>(Party.class);
			Party party = jdbcTemplate.queryForObject(sql, rowMapper, partyId, "N");
			return party;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public int fetchScoreByAnswerId(RiskProfile riskProfile) {
		String sqlScore = "SELECT `score` FROM `riskquestionaire` WHERE `answerId`= ?";
		int score = jdbcTemplate.queryForObject(sqlScore, Integer.class, riskProfile.getAnswerId());
		return score;
	}

	@Override
	public void addRiskProfile(RiskProfile riskProfile) {
		String sql = "INSERT INTO `riskprofile` (`partyId`,`questionId`,`answerId`,`score`) VALUES(?, ?, ?, ?)";
		jdbcTemplate.update(sql, riskProfile.getPartyId(), riskProfile.getQuestionId(), riskProfile.getAnswerId(),
				riskProfile.getScore());
	}

	@Override
	public List<RiskProfile> fetchRiskProfileByPartyId(long partyId) {
		List<RiskProfile> riskProfileList = new ArrayList<RiskProfile>();
		try {
			String sql = "SELECT * FROM `riskprofile` WHERE `partyId`= ?";
			RowMapper<RiskProfile> rowMapper = new BeanPropertyRowMapper<RiskProfile>(RiskProfile.class);
			riskProfileList = jdbcTemplate.query(sql, rowMapper, partyId);
			return riskProfileList;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public RiskPortfolio fetchRiskPortfolioByPoints(String points) {
		try {
			String sql = "SELECT * FROM `riskportfolio` WHERE `points`= ?";
			RowMapper<RiskPortfolio> rowMapper = new BeanPropertyRowMapper<RiskPortfolio>(RiskPortfolio.class);
			RiskPortfolio riskPortfolio = jdbcTemplate.queryForObject(sql, rowMapper, points);
			return riskPortfolio;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void addRiskSummary(RiskSummary riskSummary) {
		String sql = "INSERT INTO `risksummary` (`partyId`,`behaviour`,`eqty_alloc`,`debt_alloc`,`cash_alloc`) VALUES(?, ?, ?, ?,?)";
		jdbcTemplate.update(sql, riskSummary.getPartyId(), riskSummary.getBehaviour(), riskSummary.getEqty_alloc(),
				riskSummary.getDebt_alloc(), riskSummary.getCash_alloc());
	}

	@Override
	public List<Goal> fetchGoalByPartyId(long partyId) {
		try {
			String sql = "SELECT * FROM `goal` WHERE `partyId`= ?";
			RowMapper<Goal> rowMapper = new BeanPropertyRowMapper<Goal>(Goal.class);
			return jdbcTemplate.query(sql, rowMapper, partyId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<CashFlow> fetchCashFlowByPartyId(long partyId) {
		List<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		try {
			String sql = "SELECT * FROM `cashflow` WHERE `partyId`= ?";
			RowMapper<CashFlow> rowMapper = new BeanPropertyRowMapper<CashFlow>(CashFlow.class);
			cashFlowList = jdbcTemplate.query(sql, rowMapper, partyId);
			for (CashFlow cashFlow : cashFlowList) {
				String sql1 = "SELECT `cashFlowItem` FROM `cashflowitem` WHERE `cashFlowItemId`= ?";
				cashFlow.setCashFlowItem(jdbcTemplate.queryForObject(sql1, String.class, cashFlow.getCashFlowItemId()));
				String sql2 = "SELECT `cashFlowItemType` FROM `cashflowitemtype` WHERE `cashFlowItemTypeId`= ?";
				cashFlow.setCashFlowItemType(
						jdbcTemplate.queryForObject(sql2, String.class, cashFlow.getCashFlowItemTypeId()));
			}
			return cashFlowList;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Networth> fetchNetworthByPartyId(long partyId) {
		List<Networth> networthList = new ArrayList<Networth>();
		try {
			String sql = "SELECT * FROM `networth` WHERE `partyId`= ?";
			RowMapper<Networth> rowMapper = new BeanPropertyRowMapper<Networth>(Networth.class);
			networthList = jdbcTemplate.query(sql, rowMapper, partyId);
			for (Networth networth : networthList) {
				String sql1 = "SELECT `accountEntry` FROM `account` WHERE `accountEntryId`= ?";
				networth.setAccountEntry(jdbcTemplate.queryForObject(sql1, String.class, networth.getAccountEntryId()));
				String sql2 = "SELECT `accountType` FROM `accounttype` WHERE `accountTypeId`= ?";
				networth.setAccountType(jdbcTemplate.queryForObject(sql2, String.class, networth.getAccountTypeId()));
			}
			return networthList;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public CashFlowSummary fetchCashFlowSummaryByPartyId(long partyId) {
		try {
			String sql = "SELECT * FROM `cashflowsummary` WHERE `partyId`= ?";
			RowMapper<CashFlowSummary> rowMapper = new BeanPropertyRowMapper<CashFlowSummary>(CashFlowSummary.class);
			return jdbcTemplate.queryForObject(sql, rowMapper, partyId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public NetworthSummary fetchNetworthSummaryByPartyId(long partyId) {
		try {
			String sql = "SELECT * FROM `networthsummary` WHERE `partyId`= ?";
			RowMapper<NetworthSummary> rowMapper = new BeanPropertyRowMapper<NetworthSummary>(NetworthSummary.class);
			return jdbcTemplate.queryForObject(sql, rowMapper, partyId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public RiskSummary fetchRiskSummaryByPartyId(long partyId) {
		try {
			String sql = "SELECT * FROM `risksummary` WHERE `partyId`= ?";
			RowMapper<RiskSummary> rowMapper = new BeanPropertyRowMapper<RiskSummary>(RiskSummary.class);
			return jdbcTemplate.queryForObject(sql, rowMapper, partyId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Insurance fetchInsuranceByPartyId(long partyId) {
		try {
			String sql = "SELECT * FROM `insurance` WHERE `partyId`= ?";
			RowMapper<Insurance> rowMapper = new BeanPropertyRowMapper<Insurance>(Insurance.class);
			return jdbcTemplate.queryForObject(sql, rowMapper, partyId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<AccountType> fetchAccountTypeList() {
		try {
			String sql = "SELECT * FROM `accounttype`";
			RowMapper<AccountType> rowMapper = new BeanPropertyRowMapper<AccountType>(AccountType.class);
			List<AccountType> accountType = jdbcTemplate.query(sql, rowMapper);
			return accountType;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Account> fetchAccountList() {
		try {
			String sql = "SELECT * FROM `account`";
			RowMapper<Account> rowMapper = new BeanPropertyRowMapper<Account>(Account.class);
			List<Account> account = jdbcTemplate.query(sql, rowMapper);
			return account;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Party fetchPartyIdByRoleBasedId(String id) {
		try {
			String sql = "SELECT * FROM `party` WHERE `roleBasedId`=?";
			RowMapper<Party> rowMapper = new BeanPropertyRowMapper<Party>(Party.class);
			Party party = jdbcTemplate.queryForObject(sql, rowMapper, id);
			return party;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<CashFlowItemType> fetchCashFlowItemTypeList() {
		try {
			String sql = "SELECT * FROM `cashflowitemtype`";
			RowMapper<CashFlowItemType> rowMapper = new BeanPropertyRowMapper<CashFlowItemType>(CashFlowItemType.class);
			List<CashFlowItemType> cashFlowItemType = jdbcTemplate.query(sql, rowMapper);
			return cashFlowItemType;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<CashFlowItem> fetchCashFlowItemList() {
		try {
			String sql = "SELECT * FROM `cashflowitem`";
			RowMapper<CashFlowItem> rowMapper = new BeanPropertyRowMapper<CashFlowItem>(CashFlowItem.class);
			List<CashFlowItem> cashFlowItem = jdbcTemplate.query(sql, rowMapper);
			return cashFlowItem;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<PriorityItem> fetchPriorityItemList() {
		try {
			String sql = "SELECT * FROM `priorityitem`";
			RowMapper<PriorityItem> rowMapper = new BeanPropertyRowMapper<PriorityItem>(PriorityItem.class);
			List<PriorityItem> priorityItem = jdbcTemplate.query(sql, rowMapper);
			return priorityItem;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Urgency> fetchUrgencyList() {
		try {
			String sql = "SELECT * FROM `urgency`";
			RowMapper<Urgency> rowMapper = new BeanPropertyRowMapper<Urgency>(Urgency.class);
			List<Urgency> urgency = jdbcTemplate.query(sql, rowMapper);
			return urgency;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<RiskPortfolio> fetchRiskPortfolioList() {
		try {
			String sql = "SELECT * FROM `riskportfolio`";
			RowMapper<RiskPortfolio> rowMapper = new BeanPropertyRowMapper<RiskPortfolio>(RiskPortfolio.class);
			List<RiskPortfolio> riskPortfolio = jdbcTemplate.query(sql, rowMapper);
			return riskPortfolio;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<RiskQuestionaire> fetchRiskQuestionaireList() {
		try {
			String sql = "SELECT * FROM `riskquestionaire`";
			RowMapper<RiskQuestionaire> rowMapper = new BeanPropertyRowMapper<RiskQuestionaire>(RiskQuestionaire.class);
			List<RiskQuestionaire> riskQuestionaire = jdbcTemplate.query(sql, rowMapper);
			return riskQuestionaire;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void addEmiCalculator(EmiCalculator emiCalculator) {
		String sql = "INSERT INTO `emicalculator` (`partyId`,`loanAmount`,`tenure`,`interestRate`,`date`) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(sql, emiCalculator.getPartyId(), emiCalculator.getLoanAmount(), emiCalculator.getTenure(),
				emiCalculator.getInterestRate(), emiCalculator.getDate());
	}

	@Override
	public void addEmiCapacity(EmiCapacity emiCapacity) {
		String sql = "INSERT INTO `emicapacity` (`partyId`,`currentAge`,`retirementAge`,`stability`,`backUp`,`netFamilyIncome`,`existingEmi`,`houseHoldExpense`,`additionalIncome`,`interestRate`) VALUES (?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, emiCapacity.getPartyId(), emiCapacity.getCurrentAge(), emiCapacity.getRetirementAge(),
				emiCapacity.getStability(), emiCapacity.getBackUp(), emiCapacity.getNetFamilyIncome(),
				emiCapacity.getExistingEmi(), emiCapacity.getHouseHoldExpense(), emiCapacity.getAdditionalIncome(),
				emiCapacity.getInterestRate());
	}

	@Override
	public void addPartialPayment(PartialPayment partialPayment) {
		String sql = "INSERT INTO `partialpayment` (`partyId`,`loanAmount`,`interestRate`,`tenure`,`loanDate`,`partPayDate`,`partPayAmount`) VALUES(?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, partialPayment.getPartyId(), partialPayment.getLoanAmount(),
				partialPayment.getInterestRate(), partialPayment.getTenure(), partialPayment.getLoanDate(),
				partialPayment.getPartPayDate(), partialPayment.getPartPayAmount());
	}

	@Override
	public void addInterestChange(InterestChange interestChange) {
		String sql = "INSERT INTO `interestchange` (`partyId`,`loanAmount`,`interestRate`,`tenure`,`loanDate`,`changedRate`,`interestChangedDate`) VALUES (?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, interestChange.getPartyId(), interestChange.getLoanAmount(),
				interestChange.getInterestRate(), interestChange.getTenure(), interestChange.getLoanDate(),
				interestChange.getChangedRate(), interestChange.getInterestChangedDate());
	}

	@Override
	public void addEmiChange(EmiChange emiChange) {
		String sql = "INSERT INTO `emichange` (`partyId`,`loanAmount`,`interestRate`,`tenure`,`loanDate`,`increasedEmi`,`emiChangedDate`) VALUES (?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, emiChange.getPartyId(), emiChange.getLoanAmount(), emiChange.getInterestRate(),
				emiChange.getTenure(), emiChange.getLoanDate(), emiChange.getIncreasedEmi(),
				emiChange.getEmiChangedDate());
	}

	@Override
	public void addEmiInterestChange(EmiInterestChange emiInterestChange) {
		String sql = "INSERT INTO `emiinterestchange` (`partyId`,`loanAmount`,`interestRate`,`tenure`,`loanDate`,`increasedEmi`,`changedRate`,`changedDate`) VALUES (?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, emiInterestChange.getPartyId(), emiInterestChange.getLoanAmount(),
				emiInterestChange.getInterestRate(), emiInterestChange.getTenure(), emiInterestChange.getLoanDate(),
				emiInterestChange.getIncreasedEmi(), emiInterestChange.getChangedRate(),
				emiInterestChange.getChangedDate());
	}

	@Override
	public PriorityItem fetchPriorityItemByItemId(long priorityItemId) {
		try {
			String sql = "SELECT * FROM `priorityitem` WHERE `priorityItemId`=?";
			RowMapper<PriorityItem> rowMapper = new BeanPropertyRowMapper<PriorityItem>(PriorityItem.class);
			PriorityItem priorityItem = jdbcTemplate.queryForObject(sql, rowMapper, priorityItemId);
			return priorityItem;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public RiskProfile fetchRiskProfileByPartyIdAndAnswerId(long partyId, long answerId) {
		try {
			String sql = "SELECT * FROM `riskprofile` WHERE `partyId`=? AND `answerId`=?";
			RowMapper<RiskProfile> rowMapper = new BeanPropertyRowMapper<RiskProfile>(RiskProfile.class);
			RiskProfile riskProfile = jdbcTemplate.queryForObject(sql, rowMapper, partyId, answerId);
			return riskProfile;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Networth fetchNetworthByPartyIdAndEntryId(long partyId, long accountEntryId) {
		try {
			String sql = "SELECT * FROM `networth` WHERE `partyId`=? AND `accountEntryId`=?";
			RowMapper<Networth> rowMapper = new BeanPropertyRowMapper<Networth>(Networth.class);
			Networth networth = jdbcTemplate.queryForObject(sql, rowMapper, partyId, accountEntryId);
			String sql1 = "SELECT `accountEntry` FROM `account` WHERE `accountEntryId`= ?";
			networth.setAccountEntry(jdbcTemplate.queryForObject(sql1, String.class, networth.getAccountEntryId()));
			String sql2 = "SELECT `accountType` FROM `accounttype` WHERE `accountTypeId`= ?";
			networth.setAccountType(jdbcTemplate.queryForObject(sql2, String.class, networth.getAccountTypeId()));
			return networth;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public AccountType fetchAccountTypeByTypeId(long accountTypeId) {
		try {
			String sql = "SELECT * FROM `accounttype` WHERE `accountTypeId`=?";
			RowMapper<AccountType> rowMapper = new BeanPropertyRowMapper<AccountType>(AccountType.class);
			AccountType accountType = jdbcTemplate.queryForObject(sql, rowMapper, accountTypeId);
			return accountType;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public CashFlow fetchCashFlowByPartyIdAndItemId(long partyId, long cashFlowItemId) {
		try {
			String sql = "SELECT * FROM `cashflow` WHERE `partyId`=? AND `cashFlowItemId`=?";
			RowMapper<CashFlow> rowMapper = new BeanPropertyRowMapper<CashFlow>(CashFlow.class);
			CashFlow cashFlow = jdbcTemplate.queryForObject(sql, rowMapper, partyId, cashFlowItemId);
			String sql1 = "SELECT `cashFlowItem` FROM `cashflowitem` WHERE `cashFlowItemId`= ?";
			cashFlow.setCashFlowItem(jdbcTemplate.queryForObject(sql1, String.class, cashFlow.getCashFlowItemId()));
			String sql2 = "SELECT `cashFlowItemType` FROM `cashflowitemtype` WHERE `cashFlowItemTypeId`= ?";
			cashFlow.setCashFlowItemType(
					jdbcTemplate.queryForObject(sql2, String.class, cashFlow.getCashFlowItemTypeId()));
			return cashFlow;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public CashFlowItemType fetchCashFlowItemTypeByTypeId(long cashFlowItemTypeId) {
		try {
			String sql = "SELECT * FROM `cashflowitemtype` WHERE `cashFlowItemTypeId`=?";
			RowMapper<CashFlowItemType> rowMapper = new BeanPropertyRowMapper<CashFlowItemType>(CashFlowItemType.class);
			CashFlowItemType cashFlowItemType = jdbcTemplate.queryForObject(sql, rowMapper, cashFlowItemTypeId);
			return cashFlowItemType;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public EmiCalculator fetchEmiCalculatorByPartyId(long partyId) {
		try {
			String sql = "SELECT * FROM `emicalculator` WHERE `partyId`= ?";
			RowMapper<EmiCalculator> rowMapper = new BeanPropertyRowMapper<EmiCalculator>(EmiCalculator.class);
			return jdbcTemplate.queryForObject(sql, rowMapper, partyId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public EmiCapacity fetchEmiCapacityByPartyId(long partyId) {
		try {
			String sql = "SELECT * FROM `emicapacity` WHERE `partyId`= ?";
			RowMapper<EmiCapacity> rowMapper = new BeanPropertyRowMapper<EmiCapacity>(EmiCapacity.class);
			return jdbcTemplate.queryForObject(sql, rowMapper, partyId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<PartialPayment> fetchPartialPaymentByPartyId(long partyId) {
		try {
			String sql = "SELECT * FROM `partialpayment` WHERE `partyId`= ?";
			RowMapper<PartialPayment> rowMapper = new BeanPropertyRowMapper<PartialPayment>(PartialPayment.class);
			return jdbcTemplate.query(sql, rowMapper, partyId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<InterestChange> fetchInterestChangeByPartyId(long partyId) {
		try {
			String sql = "SELECT * FROM `interestchange` WHERE `partyId`= ?";
			RowMapper<InterestChange> rowMapper = new BeanPropertyRowMapper<InterestChange>(InterestChange.class);
			return jdbcTemplate.query(sql, rowMapper, partyId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<EmiChange> fetchEmiChangeByPartyId(long partyId) {
		try {
			String sql = "SELECT * FROM `emichange` WHERE `partyId`= ?";
			RowMapper<EmiChange> rowMapper = new BeanPropertyRowMapper<EmiChange>(EmiChange.class);
			return jdbcTemplate.query(sql, rowMapper, partyId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<EmiInterestChange> fetchEmiInterestChangeByPartyId(long partyId) {
		try {
			String sql = "SELECT * FROM `emiinterestchange` WHERE `partyId`= ?";
			RowMapper<EmiInterestChange> rowMapper = new BeanPropertyRowMapper<EmiInterestChange>(
					EmiInterestChange.class);
			return jdbcTemplate.query(sql, rowMapper, partyId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public InsuranceItem fetchInsuranceItemByPartyId(long partyId) {
		Insurance insurance = fetchInsuranceByPartyId(partyId);
		InsuranceItem insuranceItem = new InsuranceItem();
		insuranceItem.setInsuranceId(insurance.getInsuranceId());
		insuranceItem.setPartyId(insurance.getPartyId());
		InsuranceAmountItem annualIncome = new InsuranceAmountItem();
		String sql1 = "SELECT `value` FROM `insuranceitem` WHERE `insuranceItem`= ?";
		annualIncome.setLabel(jdbcTemplate.queryForObject(sql1, String.class, "annualIncome"));
		annualIncome.setValue(insurance.getAnnualIncome());
		insuranceItem.setAnnualIncome(annualIncome);
		InsuranceStringItem stability = new InsuranceStringItem();
		String sql2 = "SELECT `value` FROM `insuranceitem` WHERE `insuranceItem`= ?";
		stability.setLabel(jdbcTemplate.queryForObject(sql2, String.class, "stability"));
		stability.setValue(insurance.getStability());
		insuranceItem.setStability(stability);
		InsuranceStringItem predictability = new InsuranceStringItem();
		String sql3 = "SELECT `value` FROM `insuranceitem` WHERE `insuranceItem`= ?";
		predictability.setLabel(jdbcTemplate.queryForObject(sql3, String.class, "predictability"));
		predictability.setValue(insurance.getPredictability());
		insuranceItem.setPredictability(predictability);
		InsuranceAmountItem requiredInsurance = new InsuranceAmountItem();
		String sql4 = "SELECT `value` FROM `insuranceitem` WHERE `insuranceItem`= ?";
		requiredInsurance.setLabel(jdbcTemplate.queryForObject(sql4, String.class, "requiredInsurance"));
		requiredInsurance.setValue(insurance.getRequiredInsurance());
		insuranceItem.setRequiredInsurance(requiredInsurance);
		InsuranceAmountItem existingInsurance = new InsuranceAmountItem();
		String sql5 = "SELECT `value` FROM `insuranceitem` WHERE `insuranceItem`= ?";
		existingInsurance.setLabel(jdbcTemplate.queryForObject(sql5, String.class, "existingInsurance"));
		existingInsurance.setValue(insurance.getExistingInsurance());
		insuranceItem.setExistingInsurance(existingInsurance);
		InsuranceAmountItem additionalInsurance = new InsuranceAmountItem();
		String sql6 = "SELECT `value` FROM `insuranceitem` WHERE `insuranceItem`= ?";
		additionalInsurance.setLabel(jdbcTemplate.queryForObject(sql6, String.class, "additionalInsurance"));
		additionalInsurance.setValue(insurance.getAdditionalInsurance());
		insuranceItem.setAdditionalInsurance(additionalInsurance);
		return insuranceItem;
	}

	@Override
	public void addPlanInfo(Plan plan) {
		String sql = "INSERT INTO `plan` (`partyId`,`parentPartyId`,`referenceId`,`name`,`age`,`selectedPlan`,`spouse`,`parents`,`children`,`grandParent`,`sibilings`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, plan.getPartyId(), plan.getParentPartyId(), plan.getReferenceId(), plan.getName(),
				plan.getAge(), plan.getSelectedPlan(), plan.getSpouse(), plan.getParents(), plan.getChildren(),
				plan.getGrandParent(), plan.getSibilings());
	}

	@Override
	public String fetchRoleBasedIdByPartyId(long partyId) {
		try {
			String sql1 = "SELECT `roleBasedId` FROM `party` WHERE `partyId`= ?";
			String refId = jdbcTemplate.queryForObject(sql1, String.class, partyId);
			return refId;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public String fetchRoleBasedIdByParentPartyId(long parentPartyId) {
		try {
			String sql1 = "SELECT `roleBasedId` FROM `party` WHERE `partyId`= ?";
			String refId = jdbcTemplate.queryForObject(sql1, String.class, parentPartyId);
			return refId;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Plan> fetchPlanByReferenceId(String id) {
		try {
			String sql = "SELECT * FROM `plan` WHERE `referenceId`= ?";
			RowMapper<Plan> rowMapper = new BeanPropertyRowMapper<Plan>(Plan.class);
			return jdbcTemplate.query(sql, rowMapper, id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void updateCashFlow(CashFlow cashFlow) {
		String sql = "UPDATE `cashflow` SET `budgetAmt`=? ,`actualAmt`=?,`date`=?, `cashFlowItemTypeId`=? WHERE `partyId`=? AND `cashFlowItemId`=?";
		jdbcTemplate.update(sql, cashFlow.getBudgetAmt(), cashFlow.getActualAmt(), cashFlow.getDate(),
				cashFlow.getCashFlowItemTypeId(), cashFlow.getPartyId(), cashFlow.getCashFlowItemId());
	}

	@Override
	public void removeCashFlowSummary(long partyId) {
		String sql = "DELETE FROM `cashflowsummary` WHERE `partyId`=?";
		jdbcTemplate.update(sql, partyId);
	}

	@Override
	public void updateNetworth(Networth networth) {
		String sql = "UPDATE `networth` SET `value`=? ,`futureValue`=?,`accountTypeId`=? WHERE `partyId`=? AND `accountEntryId`=?";
		jdbcTemplate.update(sql, networth.getValue(), networth.getFutureValue(), networth.getAccountTypeId(),
				networth.getPartyId(), networth.getAccountEntryId());
	}

	@Override
	public void removeNetworthSummary(long partyId) {
		String sql = "DELETE FROM `networthsummary` WHERE `partyId`=?";
		jdbcTemplate.update(sql, partyId);
	}

	@Override
	public void updatePriority(Priority priority) {
		String sql = "UPDATE `priority` SET `value`=? ,`timeline`=?,`urgencyId`=?, `priorityOrder`=? WHERE `partyId`=? AND `priorityItemId`=?";
		jdbcTemplate.update(sql, priority.getValue(), priority.getTimeLine(), priority.getUrgencyId(),
				priority.getPriorityOrder(), priority.getPartyId(), priority.getPriorityItemId());
	}

	@Override
	public void updateInsurance(Insurance insurance) {
		String sql = "UPDATE `insurance` SET `annualIncome`=? ,`stability`=?,`predictability`=?, `requiredInsurance`=? ,`existingInsurance`=?,`additionalInsurance`=? WHERE `partyId`=?";
		jdbcTemplate.update(sql, insurance.getAnnualIncome(), insurance.getStability(), insurance.getPredictability(),
				insurance.getRequiredInsurance(), insurance.getExistingInsurance(), insurance.getAdditionalInsurance(),
				insurance.getPartyId());
	}

	@Override
	public void updateRiskProfile(RiskProfile riskProfile) {
		String sql = "UPDATE `riskprofile` SET `questionId`=? ,`answerId`=? , `score`=? WHERE `partyId`=? AND `questionId`=?";
		jdbcTemplate.update(sql, riskProfile.getQuestionId(), riskProfile.getAnswerId(), riskProfile.getScore(),
				riskProfile.getPartyId(), riskProfile.getQuestionId());
	}

	@Override
	public void removeRiskSummary(long partyId) {
		String sql = "DELETE FROM `risksummary` WHERE `partyId`=?";
		jdbcTemplate.update(sql, partyId);
	}

	@Override
	public void updateEmiCalculator(EmiCalculator emiCalculator) {
		String sql = "UPDATE `emicalculator` SET `loanAmount`=? ,`tenure`=?, `interestRate`=?,`date`=? WHERE `partyId`=? ";
		jdbcTemplate.update(sql, emiCalculator.getLoanAmount(), emiCalculator.getTenure(),
				emiCalculator.getInterestRate(), emiCalculator.getDate(), emiCalculator.getPartyId());
	}

	@Override
	public void updateEmiCalculator(EmiCapacity emiCapacity) {
		String sql = "UPDATE `emicapacity` SET `currentAge`=? ,`retirementAge`=?, `stability`=?,`backUp`=?, `netFamilyIncome`=?, `existingEmi`=?, `houseHoldExpense`=?, `additionalIncome`=?, `interestRate`=? WHERE `partyId`=? ";
		jdbcTemplate.update(sql, emiCapacity.getCurrentAge(), emiCapacity.getRetirementAge(),
				emiCapacity.getStability(), emiCapacity.getBackUp(), emiCapacity.getNetFamilyIncome(),
				emiCapacity.getExistingEmi(), emiCapacity.getHouseHoldExpense(), emiCapacity.getAdditionalIncome(),
				emiCapacity.getInterestRate(), emiCapacity.getPartyId());
	}

	@Override
	public RiskProfile fetchRiskProfileByPartyIdAndQuestionId(long partyId, String questionId) {
		try {
			String sql = "SELECT * FROM `riskprofile` WHERE `partyId`=? AND `questionId`=?";
			RowMapper<RiskProfile> rowMapper = new BeanPropertyRowMapper<RiskProfile>(RiskProfile.class);
			RiskProfile riskProfile = jdbcTemplate.queryForObject(sql, rowMapper, partyId, questionId);
			return riskProfile;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<String> fetchQuestionIdFromRiskQuestionaire() {
		try {
			String sql = "SELECT DISTINCT `questionId` FROM `riskquestionaire`";
			List<String> questionId = jdbcTemplate.queryForList(sql, String.class);
			return questionId;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<RiskQuestionaire> fetchRiskQuestionaireByQuestionId(String questionId) {
		try {
			String sql = "SELECT * FROM `riskquestionaire` WHERE `questionId`= ?";
			RowMapper<RiskQuestionaire> rowMapper = new BeanPropertyRowMapper<RiskQuestionaire>(RiskQuestionaire.class);
			return jdbcTemplate.query(sql, rowMapper, questionId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	@Override
	public String fetchQuestionByQuestionId(String questionId) {
		try {
			String sql = "SELECT DISTINCT `question` FROM `riskquestionaire` WHERE `questionId`=?";
			String question = jdbcTemplate.queryForObject(sql, String.class, questionId);
			return question;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Goal fetchGoalByPartyIdAndGoalName(long partyId, String goalName) {
		try {
			String sql = "SELECT * FROM `goal` WHERE `partyId`=? AND `goalName`=?";
			RowMapper<Goal> rowMapper = new BeanPropertyRowMapper<Goal>(Goal.class);
			Goal goal = jdbcTemplate.queryForObject(sql, rowMapper, partyId, goalName);
			return goal;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void updateGoalInfo(Goal goal) {
		String sql = "UPDATE `goal` SET `tenure`=? ,`tenureType`=?, `goalAmount`=?,`inflationRate`=?, `currentAmount`=?, `growthRate`=?, `rateOfReturn`=?, `annualInvestmentRate`=?, `futureCost`=?,`futureValue`=?,`finalCorpus`=?,`monthlyInv`=?,`annualInv`=? WHERE `partyId`=? AND `goalName`=?";
		jdbcTemplate.update(sql, goal.getTenure(), goal.getTenureType(), goal.getGoalAmount(), goal.getInflationRate(),
				goal.getCurrentAmount(), goal.getGrowthRate(), goal.getRateOfReturn(), goal.getAnnualInvestmentRate(),
				goal.getFutureCost(), goal.getFutureValue(), goal.getFinalCorpus(), goal.getMonthlyInv(),
				goal.getAnnualInv(), goal.getPartyId(), goal.getGoalName());
	}

}
