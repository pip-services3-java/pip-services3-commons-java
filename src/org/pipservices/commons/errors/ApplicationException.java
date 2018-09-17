package org.pipservices.commons.errors;

import org.pipservices.commons.data.*;

import com.fasterxml.jackson.annotation.*;

/**
 * Defines a base class to defive various application exceptions.
 * 
 * Most languages have own definition of base exception (error) types.
 * However, this class is implemented symmetrically in all languages
 * supported by PipServices toolkit. It allows to create portable implementations
 * and support proper error propagation in microservices calls.
 * 
 * Error propagation means that when microservice implemented in one language
 * calls microservice(s) implemented in a different language(s), errors are returned
 * throught the entire call chain and restored in their original (or close) type.
 * 
 * Since number of potential exception types is endless, PipServices toolkit
 * supports only 12 standard categories of exceptions defined in ErrorCategory.
 * This ApplicationException class acts as a basis for
 * all other 12 standard exception types.
 * 
 * Most exceptions have just free-form message that describes occured error.
 * That may not be sufficient to create meaninful error descriptions.
 * The ApplicationException class proposes an extended error definition
 * that has more standard fields:
 * 
 * - message: is a humand readable error description
 * - category: one of 12 standard error categories of errors
 * - status: numeric HTTP status code for REST invocations
 * - code: a unique error code, usually defined as "MY_ERROR_CODE"
 * - correlation_id: a unique transaction id to trace execution through a call chain
 * - details: map with error parameters that can help to recreate meaningful error description in other languages
 * - stack_trace: a stack trace
 * - cause: original error that is wrapped by this exception
 * 
 * ApplicationException class is not serializable. To pass errors through the wire
 * it is converted into ErrorDescription object and restored on receiving end into
 * identical exception type.
 * 
 * @see ErrorCategory
 * @see ErrorDescription
 */
public class ApplicationException extends Exception {
	private static final long serialVersionUID = -5846403471784245155L;

	/** A human-readable error description (usually written in English) */
	private String _message;
	/** Standard error category */
	private String _category;
	/** HTTP status code associated with this error type */
	private int _status = 500;
	/** A unique error code */
	private String _code = "UNKNOWN";
	/**
	 * A map with additional details that can be used to restore error description
	 * in other languages
	 */
	private StringValueMap _details;
	/** A unique transaction id to trace execution throug call chain */
	private String _correlationId;
	/** Stack trace of the exception */
	private String _stackTrace;
	/** Original error wrapped by this exception */
	private String _cause;

	/**
	 * Creates a new instance of application exception with unknown error category
	 * and assigns its values.
	 */
	public ApplicationException() {
		this(ErrorCategory.Unknown, null, null, null);
	}

	/**
	 * Creates a new instance of application exception and assigns its values.
	 * 
	 * @param category       (optional) a standard error category. Default: Unknown
	 * @param correlationId (optional) a unique transaction id to trace execution
	 *                       through call chain.
	 * @param code           (optional) a unique error code. Default: "UNKNOWN"
	 * @param message        (optional) a human-readable description of the error.
	 */
	public ApplicationException(String category, String correlationId, String code, String message) {
		super(message != null ? message : "Unknown error");
		_message = message != null ? message : "Unknown error";
		_correlationId = correlationId;
		_code = code != null ? code : "UNKNOWN";
		_category = category != null ? category : ErrorCategory.Unknown;
	}

	@JsonProperty("message")
	@Override
	public String getMessage() {
		return _message;
	}

	public void setMessage(String value) {
		_message = value;
	}

	@JsonProperty("category")
	public String getCategory() {
		return _category;
	}

	public void setCategory(String value) {
		_category = value;
	}

	@JsonProperty("code")
	public String getCode() {
		return _code;
	}

	public void setCode(String value) {
		_code = value;
	}

	@JsonProperty("status")
	public int getStatus() {
		return _status;
	}

	public void setStatus(int value) {
		_status = value;
	}

	@JsonProperty("details")
	public StringValueMap getDetails() {
		return _details;
	}

	public void setDetails(StringValueMap value) {
		_details = value;
	}

	@JsonProperty("correlation_id")
	public String getCorrelationId() {
		return _correlationId;
	}

	public void setCorrelationId(String value) {
		_correlationId = value;
	}

	/**
	 * Gets original error wrapped by this exception as a string message.
	 * 
	 * @return an original error message.
	 */
	@JsonProperty("cause")
	public String getCauseString() {
		if (super.getCause() != null)
			return super.getCause().getMessage();
		return _cause;
	}

