package com.sowisetech.advisor.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sowisetech.advisor.request.AwardReq;
import com.sowisetech.advisor.service.AdvisorService;
import com.sowisetech.advisor.util.AdvisorConstants;
import com.sowisetech.advisor.util.AppMessages;
import com.sowisetech.advisor.util.Common;

@Component
public class AdvProfessionalInfoRequestValidator implements IValidator {
	
	@Autowired
	AppMessages appMessages;
	
	@Autowired
	Common common;
	
	@Autowired
	AdvisorService advisorService;

	public HashMap<String, HashMap<String, String>> validate(AdvProfessionalInfoRequest advisorProfInfoRequest) {
		HashMap<String, HashMap<String, String>> allErrors = new HashMap<String, HashMap<String, String>>();
		// List<String> validErrors = new ArrayList<>();
		HashMap<String, String> error = new HashMap<String, String>();
		error.put("EMPTY", appMessages.getAdvisor_detail_empty());
		if (advisorProfInfoRequest == null) {
			allErrors.put("NULL", error);
		} else {
			if (advisorProfInfoRequest != null && advisorProfInfoRequest.getAwards() != null) {
				error = validateAwards(advisorProfInfoRequest.getAwards(),advisorProfInfoRequest.getAdvId());
				if (error.isEmpty() == false) {
					allErrors.put("AWARDS", error);
				}
			}
			if (advisorProfInfoRequest != null && advisorProfInfoRequest.getCertificates() != null) {
				error = validateCertificates(advisorProfInfoRequest.getCertificates(),advisorProfInfoRequest.getAdvId());
				if (error.isEmpty() == false) {
					allErrors.put("CERTIFICATES", error);
				}
			}
			if (advisorProfInfoRequest != null && advisorProfInfoRequest.getEducations() != null) {
				error = validateEducations(advisorProfInfoRequest.getEducations(),advisorProfInfoRequest.getAdvId());
				if (error.isEmpty() == false) {
					allErrors.put("EDUCATION", error);
				}
			}
			if (advisorProfInfoRequest != null && advisorProfInfoRequest.getExperiences() != null) {
				error = validateExperiences(advisorProfInfoRequest.getExperiences(),advisorProfInfoRequest.getAdvId());
				if (error.isEmpty() == false) {
					allErrors.put("EXPERIENCE", error);
				}
			}

		}

		// for (String error : allErrors) {
		// if (StringUtils.isEmpty(error) == false) {
		// validErrors.add(error);
		// }
		//
		// }
		// return validErrors;
		return allErrors;

	}


	public HashMap<String, String> validateAwards(List<AwardReq> awards, String advId) {
		String inputParamName = AdvisorConstants.SPACE_WTIH_COLON + AdvisorConstants.AWARDS;
		HashMap<String, String> errors = new HashMap<String, String>();
		for (AwardReq a : awards) {
			if(a.getAwardId() != 0){
				if(advisorService.fetchAdvAwardByAdvIdAndAwardId(a.getAwardId(),advId)== null){
					errors.put("ID", appMessages.getAward_not_found());	
				}
				}
			if (a.getTitle() != null) {
				if(common.isAlpha(a.getTitle(), inputParamName).isEmpty()==false) {
				errors.put("TITLE", common.isAlpha(a.getTitle(), inputParamName));}
			}
			if (a.getIssuedBy() != null) {
				if(common.allowMultipleText(a.getIssuedBy(), inputParamName).isEmpty()==false) {
				errors.put("ISSUEDBY", common.allowMultipleText(a.getIssuedBy(), inputParamName));}
			}
			if (a.getYear() != null) {
				if(common.fromDateCheck(a.getYear(), inputParamName).isEmpty()==false) {
				errors.put("YEAR", common.fromDateCheck(a.getYear(), inputParamName));}
			}
//			if (a.getImagePath() != null) {
//				if(Common.isImage(a.getImagePath(), inputParamName).isEmpty()==false) {
//				errors.put("IMAGE_PATH", Common.isImage(a.getImagePath(), inputParamName));}
//			}
		}
		return errors;
	}

