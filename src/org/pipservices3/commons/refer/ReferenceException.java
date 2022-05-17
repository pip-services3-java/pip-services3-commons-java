package org.pipservices3.commons.refer;

import org.pipservices3.commons.errors.*;

/**
 * Error when required component dependency cannot be found.
 */
public class ReferenceException extends InternalException {
    private static final long serialVersionUID = 439183381933188434L;

    /**
     * Creates an error instance and assigns its values.
     */
    public ReferenceException() {
        this(null, null);
    }

    /**
     * Creates an error instance and assigns its values.
     *
     * @param locator the locator to find reference to dependent component.
     */
    public ReferenceException(Object locator) {
        super(null, "REF_ERROR", "Failed to obtain reference to " + locator);
        this.withDetails("locator", locator);
    }

    /**
     * Creates an error instance and assigns its values.
     *
     * @param correlationId (optional) a unique transaction id to trace execution
     *                      through call chain.
     * @param locator       the locator to find reference to dependent component.
     */
    public ReferenceException(String correlationId, Object locator) {
        super(correlationId, "REF_ERROR", "Failed to obtain reference to " + locator);
        this.withDetails("locator", locator);
    }

    public ReferenceException(String correlationId, String message) {
        super(correlationId, "REF_ERROR", message);
    }

    public ReferenceException(String correlationId, String code, String message) {
        super(correlationId, code, message);
    }
}
