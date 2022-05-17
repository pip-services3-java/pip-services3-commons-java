package org.pipservices3.commons.validate;

import org.pipservices3.commons.convert.TypeCode;
import org.pipservices3.commons.data.*;

/**
 * Schema to validate {@link org.pipservices3.commons.data.FilterParams}.
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
