package org.pipservices.commons.errors;

/**
 * Unknown or unexpected errors
 */
public class UnknownException extends ApplicationException {
	private static final long serialVersionUID = -8513540232023043856L;

	public UnknownException() {
		super(ErrorCategory.Unknown, null, null, null);
		this.setStatus(500);
	}

	public UnknownException(String correlationId, String code, String message) {
		super(ErrorCategory.Unknown, correlationId, code, message);
		this.setStatus(500);
	}
}
