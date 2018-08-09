package org.pipservices.commons.validate;

import org.pipservices.commons.convert.TypeCode;

public class PagingParamsSchema extends ObjectSchema {
	
	public PagingParamsSchema()
    {
        withOptionalProperty("skip", long.class);
        withOptionalProperty("take", long.class);
        withOptionalProperty("total", TypeCode.Boolean);
    }

}
