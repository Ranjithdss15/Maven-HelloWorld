//package com.sowisetech.advisor.request;
//
//import static org.junit.Assert.assertTrue;
//
//import java.util.HashMap;
//
//import org.apache.commons.lang3.StringUtils;
//import org.junit.Before;
//import org.junit.Test;
//
//public class PasswordValidatorTest {
//	PasswordValidator passwordValidator;
//
//	@Before
//	public void setUp() throws Exception {
//		passwordValidator = new PasswordValidator();
//	}
//	@Test
//	public void validatePasswordTest() {
//		String password = "Ah17@bcj";
//		HashMap<String, String> errors = new HashMap<String, String>();
//		errors = passwordValidator.validatePassword(password);
//		for (String error : errors.values()) {
//			assertTrue(StringUtils.isEmpty(error) == true);
//
//		}
//	}
//
//}
