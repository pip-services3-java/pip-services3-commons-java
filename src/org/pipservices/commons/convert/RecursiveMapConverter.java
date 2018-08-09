package org.pipservices.commons.convert;

import java.util.*;

import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;

public class RecursiveMapConverter {
	private static ObjectMapper _mapper = new ObjectMapper();
	private static TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {};
	    
	private static List<Object> listToMap(Collection<Object> list) {
    	List<Object> result = new ArrayList<Object>();
    	for (Object item : list) 
    		result.add(valueToMap(item));
    	return result;
    }

	private static List<Object> arrayToMap(Object[] array) {
    	List<Object> result = new ArrayList<Object>();
    	for (Object item : array)
    		result.add(valueToMap(item));
    	return result;
    }
	
    private static Map<String, Object> mapToMap(Map<Object, Object> map) {
    	Map<String, Object> result = new HashMap<String, Object>();
    	for (Map.Entry<Object, Object> entry : map.entrySet()) {
    		result.put(StringConverter.toString(entry.getKey()) , valueToMap(entry.getValue()));
    	}
    	return result;
    }

    private static Map<String, Object> mapToMap2(Map<String, Object> map) {
    	Map<String, Object> result = new HashMap<String, Object>();
    	for (Map.Entry<String, Object> entry : map.entrySet()) {
    		result.put(entry.getKey(), valueToMap(entry.getValue()));
    	}
    	return result;
    }

	@SuppressWarnings("unchecked")
	private static Object valueToMap(Object value) {
    	if (value == null) return null;
    	if (value instanceof Map<?, ?>) return value;
    	
    	Class<?> valueClass = value.getClass();
    	if (valueClass.isPrimitive()) return value;
    	
    	if (value instanceof Map<?, ?>) 
    		return mapToMap((Map<Object, Object>)value);

    	if (valueClass.isArray())
    		return arrayToMap((Object[])value);

    	if (value instanceof Collection<?>)
    		return listToMap((Collection<Object>)value);
    	
    	try {
    		Map<String, Object> map = _mapper.convertValue(value, typeRef);
    		return mapToMap2(map);
		} catch (Exception ex) {
			return value;
		}
    }

	@SuppressWarnings("unchecked")
	public static Map<String, Object> toNullableMap(Object value) {
    	if (value == null) return null;

		Object result = valueToMap(value);
		if (result instanceof Map<?, ?>)
			return (Map<String, Object>)result;
		return null;
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
