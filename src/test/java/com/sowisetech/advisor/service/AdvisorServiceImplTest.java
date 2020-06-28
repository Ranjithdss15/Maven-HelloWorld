package com.sowisetech.advisor.service;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.sowisetech.advisor.dao.AdvisorDao;
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
import com.sowisetech.advisor.model.AdvBrandInfo;
import com.sowisetech.advisor.model.AdvBrandRank;
import com.sowisetech.advisor.model.AdvProduct;
import com.sowisetech.advisor.request.AdvProductRequest;

public class AdvisorServiceImplTest {

	@InjectMocks
	private AdvisorServiceImpl advisorServiceImpl;

	private MockMvc mockMvc;
	@Mock
	private AdvisorDao advisorDao;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(advisorServiceImpl).build();
	}

	@Test
	public void test_signup() throws Exception {
		Advisor adv1 = new Advisor();
		adv1.setAdvId("ADV000000000A");
		adv1.setName("AAA");

		doNothing().when(advisorDao).advSignup(adv1);
		advisorServiceImpl.advSignup(adv1);
		verify(advisorDao, times(1)).advSignup(adv1);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchByAdvisorId() throws Exception {
		Advisor adv1 = new Advisor();
		adv1.setAdvId("ADV000000000A");
		adv1.setName("Dobby");
		when(advisorDao.fetch("ADV000000000A")).thenReturn(adv1);
		Advisor adv = advisorServiceImpl.fetchByAdvisorId("ADV000000000A");
		Assert.assertEquals("Dobby", adv.getName());
		verify(advisorDao, times(1)).fetch("ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchAll() throws Exception {
		List<Advisor> advisors = new ArrayList<Advisor>();
		Advisor adv1 = new Advisor();
		adv1.setAdvId("ADV000000000A");
		adv1.setName("AAA");
		Advisor adv2 = new Advisor();
		adv2.setAdvId("ADV000000000B");
		adv2.setName("BBB");
		advisors.add(adv1);
		advisors.add(adv2);
		when(advisorDao.fetchAll()).thenReturn(advisors);
		List<Advisor> returnAdvisors = advisorServiceImpl.fetchAdvisorList();
		Assert.assertEquals(2, returnAdvisors.size());
		verify(advisorDao, times(1)).fetchAll();
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_removeAdvisor() throws Exception {
		Advisor adv1 = new Advisor();
		adv1.setAdvId("ADV000000000A");
		adv1.setName("Shimpa");
		doNothing().when(advisorDao).delete("ADV000000000A");
		advisorServiceImpl.removeAdvisor("ADV000000000A");
		verify(advisorDao, times(1)).delete("ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_modifyAdvisor() throws Exception {
		Advisor adv1 = new Advisor();
		adv1.setAdvId("ADV000000000A");
		adv1.setName("Shakshi");
		doNothing().when(advisorDao).update("ADV000000000A", adv1);
		advisorServiceImpl.modifyAdvisor("ADV000000000A", adv1);
		verify(advisorDao, times(1)).update("ADV000000000A", adv1);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_addAdvProfessionalInfo() throws Exception {
		List<Award> awards = new ArrayList<Award>();
		Advisor adv1 = new Advisor();
		adv1.setAdvId("ADV000000000A");
		adv1.setAwards(awards);
		doNothing().when(advisorDao).addAdvPersonalInfo("ADV000000000A", adv1);
		advisorServiceImpl.addAdvPersonalInfo("ADV000000000A", adv1);
		verify(advisorDao, times(1)).addAdvPersonalInfo("ADV000000000A", adv1);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_addAdvProductInfo() throws Exception {
		AdvProduct advProduct = new AdvProduct();
		doNothing().when(advisorDao).addAdvProductInfo("ADV000000000A", advProduct);
		advisorServiceImpl.addAdvProductInfo("ADV000000000A", advProduct);
		verify(advisorDao, times(1)).addAdvProductInfo("ADV000000000A", advProduct);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchAward() throws Exception {
		Award award = new Award();
		award.setIssuedBy("IRDA");
		when(advisorDao.fetchAward(1)).thenReturn(award);
		Award awd = advisorServiceImpl.fetchAward(1);
		Assert.assertEquals("IRDA", awd.getIssuedBy());
		verify(advisorDao, times(1)).fetchAward(1);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchCertificate() throws Exception {
		Certificate certificate = new Certificate();
		certificate.setIssuedBy("IRDA");
		when(advisorDao.fetchCertificate(1)).thenReturn(certificate);
		Certificate cert = advisorServiceImpl.fetchCertificate(1);
		Assert.assertEquals("IRDA", cert.getIssuedBy());
		verify(advisorDao, times(1)).fetchCertificate(1);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchExperience() throws Exception {
		Experience experience = new Experience();
		experience.setLocation("chennai");
		when(advisorDao.fetchExperience(1)).thenReturn(experience);
		Experience exp = advisorServiceImpl.fetchExperience(1);
		Assert.assertEquals("chennai", exp.getLocation());
		verify(advisorDao, times(1)).fetchExperience(1);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchEducation() throws Exception {
		Education education = new Education();
		education.setInstitution("IIT");
		when(advisorDao.fetchEducation(1)).thenReturn(education);
		Education edu = advisorServiceImpl.fetchEducation(1);
		Assert.assertEquals("IIT", edu.getInstitution());
		verify(advisorDao, times(1)).fetchEducation(1);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_removeAward() throws Exception {
		Award award = new Award();
		award.setAdvId("ADV000000000A");
		award.setTitle("Oskar");
		doNothing().when(advisorDao).removeAdvAward(1, "ADV000000000A");
		advisorServiceImpl.removeAdvAward(1, "ADV000000000A");
		verify(advisorDao, times(1)).removeAdvAward(1, "ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_removeCertificate() throws Exception {
		Certificate certificate = new Certificate();
		certificate.setAdvId("ADV000000000A");
		certificate.setTitle("Oskar");
		doNothing().when(advisorDao).removeAdvCertificate(1, "ADV000000000A");
		advisorServiceImpl.removeAdvCertificate(1, "ADV000000000A");
		verify(advisorDao, times(1)).removeAdvCertificate(1, "ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_removeEducation() throws Exception {
		Education education = new Education();
		education.setAdvId("ADV000000000A");
		education.setDegree("BE");
		doNothing().when(advisorDao).removeAdvEducation(1, "ADV000000000A");
		advisorServiceImpl.removeAdvEducation(1, "ADV000000000A");
		verify(advisorDao, times(1)).removeAdvEducation(1, "ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_removeExperience() throws Exception {
		Experience experience = new Experience();
		experience.setAdvId("ADV000000000A");
		experience.setDesignation("Lawyer");
		doNothing().when(advisorDao).removeAdvExperience(1, "ADV000000000A");
		advisorServiceImpl.removeAdvExperience(1, "ADV000000000A");
		verify(advisorDao, times(1)).removeAdvExperience(1, "ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchAdvisorByEmailId() throws Exception {
		Advisor adv1 = new Advisor();
		adv1.setAdvId("ADV000000000A");
		adv1.setEmailId("aaa@fo.com");
		adv1.setName("Dobby");
		when(advisorDao.fetchAdvisorByEmailId("aaa@fo.com")).thenReturn(adv1);
		Advisor adv = advisorServiceImpl.fetchAdvisorByEmailId("aaa@fo.com");
		Assert.assertEquals("aaa@fo.com", adv.getEmailId());
		verify(advisorDao, times(1)).fetchAdvisorByEmailId("aaa@fo.com");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_generateId() throws Exception {
		when(advisorDao.generateId()).thenReturn("ADV000000000A");
		String id = advisorServiceImpl.generateId();
		Assert.assertEquals("ADV000000000A", id);
		verify(advisorDao, times(1)).generateId();
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_addAdvPersonalInfo() throws Exception {
		Advisor adv1 = new Advisor();
		adv1.setAdvId("ADV000000000A");
		adv1.setPincode("654123");
		adv1.setGender("f");
		doNothing().when(advisorDao).addAdvPersonalInfo("ADV000000000A", adv1);
		advisorServiceImpl.addAdvPersonalInfo("ADV000000000A", adv1);
		verify(advisorDao, times(1)).addAdvPersonalInfo("ADV000000000A", adv1);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_checkForPasswordMatch() throws Exception {
		when(advisorDao.checkForPasswordMatch("ADV000000000A", "!@AS12as")).thenReturn(true);
		boolean status = advisorServiceImpl.checkForPasswordMatch("ADV000000000A", "!@AS12as");
		Assert.assertEquals(true, status);
		verify(advisorDao, times(1)).checkForPasswordMatch("ADV000000000A", "!@AS12as");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_changeAdvPassword() throws Exception {
		doNothing().when(advisorDao).changeAdvPassword("ADV000000000A", "AS!@as12");
		advisorServiceImpl.changeAdvPassword("ADV000000000A", "AS!@as12");
		verify(advisorDao, times(1)).changeAdvPassword("ADV000000000A", "AS!@as12");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_addAdvVideo() throws Exception {
		List<AdvVideo> advVideo = new ArrayList<AdvVideo>();
		Advisor adv1 = new Advisor();
		adv1.setAdvId("ADV000000000A");
		adv1.setVideos(advVideo);
		doNothing().when(advisorDao).addAdvVideo("ADV000000000A", adv1);
		advisorServiceImpl.addAdvVideo("ADV000000000A", adv1);
		verify(advisorDao, times(1)).addAdvVideo("ADV000000000A", adv1);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchProduct() throws Exception {
		AdvProduct product = new AdvProduct();
		product.setServiceId(1);
		when(advisorDao.fetchAdvProduct(1)).thenReturn(product);
		AdvProduct prod = advisorServiceImpl.fetchAdvProduct(1);
		Assert.assertEquals(1, prod.getServiceId());
		verify(advisorDao, times(1)).fetchAdvProduct(1);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_modifyAdvisorProduct() throws Exception {
		AdvProduct product = new AdvProduct();
		doNothing().when(advisorDao).modifyAdvisorProduct(product, "ADV000000000A");
		advisorServiceImpl.modifyAdvisorProduct(product, "ADV000000000A");
		verify(advisorDao, times(1)).modifyAdvisorProduct(product, "ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_encrypt() throws Exception {
		when(advisorDao.encrypt("!@AS12as")).thenReturn("i2v0dQxostge0Yh7BQK1GK8LpGV37/MdWiT0jZCBFhg=");
		String encryptedText = advisorServiceImpl.encrypt("!@AS12as");
		Assert.assertEquals("i2v0dQxostge0Yh7BQK1GK8LpGV37/MdWiT0jZCBFhg=", encryptedText);
		verify(advisorDao, times(1)).encrypt("!@AS12as");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_decrypt() throws Exception {
		when(advisorDao.decrypt("i2v0dQxostge0Yh7BQK1GK8LpGV37/MdWiT0jZCBFhg=")).thenReturn("!@AS12as");
		String encryptedText = advisorServiceImpl.decrypt("i2v0dQxostge0Yh7BQK1GK8LpGV37/MdWiT0jZCBFhg=");
		Assert.assertEquals("!@AS12as", encryptedText);
		verify(advisorDao, times(1)).decrypt("i2v0dQxostge0Yh7BQK1GK8LpGV37/MdWiT0jZCBFhg=");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchCategoryList() throws Exception {
		List<Category> category = new ArrayList<Category>();
		Category category1 = new Category();
		category1.setCategoryTypeId(1);
		category1.setDesc("LIC");
		Category category2 = new Category();
		category2.setCategoryTypeId(1);
		category2.setDesc("general");
		category.add(category1);
		category.add(category2);
		when(advisorDao.fetchCategoryList()).thenReturn(category);
		List<Category> categoryList = advisorServiceImpl.fetchCategoryList();
		Assert.assertEquals(2, categoryList.size());
		verify(advisorDao, times(1)).fetchCategoryList();
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchCategoryTypeList() throws Exception {
		List<CategoryType> categorytype = new ArrayList<CategoryType>();
		CategoryType categorytype1 = new CategoryType();
		categorytype1.setCategoryTypeId(1);
		categorytype1.setDesc("investment");
		CategoryType categorytype2 = new CategoryType();
		categorytype2.setCategoryTypeId(1);
		categorytype2.setDesc("accounting");
		categorytype.add(categorytype1);
		categorytype.add(categorytype2);
		when(advisorDao.fetchCategoryTypeList()).thenReturn(categorytype);
		List<CategoryType> categoryType = advisorServiceImpl.fetchCategoryTypeList();
		Assert.assertEquals(2, categoryType.size());
		verify(advisorDao, times(1)).fetchCategoryTypeList();
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchForumCategoryList() throws Exception {
		List<ForumCategory> forumCategorys = new ArrayList<ForumCategory>();
		ForumCategory forumCategory1 = new ForumCategory();
		forumCategory1.setId(1);
		forumCategory1.setName("mutual funds");
		ForumCategory forumCategory2 = new ForumCategory();
		forumCategory2.setId(2);
		forumCategory2.setName("stock");
		forumCategorys.add(forumCategory1);
		forumCategorys.add(forumCategory2);
		when(advisorDao.fetchForumCategoryList()).thenReturn(forumCategorys);
		List<ForumCategory> forumCategory = advisorServiceImpl.fetchForumCategoryList();
		Assert.assertEquals(2, forumCategory.size());
		verify(advisorDao, times(1)).fetchForumCategoryList();
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchRiskQuestionaireList() throws Exception {
		List<RiskQuestionaire> riskQuestionaires = new ArrayList<RiskQuestionaire>();
		RiskQuestionaire riskQuestionaire = new RiskQuestionaire();
		riskQuestionaire.setQuestionId("1");
		riskQuestionaire.setQuestion("RiskQuestionaire");
		riskQuestionaires.add(riskQuestionaire);
		when(advisorDao.fetchAllRiskQuestionaire()).thenReturn(riskQuestionaires);
		List<RiskQuestionaire> risk = advisorServiceImpl.fetchRiskQuestionaireList();
		Assert.assertEquals(1, risk.size());
		verify(advisorDao, times(1)).fetchAllRiskQuestionaire();
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchGoalsServedList() throws Exception {
		List<GoalsServed> goalsServedList = new ArrayList<GoalsServed>();
		GoalsServed goalsServed = new GoalsServed();
		goalsServed.setGoalsServedId(1);
		goalsServed.setGoalsServed("GoalsServed");
		goalsServedList.add(goalsServed);
		when(advisorDao.fetchGoalsServedList()).thenReturn(goalsServedList);
		List<GoalsServed> goal = advisorServiceImpl.fetchGoalsServedList();
		Assert.assertEquals(1, goal.size());
		verify(advisorDao, times(1)).fetchGoalsServedList();
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchProductList() throws Exception {
		List<Product> productList = new ArrayList<Product>();
		Product product = new Product();
		product.setProdId(1);
		product.setProduct("Product");
		productList.add(product);
		when(advisorDao.fetchProductList()).thenReturn(productList);
		List<Product> products = advisorServiceImpl.fetchProductList();
		Assert.assertEquals(1, products.size());
		verify(advisorDao, times(1)).fetchProductList();
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchSpecialisedSkills() throws Exception {
		List<SpecialisedSkills> specialisedSkills = new ArrayList<SpecialisedSkills>();
		SpecialisedSkills skill = new SpecialisedSkills();
		skill.setSpecialisedSkillId(1);
		skill.setSpecialisedSkill("Specialised Skills");
		specialisedSkills.add(skill);
		when(advisorDao.fetchSpecialisedSkills()).thenReturn(specialisedSkills);
		List<SpecialisedSkills> skills = advisorServiceImpl.fetchSpecialisedSkills();
		Assert.assertEquals(1, skills.size());
		verify(advisorDao, times(1)).fetchSpecialisedSkills();
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchRoleList() throws Exception {
		List<Role> roleList = new ArrayList<Role>();
		Role role = new Role();
		role.setId(1);
		role.setName("advisor");
		roleList.add(role);
		Role role2 = new Role();
		role2.setId(2);
		role2.setName("investor");
		roleList.add(role2);
		Role role3 = new Role();
		role3.setId(3);
		role3.setName("admin");
		roleList.add(role3);
		when(advisorDao.fetchRoleList()).thenReturn(roleList);
		List<Role> roles = advisorServiceImpl.fetchRoleList();
		Assert.assertEquals(3, roles.size());
		verify(advisorDao, times(1)).fetchRoleList();
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchForumSubCategoryList() throws Exception {
		List<ForumSubCategory> forumSubCategoryList = new ArrayList<ForumSubCategory>();
		ForumSubCategory forumSubCategory = new ForumSubCategory();
		forumSubCategory.setCategoryId(1);
		forumSubCategory.setName("Forum Sub Category");
		forumSubCategoryList.add(forumSubCategory);
		when(advisorDao.fetchForumSubCategoryList()).thenReturn(forumSubCategoryList);
		List<ForumSubCategory> forumsub = advisorServiceImpl.fetchForumSubCategoryList();
		Assert.assertEquals(1, forumsub.size());
		verify(advisorDao, times(1)).fetchForumSubCategoryList();
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchForumStatusList() throws Exception {
		List<ForumStatus> forumStatusList = new ArrayList<ForumStatus>();
		ForumStatus forumStatus = new ForumStatus();
		forumStatus.setId(1);
		forumStatus.setDesc("Forum Status");
		forumStatusList.add(forumStatus);
		when(advisorDao.fetchForumStatusList()).thenReturn(forumStatusList);
		List<ForumStatus> forum = advisorServiceImpl.fetchForumStatusList();
		Assert.assertEquals(1, forum.size());
		verify(advisorDao, times(1)).fetchForumStatusList();
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchPartyStatusList() throws Exception {
		List<PartyStatus> partyStatusList = new ArrayList<PartyStatus>();
		PartyStatus partyStatus = new PartyStatus();
		partyStatus.setId(1);
		partyStatus.setDesc("Party Status");
		partyStatusList.add(partyStatus);
		when(advisorDao.fetchPartyStatusList()).thenReturn(partyStatusList);
		List<PartyStatus> party = advisorServiceImpl.fetchPartyStatusList();
		Assert.assertEquals(1, party.size());
		verify(advisorDao, times(1)).fetchPartyStatusList();
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchServiceList() throws Exception {
		List<Service> services = new ArrayList<Service>();
		Service service1 = new Service();
		service1.setServiceId(1);
		service1.setProdId(2);
		Service service2 = new Service();
		service2.setProdId(1);
		service2.setServiceId(2);
		services.add(service1);
		services.add(service2);
		when(advisorDao.fetchServiceList()).thenReturn(services);
		List<Service> returnServices = advisorServiceImpl.fetchServiceList();
		Assert.assertEquals(2, returnServices.size());
		verify(advisorDao, times(1)).fetchServiceList();
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchBrandList() throws Exception {
		List<Brand> brands = new ArrayList<Brand>();
		Brand brand1 = new Brand();
		brand1.setBrandId(1);
		brand1.setProdId(2);
		Brand brand2 = new Brand();
		brand2.setProdId(1);
		brand2.setBrandId(2);
		brands.add(brand1);
		brands.add(brand2);
		when(advisorDao.fetchBrandList()).thenReturn(brands);
		List<Brand> returnBrands = advisorServiceImpl.fetchBrandList();
		Assert.assertEquals(2, returnBrands.size());
		verify(advisorDao, times(1)).fetchBrandList();
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchLicenseList() throws Exception {
		List<License> licenses = new ArrayList<License>();
		License lic1 = new License();
		lic1.setLicId(1);
		lic1.setProdId(2);
		License lic2 = new License();
		lic2.setProdId(1);
		lic2.setLicId(2);
		licenses.add(lic1);
		licenses.add(lic2);
		when(advisorDao.fetchLicenseList()).thenReturn(licenses);
		List<License> returnLicenses = advisorServiceImpl.fetchLicenseList();
		Assert.assertEquals(2, returnLicenses.size());
		verify(advisorDao, times(1)).fetchLicenseList();
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchRemunerationList() throws Exception {
		List<Remuneration> remunerations = new ArrayList<Remuneration>();
		Remuneration rem1 = new Remuneration();
		rem1.setRemId(1);
		rem1.setRemuneration("Fee");
		Remuneration rem2 = new Remuneration();
		rem2.setRemId(2);
		rem2.setRemuneration("Comission");
		remunerations.add(rem1);
		remunerations.add(rem2);
		when(advisorDao.fetchRemunerationList()).thenReturn(remunerations);
		List<Remuneration> returnRemunerations = advisorServiceImpl.fetchRemunerationList();
		Assert.assertEquals(2, returnRemunerations.size());
		verify(advisorDao, times(1)).fetchRemunerationList();
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_addAdvBrandInfo() throws Exception {
		AdvBrandInfo advBrandInfo = new AdvBrandInfo();
		advBrandInfo.setAdvId("ADV000000000A");
		advBrandInfo.setBrandId(1);
		advBrandInfo.setProdId(2);
		doNothing().when(advisorDao).addAdvBrandInfo("ADV000000000A", advBrandInfo);
		advisorServiceImpl.addAdvBrandInfo("ADV000000000A", advBrandInfo);
		verify(advisorDao, times(1)).addAdvBrandInfo("ADV000000000A", advBrandInfo);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchAdvBrandInfoByAdvIdAndProdId() throws Exception {
		List<AdvBrandInfo> advBrandInfos = new ArrayList<AdvBrandInfo>();
		AdvBrandInfo brandInfo1 = new AdvBrandInfo();
		brandInfo1.setAdvId("ADV000000000A");
		brandInfo1.setBrandId(1);
		AdvBrandInfo brandInfo2 = new AdvBrandInfo();
		brandInfo2.setAdvId("ADV000000000A");
		brandInfo2.setBrandId(2);
		advBrandInfos.add(brandInfo1);
		advBrandInfos.add(brandInfo2);
		when(advisorDao.fetchAdvBrandInfoByAdvIdAndProdId("ADV000000000A", 1)).thenReturn(advBrandInfos);
		List<AdvBrandInfo> returnAdvBrandInfos = advisorServiceImpl.fetchAdvBrandInfoByAdvIdAndProdId("ADV000000000A",
				1);
		Assert.assertEquals(2, returnAdvBrandInfos.size());
		verify(advisorDao, times(1)).fetchAdvBrandInfoByAdvIdAndProdId("ADV000000000A", 1);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchPriorityByBrandIdAndAdvId() throws Exception {
		List<Long> lists = new ArrayList<Long>();
		lists.add(1L);
		lists.add(2L);
		when(advisorDao.fetchPriorityByBrandIdAndAdvId("ADV000000000A", 1, 1)).thenReturn(lists);
		List<Long> returnLists = advisorServiceImpl.fetchPriorityByBrandIdAndAdvId("ADV000000000A", 1, 1);
		Assert.assertEquals(2, returnLists.size());
		verify(advisorDao, times(1)).fetchPriorityByBrandIdAndAdvId("ADV000000000A", 1, 1);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchAdvBrandRank() throws Exception {
		AdvBrandRank advBrandRank = new AdvBrandRank();
		advBrandRank.setBrandId(1);
		when(advisorDao.fetchAdvBrandRank("ADV000000000A", 1, 1)).thenReturn(advBrandRank);
		AdvBrandRank brandRank = advisorServiceImpl.fetchAdvBrandRank("ADV000000000A", 1, 1);
		Assert.assertEquals(1, brandRank.getBrandId());
		verify(advisorDao, times(1)).fetchAdvBrandRank("ADV000000000A", 1, 1);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_addAdvBrandAndRank() throws Exception {
		AdvBrandRank advBrandAndRank = new AdvBrandRank();
		advBrandAndRank.setAdvId("ADV000000000A");
		advBrandAndRank.setBrandId(1);
		advBrandAndRank.setProdId(1);
		doNothing().when(advisorDao).addAdvBrandAndRank(1, 2, "ADV000000000A", 1);
		advisorServiceImpl.addAdvBrandAndRank(1, 2, "ADV000000000A", 1);
		verify(advisorDao, times(1)).addAdvBrandAndRank(1, 2, "ADV000000000A", 1);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_updateBrandAndRank() throws Exception {
		doNothing().when(advisorDao).updateBrandAndRank(1, 2, "ADV000000000A", 1);
		advisorServiceImpl.updateBrandAndRank(1, 2, "ADV000000000A", 1);
		verify(advisorDao, times(1)).updateBrandAndRank(1, 2, "ADV000000000A", 1);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchAdvProductByAdvId() throws Exception {
		List<AdvProduct> advProducts = new ArrayList<AdvProduct>();
		AdvProduct advProduct1 = new AdvProduct();
		advProduct1.setAdvId("ADV000000000A");
		advProduct1.setAdvProdId(1);
		AdvProduct advProduct2 = new AdvProduct();
		advProduct2.setAdvId("ADV000000000A");
		advProduct2.setAdvProdId(2);
		advProducts.add(advProduct1);
		advProducts.add(advProduct2);
		when(advisorDao.fetchAdvProductByAdvId("ADV000000000A")).thenReturn(advProducts);
		List<AdvProduct> returnAdvProducts = advisorServiceImpl.fetchAdvProductByAdvId("ADV000000000A");
		Assert.assertEquals(2, returnAdvProducts.size());
		verify(advisorDao, times(1)).fetchAdvProductByAdvId("ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_removeAdvProduct() throws Exception {
		AdvProduct advProduct = new AdvProduct();
		advProduct.setAdvId("ADV000000000A");
		advProduct.setLicId(1);
		doNothing().when(advisorDao).removeAdvProduct(1, "ADV000000000A");
		advisorServiceImpl.removeAdvProduct(1, "ADV000000000A");
		verify(advisorDao, times(1)).removeAdvProduct(1, "ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_removeAdvBrandInfo() throws Exception {
		doNothing().when(advisorDao).removeAdvBrandInfo(1, 1, "ADV000000000A");
		advisorServiceImpl.removeAdvBrandInfo(1, 1, "ADV000000000A");
		verify(advisorDao, times(1)).removeAdvBrandInfo(1, 1, "ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_removeFromBrandRank() throws Exception {
		doNothing().when(advisorDao).removeFromBrandRank("ADV000000000A", 1);
		advisorServiceImpl.removeFromBrandRank("ADV000000000A", 1);
		verify(advisorDao, times(1)).removeFromBrandRank("ADV000000000A", 1);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchAdvProductByAdvIdAndAdvProdId() throws Exception {
		AdvProduct advProduct = new AdvProduct();
		advProduct.setAdvId("ADV000000000A");
		advProduct.setProdId(1);
		when(advisorDao.fetchAdvProductByAdvIdAndAdvProdId("ADV000000000A", 1)).thenReturn(advProduct);
		AdvProduct product = advisorServiceImpl.fetchAdvProductByAdvIdAndAdvProdId("ADV000000000A", 1);
		Assert.assertEquals(1, product.getProdId());
		verify(advisorDao, times(1)).fetchAdvProductByAdvIdAndAdvProdId("ADV000000000A", 1);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_removeAdvBrandInfoByAdvId() throws Exception {
		doNothing().when(advisorDao).removeAdvBrandInfoByAdvId("ADV000000000A");
		advisorServiceImpl.removeAdvBrandInfoByAdvId("ADV000000000A");
		verify(advisorDao, times(1)).removeAdvBrandInfoByAdvId("ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_removeAdvBrandRankByAdvId() throws Exception {
		doNothing().when(advisorDao).removeAdvBrandRankByAdvId("ADV000000000A");
		advisorServiceImpl.removeAdvBrandRankByAdvId("ADV000000000A");
		verify(advisorDao, times(1)).removeAdvBrandRankByAdvId("ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchAllServiceAndBrand() throws Exception {
		List<Product> products = new ArrayList<Product>();
		Product product1 = new Product();
		product1.setProdId(1);
		product1.setProduct("Fixed Income");
		Product product2 = new Product();
		product2.setProdId(2);
		product2.setProduct("Mutual Funds");
		products.add(product1);
		products.add(product2);
		when(advisorDao.fetchAllServiceAndBrand()).thenReturn(products);
		List<Product> returnProducts = advisorServiceImpl.fetchAllServiceAndBrand();
		Assert.assertEquals(2, returnProducts.size());
		verify(advisorDao, times(1)).fetchAllServiceAndBrand();
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchAwardByadvId() throws Exception {
		List<Award> awards = new ArrayList<Award>();
		Award awd1 = new Award();
		awd1.setAdvId("ADV000000000A");
		awd1.setAwardId(1);
		Award awd2 = new Award();
		awd2.setAdvId("ADV000000000B");
		awd2.setAwardId(2);
		awards.add(awd1);
		awards.add(awd2);
		when(advisorDao.fetchAwardByadvId("ADV000000000A")).thenReturn(awards);
		List<Award> returnAwards = advisorServiceImpl.fetchAwardByadvId("ADV000000000A");
		Assert.assertEquals(2, returnAwards.size());
		verify(advisorDao, times(1)).fetchAwardByadvId("ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchCertificateByadvId() throws Exception {
		List<Certificate> certificates = new ArrayList<Certificate>();
		Certificate cert1 = new Certificate();
		cert1.setAdvId("ADV000000000A");
		cert1.setCertificateId(1);
		Certificate cert2 = new Certificate();
		cert2.setAdvId("ADV000000000B");
		cert2.setCertificateId(2);
		certificates.add(cert1);
		certificates.add(cert2);
		when(advisorDao.fetchCertificateByadvId("ADV000000000A")).thenReturn(certificates);
		List<Certificate> returnCertificates = advisorServiceImpl.fetchCertificateByadvId("ADV000000000A");
		Assert.assertEquals(2, returnCertificates.size());
		verify(advisorDao, times(1)).fetchCertificateByadvId("ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchExperienceByadvId() throws Exception {
		List<Experience> experiences = new ArrayList<Experience>();
		Experience exp1 = new Experience();
		exp1.setAdvId("ADV000000000A");
		exp1.setExpId(1);
		Experience exp2 = new Experience();
		exp2.setAdvId("ADV000000000B");
		exp2.setExpId(2);
		experiences.add(exp1);
		experiences.add(exp2);
		when(advisorDao.fetchExperienceByadvId("ADV000000000A")).thenReturn(experiences);
		List<Experience> returnExperiences = advisorServiceImpl.fetchExperienceByadvId("ADV000000000A");
		Assert.assertEquals(2, returnExperiences.size());
		verify(advisorDao, times(1)).fetchExperienceByadvId("ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchEducationByadvId() throws Exception {
		List<Education> educations = new ArrayList<Education>();
		Education edu1 = new Education();
		edu1.setAdvId("ADV000000000A");
		edu1.setEduId(1);
		Education edu2 = new Education();
		edu2.setAdvId("ADV000000000B");
		edu2.setEduId(2);
		educations.add(edu1);
		educations.add(edu2);
		when(advisorDao.fetchEducationByadvId("ADV000000000A")).thenReturn(educations);
		List<Education> returnEducations = advisorServiceImpl.fetchEducationByadvId("ADV000000000A");
		Assert.assertEquals(2, returnEducations.size());
		verify(advisorDao, times(1)).fetchEducationByadvId("ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_modifyAdvisorAward() throws Exception {
		Award award = new Award();
		doNothing().when(advisorDao).modifyAdvisorAward(1, award, "ADV000000000A");
		advisorServiceImpl.modifyAdvisorAward(1, award, "ADV000000000A");
		verify(advisorDao, times(1)).modifyAdvisorAward(1, award, "ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_modifyAdvisorCertificate() throws Exception {
		Certificate certificate = new Certificate();
		doNothing().when(advisorDao).modifyAdvisorCertificate(1, certificate, "ADV000000000A");
		advisorServiceImpl.modifyAdvisorCertificate(1, certificate, "ADV000000000A");
		verify(advisorDao, times(1)).modifyAdvisorCertificate(1, certificate, "ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_modifyAdvisorExperience() throws Exception {
		Experience experience = new Experience();
		doNothing().when(advisorDao).modifyAdvisorExperience(1, experience, "ADV000000000A");
		advisorServiceImpl.modifyAdvisorExperience(1, experience, "ADV000000000A");
		verify(advisorDao, times(1)).modifyAdvisorExperience(1, experience, "ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_modifyAdvisorEducation() throws Exception {
		Education education = new Education();
		doNothing().when(advisorDao).modifyAdvisorEducation(1, education, "ADV000000000A");
		advisorServiceImpl.modifyAdvisorEducation(1, education, "ADV000000000A");
		verify(advisorDao, times(1)).modifyAdvisorEducation(1, education, "ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_addAdvAwardInfo() throws Exception {
		Award award = new Award();
		award.setAdvId("ADV000000000A");
		award.setAwardId(1);
		award.setTitle("IFRD");
		doNothing().when(advisorDao).addAdvAwardInfo("ADV000000000A", award);
		advisorServiceImpl.addAdvAwardInfo("ADV000000000A", award);
		verify(advisorDao, times(1)).addAdvAwardInfo("ADV000000000A", award);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_addAdvCertificateInfo() throws Exception {
		Certificate certificate = new Certificate();
		certificate.setAdvId("ADV000000000A");
		certificate.setCertificateId(1);
		certificate.setTitle("IFRD");
		doNothing().when(advisorDao).addAdvCertificateInfo("ADV000000000A", certificate);
		advisorServiceImpl.addAdvCertificateInfo("ADV000000000A", certificate);
		verify(advisorDao, times(1)).addAdvCertificateInfo("ADV000000000A", certificate);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_addAdvExperienceInfo() throws Exception {
		Experience experience = new Experience();
		experience.setAdvId("ADV000000000A");
		experience.setExpId(1);
		experience.setLocation("Chennai");
		doNothing().when(advisorDao).addAdvExperienceInfo("ADV000000000A", experience);
		advisorServiceImpl.addAdvExperienceInfo("ADV000000000A", experience);
		verify(advisorDao, times(1)).addAdvExperienceInfo("ADV000000000A", experience);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_addAdvEducationInfo() throws Exception {
		Education education = new Education();
		education.setAdvId("ADV000000000A");
		education.setEduId(1);
		education.setFromYear("OCT-2018");
		doNothing().when(advisorDao).addAdvEducationInfo("ADV000000000A", education);
		advisorServiceImpl.addAdvEducationInfo("ADV000000000A", education);
		verify(advisorDao, times(1)).addAdvEducationInfo("ADV000000000A", education);
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchAdvAwardByAdvIdAndAwardId() throws Exception {
		Award award = new Award();
		award.setAwardId(1);
		when(advisorDao.fetchAdvAwardByAdvIdAndAwardId(1, "ADV000000000A")).thenReturn(award);
		Award awd = advisorServiceImpl.fetchAdvAwardByAdvIdAndAwardId(1, "ADV000000000A");
		Assert.assertEquals(1, awd.getAwardId());
		verify(advisorDao, times(1)).fetchAdvAwardByAdvIdAndAwardId(1, "ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchAdvCertificateByAdvIdAndCertificateId() throws Exception {
		Certificate certificate = new Certificate();
		certificate.setCertificateId(2);
		when(advisorDao.fetchAdvCertificateByAdvIdAndCertificateId(1, "ADV000000000A")).thenReturn(certificate);
		Certificate cert = advisorServiceImpl.fetchAdvCertificateByAdvIdAndCertificateId(1, "ADV000000000A");
		Assert.assertEquals(2, cert.getCertificateId());
		verify(advisorDao, times(1)).fetchAdvCertificateByAdvIdAndCertificateId(1, "ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchAdvEducationByAdvIdAndEduId() throws Exception {
		Education education = new Education();
		education.setEduId(1);
		when(advisorDao.fetchAdvEducationByAdvIdAndEduId(1, "ADV000000000A")).thenReturn(education);
		Education edu = advisorServiceImpl.fetchAdvEducationByAdvIdAndEduId(1, "ADV000000000A");
		Assert.assertEquals(1, edu.getEduId());
		verify(advisorDao, times(1)).fetchAdvEducationByAdvIdAndEduId(1, "ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_fetchAdvExperienceByAdvIdAndExpId() throws Exception {
		Experience experience = new Experience();
		experience.setExpId(1);
		when(advisorDao.fetchAdvExperienceByAdvIdAndExpId(1, "ADV000000000A")).thenReturn(experience);
		Experience exp = advisorServiceImpl.fetchAdvExperienceByAdvIdAndExpId(1, "ADV000000000A");
		Assert.assertEquals(1, exp.getExpId());
		verify(advisorDao, times(1)).fetchAdvExperienceByAdvIdAndExpId(1, "ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_removeAwardByAdvId() throws Exception {
		doNothing().when(advisorDao).removeAwardByAdvId("ADV000000000A");
		advisorServiceImpl.removeAwardByAdvId("ADV000000000A");
		verify(advisorDao, times(1)).removeAwardByAdvId("ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_removeCertificateByAdvId() throws Exception {
		doNothing().when(advisorDao).removeCertificateByAdvId("ADV000000000A");
		advisorServiceImpl.removeCertificateByAdvId("ADV000000000A");
		verify(advisorDao, times(1)).removeCertificateByAdvId("ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_removeExperienceByAdvId() throws Exception {
		doNothing().when(advisorDao).removeExperienceByAdvId("ADV000000000A");
		advisorServiceImpl.removeExperienceByAdvId("ADV000000000A");
		verify(advisorDao, times(1)).removeExperienceByAdvId("ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	@Test
	public void test_removeEducationByAdvId() throws Exception {
		doNothing().when(advisorDao).removeEducationByAdvId("ADV000000000A");
		advisorServiceImpl.removeEducationByAdvId("ADV000000000A");
		verify(advisorDao, times(1)).removeEducationByAdvId("ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}
	
	@Test
	public void test_fetchAllStateCityPincode() throws Exception {
		List<State> stateList = new ArrayList<State>();
		State state1 = new State();
		state1.setStateId(1);
		state1.setState("TamilNadu");
		State state2 = new State();
		state2.setStateId(2);
		state2.setState("Kerala");
		stateList.add(state1);
		stateList.add(state2);
		when(advisorDao.fetchAllStateCityPincode()).thenReturn(stateList);
		List<State> states = advisorServiceImpl.fetchAllStateCityPincode();
		Assert.assertEquals(2, states.size());
		verify(advisorDao, times(1)).fetchAllStateCityPincode();
		verifyNoMoreInteractions(advisorDao);
	
	}

	@Test
	public void test_fetchAdvBrandRankByAdvId() throws Exception {
		List<AdvBrandRank> advBrandRankList = new ArrayList<AdvBrandRank>();
		AdvBrandRank brandRank1 = new AdvBrandRank();
		brandRank1.setAdvId("ADV000000000A");
		brandRank1.setBrandId(1);
		AdvBrandRank brandRank2 = new AdvBrandRank();
		brandRank2.setAdvId("ADV000000000A");
		brandRank2.setBrandId(2);
		advBrandRankList.add(brandRank1);
		advBrandRankList.add(brandRank2);

		when(advisorDao.fetchAdvBrandRankByAdvId("ADV000000000A")).thenReturn(advBrandRankList);
		List<AdvBrandRank> advBrandRank = advisorServiceImpl.fetchAdvBrandRankByAdvId("ADV000000000A");
		Assert.assertEquals(2, advBrandRank.size());
		verify(advisorDao, times(1)).fetchAdvBrandRankByAdvId("ADV000000000A");
		verifyNoMoreInteractions(advisorDao);
	}

	
}
