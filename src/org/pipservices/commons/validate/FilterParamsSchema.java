package org.pipservices.commons.validate;

import org.pipservices.commons.convert.TypeCode;
import org.pipservices.commons.data.*;

/**
 * Schema to validate {@link org.pipservices.commons.data.FilterParams}.
 * 
 * @see FilterParams
 */
public class FilterParamsSchema extends MapSchema {

	/**
	 * Creates a new instance of validation schema.
	 */
	public FilterParamsSchema() {
		super(TypeCode.String, null);
	}
}
