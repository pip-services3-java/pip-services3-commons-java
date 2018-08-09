package org.pipservices.commons.convert;

import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

public class MapConverter {
	private static ObjectMapper mapper = new ObjectMapper();
	private static TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {};
    
	private static Map<String, Object> listToMap(Collection<Object> list) {
    	Map<String, Object> result = new HashMap<String, Object>();
    	int index = 0;
    	for (Object item : list) {
    		result.put(Integer.toString(index), item);
    		index++;
    	}
    	return result;
    }

	private static Map<String, Object> arrayToMap(Object[] array) {
    	Map<String, Object> result = new HashMap<String, Object>();
    	int index = 0;
    	for (Object item : array) {
    		result.put(Integer.toString(index), item);
    		index++;
    	}
    	return result;
    }
	
    private static Map<String, Object> mapToMap(Map<Object, Object> map) {
    	Map<String, Object> result = new HashMap<String, Object>();
    	for (Map.Entry<Object, Object> entry : map.entrySet()) {
    		result.put(StringConverter.toString(entry.getKey()) , entry.getValue());
    	}
    	return result;
    }

    @SuppressWarnings("unchecked")
	public static Map<String, Object> toNullableMap(Object value) {
    	if (value == null) return null;
    	
    	Class<?> valueClass = value.getClass();
    	if (valueClass.isPrimitive()) return null;
    	
    	if (value instanceof Map<?, ?>) 
    		return mapToMap((Map<Object, Object>)value);

    	if (valueClass.isArray())
    		return arrayToMap((Object[])value);

    	if (value instanceof Collection<?>)
    		return listToMap((Collection<Object>)value);
    	
    	try {
    		return mapper.convertValue(value, typeRef);
		} catch (Exception ex) {
			return null;
		}
    }

	public static Map<String, Object> toMap(Object value) {
    	Map<String, Object> result = toNullableMap(value);
    	return result != null ? result : new HashMap<String, Object>();
    }

	public static Map<String, Object> toMapWithDefault(Object value, Map<String, Object> defaultValue) {
    	Map<String, Object> result = toNullableMap(value);
    	return result != null ? result : defaultValue;
    }
}
