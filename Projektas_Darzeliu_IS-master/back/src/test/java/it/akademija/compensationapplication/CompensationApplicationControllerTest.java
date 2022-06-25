package it.akademija.compensationapplication;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import it.akademija.journal.JournalService;
import it.akademija.privatekindergarten.PrivateKindergartenDTO;
import it.akademija.role.Role;
import it.akademija.user.UserDTO;
import it.akademija.user.UserService;
import it.akademija.user.passwordresetrequests.UserPasswordResetRequestsService;

public class CompensationApplicationControllerTest {

	@InjectMocks
	private CompensationApplicationController dc = new CompensationApplicationController();

	@Mock
	private UserPasswordResetRequestsService service;

	@Mock
	private UserService userService;

	@Mock
	private CompensationApplicationService compensationApplicationService;

	@Mock
	private JournalService journalService;

	@Test
	public void testNewCompensation() {

		Authentication authentication = Mockito.mock(Authentication.class);
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		Mockito.when(authentication.getName()).thenReturn("");

		MockitoAnnotations.initMocks(this);

		var user = new UserDTO();
		user.setRole(Role.MANAGER.name());
		user.setName("test");
		user.setSurname("test");
		user.setPassword("test@test.lt");
		user.setEmail("test@test.lt");
		user.setUsername("test@test.lt");

		var privateDto = new PrivateKindergartenDTO("", "", "", "", "", "", "", "");
		var dto = new CompensationApplicationDTO("vaikoVardas", null, "", "", null, privateDto, user);
		dc.createNewCompensationApplication(dto);
	}

	@Test
	public void testGetAllCompesation() {

		Authentication authentication = Mockito.mock(Authentication.class);
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);

		MockitoAnnotations.initMocks(this);
		dc.getAllUserApplications();
	}

	@Test
	public void testDeleteCompensation() {

		Authentication authentication = Mockito.mock(Authentication.class);
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);

		MockitoAnnotations.initMocks(this);
		dc.deleteCompensationApplication(1L);
	}

}
