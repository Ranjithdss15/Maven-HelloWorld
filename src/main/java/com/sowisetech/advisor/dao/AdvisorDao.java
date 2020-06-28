package com.sowisetech.advisor.dao;

import java.util.List;
import java.util.Map.Entry;

import com.sowisetech.advisor.model.Advisor;
import com.sowisetech.advisor.model.Award;
import com.sowisetech.advisor.model.Brand;
import com.sowisetech.advisor.model.Category;
import com.sowisetech.advisor.model.CategoryType;
import com.sowisetech.advisor.model.Certificate;
import com.sowisetech.advisor.model.Education;
import com.sowisetech.advisor.model.Experience;
import com.sowisetech.advisor.model.ForumCategory;
import com.sowisetech.advisor.model.ForumStatus;
import com.sowisetech.advisor.model.ForumSubCategory;
import com.sowisetech.advisor.model.GoalsServed;
import com.sowisetech.advisor.model.License;
import com.sowisetech.advisor.model.PartyStatus;
import com.sowisetech.advisor.model.Product;
import com.sowisetech.advisor.model.Remuneration;
import com.sowisetech.advisor.model.RiskQuestionaire;
import com.sowisetech.advisor.model.Role;
import com.sowisetech.advisor.model.Service;
import com.sowisetech.advisor.model.SpecialisedSkills;
import com.sowisetech.advisor.model.State;
import com.sowisetech.advisor.model.AdvBrandInfo;
import com.sowisetech.advisor.model.AdvBrandRank;
import com.sowisetech.advisor.model.AdvProduct;

public interface AdvisorDao {

	void advSignup(Advisor advisor);

	Advisor fetch(String advId);

	List<Advisor> fetchAll();

	void delete(String advId);

	void update(String advId, Advisor adv);

	void addAdvProfessionalInfo(String advId, Advisor adv);

	void addAdvProductInfo(String advId, AdvProduct advProduct);

	Award fetchAward(long awardId);

	Experience fetchExperience(long expId);

	Education fetchEducation(long eduId);

	void removeAdvAward(long awardId, String advId);

	void removeAdvEducation(long eduId, String advId);

	void removeAdvExperience(long expId, String advId);

	Advisor fetchAdvisorByEmailId(String emailId);

	String generateId();

	void addAdvPersonalInfo(String advId, Advisor adv);

	boolean checkForPasswordMatch(String advId, String currentPassword);

	void changeAdvPassword(String advId, String newPassword);

	void addAdvVideo(String advId, Advisor adv);

	AdvProduct fetchAdvProduct(long advProdId);

	void modifyAdvisorProduct(AdvProduct advProduct, String advId);

	String encrypt(String rawPassword);

	String decrypt(String encodedPassword);

	Certificate fetchCertificate(long certificateId);

	void removeAdvCertificate(long certificateId, String advId);

	List<Category> fetchCategoryList();

	List<CategoryType> fetchCategoryTypeList();

	List<ForumCategory> fetchForumCategoryList();

	List<RiskQuestionaire> fetchAllRiskQuestionaire();

	List<GoalsServed> fetchGoalsServedList();

	List<Product> fetchProductList();

	List<SpecialisedSkills> fetchSpecialisedSkills();

	List<Role> fetchRoleList();

	List<ForumSubCategory> fetchForumSubCategoryList();

	List<ForumStatus> fetchForumStatusList();

	List<PartyStatus> fetchPartyStatusList();

	List<Service> fetchServiceList();

	List<Brand> fetchBrandList();

	List<License> fetchLicenseList();

	List<Remuneration> fetchRemunerationList();

	void addAdvBrandInfo(String advId, AdvBrandInfo advBrandInfo);

	List<AdvBrandInfo> fetchAdvBrandInfoByAdvIdAndProdId(String advId, long prodId);

	List<Long> fetchPriorityByBrandIdAndAdvId(String advId, long prodId, long brandId);

	AdvBrandRank fetchAdvBrandRank(String advId, long prodId, int rank);

	void addAdvBrandAndRank(long brand, int rank, String advId, long prodId);

	void updateBrandAndRank(long brand, int rank, String advId, long prodId);

	List<AdvProduct> fetchAdvProductByAdvId(String advId);

	void removeAdvProduct(long advProdId, String advId);

	void removeAdvBrandInfo(long prodId, long serviceId, String advId);

	void removeFromBrandRank(String advId, long prodId);

	AdvProduct fetchAdvProductByAdvIdAndAdvProdId(String advId, long advProdId);

	void removeAdvBrandInfoByAdvId(String advId);

	void removeAdvBrandRankByAdvId(String advId);

	List<Product> fetchAllServiceAndBrand();

	List<Award> fetchAwardByadvId(String advId);

	List<Certificate> fetchCertificateByadvId(String advId);

	List<Experience> fetchExperienceByadvId(String advId);

	List<Education> fetchEducationByadvId(String advId);

	void modifyAdvisorAward(long awardId, Award award, String advId);

	void modifyAdvisorCertificate(long certificateId, Certificate certificate, String advId);

	void modifyAdvisorExperience(long expId, Experience experience, String advId);

	void modifyAdvisorEducation(long eduId, Education education, String advId);

	void addAdvAwardInfo(String advId, Award award);

	void addAdvCertificateInfo(String advId, Certificate certificate);

	void addAdvExperienceInfo(String advId, Experience experience);

	void addAdvEducationInfo(String advId, Education education);

	Award fetchAdvAwardByAdvIdAndAwardId(long awardId, String advId);

	Certificate fetchAdvCertificateByAdvIdAndCertificateId(long certificateId, String advId);

	Education fetchAdvEducationByAdvIdAndEduId(long eduId, String advId);

	Experience fetchAdvExperienceByAdvIdAndExpId(long expId, String advId);

	void removeAwardByAdvId(String advId);

	void removeCertificateByAdvId(String advId);

	void removeExperienceByAdvId(String advId);

	void removeEducationByAdvId(String advId);

	List<State> fetchAllStateCityPincode();

	List<AdvBrandRank> fetchAdvBrandRankByAdvId(String advId);

	long fetchPartyIdByRoleBasedId(String advId);


	// List<AdvBrandInfo> fetchAdvBrandInfoByProdIdAndServiceId(long prodId,
	// long serviceId, String advId);

	// List<Award> fetchAwardByadvId(long advid);
	//
	// List<Education> fetchEducationByadvId(long advid);
	//
	// List<Experience> fetchExperienceByadvId(long advid);

}
