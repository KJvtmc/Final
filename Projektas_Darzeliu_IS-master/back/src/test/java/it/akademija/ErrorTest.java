package it.akademija;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class ErrorTest {

	private CustomExceptionHandler handler = new CustomExceptionHandler();

	@Test
	public void testHandler() throws Exception {
		var bind = new BindException(this, "");
		var errors = new DirectFieldBindingResult(null, "");
		errors.addError(new ObjectError("Erorr", "Error message"));

		bind.addAllErrors(errors);
		var result = handler.handleException(new MethodArgumentNotValidException(null, bind), null);
		var body = (ErrorResponse) result.getBody();
		assertNotNull(body.getDetails());
		assertEquals("Validation Failed", body.getMessage());
		assertEquals("Error message", body.getDetails().get(0));

	}

}
