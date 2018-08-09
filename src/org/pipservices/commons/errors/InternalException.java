package org.pipservices.commons.errors;

/**
 * Errors caused by programming mistakes
 */
public class InternalException extends ApplicationException {
	private static final long serialVersionUID = 9121408616688009166L;

	public InternalException() {
		super(ErrorCategory.Internal, null, null, null);
		this.setStatus(500);
	}

	public InternalException(String correlationId, String code, String message) {
		super(ErrorCategory.Internal, correlationId, code, message);
		this.setStatus(500);
	}
}
