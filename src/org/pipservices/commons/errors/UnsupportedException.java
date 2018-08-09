package org.pipservices.commons.errors;

/**
 * Errors caused by calls to unsupported or not yet implemented functionality
 */
public class UnsupportedException extends ApplicationException {
	private static final long serialVersionUID = -8650683748145033352L;

	public UnsupportedException() {
		super(ErrorCategory.Unsupported, null, null, null);
		this.setStatus(500);
	}

	public UnsupportedException(String correlationId, String code, String message) {
		super(ErrorCategory.Unsupported, correlationId, code, message);
		this.setStatus(500);
	}
}
