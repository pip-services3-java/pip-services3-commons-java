package org.pipservices.commons.errors;

/**
 * Errors in read/write file operations
 */
public class FileException extends ApplicationException {
	private static final long serialVersionUID = 1330544660294516445L;

	public FileException() {
		super(ErrorCategory.FileError, null, null, null);
		this.setStatus(500);
	}

	public FileException(String correlationId, String code, String message) {
		super(ErrorCategory.FileError, correlationId, code, message);
		this.setStatus(500);
	}
}
