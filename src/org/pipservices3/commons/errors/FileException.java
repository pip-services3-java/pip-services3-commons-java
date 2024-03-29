package org.pipservices3.commons.errors;

/**
 * Errors in read/write file operations
 */
public class FileException extends ApplicationException {
    private static final long serialVersionUID = 1330544660294516445L;

    /**
     * Creates an error instance and assigns its values.
     *
     * @see ErrorCategory
     */
    public FileException() {
        super(ErrorCategory.FileError, null, null, null);
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
    public FileException(String correlationId, String code, String message) {
        super(ErrorCategory.FileError, correlationId, code, message);
        this.setStatus(500);
    }
}
