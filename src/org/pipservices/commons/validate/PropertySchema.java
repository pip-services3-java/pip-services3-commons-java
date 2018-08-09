package org.pipservices.commons.validate;

import java.util.*;

public class PropertySchema extends Schema {
	private String _name;
	private Object _type;
	
    public PropertySchema() { }

    public PropertySchema(String name, Object type) {
        _name = name;
        _type = type;
    }

    public String getName() { return _name; }
    public void setName(String value) { _name = value; }
    
    public Object getType() { return _type; }
    public void setType(Object value) { _type = value; }

    @Override
    protected void performValidation(String path, Object value, List<ValidationResult> results) {
        path = path == null || path.length() == 0 ? _name : path + "." + _name;

        super.performValidation(path, value, results);
        performTypeValidation(path, _type, value, results);
    }
}
