package org.pipservices.commons.errors;

/**
 * Errors related to mistakes in microservice user-defined configuration
 */
public class ConfigException extends ApplicationException {
	private static final long serialVersionUID = 3832437788895163030L;

	public ConfigException() {
		super(ErrorCategory.Misconfiguration, null, null, null);
		this.setStatus(500);
	}

	public ConfigException(String correlationId, String code, String message) {
		super(ErrorCategory.Misconfiguration, correlationId, code, message);
		this.setStatus(500);
	}
}
