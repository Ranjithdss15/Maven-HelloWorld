package com.sowisetech.calc.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:messages.properties")
@Component
public class CalcAppMessages {

	@Value("${no_record_found}")
	public String no_record_found;
	
	@Value("${value_empty}")
	public String value_empty;
	
	@Value("${value_is_not_alpha}")
	public String value_is_not_alpha;
	
	@Value("${value_is_not_numeric}")
	public String value_is_not_numeric;
	
	@Value("${tenuretype_error}")
	public String tenuretype_error;
	
	@Value("${date_error}")
	public String date_error;
	
	@Value("${value_is_not_alphanumeric}")
	public String value_is_not_alphanumeric;
	
	@Value("${fields_cannot_be_empty}")
	public String fields_cannot_be_empty;
	
	@Value("${goal_calculated_successfully}")
	public String goal_calculated_successfully;
	
	@Value("${insurance_calculated_successfully}")
	public String insurance_calculated_successfully;
	
	@Value("${stability_error}")
	public String stability_error;
	
	@Value("${predictabilty_error}")
	public String predictabilty_error;
	
	@Value("${cashflow_calculated_successfully}")
	public String cashflow_calculated_successfully;
	
	@Value("${networth_calculated_successfully}")
	public String networth_calculated_successfully;
	
	@Value("${riskprofile_added_successfully}")
	public String riskprofile_added_successfully;
	
	@Value("${priority_added_successfully}")
	public String priority_added_successfully;
	
	@Value("${amounttype_error}")
	public String amounttype_error;
	
	@Value("${value_calculated_successfully}")
	public String value_calculated_successfully;
	
	@Value("${rate_calculated_successfully}")
	public String rate_calculated_successfully;
	
	@Value("${month_year_error}")
	public String month_year_error;
	
	@Value("${capacity_stability_error}")
	public String capacity_stability_error;
	
	@Value("${capacity_backup_error}")
	public String capacity_backup_error;
	
	@Value("${interest_changed_successfully}")
	public String interest_changed_successfully;
	
	@Value("${future_date_error}")
	public String future_date_error;
	
	@Value("${plan_added_successfully}")
	public String plan_added_successfully;
	
	@Value("${success}")
	public String success;
	
	@Value("${error}")
	public String error;
	
	@Value("${tenure_calculated_successfully}")
	public String tenure_calculated_successfully;
	
	@Value("${emi_calculated_successfully}")
	public String emi_calculated_successfully;
	
	@Value("${partpayment_calculated_successfully}")
	public String partpayment_calculated_successfully;
	
	@Value("${int_change_calculated_successfully}")
	public String int_change_calculated_successfully;
	
	@Value("${investment_type_error}")
	public String investment_type_error;

	public String getNo_record_found() {
		return no_record_found;
	}

	public void setNo_record_found(String no_record_found) {
		this.no_record_found = no_record_found;
	}

	public String getValue_empty() {
		return value_empty;
	}

	public void setValue_empty(String value_empty) {
		this.value_empty = value_empty;
	}

	public String getValue_is_not_alpha() {
		return value_is_not_alpha;
	}

	public void setValue_is_not_alpha(String value_is_not_alpha) {
		this.value_is_not_alpha = value_is_not_alpha;
	}

	public String getValue_is_not_numeric() {
		return value_is_not_numeric;
	}

	public void setValue_is_not_numeric(String value_is_not_numeric) {
		this.value_is_not_numeric = value_is_not_numeric;
	}

	public String getTenuretype_error() {
		return tenuretype_error;
	}

	public void setTenuretype_error(String tenuretype_error) {
		this.tenuretype_error = tenuretype_error;
	}

	public String getDate_error() {
		return date_error;
	}

	public void setDate_error(String date_error) {
		this.date_error = date_error;
	}

	public String getValue_is_not_alphanumeric() {
		return value_is_not_alphanumeric;
	}

	public void setValue_is_not_alphanumeric(String value_is_not_alphanumeric) {
		this.value_is_not_alphanumeric = value_is_not_alphanumeric;
	}

	public String getFields_cannot_be_empty() {
		return fields_cannot_be_empty;
	}

	public void setFields_cannot_be_empty(String fields_cannot_be_empty) {
		this.fields_cannot_be_empty = fields_cannot_be_empty;
	}

	public String getGoal_calculated_successfully() {
		return goal_calculated_successfully;
	}

	public void setGoal_calculated_successfully(String goal_calculated_successfully) {
		this.goal_calculated_successfully = goal_calculated_successfully;
	}

