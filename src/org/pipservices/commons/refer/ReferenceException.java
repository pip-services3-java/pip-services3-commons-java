package org.pipservices.commons.refer;

import org.pipservices.commons.errors.*;

/**
 * Exception thrown when required component is not found in references
 */
public class ReferenceException extends InternalException {
	private static final long serialVersionUID = 439183381933188434L;

	public ReferenceException() {
		this(null, null);
	}

	public ReferenceException(Object locator) {
		super(null, "REF_ERROR", "Failed to obtain reference to " + locator);
		this.withDetails("locator", locator);
	}

	public ReferenceException(String correlationId, Object locator) {
		super(correlationId, "REF_ERROR", "Failed to obtain reference to " + locator);
		this.withDetails("locator", locator);
	}

	public ReferenceException(String correlationId, String message) {
		super(correlationId, "REF_ERROR", message);
	}

	public ReferenceException(String correlationId, String code, String message) {
		super(correlationId, code, message);
	}
}
