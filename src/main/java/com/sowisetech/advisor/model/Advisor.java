package com.sowisetech.advisor.model;

import java.sql.Timestamp;
import java.util.List;

public class Advisor {

	private String advId;
	private String name;
	private String designation;
	private String emailId;
	private String phoneNumber;
	private String password;
	private long partyStatusId;
	private String delete_flag;
	private int advType;

	private String displayName;
	private String dob;
	private String gender;
	private String panNumber;
	private String address1;
	private String address2;
	private String state;
	private String city;
	private String pincode;
	private String aboutme;

	private Timestamp created;
	private Timestamp updated;

	public int getAdvType() {
		return advType;
	}

	public void setAdvType(int advType) {
		this.advType = advType;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getAboutme() {
		return aboutme;
	}

	public void setAboutme(String aboutme) {
		this.aboutme = aboutme;
	}

	public String getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(String delete_flag) {
		this.delete_flag = delete_flag;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	private List<Award> awards;
	private List<Certificate> certificates;
	private List<Education> educations;
	private List<Experience> experiences;
	private List<AdvProduct> advProducts;
	private List<AdvBrandInfo> advBrandInfo;
	private List<AdvBrandRank> advBrandRank;


	public List<AdvBrandInfo> getAdvBrandInfo() {
		return advBrandInfo;
	}

	public void setAdvBrandInfo(List<AdvBrandInfo> advBrandInfo) {
		this.advBrandInfo = advBrandInfo;
	}

	public List<AdvBrandRank> getAdvBrandRank() {
		return advBrandRank;
	}

	public void setAdvBrandRank(List<AdvBrandRank> advBrandRank) {
		this.advBrandRank = advBrandRank;
	}

	public List<Certificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<Certificate> certificates) {
		this.certificates = certificates;
	}

	private List<AdvVideo> videos;

	public List<AdvVideo> getVideos() {
		return videos;
	}

	public void setVideos(List<AdvVideo> videos) {
		this.videos = videos;
	}

	public List<AdvProduct> getAdvProducts() {
		return advProducts;
	}

	public void setAdvProducts(List<AdvProduct> products) {
		this.advProducts = products;
	}

	public List<Education> getEducations() {
		return educations;
	}

	public void setEducations(List<Education> educations) {
		this.educations = educations;
	}

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	public long getPartyStatusId() {
		return partyStatusId;
	}

	public void setPartyStatusId(long partyStatusId) {
		this.partyStatusId = partyStatusId;
	}

	public String getAdvId() {
		return advId;
	}

	public void setAdvId(String advId) {
		this.advId = advId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Award> getAwards() {
		return awards;
	}

	public void setAwards(List<Award> awards) {
		this.awards = awards;
	}

	public void addAward(Award award) {
		awards.add(award);
	}

}
