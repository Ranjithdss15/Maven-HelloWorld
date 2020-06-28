package com.sowisetech.advisor.service;

import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sowisetech.advisor.dao.AdvisorDao;
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
import com.sowisetech.advisor.model.SpecialisedSkills;
import com.sowisetech.advisor.model.State;
import com.sowisetech.advisor.model.AdvBrandInfo;
import com.sowisetech.advisor.model.AdvBrandRank;
import com.sowisetech.advisor.model.AdvProduct;

@Transactional(readOnly = true)
@Service("AdvisorService")
public class AdvisorServiceImpl implements AdvisorService {

	@Autowired
	private AdvisorDao advisorDao;

	@Transactional
	public void advSignup(Advisor advisor) {
		advisorDao.advSignup(advisor);
	}

	@Transactional
	public Advisor fetchByAdvisorId(String advId) {
		return advisorDao.fetch(advId);
	}

	@Transactional
	public List<Advisor> fetchAdvisorList() {
		return advisorDao.fetchAll();
	}

	@Transactional
	public void removeAdvisor(String advId) {
		advisorDao.delete(advId);
	}

	@Transactional
	public void modifyAdvisor(String advId, Advisor adv) {
		advisorDao.update(advId, adv);
	}

	@Transactional
	public void addAdvProfessionalInfo(String advId, Advisor adv) {
		advisorDao.addAdvProfessionalInfo(advId, adv);
	}

	@Transactional
	public void addAdvProductInfo(String advId, AdvProduct advProduct) {
		advisorDao.addAdvProductInfo(advId, advProduct);
	}

	@Transactional
	public Award fetchAward(long awardId) {
		return advisorDao.fetchAward(awardId);
	}

	@Transactional
	public Certificate fetchCertificate(long certificateId) {
		return advisorDao.fetchCertificate(certificateId);
	}

	@Transactional
	public Experience fetchExperience(long expId) {
		return advisorDao.fetchExperience(expId);
	}

	@Transactional
	public Education fetchEducation(long eduId) {
		return advisorDao.fetchEducation(eduId);
	}

	@Transactional
	public void removeAdvAward(long awardId, String advId) {
		advisorDao.removeAdvAward(awardId, advId);
	}

	@Transactional
	public void removeAdvCertificate(long certificateId, String advId) {
		advisorDao.removeAdvCertificate(certificateId, advId);
	}

	@Transactional
	public void removeAdvEducation(long eduId, String advId) {
		advisorDao.removeAdvEducation(eduId, advId);
	}

	@Transactional
	public void removeAdvExperience(long expId, String advId) {
		advisorDao.removeAdvExperience(expId, advId);
	}

	@Transactional
	public Advisor fetchAdvisorByEmailId(String emailId) {
		return advisorDao.fetchAdvisorByEmailId(emailId);
	}

	@Transactional
	public String generateId() {
		return advisorDao.generateId();
	}

	@Transactional
	public void addAdvPersonalInfo(String advId, Advisor adv) {
		advisorDao.addAdvPersonalInfo(advId, adv);
	}

	@Transactional
	public boolean checkForPasswordMatch(String advId, String currentPassword) {
		return advisorDao.checkForPasswordMatch(advId, currentPassword);
	}

	@Transactional
	public void changeAdvPassword(String advId, String newPassword) {
		advisorDao.changeAdvPassword(advId, newPassword);
	}

	@Transactional
	public void addAdvVideo(String advId, Advisor adv) {
		advisorDao.addAdvVideo(advId, adv);
	}

	@Transactional
	public AdvProduct fetchAdvProduct(long advProdId) {
		return advisorDao.fetchAdvProduct(advProdId);
	}

	@Transactional
	public void modifyAdvisorProduct(AdvProduct advProduct, String advId) {
		advisorDao.modifyAdvisorProduct(advProduct, advId);
	}

	@Transactional
	public String encrypt(String rawPassword) {
		return advisorDao.encrypt(rawPassword);
	}

	@Transactional
	public String decrypt(String encodedPassword) {
		return advisorDao.decrypt(encodedPassword);
	}

	@Transactional
	public List<Category> fetchCategoryList() {
		return advisorDao.fetchCategoryList();
	}

	@Transactional
	public List<CategoryType> fetchCategoryTypeList() {
		return advisorDao.fetchCategoryTypeList();
	}

	@Transactional
	public List<ForumCategory> fetchForumCategoryList() {
		return advisorDao.fetchForumCategoryList();
	}

