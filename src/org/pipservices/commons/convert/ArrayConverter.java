package org.pipservices.commons.convert;

import java.lang.reflect.Array;
import java.util.*;

public class ArrayConverter {
            
	@SuppressWarnings("unchecked")
	public static List<Object> toNullableArray(Object value) {
		// Return null when nothing found
		if (value == null) {
			return null;
		}
		// Convert list
		else if (value instanceof List<?>) {
			return (List<Object>)value;
		}
		// Convert array
		else if (value.getClass().isArray()) {
			List<Object> array = new ArrayList<Object>();
			int length = Array.getLength(value);
			for (int index = 0; index < length; index++)
				array.add(Array.get(value, index));
			return array;
		}
		// Convert maps by taking all values and ignoring keys
		else if (value instanceof Map<?, ?>) {
			List<Object> array = new ArrayList<Object>();
			Map<Object, Object> map = (Map<Object, Object>)value;
			for (Map.Entry<Object, Object> entry : map.entrySet())
				array.add(entry.getValue());
			return array;
		}
		// Convert single values
		else {
			List<Object> array = new ArrayList<Object>();
			array.add(value);
			return array;
		}
	}

	public static List<Object> toArray(Object value) {
		List<Object> result = toNullableArray(value);
		return result != null ? result : new ArrayList<Object>();
	}

	public static List<Object> toArrayWithDefault(Object value, List<Object> defaultValue) {
		List<Object> result = toNullableArray(value);
		return result != null ? result : defaultValue;
	}
    
}
