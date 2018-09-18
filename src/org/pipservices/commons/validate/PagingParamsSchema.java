package org.pipservices.commons.validate;

import org.pipservices.commons.convert.TypeCode;

/**
 * Schema to validate PagingParams.
 * 
 * @see org.pipservices.commons.data.PagingParams
 */
public class PagingParamsSchema extends ObjectSchema {

	/**
	 * Creates a new instance of validation schema.
	 */
	public PagingParamsSchema() {
		super();
		withOptionalProperty("skip", TypeCode.Long);
		withOptionalProperty("take", TypeCode.Long);
		withOptionalProperty("total", TypeCode.Boolean);
	}

}
