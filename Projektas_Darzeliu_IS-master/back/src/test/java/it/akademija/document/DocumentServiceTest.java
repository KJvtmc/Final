package it.akademija.document;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import it.akademija.user.UserDAO;

@RunWith(SpringRunner.class)
@SpringBootTest
class DocumentServiceTest {

	@InjectMocks
	@Autowired
	private DocumentService documentService;

	@MockBean
	private DocumentDAO documentDao;

	@Autowired
	private UserDAO userDao;

	@Test
	void testUploadDocument() {

		Authentication authentication = Mockito.mock(Authentication.class);
		// Mockito.whens() for your authorization object
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);

		MockMultipartFile file = new MockMultipartFile("file", "file.pdf", MediaType.APPLICATION_PDF_VALUE,
				"Hello, World!".getBytes());

		assertTrue(documentService.uploadDocument(file, "file.pdf"));
		Mockito.when(documentDao.getDocumentById(1L)).thenReturn(new DocumentEntity());
		assertNotNull(documentService.getDocumentById(1L));

	}

	@Test
	void getDocumentByUser() {

		DocumentViewmodel newDocument = new DocumentViewmodel();
		newDocument.setDocumentId(123L);
		newDocument.setName("pazyma");
		newDocument.setUploadDate(LocalDate.of(2019, 5, 5));

		assertEquals(123L, newDocument.getDocumentId());
		assertEquals("pazyma", newDocument.getName());
		assertEquals(LocalDate.of(2019, 5, 5), newDocument.getUploadDate());

		assertTrue(documentService.getDocumentsByUser(userDao.findByUsername("user@user.lt")).size() == 0);

	}

	@Test
	void deleteDocument() {

		DocumentViewmodel newDocument = new DocumentViewmodel();
		newDocument.setDocumentId(123L);
		newDocument.setName("pazyma");
		newDocument.setUploadDate(LocalDate.of(2019, 5, 5));

		assertEquals(123L, newDocument.getDocumentId());
		assertEquals("pazyma", newDocument.getName());
		assertEquals(LocalDate.of(2019, 5, 5), newDocument.getUploadDate());

		documentService.deleteDocument(1L);

	}

	@Test
	void getModels() {

		DocumentViewmodel newDocument = new DocumentViewmodel();
		newDocument.setDocumentId(123L);
		newDocument.setName("pazyma");
		newDocument.setUploadDate(LocalDate.of(2019, 5, 5));

		assertEquals(123L, newDocument.getDocumentId());
		assertEquals("pazyma", newDocument.getName());
		assertEquals(LocalDate.of(2019, 5, 5), newDocument.getUploadDate());
		var list = new ArrayList<DocumentEntity>();
		Mockito.when(documentDao.orderdByDocumentId("", null)).thenReturn(new PageImpl(list));

		assertNotNull(documentService.getDocumentViewmodelPage(null, null));

	}
}
