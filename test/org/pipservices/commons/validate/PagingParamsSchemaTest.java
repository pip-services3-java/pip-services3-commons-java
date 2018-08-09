package org.pipservices.commons.validate;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.pipservices.commons.data.PagingParams;

public class PagingParamsSchemaTest {
	
	@Test
	public void testEmptyPagingParams() {
		PagingParamsSchema schema = new PagingParamsSchema();
		PagingParams pagingParams = new PagingParams();

        List<ValidationResult> results = schema.validate(pagingParams);
        assertEquals(0, results.size());
    }

    @Test
    public void testNonEmptyPagingParams() {
		PagingParamsSchema schema = new PagingParamsSchema();
		PagingParams pagingParams = new PagingParams(1, 1, true);

        List<ValidationResult> results = schema.validate(pagingParams);
        assertEquals(0, results.size());
    }

    @Test
    public void testOptionalPagingParams(){
		PagingParamsSchema schema = new PagingParamsSchema();
		PagingParams pagingParams = new PagingParams(null, null, true);

        List<ValidationResult> results = schema.validate(pagingParams);
        assertEquals(0, results.size());
    }
}
