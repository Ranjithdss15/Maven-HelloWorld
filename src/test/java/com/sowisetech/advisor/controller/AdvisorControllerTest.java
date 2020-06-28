package com.sowisetech.advisor.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.sowisetech.AdvisorApplication;
//import com.sowisetech.advisor.config.AppConfig;
import com.sowisetech.advisor.model.AdvBrandInfo;
import com.sowisetech.advisor.model.AdvBrandRank;
import com.sowisetech.advisor.model.AdvProduct;
import com.sowisetech.advisor.request.AdvBrandInfoReq;
import com.sowisetech.advisor.request.AdvBrandInfoRequest;
import com.sowisetech.advisor.request.AdvIdRequest;
import com.sowisetech.advisor.request.AdvPersonalInfoRequest;
import com.sowisetech.advisor.request.AdvPersonalInfoRequestValidator;
import com.sowisetech.advisor.request.AdvProductInfoRequest;
import com.sowisetech.advisor.request.AdvProductInfoRequestValidator;
import com.sowisetech.advisor.request.AdvProfessionalInfoRequest;
import com.sowisetech.advisor.request.AdvProfessionalInfoRequestValidator;
import com.sowisetech.advisor.request.AdvVideoReq;
import com.sowisetech.advisor.request.AdvVideoRequestValidator;
import com.sowisetech.advisor.request.AdvisorRequest;
import com.sowisetech.advisor.request.AdvisorRequestValidator;
import com.sowisetech.advisor.request.AwardReq;
import com.sowisetech.advisor.request.CertificateReq;
import com.sowisetech.advisor.request.EducationReq;
import com.sowisetech.advisor.request.ExperienceReq;
import com.sowisetech.advisor.request.IdRequest;
import com.sowisetech.advisor.request.ModifyAdvReqValidator;
import com.sowisetech.advisor.request.ModifyAdvRequest;
import com.sowisetech.advisor.request.PasswordChangeRequest;
import com.sowisetech.advisor.request.PasswordValidator;
import com.sowisetech.advisor.request.AdvProductRequest;
import com.sowisetech.advisor.request.VideoReq;
import com.sowisetech.advisor.response.SuccessResponse;
import com.sowisetech.advisor.service.AdvisorService;
import com.sowisetech.advisor.util.AppMessages;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdvisorApplication.class)
public class AdvisorControllerTest {

	@InjectMocks
	private AdvisorController advisorController;

	private MockMvc mockMvc;
	@Mock
	private AdvisorService advisorService;
	@Mock
	private AdvisorRequestValidator advisorRequestValidator;
	@Mock
	private ModifyAdvReqValidator modifyAdvReqValidator;
	@Mock
	private AdvPersonalInfoRequestValidator advPersonalInfoRequestValidator;
	@Mock
	private AdvProductInfoRequestValidator advProductInfoRequestValidator;
	@Mock
	private AdvProfessionalInfoRequestValidator advProfessionalInfoRequestValidator;
	@Mock
	private PasswordValidator passwordValidator;
	@Mock
	private AdvVideoRequestValidator advVideoRequestValidator;

	@Mock
	private SuccessResponse successResponse;

