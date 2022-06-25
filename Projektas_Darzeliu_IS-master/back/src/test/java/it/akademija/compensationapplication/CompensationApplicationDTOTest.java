package it.akademija.compensationapplication;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class CompensationApplicationDTOTest {

	@InjectMocks
	private CompensationApplicationDTO dc = new CompensationApplicationDTO();

	@Test
	public void testGetSubmitedAt() {
		MockitoAnnotations.initMocks(this);
		dc.getSubmitedAt();
	}

	@Test
	public void testGetMainGuardian() {
		MockitoAnnotations.initMocks(this);
		dc.getMainGuardian();
	}

	@Test
	public void testGetKinderGarden() {
		MockitoAnnotations.initMocks(this);
		dc.getPrivateKindergarten();
	}

	@Test
	public void testName() {
		MockitoAnnotations.initMocks(this);
		dc.setChildName("");
	}

	@Test
	public void testLastName() {
		MockitoAnnotations.initMocks(this);
		dc.setChildSurname("");
	}

}
