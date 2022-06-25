package it.akademija.compensationapplication;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import it.akademija.privatekindergarten.PrivateKindergartenDAO;
import it.akademija.privatekindergarten.PrivateKindergartenDTO;
import it.akademija.role.Role;
import it.akademija.user.UserDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
class CompensationApplicactionTest {

	@InjectMocks
	@Autowired
	private CompensationApplicationService compensationApplicationService;

	@MockBean
	private CompensationApplicationDAO compensationApplicationDAO;

	@MockBean
	private PrivateKindergartenDAO privateKindergartenDAO;

	@Test
	void testCompensationCreation() {

		Authentication authentication = Mockito.mock(Authentication.class);
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);

		var user = new UserDTO();
		user.setRole(Role.MANAGER.name());
		user.setName("test");
		user.setSurname("test");
		user.setPassword("test@test.lt");
		user.setEmail("test@test.lt");
		user.setUsername("test@test.lt");

		var privateDto = new PrivateKindergartenDTO("", "", "", "", "", "", "", "");
		var dto = new CompensationApplicationDTO("vaikoVardas", null, "", "", null, privateDto, user);

		var application = compensationApplicationService.createNewCompensationApplicationDTO(null, dto);

	}

	@Test
	void testDeleteCompensation() {

		Authentication authentication = Mockito.mock(Authentication.class);
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);

		var application = compensationApplicationService.deleteCompensationApplication(1L);

	}

}