	public HashMap<String, String> validateCertificates(List<CertificateReq> certificates, String advId) {
		String inputParamName = AdvisorConstants.SPACE_WTIH_COLON + AdvisorConstants.CERTIFICATES;
		HashMap<String, String> errors = new HashMap<String, String>();
		for (CertificateReq cert : certificates) {
			if(cert.getCertificateId() != 0){
				if(advisorService.fetchAdvCertificateByAdvIdAndCertificateId(cert.getCertificateId(),advId)== null){
					errors.put("ID", appMessages.getCertificate_not_found());						
				}
				}
			if (cert.getTitle() != null) {
				if(common.isAlpha(cert.getTitle(), inputParamName).isEmpty()==false) {
				errors.put("TITLE", common.isAlpha(cert.getTitle(), inputParamName));}
			}
			if (cert.getIssuedBy() != null) {
				if(common.allowMultipleText(cert.getIssuedBy(), inputParamName).isEmpty()==false) {
				errors.put("ISSUEDBY", common.allowMultipleText(cert.getIssuedBy(), inputParamName));}
			}
			if (cert.getYear() != null) {
				if(common.fromDateCheck(cert.getYear(), inputParamName).isEmpty()==false) {
				errors.put("YEAR", common.fromDateCheck(cert.getYear(), inputParamName));}
			}
//			if (cert.getImagePath() != null) {
//				if(Common.isImage(cert.getImagePath(), inputParamName).isEmpty()==false) {
//				errors.put("IMAGE_PATH", Common.isImage(cert.getImagePath(), inputParamName));}
//			}
		}
		return errors;
		}

	protected HashMap<String, String> validateEducations(List<EducationReq> educations, String advId) {
		String inputParamName = AdvisorConstants.SPACE_WTIH_COLON + AdvisorConstants.EDUCATION;
		HashMap<String, String> errors = new HashMap<String, String>();
		for (EducationReq e : educations) {
			if(e.getEduId() != 0){
				if(advisorService.fetchAdvEducationByAdvIdAndEduId(e.getEduId(),advId)== null){
					errors.put("ID", appMessages.getEducation_not_found());//						
				}
				}
			if (e.getInstitution() != null) {
				if( common.isAlpha(e.getInstitution(), inputParamName).isEmpty()==false) {
				errors.put("INSTITUTION", common.isAlpha(e.getInstitution(), inputParamName));}
			}
			if (e.getDegree() != null) {
				if(common.degreeCheck(e.getDegree(), inputParamName).isEmpty()==false) {
				errors.put("DEGREE", common.degreeCheck(e.getDegree(), inputParamName));}
			}
			if (e.getField() != null) {
				if(common.isAlpha(e.getField(), inputParamName).isEmpty()==false) {
				errors.put("FIELD", common.isAlpha(e.getField(), inputParamName));}
			}
			if (e.getFromYear() != null) {
				if(common.fromDateCheck(e.getFromYear(), inputParamName).isEmpty()==false) {
				errors.put("FROMYEAR", common.fromDateCheck(e.getFromYear(), inputParamName));}
			}
			if (e.getToYear() != null) {
				if(common.fromDateCheck(e.getToYear(), inputParamName).isEmpty()==false) {
				errors.put("TOYEAR", common.fromDateCheck(e.getToYear(), inputParamName));}
			}
			
		}
		return errors;
	}

	protected HashMap<String, String> validateExperiences(List<ExperienceReq> experiences, String advId) {
		String inputParamName = AdvisorConstants.SPACE_WTIH_COLON + AdvisorConstants.EXPERIENCE;
		HashMap<String, String> errors = new HashMap<String, String>();
		for (ExperienceReq e : experiences) {
			if(e.getExpId() != 0){
				if(advisorService.fetchAdvExperienceByAdvIdAndExpId(e.getExpId(),advId)== null){
					errors.put("ID", appMessages.getExperience_not_found());//						
				}
				}
			if (e.getCompany() != null) {
				if(common.isAlpha(e.getCompany(), inputParamName).isEmpty()==false) {
				errors.put("COMPANY", common.isAlpha(e.getCompany(), inputParamName));}
			}
			if (e.getDesignation() != null) {
				if(common.isAlpha(e.getDesignation(), inputParamName).isEmpty()==false) {
				errors.put("DESIGNATION", common.isAlpha(e.getDesignation(), inputParamName));}
			}
			if (e.getLocation() != null) {
				if(common.isAlpha(e.getLocation(), inputParamName).isEmpty()==false) {
				errors.put("LOCATION", common.isAlpha(e.getLocation(), inputParamName));}
			}
			if (e.getFromYear() != null) {
				if(common.fromDateCheck(e.getFromYear(), inputParamName).isEmpty()==false) {
				errors.put("FROMYEAR", common.fromDateCheck(e.getFromYear(), inputParamName));}
			}
			if (e.getToYear() != null) {
				if(common.fromDateCheck(e.getToYear(), inputParamName).isEmpty()==false) {
				errors.put("TOYEAR", common.fromDateCheck(e.getToYear(), inputParamName));}
			}
		}
		return errors;
	}
}
