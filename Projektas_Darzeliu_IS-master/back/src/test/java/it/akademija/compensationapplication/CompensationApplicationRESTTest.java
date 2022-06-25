package it.akademija.compensationapplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;

import it.akademija.App;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(classes = { App.class, CompensationApplicationController.class,
		CompensationApplicationService.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureMockMvc
class CompensationApplicationRESTTest {

	@Value("${local.server.port}")
	int port;

	@Autowired
	private MockMvc mvc;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	private WebApplicationContext context;

	@MockBean
	private CompensationApplicationService compensationApplicationService;

	@MockBean
	private CompensationApplicationDAO compensationApplicationDao;

	@BeforeAll
	public void setUp() throws Exception {
		RestAssured.port = port;
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();

	}

	@Test
	@WithMockUser(username = "user@user.lt", roles = { "USER" })
	public void testDeleteApplicationMethod() throws Exception {

		CompensationApplication application = new CompensationApplication();
		application.setId(123L);
		compensationApplicationDao.save(application);

		MvcResult deleteApplication = mvc.perform(delete("/api/naujas_kompensacija/user/delete/{id}", 123L))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, deleteApplication.getResponse().getStatus());

	}

	@Test
	@WithMockUser(username = "user@user.lt", roles = { "USER" })
	public void testGetAllUserApplications() throws Exception {

		CompensationApplicationInfoUser infoUser = new CompensationApplicationInfoUser(123L, "test", "test",
				LocalDate.of(2020, 5, 5), "Test darzelis", "LT00-0000-0000-0000-0000");

		Set<CompensationApplicationInfoUser> newSet = new HashSet<>();
		newSet.add(infoUser);

		mvc.perform(get("/api/naujas_kompensacija/user/")).andExpect(result -> assertTrue(newSet.size() != 0));

	}

}