	@Transactional
	public List<RiskQuestionaire> fetchRiskQuestionaireList() {
		return advisorDao.fetchAllRiskQuestionaire();
	}

	@Transactional
	public List<GoalsServed> fetchGoalsServedList() {
		return advisorDao.fetchGoalsServedList();
	}

	@Transactional
	public List<Product> fetchProductList() {
		return advisorDao.fetchProductList();
	}

	@Transactional
	public List<SpecialisedSkills> fetchSpecialisedSkills() {
		return advisorDao.fetchSpecialisedSkills();
	}

	@Transactional
	public List<Role> fetchRoleList() {
		return advisorDao.fetchRoleList();
	}

	@Transactional
	public List<ForumSubCategory> fetchForumSubCategoryList() {
		return advisorDao.fetchForumSubCategoryList();
	}

	@Transactional
	public List<ForumStatus> fetchForumStatusList() {
		return advisorDao.fetchForumStatusList();
	}

	@Transactional
	public List<PartyStatus> fetchPartyStatusList() {
		return advisorDao.fetchPartyStatusList();
	}

	@Transactional
	public List<com.sowisetech.advisor.model.Service> fetchServiceList() {
		return advisorDao.fetchServiceList();
	}

	@Transactional
	public List<Brand> fetchBrandList() {
		return advisorDao.fetchBrandList();
	}

	@Transactional
	public List<License> fetchLicenseList() {
		return advisorDao.fetchLicenseList();
	}

	@Transactional
	public List<Remuneration> fetchRemunerationList() {
		return advisorDao.fetchRemunerationList();
	}

	@Transactional
	public void addAdvBrandInfo(String advId, AdvBrandInfo advBrandInfo) {
		advisorDao.addAdvBrandInfo(advId, advBrandInfo);
	}

	@Transactional
	public List<AdvBrandInfo> fetchAdvBrandInfoByAdvIdAndProdId(String advId, long prodId) {
		return advisorDao.fetchAdvBrandInfoByAdvIdAndProdId(advId, prodId);
	}

	@Transactional
	public List<Long> fetchPriorityByBrandIdAndAdvId(String advId, long prodId, long brandId) {
		return advisorDao.fetchPriorityByBrandIdAndAdvId(advId, prodId, brandId);
	}

	@Transactional
	public AdvBrandRank fetchAdvBrandRank(String advId, long prodId, int rank) {
		return advisorDao.fetchAdvBrandRank(advId, prodId, rank);
	}

	@Transactional
	public void addAdvBrandAndRank(long brand, int rank, String advId, long prodId) {
		advisorDao.addAdvBrandAndRank(brand, rank, advId, prodId);
	}

	@Transactional
	public void updateBrandAndRank(long brand, int rank, String advId, long prodId) {
		advisorDao.updateBrandAndRank(brand, rank, advId, prodId);
	}

	@Transactional
	public List<AdvProduct> fetchAdvProductByAdvId(String advId) {
		return advisorDao.fetchAdvProductByAdvId(advId);
	}

	@Transactional
	public void removeAdvProduct(long advProdId, String advId) {
		advisorDao.removeAdvProduct(advProdId, advId);
	}

	@Transactional
	public void removeAdvBrandInfo(long prodId, long serviceId, String advId) {
		advisorDao.removeAdvBrandInfo(prodId, serviceId, advId);
	}

	@Transactional
	public void removeFromBrandRank(String advId, long prodId) {
		advisorDao.removeFromBrandRank(advId, prodId);
	}

	@Transactional
	public AdvProduct fetchAdvProductByAdvIdAndAdvProdId(String advId, long advProdId) {
		return advisorDao.fetchAdvProductByAdvIdAndAdvProdId(advId, advProdId);
	}

	@Transactional
	public void removeAdvBrandInfoByAdvId(String advId) {
		advisorDao.removeAdvBrandInfoByAdvId(advId);
	}

	@Transactional
	public void removeAdvBrandRankByAdvId(String advId) {
		advisorDao.removeAdvBrandRankByAdvId(advId);
	}

	@Transactional
	public List<Product> fetchAllServiceAndBrand() {
		return advisorDao.fetchAllServiceAndBrand();
	}

	@Transactional
	public List<Award> fetchAwardByadvId(String advId) {
		return advisorDao.fetchAwardByadvId(advId);
	}

