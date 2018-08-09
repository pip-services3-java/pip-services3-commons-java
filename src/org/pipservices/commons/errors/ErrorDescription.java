package org.pipservices.commons.errors;

import org.pipservices.commons.data.*;

import com.fasterxml.jackson.annotation.*;

public class ErrorDescription {
	private String _type;
	private String _category;
	private int _status;
	private String _code;
	private String _message;
	private StringValueMap _details;
	private String _correlationId;
	private String _cause;
	private String _stackTrace;

	public ErrorDescription() {}
	
    @JsonProperty("type")
	public String getType() { return _type; }
	public void setType(String value) { _type = value; }

	@JsonProperty("category")
	public String getCategory() { return _category; }
	public void setCategory(String value) { _category = value; }
		
    @JsonProperty("status")
	public int getStatus() { return _status; }
    public void setStatus(int value) { _status = value; }

    @JsonProperty("code")
	public String getCode() { return _code; }
    public void setCode(String value) { _code = value; }
    
    @JsonProperty("message")
	public String getMessage() { return _message; }
    public void setMessage(String value) { _message = value; }

    @JsonProperty("details")
    public StringValueMap getDetails() { return _details; }
    public void setDetails(StringValueMap value) { _details = value; }
    
    @JsonProperty("correlation_id")
    public String getCorrelationId() { return _correlationId; }
    public void setCorrelationId(String value) { _correlationId = value; }

    @JsonProperty("cause")
    public String getCause() { return _cause; }
    public void setCause(String value) { _cause = value; }

    @JsonProperty("stack_trace")
    public String getStackTrace() { return _stackTrace; }
    public void setStackTrace(String value) { _stackTrace = value; }              
}
