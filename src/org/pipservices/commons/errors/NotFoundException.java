package org.pipservices.commons.errors;

/**
 * Error caused by attempt to access missing object
 */
public class NotFoundException extends ApplicationException {
	private static final long serialVersionUID = -3296918665715724164L;

	public NotFoundException() {
		super(ErrorCategory.NotFound, null, null, null);
		this.setStatus(404);
	}

	public NotFoundException(String correlationId, String code, String message) {
		super(ErrorCategory.NotFound, correlationId, code, message);
		this.setStatus(404);
	}
}
