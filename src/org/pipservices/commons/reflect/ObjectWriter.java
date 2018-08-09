package org.pipservices.commons.reflect;

import java.lang.reflect.*;
import java.util.*;

import org.pipservices.commons.convert.IntegerConverter;

public class ObjectWriter {
	
	@SuppressWarnings("unchecked")
	public static void setProperty(Object obj, String name, Object value) {
		if (obj == null)
			throw new NullPointerException("Object cannot be null");
		if (name == null)
			throw new NullPointerException("Property name cannot be null");

        if (obj instanceof Map<?,?>) {
            Map<Object,Object> mapObj = (Map<Object,Object>)obj;
            for (Object key : mapObj.keySet()) {
            	if (name.equalsIgnoreCase(key.toString())) {
            		mapObj.put(key, value);
            		return;
            	}
            }
            mapObj.put(name, value);
        } else if (obj instanceof List<?>) {
            List<Object> list = (List<Object>)obj;
            int index = IntegerConverter.toIntegerWithDefault(name, -1);
            if (index < 0)
            	return;
            else if (index < list.size())
            	list.set(index, value);
            else {
            	while (index - 1 >= list.size())
            		list.add(null);
            	list.add(value);
            }
        } else if (obj.getClass().isArray()) {
			int length = Array.getLength(obj);
            int index = IntegerConverter.toIntegerWithDefault(name, -1);
			if (index >= 0 && index < length)
				Array.set(obj, index, value);
        } else {
            PropertyReflector.setProperty(obj, name, value);
        }
	}
	
	public static void setProperties(Object obj, Map<String, Object> values) {
		if (values == null || values.size() == 0) return;
		
		for (Map.Entry<String, Object> entry : values.entrySet()) {
			setProperty(obj, entry.getKey(), entry.getValue());
		}
	}
}
