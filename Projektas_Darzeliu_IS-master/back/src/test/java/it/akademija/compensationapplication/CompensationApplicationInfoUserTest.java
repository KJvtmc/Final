package it.akademija.compensationapplication;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class CompensationApplicationInfoUserTest {

	@InjectMocks
	private CompensationApplicationInfoUser dto = new CompensationApplicationInfoUser();

	@Test
	public void testGetId() {
		MockitoAnnotations.initMocks(this);
		dto.getId();
	}

	@Test
	public void testGetChildName() {
		MockitoAnnotations.initMocks(this);
		dto.getChildName();
	}

	@Test
	public void testgetChildSurname() {
		MockitoAnnotations.initMocks(this);
		dto.getChildSurname();
	}

	@Test
	public void testGetKindergartenAccountNumber() {
		MockitoAnnotations.initMocks(this);
		dto.getKindergartenAccountNumber();
	}

	@Test
	public void testGetSubmitedAt() {
		MockitoAnnotations.initMocks(this);
		dto.getSubmitedAt();
	}

}
