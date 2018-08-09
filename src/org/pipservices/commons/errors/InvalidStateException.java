package org.pipservices.commons.errors;

/**
 * Errors related to operations called in wrong component state.
 * For instance, business calls when component is not ready
 */
public class InvalidStateException extends ApplicationException {
	private static final long serialVersionUID = 8713306897733892945L;

	public InvalidStateException() {
		super(ErrorCategory.InvalidState, null, null, null);
		this.setStatus(500);
	}

	public InvalidStateException(String correlationId, String code, String message) {
		super(ErrorCategory.InvalidState, correlationId, code, message);
		this.setStatus(500);
	}
}
