package com.sowisetech.advisor.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.sowisetech.advisor.model.AdvBrandInfo;
import com.sowisetech.advisor.model.AdvBrandRank;
import com.sowisetech.advisor.model.AdvProduct;
import com.sowisetech.advisor.model.AdvVideo;
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

@Transactional
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class AdvisorDaoImplTest {

	AdvisorDaoImpl advisorDaoImpl;
	EmbeddedDatabase db;

	@Before
	public void setup() {
		EmbeddedDatabase db = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript("db_sql/schema.sql").addScript("db_sql/data.sql").build();
		advisorDaoImpl = new AdvisorDaoImpl();
		advisorDaoImpl.setDataSource(db);
		advisorDaoImpl.postConstruct();
	}

	@Test
	public void tes_advSignUp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Advisor advisor = new Advisor();
		advisor.setAdvId("ADV000000000C");
		advisor.setName("adv");
		advisor.setDesignation("Eng");
		advisor.setEmailId("adv@adv.com");
		advisor.setPassword("!@AS12as");
		advisor.setPhoneNumber("9876541230");
		advisor.setPartyStatusId(1);
		advisor.setCreated(timestamp);
		advisor.setUpdated(timestamp);
		advisor.setDelete_flag("N");
		advisor.setAdvType(1);
		advisorDaoImpl.advSignup(advisor);
		Advisor adv = advisorDaoImpl.fetch("ADV000000000C");
		Assert.assertEquals("adv", adv.getName());
		Assert.assertEquals(1, adv.getAdvType());

	}

	@Test
	public void test_fetch() {
		Advisor advisor = advisorDaoImpl.fetch("ADV000000000A");
		Assert.assertEquals("adv", advisor.getName());

	}

	@Test
	public void test_fetchAll() {
		List<Advisor> advisors = advisorDaoImpl.fetchAll();
		Assert.assertEquals(1, advisors.size());
	}

	@Test
	public void test_delete() {
		advisorDaoImpl.delete("ADV000000000A");
		List<Advisor> advisors = advisorDaoImpl.fetchAll();
		Assert.assertEquals(0, advisors.size());

	}

	@Test
	public void test_update() {
		Advisor advisor = new Advisor();
		advisor.setName("advisor");
		advisor.setDesignation("Engineer");
		advisorDaoImpl.update("ADV000000000A", advisor);
		Advisor adv1 = advisorDaoImpl.fetch("ADV000000000A");
		Assert.assertEquals("advisor", adv1.getName());
		Assert.assertEquals("Engineer", adv1.getDesignation());
	}

	@Test
	public void test_addAdvProfessionalInfo() {
		List<Award> awards = new ArrayList<Award>();
		List<Certificate> certificates = new ArrayList<Certificate>();
		List<Education> educations = new ArrayList<Education>();
		List<Experience> experiences = new ArrayList<Experience>();

		Advisor advisor = new Advisor();
		advisor.setAdvId("ADV000000000A");
		advisor.setName("advisor");
		advisor.setDesignation("Engineer");
		Award award = new Award();
		award.setTitle("award");
		awards.add(award);
		Certificate certificate = new Certificate();
		certificate.setTitle("IRDA");
		certificates.add(certificate);
		Education education = new Education();
		education.setFromYear("MAY-2000");
		;
		educations.add(education);
		Experience experience = new Experience();
		experience.setCompany("sowise");
		experiences.add(experience);
		advisor.setAwards(awards);
		advisor.setCertificates(certificates);
		advisor.setEducations(educations);
		advisor.setExperiences(experiences);
		advisorDaoImpl.addAdvProfessionalInfo("ADV000000000A", advisor);
		Advisor adv = advisorDaoImpl.fetch("ADV000000000A");
		Assert.assertEquals(false, adv.getAwards().isEmpty());
		Assert.assertEquals(false, adv.getCertificates().isEmpty());
		Assert.assertEquals(false, adv.getExperiences().isEmpty());
		Assert.assertEquals(false, adv.getEducations().isEmpty());
	}

	@Test
	public void test_addAdvProductInfo() {
		Advisor advisor = new Advisor();
		advisor.setAdvId("ADV000000000A");
		advisor.setName("advisor");
		advisor.setDesignation("Engineer");
		List<AdvProduct> advProducts = new ArrayList<AdvProduct>();
		AdvProduct advProduct = new AdvProduct();
		advProduct.setProdId(1);
		advProducts.add(advProduct);
		advisor.setAdvProducts(advProducts);
		advisorDaoImpl.addAdvProductInfo("ADV000000000A", advProduct);
		Advisor adv = advisorDaoImpl.fetch("ADV000000000A");
		Assert.assertEquals(false, adv.getAdvProducts().isEmpty());
	}

	@Test
	public void test_fetchAward() {
		Award award = advisorDaoImpl.fetchAward(1);
		Assert.assertEquals("Award", award.getTitle());
	}

	@Test
	public void test_fetchCertificate() {
		Certificate certificate = advisorDaoImpl.fetchCertificate(1);
		Assert.assertEquals("Certificate", certificate.getTitle());
	}

	@Test
	public void test_fetchEducation() {
		Education education = advisorDaoImpl.fetchEducation(1);
		Assert.assertEquals("B.E", education.getDegree());
	}

	@Test
	public void test_fetchExperience() {
		Experience experience = advisorDaoImpl.fetchExperience(1);
		Assert.assertEquals("chennai", experience.getLocation());
	}

	@Test
	public void test_removeAdvAward() {
		advisorDaoImpl.removeAdvAward(1, "ADV000000000A");
		Award awd = advisorDaoImpl.fetchAward(1);
		Assert.assertEquals(null, awd);

	}

	@Test
	public void test_removeAdvCertificate() {
		advisorDaoImpl.removeAdvCertificate(1, "ADV000000000A");
		Certificate cert = advisorDaoImpl.fetchCertificate(1);
		Assert.assertEquals(null, cert);
	}

	@Test
	public void test_removeAdvEducation() {
		advisorDaoImpl.removeAdvEducation(1, "ADV000000000A");
		Education edu = advisorDaoImpl.fetchEducation(1);
		Assert.assertEquals(null, edu);
	}

	@Test
	public void test_removeAdvExperience() {
		advisorDaoImpl.removeAdvExperience(1, "ADV000000000A");
		Experience exp = advisorDaoImpl.fetchExperience(1);
		Assert.assertEquals(null, exp);
	}

	@Test
	public void test_fetchAdvisorByEmailId() {
		Advisor adv = advisorDaoImpl.fetchAdvisorByEmailId("adv@adv.com");
		Assert.assertEquals("adv", adv.getName());
	}

	@Test
	public void test_addAdvPersonalInfo() {
		Advisor adv = new Advisor();
		adv.setAdvId("ADV000000000A");
		adv.setDisplayName("advisor96");
		adv.setPincode("654123");
		advisorDaoImpl.addAdvPersonalInfo("ADV000000000A", adv);
		Advisor adv1 = advisorDaoImpl.fetch("ADV000000000A");
		Assert.assertEquals("advisor96", adv1.getDisplayName());
		Assert.assertEquals("654123", adv1.getPincode());
	}

	@Test
	public void test_checkForPasswordMatch() {
		Advisor advisor = new Advisor();
		advisor.setAdvId("ADV000000000C");
		advisor.setName("adv");
		advisor.setDesignation("Eng");
		advisor.setEmailId("adv@adv.com");
		advisor.setPassword("!@AS12as");
		advisorDaoImpl.advSignup(advisor);
		boolean result = advisorDaoImpl.checkForPasswordMatch("ADV000000000C", "!@AS12as");
		Assert.assertEquals(true, result);
	}

	@Test
	public void test_changeAdvPassword() {
		advisorDaoImpl.changeAdvPassword("ADV000000000A", "SA@!sa21");
		Advisor adv = advisorDaoImpl.fetch("ADV000000000A");
		Assert.assertEquals("SA@!sa21", advisorDaoImpl.decrypt(adv.getPassword()));

	}

	@Test
	public void test_addAdvVideo() {
		Advisor advisor = new Advisor();
		advisor.setAdvId("ADV000000000A");
		advisor.setName("adv");
		AdvVideo advV = new AdvVideo();
		advV.setTitle("advisorVDO");
		advisorDaoImpl.addAdvVideo("ADV000000000A", advisor);
		Advisor adv1 = advisorDaoImpl.fetch("ADV000000000A");
		Assert.assertEquals(false, adv1.getVideos().isEmpty());
		Assert.assertEquals(1, adv1.getVideos().size());

	}

	@Test
	public void test_fetchAdvProduct() {
		AdvProduct advProduct = advisorDaoImpl.fetchAdvProduct(1);
		Assert.assertEquals(1, advProduct.getServiceId());
	}

	@Test
	public void test_modifyAdvisorProduct() {
		AdvProduct advProduct = new AdvProduct();
		advProduct.setAdvProdId(1);
		advProduct.setServiceId(2);
		advisorDaoImpl.modifyAdvisorProduct(advProduct, "ADV000000000A");
		AdvProduct prod = advisorDaoImpl.fetchAdvProduct(1);
		Assert.assertEquals(2, prod.getServiceId());
	}

	@Test
	public void test_addAdvBrandInfo() {
		AdvBrandInfo advBrandInfo = new AdvBrandInfo();
		advBrandInfo.setAdvId("ADV000000000A");
		advBrandInfo.setBrandId(1);
		advBrandInfo.setProdId(2);
		advisorDaoImpl.addAdvBrandInfo("ADV000000000A", advBrandInfo);
		Advisor adv = advisorDaoImpl.fetch("ADV000000000A");
		Assert.assertEquals(3, adv.getAdvBrandInfo().size());
	}

	@Test
	public void testfetchCategoryList() {
		List<Category> category = advisorDaoImpl.fetchCategoryList();
		Assert.assertEquals(2, category.size());
		Assert.assertEquals("general insur", category.get(1).getDesc());
	}

	@Test
	public void testfetchCategoryTypeList() {
		List<CategoryType> categoryType = advisorDaoImpl.fetchCategoryTypeList();
		Assert.assertEquals(2, categoryType.size());
		Assert.assertEquals("accounting", categoryType.get(1).getDesc());
	}

	@Test
	public void testfetchForumCategoryList() {
		List<ForumCategory> forumCategory = advisorDaoImpl.fetchForumCategoryList();
		Assert.assertEquals(2, forumCategory.size());
		Assert.assertEquals("stock", forumCategory.get(1).getName());
	}

	@Test
	public void testfetchAllRiskQuestionaire() {
		List<RiskQuestionaire> riskQuestionaire = advisorDaoImpl.fetchAllRiskQuestionaire();
		Assert.assertEquals(1, riskQuestionaire.size());
		Assert.assertEquals(2, riskQuestionaire.get(0).getAnswerId());
	}

	@Test
	public void testfetchGoalsServedList() {
		List<GoalsServed> goalsServed = advisorDaoImpl.fetchGoalsServedList();
		Assert.assertEquals(1, goalsServed.size());
		Assert.assertEquals("insure", goalsServed.get(0).getGoalsServed());
	}

	@Test
	public void fetchProductList() {
		List<Product> product = advisorDaoImpl.fetchProductList();
		Assert.assertEquals(1, product.size());
		Assert.assertEquals(1, product.get(0).getServices().size());
		Assert.assertEquals(2, product.get(0).getBrands().size());
	}

	@Test
	public void testfetchSpecialisedSkills() {
		List<SpecialisedSkills> specialisedSkills = advisorDaoImpl.fetchSpecialisedSkills();
		Assert.assertEquals(1, specialisedSkills.size());
		Assert.assertEquals("goal setting", specialisedSkills.get(0).getSpecialisedSkill());
	}

	@Test
	public void testfetchRoleList() {
		List<Role> role = advisorDaoImpl.fetchRoleList();
		Assert.assertEquals(3, role.size());
		Assert.assertEquals("admin", role.get(2).getName());
	}

	@Test
	public void testfetchForumSubCategoryList() {
		List<ForumSubCategory> forum = advisorDaoImpl.fetchForumSubCategoryList();
		Assert.assertEquals(1, forum.size());
		Assert.assertEquals("equity fund", forum.get(0).getName());
	}

	@Test
	public void testfetchForumStatusList() {
		List<ForumStatus> status = advisorDaoImpl.fetchForumStatusList();
		Assert.assertEquals(1, status.size());
		Assert.assertEquals("init", status.get(0).getDesc());
	}

	@Test
	public void testfetchPartyStatusList() {
		List<PartyStatus> party = advisorDaoImpl.fetchPartyStatusList();
		Assert.assertEquals(2, party.size());
		Assert.assertEquals("active", party.get(0).getDesc());
		Assert.assertEquals("inactive", party.get(1).getDesc());
	}

	@Test
	public void test_fetchServiceList() {
		List<Service> services = advisorDaoImpl.fetchServiceList();
		Assert.assertEquals(1, services.size());
	}

	@Test
	public void test_fetchBrandList() {
		List<Brand> brands = advisorDaoImpl.fetchBrandList();
		Assert.assertEquals(2, brands.size());
	}

	@Test
	public void test_fetchLicenseList() {
		List<License> licenses = advisorDaoImpl.fetchLicenseList();
		Assert.assertEquals(1, licenses.size());
	}

	@Test
	public void test_fetchRemunerationList() {
		List<Remuneration> remunerations = advisorDaoImpl.fetchRemunerationList();
		Assert.assertEquals(1, remunerations.size());
	}

	@Test
	public void test_fetchAdvBrandInfoByAdvIdAndProdId() {
		List<AdvBrandInfo> advBrandInfos = advisorDaoImpl.fetchAdvBrandInfoByAdvIdAndProdId("ADV000000000A", 1);
		Assert.assertEquals(2, advBrandInfos.size());
	}

	@Test
	public void test_fetchPriorityByBrandIdAndAdvId() {
		List<Long> priority = advisorDaoImpl.fetchPriorityByBrandIdAndAdvId("ADV000000000A", 1, 1);
		Assert.assertEquals(1, priority.size());
	}

	@Test
	public void test_fetchAdvBrandRank() {
		AdvBrandRank advBrandRank = advisorDaoImpl.fetchAdvBrandRank("ADV000000000A", 1, 1);
		Assert.assertEquals(1, advBrandRank.getBrandId());
	}

	@Test
	public void test_addAdvBrandAndRank() {
		AdvBrandRank advBrandRank = new AdvBrandRank();
		advBrandRank.setAdvId("ADV000000000A");
		advBrandRank.setBrandId(1);
		advBrandRank.setProdId(2);
		advisorDaoImpl.addAdvBrandAndRank(1, 1, "ADV000000000A", 1);
		Advisor adv = advisorDaoImpl.fetch("ADV000000000A");
		Assert.assertEquals(3, adv.getAdvBrandRank().size());
	}

	@Test
	public void test_updateBrandAndRank() {
		advisorDaoImpl.updateBrandAndRank(2, 1, "ADV000000000A", 1);
		AdvBrandRank brandRank = advisorDaoImpl.fetchAdvBrandRank("ADV000000000A", 1, 1);
		Assert.assertEquals(2, brandRank.getBrandId());
	}

	@Test
	public void test_fetchAdvProductByAdvId() {
		List<AdvProduct> advProducts = advisorDaoImpl.fetchAdvProductByAdvId("ADV000000000A");
		Assert.assertEquals(1, advProducts.size());
	}

	@Test
	public void test_removeAdvProduct() {
		advisorDaoImpl.removeAdvProduct(1, "ADV000000000A");
		AdvProduct advProduct = advisorDaoImpl.fetchAdvProduct(1);
		Assert.assertEquals(null, advProduct);
	}

	@Test
	public void test_removeAdvBrandInfo() {
		advisorDaoImpl.removeAdvBrandInfo(1, 1, "ADV000000000A");
		List<AdvBrandInfo> advBrandInfo = advisorDaoImpl.fetchAdvBrandInfoByAdvIdAndProdId("ADV000000000A", 1);
		Assert.assertEquals(1, advBrandInfo.size());
	}

	@Test
	public void test_removeFromBrandRank() {
		advisorDaoImpl.removeFromBrandRank("ADV000000000A", 1);
		AdvBrandRank advBrandRank = advisorDaoImpl.fetchAdvBrandRank("ADV000000000A", 1, 1);
		Assert.assertEquals(null, advBrandRank);
	}

	@Test
	public void test_fetchAdvProductByAdvIdAndAdvProdId() {
		AdvProduct advProduct = advisorDaoImpl.fetchAdvProductByAdvIdAndAdvProdId("ADV000000000A", 1);
		Assert.assertEquals(1, advProduct.getProdId());
	}

	@Test
	public void test_removeAdvBrandInfoByAdvId() {
		advisorDaoImpl.removeAdvBrandInfoByAdvId("ADV000000000A");
		List<AdvBrandInfo> advBrandInfo = advisorDaoImpl.fetchAdvBrandInfoByAdvIdAndProdId("ADV000000000A", 1);
		Assert.assertEquals(0, advBrandInfo.size());
	}

	@Test
	public void test_removeAdvBrandRankByAdvId() {
		advisorDaoImpl.removeAdvBrandRankByAdvId("ADV000000000A");
		AdvBrandRank advBrandRank = advisorDaoImpl.fetchAdvBrandRank("ADV000000000A", 1, 1);
		Assert.assertEquals(null, advBrandRank);
	}

	@Test
	public void test_fetchAllServiceAndBrand() {
		List<Product> products = advisorDaoImpl.fetchAllServiceAndBrand();
		Assert.assertEquals(1, products.size());
	}

	@Test
	public void test_fetchAwardByadvId() {
		List<Award> awards = advisorDaoImpl.fetchAwardByadvId("ADV000000000A");
		Assert.assertEquals(1, awards.size());
	}

	@Test
	public void test_fetchCertificateByadvId() {
		List<Certificate> certificates = advisorDaoImpl.fetchCertificateByadvId("ADV000000000A");
		Assert.assertEquals(1, certificates.size());
	}

	@Test
	public void test_fetchExperienceByadvId() {
		List<Experience> experiences = advisorDaoImpl.fetchExperienceByadvId("ADV000000000A");
		Assert.assertEquals(1, experiences.size());
	}

	@Test
	public void test_fetchEducationByadvId() {
		List<Education> educations = advisorDaoImpl.fetchEducationByadvId("ADV000000000A");
		Assert.assertEquals(1, educations.size());
	}

	@Test
	public void test_modifyAdvisorAward() {
		Award award = new Award();
		award.setAwardId(1);
		award.setYear("1996");
		advisorDaoImpl.modifyAdvisorAward(1, award, "ADV000000000A");
		Award awd = advisorDaoImpl.fetchAward(1);
		Assert.assertEquals("1996", awd.getYear());
	}

	@Test
	public void test_modifyAdvisorCertificate() {
		Certificate certificate = new Certificate();
		certificate.setCertificateId(1);
		certificate.setYear("1996");
		advisorDaoImpl.modifyAdvisorCertificate(1, certificate, "ADV000000000A");
		Certificate cert = advisorDaoImpl.fetchCertificate(1);
		Assert.assertEquals("1996", cert.getYear());
	}

	@Test
	public void test_modifyAdvisorExperience() {
		Experience experience = new Experience();
		experience.setExpId(1);
		experience.setLocation("nellai");
		;
		advisorDaoImpl.modifyAdvisorExperience(1, experience, "ADV000000000A");
		Experience exp = advisorDaoImpl.fetchExperience(1);
		Assert.assertEquals("nellai", exp.getLocation());
	}

	@Test
	public void test_modifyAdvisorEducation() {
		Education education = new Education();
		education.setEduId(1);
		education.setField("CSE");
		;
		advisorDaoImpl.modifyAdvisorEducation(1, education, "ADV000000000A");
		Education edu = advisorDaoImpl.fetchEducation(1);
		Assert.assertEquals("CSE", edu.getField());
	}

	@Test
	public void test_addAdvAwardInfo() {
		List<Award> awards = new ArrayList<Award>();
		Award award = new Award();
		award.setAdvId("ADV000000000A");
		award.setAwardId(1);
		award.setYear("1996");
		awards.add(award);
		advisorDaoImpl.addAdvAwardInfo("ADV000000000A", award);
		Advisor adv = advisorDaoImpl.fetch("ADV000000000A");
		Assert.assertEquals(2, adv.getAwards().size());
	}

	@Test
	public void test_addAdvCertificateInfo() {
		List<Certificate> certificates = new ArrayList<Certificate>();
		Certificate certificate = new Certificate();
		certificate.setAdvId("ADV000000000A");
		certificate.setCertificateId(1);
		certificate.setYear("1996");
		certificates.add(certificate);
		advisorDaoImpl.addAdvCertificateInfo("ADV000000000A", certificate);
		Advisor adv = advisorDaoImpl.fetch("ADV000000000A");
		Assert.assertEquals(2, adv.getCertificates().size());
	}

	@Test
	public void test_addAdvExperienceInfo() {
		List<Experience> experiences = new ArrayList<Experience>();
		Experience experience = new Experience();
		experience.setAdvId("ADV000000000A");
		experience.setExpId(1);
		experience.setFromYear("MAR-1996");
		experiences.add(experience);
		advisorDaoImpl.addAdvExperienceInfo("ADV000000000A", experience);
		Advisor adv = advisorDaoImpl.fetch("ADV000000000A");
		Assert.assertEquals(2, adv.getExperiences().size());
	}

	@Test
	public void test_addAdvEducationInfo() {
		List<Education> educations = new ArrayList<Education>();
		Education education = new Education();
		education.setAdvId("ADV000000000A");
		education.setEduId(1);
		education.setDegree("B.E");
		educations.add(education);
		advisorDaoImpl.addAdvEducationInfo("ADV000000000A", education);
		Advisor adv = advisorDaoImpl.fetch("ADV000000000A");
		Assert.assertEquals(2, adv.getEducations().size());
	}

	@Test
	public void test_fetchAdvAwardByAdvIdAndAwardId() {
		Award award = advisorDaoImpl.fetchAdvAwardByAdvIdAndAwardId(1, "ADV000000000A");
		Assert.assertEquals("Award", award.getTitle());
	}

	@Test
	public void test_fetchAdvCertificateByAdvIdAndCertificateId() {
		Certificate cert = advisorDaoImpl.fetchAdvCertificateByAdvIdAndCertificateId(1, "ADV000000000A");
		Assert.assertEquals("Certificate", cert.getTitle());
	}

	@Test
	public void test_fetchAdvEducationByAdvIdAndEduId() {
		Education edu = advisorDaoImpl.fetchAdvEducationByAdvIdAndEduId(1, "ADV000000000A");
		Assert.assertEquals("IT", edu.getField());
	}

	@Test
	public void test_fetchAdvExperienceByAdvIdAndExpId() {
		Experience exp = advisorDaoImpl.fetchAdvExperienceByAdvIdAndExpId(1, "ADV000000000A");
		Assert.assertEquals("engineer", exp.getDesignation());
	}

	@Test
	public void test_removeAwardByAdvId() {
		advisorDaoImpl.removeAwardByAdvId("ADV000000000A");
		Award awd = advisorDaoImpl.fetchAward(1);
		Assert.assertEquals(null, awd);
	}

	@Test
	public void test_removeCertificateByAdvId() {
		advisorDaoImpl.removeCertificateByAdvId("ADV000000000A");
		Certificate certificate = advisorDaoImpl.fetchCertificate(1);
		Assert.assertEquals(null, certificate);
	}

	@Test
	public void test_removeExperienceByAdvId() {
		advisorDaoImpl.removeExperienceByAdvId("ADV000000000A");
		Experience experience = advisorDaoImpl.fetchExperience(1);
		Assert.assertEquals(null, experience);
	}

	@Test
	public void test_removeEducationByAdvId() {
		advisorDaoImpl.removeEducationByAdvId("ADV000000000A");
		Education education = advisorDaoImpl.fetchEducation(1);
		Assert.assertEquals(null, education);
	}

	@Test
	public void test_fetchAllStateCityPincode() {
		List<State> states = advisorDaoImpl.fetchAllStateCityPincode();
		Assert.assertEquals(1, states.size());
		Assert.assertEquals(2, states.get(0).getCities().size());

	}

	@Test
	public void test_fetchAdvBrandRankByAdvId() {
		List<AdvBrandRank> advBrandRank = advisorDaoImpl.fetchAdvBrandRankByAdvId("ADV000000000A");
		Assert.assertEquals(2, advBrandRank.size());
	}

	
}