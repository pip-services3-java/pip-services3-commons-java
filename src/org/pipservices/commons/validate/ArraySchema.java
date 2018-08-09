package org.pipservices.commons.validate;

import java.lang.reflect.Array;
import java.util.*;

import org.pipservices.commons.reflect.ObjectReader;

public class ArraySchema extends Schema {
	private Object _valueType;
	
    public ArraySchema() { }

    public ArraySchema(Object valueType) {
        _valueType = valueType;
    }

    public Object getValueType() { return _valueType; }
    public void setValueType(Object value) { _valueType = value; }

    @SuppressWarnings("unchecked")
	@Override
    protected void performValidation(String path, Object value, List<ValidationResult> results) {
        String name = path != null ? path : "value";
        value = ObjectReader.getValue(value);

        super.performValidation(path, value, results);

        if (value == null) return;

        if (value instanceof List<?>) {
            List<Object> list = (List<Object>)value;
            int index = 0;
            for (Object element : list) {
                String elementPath = path == null || path.length() == 0
                    ? Integer.toString(index) : path + "." + index;
                performTypeValidation(elementPath, _valueType, element, results);
                index++;
            }
        } else if (value.getClass().isArray()) {
			int length = Array.getLength(value);
			for (int index = 0; index < length; index++) {
				Object element = Array.get(value, index);
                String elementPath = path == null || path.length() == 0
                    ? Integer.toString(index) : path + "." + index;
                performTypeValidation(elementPath, _valueType, element, results);
                index++;
			}
        } else {
            results.add(
                new ValidationResult(
                    path,
                    ValidationResultType.Error,
                    "VALUE_ISNOT_ARRAY",
                    name + " type must be List or Array",
                    "List",
                    value.getClass()
                )
            );
        }
    }
}
