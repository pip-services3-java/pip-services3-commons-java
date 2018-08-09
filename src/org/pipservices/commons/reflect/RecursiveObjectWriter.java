package org.pipservices.commons.reflect;

import java.util.*;

public class RecursiveObjectWriter {

	// Todo: Make it smarter
	private static Object createProperty(Object obj, String name) {
		return new HashMap<String, Object>();
	}
	
	private static void performSetProperty(Object obj, String[] names, int nameIndex, Object value) {
		if (nameIndex < names.length - 1) {
			Object subObj = ObjectReader.getProperty(obj, names[nameIndex]);
			if (subObj != null)
				performSetProperty(subObj, names, nameIndex + 1, value);
			else {
				subObj = createProperty(obj, names[nameIndex]);
				if (subObj != null) {					
					performSetProperty(subObj, names, nameIndex + 1, value);
					ObjectWriter.setProperty(obj, names[nameIndex], subObj);
				}
			}
		} else
			ObjectWriter.setProperty(obj, names[nameIndex], value);
	}

	public static void setProperty(Object obj, String name, Object value) {
        if (obj == null || name == null) return;

        String[] names = name.split("\\.");
        if (names == null || names.length == 0) 
        	return;

        performSetProperty(obj, names, 0, value);
	}

	public static void setProperties(Object obj, Map<String, Object> values) {
		if (values == null || values.size() == 0) return;
		
		for (Map.Entry<String, Object> entry : values.entrySet()) {
			setProperty(obj, entry.getKey(), entry.getValue());
		}
	}

	public static void copyProperties(Object dest, Object src) {
		if (dest == null || src == null) return;
		
		Map<String, Object> values = RecursiveObjectReader.getProperties(src);
		setProperties(dest, values);
	}

}
