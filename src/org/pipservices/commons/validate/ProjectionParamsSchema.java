package org.pipservices.commons.validate;

import org.pipservices.commons.convert.TypeCode;

/**
 * Schema to validate ProjectionParams.
 * 
 * @see org.pipservices.commons.data.ProjectionParams
 */
public class ProjectionParamsSchema extends ArraySchema {

	/**
	 * Creates a new instance of validation schema.
	 */
	public ProjectionParamsSchema() {
		super(TypeCode.String);
	}
}
