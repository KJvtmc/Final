package it.akademija.user.passwordresetrequests;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import it.akademija.journal.JournalService;
import it.akademija.user.UserService;

public class UserPasswordResetRequestsControllerTest {

	@InjectMocks
	private UserPasswordResetRequestsController dc = new UserPasswordResetRequestsController();

	@Mock
	private UserPasswordResetRequestsService service;

	@Mock
	private UserService userService;

	@Mock
	private JournalService journalService;

	@Test
	public void testResetPass() {
		MockitoAnnotations.initMocks(this);
		dc.requestPasswordReset("username");
	}

	@Test
	public void testDeleteResetRequest() {
		MockitoAnnotations.initMocks(this);
		dc.deletePasswordResetRequest("username");
	}

	@Test
	public void testGetAll() {
		MockitoAnnotations.initMocks(this);
		dc.getAllPasswordResetRequests();
	}

}
