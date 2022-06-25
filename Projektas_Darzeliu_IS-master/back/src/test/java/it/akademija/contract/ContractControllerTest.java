package it.akademija.contract;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import it.akademija.journal.JournalService;
import it.akademija.user.UserService;

public class ContractControllerTest {

	@InjectMocks
	private ContractController contract = new ContractController();

	@Mock
	private UserService userService;

	@Mock
	private ContractService contractService;

	@Mock
	private JournalService journalService;

	@Test
	public void testResetPass() throws IOException {
		MockitoAnnotations.initMocks(this);
		contract.getGeneratedPdf(1L, null, null);
	}

}
