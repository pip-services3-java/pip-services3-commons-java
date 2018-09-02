package org.pipservices.commons.validate;

import org.pipservices.commons.convert.TypeCode;

public class PagingParamsSchema extends ObjectSchema {
	
	public PagingParamsSchema()
    {
        withOptionalProperty("skip", TypeCode.Long);
        withOptionalProperty("take", TypeCode.Long);
        withOptionalProperty("total", TypeCode.Boolean);
    }

}
