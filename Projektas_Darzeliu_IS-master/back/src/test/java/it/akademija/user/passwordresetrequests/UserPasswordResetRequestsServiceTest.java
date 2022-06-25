package it.akademija.user.passwordresetrequests;

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

@RunWith(SpringRunner.class)
@SpringBootTest
class UserPasswordResetRequestsServiceTest {
	@InjectMocks
	@Autowired
	private UserPasswordResetRequestsService userPasswordResetRequestsService;

	@MockBean
	private UserPasswordResetRequestsDAO userPasswordResetRequestsDAO;

	@Test
	void testResetPass() {

		Authentication authentication = Mockito.mock(Authentication.class);
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);

		userPasswordResetRequestsService.requestPasswordReset("");

	}

	@Test
	void testDeleteResetPass() {

		Authentication authentication = Mockito.mock(Authentication.class);
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);

		userPasswordResetRequestsService.deletePasswordRequest("");

	}

}
