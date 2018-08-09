package org.pipservices.commons.convert;

import java.time.*;

public class BooleanConverter {

	public static Boolean toNullableBoolean(Object value) {
    	// Shortcuts
        if (value == null) return null;
        if (value instanceof Boolean) return (boolean)value;
        if (value instanceof Duration) return ((Duration)value).toMillis() > 0;

    	// All true values
        String strValue = value.toString().toLowerCase();
        if (strValue.equals("1") || strValue.equals("true") 
    		|| strValue.equals("t") || strValue.equals("yes") 
    		|| strValue.equals("y"))
            return true;

    	// All false values
        if (strValue.equals("0") || strValue.equals("false") 
    		|| strValue.equals("f") || strValue.equals("no") 
    		|| strValue.equals("n"))
            return false;

    	// Everything else
        return null;
    }
    
    public static boolean toBoolean(Object value) {
    	return toBooleanWithDefault(value, false);
    }
    
    public static boolean toBooleanWithDefault(Object value, boolean defaultValue) {
    	Boolean result = toNullableBoolean(value);
    	return result != null ? (boolean)result : defaultValue;
    }
    
}