	@Transactional
	public List<Certificate> fetchCertificateByadvId(String advId) {
		return advisorDao.fetchCertificateByadvId(advId);
	}

	@Transactional
	public List<Experience> fetchExperienceByadvId(String advId) {
		return advisorDao.fetchExperienceByadvId(advId);
	}

	@Transactional
	public List<Education> fetchEducationByadvId(String advId) {
		return advisorDao.fetchEducationByadvId(advId);
	}

	@Transactional
	public void modifyAdvisorAward(long awardId, Award award, String advId) {
		advisorDao.modifyAdvisorAward(awardId, award, advId);
	}

	@Transactional
	public void modifyAdvisorCertificate(long certificateId, Certificate certificate, String advId) {
		advisorDao.modifyAdvisorCertificate(certificateId, certificate, advId);
	}

	@Transactional
	public void modifyAdvisorExperience(long expId, Experience experience, String advId) {
		advisorDao.modifyAdvisorExperience(expId, experience, advId);
	}

	@Transactional
	public void modifyAdvisorEducation(long eduId, Education education, String advId) {
		advisorDao.modifyAdvisorEducation(eduId, education, advId);
	}

	@Transactional
	public void addAdvAwardInfo(String advId, Award award) {
		advisorDao.addAdvAwardInfo(advId, award);
	}

	@Transactional
	public void addAdvCertificateInfo(String advId, Certificate certificate) {
		advisorDao.addAdvCertificateInfo(advId, certificate);
	}

	@Transactional
	public void addAdvExperienceInfo(String advId, Experience experience) {
		advisorDao.addAdvExperienceInfo(advId, experience);
	}

	@Transactional
	public void addAdvEducationInfo(String advId, Education education) {
		advisorDao.addAdvEducationInfo(advId, education);
	}

	@Transactional
	public Award fetchAdvAwardByAdvIdAndAwardId(long awardId, String advId) {
		return advisorDao.fetchAdvAwardByAdvIdAndAwardId(awardId, advId);
	}

	@Transactional
	public Certificate fetchAdvCertificateByAdvIdAndCertificateId(long certificateId, String advId) {
		return advisorDao.fetchAdvCertificateByAdvIdAndCertificateId(certificateId, advId);
	}

	@Transactional
	public Education fetchAdvEducationByAdvIdAndEduId(long eduId, String advId) {
		return advisorDao.fetchAdvEducationByAdvIdAndEduId(eduId, advId);
	}

	@Transactional
	public Experience fetchAdvExperienceByAdvIdAndExpId(long expId, String advId) {
		return advisorDao.fetchAdvExperienceByAdvIdAndExpId(expId, advId);
	}

	@Transactional
	public void removeAwardByAdvId(String advId) {
		advisorDao.removeAwardByAdvId(advId);
	}

	@Transactional
	public void removeCertificateByAdvId(String advId) {
		advisorDao.removeCertificateByAdvId(advId);
	}

	@Transactional
	public void removeExperienceByAdvId(String advId) {
		advisorDao.removeExperienceByAdvId(advId);
	}

	@Transactional
	public void removeEducationByAdvId(String advId) {
		advisorDao.removeEducationByAdvId(advId);
	}

	@Transactional
	public List<State> fetchAllStateCityPincode() {
		return advisorDao.fetchAllStateCityPincode();
	}

	@Transactional
	public List<AdvBrandRank> fetchAdvBrandRankByAdvId(String advId) {
		return advisorDao.fetchAdvBrandRankByAdvId(advId);
	}

	@Transactional
	public long fetchPartyIdByRoleBasedId(String advId) {
		return advisorDao.fetchPartyIdByRoleBasedId(advId);
	}

	// @Override
	// public List<AdvBrandInfo> fetchAdvBrandInfoByProdIdAndServiceId(long
	// prodId,
	// long serviceId, String advId) {
	// return
	// advisorDao.fetchAdvBrandInfoByProdIdAndServiceId(prodId,serviceId,advId);
	// }

	// @Transactional
	// public List<Award> fetchAwardByadvId(long advid) {
	// return advisorDao.fetchAwardByadvId(advid);
	// }
	//
	// @Override
	// public List<Education> fetchEducationByadvId(long advid) {
	// return advisorDao.fetchEducationByadvId(advid);
	// }
	//
	// @Override
	// public List<Experience> fetchExperienceByadvId(long advid) {
	// return advisorDao.fetchExperienceByadvId(advid);
	//
	// }

}