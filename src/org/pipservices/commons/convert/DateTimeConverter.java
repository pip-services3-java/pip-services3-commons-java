package org.pipservices.commons.convert;

import java.time.*;
import java.time.format.*;
import java.util.*;

public class DateTimeConverter {
    private static final DateTimeFormatter simpleDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static final DateTimeFormatter simpleDateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        
    private static ZonedDateTime millisToDateTime(long millis) {
    	Instant instant = Instant.ofEpochMilli(millis);
    	return ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
    
    public static ZonedDateTime toNullableDateTime(Object value) {
    	// Shortcuts
    	if (value == null) return null;
    	if (value instanceof ZonedDateTime) return (ZonedDateTime)value;

    	// Legacy dates
    	if (value instanceof Calendar) {
    		Calendar calendar = (Calendar)value;
    		return ZonedDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
    	}
    	if (value instanceof Date) 
    		return ZonedDateTime.ofInstant(((Date) value).toInstant(), ZoneId.systemDefault());
    	
    	// New dates
    	if (value instanceof LocalDate)
    		return ZonedDateTime.of((LocalDate)value, LocalTime.of(0, 0), ZoneId.systemDefault());
    	if (value instanceof LocalDateTime)
    		return ZonedDateTime.of((LocalDateTime)value, ZoneId.systemDefault());
    	
    	// Number fields
    	if (value instanceof Integer) return millisToDateTime((int)value);
    	if (value instanceof Short) return millisToDateTime((short)value);
    	if (value instanceof Long) return millisToDateTime((long)value);
    	if (value instanceof Float) return millisToDateTime((long)((float)value)); 
    	if (value instanceof Double) return millisToDateTime((long)((double)value));
        if (value instanceof Duration) return millisToDateTime(((Duration)value).toMillis());
    	
    	// Strings
    	if (value instanceof String) {
    		// Parse ISO date and time with zone
    		try {
    			return ZonedDateTime.parse((String)value, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    		} catch (DateTimeParseException ex) {
    			// Ignore...
    		}

    		// Parse local date and time
    		try {
    			return ZonedDateTime.of(
					LocalDateTime.parse((String)value, simpleDateTimeFormatter),
					ZoneId.systemDefault()
				);
    		} catch (DateTimeParseException ex) {
    			// Ignore...
    		}

    		// Parse local date
    		try {
    			return ZonedDateTime.of(
					LocalDate.parse((String)value, simpleDateFormatter),
					LocalTime.of(0, 0),
					ZoneId.systemDefault()
				);
    		} catch (DateTimeParseException ex) {
    			// Ignore...
    		}
    	}
    	
    	return null;
    }
    
    public static ZonedDateTime toDateTime(Object value) {
    	return toDateTimeWithDefault(value, null);
    }
    
    public static ZonedDateTime toDateTimeWithDefault(Object value, ZonedDateTime defaultValue) {
    	ZonedDateTime result = toNullableDateTime(value);
    	return result != null ? result : defaultValue;
    }
            
}
