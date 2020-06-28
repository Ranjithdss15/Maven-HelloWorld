//package com.sowisetech.advisor.request;
//
//import static org.junit.Assert.*;
//
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
//public class AdvProfessionalInfoRequestValidatorTest {
//
//	AdvProfessionalInfoRequestValidator advProfessionalInfoRequestValidator;
//	
//	@Before
//	public void setUp() throws Exception {
//		advProfessionalInfoRequestValidator = new AdvProfessionalInfoRequestValidator();
//	}
//	
//	
//	//Awards Test
//	@Test
//	public void validateAwardsTest() {
//		
//		List<AwardReq> awards = new ArrayList<AwardReq>();
//		AwardReq award=new AwardReq();
//		award.setYear("MAY-1996");
//		award.setTitle("Upper Crest Award");
//		award.setIssuedBy("HDFC Mutual Funds");
//		award.setImagePath("C:\\Users\\Employee2\\Downloads\\download.jpg");
//		awards.add(award);
//				
//		HashMap<String, String> errors = new HashMap<String, String>();
//		errors = advProfessionalInfoRequestValidator.validateAwards(awards,"ADV000000000N");
//		for (String error : errors.values()) {
//			assertTrue(StringUtils.isEmpty(error) == true);
//		}
//	}
//	
//	//Certificate Test
//	@Test
//	public void validateCertificateTest() {
//		
//		List<CertificateReq> certificates = new ArrayList<CertificateReq>();
//		CertificateReq certificate=new CertificateReq();
//		certificate.setYear("MAR-2008");
//		certificate.setTitle("Upper Crest Award");
//		certificate.setIssuedBy("HDFC Mutual Funds");
//		certificate.setImagePath("C:\\Users\\Employee2\\Downloads\\download.jpg");
//		certificates.add(certificate);
//				
//		HashMap<String, String> errors = new HashMap<String, String>();
//		errors = advProfessionalInfoRequestValidator.validateCertificates(certificates,"ADV000000000N");
//		for (String error : errors.values()) {
//			assertTrue(StringUtils.isEmpty(error) == true);
//		}
//	}
//	//Education Test
//	@Test
//	public void validateEducationsTest() {
//		
//		List<EducationReq> educations = new ArrayList<EducationReq>();
//		EducationReq education=new EducationReq();
//		education.setDegree("MBA");
//		education.setField("Finance");
//		education.setInstitution("NIIT");
//		education.setFromYear("FEB-2001");
//		education.setToYear("FEB-2001");
//		educations.add(education);
//				
//		HashMap<String, String> errors = new HashMap<String, String>();
//		errors = advProfessionalInfoRequestValidator.validateEducations(educations,"ADV000000000N");
//		for (String error : errors.values()) {
//			assertTrue(StringUtils.isEmpty(error) == true);
//		}
//	}
//	
//	
//	//Experience Test
//	@Test
//	public void validateExperienceTest() {
//		
//		List<ExperienceReq> experiences = new ArrayList<ExperienceReq>();
//		ExperienceReq experience=new ExperienceReq();
//		experience.setCompany("Reliance mutual Funds");
//		experience.setDesignation("Branch Manager");
//		experience.setFromYear("FEB-2016");
//		experience.setToYear("APR-2018");
//		experience.setLocation("Bangalore");
//		experiences.add(experience);
//				
//		HashMap<String, String> errors = new HashMap<String, String>();
//		errors = advProfessionalInfoRequestValidator.validateExperiences(experiences,"ADV000000000N");
//		for (String error : errors.values()) {
//			assertTrue(StringUtils.isEmpty(error) == true);
//		}
//	}
//	
//}
//	
