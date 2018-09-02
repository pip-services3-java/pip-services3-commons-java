package org.pipservices.commons.reflect;

import java.time.*;
import java.util.*;

import org.pipservices.commons.convert.TypeCode;
import org.pipservices.commons.convert.TypeConverter;

public class TypeMatcher {
	
    public static boolean matchValue(Object expectedType, Object actualValue) {
        if (expectedType == null)
            return true;
        if (actualValue == null)
        	throw new NullPointerException("Actual value cannot be null");

        return matchType(expectedType, actualValue.getClass());
    }

    public static boolean matchType(Object expectedType, Class<?> actualType) {
        if (expectedType == null)
            return true;
        if (actualType == null)
        	throw new NullPointerException("Actual type cannot be null");

        if (expectedType instanceof Class<?>)
            return ((Class<?>)expectedType).isAssignableFrom(actualType);
        
        if (expectedType instanceof String)
            return matchTypeByName((String)expectedType, actualType);

        if (expectedType instanceof TypeCode)
        	return TypeConverter.toTypeCode(actualType).equals(expectedType);
        
        return false;
    }

    public static boolean matchValueByName(String expectedType, Object actualValue) {
        if (expectedType == null) 
        	return true;
        if (actualValue == null)
        	throw new NullPointerException("Actual value cannot be null");

        return matchTypeByName(expectedType, actualValue.getClass());
    }

    public static boolean matchTypeByName(String expectedType, Class<?> actualType) {
        if (expectedType == null)
        	return true;
        if (actualType == null)
        	throw new NullPointerException("Actual type cannot be null");
        
        expectedType = expectedType.toLowerCase();

        if (actualType.getName().equalsIgnoreCase(expectedType)) 
        	return true;
        else if (actualType.getSimpleName().equalsIgnoreCase(expectedType)) 
        	return true;        
        else if (expectedType.equals("object"))
            return true;
        else if (expectedType.equals("int") || expectedType.equals("integer")) {
            return Integer.class.isAssignableFrom(actualType)
                || Long.class.isAssignableFrom(actualType);
        } else if (expectedType.equals("long")) {
            return Long.class.isAssignableFrom(actualType);
        } else if (expectedType.equals("float")) {
            return Float.class.isAssignableFrom(actualType)
                || Double.class.isAssignableFrom(actualType);
        } else if (expectedType.equals("double")) {
            return Double.class.isAssignableFrom(actualType);
        } else if (expectedType.equals("string")) {
            return String.class.isAssignableFrom(actualType);
        } else if (expectedType.equals("bool") || expectedType.equals("boolean")) {
            return Boolean.class.isAssignableFrom(actualType);
        } else if (expectedType.equals("date") || expectedType.equals("datetime")) {
            return Date.class.isAssignableFrom(actualType)
            	|| Calendar.class.isAssignableFrom(actualType)
                || ZonedDateTime.class.isAssignableFrom(actualType);
        } else if (expectedType.equals("timespan") || expectedType.equals("duration")) {
            return Integer.class.isAssignableFrom(actualType)
                || Long.class.isAssignableFrom(actualType)
                || Float.class.isAssignableFrom(actualType)
                || Double.class.isAssignableFrom(actualType);
        } else if (expectedType.equals("enum")) {
            return Enum.class.isAssignableFrom(actualType);
        } else if (expectedType.equals("map") || expectedType.equals("dict") || expectedType.equals("dictionary")) {
            return Map.class.isAssignableFrom(actualType);
        } else if (expectedType.equals("array") || expectedType.equals("list")) {
            return actualType.isArray()
            	|| List.class.isAssignableFrom(actualType);
        } else if (expectedType.endsWith("[]")) {
        	// Todo: Check subtype
            return actualType.isArray()
            	|| List.class.isAssignableFrom(actualType);
        } else
            return false;
    }
}
