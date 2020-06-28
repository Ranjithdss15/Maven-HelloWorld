package com.sowisetech.advisor.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:appmessages.properties")
@Component
public class AppMessages {

	@Value("${no_record_found}")
	public String no_record_found;

	@Value("${advisor_added_successfully}")
	public String advisor_added_successfully;

	@Value("${advisor_info_added_successfully}")
	public String advisor_info_added_successfully;

	@Value("${advisor_detail_empty}")
	public String advisor_detail_empty;

	@Value("${advisor_updated_successfully}")
	public String advisor_updated_successfully;

	@Value("${advisor_deleted_successfully}")
	public String advisor_deleted_successfully;

	@Value("${json_request_error}")
	public String json_request_error;

	@Value("${value_null_or_empty}")
	public String value_null_or_empty;

	@Value("${value_not_number}")
	public String value_not_number;

	@Value("${value_not_positive}")
	public String value_not_positive;

	@Value("${value_invalid_percent}")
	public String value_invalid_percent;

	@Value("${value_is_not_alpha}")
	public String value_is_not_alpha;

	@Value("${value_is_not_numeric}")
	public String value_is_not_numeric;

	@Value("${value_is_not_emailid}")
	public String value_is_not_emailid;

	@Value("${phonenumber_length_error}")
	public String phonenumber_length_error;

	@Value("${pincode_length_error}")
	public String pincode_length_error;

	@Value("${password_format_error}")
	public String password_format_error;

	@Value("${dob_error}")
	public String dob_error;

	@Value("${gender_error}")
	public String gender_error;

	@Value("${pan_error}")
	public String pan_error;

	@Value("${text_format_error}")
	public String text_format_error;

	@Value("${expertise_level_error}")
	public String expertise_level_error;

	@Value("${remuneration_error}")
	public String remuneration_error;

	@Value("${validtill_date_error}")
	public String validtill_date_error;

	@Value("${legalno_error}")
	public String legalno_error;

	@Value("${aboutme_error}")
	public String aboutme_error;

	@Value("${year_error}")
	public String year_error;

	@Value("${value_is_not_image}")
	public String value_is_not_image;

	@Value("${degree_error}")
	public String degree_error;

	@Value("${from_date_error}")
	public String from_date_error;

	@Value("${to_date_error}")
	public String to_date_error;

	@Value("${fields_cannot_be_empty}")
	public String fields_cannot_be_empty;

	@Value("${advisor_already_present}")
	public String advisor_already_present;

	@Value("${from_to_error}")
	public String from_to_error;

	@Value("${password_changed_successfully}")
	public String password_changed_successfully;

	@Value("${incorrect_password}")
	public String incorrect_password;

	@Value("${value_is_not_video}")
	public String value_is_not_video;

	@Value("${certificate_year_error}")
	public String certificate_year_error;

	@Value("${advproduct_not_found}")
	public String advproduct_not_found;

	@Value("${award_not_found}")
	public String award_not_found;

	@Value("${certificate_not_found}")
	public String certificate_not_found;

	@Value("${education_not_found}")
	public String education_not_found;

	@Value("${experience_not_found}")
	public String experience_not_found;

	@Value("${brand_should_be_added}")
	public String brand_should_be_added;

	public String getNo_record_found() {
		return no_record_found;
	}

	public void setNo_record_found(String no_record_found) {
		this.no_record_found = no_record_found;
	}

	public String getAdvisor_added_successfully() {
		return advisor_added_successfully;
	}

	public void setAdvisor_added_successfully(String advisor_added_successfully) {
		this.advisor_added_successfully = advisor_added_successfully;
	}

	public String getAdvisor_info_added_successfully() {
		return advisor_info_added_successfully;
	}

	public void setAdvisor_info_added_successfully(String advisor_info_added_successfully) {
		this.advisor_info_added_successfully = advisor_info_added_successfully;
	}

	public String getAdvisor_detail_empty() {
		return advisor_detail_empty;
	}

	public void setAdvisor_detail_empty(String advisor_detail_empty) {
		this.advisor_detail_empty = advisor_detail_empty;
	}

	public String getAdvisor_updated_successfully() {
		return advisor_updated_successfully;
	}

	public void setAdvisor_updated_successfully(String advisor_updated_successfully) {
		this.advisor_updated_successfully = advisor_updated_successfully;
	}

	public String getAdvisor_deleted_successfully() {
		return advisor_deleted_successfully;
	}

	public void setAdvisor_deleted_successfully(String advisor_deleted_successfully) {
		this.advisor_deleted_successfully = advisor_deleted_successfully;
	}

	public String getJson_request_error() {
		return json_request_error;
	}

	public void setJson_request_error(String json_request_error) {
		this.json_request_error = json_request_error;
	}

	public String getValue_null_or_empty() {
		return value_null_or_empty;
	}

	public void setValue_null_or_empty(String value_null_or_empty) {
		this.value_null_or_empty = value_null_or_empty;
	}

	public String getValue_not_number() {
		return value_not_number;
	}

	public void setValue_not_number(String value_not_number) {
		this.value_not_number = value_not_number;
	}

	public String getValue_not_positive() {
		return value_not_positive;
	}

	public void setValue_not_positive(String value_not_positive) {
		this.value_not_positive = value_not_positive;
	}

	public String getValue_invalid_percent() {
		return value_invalid_percent;
	}

