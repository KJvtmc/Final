package it.akademija.order;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Set;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.akademija.serviceProvider.ServiceProvider;
import it.akademija.serviceProvider.ServiceProviderDAO;
import it.akademija.serviceProvider.ServiceProviderService;
import it.akademija.user.User;
import it.akademija.user.UserService;

@Service
public class OrderService {

	@Autowired
	private OrderDAO applicationDao;

	@Autowired
	private UserService userService;

	@Autowired
	private ServiceProviderService gartenService;

	@Autowired
	private ServiceProviderDAO gartenDAO;

	/**
	 *
	 * Get information about submitted compensation applications for logged in user
	 *
	 * @param currentUsername
	 * @return list of user compensation applications
	 */
	@Transactional(readOnly = true)
	public Set<OrderInfoUser> getAllUserCompensationApplications(String currentUsername) {

		return applicationDao.findAllUserCompensationApplications(currentUsername);
	}

	/**
	 * Create an compensation application for logged in user's child with specified
	 * child data. Creates a private kindergarten with specified data. Receives and
	 * updates user data. Sets received main guardian and private kindergarten to
	 * application.
	 *
	 * 
	 * @param currentUsername
	 * @param data
	 * @return compensationApplication
	 */
	@Transactional
	public OrderEntity createNewCompensationApplication(String currentUsername,
			OrderDTO data) {

		OrderEntity application = new OrderEntity();

		User firstParent = userService.updateUserDataInfo(data.getMainGuardian(), currentUsername);
		String id = gartenService.createNewPrivateKindergarten(data.getPrivateKindergarten());
		ServiceProvider garten = gartenDAO.getById(id);

		application.setSubmitedAt();
		application.setChildName(capitalize(data.getChildName()));
		application.setChildSurname(capitalize(data.getChildSurname()));
		application.setChildPersonalCode(data.getChildPersonalCode());
		application.setBirthdate(data.getBirthdate());
		application.setMainGuardian(firstParent);
		application.setPrivateKindergarten(garten);
		application.setStatus(false);
		application = applicationDao.saveAndFlush(application);

		return application;

	}

	/**
	 * Create an compensation application for logged in user's child with specified
	 * child data. Creates a private kindergarten with specified data. Receives and
	 * updates user data. Sets received main guardian and private kindergarten to
	 * application.
	 *
	 *
	 * @param currentUsername
	 * @param data
	 * @return compensationApplication
	 */
	@Transactional
	public OrderEntity createNewCompensationApplicationDTO(String currentUsername,
			OrderDTO data) {

		OrderEntity application = new OrderEntity();

		User firstParent = userService.updateUserData(data.getMainGuardianSec(), currentUsername);
		String id = gartenService.createNewPrivateKindergarten(data.getPrivateKindergarten());
		ServiceProvider garten = gartenDAO.getById(id);

		application.setSubmitedAt();
		application.setChildName(capitalize(data.getChildName()));
		application.setChildSurname(capitalize(data.getChildSurname()));
		application.setChildPersonalCode(data.getChildPersonalCode());
		application.setBirthdate(data.getBirthdate());
		application.setMainGuardian(firstParent);
		application.setPrivateKindergarten(garten);
		application.setStatus(false);
		application = applicationDao.saveAndFlush(application);

		return application;

	}

	/**
	 *
	 * Capitalize first letter of word, other letters to lowercase
	 *
	 * @param str
	 * @return
	 */
	private String capitalize(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}

		return Pattern.compile("\\b(.)(.*?)\\b").matcher(str)
				.replaceAll(match -> match.group(1).toUpperCase() + match.group(2).toLowerCase());
	}

	/**
	 * Delete user compensation application by its id. Additionally deletes private
	 * kindergarten that was created with the submission of application. Accessible
	 * to User only
	 *
	 * @param id
	 * @return message indicating whether deletion was successful
	 */
	@Transactional
	public ResponseEntity<String> deleteCompensationApplication(Long id) {

		OrderEntity application = applicationDao.getById(id);

		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		if (application != null && application.getMainGuardian().equals(user)) {

			applicationDao.delete(application);

			return new ResponseEntity<>("Ištrinta sėkmingai", HttpStatus.OK);
		}

		return new ResponseEntity<>("Prašymas nerastas", HttpStatus.NOT_FOUND);
	}

	/**
	 *
	 * Returns a page of Compensation application with specified page number and
	 * page size
	 *
	 * @param pageable
	 * @return page from compensation application database
	 */
	@Transactional(readOnly = true)
	public Page<OrderDTO> getCompensationApplicationPage(Pageable pageable, LocalDate search) {
		if (search == null) {
			return applicationDao.orderdByDocumentId(pageable).map(OrderDTO::from);
		}
		return applicationDao.findBySubmitedAt(search, pageable).map(OrderDTO::from);

	}

	public LocalDate checkSearchIsValidDate(String search) {

		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			df.setLenient(false);
			df.parse(search);
			LocalDate date = LocalDate.parse(search);
			return date;
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Update user compensation application. 
	 *
	 * @param data
	 * @return message indicating whether update was successful
	 */
	@Transactional
	public ResponseEntity<String> updateCompensationApplication(@Valid OrderDTO data) {
		
		OrderEntity application = applicationDao.getById(data.getId());
		if (application != null ) {

			application.setSubmitedAt();
			application.setChildName(capitalize(data.getChildName()));
			application.setChildSurname(capitalize(data.getChildSurname()));
			application.setChildPersonalCode(data.getChildPersonalCode());
			application.setBirthdate(data.getBirthdate());
			application.setStatus(data.isStatus());
			application = applicationDao.saveAndFlush(application);
			
			applicationDao.save(application);

			return new ResponseEntity<>("Atnaujinta sėkmingai", HttpStatus.OK);
		}

		return new ResponseEntity<>("Prašymas nerastas", HttpStatus.NOT_FOUND);
	}

}
