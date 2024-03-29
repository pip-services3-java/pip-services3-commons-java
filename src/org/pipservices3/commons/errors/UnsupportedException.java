package org.pipservices3.commons.errors;

/**
 * Errors caused by calls to unsupported or not yet implemented functionality
 */
public class UnsupportedException extends ApplicationException {
    private static final long serialVersionUID = -8650683748145033352L;

    /**
     * Creates an error instance and assigns its values.
     *
     * @see ErrorCategory
     */
    public UnsupportedException() {
        super(ErrorCategory.Unsupported, null, null, null);
        this.setStatus(500);
    }

    /**
     * Creates an error instance and assigns its values.
     *
     * @param correlationId (optional) a unique transaction id to trace execution through call chain.
     * @param code          (optional) a unique error code. Default: "UNKNOWN"
     * @param message       (optional) a human-readable description of the error.
     * @see ErrorCategory
     */
    public UnsupportedException(String correlationId, String code, String message) {
        super(ErrorCategory.Unsupported, correlationId, code, message);
        this.setStatus(500);
    }
}
