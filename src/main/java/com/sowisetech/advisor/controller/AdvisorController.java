package com.sowisetech.advisor.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
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
import com.sowisetech.advisor.request.AdvBrandInfoReq;
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
import com.sowisetech.advisor.response.ErrorResponse;
import com.sowisetech.advisor.response.SuccessResponse;
import com.sowisetech.advisor.service.AdvisorService;
import com.sowisetech.advisor.util.AppMessages;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdvisorController {
	
	@Autowired
	AppMessages appMessages;

	@Autowired
	AdvisorRequest advisorRequest;

	@Autowired
	AdvisorRequestValidator advisorRequestValidator;

	@Autowired
	ErrorResponse errorResponse;
	@Autowired
	SuccessResponse successResponse;

	@Autowired
	AdvisorService advisorService;

	@Autowired
	AwardReq awardRequest;
	@Autowired
	AdvPersonalInfoRequest advPersonalInfoRequest;
	@Autowired
	AdvPersonalInfoRequestValidator advPersonalInfoRequestValidator;
	@Autowired
	AdvProductInfoRequest advProductInfoRequest;

	@Autowired
	AdvProductInfoRequestValidator advProductInfoRequestValidator;

	@Autowired
	AdvProfessionalInfoRequest advProfInfoRequest;

	@Autowired
	AdvProfessionalInfoRequestValidator advProfessionalInfoRequestValidator;
	@Autowired
	PasswordChangeRequest passwordChangeRequest;
	@Autowired
	ModifyAdvRequest modifyAdvRequest;
	@Autowired
	ModifyAdvReqValidator modifyAdvReqValidator;
	@Autowired
	PasswordValidator passwordValidator;
	@Autowired
	AdvVideoReq advVideoReq;
	@Autowired
	AdvBrandInfoRequest advBrandInfoRequest;
	@Autowired
	AdvVideoRequestValidator advVideoRequestValidator;

	private static final Logger logger = LoggerFactory.getLogger(AdvisorController.class);

	/**
	 * Extended Content Verification - Health check
	 * 
	 * @return ResponseEntity - HttpStatus.OK
	 */
	@RequestMapping(value = "/ecv", method = RequestMethod.GET)
	public ResponseEntity getEcv() {
		logger.info("Advisor module running.");
		// To view the log file location
		// System.out.println(System.getProperty("user.dir"));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Method to add advisor to the Maria database.
	 * 
	 * @param requestEntity
	 *            contains the <code>AdvisorRequest</code>
	 * @return ResponseEntity<String> contains the either the Result of advisor
	 *         addition or <code>ErrorResponse</code>
	 */
	/*---Fetch all Records ---*/
	@GetMapping("/fetch-all")
	public ResponseEntity<List<Advisor>> fetchAdvisorList() {
		List<Advisor> advisors = advisorService.fetchAdvisorList();
		return ResponseEntity.ok().body(advisors);
	}

	/*---- fetch a Record by advId---*/

	@RequestMapping(value = "/fetch", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> fetchByAdvisorID(@RequestBody AdvIdRequest advIdRequest) {
		String advId = advIdRequest.getAdvId();
		Advisor advisor = advisorService.fetchByAdvisorId(advId);
		if (advisor == null) {
			logger.info("No record Found");
			return ResponseEntity.ok().body(errorResponse.createResponse(appMessages.getNo_record_found()));
		} else {
			return ResponseEntity.ok().body(advisor);
		}
	}

	/*---Remove a Record by advId---*/
	@RequestMapping(value = "/remove", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<String> deleteAdvisor(@RequestBody AdvIdRequest advIdRequest) {
		String advId = advIdRequest.getAdvId();
		Advisor advisor = advisorService.fetchByAdvisorId(advId);
		if (advisor == null) {
			logger.info("No record Found");
			return ResponseEntity.ok().body(errorResponse.createResponse(appMessages.getNo_record_found()));
		} else {
			advisorService.removeAdvisor(advId);
			return ResponseEntity.ok()
					.body(successResponse.createSuccessResponse(appMessages.getAdvisor_deleted_successfully()));
		}
	}

	// Add Advisor Data in advisor table
	@RequestMapping(value = "/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<String> advSignup(@RequestBody AdvisorRequest advReq) {
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		String advResponseStr = "";
		try {
			// Convert input json request to AdvisorRequest object.
			advisorRequest = advReq;
			// Validating Advisor Request
			errors = advisorRequestValidator.validate(advisorRequest);
			if (errors.isEmpty() == true) {
				if (advisorRequest.getName() != null && advisorRequest.getEmailId() != null
						&& advisorRequest.getPassword() != null && advisorRequest.getPhoneNumber() != null) {
					if (advisorService.fetchAdvisorByEmailId(advisorRequest.getEmailId()) == null) {
						Advisor adv = getValue();// get value Method call
						// Generate Advisor Id/addAdvProdInfo
						
						adv.setAdvId(advisorService.generateId());
						advisorService.advSignup(adv);
						return ResponseEntity.ok()
								.body(successResponse.createSuccessResponse(appMessages.getAdvisor_added_successfully()));
					} else {
						logger.info("Advisor already present");
						return ResponseEntity.ok()
								.body(errorResponse.createResponse(appMessages.getAdvisor_already_present()));
					}
				} else {
					return ResponseEntity.ok().body(errorResponse.createResponse(appMessages.getFields_cannot_be_empty()));
				}
			} else if (errors.isEmpty() == false) {
				return new ResponseEntity<String>(errorResponse.createResponse(errors),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			advResponseStr = new ObjectMapper()
					.writeValueAsString(successResponse.createSuccessResponse(appMessages.getAdvisor_added_successfully()));
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<String>(errorResponse.createResponse(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(advResponseStr, HttpStatus.OK);
	}

	public Advisor getValue() {
		Advisor adv = new Advisor();
		adv.setName(advisorRequest.getName());
		adv.setEmailId(advisorRequest.getEmailId());
		adv.setPanNumber(advisorRequest.getPanNumber());
		adv.setPhoneNumber(advisorRequest.getPhoneNumber());
		adv.setPassword(advisorRequest.getPassword());
		adv.setPartyStatusId(1);
		return adv;
	}

	// This service api is not used in normal advisor flow, but it may be used in
	// admin flow
	@RequestMapping(value = "/modify", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<String> modifyAdvisor(@RequestBody ModifyAdvRequest modifyAdvReq) {
		String advId = modifyAdvReq.getAdvId();
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		String advResponseStr = "";
		Advisor advisor = advisorService.fetchByAdvisorId(advId);
		if (advisor == null) {
			logger.info("No record found");
			return ResponseEntity.ok().body(errorResponse.createResponse(appMessages.getNo_record_found()));
		}

		else {
			try {
				// Convert input json request to AdvisorRequest object.
				modifyAdvRequest = modifyAdvReq;
				// Validating advisor request
				errors = modifyAdvReqValidator.validate(modifyAdvRequest);
				if (errors.isEmpty() == true) {
					Advisor adv = getModifiedValue();// get value Method call
					advisorService.modifyAdvisor(advId, adv);
					return ResponseEntity.ok()
							.body(successResponse.createSuccessResponse(appMessages.getAdvisor_updated_successfully()));
				} else if (errors.isEmpty() == false) {
					return new ResponseEntity<String>(errorResponse.createResponse(errors),
							HttpStatus.INTERNAL_SERVER_ERROR);

				}
				advResponseStr = new ObjectMapper().writeValueAsString(
						successResponse.createSuccessResponse(appMessages.getAdvisor_updated_successfully()));
			} catch (JsonProcessingException e) {
				logger.error(e.getMessage());
				return new ResponseEntity<String>(errorResponse.createResponse(e.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<String>(advResponseStr, HttpStatus.OK);
		}
	}

	private Advisor getModifiedValue() {
		Advisor adv = new Advisor();
		adv.setName(modifyAdvRequest.getName());
		adv.setDesignation(modifyAdvRequest.getDesignation());
		adv.setEmailId(modifyAdvRequest.getEmailId());
		adv.setPhoneNumber(modifyAdvRequest.getPhoneNumber());
		adv.setDisplayName(modifyAdvRequest.getDisplayName());
		adv.setDob(modifyAdvRequest.getDob());
		adv.setGender(modifyAdvRequest.getGender());
		adv.setPanNumber(modifyAdvRequest.getPanNumber());
		adv.setAddress1(modifyAdvRequest.getAddress1());
		adv.setAddress2(modifyAdvRequest.getAddress2());
		adv.setState(modifyAdvRequest.getState());
		adv.setCity(modifyAdvRequest.getCity());
		adv.setPincode(modifyAdvRequest.getPincode());
		adv.setAboutme(modifyAdvRequest.getAboutme());
		return adv;
	}

	// Add Advisor Personal Information in advisor table
	@RequestMapping(value = "/addAdvPersonalInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<String> addAdvPersonalInfo(@RequestBody AdvPersonalInfoRequest advPersonalInfoReq) {
		String advId = advPersonalInfoReq.getAdvId();
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		String advResponseStr = "";
		Advisor advisor = advisorService.fetchByAdvisorId(advId);
		if (advisor == null) {
			logger.info("No record found");
			return ResponseEntity.ok().body(errorResponse.createResponse(appMessages.getNo_record_found()));
		} else {
			try {
				// Convert input json request to AdvisorRequest object.
				advPersonalInfoRequest = advPersonalInfoReq;
				// Validating advPersonalInfoReq
				errors = advPersonalInfoRequestValidator.validate(advPersonalInfoRequest);
				if (errors.isEmpty() == true) {
					Advisor adv = getValuePersonalInfo();// get value Method call

					advisorService.addAdvPersonalInfo(advId, adv);
					return ResponseEntity.ok()
							.body(successResponse.createSuccessResponse(appMessages.getAdvisor_info_added_successfully()));
				} else if (errors.isEmpty() == false) {
					return new ResponseEntity<String>(errorResponse.createResponse(errors),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
				advResponseStr = new ObjectMapper().writeValueAsString(
						successResponse.createSuccessResponse(appMessages.getAdvisor_info_added_successfully()));
			} catch (JsonProcessingException e) {
				logger.error(e.getMessage());
				return new ResponseEntity<String>(errorResponse.createResponse(e.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<String>(advResponseStr, HttpStatus.OK);
		}
	}

	private Advisor getValuePersonalInfo() {
		Advisor adv = new Advisor();
		adv.setDisplayName(advPersonalInfoRequest.getDisplayName());
		adv.setDesignation(advPersonalInfoRequest.getDesignation());
		adv.setDob(advPersonalInfoRequest.getDob());
		adv.setGender(advPersonalInfoRequest.getGender());
		// adv.setPanNumber(advPersonalInfoRequest.getPanNumber());
		adv.setAddress1(advPersonalInfoRequest.getAddress1());
		adv.setAddress2(advPersonalInfoRequest.getAddress2());
		adv.setState(advPersonalInfoRequest.getState());
		adv.setCity(advPersonalInfoRequest.getCity());
		adv.setPincode(advPersonalInfoRequest.getPincode());
		adv.setAboutme(advPersonalInfoRequest.getAboutme());
		return adv;
	}

	// Add Advisor Product Info
	@RequestMapping(value = "/addAdvProdInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<String> addAdvProdInfo(@RequestBody AdvProductInfoRequest advProdInfoReq) {
		String advId = advProdInfoReq.getAdvId();
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		String advResponseStr = "";
		Advisor advisor = advisorService.fetchByAdvisorId(advId);
		if (advisor == null) {
			logger.info("No record found");
			return ResponseEntity.ok().body(errorResponse.createResponse(appMessages.getNo_record_found()));
		} else {
			try {
				// Convert input json request to AdvisorRequest object.
				advProductInfoRequest = advProdInfoReq;
				// validating advProductInfoRequest
				errors = advProductInfoRequestValidator.validate(advProductInfoRequest);
				if (errors.isEmpty() == true) {
					// Advisor adv = getValueProdInfo(advProductInfoRequest);

					for (AdvProductRequest advProductRequest : advProductInfoRequest.getAdvProducts()) {
						AdvProduct advProduct = getValueAdvProductInfo(advProductRequest);// get value Method call
						advisorService.addAdvProductInfo(advId, advProduct);
					}
					return ResponseEntity.ok()
							.body(successResponse.createSuccessResponse(appMessages.getAdvisor_info_added_successfully()));
				} else if (errors.isEmpty() == false) {
					return new ResponseEntity<String>(errorResponse.createResponse(errors),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
				advResponseStr = new ObjectMapper().writeValueAsString(
						successResponse.createSuccessResponse(appMessages.getAdvisor_info_added_successfully()));
			} catch (JsonProcessingException e) {
				logger.error(e.getMessage());
				return new ResponseEntity<String>(errorResponse.createResponse(e.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<String>(advResponseStr, HttpStatus.OK);
		}
	}

	// Modify Advisor Product info
	@RequestMapping(value = "/modifyAdvProdInfo", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<String> modifyAdvisorProduct(@RequestBody AdvProductInfoRequest advProdInfoReq) {
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		String advResponseStr = "";
		// Convert input json request to AdvisorRequest object.
		advProductInfoRequest = advProdInfoReq;
		String advId = advProductInfoRequest.getAdvId();
		Advisor advisor = advisorService.fetchByAdvisorId(advId);
		if (advisor == null) {
			logger.info("No record found");
			return ResponseEntity.ok().body(errorResponse.createResponse(appMessages.getNo_record_found()));
		} else {
			try {
				// validating advProductInfoRequest
				errors = advProductInfoRequestValidator.validate(advProductInfoRequest);
				if (errors.isEmpty() == true) {
					// remove from advProduct
					List<AdvProduct> advProducts = advisorService.fetchAdvProductByAdvId(advId);
					List<Long> advProdIdList = new ArrayList<>();
					// If advProduct is not available in the request. we remove the corresponding
					// data in table
					for (AdvProductRequest advProdReq : advProductInfoRequest.getAdvProducts()) {
						advProdIdList.add(advProdReq.getAdvProdId());
					}
					for (AdvProduct advProd : advProducts) {
						if (advProdIdList.contains(advProd.getAdvProdId()) == false) {
							advisorService.removeAdvProduct(advProd.getAdvProdId(), advId);
							advisorService.removeAdvBrandInfo(advProd.getProdId(), advProd.getServiceId(), advId);
							advisorService.removeFromBrandRank(advId, advProd.getProdId());
							addBrandrank(advId, advProd.getProdId());
						}
					}
					// modify and add advProduct
					for (AdvProductRequest advProductRequest : advProductInfoRequest.getAdvProducts()) {
						if (advProductRequest.getAdvProdId() != 0) {
							// fetch advproduct by AdvId, prodId
							AdvProduct advProd = advisorService.fetchAdvProductByAdvIdAndAdvProdId(advId,
									advProductRequest.getAdvProdId());
							if (advProd == null) {
								return ResponseEntity.ok()
										.body(successResponse.createSuccessResponse(appMessages.getAdvproduct_not_found()));
							} else {
								AdvProduct advProduct = getValueAdvProductInfo(advProductRequest); // get value method
																									// call
								// Modify advisor product
								advisorService.modifyAdvisorProduct(advProduct, advId);
							}
						} else {
							AdvProduct advProduct = getValueAdvProductInfo(advProductRequest); // get value method call
							// Add into advisor product
							advisorService.addAdvProductInfo(advId, advProduct);
						}
					}
					return ResponseEntity.ok()
							.body(successResponse.createSuccessResponse(appMessages.getAdvisor_updated_successfully()));
				} else if (errors.isEmpty() == false) {
					return new ResponseEntity<String>(errorResponse.createResponse(errors),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
				advResponseStr = new ObjectMapper().writeValueAsString(
						successResponse.createSuccessResponse(appMessages.getAdvisor_updated_successfully()));
			} catch (JsonProcessingException e) {
				logger.error(e.getMessage());
				return new ResponseEntity<String>(errorResponse.createResponse(e.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<String>(HttpStatus.OK);
		}
	}

	private AdvProduct getValueAdvProductInfo(AdvProductRequest advProductRequest) {
		AdvProduct advProduct = new AdvProduct();
		advProduct.setAdvProdId(advProductRequest.getAdvProdId());
		if (advProductRequest.getProdId() != 0) {
			advProduct.setProdId(advProductRequest.getProdId());
		}
		if (advProductRequest.getServiceId() != 0) {
			advProduct.setServiceId(advProductRequest.getServiceId());
		}
		if (advProductRequest.getRemId() != 0) {
			advProduct.setRemId(advProductRequest.getRemId());
		}
		if (advProductRequest.getLicId() != 0) {
			advProduct.setLicId(advProductRequest.getLicId());
		}
		if (advProductRequest.getLicNumber() != null) {
			advProduct.setLicNumber(advProductRequest.getLicNumber());
		}
		if (advProductRequest.getValidity() != null) {
			advProduct.setValidity(advProductRequest.getValidity());
		}
		if (advProductRequest.getLicImage() != null) {
			advProduct.setLicImage(advProductRequest.getLicImage());
		}
		return advProduct;
	}

	// Add Advisor Professional Info
	@RequestMapping(value = "/addAdvProfInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<String> addAdvProfessionalInfo(@RequestBody AdvProfessionalInfoRequest advProfInfoReq) {
		String advId = advProfInfoReq.getAdvId();
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		String advResponseStr = "";
		Advisor advisor = advisorService.fetchByAdvisorId(advId);
		if (advisor == null) {
			logger.info("No record found");
			return ResponseEntity.ok().body(errorResponse.createResponse(appMessages.getNo_record_found()));
		} else {
			try {
				// Convert input json request to AdvisorRequest object.
				advProfInfoRequest = advProfInfoReq;
				// validating advProfInfoRequest
				errors = advProfessionalInfoRequestValidator.validate(advProfInfoRequest);
				if (errors.isEmpty() == true) {
					Advisor adv = getValueProfessionalInfo();// get value Method call
					// Add advisor professional info
					advisorService.addAdvProfessionalInfo(advId, adv);
					return ResponseEntity.ok()
							.body(successResponse.createSuccessResponse(appMessages.getAdvisor_added_successfully()));
				} else if (errors.isEmpty() == false) {
					return new ResponseEntity<String>(errorResponse.createResponse(errors),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
				advResponseStr = new ObjectMapper().writeValueAsString(
						successResponse.createSuccessResponse(appMessages.getAdvisor_added_successfully()));
			} catch (JsonProcessingException e) {
				logger.error(e.getMessage());
				return new ResponseEntity<String>(errorResponse.createResponse(e.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<String>(advResponseStr, HttpStatus.OK);
		}
	}

	// Modify Professional Info
	@RequestMapping(value = "/modifyAdvProfInfo", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<String> modifyAdvProfessionalInfo(@RequestBody AdvProfessionalInfoRequest advProfInfoReq) {
		String advId = advProfInfoReq.getAdvId();
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		String advResponseStr = "";
		Advisor advisor = advisorService.fetchByAdvisorId(advId);
		List<AwardReq> awardReq = advProfInfoReq.getAwards();
		List<CertificateReq> certificateReq = advProfInfoReq.getCertificates();
		List<ExperienceReq> experienceReq = advProfInfoReq.getExperiences();
		List<EducationReq> educationReq = advProfInfoReq.getEducations();

		if (advisor == null) {
			logger.info("No record found");
			return ResponseEntity.ok().body(errorResponse.createResponse(appMessages.getNo_record_found()));
		} else {
			try {
				// Convert input json request to AdvisorRequest object.
				advProfInfoRequest = advProfInfoReq;
				errors = advProfessionalInfoRequestValidator.validate(advProfInfoRequest);

				if (errors.isEmpty() == true) {

					// we removing the record by deleteALl (array of string)
					if (advProfInfoRequest.getDeleteAll() != null) {
						for (String value : advProfInfoRequest.getDeleteAll()) {
							switch (value) {
							case "award":
								advisorService.removeAwardByAdvId(advId);
								break;
							case "certificate":
								advisorService.removeCertificateByAdvId(advId);
								break;
							case "experience":
								advisorService.removeExperienceByAdvId(advId);
								break;
							case "education":
								advisorService.removeEducationByAdvId(advId);
								break;
							default:
								break;
							}
						}
					}

					if (awardReq != null) {
						modifyAward(advId, awardReq);
					}
					if (certificateReq != null) {
						modifyCertificate(advId, certificateReq);
					}
					if (experienceReq != null) {
						modifyExperience(advId, experienceReq);
					}
					if (educationReq != null) {
						modifyEducation(advId, educationReq);
					}

					return ResponseEntity.ok()
							.body(successResponse.createSuccessResponse(appMessages.getAdvisor_updated_successfully()));
				} else if (errors.isEmpty() == false) {
					return new ResponseEntity<String>(errorResponse.createResponse(errors),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
				advResponseStr = new ObjectMapper().writeValueAsString(
						successResponse.createSuccessResponse(appMessages.getAdvisor_added_successfully()));
			} catch (JsonProcessingException e) {
				logger.error(e.getMessage());
				return new ResponseEntity<String>(errorResponse.createResponse(e.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<String>(advResponseStr, HttpStatus.OK);
		}
	}

	private void modifyAward(String advId, List<AwardReq> awardReq) {
		List<Award> awardList = advisorService.fetchAwardByadvId(advId);
		List<Long> awardIdList = new ArrayList<>();
		for (AwardReq awdReq : awardReq) {
			awardIdList.add(awdReq.getAwardId());
		}
		for (Award awd : awardList) {
			if (awardIdList.contains(awd.getAwardId()) == false) {
				advisorService.removeAdvAward(awd.getAwardId(), advId);
			}
		}
		for (AwardReq awdReq : awardReq) {
			if (awdReq.getAwardId() != 0) {
				// getvalue
				Award award = getValueAwardInfo(awdReq);
				long awardId = awdReq.getAwardId();
				advisorService.modifyAdvisorAward(awardId, award, advId);
			} else {
				Award award = getValueAwardInfo(awdReq);
				advisorService.addAdvAwardInfo(advId, award);
			}
		}
	}

	private void modifyCertificate(String advId, List<CertificateReq> certificateReq) {
		List<Certificate> certificateList = advisorService.fetchCertificateByadvId(advId);
		List<Long> certificateIdList = new ArrayList<>();
		for (CertificateReq certReq : certificateReq) {
			certificateIdList.add(certReq.getCertificateId());
		}
		for (Certificate cert : certificateList) {
			if (certificateIdList.contains(cert.getCertificateId()) == false) {
				advisorService.removeAdvCertificate(cert.getCertificateId(), advId);
			}
		}
		for (CertificateReq certReq : certificateReq) {
			if (certReq.getCertificateId() != 0) {
				Certificate certificate = getValueCertificateInfo(certReq);
				advisorService.modifyAdvisorCertificate(certReq.getCertificateId(), certificate, advId);
			} else {
				Certificate Certificate = getValueCertificateInfo(certReq);
				advisorService.addAdvCertificateInfo(advId, Certificate);
			}
		}
	}

	private void modifyExperience(String advId, List<ExperienceReq> experienceReq) {
		List<Experience> experienceList = advisorService.fetchExperienceByadvId(advId);
		List<Long> experienceIdList = new ArrayList<>();
		for (ExperienceReq expReq : experienceReq) {
			experienceIdList.add(expReq.getExpId());
		}
		for (Experience exp : experienceList) {
			if (experienceIdList.contains(exp.getExpId()) == false) {
				advisorService.removeAdvExperience(exp.getExpId(), advId);
			}
		}

		for (ExperienceReq expReq : experienceReq) {
			if (expReq.getExpId() != 0) {
				Experience experience = getValueExperienceInfo(expReq);
				advisorService.modifyAdvisorExperience(expReq.getExpId(), experience, advId);
			} else {
				Experience experience = getValueExperienceInfo(expReq);
				advisorService.addAdvExperienceInfo(advId, experience);
			}
		}
	}

	private void modifyEducation(String advId, List<EducationReq> educationReq) {
		List<Education> educationList = advisorService.fetchEducationByadvId(advId);
		List<Long> educationIdList = new ArrayList<>();
		for (EducationReq eduReq : educationReq) {
			educationIdList.add(eduReq.getEduId());
		}
		for (Education edu : educationList) {
			if (educationIdList.contains(edu.getEduId()) == false) {
				advisorService.removeAdvEducation(edu.getEduId(), advId);
			}
		}
		for (EducationReq eduReq : educationReq) {
			if (eduReq.getEduId() != 0) {
				Education education = getValueEducationInfo(eduReq);
				advisorService.modifyAdvisorEducation(eduReq.getEduId(), education, advId);
			} else {
				Education education = getValueEducationInfo(eduReq);
				advisorService.addAdvEducationInfo(advId, education);
			}
		}
	}

	private Award getValueAwardInfo(AwardReq awdReq) {
		Award awd = new Award();
		if (awdReq.getTitle() != null) {
			awd.setTitle(awdReq.getTitle());
		}
		if (awdReq.getIssuedBy() != null) {
			awd.setIssuedBy(awdReq.getIssuedBy());
		}
		if (awdReq.getImagePath() != null) {
			awd.setImagePath(awdReq.getImagePath());
		}
		if (awdReq.getYear() != null) {
			awd.setYear(awdReq.getYear());
		} //
		return awd;
	}

	private Certificate getValueCertificateInfo(CertificateReq certReq) {
		Certificate cert = new Certificate();
		if (certReq.getTitle() != null) {
			cert.setTitle(certReq.getTitle());
		}
		if (certReq.getIssuedBy() != null) {
			cert.setIssuedBy(certReq.getIssuedBy());
		}
		if (certReq.getImagePath() != null) {
			cert.setImagePath(certReq.getImagePath());
		}
		if (certReq.getYear() != null) {
			cert.setYear(certReq.getYear());
		}
		return cert;
	}

	private Experience getValueExperienceInfo(ExperienceReq expReq) {

		Experience exp = new Experience();
		if (expReq.getCompany() != null) {
			exp.setCompany(expReq.getCompany());
		}
		if (expReq.getDesignation() != null) {
			exp.setDesignation(expReq.getDesignation());
		}
		if (expReq.getLocation() != null) {
			exp.setLocation(expReq.getLocation());
		}
		if (expReq.getFromYear() != null) {
			exp.setFromYear(expReq.getFromYear());
		}
		if (expReq.getToYear() != null) {
			exp.setToYear(expReq.getToYear());
		}
		return exp;
	}

	private Education getValueEducationInfo(EducationReq eduReq) {
		Education edu = new Education();
		if (eduReq.getDegree() != null) {
			edu.setDegree(eduReq.getDegree());
		}
		if (eduReq.getField() != null) {
			edu.setField(eduReq.getField());
		}
		if (eduReq.getInstitution() != null) {
			edu.setInstitution(eduReq.getInstitution());
		}
		if (eduReq.getFromYear() != null) {
			edu.setFromYear(eduReq.getFromYear());
		}
		if (eduReq.getToYear() != null) {
			edu.setToYear(eduReq.getToYear());
		}
		return edu;
	}

	public Advisor getValueProfessionalInfo() {
		Advisor adv = new Advisor();

		// Awards
		if (advProfInfoRequest != null && advProfInfoRequest.getAwards() != null) {
			List<AwardReq> awardsReq = advProfInfoRequest.getAwards();
			List<Award> awards = new ArrayList<Award>();

			for (AwardReq award : awardsReq) {
				Award awd = new Award();
				if (award.getTitle() != null) {
					awd.setTitle(award.getTitle());
				}
				if (award.getImagePath() != null) {
					awd.setImagePath(award.getImagePath());
				}
				if (award.getIssuedBy() != null) {
					awd.setIssuedBy(award.getIssuedBy());
				}
				if (award.getYear() != null) {
					awd.setYear(award.getYear());
				}
				awards.add(awd);
			}
			adv.setAwards(awards);

		}

		// Certificate

		if (advProfInfoRequest != null && advProfInfoRequest.getCertificates() != null) {
			List<CertificateReq> certificatesReq = advProfInfoRequest.getCertificates();
			List<Certificate> certificates = new ArrayList<Certificate>();

			for (CertificateReq certificate : certificatesReq) {
				Certificate cert = new Certificate();
				if (certificate.getTitle() != null) {
					cert.setTitle(certificate.getTitle());
				}
				if (certificate.getImagePath() != null) {
					cert.setImagePath(certificate.getImagePath());
				}
				if (certificate.getIssuedBy() != null) {
					cert.setIssuedBy(certificate.getIssuedBy());
				}
				if (certificate.getYear() != null) {
					cert.setYear(certificate.getYear());
				}
				certificates.add(cert);
			}
			adv.setCertificates(certificates);

		}
		// Experience

		if (advProfInfoRequest != null && advProfInfoRequest.getExperiences() != null) {
			List<ExperienceReq> experiencesReq = advProfInfoRequest.getExperiences();
			List<Experience> experiences = new ArrayList<Experience>();

			for (ExperienceReq experience : experiencesReq) {
				Experience exp = new Experience();
				if (experience.getCompany() != null) {
					exp.setCompany(experience.getCompany());
				}
				if (experience.getDesignation() != null) {
					exp.setDesignation(experience.getDesignation());
				}
				if (experience.getFromYear() != null) {
					exp.setFromYear(experience.getFromYear());
				}
				if (experience.getToYear() != null) {
					exp.setToYear(experience.getToYear());
				}
				if (experience.getLocation() != null) {
					exp.setLocation(experience.getLocation());
				}
				experiences.add(exp);
			}
			adv.setExperiences(experiences);
		}

		// Education

		if (advProfInfoRequest != null && advProfInfoRequest.getEducations() != null) {
			List<EducationReq> educationsReq = advProfInfoRequest.getEducations();
			List<Education> educations = new ArrayList<Education>();

			for (EducationReq education : educationsReq) {
				Education edu = new Education();
				if (education.getDegree() != null) {
					edu.setDegree(education.getDegree());
				}
				if (education.getInstitution() != null) {
					edu.setInstitution(education.getInstitution());
				}
				if (education.getField() != null) {
					edu.setField(education.getField());
				}
				if (education.getFromYear() != null) {
					edu.setFromYear(education.getFromYear());
				}
				if (education.getToYear() != null) {
					edu.setToYear(education.getToYear());
				}

				educations.add(edu);
			}
			adv.setEducations(educations);
		}

		return adv;
	}

	// Change Password
	@RequestMapping(value = "/changePassword", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<String> changePassword(@RequestBody PasswordChangeRequest passwordChangeReq)
			throws JsonProcessingException {
		String advId = passwordChangeReq.getAdvId();
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		String advResponseStr = "";
		Advisor advisor = advisorService.fetchByAdvisorId(advId);
		if (advisor == null) {
			logger.info("No record found");
			return ResponseEntity.ok().body(errorResponse.createResponse(appMessages.getNo_record_found()));
		}

		else {
			// Convert input json request to AdvisorRequest object.
			passwordChangeRequest = passwordChangeReq;
			// check a password match current password and new password
			if (advisorService.checkForPasswordMatch(advId, passwordChangeRequest.getCurrentPassword()) == true) {
				errors = passwordValidator.validate(passwordChangeRequest);
				if (errors.isEmpty() == true) {
					advisorService.changeAdvPassword(advId, passwordChangeRequest.getNewPassword());
					return ResponseEntity.ok()
							.body(successResponse.createSuccessResponse(appMessages.getPassword_changed_successfully()));
				} else {
					return new ResponseEntity<String>(errorResponse.createResponse(errors),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {

				return ResponseEntity.ok().body(errorResponse.createResponse(appMessages.getIncorrect_password()));
			}
			// advResponseStr = new ObjectMapper().writeValueAsString(
			// successResponse.createSuccessResponse(AppMessages.PASSWORD_CHANGED_SUCCESSFULLY));
		}
	}

	// Add Advisor Video
	@RequestMapping(value = "/addAdvVideo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<String> addVideo(@RequestBody AdvVideoReq advisorVideoReq) {
		String advId = advisorVideoReq.getAdvId();
		HashMap<String, HashMap<String, String>> errors = new HashMap<String, HashMap<String, String>>();
		String advResponseStr = "";
		Advisor advisor = advisorService.fetchByAdvisorId(advId);
		if (advisor == null) {
			logger.info("No record found");
			return ResponseEntity.ok().body(errorResponse.createResponse(appMessages.getNo_record_found()));
		} else {
			try {
				// Convert input json request to AdvisorRequest object.
				advVideoReq = advisorVideoReq;
				errors = advVideoRequestValidator.validate(advVideoReq);
				if (errors.isEmpty() == true) {
					Advisor adv = getValueOfVideo();// get value Method call
					advisorService.addAdvVideo(advId, adv);
					return ResponseEntity.ok()
							.body(successResponse.createSuccessResponse(appMessages.getAdvisor_added_successfully()));
				} else if (errors.isEmpty() == false) {
					return new ResponseEntity<String>(errorResponse.createResponse(errors),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
				advResponseStr = new ObjectMapper().writeValueAsString(
						successResponse.createSuccessResponse(appMessages.getAdvisor_added_successfully()));
			} catch (JsonProcessingException e) {
				logger.error(e.getMessage());
				return new ResponseEntity<String>(errorResponse.createResponse(e.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<String>(advResponseStr, HttpStatus.OK);
		}
	}

	private Advisor getValueOfVideo() {
		Advisor adv = new Advisor();

		// Awards
		if (advVideoReq != null && advVideoReq.getVideos() != null) {
			List<VideoReq> videoReq = advVideoReq.getVideos();
			List<AdvVideo> advVideos = new ArrayList<AdvVideo>();

			for (VideoReq video : videoReq) {
				AdvVideo avd = new AdvVideo();
				if (video.getTitle() != null) {
					avd.setTitle(video.getTitle());
				}
				if (video.getAboutVideo() != null) {
					avd.setAboutVideo(video.getAboutVideo());
				}
				if (video.getVideo() != null) {
					avd.setVideo(video.getVideo());
				}

				advVideos.add(avd);
			}
			adv.setVideos(advVideos);

		}
		return adv;
	}

	@RequestMapping(value = "/addAdvBrandInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<String> addAdvBrandInfo(@RequestBody AdvBrandInfoRequest advBrandInfoReq)
			throws JsonProcessingException {
		String advId = advBrandInfoReq.getAdvId();
		Advisor advisor = advisorService.fetchByAdvisorId(advId);
		if (advisor == null) {
			logger.info("No record found");
			return ResponseEntity.ok().body(errorResponse.createResponse(appMessages.getNo_record_found()));
		} else {
			advBrandInfoRequest = advBrandInfoReq;
			// Removing brandRank and brandInfo from table and add new info
			advisorService.removeAdvBrandInfoByAdvId(advId);
			advisorService.removeAdvBrandRankByAdvId(advId);
			// Add brandInfo
			for (AdvBrandInfoReq brandInfoReq : advBrandInfoRequest.getBrandInfoReqList()) {
				if (brandInfoReq.getBrandId1() == 0 && brandInfoReq.getBrandId2() == 0
						&& brandInfoReq.getBrandId3() == 0) {
					return ResponseEntity.ok().body(errorResponse.createResponse(appMessages.getBrand_should_be_added()));
				} else {
					List<Long> brands = new ArrayList<Long>();
					if (brandInfoReq.getBrandId1() != 0) {
						brands.add(brandInfoReq.getBrandId1());
					}
					if (brandInfoReq.getBrandId2() != 0) {
						brands.add(brandInfoReq.getBrandId2());
					}
					if (brandInfoReq.getBrandId3() != 0) {
						brands.add(brandInfoReq.getBrandId3());
					}
					List<AdvBrandInfo> advBrandInfoList = getValueBrandInfo(brands, brandInfoReq);
					for (AdvBrandInfo advBrandInfo : advBrandInfoList) {
						advisorService.addAdvBrandInfo(advId, advBrandInfo);
					}
					// calculate brandRank using brandInfo
					addBrandrank(advId, brandInfoReq.getProdId());
				}
			}
			return ResponseEntity.ok()
					.body(successResponse.createSuccessResponse(appMessages.getAdvisor_added_successfully()));
		}
	}

	public List<AdvBrandInfo> getValueBrandInfo(List<Long> brands, AdvBrandInfoReq brandInfoReq) {
		List<AdvBrandInfo> advBrandInfoList = new ArrayList<AdvBrandInfo>();
		int count = 0;
		for (long brandId : brands) {
			AdvBrandInfo advBrandInfo = new AdvBrandInfo();
			if (brandInfoReq.getProdId() != 0) {
				advBrandInfo.setProdId(brandInfoReq.getProdId());
			}
			if (brandInfoReq.getServiceId() != 0) {
				advBrandInfo.setServiceId(brandInfoReq.getServiceId());
			}
			if (brandId != 0) {
				advBrandInfo.setBrandId(brandId);
			}
			advBrandInfo.setPriority(++count);

			advBrandInfoList.add(advBrandInfo);
		}
		return advBrandInfoList;
	}

	// calculate brandRank
	void addBrandrank(String advId, long prodId) {
		HashMap<Long, Integer> brandIdAndRank = new HashMap<>();
		int rank = 0;
		// fetch brandId by advId and prodId
		List<AdvBrandInfo> advBrandInfoList = advisorService.fetchAdvBrandInfoByAdvIdAndProdId(advId, prodId);
		List<Long> brandIdList = new ArrayList<Long>();
		for (AdvBrandInfo advBrandInfo : advBrandInfoList) {
			brandIdList.add(advBrandInfo.getBrandId());
		}
		// store barandId and its equivalent count in hashmap
		HashMap<Long, Integer> brandIdAndCount = new HashMap<Long, Integer>();
		for (long brandId : brandIdList) {
			Integer count = brandIdAndCount.get(brandId);
			if (count == null) {
				brandIdAndCount.put(brandId, 1);
			} else {
				brandIdAndCount.put(brandId, ++count);
			}
		}
		// sort the brandIdAndCount map by count in reverse order and store in
		// sorted
		// map
		LinkedHashMap<Long, Integer> sorted = new LinkedHashMap<Long, Integer>();
		brandIdAndCount.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
		// iterating the sorted map
		Iterator<Map.Entry<Long, Integer>> iterator = sorted.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Long, Integer> entry = iterator.next();
			// count the occurance of value in map
			int count = Collections.frequency(sorted.values(), entry.getValue());
			if (count == 1) {
				brandIdAndRank.put(entry.getKey(), ++rank);
			} else {
				List<Long> keys = new ArrayList<>();
				for (Entry<Long, Integer> entryKey : sorted.entrySet()) {
					if (Objects.equals(entry.getValue(), entryKey.getValue())) {
						keys.add(entryKey.getKey());
					}
				}
				// if count repeated for more than one brandId check the
				// priority
				LinkedHashMap<Long, Long> sortedMap = priorityRanking(keys, advId, prodId);
				for (Map.Entry<Long, Long> entry1 : sortedMap.entrySet()) {
					brandIdAndRank.put(entry1.getKey(), ++rank);
				}
				for (int i = 1; i < keys.size(); i++) {
					iterator.next();
				}
			}
		}
		// sortedBrandAndRank contains key -- brand , value -- rank
		HashMap<Long, Integer> sortedBrandAndRank = new LinkedHashMap<>();
		brandIdAndRank.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.naturalOrder())).limit(5)
				.forEachOrdered(x -> sortedBrandAndRank.put(x.getKey(), x.getValue()));
		addBrandRankIntoTable(sortedBrandAndRank, advId, prodId);

	}

	private void addBrandRankIntoTable(HashMap<Long, Integer> sortedBrandAndRank, String advId, long prodId) {
		for (Map.Entry<Long, Integer> entry : sortedBrandAndRank.entrySet()) {
			if (advisorService.fetchAdvBrandRank(advId, prodId, entry.getValue()) == null) {
				// Add brandRank
				advisorService.addAdvBrandAndRank(entry.getKey(), entry.getValue(), advId, prodId);
			} else {
				// Update brandRank
				advisorService.updateBrandAndRank(entry.getKey(), entry.getValue(), advId, prodId);
			}
		}
	}

	private LinkedHashMap<Long, Long> priorityRanking(List<Long> brands, String advId, long prodId) {
		Map<Long, Long> brandIdAndPriority = new HashMap<>();
		for (long brandId : brands) {
			// Sorting by priority
			// adding the priority value for the brandId
			List<Long> priorityList = advisorService.fetchPriorityByBrandIdAndAdvId(advId, prodId, brandId);
			long priority = 0;
			for (long priority1 : priorityList) {
				priority = priority + priority1;
			}
			brandIdAndPriority.put(brandId, priority);
		}
		LinkedHashMap<Long, Long> sortedMap = sortAndRank(brandIdAndPriority);
		return sortedMap;
	}

	private LinkedHashMap<Long, Long> sortAndRank(Map<Long, Long> brandIdAndPriority) {
		// sortedMap -- key - brand, value- priority sum
		LinkedHashMap<Long, Long> sortedMap = new LinkedHashMap<>();
		brandIdAndPriority.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
		return sortedMap;
	}

	// LookUp Table Fetch Services

	@RequestMapping(value = "/fetch-all-product", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Product>> fetchProductList() {
		List<Product> productList = advisorService.fetchProductList();
		return ResponseEntity.ok().body(productList);
	}

	@RequestMapping(value = "/fetch-all-role", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Role>> fetchRoleList() {
		List<Role> roleList = advisorService.fetchRoleList();
		return ResponseEntity.ok().body(roleList);
	}

	@RequestMapping(value = "/fetch-all-partystatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<PartyStatus>> fetchPartyStatusList() {
		List<PartyStatus> partyStatusList = advisorService.fetchPartyStatusList();
		return ResponseEntity.ok().body(partyStatusList);
	}

	@RequestMapping(value = "/fetch-all-service", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Service>> fetchServiceList() {
		List<Service> serviceList = advisorService.fetchServiceList();
		return ResponseEntity.ok().body(serviceList);
	}

	@RequestMapping(value = "/fetch-all-brand", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Brand>> fetchBrandList() {
		List<Brand> brandList = advisorService.fetchBrandList();
		return ResponseEntity.ok().body(brandList);
	}

	@RequestMapping(value = "/fetch-all-license", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<License>> fetchLicenseList() {
		List<License> licenseList = advisorService.fetchLicenseList();
		return ResponseEntity.ok().body(licenseList);
	}

	@RequestMapping(value = "/fetch-all-remuneration", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Remuneration>> fetchRemunerationList() {
		List<Remuneration> remunerationList = advisorService.fetchRemunerationList();
		return ResponseEntity.ok().body(remunerationList);
	}

	@RequestMapping(value = "/fetch-all-productServBrand", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Product>> fetchAllServiceAndBrand() {
		List<Product> productList = advisorService.fetchAllServiceAndBrand();
		return ResponseEntity.ok().body(productList);
	}

	@RequestMapping(value = "/fetch-all-stateCityPincode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<State>> fetchAllStateCityPincode() {
		List<State> stateCity = advisorService.fetchAllStateCityPincode();
		return ResponseEntity.ok().body(stateCity);
	}

	@RequestMapping(value = "/fetchAdvBrandRank", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<AdvBrandRank>> fetchAdvBrandRankByAdvId(@RequestBody AdvIdRequest advIdRequest) {
		String advId = advIdRequest.getAdvId();
		List<AdvBrandRank> advBrandRank = advisorService.fetchAdvBrandRankByAdvId(advId);
		return ResponseEntity.ok().body(advBrandRank);
	}

	@RequestMapping(value = "/fetch-all-Category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Category>> fetchCategoryList() {
		List<Category> category = advisorService.fetchCategoryList();
		return ResponseEntity.ok().body(category);
	}

	@RequestMapping(value = "/fetch-all-CategoryType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<CategoryType>> fetchCategoryTypeList() {
		List<CategoryType> categoryType = advisorService.fetchCategoryTypeList();
		return ResponseEntity.ok().body(categoryType);
	}

	@RequestMapping(value = "/fetch-all-ForumCategory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<ForumCategory>> fetchForumCategoryList() {
		List<ForumCategory> forumCategory = advisorService.fetchForumCategoryList();
		return ResponseEntity.ok().body(forumCategory);
	}

	@RequestMapping(value = "/fetch-all-RiskQuestionaire", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<RiskQuestionaire>> fetchRiskQuestionaireList() {
		List<RiskQuestionaire> riskQuestionaire = advisorService.fetchRiskQuestionaireList();
		return ResponseEntity.ok().body(riskQuestionaire);
	}

	@RequestMapping(value = "/fetch-all-GoalsServed", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<GoalsServed>> fetchGoalsServedList() {
		List<GoalsServed> goalsServed = advisorService.fetchGoalsServedList();
		return ResponseEntity.ok().body(goalsServed);
	}

	@RequestMapping(value = "/fetch-all-SpecialisedSkills", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<SpecialisedSkills>> fetchSpecialisedSkills() {
		List<SpecialisedSkills> specialisedSkills = advisorService.fetchSpecialisedSkills();
		return ResponseEntity.ok().body(specialisedSkills);
	}

	@RequestMapping(value = "/fetch-all-ForumSubCategory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<ForumSubCategory>> fetchForumSubCategoryList() {
		List<ForumSubCategory> forumSubCategoryList = advisorService.fetchForumSubCategoryList();
		return ResponseEntity.ok().body(forumSubCategoryList);
	}

	@RequestMapping(value = "/fetch-all-ForumStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<ForumStatus>> fetchForumStatusList() {
		List<ForumStatus> forumStatusList = advisorService.fetchForumStatusList();
		return ResponseEntity.ok().body(forumStatusList);
	}

	// @GetMapping("/fetchAwardByadvId/{advid}")
	// public ResponseEntity<?> fetchAwardByadvId(@PathVariable("advid") long
	// advid)
	// {
	// List<Award> awards = advisorService.fetchAwardByadvId(advid);
	// if (awards.isEmpty() == true) {
	// return ResponseEntity.ok().body(AppMessages.NO_RECORD_FOUND);
	// } else {
	// return ResponseEntity.ok().body(awards);
	// }
	// }
	//
	// @GetMapping("/fetchEducationByadvId/{advid}")
	// public ResponseEntity<?> fetchEducationByadvId(@PathVariable("advid")
	// long
	// advid) {
	// List<Education> educations = advisorService.fetchEducationByadvId(advid);
	// if (educations.isEmpty() == true) {
	// return ResponseEntity.ok().body(AppMessages.NO_RECORD_FOUND);
	// } else {
	// return ResponseEntity.ok().body(educations);
	// }
	// }
	//
	// @GetMapping("/fetchExperienceByadvId/{advid}")
	// public ResponseEntity<?> fetchExperienceByadvId(@PathVariable("advid")
	// long
	// advid) {
	// List<Experience> experiences =
	// advisorService.fetchExperienceByadvId(advid);
	// if (experiences.isEmpty() == true) {
	// return ResponseEntity.ok().body(AppMessages.NO_RECORD_FOUND);
	// } else {
	// return ResponseEntity.ok().body(experiences);
	// }
	// }
}
