package org.pipservices.commons.convert;

import java.time.Duration;
import java.util.*;

public class LongConverter {
    
    public static Long toNullableLong(Object value) {
    	// Shortcuts
    	if (value == null) return null;
    	
    	// All date and times (this is incomplete, consider removing)
    	if (value instanceof Date) return ((Date)value).getTime();
    	if (value instanceof Calendar) return ((Calendar)value).getTimeInMillis();
        if (value instanceof Duration) return ((Duration)value).toMillis();
    	
    	// Booleans
    	if (value instanceof Boolean) return (boolean)value ? 1L : 0L;
    	
    	// All numeric types
    	if (value instanceof Integer) return (long)((int)value);
    	if (value instanceof Short) return (long)((short)value);
    	if (value instanceof Long) return (long)value;
    	if (value instanceof Float) return (long) Math.round((float)value); 
    	if (value instanceof Double) return (long) Math.round((double)value);

    	// Strings
    	if (value instanceof String)
    		try {
    			return (long) Math.round(Double.parseDouble((String)value));
    		} catch (NumberFormatException ex) {
    			return null;
    		}
    	
    	// Everything else
    	return null;
    }

    public static long toLong(Object value) {
    	return toLongWithDefault(value, 0);
    }
    
    public static long toLongWithDefault(Object value, long defaultValue) {
    	Long result = toNullableLong(value);
    	return result != null ? (long)result : defaultValue;
    }

}
