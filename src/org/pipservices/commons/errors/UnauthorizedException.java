package org.pipservices.commons.errors;

/**
 * Access errors caused by missing user identity or security permissions
 */
public class UnauthorizedException extends ApplicationException {
	private static final long serialVersionUID = 1728971490844757508L;

	public UnauthorizedException() {
		super(ErrorCategory.Unauthorized, null, null, null);
		this.setStatus(401);
	}

	public UnauthorizedException(String correlationId, String code, String message) {
		super(ErrorCategory.Unauthorized, correlationId, code, message);
		this.setStatus(401);
	}
}
