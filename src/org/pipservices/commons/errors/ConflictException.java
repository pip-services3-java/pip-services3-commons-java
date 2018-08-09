package org.pipservices.commons.errors;

/**
 * Errors raised by conflict in object versions posted by user and stored on server.
 */
public class ConflictException extends ApplicationException {
	private static final long serialVersionUID = -3421059253211761993L;

	public ConflictException() {
		super(ErrorCategory.Conflict, null, null, null);
		this.setStatus(409);
	}

	public ConflictException(String correlationId, String code, String message) {
		super(ErrorCategory.Conflict, correlationId, code, message);
		this.setStatus(409);
		this.setCorrelationId(correlationId);
	}
}
