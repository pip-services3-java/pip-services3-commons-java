package org.pipservices3.commons.validate;

import org.pipservices3.commons.convert.TypeCode;

/**
 * Schema to validate {@link org.pipservices3.commons.data.PagingParams}.
 * 
 * @see org.pipservices3.commons.data.PagingParams
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
