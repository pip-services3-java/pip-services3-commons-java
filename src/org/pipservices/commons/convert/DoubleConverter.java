package org.pipservices.commons.convert;

import java.time.Duration;
import java.util.*;

public class DoubleConverter {
    
    public static Double toNullableDouble(Object value) {
    	// Shortcuts
    	if (value == null) return null;

    	// All date and times (this is incomplete, consider removing)
    	if (value instanceof Date) return (double)((Date)value).getTime();
    	if (value instanceof Calendar) return (double)((Calendar)value).getTimeInMillis();
        if (value instanceof Duration) return (double)((Duration)value).toMillis();

    	// Boolean types
    	if (value instanceof Boolean) return (boolean)value ? 1.0 : 0.0;

    	// All numeric types
    	if (value instanceof Integer) return (double)((int)value);
    	if (value instanceof Short) return (double)((short)value);
    	if (value instanceof Long) return (double)((long)value);
    	if (value instanceof Float) return (double)((float)value); 
    	if (value instanceof Double) return (double)value;

    	// Strings
    	if (value instanceof String)
    		try {
    			return Double.parseDouble((String)value);
    		} catch (NumberFormatException ex) {
    			return null;
    		}
    	
    	// Everything else
    	return null;
    }

    public static double toDouble(Object value) {
    	return toDoubleWithDefault(value, 0);
    }
    
    public static double toDoubleWithDefault(Object value, double defaultValue) {
    	Double result = toNullableDouble(value);
    	return result != null ? (double)result : defaultValue;
    }
    
}
