//package com.sowisetech.advisor.request;
//
//import static org.junit.Assert.*;
//
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import org.apache.commons.lang3.StringUtils;
//import org.junit.Before;
//import org.junit.Test;
//
//public class AdvProductInfoRequestValidatorTest {
//
////	AdvProductInfoRequestValidator advProductInfoRequestValidator;
////	
////	@Before
////	public void setUp() throws Exception {
////		advProductInfoRequestValidator = new AdvProductInfoRequestValidator();
////	}
////	
////	
////	@Test
////	public void validateProductTest() {
////		
////		List<AdvProductRequest> products = new ArrayList<AdvProductRequest>();
////		AdvProductRequest product=new AdvProductRequest();
////		List<String> services= new ArrayList<String>();
////		services.add("service one");
////		services.add("service two");
////		product.setProdId("loan");
////		product.setServices(services);
////		product.setRemuneration("Fee");
////		product.setBrand("LIC");
////		product.setCertificate("certificate");
////		product.setCertificateNumber("ABCD1234");
////		product.setIssuedBy("IRDA");
////		product.setValidity("19-05-2019");
////		product.setImagePath("Image.jpg");
////		products.add(product);
////				
////		HashMap<String, String> errors = new HashMap<String, String>();
////		errors = advProductInfoRequestValidator.validateProducts(products);
////		for (String error : errors.values()) {
////			assertTrue(StringUtils.isEmpty(error) == true);
////		}
////	}
//	
//	//PAN test
////	@Test
////	public void validatePanTest() {
////		String pan = "AAAPL1234C";
////		List<String> errors = new ArrayList<String>();
////		errors = advAdditionalInfoRequestValidator.validatePan(pan);
////		for (String error : errors) {
////			assertTrue(StringUtils.isEmpty(error) == true);
////		}
////	}
////	
////	//Goals Served test
////	@Test
////	public void validateGoalsServedTest() {
////		String goals = "Tax Planning,Buying House";
////		List<String> errors = new ArrayList<String>();
////		errors = advAdditionalInfoRequestValidator.validateGoalsServed(goals);
////		for (String error : errors) {
////			assertTrue(StringUtils.isEmpty(error) == true);
////		}
////	}
////
////	//Skills test
////	@Test
////	public void validateSpecializedSkillsTest() {
////		String skills = "Goal Setting,SIP advice";
////		List<String> errors = new ArrayList<String>();
////		errors = advAdditionalInfoRequestValidator.validateSpecializedSkills(skills);
////		for (String error : errors) {
////			assertTrue(StringUtils.isEmpty(error) == true);
////		}
////	}
////	
////	//Expertiselevel test
////	@Test
////	public void validateExpertiseLevelTest() {
////		String level = "1";
////		List<String> errors = new ArrayList<String>();
////		errors = advAdditionalInfoRequestValidator.validateExpertiseLevel(level);
////		for (String error : errors) {
////			assertTrue(StringUtils.isEmpty(error) == true);
////		}
////	}
////	
////	//validtill test
////	@Test
////	public void validateValidTillTest() {
////		String validtill = "19-05-2019";
////		List<String> errors = new ArrayList<String>();
////		errors = advAdditionalInfoRequestValidator.validateValidTill(validtill);
////		for (String error : errors) {
////			assertTrue(StringUtils.isEmpty(error) == true);
////		}
////	}
////	
////	//Remuneration test
////	@Test
////	public void validateRemunerationTest() {
////		String remuneration = "commision";
////		List<String> errors = new ArrayList<String>();
////		errors = advAdditionalInfoRequestValidator.validateRemuneration(remuneration);
////		for (String error : errors) {
////			assertTrue(StringUtils.isEmpty(error) == true);
////		}
////	}
////	
////	
////	//Legalno. test
////	@Test
////	public void validateLegalNoTest() {
////		String legalNo = "AA65874587";
////		List<String> errors = new ArrayList<String>();
////		errors = advAdditionalInfoRequestValidator.validateLegalNo(legalNo);
////		for (String error : errors) {
////			assertTrue(StringUtils.isEmpty(error) == true);
////		}
////	}
////	
////	//Reprentation test
////	@Test
////	public void validateRepresentationTest() {
////		String representation = "Star Health Insurance";
////		List<String> errors = new ArrayList<String>();
////		errors = advAdditionalInfoRequestValidator.validateRepresentation(representation);
////		for (String error : errors) {
////			assertTrue(StringUtils.isEmpty(error) == true);
////		}
////	}
////	
////	
////	//CertificationName test
////	@Test
////	public void validateCertificationNameTest() {
////		String certificationName = "IRDA";
////		List<String> errors = new ArrayList<String>();
////		errors = advAdditionalInfoRequestValidator.validateRepresentation(certificationName);
////		for (String error : errors) {
////			assertTrue(StringUtils.isEmpty(error) == true);
////		}
////	}
////	
////	//Product Test
////	@Test
////	public void validateProductTest() {
////		String product = "Life Insurance";
////		List<String> errors = new ArrayList<String>();
////		errors = advAdditionalInfoRequestValidator.validateProduct(product);
////		for (String error : errors) {
////			assertTrue(StringUtils.isEmpty(error) == true);
////		}
////	}
//}
