package org.pipservices.commons.errors;

/**
 * Errors due to improper user requests, like missing or wrong parameters 
 */
public class BadRequestException extends ApplicationException {
	private static final long serialVersionUID = -6858254084911710376L;

	public BadRequestException() {
		super(ErrorCategory.BadRequest, null, null, null);
		this.setStatus(400);
	}

	public BadRequestException(String correlationId, String code, String message) {
		super(ErrorCategory.BadRequest, correlationId, code, message);
		this.setStatus(400);
	}
}
