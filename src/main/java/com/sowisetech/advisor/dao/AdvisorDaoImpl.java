package com.sowisetech.advisor.dao;

import java.security.Security;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sowisetech.advisor.model.AdvVideo;
import com.sowisetech.advisor.model.Advisor;
import com.sowisetech.advisor.model.Award;
import com.sowisetech.advisor.model.Brand;
import com.sowisetech.advisor.model.Category;
import com.sowisetech.advisor.model.CategoryType;
import com.sowisetech.advisor.model.Certificate;
import com.sowisetech.advisor.model.City;
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
import com.sowisetech.advisor.service.AdvisorService;
import com.sowisetech.advisor.model.AdvBrandInfo;
import com.sowisetech.advisor.model.AdvBrandRank;
import com.sowisetech.advisor.model.AdvProduct;

@Transactional
@Repository
public class AdvisorDaoImpl implements AdvisorDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	DataSource dataSource;
	private static final Logger logger = LoggerFactory.getLogger(AdvisorDaoImpl.class);

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@PostConstruct
	public void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void advSignup(Advisor advisor) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String password = encrypt(advisor.getPassword());
		String sqlType = "SELECT `id` FROM `advisortype` WHERE `advType`= ?";
		int typeId = jdbcTemplate.queryForObject(sqlType, Integer.class, "individual");
		String sql = "INSERT INTO `advisor` (`advId`,`name`, `emailId`,`panNumber`, `password`, `phoneNumber`,`partyStatusId`,`created`,`updated`,`delete_flag`,`advType`) values (?,?,?,?,?,?,?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, advisor.getAdvId(), advisor.getName(), advisor.getEmailId(), advisor.getPanNumber(),
				password, advisor.getPhoneNumber(), advisor.getPartyStatusId(), timestamp, timestamp, "N", typeId);// advisor.getPartyStatusId()
		String sqlRole = "SELECT `id` FROM `role` WHERE `name`= ?";
		Long roleId = jdbcTemplate.queryForObject(sqlRole, Long.class, "advisor");
		String sqlForPartyInsert = "INSERT INTO `party` (`partyStatusId`,`roleId`,`roleBasedId`,`created`,`updated`,`delete_flag`) values (?,?,?, ?, ?, ?)";
		jdbcTemplate.update(sqlForPartyInsert, advisor.getPartyStatusId(), roleId, advisor.getAdvId(), timestamp,
				timestamp, "N");
	}

	@Override
	public Advisor fetch(String advId) {
		try {
			String sql = "SELECT * FROM `advisor` WHERE `advId` = ? AND `delete_flag`=?";
			RowMapper<Advisor> rowMapper = new BeanPropertyRowMapper<Advisor>(Advisor.class);
			Advisor advisor = jdbcTemplate.queryForObject(sql, rowMapper, advId, "N");

			String sqlAward = "SELECT * FROM `award` WHERE `advId` = ? AND `delete_flag`=?";
			RowMapper<Award> awardrowMapper = new BeanPropertyRowMapper<Award>(Award.class);
			List<Award> awards = jdbcTemplate.query(sqlAward, awardrowMapper, advId, "N");
			advisor.setAwards(awards);

			String sqlCertificate = "SELECT * FROM `certificate` WHERE `advId` = ? AND `delete_flag`=?";
			RowMapper<Certificate> certificaterowMapper = new BeanPropertyRowMapper<Certificate>(Certificate.class);
			List<Certificate> certificates = jdbcTemplate.query(sqlCertificate, certificaterowMapper, advId, "N");
			advisor.setCertificates(certificates);

			String sqlEducation = "SELECT * FROM `education` WHERE `advId` = ? AND `delete_flag`=?";
			RowMapper<Education> eduRowMapper = new BeanPropertyRowMapper<Education>(Education.class);
			List<Education> educations = jdbcTemplate.query(sqlEducation, eduRowMapper, advId, "N");
			advisor.setEducations(educations);

			String sqlExperience = "SELECT * FROM `experience` WHERE `advId`= ? AND `delete_flag`=?";
			RowMapper<Experience> expRowMapper = new BeanPropertyRowMapper<Experience>(Experience.class);
			List<Experience> experiences = jdbcTemplate.query(sqlExperience, expRowMapper, advId, "N");
			advisor.setExperiences(experiences);

			String sqlVideo = "SELECT * FROM `advvideo` WHERE `advId`=? AND `delete_flag`=?";
			RowMapper<AdvVideo> videoMapper = new BeanPropertyRowMapper<AdvVideo>(AdvVideo.class);
			advisor.setVideos(jdbcTemplate.query(sqlVideo, videoMapper, advisor.getAdvId(), "N"));

			String sqlProduct = "SELECT * FROM `advproduct` WHERE `advId`=? AND `delete_flag`=?";
			RowMapper<AdvProduct> productMapper = new BeanPropertyRowMapper<AdvProduct>(AdvProduct.class);
			advisor.setAdvProducts(jdbcTemplate.query(sqlProduct, productMapper, advisor.getAdvId(), "N"));

			String sqlBrandInfo = "SELECT * FROM `advbrandinfo` WHERE `advId`=? AND `delete_flag`=?";
			RowMapper<AdvBrandInfo> brandInfoMapper = new BeanPropertyRowMapper<AdvBrandInfo>(AdvBrandInfo.class);
			advisor.setAdvBrandInfo(jdbcTemplate.query(sqlBrandInfo, brandInfoMapper, advisor.getAdvId(), "N"));

			String sqlBrandRank = "SELECT * FROM `advbrandrank` WHERE `advId`=? AND `delete_flag`=?";
			RowMapper<AdvBrandRank> brandRankMapper = new BeanPropertyRowMapper<AdvBrandRank>(AdvBrandRank.class);
			List<AdvBrandRank> advBrandRank = jdbcTemplate.query(sqlBrandRank, brandRankMapper, advisor.getAdvId(),
					"N");
			List<AdvBrandRank> advBrandRankList = new ArrayList<>();
			for (AdvBrandRank advBrandRank1 : advBrandRank) {
				String brand;
				try {
					String sql1 = "SELECT `brand` FROM `brand` WHERE `brandId` = ?";
					brand = jdbcTemplate.queryForObject(sql1, String.class, advBrandRank1.getBrandId());
				} catch (EmptyResultDataAccessException e) {
					logger.error(e.getMessage());
					return null;
				}
				advBrandRank1.setBrand(brand);
				advBrandRankList.add(advBrandRank1);
			}
			advisor.setAdvBrandRank(advBrandRankList);
			return advisor;
		} catch (EmptyResultDataAccessException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Advisor> fetchAll() {
		String sql = "SELECT * FROM `advisor` WHERE `delete_flag`=?";
		RowMapper<Advisor> rowMapper = new BeanPropertyRowMapper<Advisor>(Advisor.class);
		List<Advisor> advisors = jdbcTemplate.query(sql, rowMapper, "N");

		for (Advisor adv : advisors) {
			String sqlAward = "SELECT * FROM `award` WHERE `advId`=? AND `delete_flag`=?";
			RowMapper<Award> awardMapper = new BeanPropertyRowMapper<Award>(Award.class);
			adv.setAwards(jdbcTemplate.query(sqlAward, awardMapper, adv.getAdvId(), "N"));

			String sqlCertificate = "SELECT * FROM `certificate` WHERE `advId`=? AND `delete_flag`=?";
			RowMapper<Certificate> certificateMapper = new BeanPropertyRowMapper<Certificate>(Certificate.class);
			adv.setCertificates(jdbcTemplate.query(sqlCertificate, certificateMapper, adv.getAdvId(), "N"));

			String sqlEducation = "SELECT * FROM `education` WHERE `advId`=? AND `delete_flag`=?";
			RowMapper<Education> educationMapper = new BeanPropertyRowMapper<Education>(Education.class);
			adv.setEducations(jdbcTemplate.query(sqlEducation, educationMapper, adv.getAdvId(), "N"));

			String sqlExperience = "SELECT * FROM `experience` WHERE `advId`=? AND `delete_flag`=?";
			RowMapper<Experience> experienceMapper = new BeanPropertyRowMapper<Experience>(Experience.class);
			adv.setExperiences(jdbcTemplate.query(sqlExperience, experienceMapper, adv.getAdvId(), "N"));

			String sqlVideo = "SELECT * FROM `advvideo` WHERE `advId`=? AND `delete_flag`=?";
			RowMapper<AdvVideo> videoMapper = new BeanPropertyRowMapper<AdvVideo>(AdvVideo.class);
			adv.setVideos(jdbcTemplate.query(sqlVideo, videoMapper, adv.getAdvId(), "N"));

			String sqlProduct = "SELECT * FROM `advproduct` WHERE `advId`=? AND `delete_flag`=?";
			RowMapper<AdvProduct> productMapper = new BeanPropertyRowMapper<AdvProduct>(AdvProduct.class);
			adv.setAdvProducts(jdbcTemplate.query(sqlProduct, productMapper, adv.getAdvId(), "N"));

			String sqlBrandInfo = "SELECT * FROM `advbrandinfo` WHERE `advId`=? AND `delete_flag`=?";
			RowMapper<AdvBrandInfo> brandInfoMapper = new BeanPropertyRowMapper<AdvBrandInfo>(AdvBrandInfo.class);
			adv.setAdvBrandInfo(jdbcTemplate.query(sqlBrandInfo, brandInfoMapper, adv.getAdvId(), "N"));

			String sqlBrandRank = "SELECT * FROM `advbrandrank` WHERE `advId`=? AND `delete_flag`=?";
			RowMapper<AdvBrandRank> brandRankMapper = new BeanPropertyRowMapper<AdvBrandRank>(AdvBrandRank.class);
			adv.setAdvBrandRank(jdbcTemplate.query(sqlBrandRank, brandRankMapper, adv.getAdvId(), "N"));
		}
		return advisors;
	}

	@Override
	public void delete(String advId) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		String sqlRole = "SELECT `id` FROM `role` WHERE `name`= ?";
		Long roleId = jdbcTemplate.queryForObject(sqlRole, Long.class, "advisor");
		String sql1 = "UPDATE `advisor` SET `delete_flag`=?,`updated`=? WHERE `advId`=?";
		jdbcTemplate.update(sql1, "Y", timestamp, advId);
		String sqlDeleteAward = "UPDATE `award` SET `delete_flag`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlDeleteAward, "Y", advId);
		String sqlDeleteCertificate = "UPDATE `certificate` SET `delete_flag`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlDeleteCertificate, "Y", advId);
		String sqlDeleteEducation = "UPDATE `education` SET `delete_flag`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlDeleteEducation, "Y", advId);
		String sqlDeleteExperience = "UPDATE `experience` SET `delete_flag`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlDeleteExperience, "Y", advId);
		String sqlDeleteProduct = "UPDATE `advproduct` SET `delete_flag`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlDeleteProduct, "Y", advId);
		String sqlDeleteVideo = "UPDATE `advvideo` SET `delete_flag`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlDeleteVideo, "Y", advId);
		String sqlDeleteFromParty = "UPDATE `party` SET `delete_flag`=?,`updated`=? WHERE `roleBasedId`=? AND `roleId`=?";
		jdbcTemplate.update(sqlDeleteFromParty, "Y", timestamp, advId, roleId);
		String sqlDeleteFromBrandInfo = "UPDATE `advbrandinfo` SET `delete_flag`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlDeleteFromBrandInfo, "Y", advId);
		String sqlDeleteFromRank = "UPDATE `advbrandrank` SET `delete_flag`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlDeleteFromRank, "Y", advId);
	}

	@Override
	public void update(String advId, Advisor adv) {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		String sql = "SELECT * FROM `advisor` WHERE `advId` = ?";
		RowMapper<Advisor> rowMapper = new BeanPropertyRowMapper<Advisor>(Advisor.class);
		Advisor advisor = jdbcTemplate.queryForObject(sql, rowMapper, advId);

		if (adv.getName() != null) {
			advisor.setName(adv.getName());
		}
		if (adv.getEmailId() != null) {
			advisor.setEmailId(adv.getEmailId());
		}
		if (adv.getDesignation() != null) {
			advisor.setDesignation(adv.getDesignation());
		}
		if (adv.getPhoneNumber() != null) {
			advisor.setPhoneNumber(adv.getPhoneNumber());
		}
		if (adv.getDisplayName() != null) {
			advisor.setDisplayName(adv.getDisplayName());
		}
		if (adv.getDob() != null) {
			advisor.setDob(adv.getDob());
		}
		if (adv.getGender() != null) {
			advisor.setGender(adv.getGender());
		}
		if (adv.getPanNumber() != null) {
			advisor.setPanNumber(adv.getPanNumber());
		}
		if (adv.getAddress1() != null) {
			advisor.setAddress1(adv.getAddress1());
		}
		if (adv.getAddress2() != null) {
			advisor.setAddress2(adv.getAddress2());
		}
		if (adv.getCity() != null) {
			advisor.setCity(adv.getCity());
		}
		if (adv.getState() != null) {
			advisor.setState(adv.getState());
		}
		if (adv.getAboutme() != null) {
			advisor.setAboutme(adv.getAboutme());
		}
		if (adv.getPincode() != null) {
			advisor.setPincode(adv.getPincode());
		}
		if (adv.getPartyStatusId() != 0) {
			advisor.setPartyStatusId(adv.getPartyStatusId());
		}
		String sql1 = "UPDATE `advisor` SET `name`=?, `emailId`=?, `phoneNumber`=?,`designation`=?,`displayName`=?,`dob`=?,`gender`=?,`panNumber`=?,`address1`=?,`address2`=?,`state`=?,`city`=?,`aboutme`=?, `pincode`=?, `partyStatusId`=? ,`updated`=? WHERE `advId`=?";
		jdbcTemplate.update(sql1, advisor.getName(), advisor.getEmailId(), advisor.getPhoneNumber(),
				advisor.getDesignation(), advisor.getDisplayName(), advisor.getDob(), advisor.getGender(),
				advisor.getPanNumber(), advisor.getAddress1(), advisor.getAddress2(), advisor.getState(),
				advisor.getCity(), advisor.getAboutme(), advisor.getPincode(), advisor.getPartyStatusId(), timestamp,
				advId);
	}

	@Override
	public void addAdvProfessionalInfo(String advId, Advisor adv) {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String sqlUpdate = "UPDATE `advisor` SET `updated`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlUpdate, timestamp, advId);

		List<Award> awards = new ArrayList<Award>();
		List<Certificate> certificates = new ArrayList<Certificate>();
		List<Education> educations = new ArrayList<Education>();
		List<Experience> experiences = new ArrayList<Experience>();
		if (adv != null && adv.getAwards() != null) {
			awards = adv.getAwards();
		}
		if (adv != null && adv.getCertificates() != null) {
			certificates = adv.getCertificates();
		}
		if (adv != null && adv.getEducations() != null) {
			educations = adv.getEducations();
		}
		if (adv != null && adv.getExperiences() != null) {
			experiences = adv.getExperiences();
		}
		String sql = "SELECT `advId`,`name`, `displayName`, `dob`, `gender`, `emailId`, `password`, `phoneNumber`, `pincode`, `partyStatusId` FROM `advisor` WHERE `advId` = ?";
		RowMapper<Advisor> rowMapper = new BeanPropertyRowMapper<Advisor>(Advisor.class);
		Advisor advisor = jdbcTemplate.queryForObject(sql, rowMapper, advId);
		advisor.setAwards(adv.getAwards());
		advisor.setCertificates(adv.getCertificates());
		advisor.setEducations(adv.getEducations());
		advisor.setExperiences(adv.getExperiences());

		for (Award award : awards) {

			String sqlInsert = "INSERT INTO `award` (`title`, `issuedBy`, `year`, `imagePath`, `advId`,`delete_flag`) values (?,?, ?, ?, ?, ?)";
			jdbcTemplate.update(sqlInsert, award.getTitle(), award.getIssuedBy(), award.getYear(), award.getImagePath(),
					advId, "N");
		}
		for (Certificate certificate : certificates) {

			String sqlInsert = "INSERT INTO `certificate` (`title`, `issuedBy`, `year`, `imagePath`, `advId`,`delete_flag`) values (?,?, ?, ?, ?, ?)";
			jdbcTemplate.update(sqlInsert, certificate.getTitle(), certificate.getIssuedBy(), certificate.getYear(),
					certificate.getImagePath(), advId, "N");
		}

		for (Education education : educations) {

			String sqlInsert = "INSERT INTO `education` (`institution`, `degree`, `field`, `fromYear`, `toYear`, `advId`,`delete_flag`) values (?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sqlInsert, education.getInstitution(), education.getDegree(), education.getField(),
					education.getFromYear(), education.getToYear(), advId, "N");
		}
		for (Experience experience : experiences) {

			String sqlInsert = "INSERT INTO `experience` (`company`, `designation`, `location`, `fromYear`, `toYear`, `advId`,`delete_flag`) values (?,?,?,?, ?, ?, ?)";
			jdbcTemplate.update(sqlInsert, experience.getCompany(), experience.getDesignation(),
					experience.getLocation(), experience.getFromYear(), experience.getToYear(), advId, "N");
		}
	}

	@Override
	public void addAdvProductInfo(String advId, AdvProduct advProduct) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String sqlUpdate = "UPDATE `advisor` SET `updated`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlUpdate, timestamp, advId);
		String sqlInsert = "INSERT INTO `advproduct` (`advId`, `prodId`, `serviceId`,`remId`,`licId`,`licNumber`,`validity`,`delete_flag`,`licImage`) values (?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sqlInsert, advId, advProduct.getProdId(), advProduct.getServiceId(), advProduct.getRemId(),
				advProduct.getLicId(), advProduct.getLicNumber(), advProduct.getValidity(), "N",
				advProduct.getLicImage());
	}

	@Override
	public Award fetchAward(long awardId) {
		try {
			String sql = "SELECT * FROM `award` WHERE `awardId` = ? AND `delete_flag`=?";
			RowMapper<Award> rowMapper = new BeanPropertyRowMapper<Award>(Award.class);
			Award award = jdbcTemplate.queryForObject(sql, rowMapper, awardId, "N");
			return award;
		} catch (EmptyResultDataAccessException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Certificate fetchCertificate(long certificateId) {
		try {
			String sql = "SELECT * FROM `certificate` WHERE `certificateId` = ? AND `delete_flag`=?";
			RowMapper<Certificate> rowMapper = new BeanPropertyRowMapper<Certificate>(Certificate.class);
			Certificate certificate = jdbcTemplate.queryForObject(sql, rowMapper, certificateId, "N");
			return certificate;
		} catch (EmptyResultDataAccessException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Education fetchEducation(long educationId) {
		try {
			String sql = "SELECT * FROM `education` WHERE `eduId` = ? AND `delete_flag`=?";
			RowMapper<Education> rowMapper = new BeanPropertyRowMapper<Education>(Education.class);
			Education education = jdbcTemplate.queryForObject(sql, rowMapper, educationId, "N");
			return education;
		} catch (EmptyResultDataAccessException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Experience fetchExperience(long expId) {
		try {
			String sql = "SELECT * FROM `experience` WHERE `expId` = ? AND `delete_flag`=?";
			RowMapper<Experience> rowMapper = new BeanPropertyRowMapper<Experience>(Experience.class);
			Experience experience = jdbcTemplate.queryForObject(sql, rowMapper, expId, "N");
			return experience;
		} catch (EmptyResultDataAccessException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public void removeAdvAward(long awardId, String advId) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String sqlUpdate = "UPDATE `advisor` SET `updated`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlUpdate, timestamp, advId);
		String sql = "UPDATE `award` SET `delete_flag`=? WHERE `awardId`=?";
		jdbcTemplate.update(sql, "Y", awardId);
	}

	@Override
	public void removeAdvCertificate(long certificateId, String advId) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String sqlUpdate = "UPDATE `advisor` SET `updated`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlUpdate, timestamp, advId);
		String sql = "UPDATE `certificate` SET `delete_flag`=? WHERE `certificateId`=?";
		jdbcTemplate.update(sql, "Y", certificateId);
	}

	@Override
	public void removeAdvEducation(long eduId, String advId) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String sqlUpdate = "UPDATE `advisor` SET `updated`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlUpdate, timestamp, advId);
		String sql = "UPDATE `education` SET `delete_flag`=? WHERE `eduId`=?";
		jdbcTemplate.update(sql, "Y", eduId);
	}

	@Override
	public void removeAdvExperience(long expId, String advId) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String sqlUpdate = "UPDATE `advisor` SET `updated`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlUpdate, timestamp, advId);
		String sql = "UPDATE `experience` SET `delete_flag`=? WHERE `expId`=?";
		jdbcTemplate.update(sql, "Y", expId);
	}

	@Override
	public Advisor fetchAdvisorByEmailId(String emailId) {
		try {
			String sql = "SELECT * FROM `advisor` WHERE `emailId` = ?";
			RowMapper<Advisor> rowMapper = new BeanPropertyRowMapper<Advisor>(Advisor.class);
			return jdbcTemplate.queryForObject(sql, rowMapper, emailId);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e.getMessage());
			return null;
		} catch (IncorrectResultSizeDataAccessException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	// Generate Advisor Id
	@Override
	public String generateId() {
		try {
			String sql1 = "SELECT `id` FROM `advisorsmartid` ORDER BY `s_no` DESC LIMIT 1";
			String id = jdbcTemplate.queryForObject(sql1, String.class);
			String newId = idIncrement(id);
			String sqlInsert = "INSERT INTO `advisorsmartid` (`id`) values (?)";
			jdbcTemplate.update(sqlInsert, newId);
			return newId;
		} catch (EmptyResultDataAccessException e) {
			logger.error(e.getMessage());
			String newId = "ADV0000000000";
			String sqlInsert = "INSERT INTO `advisorsmartid` (`id`) values (?)";
			jdbcTemplate.update(sqlInsert, newId);
			return newId;

		}
	}

	private String idIncrement(String id) {
		String middle = id.substring(3, 12);
		String suffix;
		String newId;
		if (Character.isDigit(id.charAt(12))) {
			if (id.charAt(12) != '9') {
				String number = id.substring(3, 13);
				long num = Long.parseLong(number);
				middle = String.format("%010d", num + 1);
				newId = "ADV" + middle;
			} else {
				newId = "ADV" + middle + "A";
			}
		} else {
			if (id.charAt(12) != 'Z') {
				char last = id.charAt(12);
				suffix = String.valueOf((char) (last + 1));
				newId = id.substring(0, 12) + suffix;
			} else {
				long num = Long.parseLong(middle);
				middle = String.format("%09d", num + 1);
				newId = "ADV" + middle + "0";
			}
		}
		return newId;
	}

	// Password Encrypt
	@Override
	public String encrypt(String plainText) {
		Security.addProvider(new BouncyCastleProvider());
		StandardPBEStringEncryptor cryptor = new StandardPBEStringEncryptor();
		String sql1 = "SELECT * FROM `secret_key`";
		String key = jdbcTemplate.queryForObject(sql1, String.class);
		cryptor.setPassword(key);
		cryptor.setAlgorithm("PBEWITHSHA256AND128BITAES-CBC-BC");
		String encryptedText = cryptor.encrypt(plainText);
		return encryptedText;

	}

	@Override
	public void addAdvPersonalInfo(String advId, Advisor adv) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String sqlUpdate = "UPDATE `advisor` SET `displayName`=?,`dob`=?,`designation`=?,`gender`=?,`address1`=?,`address2`=?,`state`=?,`city`=?,`pincode`=?,`aboutme`=?,`updated`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlUpdate, adv.getDisplayName(), adv.getDob(), adv.getDesignation(), adv.getGender(),
				adv.getAddress1(), adv.getAddress2(), adv.getState(), adv.getCity(), adv.getPincode(), adv.getAboutme(),
				timestamp, advId);

	}

	@Override
	public boolean checkForPasswordMatch(String advId, String currentPassword) {
		String sql = "SELECT * FROM `advisor` WHERE `advId` = ? AND `delete_flag`=?";
		RowMapper<Advisor> rowMapper = new BeanPropertyRowMapper<Advisor>(Advisor.class);
		Advisor advisor = jdbcTemplate.queryForObject(sql, rowMapper, advId, "N");
		// System.out.println(currentPassword);
		String password = decrypt(advisor.getPassword());
		if (password.equals(currentPassword)) {
			return true;
		} else {
			return false;
		}
	}

	// Password Decrypt
	@Override
	public String decrypt(String encryptedText) {
		Security.addProvider(new BouncyCastleProvider());
		StandardPBEStringEncryptor cryptor = new StandardPBEStringEncryptor();
		String sql1 = "SELECT * FROM `secret_key`";
		String key = jdbcTemplate.queryForObject(sql1, String.class);
		cryptor.setPassword(key);
		cryptor.setAlgorithm("PBEWITHSHA256AND128BITAES-CBC-BC");
		String plainText = cryptor.decrypt(encryptedText);
		// System.out.println(plainText);
		return plainText;
	}

	@Override
	public void changeAdvPassword(String advId, String newPassword) {
		String password = encrypt(newPassword);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String sqlUpdate = "UPDATE `advisor` SET `password`=?,`updated`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlUpdate, password, timestamp, advId);
	}

	@Override
	public void addAdvVideo(String advId, Advisor adv) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String sqlUpdate = "UPDATE `advisor` SET `updated`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlUpdate, timestamp, advId);
		List<AdvVideo> videos = new ArrayList<AdvVideo>();

		if (adv != null && adv.getVideos() != null) {
			videos = adv.getVideos();
		}
		String sql = "SELECT * FROM `advisor` WHERE `advId` = ?";
		RowMapper<Advisor> rowMapper = new BeanPropertyRowMapper<Advisor>(Advisor.class);
		Advisor advisor = jdbcTemplate.queryForObject(sql, rowMapper, advId);
		advisor.setVideos(videos);
		for (AdvVideo video : videos) {
			String sqlInsert = "INSERT INTO `advvideo` (`advId`, `title`, `aboutVideo`,`video`,`delete_flag`) values (?,?,?,?,?)";
			jdbcTemplate.update(sqlInsert, advId, video.getTitle(), video.getAboutVideo(), video.getVideo(), "N");
		}
	}

	@Override
	public AdvProduct fetchAdvProduct(long advProdId) {
		try {
			String sql = "SELECT * FROM `advproduct` WHERE `advProdId` = ? AND `delete_flag`=?";
			RowMapper<AdvProduct> rowMapper = new BeanPropertyRowMapper<AdvProduct>(AdvProduct.class);
			AdvProduct product = jdbcTemplate.queryForObject(sql, rowMapper, advProdId, "N");
			return product;
		} catch (EmptyResultDataAccessException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public void modifyAdvisorProduct(AdvProduct advProduct, String advId) {
		String sql = "SELECT * FROM `advproduct` WHERE `advProdId` = ? AND `delete_flag`=?";
		RowMapper<AdvProduct> rowMapper = new BeanPropertyRowMapper<AdvProduct>(AdvProduct.class);
		AdvProduct advProduct1 = jdbcTemplate.queryForObject(sql, rowMapper, advProduct.getAdvProdId(), "N");

		if (advProduct.getProdId() != 0) {
			advProduct1.setProdId(advProduct.getProdId());
		}
		if (advProduct.getServiceId() != 0) {
			advProduct1.setServiceId(advProduct.getServiceId());
		}
		if (advProduct.getRemId() != 0) {
			advProduct1.setRemId(advProduct.getRemId());
		}
		if (advProduct.getLicId() != 0) {
			advProduct1.setLicId(advProduct.getLicId());
		}
		if (advProduct.getLicNumber() != null) {
			advProduct1.setLicNumber(advProduct.getLicNumber());
		}
		// if (product.getIssuedBy() != null) {
		// advProduct1.setIssuedBy(product.getIssuedBy());
		// }
		if (advProduct.getValidity() != null) {
			advProduct1.setValidity(advProduct.getValidity());
		}
		if (advProduct.getLicImage() != null) {
			advProduct1.setLicImage(advProduct.getLicImage());
		}
		String sql1 = "UPDATE `advproduct` SET  `prodId`=?, `serviceId`=?,`remId`=?,`licId`=?,`licNumber`=?,`validity`=?,`licImage`=? WHERE `advProdId`=? AND `advId`=?";
		jdbcTemplate.update(sql1, advProduct1.getProdId(), advProduct1.getServiceId(), advProduct1.getRemId(),
				advProduct1.getLicId(), advProduct1.getLicNumber(), advProduct1.getValidity(),
				advProduct1.getLicImage(), advProduct.getAdvProdId(), advId);

	}

	@Override
	public void addAdvBrandInfo(String advId, AdvBrandInfo advBrandInfo) {
		String sql = "INSERT INTO `advbrandinfo` (`prodId`, `serviceId`,`brandId`, `delete_flag`, `priority`,`advId`) values (?,?,?,?,?,?)";
		jdbcTemplate.update(sql, advBrandInfo.getProdId(), advBrandInfo.getServiceId(), advBrandInfo.getBrandId(), "N",
				advBrandInfo.getPriority(), advId);

	}

	@Override
	public List<Category> fetchCategoryList() {
		String sql = "SELECT * FROM `category`";
		RowMapper<Category> rowMapper = new BeanPropertyRowMapper<Category>(Category.class);
		List<Category> category = jdbcTemplate.query(sql, rowMapper);
		return category;
	}

	@Override
	public List<CategoryType> fetchCategoryTypeList() {
		String sql = "SELECT * FROM `categorytype`";
		RowMapper<CategoryType> rowMapper = new BeanPropertyRowMapper<CategoryType>(CategoryType.class);
		List<CategoryType> categoryType = jdbcTemplate.query(sql, rowMapper);
		return categoryType;
	}

	@Override
	public List<ForumCategory> fetchForumCategoryList() {
		String sql = "SELECT * FROM `forumcategory`";
		RowMapper<ForumCategory> rowMapper = new BeanPropertyRowMapper<ForumCategory>(ForumCategory.class);
		List<ForumCategory> forumCategory = jdbcTemplate.query(sql, rowMapper);
		return forumCategory;
	}

	@Override
	public List<RiskQuestionaire> fetchAllRiskQuestionaire() {
		String sql = "SELECT * FROM `riskquestionaire`";
		RowMapper<RiskQuestionaire> rowMapper = new BeanPropertyRowMapper<RiskQuestionaire>(RiskQuestionaire.class);
		List<RiskQuestionaire> riskQuestionaire = jdbcTemplate.query(sql, rowMapper);
		return riskQuestionaire;
	}

	@Override
	public List<GoalsServed> fetchGoalsServedList() {
		String sql = "SELECT * FROM `goalsserved`";
		RowMapper<GoalsServed> rowMapper = new BeanPropertyRowMapper<GoalsServed>(GoalsServed.class);
		List<GoalsServed> goalsServed = jdbcTemplate.query(sql, rowMapper);
		return goalsServed;
	}

	@Override
	public List<Product> fetchProductList() {
		String sql = "SELECT * FROM `product`";
		RowMapper<Product> rowMapper = new BeanPropertyRowMapper<Product>(Product.class);
		List<Product> products = jdbcTemplate.query(sql, rowMapper);
		for (Product prod : products) {
			String sqlService = "SELECT * FROM `service` WHERE `prodId`=?";
			RowMapper<Service> serviceMapper = new BeanPropertyRowMapper<Service>(Service.class);
			prod.setServices(jdbcTemplate.query(sqlService, serviceMapper, prod.getProdId()));

			String sqlBrand = "SELECT * FROM `brand` WHERE `prodId`=?";
			RowMapper<Brand> BrandMapper = new BeanPropertyRowMapper<Brand>(Brand.class);
			prod.setBrands(jdbcTemplate.query(sqlBrand, BrandMapper, prod.getProdId()));
		}
		return products;
	}

	@Override
	public List<SpecialisedSkills> fetchSpecialisedSkills() {
		String sql = "SELECT * FROM `specialisedskills`";
		RowMapper<SpecialisedSkills> rowMapper = new BeanPropertyRowMapper<SpecialisedSkills>(SpecialisedSkills.class);
		List<SpecialisedSkills> specialisedSkills = jdbcTemplate.query(sql, rowMapper);
		return specialisedSkills;
	}

	@Override
	public List<Role> fetchRoleList() {
		String sql = "SELECT * FROM `role`";
		RowMapper<Role> rowMapper = new BeanPropertyRowMapper<Role>(Role.class);
		List<Role> roleList = jdbcTemplate.query(sql, rowMapper);
		return roleList;
	}

	@Override
	public List<ForumSubCategory> fetchForumSubCategoryList() {
		String sql = "SELECT * FROM `forumsubcategory`";
		RowMapper<ForumSubCategory> rowMapper = new BeanPropertyRowMapper<ForumSubCategory>(ForumSubCategory.class);
		List<ForumSubCategory> forumSubCategory = jdbcTemplate.query(sql, rowMapper);
		return forumSubCategory;
	}

	@Override
	public List<ForumStatus> fetchForumStatusList() {
		String sql = "SELECT * FROM `forumstatus`";
		RowMapper<ForumStatus> rowMapper = new BeanPropertyRowMapper<ForumStatus>(ForumStatus.class);
		List<ForumStatus> forumStatus = jdbcTemplate.query(sql, rowMapper);
		return forumStatus;
	}

	@Override
	public List<PartyStatus> fetchPartyStatusList() {
		String sql = "SELECT * FROM `partystatus`";
		RowMapper<PartyStatus> rowMapper = new BeanPropertyRowMapper<PartyStatus>(PartyStatus.class);
		List<PartyStatus> partyStatus = jdbcTemplate.query(sql, rowMapper);
		return partyStatus;
	}

	@Override
	public List<Service> fetchServiceList() {
		String sql = "SELECT * FROM `service`";
		RowMapper<Service> rowMapper = new BeanPropertyRowMapper<Service>(Service.class);
		List<Service> service = jdbcTemplate.query(sql, rowMapper);
		return service;
	}

	@Override
	public List<Brand> fetchBrandList() {
		String sql = "SELECT * FROM `brand`";
		RowMapper<Brand> rowMapper = new BeanPropertyRowMapper<Brand>(Brand.class);
		List<Brand> brand = jdbcTemplate.query(sql, rowMapper);
		return brand;
	}

	@Override
	public List<License> fetchLicenseList() {
		String sql = "SELECT * FROM `license`";
		RowMapper<License> rowMapper = new BeanPropertyRowMapper<License>(License.class);
		List<License> license = jdbcTemplate.query(sql, rowMapper);
		return license;
	}

	@Override
	public List<Remuneration> fetchRemunerationList() {
		String sql = "SELECT * FROM `remuneration`";
		RowMapper<Remuneration> rowMapper = new BeanPropertyRowMapper<Remuneration>(Remuneration.class);
		List<Remuneration> remuneration = jdbcTemplate.query(sql, rowMapper);
		return remuneration;
	}

	@Override
	public List<AdvBrandInfo> fetchAdvBrandInfoByAdvIdAndProdId(String advId, long prodId) {
		try {
			String sql = "SELECT * FROM `advbrandinfo` WHERE `advId` = ? AND `prodId`=? AND `delete_flag`=?";
			RowMapper<AdvBrandInfo> rowMapper = new BeanPropertyRowMapper<AdvBrandInfo>(AdvBrandInfo.class);
			List<AdvBrandInfo> advBrandInfo = jdbcTemplate.query(sql, rowMapper, advId, prodId, "N");
			return advBrandInfo;
		} catch (EmptyResultDataAccessException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Long> fetchPriorityByBrandIdAndAdvId(String advId, long prodId, long brandId) {
		try {
			String sqlPriority = "SELECT `priority` FROM `advbrandinfo` WHERE `brandId`= ? AND `advId`=? AND `prodId`=? AND `delete_flag`=?";
			List<Long> priorities = jdbcTemplate.queryForList(sqlPriority, Long.class, brandId, advId, prodId, "N");
			return priorities;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public AdvBrandRank fetchAdvBrandRank(String advId, long prodId, int rank) {
		try {
			String sql = "SELECT * FROM `advbrandrank` WHERE `ranking`= ? AND `advId`=? AND `prodId`=? AND `delete_flag`=?";
			RowMapper<AdvBrandRank> rowMapper = new BeanPropertyRowMapper<AdvBrandRank>(AdvBrandRank.class);
			AdvBrandRank advBrandRank = jdbcTemplate.queryForObject(sql, rowMapper, rank, advId, prodId, "N");
			return advBrandRank;

		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	@Override
	public void addAdvBrandAndRank(long brand, int rank, String advId, long prodId) {
		String sql = "INSERT INTO `advbrandrank` (`advId`,`prodId`,`brandId`,`ranking`,`delete_flag`) values (?,?,?,?,?)";
		jdbcTemplate.update(sql, advId, prodId, brand, rank, "N");
	}

	@Override
	public void updateBrandAndRank(long brand, int rank, String advId, long prodId) {
		String sql1 = "UPDATE `advbrandrank` SET `advId`=?,  `prodId`=?,`brandId`=?, `ranking`=?, `delete_flag`=? WHERE `advId`=? AND `prodId`=? AND `ranking`=? AND `delete_flag`=?";
		jdbcTemplate.update(sql1, advId, prodId, brand, rank, "N", advId, prodId, rank, "N");
	}

	@Override
	public List<AdvProduct> fetchAdvProductByAdvId(String advId) {
		String sql = "SELECT * FROM `advproduct` WHERE `delete_flag`=? AND `advId`=?";
		RowMapper<AdvProduct> rowMapper = new BeanPropertyRowMapper<AdvProduct>(AdvProduct.class);
		List<AdvProduct> advProducts = jdbcTemplate.query(sql, rowMapper, "N", advId);
		return advProducts;
	}

	@Override
	public void removeAdvProduct(long advProdId, String advId) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String sqlUpdate = "UPDATE `advisor` SET `updated`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlUpdate, timestamp, advId);
		String sql = "UPDATE `advproduct` SET `delete_flag`=? WHERE `advProdId`=? AND `advId`=?";
		jdbcTemplate.update(sql, "Y", advProdId, advId);
	}

	@Override
	public void removeAdvBrandInfo(long prodId, long serviceId, String advId) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String sqlUpdate = "UPDATE `advisor` SET `updated`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlUpdate, timestamp, advId);
		String sql = "UPDATE `advbrandinfo` SET `delete_flag`=? WHERE `prodId`=? AND `serviceId`=? AND `advId`=?";
		jdbcTemplate.update(sql, "Y", prodId, serviceId, advId);
	}

	@Override
	public void removeFromBrandRank(String advId, long prodId) {
		String sql = "DELETE FROM `advbrandrank` WHERE `advId`=? AND `prodId`=?";
		jdbcTemplate.update(sql, advId, prodId);
	}

	@Override
	public AdvProduct fetchAdvProductByAdvIdAndAdvProdId(String advId, long advProdId) {
		try {
			String sql = "SELECT * FROM `advproduct` WHERE `advId` = ? AND `advProdId`=? AND `delete_flag`=?";
			RowMapper<AdvProduct> rowMapper = new BeanPropertyRowMapper<AdvProduct>(AdvProduct.class);
			AdvProduct advProduct = jdbcTemplate.queryForObject(sql, rowMapper, advId, advProdId, "N");
			return advProduct;
		} catch (EmptyResultDataAccessException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public void removeAdvBrandInfoByAdvId(String advId) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String sqlUpdate = "UPDATE `advisor` SET `updated`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlUpdate, timestamp, advId);
		String sql = "DELETE FROM `advbrandinfo` WHERE `advId`=?";
		jdbcTemplate.update(sql, advId);
	}

	@Override
	public void removeAdvBrandRankByAdvId(String advId) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String sqlUpdate = "UPDATE `advisor` SET `updated`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlUpdate, timestamp, advId);
		String sql = "DELETE FROM `advbrandrank` WHERE `advId`=?";
		jdbcTemplate.update(sql, advId);

	}

	@Override
	public List<Product> fetchAllServiceAndBrand() {
		String sql = "SELECT * FROM `product`";
		RowMapper<Product> rowMapper = new BeanPropertyRowMapper<Product>(Product.class);
		List<Product> products = jdbcTemplate.query(sql, rowMapper);

		for (Product prod : products) {
			String sqlService = "SELECT * FROM `service` WHERE `prodId`=?";
			RowMapper<Service> serviceMapper = new BeanPropertyRowMapper<Service>(Service.class);
			prod.setServices(jdbcTemplate.query(sqlService, serviceMapper, prod.getProdId()));

			String sqlBrand = "SELECT * FROM `brand` WHERE `prodId`=?";
			RowMapper<Brand> BrandMapper = new BeanPropertyRowMapper<Brand>(Brand.class);
			prod.setBrands(jdbcTemplate.query(sqlBrand, BrandMapper, prod.getProdId()));
		}
		return products;
	}

	@Override
	public List<Award> fetchAwardByadvId(String advId) {
		try {
			String sqlAward = "SELECT * FROM `award` WHERE `advId`=?";
			RowMapper<Award> awardMapper = new BeanPropertyRowMapper<Award>(Award.class);
			return jdbcTemplate.query(sqlAward, awardMapper, advId);
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<Certificate> fetchCertificateByadvId(String advId) {
		try {
			String sqlCertificate = "SELECT * FROM `certificate` WHERE `advId`=?";
			RowMapper<Certificate> certificateMapper = new BeanPropertyRowMapper<Certificate>(Certificate.class);
			return jdbcTemplate.query(sqlCertificate, certificateMapper, advId);
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<Experience> fetchExperienceByadvId(String advId) {
		try {
			String sqlExperience = "SELECT * FROM `experience` WHERE `advId`=?";
			RowMapper<Experience> experienceMapper = new BeanPropertyRowMapper<Experience>(Experience.class);
			return jdbcTemplate.query(sqlExperience, experienceMapper, advId);
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<Education> fetchEducationByadvId(String advId) {
		try {
			String sqlEducation = "SELECT * FROM `education` WHERE `advId`=?";
			RowMapper<Education> educationMapper = new BeanPropertyRowMapper<Education>(Education.class);
			return jdbcTemplate.query(sqlEducation, educationMapper, advId);
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public void modifyAdvisorAward(long awardId, Award award1, String advId) {

		String sql = "SELECT * FROM `award` WHERE `awardId` = ? AND `advId`=?";
		RowMapper<Award> rowMapper = new BeanPropertyRowMapper<Award>(Award.class);
		Award award = jdbcTemplate.queryForObject(sql, rowMapper, awardId, advId);
		if (award1.getImagePath() != null) {
			award.setImagePath(award1.getImagePath());
		}
		if (award1.getIssuedBy() != null) {
			award.setIssuedBy(award1.getIssuedBy());
		}
		if (award1.getTitle() != null) {
			award.setTitle(award1.getTitle());
		}
		if (award1.getYear() != null) {
			award.setYear(award1.getYear());
		}
		String sql1 = "UPDATE `award` SET `title`=?, `issuedBy`=?,`year`=?, `imagePath`=? WHERE `awardId`=? AND `advId`=?";
		jdbcTemplate.update(sql1, award.getTitle(), award.getIssuedBy(), award.getYear(), award.getImagePath(), awardId,
				advId);

	}

	@Override
	public void modifyAdvisorCertificate(long certificateId, Certificate certificate1, String advId) {

		String sql = "SELECT * FROM `certificate` WHERE `certificateId` = ? AND `advId`=?";
		RowMapper<Certificate> rowMapper = new BeanPropertyRowMapper<Certificate>(Certificate.class);
		Certificate certificate = jdbcTemplate.queryForObject(sql, rowMapper, certificateId, advId);
		if (certificate1.getImagePath() != null) {
			certificate.setImagePath(certificate1.getImagePath());
		}
		if (certificate1.getIssuedBy() != null) {
			certificate.setIssuedBy(certificate1.getIssuedBy());
		}
		if (certificate1.getTitle() != null) {
			certificate.setTitle(certificate1.getTitle());
		}
		if (certificate1.getYear() != null) {
			certificate.setYear(certificate1.getYear());
		}
		String sql1 = "UPDATE `certificate` SET `title`=?, `issuedBy`=?,`year`=?, `imagePath`=? WHERE `certificateId`=? AND `advId`=?";
		jdbcTemplate.update(sql1, certificate.getTitle(), certificate.getIssuedBy(), certificate.getYear(),
				certificate.getImagePath(), certificateId, advId);

	}

	@Override
	public void modifyAdvisorExperience(long expId, Experience experience1, String advId) {

		String sql = "SELECT * FROM `experience` WHERE `expId` = ? AND `advId` = ?";
		RowMapper<Experience> rowMapper = new BeanPropertyRowMapper<Experience>(Experience.class);
		Experience experience = jdbcTemplate.queryForObject(sql, rowMapper, expId, advId);
		// for (Experience exp : exp1) {
		if (experience1.getCompany() != null) {
			experience.setCompany(experience1.getCompany());
		}
		if (experience1.getDesignation() != null) {
			experience.setDesignation(experience1.getDesignation());
		}
		if (experience1.getLocation() != null) {
			experience.setLocation(experience1.getLocation());
		}
		if (experience1.getFromYear() != null) {
			experience.setFromYear(experience1.getFromYear());
		}
		if (experience1.getToYear() != null) {
			experience.setToYear(experience1.getToYear());
		}
		String sql1 = "UPDATE `experience` SET `company`=?, `designation`=?, `location`=?, `fromYear`=?, `toYear`=? WHERE `expId`=? AND `advId`=?";
		jdbcTemplate.update(sql1, experience.getCompany(), experience.getDesignation(), experience.getLocation(),
				experience.getFromYear(), experience.getToYear(), expId, advId);

	}

	@Override
	public void modifyAdvisorEducation(long eduId, Education education1, String advId) {

		String sql = "SELECT * FROM `education` WHERE `eduId` = ? AND `advId` = ?";
		RowMapper<Education> rowMapper = new BeanPropertyRowMapper<Education>(Education.class);
		Education education = jdbcTemplate.queryForObject(sql, rowMapper, eduId, advId);

		if (education1.getDegree() != null) {
			education.setDegree(education1.getDegree());
		}
		if (education1.getInstitution() != null) {
			education.setInstitution(education1.getInstitution());
		}
		if (education1.getField() != null) {
			education.setField(education1.getField());
		}
		if (education1.getFromYear() != null) {
			education.setFromYear(education1.getFromYear());
		}
		if (education1.getToYear() != null) {
			education.setToYear(education1.getToYear());
		}

		String sql1 = "UPDATE `education` SET `institution`=?, `degree`=?, `field`=?, `fromYear`=?, `toYear`=? WHERE `eduId`=? AND `advId` = ?";
		jdbcTemplate.update(sql1, education.getInstitution(), education.getDegree(), education.getField(),
				education.getFromYear(), education.getToYear(), eduId, advId);
	}

	@Override
	public void addAdvAwardInfo(String advId, Award award) {

		String sql = "INSERT INTO `award` (`title`, `issuedBy`,`imagePath`, `year`, `delete_flag`,`advId`) values (?,?,?,?,?,?)";
		jdbcTemplate.update(sql, award.getTitle(), award.getIssuedBy(), award.getImagePath(), award.getYear(), "N",
				advId);
	}

	@Override
	public void addAdvCertificateInfo(String advId, Certificate certificate) {
		String sql = "INSERT INTO `certificate` (`title`, `issuedBy`,`imagePath`, `year`, `delete_flag`,`advId`) values (?,?,?,?,?,?)";
		jdbcTemplate.update(sql, certificate.getTitle(), certificate.getIssuedBy(), certificate.getImagePath(),
				certificate.getYear(), "N", advId);

	}

	@Override
	public void addAdvExperienceInfo(String advId, Experience experience) {
		String sql = "INSERT INTO `experience` (`company`, `designation`,`location`, `fromYear`, `toYear`, `delete_flag`,`advId`) values (?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, experience.getCompany(), experience.getDesignation(), experience.getLocation(),
				experience.getFromYear(), experience.getToYear(), "N", advId);

	}

	@Override
	public void addAdvEducationInfo(String advId, Education education) {
		String sql = "INSERT INTO `education` (`degree`, `field`,`institution`, `fromYear`, `toYear`, `delete_flag`,`advId`) values (?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, education.getDegree(), education.getField(), education.getInstitution(),
				education.getFromYear(), education.getToYear(), "N", advId);
	}

	@Override
	public Award fetchAdvAwardByAdvIdAndAwardId(long awardId, String advId) {
		try {
			String sql = "SELECT * FROM `award` WHERE `awardId` = ? AND `advId` = ? AND `delete_flag`=?";
			RowMapper<Award> rowMapper = new BeanPropertyRowMapper<Award>(Award.class);
			Award award = jdbcTemplate.queryForObject(sql, rowMapper, awardId, advId, "N");
			return award;
		} catch (EmptyResultDataAccessException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Certificate fetchAdvCertificateByAdvIdAndCertificateId(long certificateId, String advId) {
		try {
			String sql = "SELECT * FROM `certificate` WHERE `certificateId` = ? AND `advId` = ? AND `delete_flag`=?";
			RowMapper<Certificate> rowMapper = new BeanPropertyRowMapper<Certificate>(Certificate.class);
			Certificate certificate = jdbcTemplate.queryForObject(sql, rowMapper, certificateId, advId, "N");
			return certificate;
		} catch (EmptyResultDataAccessException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Education fetchAdvEducationByAdvIdAndEduId(long eduId, String advId) {
		try {
			String sql = "SELECT * FROM `education` WHERE `eduId` = ? AND `advId` = ? AND `delete_flag`=?";
			RowMapper<Education> rowMapper = new BeanPropertyRowMapper<Education>(Education.class);
			Education education = jdbcTemplate.queryForObject(sql, rowMapper, eduId, advId, "N");
			return education;
		} catch (EmptyResultDataAccessException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Experience fetchAdvExperienceByAdvIdAndExpId(long expId, String advId) {
		try {
			String sql = "SELECT * FROM `experience` WHERE `expId` = ? AND `advId` = ? AND `delete_flag`=?";
			RowMapper<Experience> rowMapper = new BeanPropertyRowMapper<Experience>(Experience.class);
			Experience experience = jdbcTemplate.queryForObject(sql, rowMapper, expId, advId, "N");
			return experience;
		} catch (EmptyResultDataAccessException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public void removeAwardByAdvId(String advId) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String sqlUpdate = "UPDATE `advisor` SET `updated`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlUpdate, timestamp, advId);
		String sql = "UPDATE `award` SET `delete_flag`=? WHERE `advId`=?";
		jdbcTemplate.update(sql, "Y", advId);
	}

	@Override
	public void removeCertificateByAdvId(String advId) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String sqlUpdate = "UPDATE `advisor` SET `updated`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlUpdate, timestamp, advId);
		String sql = "UPDATE `certificate` SET `delete_flag`=? WHERE `advId`=?";
		jdbcTemplate.update(sql, "Y", advId);
	}

	@Override
	public void removeExperienceByAdvId(String advId) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String sqlUpdate = "UPDATE `advisor` SET `updated`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlUpdate, timestamp, advId);
		String sql = "UPDATE `experience` SET `delete_flag`=? WHERE `advId`=?";
		jdbcTemplate.update(sql, "Y", advId);
	}

	@Override
	public void removeEducationByAdvId(String advId) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String sqlUpdate = "UPDATE `advisor` SET `updated`=? WHERE `advId`=?";
		jdbcTemplate.update(sqlUpdate, timestamp, advId);
		String sql = "UPDATE `education` SET `delete_flag`=? WHERE `advId`=?";
		jdbcTemplate.update(sql, "Y", advId);
	}

	@Override
	public List<State> fetchAllStateCityPincode() {
		String sql = "SELECT * FROM `state`";
		RowMapper<State> rowMapper = new BeanPropertyRowMapper<State>(State.class);
		List<State> states = jdbcTemplate.query(sql, rowMapper);

		for (State state : states) {
			String sqlCity = "SELECT * FROM `city` WHERE `stateId`=?";
			RowMapper<City> serviceMapper = new BeanPropertyRowMapper<City>(City.class);
			state.setCities(jdbcTemplate.query(sqlCity, serviceMapper, state.getStateId()));
		}
		return states;
	}

	@Override
	public List<AdvBrandRank> fetchAdvBrandRankByAdvId(String advId) {
		String sqlRank = "SELECT * FROM `advbrandrank` WHERE `advId` = ?";
		RowMapper<AdvBrandRank> rankMapper = new BeanPropertyRowMapper<AdvBrandRank>(AdvBrandRank.class);
		List<AdvBrandRank> advBrandRank = jdbcTemplate.query(sqlRank, rankMapper, advId);
		return advBrandRank;
	}

	@Override
	public long fetchPartyIdByRoleBasedId(String advId) {
		String sqlRole = "SELECT `partyId` FROM `party` WHERE `roleBasedId`= ?";
		Long partyId = jdbcTemplate.queryForObject(sqlRole, Long.class, advId);
		return partyId;
	}

	// @Override
	// public List<AdvBrandInfo> fetchAdvBrandInfoByProdIdAndServiceId(long
	// prodId,
	// long serviceId, String advId) {
	// String sql = "SELECT * FROM `advbrandinfo` WHERE `delete_flag`=? AND
	// `prodId`=? AND `serviceId`=? AND `advId`=?";
	// RowMapper<AdvBrandInfo> rowMapper = new
	// BeanPropertyRowMapper<AdvBrandInfo>(AdvBrandInfo.class);
	// List<AdvBrandInfo> advBrandInfo = jdbcTemplate.query(sql, rowMapper, "N",
	// prodId, serviceId, advId);
	// return advBrandInfo;
	// }

	// @Override
	// public List<Award> fetchAwardByadvId(long advId) {
	// try {
	// String sqlAward = "SELECT *,`advId` FROM `award` WHERE `advId`=?";
	// RowMapper<Award> awardMapper = new
	// BeanPropertyRowMapper<Award>(Award.class);
	// return jdbcTemplate.query(sqlAward, awardMapper, advId);
	// } catch (EmptyResultDataAccessException e) {
	// System.out.println(e);
	// return null;
	// }
	// }
	//
	// @Override
	// public List<Education> fetchEducationByadvId(long advid) {
	// try {
	// String sqlEducation = "SELECT * FROM `education` WHERE `advId`=?";
	// RowMapper<Education> educationMapper = new
	// BeanPropertyRowMapper<Education>(Education.class);
	// return jdbcTemplate.query(sqlEducation, educationMapper, advid);
	// } catch (EmptyResultDataAccessException e) {
	// System.out.println(e);
	// return null;
	// }
	// }
	//
	// @Override
	// public List<Experience> fetchExperienceByadvId(long advid) {
	// try {
	// String sqlExperience = "SELECT * FROM `experience` WHERE `advId`=?";
	// RowMapper<Experience> experienceMapper = new
	// BeanPropertyRowMapper<Experience>(Experience.class);
	// return jdbcTemplate.query(sqlExperience, experienceMapper, advid);
	// } catch (EmptyResultDataAccessException e) {
	// System.out.println(e);
	// return null;
	// }
	// }

}