	public void setValue_invalid_percent(String value_invalid_percent) {
		this.value_invalid_percent = value_invalid_percent;
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

	public String getValue_is_not_emailid() {
		return value_is_not_emailid;
	}

	public void setValue_is_not_emailid(String value_is_not_emailid) {
		this.value_is_not_emailid = value_is_not_emailid;
	}

	public String getPhonenumber_length_error() {
		return phonenumber_length_error;
	}

	public void setPhonenumber_length_error(String phonenumber_length_error) {
		this.phonenumber_length_error = phonenumber_length_error;
	}

	public String getPincode_length_error() {
		return pincode_length_error;
	}

	public void setPincode_length_error(String pincode_length_error) {
		this.pincode_length_error = pincode_length_error;
	}

	public String getPassword_format_error() {
		return password_format_error;
	}

	public void setPassword_format_error(String password_format_error) {
		this.password_format_error = password_format_error;
	}

	public String getDob_error() {
		return dob_error;
	}

	public void setDob_error(String dob_error) {
		this.dob_error = dob_error;
	}

	public String getGender_error() {
		return gender_error;
	}

	public void setGender_error(String gender_error) {
		this.gender_error = gender_error;
	}

	public String getPan_error() {
		return pan_error;
	}

	public void setPan_error(String pan_error) {
		this.pan_error = pan_error;
	}

	public String getText_format_error() {
		return text_format_error;
	}

	public void setText_format_error(String text_format_error) {
		this.text_format_error = text_format_error;
	}

	public String getExpertise_level_error() {
		return expertise_level_error;
	}

	public void setExpertise_level_error(String expertise_level_error) {
		this.expertise_level_error = expertise_level_error;
	}

	public String getRemuneration_error() {
		return remuneration_error;
	}

	public void setRemuneration_error(String remuneration_error) {
		this.remuneration_error = remuneration_error;
	}

	public String getValidtill_date_error() {
		return validtill_date_error;
	}

	public void setValidtill_date_error(String validtill_date_error) {
		this.validtill_date_error = validtill_date_error;
	}

	public String getLegalno_error() {
		return legalno_error;
	}

	public void setLegalno_error(String legalno_error) {
		this.legalno_error = legalno_error;
	}

	public String getAboutme_error() {
		return aboutme_error;
	}

	public void setAboutme_error(String aboutme_error) {
		this.aboutme_error = aboutme_error;
	}

	public String getYear_error() {
		return year_error;
	}

	public void setYear_error(String year_error) {
		this.year_error = year_error;
	}

	public String getValue_is_not_image() {
		return value_is_not_image;
	}

	public void setValue_is_not_image(String value_is_not_image) {
		this.value_is_not_image = value_is_not_image;
	}

	public String getDegree_error() {
		return degree_error;
	}

	public void setDegree_error(String degree_error) {
		this.degree_error = degree_error;
	}

	public String getFrom_date_error() {
		return from_date_error;
	}

	public void setFrom_date_error(String from_date_error) {
		this.from_date_error = from_date_error;
	}

	public String getTo_date_error() {
		return to_date_error;
	}

	public void setTo_date_error(String to_date_error) {
		this.to_date_error = to_date_error;
	}

	public String getFields_cannot_be_empty() {
		return fields_cannot_be_empty;
	}

	public void setFields_cannot_be_empty(String fields_cannot_be_empty) {
		this.fields_cannot_be_empty = fields_cannot_be_empty;
	}

	public String getAdvisor_already_present() {
		return advisor_already_present;
	}

	public void setAdvisor_already_present(String advisor_already_present) {
		this.advisor_already_present = advisor_already_present;
	}

	public String getFrom_to_error() {
		return from_to_error;
	}

	public void setFrom_to_error(String from_to_error) {
		this.from_to_error = from_to_error;
	}

	public String getPassword_changed_successfully() {
		return password_changed_successfully;
	}

	public void setPassword_changed_successfully(String password_changed_successfully) {
		this.password_changed_successfully = password_changed_successfully;
	}

	public String getIncorrect_password() {
		return incorrect_password;
	}

	public void setIncorrect_password(String incorrect_password) {
		this.incorrect_password = incorrect_password;
	}

	public String getValue_is_not_video() {
		return value_is_not_video;
	}

	public void setValue_is_not_video(String value_is_not_video) {
		this.value_is_not_video = value_is_not_video;
	}

	public String getCertificate_year_error() {
		return certificate_year_error;
	}

	public void setCertificate_year_error(String certificate_year_error) {
		this.certificate_year_error = certificate_year_error;
	}

	public String getAdvproduct_not_found() {
		return advproduct_not_found;
	}

	public void setAdvproduct_not_found(String advproduct_not_found) {
		this.advproduct_not_found = advproduct_not_found;
	}

	public String getAward_not_found() {
		return award_not_found;
	}

	public void setAward_not_found(String award_not_found) {
		this.award_not_found = award_not_found;
	}

	public String getCertificate_not_found() {
		return certificate_not_found;
	}

	public void setCertificate_not_found(String certificate_not_found) {
		this.certificate_not_found = certificate_not_found;
	}

	public String getEducation_not_found() {
		return education_not_found;
	}

	public void setEducation_not_found(String education_not_found) {
		this.education_not_found = education_not_found;
	}

	public String getExperience_not_found() {
		return experience_not_found;
	}

	public void setExperience_not_found(String experience_not_found) {
		this.experience_not_found = experience_not_found;
	}

	public String getBrand_should_be_added() {
		return brand_should_be_added;
	}

	public void setBrand_should_be_added(String brand_should_be_added) {
		this.brand_should_be_added = brand_should_be_added;
	}

}
