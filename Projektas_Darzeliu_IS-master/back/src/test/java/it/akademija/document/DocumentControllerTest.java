package it.akademija.document;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import it.akademija.journal.JournalService;
import it.akademija.user.UserService;

public class DocumentControllerTest {

	@InjectMocks
	private DocumentController dc = new DocumentController();

	@Mock
	private DocumentService service;

	@Mock
	private UserService userService;

	@Mock
	private JournalService journalService;

	@Test
	public void testUploadDocument() {
		MockitoAnnotations.initMocks(this);
		dc.UploadDocument(null, null);
	}

	@Test
	public void testDeleteDocument() {
		MockitoAnnotations.initMocks(this);
		dc.deleteDocument(1L);
	}

	@Test
	public void testGetDocument() {
		MockitoAnnotations.initMocks(this);
		dc.getDocumentFileById(1L);
	}

	@Test
	public void testUserDocument() {
		MockitoAnnotations.initMocks(this);
		Authentication authentication = Mockito.mock(Authentication.class);
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);

		dc.getLoggedUserDocuments();
	}

	@Test
	public void testDocumentModel() {
		MockitoAnnotations.initMocks(this);
		dc.getDocumentViewmodelPage(1, 1, "");
	}

}
