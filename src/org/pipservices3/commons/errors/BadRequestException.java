package org.pipservices3.commons.errors;

/**
 * Errors due to improper user requests.
 * <p>
 * For example: missing or incorrect parameters.
 */
public class BadRequestException extends ApplicationException {
    private static final long serialVersionUID = -6858254084911710376L;

    /**
     * Creates an error instance with bad request error category and assigns its values.
     *
     * @see ErrorCategory
     */
    public BadRequestException() {
        super(ErrorCategory.BadRequest, null, null, null);
        this.setStatus(400);
    }

    /**
     * Creates an error instance and assigns its values.
     *
     * @param correlationId (optional) a unique transaction id to trace execution through call chain.
     * @param code          (optional) a unique error code. Default: "UNKNOWN"
     * @param message       (optional) a human-readable description of the error.
     * @see ErrorCategory
     */
    public BadRequestException(String correlationId, String code, String message) {
        super(ErrorCategory.BadRequest, correlationId, code, message);
        this.setStatus(400);
    }
}
