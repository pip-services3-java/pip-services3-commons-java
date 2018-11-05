package org.pipservices3.commons.errors;

import static org.junit.Assert.*;

import org.junit.Test;

public class ApplicationExceptionTest {

	@Test
	public void test() {
		ApplicationException error = new ApplicationException(null, null, null, "Test error")
			.withCode("TEST_ERROR");
		
		assertEquals("TEST_ERROR", error.getCode());
		assertEquals("Test error", error.getMessage());
		
		error = new ApplicationException();

		assertEquals("UNKNOWN", error.getCode());
		assertEquals("Unknown error", error.getMessage());
	}

}
