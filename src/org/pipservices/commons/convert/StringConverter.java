package org.pipservices.commons.convert;

import java.time.*;
import java.time.format.*;
import java.util.*;

public class StringConverter {

	public static String toNullableString(Object value) {
    	// Shortcuts
        if (value == null) return null;
        if (value instanceof String) return (String)value;

        // Legacy and new dates
        if (value instanceof Date)
        	value = ZonedDateTime.ofInstant(((Date)value).toInstant(), ZoneId.systemDefault());
        if (value instanceof Calendar) { 
        	value = ZonedDateTime.ofInstant(
    			((Calendar)value).toInstant(), ((Calendar)value).getTimeZone().toZoneId());
        }
        if (value instanceof Duration) 
        	value = ((Duration)value).toMillis();
        if (value instanceof Instant)
        	value = ZonedDateTime.ofInstant((Instant)value, ZoneId.systemDefault());
        if (value instanceof LocalDateTime)
        	value = ZonedDateTime.of((LocalDateTime)value, ZoneId.systemDefault());
        if (value instanceof LocalDate)
        	value = ZonedDateTime.of((LocalDate)value, LocalTime.of(0,0), ZoneId.systemDefault());
        if (value instanceof ZonedDateTime)
        	return ((ZonedDateTime)value).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    	// Everything else
        return value.toString();
	}

	public static String toString(Object value) {
		return toStringWithDefault(value, "");
	}
	
    public static String toStringWithDefault(Object value, String defaultValue) {
    	String result = toNullableString(value);
    	return result != null ? result : defaultValue;
    }

}
