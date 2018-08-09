package org.pipservices.commons.validate;

import com.fasterxml.jackson.annotation.*;

public class ValidationResult {
	private String _path;
	private ValidationResultType _type;
	private String _code;
	private String _message;
	private Object _expected;
	private Object _actual;
	
	public ValidationResult() { }

    public ValidationResult(String path, ValidationResultType type,
        String code, String message, Object expected, Object actual) {
        _path = path;
        _type = type;
        _code = code;
        _message = message;
    }

    @JsonProperty("path")
    public String getPath() { return _path; }
    public void setPath(String value) { _path = value; }
    
    @JsonProperty("type")
    public ValidationResultType getType() { return _type; }
    public void setType(ValidationResultType value) { _type = value; }
    
    @JsonProperty("code")
    public String getCode() { return _code; }
    public void setCode(String value) { _code = value; }
    
    @JsonProperty("message")
    public String getMessage() { return _message; }
    public void setMessage(String value) { _message = value; }
    
    @JsonProperty("expected")
    public Object getExpected() { return _expected; }
    public void setExpected(Object value) { _expected = value; }
    
    @JsonProperty("actual")
    public Object getActual() { return _actual; }
    public void setActual(Object value) { _actual = value; }
}