	public String getInsurance_calculated_successfully() {
		return insurance_calculated_successfully;
	}

	public void setInsurance_calculated_successfully(String insurance_calculated_successfully) {
		this.insurance_calculated_successfully = insurance_calculated_successfully;
	}

	public String getStability_error() {
		return stability_error;
	}

	public void setStability_error(String stability_error) {
		this.stability_error = stability_error;
	}

	public String getPredictabilty_error() {
		return predictabilty_error;
	}

	public void setPredictabilty_error(String predictabilty_error) {
		this.predictabilty_error = predictabilty_error;
	}

	public String getCashflow_calculated_successfully() {
		return cashflow_calculated_successfully;
	}

	public void setCashflow_calculated_successfully(String cashflow_calculated_successfully) {
		this.cashflow_calculated_successfully = cashflow_calculated_successfully;
	}

	public String getNetworth_calculated_successfully() {
		return networth_calculated_successfully;
	}

	public void setNetworth_calculated_successfully(String networth_calculated_successfully) {
		this.networth_calculated_successfully = networth_calculated_successfully;
	}

	public String getRiskprofile_added_successfully() {
		return riskprofile_added_successfully;
	}

	public void setRiskprofile_added_successfully(String riskprofile_added_successfully) {
		this.riskprofile_added_successfully = riskprofile_added_successfully;
	}

	public String getPriority_added_successfully() {
		return priority_added_successfully;
	}

	public void setPriority_added_successfully(String priority_added_successfully) {
		this.priority_added_successfully = priority_added_successfully;
	}

	public String getAmounttype_error() {
		return amounttype_error;
	}

	public void setAmounttype_error(String amounttype_error) {
		this.amounttype_error = amounttype_error;
	}

	public String getValue_calculated_successfully() {
		return value_calculated_successfully;
	}

	public void setValue_calculated_successfully(String value_calculated_successfully) {
		this.value_calculated_successfully = value_calculated_successfully;
	}

	public String getRate_calculated_successfully() {
		return rate_calculated_successfully;
	}

	public void setRate_calculated_successfully(String rate_calculated_successfully) {
		this.rate_calculated_successfully = rate_calculated_successfully;
	}

	public String getMonth_year_error() {
		return month_year_error;
	}

	public void setMonth_year_error(String month_year_error) {
		this.month_year_error = month_year_error;
	}

	public String getCapacity_stability_error() {
		return capacity_stability_error;
	}

	public void setCapacity_stability_error(String capacity_stability_error) {
		this.capacity_stability_error = capacity_stability_error;
	}

	public String getCapacity_backup_error() {
		return capacity_backup_error;
	}

	public void setCapacity_backup_error(String capacity_backup_error) {
		this.capacity_backup_error = capacity_backup_error;
	}

	public String getInterest_changed_successfully() {
		return interest_changed_successfully;
	}

	public void setInterest_changed_successfully(String interest_changed_successfully) {
		this.interest_changed_successfully = interest_changed_successfully;
	}

	public String getFuture_date_error() {
		return future_date_error;
	}

	public void setFuture_date_error(String future_date_error) {
		this.future_date_error = future_date_error;
	}

	public String getPlan_added_successfully() {
		return plan_added_successfully;
	}

	public void setPlan_added_successfully(String plan_added_successfully) {
		this.plan_added_successfully = plan_added_successfully;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getTenure_calculated_successfully() {
		return tenure_calculated_successfully;
	}

	public void setTenure_calculated_successfully(String tenure_calculated_successfully) {
		this.tenure_calculated_successfully = tenure_calculated_successfully;
	}

	public String getEmi_calculated_successfully() {
		return emi_calculated_successfully;
	}

	public void setEmi_calculated_successfully(String emi_calculated_successfully) {
		this.emi_calculated_successfully = emi_calculated_successfully;
	}

	public String getPartpayment_calculated_successfully() {
		return partpayment_calculated_successfully;
	}

	public void setPartpayment_calculated_successfully(String partpayment_calculated_successfully) {
		this.partpayment_calculated_successfully = partpayment_calculated_successfully;
	}

	public String getInt_change_calculated_successfully() {
		return int_change_calculated_successfully;
	}

	public void setInt_change_calculated_successfully(String int_change_calculated_successfully) {
		this.int_change_calculated_successfully = int_change_calculated_successfully;
	}

	public String getInvestment_type_error() {
		return investment_type_error;
	}

	public void setInvestment_type_error(String investment_type_error) {
		this.investment_type_error = investment_type_error;
	}
	
	
	
	
	
	
	
	
	
	
	


}
