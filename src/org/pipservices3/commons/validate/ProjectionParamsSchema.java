package org.pipservices3.commons.validate;

import org.pipservices3.commons.convert.TypeCode;

/**
 * Schema to validate {@link org.pipservices3.commons.data.ProjectionParams}.
 *
 * @see org.pipservices3.commons.data.ProjectionParams
 */
public class ProjectionParamsSchema extends ArraySchema {

    /**
     * Creates a new instance of validation schema.
     */
    public ProjectionParamsSchema() {
        super(TypeCode.String);
    }
}
