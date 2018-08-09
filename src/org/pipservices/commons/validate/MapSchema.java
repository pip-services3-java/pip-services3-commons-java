package org.pipservices.commons.validate;

import java.util.*;

import org.pipservices.commons.reflect.ObjectReader;

public class MapSchema extends Schema {
	private Object _keyType;
	private Object _valueType;
	
	public MapSchema() { }

    public MapSchema(Object keyType, Object valueType) {
        _keyType = keyType;
        _valueType = valueType;
    }

    public Object getKeyType() { return _keyType; }
    public void setKeyType(Object value) { _keyType = value; }
    
    public Object getValueType() { return _valueType; }
    public void setValueType(Object value) { _valueType = value; }

    @SuppressWarnings("unchecked")
	@Override
    protected void performValidation(String path, Object value, List<ValidationResult> results) {
        String name = path != null ? path : "value";
        value = ObjectReader.getValue(value);

        super.performValidation(path, value, results);

        if (value == null) return;

        if (value instanceof Map<?, ?>) {
            Map<Object, Object> map = (Map<Object, Object>)value;
            for (Object key : map.keySet()) {
                String elementPath = path == null || path.length() == 0
                    ? key.toString() : path + "." + key;

                performTypeValidation(elementPath, _keyType, key, results);
                performTypeValidation(elementPath, _valueType, map.get(key), results);
            }
        } else {
            results.add(
                new ValidationResult(
                    path,
                    ValidationResultType.Error,
                    "VALUE_ISNOT_MAP",
                    name + " type must be Map",
                    "Map",
                    value.getClass()
                )
            );
        }
    }
}
