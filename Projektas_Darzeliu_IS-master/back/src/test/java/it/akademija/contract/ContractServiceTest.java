package it.akademija.contract;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.WebContext;

import it.akademija.application.Application;
import it.akademija.application.ApplicationDAO;
import it.akademija.application.ApplicationDTO;
import it.akademija.application.ApplicationService;
import it.akademija.application.priorities.PrioritiesDAO;
import it.akademija.application.priorities.PrioritiesDTO;
import it.akademija.application.queue.ApplicationQueueService;
import it.akademija.kindergarten.Kindergarten;
import it.akademija.kindergarten.KindergartenDAO;
import it.akademija.kindergarten.KindergartenService;
import it.akademija.kindergartenchoise.KindergartenChoiseDAO;
import it.akademija.kindergartenchoise.KindergartenChoiseDTO;
import it.akademija.user.ParentDetails;
import it.akademija.user.ParentDetailsDAO;
import it.akademija.user.User;
import it.akademija.user.UserDAO;
import it.akademija.user.UserDTO;
import it.akademija.user.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class ContractServiceTest {

	@InjectMocks
	@Autowired
	private ContractService contractService;

	@MockBean
	private HttpServletRequest request;

	@MockBean
	private HttpServletResponse response;

	@MockBean
	private ServletContext servletContext;

	@InjectMocks
	@Autowired
	private ApplicationService applicationService;

	@Mock
	private ApplicationDAO applicationDao;

	@MockBean
	private UserService userService;

	@MockBean
	private KindergartenService gartenService;

	@MockBean
	private ApplicationQueueService queueService;

	@MockBean
	private ParentDetailsDAO parentDetailsDao;

	@Mock
	private PrioritiesDAO prioritiesDao;

	@MockBean
	private KindergartenChoiseDAO choiseDao;

	@MockBean
	private UserDAO userDao;

	@MockBean
	private KindergartenDAO gartenDao;

	@Test
	@Transactional
	void testGeneratePDF() throws IOException {
		String content = "test";

		assertNotNull(contractService.generatePdf(content));

	}

	@Test
	@Transactional
	void testGetHTMLFromTemplate() {

		WebContext context = new WebContext(request, response, servletContext);
		context.setVariable("name", "Test");
		context.setVariable("director", "Test");
		context.setVariable("mainGuardian", "Test");
		context.setVariable("mainGuardianEmail", "Test");
		context.setVariable("mainGuardianPhone", "Test");
		context.setVariable("mainGuardianAddress", "Test");
		context.setVariable("child", "Test");

		assertNotNull(contractService.getHTMLFromTemplate(context));

	}

	@Test
	@Transactional
	void testFillDataToThymeleafTemplate() throws IOException {

		PrioritiesDTO prioritiesDto = new PrioritiesDTO(true, false, false, false, false, false);

		UserDTO mainGuardian = new UserDTO("USER", "Testas", "Testauskas", "12345698745", "Adresas 1", "+37068945123",
				"test@test.lt", "test@test.lt", "password");

		User user = new User(1L, null, "");
		user.setParentDetails(new ParentDetails());

		Mockito.when(userService.updateUserData(mainGuardian, "test@test.lt")).thenReturn(user);

		Kindergarten garten = new Kindergarten("111111111", "A darzelis", "Adresas darzelio A", "Direktorius",
				"Antakalnio", 1, 0);
		gartenDao.save(garten);
		Mockito.when(gartenService.findById("111111111")).thenReturn(garten);

		KindergartenChoiseDTO kindergartenChoises = new KindergartenChoiseDTO("111111111", "", "", "", "");

		ApplicationDTO data = new ApplicationDTO("Pirmas", "Prasymas", "11111111111", LocalDate.of(2019, 03, 01),
				prioritiesDto, mainGuardian, null, kindergartenChoises);

		Application application = applicationService.createNewApplication("test@test.lt", data);

		assertNotNull(application);
		assertTrue(applicationService.existsByPersonalCode("11111111111"));

		Long id = application.getId();
		Mockito.when(applicationDao.findById(id)).thenReturn(Optional.of(application));
		application.setApprovedKindergarten(garten);
		assertNotNull(contractService.fillDataToThymeleafTemplate(id, request, response));

	}

}
