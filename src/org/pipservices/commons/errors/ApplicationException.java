package org.pipservices.commons.errors;

import org.pipservices.commons.data.*;

import com.fasterxml.jackson.annotation.*;

public class ApplicationException extends Exception {
	private static final long serialVersionUID = -5846403471784245155L;

	private String _message;
	private String _category;
	private int _status = 500;
	private String _code = "UNKNOWN";
	private StringValueMap _details;
	private String _correlationId;
	private String _stackTrace;
	private String _cause;
	
	public ApplicationException() {
		this(ErrorCategory.Unknown, null, null, null);
	}

	public ApplicationException(String category, String correlationId, String code, String message) {
		super(message != null ? message : "Unknown error");
		_message = message != null ? message : "Unknown error";
		_correlationId = correlationId;
		_code = code != null ? code : "UNKNOWN";
		_category = category != null ? category : ErrorCategory.Unknown;
	}
	
    @JsonProperty("message")
    @Override
    public String getMessage() { return _message; }
    public void setMessage(String value) { _message = value; }

	@JsonProperty("category")
	public String getCategory() { return _category; }
	public void setCategory(String value) { _category = value; }
		
    @JsonProperty("code")
	public String getCode() { return _code; }
    public void setCode(String value) { _code = value; }
    
    @JsonProperty("status")
	public int getStatus() { return _status; }
    public void setStatus(int value) { _status = value; }

    @JsonProperty("details")
    public StringValueMap getDetails() { return _details; }
    public void setDetails(StringValueMap value) { _details = value; }
    
    @JsonProperty("correlation_id")
    public String getCorrelationId() { return _correlationId; }
    public void setCorrelationId(String value) { _correlationId = value; }
	
    @JsonProperty("cause")
    public String getCauseString() { 
    	if (super.getCause() != null)
    		return super.getCause().getMessage();
		return _cause;
    }
    public void setCauseString(String value) {
    	_cause = value;
    }
    
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
    public void setStackTraceString(String value) {
    	_stackTrace = value;
    }
    	
	public ApplicationException withCode(String code) {
		_code = code != null ? code : "UNKNOWN";
		return this;
	}
	
	public ApplicationException withStatus(int status) {
		_status = status;
		return this;
	}
	
	public ApplicationException withDetails(String key, Object value) {
		_details = _details != null ? _details : new StringValueMap();
		_details.setAsObject(key, value);
		return this;
	}
	
	public ApplicationException withCause(Throwable cause) {
		super.initCause(cause);
		return this;
	}
	
	public ApplicationException withCorrelationId(String correlationId) {
		_correlationId = correlationId;
		return this;
	}

	public ApplicationException wrap(Throwable cause) {
		if (cause instanceof ApplicationException) 
			return (ApplicationException)cause;

		this.withCause(cause);
		return this;
	}

	public static ApplicationException wrapException(
		ApplicationException error, Throwable cause) {
		
		if (cause instanceof ApplicationException) 
			return (ApplicationException)cause;

		error.withCause(cause);
		return error;
	}
	
}
