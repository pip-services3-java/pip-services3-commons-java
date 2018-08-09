package org.pipservices.commons.errors;

/**
 * Errors happened during connection to remote services.
 * They can be related to misconfiguration, network issues or remote service itself 
 */
public class ConnectionException extends ApplicationException {
	private static final long serialVersionUID = 5757636441830366775L;

	public ConnectionException() {
		super(ErrorCategory.NoResponse, null, null, null);
		this.setStatus(500);
	}

	public ConnectionException(String correlationId, String code, String message) {
		super(ErrorCategory.NoResponse, correlationId, code, message);
		this.setStatus(500);
	}
}
