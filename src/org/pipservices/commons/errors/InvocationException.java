package org.pipservices.commons.errors;

/**
 * Errors returned by remote services or network during call attempts
 */
public class InvocationException extends ApplicationException {
	private static final long serialVersionUID = 7516215539095097503L;

	public InvocationException() {
		super(ErrorCategory.FailedInvocation, null, null, null);
		this.setStatus(500);
	}

	public InvocationException(String correlationId, String code, String message) {
		super(ErrorCategory.FailedInvocation, correlationId, code, message);
		this.setStatus(500);
	}
}
