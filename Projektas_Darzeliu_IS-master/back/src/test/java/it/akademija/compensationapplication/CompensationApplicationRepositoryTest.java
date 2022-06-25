package it.akademija.compensationapplication;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import it.akademija.privatekindergarten.PrivateKindergarten;
import it.akademija.role.Role;
import it.akademija.user.ParentDetails;
import it.akademija.user.User;
import it.akademija.user.UserDAO;

@DataJpaTest
public class CompensationApplicationRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CompensationApplicationDAO applicationDAO;

	@Autowired
	private UserDAO userDAO;

	@Test
	public void itShouldSaveApplications() {
		
		CompensationApplication application1 = new CompensationApplication();
		ParentDetails parentDetails = new ParentDetails("48602257896", "Test", "Test", "test@test.lt", "Adresas 1",
				"+37063502254");
		User mainGuardian = userDAO.save(
				new User(Role.USER, "Test", "Test", "test@test.lt", parentDetails, "test@test.lt", "test@test.lt"));
		
		PrivateKindergarten garten1 = new PrivateKindergarten("111111111", "A darzelis", "Adresas darzelio A", "+37011111111","darzelis@darzelis.lt", "Bankas A", "LT00-0000-0000-0000-0000","00000");

		application1.setSubmitedAt();
		application1.setChildName("Test");
		application1.setChildSurname("Test");
		application1.setChildPersonalCode("49902256547");
		application1.setBirthdate(LocalDate.of(2019, 5, 5));
		application1.setMainGuardian(mainGuardian);
		application1.setPrivateKindergarten(garten1);
		application1 = entityManager.persistAndFlush(application1);
		assertTrue(applicationDAO.existsCompensationApplicationByChildPersonalCode("49902256547"));
		assertTrue(applicationDAO.count()==1);
		
		CompensationApplication application2 = new CompensationApplication();
		ParentDetails parentDetail2 = new ParentDetails("48702257896", "Test", "Test", "test1@test.lt", "Adresas 1",
						"+37063502254");
		User mainGuardian2 = userDAO.save(
						new User(Role.USER, "Test", "Test", "test1@test.lt", parentDetail2, "test1@test.lt", "test1@test.lt"));
		PrivateKindergarten garten2 = new PrivateKindergarten("111111111", "A darzelis", "Adresas darzelio A", "+37011111111","darzelis@darzelis.lt", "Bankas A", "LT00-0000-0000-0000-0000","00000");
		
		application2.setSubmitedAt();
		application2.setChildName("Test");
		application2.setChildSurname("Test");
		application2.setChildPersonalCode("49903256547");
		application2.setBirthdate(LocalDate.of(2019, 5, 5));
		application2.setMainGuardian(mainGuardian2);
		application2.setPrivateKindergarten(garten2);
		application2 = entityManager.persistAndFlush(application2);
		assertTrue(applicationDAO.existsCompensationApplicationByChildPersonalCode("49903256547"));
		assertTrue(applicationDAO.count()==2);
		applicationDAO.deleteById(application1.getId());
		applicationDAO.deleteById(application2.getId());
		assertTrue(applicationDAO.findAll().isEmpty());
	}
}