	/**
	 * Sets original error wrapped by this exception as a string message.
	 * 
	 * @param value an original error message.
	 */
	public void setCauseString(String value) {
		_cause = value;
	}

	/**
	 * Gets a stack trace where this exception occured.
	 * 
	 * @return a stack trace as a string.
	 */
	@JsonProperty("stack_trace")
	public String getStackTraceString() {
		if (_stackTrace != null)
			return _stackTrace;

		StackTraceElement[] ste = getStackTrace();
		StringBuilder builder = new StringBuilder();
		if (ste != null) {
			for (int i = 0; i < ste.length; i++) {
				if (builder.length() > 0)
					builder.append(" ");
				builder.append(ste[i].toString());
			}
		}
		return builder.toString();
	}

	/**
	 * Sets a stack trace where this exception occured.
	 * 
	 * @param value a stack trace as a string
	 */
	public void setStackTraceString(String value) {
		_stackTrace = value;
	}

	/**
	 * Sets a unique error code.
	 * 
	 * This method returns reference to this exception to implement Builder pattern
	 * to chain additional calls.
	 * 
	 * @param code a unique error code
	 * @return this exception object
	 */
	public ApplicationException withCode(String code) {
		_code = code != null ? code : "UNKNOWN";
		return this;
	}

	/**
	 * Sets a HTTP status code which shall be returned by REST calls.
	 * 
	 * This method returns reference to this exception to implement Builder pattern
	 * to chain additional calls.
	 * 
	 * @param status an HTTP error code.
	 * @return this exception object
	 */
	public ApplicationException withStatus(int status) {
		_status = status;
		return this;
	}

	/**
	 * Sets a parameter for additional error details. This details can be used to
	 * restore error description in other languages.
	 * 
	 * This method returns reference to this exception to implement Builder pattern
	 * to chain additional calls.
	 * 
	 * @param key   a details parameter name
	 * @param value a details parameter name
	 * @return this exception object
	 */
	public ApplicationException withDetails(String key, Object value) {
		_details = _details != null ? _details : new StringValueMap();
		_details.setAsObject(key, value);
		return this;
	}

	/**
	 * Sets a original error wrapped by this exception
	 * 
	 * This method returns reference to this exception to implement Builder pattern
	 * to chain additional calls.
	 * 
	 * @param cause original error object
	 * @return this exception object
	 */
	public ApplicationException withCause(Throwable cause) {
		super.initCause(cause);
		return this;
	}

	/**
	 * Sets a correlation id which can be used to trace this error through a call
	 * chain.
	 * 
	 * This method returns reference to this exception to implement Builder pattern
	 * to chain additional calls.
	 * 
	 * @param correlationId a unique transaction id to trace error through call
	 *                      chain
	 * @return this exception object
	 */
	public ApplicationException withCorrelationId(String correlationId) {
		_correlationId = correlationId;
		return this;
	}

	/**
	 * Sets a stack trace for this error.
	 * 
	 * This method returns reference to this exception to implement Builder pattern
	 * to chain additional calls.
	 * 
	 * @param stackTrace a stack trace where this error occured
	 * @return this exception object
	 */
	public ApplicationException withStackTrace(String stackTrace) {
		_stackTrace = stackTrace;
		return this;
	}

	/**
	 * Wraps another exception into an application exception object.
	 * 
	 * If original exception is of ApplicationException type it is returned without
	 * changes. Otherwise a new ApplicationException is created and original error
	 * is set as its cause.
	 * 
	 * @param cause an original error object
	 * @return an original or newly created ApplicationException
	 */
	public ApplicationException wrap(Throwable cause) {
		if (cause instanceof ApplicationException)
			return (ApplicationException) cause;

		this.withCause(cause);
		return this;
	}

	/**
	 * Wraps another exception into specified application exception object.
	 * 
	 * If original exception is of ApplicationException type it is returned without
	 * changes. Otherwise the original error is set as a cause to specified
	 * ApplicationException object.
	 * 
	 * @param error an ApplicationException object to wrap the cause
	 * @param cause an original error object
	 * @return an original or newly created ApplicationException
	 * 
	 */
	public static ApplicationException wrapException(ApplicationException error, Throwable cause) {

		if (cause instanceof ApplicationException)
			return (ApplicationException) cause;

		error.withCause(cause);
		return error;
	}

}