	@Autowired(required = true)
	@Spy
	AppMessages appMessages;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(advisorController).build();
	}

	@Test
	public void testEcv() throws Exception {
		this.mockMvc.perform(get("/ecv")).andExpect(status().isOk());
	}
	// =========================================== Get All Advisors
	// ==========================================

	@Test
	public void test_fetchAll() throws Exception {
		List<Advisor> advisors = new ArrayList<Advisor>();
		when(advisorService.fetchAdvisorList()).thenReturn(advisors);
		Advisor adv1 = new Advisor();
		adv1.setAdvId(("ADV000000000A"));
		adv1.setName("AAA");
		Advisor adv2 = new Advisor();
		adv2.setAdvId("ADV000000000B");
		adv2.setName("BBB");
		advisors.add(adv1);
		advisors.add(adv2);

		mockMvc.perform(get("/fetch-all")).andExpect(status().isOk())
				// .andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].advId", is("ADV000000000A")))
				.andExpect(jsonPath("$[0].name", is("AAA"))).andExpect(jsonPath("$[1].advId", is("ADV000000000B")))
				.andExpect(jsonPath("$[1].name", is("BBB")));

		verify(advisorService, times(1)).fetchAdvisorList();
		verifyNoMoreInteractions(advisorService);
	}
	// =========================================== Get Advisor By ID
	// ==========================================

	@Test
	public void test_fetchAdvisorById() throws Exception {
		AdvIdRequest advIdReq = new AdvIdRequest();
		advIdReq.setAdvId("ADV000000000A");
		Advisor adv = new Advisor();
		adv.setAdvId("ADV000000000A");
		adv.setName("aaa");
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(advIdReq);
		when(advisorService.fetchByAdvisorId(Mockito.anyString())).thenReturn(adv);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/fetch").content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.advId", is("ADV000000000A")))
				.andExpect(jsonPath("$.name", is("aaa")));
		verify(advisorService, times(1)).fetchByAdvisorId("ADV000000000A");
		verifyNoMoreInteractions(advisorService);

	}

	// =========================================== Delete Advisor By ID
	// ==========================================

	@Test
	public void test_removeAdvisor() throws Exception {
		AdvIdRequest advIdReq = new AdvIdRequest();
		advIdReq.setAdvId("ADV000000000B");
		Advisor adv = new Advisor();
		adv.setAdvId("ADV000000000B");
		adv.setName("aaa");
		when(advisorService.fetchByAdvisorId(adv.getAdvId())).thenReturn(adv);
		// when(successResponse.createSuccessResponse(Mockito.anyString()))
		// .thenReturn(createSuccessResponse(appMessages.getAdvisor_deleted_successfully()));
		String msg = appMessages.getAdvisor_deleted_successfully();
		when(successResponse.createSuccessResponse(appMessages.getAdvisor_deleted_successfully()))
				.thenReturn(createSuccessResponse(msg));
		doNothing().when(advisorService).removeAdvisor(adv.getAdvId());
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(advIdReq);
		MvcResult result = mockMvc
				.perform(delete("/remove").content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		Assert.assertEquals(createSuccessResponse(appMessages.getAdvisor_deleted_successfully()),
				result.getResponse().getContentAsString());
		verify(advisorService, times(1)).fetchByAdvisorId(adv.getAdvId());
		verify(advisorService, times(1)).removeAdvisor(adv.getAdvId());
		verifyNoMoreInteractions(advisorService);
	}

	@Test
	public void test_signup() throws Exception {

		AdvisorRequest advReq = new AdvisorRequest();
		advReq.setName("name");
		advReq.setEmailId("aaa@aaa.com");
		advReq.setPhoneNumber("9874563210");
		advReq.setPassword("!@AS12as");
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(advReq);

		when(advisorRequestValidator.validate(advReq)).thenReturn(null);
		when(advisorService.fetchAdvisorByEmailId(advReq.getEmailId())).thenReturn(null);
		when(advisorService.generateId()).thenReturn("ADV000000000A");
		doNothing().when(advisorService).advSignup(Mockito.any(Advisor.class));
		// when(successResponse.createSuccessResponse(Mockito.anyString()))
		// .thenReturn(createSuccessResponse(appMessages.getAdvisor_added_successfully()));
		String msg = appMessages.getAdvisor_added_successfully();
		when(successResponse.createSuccessResponse(appMessages.getAdvisor_added_successfully()))
				.thenReturn(createSuccessResponse(msg));

		MvcResult result = mockMvc.perform(post("/signup").content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		Assert.assertEquals(createSuccessResponse(appMessages.getAdvisor_added_successfully()),
				result.getResponse().getContentAsString());

		verify(advisorService, times(1)).fetchAdvisorByEmailId(advReq.getEmailId());
		verify(advisorService, times(1)).generateId();
		verify(advisorService, times(1)).advSignup(Mockito.any(Advisor.class));
		verifyNoMoreInteractions(advisorService);
	}

	@Test
	public void test_modifyAdvisor() throws Exception {

		ModifyAdvRequest modAdvReq = new ModifyAdvRequest();
		modAdvReq.setAdvId("ADV000000000A");
		modAdvReq.setName("advisor");
		modAdvReq.setDisplayName("advisor!");
		Advisor adv = new Advisor();
		adv.setAdvId("ADV000000000A");
		adv.setName("advisor");
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(modAdvReq);
		when(modifyAdvReqValidator.validate(modAdvReq)).thenReturn(null);
		when(advisorService.fetchByAdvisorId(Mockito.anyString())).thenReturn(adv);
		doNothing().when(advisorService).modifyAdvisor(Mockito.anyString(), Mockito.any(Advisor.class));
		// when(successResponse.createSuccessResponse(Mockito.anyString()))
		// .thenReturn(createSuccessResponse(appMessages.getAdvisor_updated_successfully()));
		String msg = appMessages.getAdvisor_updated_successfully();
		when(successResponse.createSuccessResponse(appMessages.getAdvisor_updated_successfully()))
				.thenReturn(createSuccessResponse(msg));
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.put("/modify").content(jsonString)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		Assert.assertEquals(createSuccessResponse(appMessages.getAdvisor_updated_successfully()),
				result.getResponse().getContentAsString());

	}

	@Test
	public void test_AddPersonalInfo() throws Exception {

		AdvPersonalInfoRequest advPersonalInfoReq = new AdvPersonalInfoRequest();
		advPersonalInfoReq.setAdvId("ADV000000000A");
		advPersonalInfoReq.setDisplayName("display_name");
		Advisor adv = new Advisor();
		adv.setAdvId("ADV000000000A");
		adv.setName("advisor");
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(advPersonalInfoReq);
		when(advisorService.fetchByAdvisorId(Mockito.anyString())).thenReturn(adv);
		when(advPersonalInfoRequestValidator.validate(advPersonalInfoReq)).thenReturn(null);
		doNothing().when(advisorService).addAdvPersonalInfo(Mockito.anyString(), Mockito.any(Advisor.class));
		// when(successResponse.createSuccessResponse(Mockito.anyString()))
		// .thenReturn(createSuccessResponse(appMessages.getAdvisor_info_added_successfully()));

		String msg = appMessages.getAdvisor_info_added_successfully();
		when(successResponse.createSuccessResponse(appMessages.getAdvisor_info_added_successfully()))
				.thenReturn(createSuccessResponse(msg));
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/addAdvPersonalInfo").content(jsonString)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		Assert.assertEquals(createSuccessResponse(appMessages.getAdvisor_info_added_successfully()),
				result.getResponse().getContentAsString());
	}

	@Test
	public void test_AddProductInfo() throws Exception {
		AdvProductInfoRequest advProductInfoRequest = new AdvProductInfoRequest();
		advProductInfoRequest.setAdvId("ADV000000000A");
		List<AdvProductRequest> productRequestList = new ArrayList<AdvProductRequest>();
		AdvProductRequest productRequest = new AdvProductRequest();
		productRequest.setProdId(1);
		productRequest.setServiceId(1);
		productRequestList.add(productRequest);
		advProductInfoRequest.setAdvProducts(productRequestList);

		Advisor adv = new Advisor();
		adv.setAdvId("ADV000000000A");

		AdvProduct advProduct = new AdvProduct();
		advProduct.setAdvId("ADV000000000A");
		advProduct.setAdvProdId(1);
		advProduct.setServiceId(1);

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(advProductInfoRequest);
		// when(advProductInfoRequestValidator.validate(advProductInfoRequest)).thenReturn(null);
		when(advisorService.fetchByAdvisorId(Mockito.anyString())).thenReturn(adv);
		doNothing().when(advisorService).addAdvProductInfo(Mockito.anyString(), Mockito.any(AdvProduct.class));
		// when(successResponse.createSuccessResponse(Mockito.anyString()))
		// .thenReturn(createSuccessResponse(appMessages.getAdvisor_info_added_successfully()));
		String msg = appMessages.getAdvisor_info_added_successfully();
		when(successResponse.createSuccessResponse(appMessages.getAdvisor_info_added_successfully()))
				.thenReturn(createSuccessResponse(msg));
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/addAdvProdInfo").content(jsonString)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		Assert.assertEquals(createSuccessResponse(appMessages.getAdvisor_info_added_successfully()),
				result.getResponse().getContentAsString());
	}

	@Test
	public void test_ModifyProductInfo() throws Exception {
		Advisor adv = new Advisor();
		adv.setAdvId("ADV0000000001");

		List<AdvProductRequest> productRequestList = new ArrayList<AdvProductRequest>();
		AdvProductRequest productRequest1 = new AdvProductRequest();
		productRequest1.setAdvProdId(1);
		productRequest1.setProdId(1);
		productRequest1.setServiceId(2);
		productRequest1.setValidity("14-02-2012");

		AdvProductRequest productRequest2 = new AdvProductRequest();
		productRequest2.setProdId(3);
		productRequest2.setServiceId(2);
		productRequest2.setValidity("14-02-2012");

		productRequestList.add(productRequest1);
		productRequestList.add(productRequest2);

		AdvProductInfoRequest advProductInfoReq = new AdvProductInfoRequest();
		advProductInfoReq.setAdvId("ADV0000000001");
		advProductInfoReq.setAdvProducts(productRequestList);

		List<AdvProduct> advProducts = new ArrayList<AdvProduct>();
		AdvProduct product1 = new AdvProduct();
		product1.setAdvId("ADV0000000001");
		product1.setAdvProdId(1);
		product1.setProdId(1);
		AdvProduct product2 = new AdvProduct();
		product2.setAdvId("ADV0000000001");
		product2.setAdvProdId(2);
		product2.setProdId(2);
		advProducts.add(product1);
		advProducts.add(product2);

		AdvProduct product3 = new AdvProduct();
		product1.setAdvId("ADV0000000001");
		product1.setAdvProdId(3);
		product1.setProdId(3);

		List<AdvBrandInfo> advBrandInfoList = new ArrayList<>();
		List<Long> advBrandInfoListPriority = new ArrayList<>();
		when(advisorService.fetchAdvBrandRank(Mockito.anyString(), Mockito.anyLong(), Mockito.anyInt()))
				.thenReturn(null);

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(advProductInfoReq);

		when(advisorService.fetchByAdvisorId("ADV0000000001")).thenReturn(adv);
		when(advProductInfoRequestValidator.validate(advProductInfoReq)).thenReturn(null);
		// remove
		when(advisorService.fetchAdvProductByAdvId("ADV0000000001")).thenReturn(advProducts);
		doNothing().when(advisorService).removeAdvProduct(Mockito.anyLong(), Mockito.anyString());
		doNothing().when(advisorService).removeAdvBrandInfo(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString());
		doNothing().when(advisorService).removeFromBrandRank(Mockito.anyString(), Mockito.anyLong());
		when(advisorService.fetchAdvBrandInfoByAdvIdAndProdId(Mockito.anyString(), Mockito.anyLong()))
				.thenReturn(advBrandInfoList);
		when(advisorService.fetchPriorityByBrandIdAndAdvId(Mockito.anyString(), Mockito.anyLong(), Mockito.anyLong()))
				.thenReturn(advBrandInfoListPriority);
		when(advisorService.fetchAdvBrandRank(Mockito.anyString(), Mockito.anyLong(), Mockito.anyInt()))
				.thenReturn(null);
		doNothing().when(advisorService).addAdvBrandAndRank(Mockito.anyLong(), Mockito.anyInt(), Mockito.anyString(),
				Mockito.anyLong());
		// modify
		when(advisorService.fetchAdvProductByAdvIdAndAdvProdId(Mockito.anyString(), Mockito.anyLong()))
				.thenReturn(product1);
		doNothing().when(advisorService).modifyAdvisorProduct(Mockito.any(AdvProduct.class), Mockito.anyString());
		// add
		doNothing().when(advisorService).addAdvProductInfo("ADV0000000001", product3);

		// when(successResponse.createSuccessResponse(appMessages.getAdvisor_updated_successfully()))
		// .thenReturn(createSuccessResponse(appMessages.getAdvisor_updated_successfully()));

		String msg = appMessages.getAdvisor_updated_successfully();
		when(successResponse.createSuccessResponse(appMessages.getAdvisor_updated_successfully()))
				.thenReturn(createSuccessResponse(msg));
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.put("/modifyAdvProdInfo").content(jsonString)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		Assert.assertEquals(createSuccessResponse(appMessages.getAdvisor_updated_successfully()),
				result.getResponse().getContentAsString());
	}

	@Test
	public void test_AddProfInfo() throws Exception {
		AdvProfessionalInfoRequest advProfessionalInfoRequest = new AdvProfessionalInfoRequest();
		advProfessionalInfoRequest.setAdvId("ADV000000000A");
		List<AwardReq> awards = new ArrayList<AwardReq>();
		List<EducationReq> educations = new ArrayList<EducationReq>();
		List<ExperienceReq> experiences = new ArrayList<ExperienceReq>();

		advProfessionalInfoRequest.setAwards(awards);
		advProfessionalInfoRequest.setEducations(educations);
		advProfessionalInfoRequest.setExperiences(experiences);

		Advisor adv = new Advisor();
		adv.setAdvId("ADV000000000A");
		adv.setName("advisor");

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(advProfessionalInfoRequest);
		when(advisorService.fetchByAdvisorId(Mockito.anyString())).thenReturn(adv);
		when(advProfessionalInfoRequestValidator.validate(advProfessionalInfoRequest)).thenReturn(null);
		doNothing().when(advisorService).addAdvProfessionalInfo(Mockito.anyString(), Mockito.any(Advisor.class));
		// when(successResponse.createSuccessResponse(Mockito.anyString()))
		// .thenReturn(createSuccessResponse(appMessages.getAdvisor_added_successfully()));
		String msg = appMessages.getAdvisor_added_successfully();
		when(successResponse.createSuccessResponse(appMessages.getAdvisor_added_successfully()))
				.thenReturn(createSuccessResponse(msg));
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/addAdvProfInfo").content(jsonString)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		Assert.assertEquals(createSuccessResponse(appMessages.getAdvisor_added_successfully()),
				result.getResponse().getContentAsString());
	}

	@Test
	public void test_ModifyAdvProfInfo_Award() throws Exception {
		AdvProfessionalInfoRequest advProfessionalInfoRequest = new AdvProfessionalInfoRequest();
		advProfessionalInfoRequest.setAdvId("ADV000000000A");
		List<AwardReq> awards = new ArrayList<AwardReq>();
		AwardReq awardReq1 = new AwardReq();
		awardReq1.setAwardId(1);
		awardReq1.setTitle("award one");
		AwardReq awardReq2 = new AwardReq();
		awardReq2.setTitle("award two");
		awards.add(awardReq1);
		awards.add(awardReq2);
		advProfessionalInfoRequest.setAwards(awards);

		List<Award> award = new ArrayList<Award>();

		Award award1 = new Award();
		award1.setAwardId(1);
		award1.setTitle("award");
		Award award2 = new Award();
		award1.setAwardId(2);
		award1.setTitle("award two");
		award.add(award1);
		award.add(award2);

		Advisor adv = new Advisor();
		adv.setAdvId("ADV000000000A");
		adv.setName("advisor");

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(advProfessionalInfoRequest);

		when(advisorService.fetchByAdvisorId(Mockito.anyString())).thenReturn(adv);
		when(advProfessionalInfoRequestValidator.validate(advProfessionalInfoRequest)).thenReturn(null);
		// modifyAward
		when(advisorService.fetchAwardByadvId("ADV000000000A")).thenReturn(award);
		doNothing().when(advisorService).removeAdvAward(Mockito.anyLong(), Mockito.anyString());
		doNothing().when(advisorService).modifyAdvisorAward(Mockito.anyLong(), Mockito.any(Award.class),
				Mockito.anyString());
		doNothing().when(advisorService).addAdvAwardInfo(Mockito.anyString(), Mockito.any(Award.class));
		// when(successResponse.createSuccessResponse(appMessages.getAdvisor_updated_successfully()))
		// .thenReturn(createSuccessResponse(appMessages.getAdvisor_updated_successfully()));
		String msg = appMessages.getAdvisor_updated_successfully();
		when(successResponse.createSuccessResponse(appMessages.getAdvisor_updated_successfully()))
				.thenReturn(createSuccessResponse(msg));
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.put("/modifyAdvProfInfo").content(jsonString)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		Assert.assertEquals(createSuccessResponse(appMessages.getAdvisor_updated_successfully()),
				result.getResponse().getContentAsString());
	}

	@Test
	public void test_ModifyAdvProfInfo_Certificate() throws Exception {
		AdvProfessionalInfoRequest advProfessionalInfoRequest = new AdvProfessionalInfoRequest();
		advProfessionalInfoRequest.setAdvId("ADV000000000A");
		List<AwardReq> awards = new ArrayList<AwardReq>();
		AwardReq awardReq1 = new AwardReq();
		awardReq1.setAwardId(1);
		awardReq1.setTitle("award one");
		AwardReq awardReq2 = new AwardReq();
		awardReq2.setTitle("award two");
		awards.add(awardReq1);
		awards.add(awardReq2);
		advProfessionalInfoRequest.setAwards(awards);

		List<Award> award = new ArrayList<Award>();

		Award award1 = new Award();
		award1.setAwardId(1);
		award1.setTitle("award");
		Award award2 = new Award();
		award1.setAwardId(2);
		award1.setTitle("award two");
		award.add(award1);
		award.add(award2);

		Advisor adv = new Advisor();
		adv.setAdvId("ADV000000000A");
		adv.setName("advisor");

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(advProfessionalInfoRequest);

		when(advisorService.fetchByAdvisorId(Mockito.anyString())).thenReturn(adv);
		when(advProfessionalInfoRequestValidator.validate(advProfessionalInfoRequest)).thenReturn(null);
		// modifyAward
		when(advisorService.fetchAwardByadvId("ADV000000000A")).thenReturn(award);
		doNothing().when(advisorService).removeAdvAward(Mockito.anyLong(), Mockito.anyString());
		doNothing().when(advisorService).modifyAdvisorAward(Mockito.anyLong(), Mockito.any(Award.class),
				Mockito.anyString());
		doNothing().when(advisorService).addAdvAwardInfo(Mockito.anyString(), Mockito.any(Award.class));
		// when(successResponse.createSuccessResponse(appMessages.getAdvisor_updated_successfully()))
		// .thenReturn(createSuccessResponse(appMessages.getAdvisor_updated_successfully()));
		String msg = appMessages.getAdvisor_updated_successfully();
		when(successResponse.createSuccessResponse(appMessages.getAdvisor_updated_successfully()))
				.thenReturn(createSuccessResponse(msg));
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.put("/modifyAdvProfInfo").content(jsonString)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		Assert.assertEquals(createSuccessResponse(appMessages.getAdvisor_updated_successfully()),
				result.getResponse().getContentAsString());
	}

	@Test
	public void test_changePassword() throws Exception {
		PasswordChangeRequest passwordChangeRequest = new PasswordChangeRequest();
		passwordChangeRequest.setAdvId("ADV000000000A");
		passwordChangeRequest.setCurrentPassword("AS!@as12");
		passwordChangeRequest.setNewPassword("!@AS12as");
		Advisor adv = new Advisor();
		adv.setAdvId("ADV000000000A");
		adv.setName("advisor");

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(passwordChangeRequest);
		when(advisorService.fetchByAdvisorId(Mockito.anyString())).thenReturn(adv);
		when(advisorService.checkForPasswordMatch(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
		when(passwordValidator.validate(passwordChangeRequest)).thenReturn(null);
		Mockito.doNothing().when(advisorService).changeAdvPassword(Mockito.anyString(), Mockito.anyString());
		// when(successResponse.createSuccessResponse(Mockito.anyString()))
		// .thenReturn(createSuccessResponse(appMessages.getPassword_changed_successfully()));
		String msg = appMessages.getPassword_changed_successfully();
		when(successResponse.createSuccessResponse(appMessages.getPassword_changed_successfully()))
				.thenReturn(createSuccessResponse(msg));
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.put("/changePassword").content(jsonString)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		Assert.assertEquals(createSuccessResponse(appMessages.getPassword_changed_successfully()),
				result.getResponse().getContentAsString());
	}

	@Test
	public void test_AddAdvVideo() throws Exception {
		AdvVideoReq advVideoReq = new AdvVideoReq();
		advVideoReq.setAdvId("ADV000000000A");
		List<VideoReq> videoReq = new ArrayList<VideoReq>();
		advVideoReq.setVideos(videoReq);

		Advisor adv = new Advisor();
		adv.setAdvId("ADV000000000A");
		adv.setName("advisor");

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(advVideoReq);
		when(advisorService.fetchByAdvisorId(Mockito.anyString())).thenReturn(adv);
		when(advVideoRequestValidator.validate(advVideoReq)).thenReturn(null);
		doNothing().when(advisorService).addAdvVideo(Mockito.anyString(), Mockito.any(Advisor.class));
		// when(successResponse.createSuccessResponse(Mockito.anyString()))
		// .thenReturn(createSuccessResponse(appMessages.getAdvisor_added_successfully()));
		String msg = appMessages.getAdvisor_added_successfully();
		when(successResponse.createSuccessResponse(appMessages.getAdvisor_added_successfully()))
				.thenReturn(createSuccessResponse(msg));
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/addAdvVideo").content(jsonString)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		Assert.assertEquals(createSuccessResponse(appMessages.getAdvisor_added_successfully()),
				result.getResponse().getContentAsString());
	}

	@Test
	public void test_AddAdvBrandInfo() throws Exception {
		AdvBrandInfoReq advBrandInfoReq = new AdvBrandInfoReq();
		advBrandInfoReq.setProdId(1);
		advBrandInfoReq.setServiceId(1);
		advBrandInfoReq.setBrandId1(1);
		advBrandInfoReq.setBrandId2(2);
		advBrandInfoReq.setBrandId3(3);

		List<AdvBrandInfoReq> advBrandInfoReqList = new ArrayList<AdvBrandInfoReq>();
		advBrandInfoReqList.add(advBrandInfoReq);

		AdvBrandInfoRequest advBrandInfoRequest = new AdvBrandInfoRequest();
		advBrandInfoRequest.setAdvId("ADV000000000A");
		advBrandInfoRequest.setBrandInfoReqList(advBrandInfoReqList);

		AdvBrandInfo advBrandInfo = new AdvBrandInfo();
		List<AdvBrandInfo> advBrandInfoList = new ArrayList<>();
		List<Long> advBrandInfoListPriority = new ArrayList<>();

		Advisor adv = new Advisor();
		adv.setAdvId("ADV000000000A");
		adv.setName("advisor");

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(advBrandInfoRequest);

		when(advisorService.fetchByAdvisorId(Mockito.anyString())).thenReturn(adv);
		doNothing().when(advisorService).removeAdvBrandInfoByAdvId("ADV000000000A");
		doNothing().when(advisorService).removeAdvBrandRankByAdvId("ADV000000000A");
		doNothing().when(advisorService).addAdvBrandInfo("ADV000000000A", advBrandInfo);

		when(advisorService.fetchAdvBrandInfoByAdvIdAndProdId(Mockito.anyString(), Mockito.anyLong()))
				.thenReturn(advBrandInfoList);
		when(advisorService.fetchPriorityByBrandIdAndAdvId(Mockito.anyString(), Mockito.anyLong(), Mockito.anyLong()))
				.thenReturn(advBrandInfoListPriority);
		when(advisorService.fetchAdvBrandRank(Mockito.anyString(), Mockito.anyLong(), Mockito.anyInt()))
				.thenReturn(null);
		doNothing().when(advisorService).addAdvBrandAndRank(Mockito.anyLong(), Mockito.anyInt(), Mockito.anyString(),
				Mockito.anyLong());
		// when(successResponse.createSuccessResponse(appMessages.getAdvisor_added_successfully()))
		// .thenReturn(createSuccessResponse(appMessages.getAdvisor_added_successfully()));

		String msg = appMessages.getAdvisor_added_successfully();
		when(successResponse.createSuccessResponse(appMessages.getAdvisor_added_successfully()))
				.thenReturn(createSuccessResponse(msg));
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/addAdvBrandInfo").content(jsonString)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		Assert.assertEquals(createSuccessResponse(appMessages.getAdvisor_added_successfully()),
				result.getResponse().getContentAsString());
	}

	@Test
	public void test_fetchAllServiceAndBrand() throws Exception {
		List<Product> products = new ArrayList<Product>();
		Product prod1 = new Product();
		prod1.setProdId(1);
		prod1.setProduct("ABC");
		Product prod2 = new Product();
		prod2.setProdId(2);
		prod2.setProduct("DEF");
		products.add(prod1);
		products.add(prod2);
		when(advisorService.fetchAllServiceAndBrand()).thenReturn(products);
		mockMvc.perform(get("/fetch-all-productServBrand")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].prodId", is(1)))
				.andExpect(jsonPath("$[0].product", is("ABC"))).andExpect(jsonPath("$[1].prodId", is(2)))
				.andExpect(jsonPath("$[1].product", is("DEF")));

		verify(advisorService, times(1)).fetchAllServiceAndBrand();
		verifyNoMoreInteractions(advisorService);

	}

	@Test
	public void test_fetchCategoryList() throws Exception {
		List<Category> category = new ArrayList<Category>();
		Category category1 = new Category();
		category1.setCategoryId(1);
		category1.setDesc("life insurance");
		Category category2 = new Category();
		category2.setCategoryId(2);
		category2.setDesc("general insurance");
		category.add(category1);
		category.add(category2);

		when(advisorService.fetchCategoryList()).thenReturn(category);
		mockMvc.perform(get("/fetch-all-Category")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].categoryId", is(1))).andExpect(jsonPath("$[0].desc", is("life insurance")))
				.andExpect(jsonPath("$[1].categoryId", is(2)))
				.andExpect(jsonPath("$[1].desc", is("general insurance")));
		verify(advisorService, times(1)).fetchCategoryList();
		verifyNoMoreInteractions(advisorService);

	}

	@Test
	public void test_fetchCategoryTypeList() throws Exception {
		List<CategoryType> categoryTypeList = new ArrayList<CategoryType>();
		CategoryType category1 = new CategoryType();
		category1.setCategoryTypeId(1);
		category1.setDesc("investment");
		CategoryType category2 = new CategoryType();
		category2.setCategoryTypeId(2);
		category2.setDesc("accounting");
		categoryTypeList.add(category1);
		categoryTypeList.add(category2);

		when(advisorService.fetchCategoryTypeList()).thenReturn(categoryTypeList);
		mockMvc.perform(get("/fetch-all-CategoryType")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].categoryTypeId", is(1))).andExpect(jsonPath("$[0].desc", is("investment")))
				.andExpect(jsonPath("$[1].categoryTypeId", is(2))).andExpect(jsonPath("$[1].desc", is("accounting")));
		verify(advisorService, times(1)).fetchCategoryTypeList();
		verifyNoMoreInteractions(advisorService);

	}

	@Test
	public void test_fetchForumCategoryList() throws Exception {
		List<ForumCategory> forumCategoryList = new ArrayList<ForumCategory>();
		ForumCategory forumCategory1 = new ForumCategory();
		forumCategory1.setId(1);
		forumCategory1.setName("mutual funds");
		ForumCategory forumCategory2 = new ForumCategory();
		forumCategory2.setId(2);
		forumCategory2.setName("stock");
		forumCategoryList.add(forumCategory1);
		forumCategoryList.add(forumCategory2);
		when(advisorService.fetchForumCategoryList()).thenReturn(forumCategoryList);
		mockMvc.perform(get("/fetch-all-ForumCategory")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].name", is("mutual funds")))
				.andExpect(jsonPath("$[1].id", is(2))).andExpect(jsonPath("$[1].name", is("stock")));
		verify(advisorService, times(1)).fetchForumCategoryList();
		verifyNoMoreInteractions(advisorService);

	}

	@Test
	public void test_fetchRiskQuestionaireList() throws Exception {
		List<RiskQuestionaire> riskQuestionaires = new ArrayList<RiskQuestionaire>();
		RiskQuestionaire risk = new RiskQuestionaire();
		risk.setQuestionId("1");
		risk.setQuestion("RiskQuestionaire");
		riskQuestionaires.add(risk);
		when(advisorService.fetchRiskQuestionaireList()).thenReturn(riskQuestionaires);
		mockMvc.perform(get("/fetch-all-RiskQuestionaire")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].questionId", is("1")))
				.andExpect(jsonPath("$[0].question", is("RiskQuestionaire")));
		verify(advisorService, times(1)).fetchRiskQuestionaireList();
		verifyNoMoreInteractions(advisorService);
	}

	@Test
	public void test_fetchGoalsServedList() throws Exception {
		List<GoalsServed> goalsServedList = new ArrayList<GoalsServed>();
		GoalsServed goalsServed = new GoalsServed();
		goalsServed.setGoalsServedId(1);
		goalsServed.setGoalsServed("GoalsServed");
		goalsServedList.add(goalsServed);
		when(advisorService.fetchGoalsServedList()).thenReturn(goalsServedList);
		mockMvc.perform(get("/fetch-all-GoalsServed")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].goalsServedId", is(1)))
				.andExpect(jsonPath("$[0].goalsServed", is("GoalsServed")));
		verify(advisorService, times(1)).fetchGoalsServedList();
		verifyNoMoreInteractions(advisorService);
	}

	@Test
	public void test_fetchProductList() throws Exception {
		List<Product> productList = new ArrayList<Product>();
		Product product = new Product();
		product.setProdId(1);
		product.setProduct("Product");
		productList.add(product);
		when(advisorService.fetchProductList()).thenReturn(productList);
		mockMvc.perform(get("/fetch-all-product")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].prodId", is(1))).andExpect(jsonPath("$[0].product", is("Product")));
		verify(advisorService, times(1)).fetchProductList();
		verifyNoMoreInteractions(advisorService);
	}

	@Test
	public void test_fetchSpecialisedSkills() throws Exception {
		List<SpecialisedSkills> specialisedSkills = new ArrayList<SpecialisedSkills>();
		SpecialisedSkills skill = new SpecialisedSkills();
		skill.setSpecialisedSkillId(1);
		skill.setSpecialisedSkill("SpecialisedSkills");
		specialisedSkills.add(skill);
		when(advisorService.fetchSpecialisedSkills()).thenReturn(specialisedSkills);
		mockMvc.perform(get("/fetch-all-SpecialisedSkills")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].specialisedSkillId", is(1)))
				.andExpect(jsonPath("$[0].specialisedSkill", is("SpecialisedSkills")));
		verify(advisorService, times(1)).fetchSpecialisedSkills();
		verifyNoMoreInteractions(advisorService);
	}

	@Test
	public void test_fetchRoleList() throws Exception {
		List<Role> roleList = new ArrayList<Role>();
		Role role = new Role();
		role.setId(1);
		role.setName("Advisor");
		Role role2 = new Role();
		role2.setId(2);
		role2.setName("Investor");
		roleList.add(role);
		roleList.add(role2);
		when(advisorService.fetchRoleList()).thenReturn(roleList);
		mockMvc.perform(get("/fetch-all-role")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].name", is("Advisor")))
				.andExpect(jsonPath("$[1].id", is(2))).andExpect(jsonPath("$[1].name", is("Investor")));
		verify(advisorService, times(1)).fetchRoleList();
		verifyNoMoreInteractions(advisorService);
	}

	@Test
	public void test_fetchForumSubCategoryList() throws Exception {
		List<ForumSubCategory> forumSubCategoryList = new ArrayList<ForumSubCategory>();
		ForumSubCategory forumSubCategory = new ForumSubCategory();
		forumSubCategory.setCategoryId(1);
		forumSubCategory.setName("Forum SubCategory");
		forumSubCategoryList.add(forumSubCategory);
		when(advisorService.fetchForumSubCategoryList()).thenReturn(forumSubCategoryList);
		mockMvc.perform(get("/fetch-all-ForumSubCategory")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].categoryId", is(1)))
				.andExpect(jsonPath("$[0].name", is("Forum SubCategory")));
		verify(advisorService, times(1)).fetchForumSubCategoryList();
		verifyNoMoreInteractions(advisorService);
	}

	@Test
	public void test_fetchForumStatusList() throws Exception {
		List<ForumStatus> forumStatusList = new ArrayList<ForumStatus>();
		ForumStatus forumStatus = new ForumStatus();
		forumStatus.setId(2);
		forumStatus.setDesc("Forum Status");
		forumStatusList.add(forumStatus);
		when(advisorService.fetchForumStatusList()).thenReturn(forumStatusList);
		mockMvc.perform(get("/fetch-all-ForumStatus")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(2))).andExpect(jsonPath("$[0].desc", is("Forum Status")));
		verify(advisorService, times(1)).fetchForumStatusList();
		verifyNoMoreInteractions(advisorService);
	}

	@Test
	public void test_fetchPartyStatusList() throws Exception {
		List<PartyStatus> partyStatusList = new ArrayList<PartyStatus>();
		PartyStatus partyStatus = new PartyStatus();
		partyStatus.setId(1);
		partyStatus.setDesc("Party Status");
		partyStatusList.add(partyStatus);
		when(advisorService.fetchPartyStatusList()).thenReturn(partyStatusList);
		mockMvc.perform(get("/fetch-all-partystatus")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].desc", is("Party Status")));
		verify(advisorService, times(1)).fetchPartyStatusList();
		verifyNoMoreInteractions(advisorService);
	}

	@Test
	public void test_fetchServiceList() throws Exception {
		List<Service> serviceList = new ArrayList<Service>();
		Service service = new Service();
		service.setServiceId(1);
		service.setService("service");
		serviceList.add(service);
		when(advisorService.fetchServiceList()).thenReturn(serviceList);
		mockMvc.perform(get("/fetch-all-service")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].serviceId", is(1))).andExpect(jsonPath("$[0].service", is("service")));
		verify(advisorService, times(1)).fetchServiceList();
		verifyNoMoreInteractions(advisorService);
	}

	@Test
	public void test_fetchBrandList() throws Exception {
		List<Brand> brandList = new ArrayList<Brand>();
		Brand brand = new Brand();
		brand.setBrandId(2);
		brand.setBrand("LIC");
		brand.setProdId(3);
		brandList.add(brand);
		when(advisorService.fetchBrandList()).thenReturn(brandList);
		mockMvc.perform(get("/fetch-all-brand")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].brandId", is(2))).andExpect(jsonPath("$[0].brand", is("LIC")))
				.andExpect(jsonPath("$[0].prodId", is(3)));
		verify(advisorService, times(1)).fetchBrandList();
		verifyNoMoreInteractions(advisorService);
	}

	@Test
	public void test_fetchLicenseList() throws Exception {
		List<License> licenseList = new ArrayList<License>();
		License license = new License();
		license.setLicId(1);
		license.setLicense("License");
		license.setProdId(1);
		licenseList.add(license);
		when(advisorService.fetchLicenseList()).thenReturn(licenseList);
		mockMvc.perform(get("/fetch-all-license")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].licId", is(1))).andExpect(jsonPath("$[0].license", is("License")))
				.andExpect(jsonPath("$[0].prodId", is(1)));
		verify(advisorService, times(1)).fetchLicenseList();
		verifyNoMoreInteractions(advisorService);
	}

	@Test
	public void test_fetchRemunerationList() throws Exception {
		List<Remuneration> remunerationList = new ArrayList<Remuneration>();
		Remuneration rem1 = new Remuneration();
		rem1.setRemId(1);
		rem1.setRemuneration("Fee Based");
		Remuneration rem2 = new Remuneration();
		rem2.setRemId(2);
		rem2.setRemuneration("Commision Based");
		remunerationList.add(rem1);
		remunerationList.add(rem2);
		when(advisorService.fetchRemunerationList()).thenReturn(remunerationList);
		mockMvc.perform(get("/fetch-all-remuneration")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].remId", is(1))).andExpect(jsonPath("$[0].remuneration", is("Fee Based")))
				.andExpect(jsonPath("$[1].remId", is(2)))
				.andExpect(jsonPath("$[1].remuneration", is("Commision Based")));
		verify(advisorService, times(1)).fetchRemunerationList();
		verifyNoMoreInteractions(advisorService);
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
		when(advisorService.fetchAllStateCityPincode()).thenReturn(stateList);
		mockMvc.perform(get("/fetch-all-stateCityPincode")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].stateId", is(1)))
				.andExpect(jsonPath("$[0].state", is("TamilNadu"))).andExpect(jsonPath("$[1].stateId", is(2)))
				.andExpect(jsonPath("$[1].state", is("Kerala")));
		verify(advisorService, times(1)).fetchAllStateCityPincode();
		verifyNoMoreInteractions(advisorService);
	}

	@Test
	public void test_fetchAdvBrandRankByAdvId() throws Exception {
		AdvIdRequest advIdReq = new AdvIdRequest();
		advIdReq.setAdvId("ADV000000000A");
		List<AdvBrandRank> advBrandRankList = new ArrayList<AdvBrandRank>();
		AdvBrandRank advBrandRank = new AdvBrandRank();
		advBrandRank.setAdvId("ADV000000000A");
		advBrandRank.setAdvBrandRankId(1);
		advBrandRank.setRanking(1);
		advBrandRankList.add(advBrandRank);

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(advIdReq);

		when(advisorService.fetchAdvBrandRankByAdvId(Mockito.anyString())).thenReturn(advBrandRankList);
		mockMvc.perform(MockMvcRequestBuilders.post("/fetchAdvBrandRank").content(jsonString)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].advId", is("ADV000000000A")))
				.andExpect(jsonPath("$[0].advBrandRankId", is(1))).andExpect(jsonPath("$[0].ranking", is(1)))
				.andExpect(status().isOk());
		verify(advisorService, times(1)).fetchAdvBrandRankByAdvId("ADV000000000A");
		verifyNoMoreInteractions(advisorService);

	}

	public String createSuccessResponse(String text) {
		String response = "";
		try {
			response = new ObjectMapper().writeValueAsString(text);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return response;
	}
}