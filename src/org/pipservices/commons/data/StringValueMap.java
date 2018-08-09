package org.pipservices.commons.data;

import java.time.*;
import java.util.*;
import org.pipservices.commons.convert.*;

public class StringValueMap extends HashMap<String, String> implements Cloneable {
	private static final long serialVersionUID = -7743560121354524782L;

	public StringValueMap() { }

    public StringValueMap(Map<?, ?> map) { 
    	append(map);
    }
    
    public String get(String key) {
    	return super.get(key);
    }
    
    public void append(Map<?, ?> map) {
    	if (map == null || map.size() == 0) return;
    	
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			put(
				StringConverter.toString(entry.getKey()), 
				StringConverter.toNullableString(entry.getValue())
			);
		}
    }
        
    public Object getAsObject() {
    	Map<String, Object> result = new HashMap<String, Object>();
    	for (Map.Entry<String, String> entry : this.entrySet()) {
    		result.put(entry.getKey(), entry.getValue());
    	}
    	return result;
    }
    
    public void setAsObject(Object value) {
    	clear();
    	Map<String, Object> values = MapConverter.toMap(value);
    	append(values);
    }
    
    public Object getAsObject(String key) {
        return get(key);
    }

    public void setAsObject(String key, Object value) {
        put(key, StringConverter.toNullableString(value));
    }

    public String getAsNullableString(String key) {
        Object value = getAsObject(key);
        return StringConverter.toNullableString(value);
    }

    public String getAsString(String key) {
        return getAsStringWithDefault(key, null);
    }

    public String getAsStringWithDefault(String key, String defaultValue) {
        Object value = getAsObject(key);
        return StringConverter.toStringWithDefault(value, defaultValue);
    }

    public Boolean getAsNullableBoolean(String key) {
        Object value = getAsObject(key);
        return BooleanConverter.toNullableBoolean(value);
    }

    public boolean getAsBoolean(String key) {
        return getAsBooleanWithDefault(key, false);
    }

    public boolean getAsBooleanWithDefault(String key, boolean defaultValue) {
        Object value = getAsObject(key);
        return BooleanConverter.toBooleanWithDefault(value, defaultValue);
    }

    public Integer getAsNullableInteger(String key) {
        Object value = getAsObject(key);
        return IntegerConverter.toNullableInteger(value);
    }

    public int getAsInteger(String key) {
        return getAsIntegerWithDefault(key, 0);
    }

    public int getAsIntegerWithDefault(String key, int defaultValue) {
        Object value = getAsObject(key);
        return IntegerConverter.toIntegerWithDefault(value, defaultValue);
    }

    public Long getAsNullableLong(String key) {
        Object value = getAsObject(key);
        return LongConverter.toNullableLong(value);
    }

    public long getAsLong(String key) {
        return getAsLongWithDefault(key, 0);
    }

    public long getAsLongWithDefault(String key, long defaultValue) {
        Object value = getAsObject(key);
        return LongConverter.toLongWithDefault(value, defaultValue);
    }

    public Float getAsNullableFloat(String key) {
        Object value = getAsObject(key);
        return FloatConverter.toNullableFloat(value);
    }

    public float getAsFloat(String key) {
        return getAsFloatWithDefault(key, 0);
    }

    public float getAsFloatWithDefault(String key, float defaultValue) {
        Object value = getAsObject(key);
        return FloatConverter.toFloatWithDefault(value, defaultValue);
    }

    public Double getAsNullableDouble(String key) {
        Object value = getAsObject(key);
        return DoubleConverter.toNullableDouble(value);
    }

    public double getAsDouble(String key) {
        return getAsDoubleWithDefault(key, 0);
    }

    public double getAsDoubleWithDefault(String key, double defaultValue) {
        Object value = getAsObject(key);
        return DoubleConverter.toDoubleWithDefault(value, defaultValue);
    }

    public ZonedDateTime getAsNullableDateTime(String key) {
        Object value = getAsObject(key);
        return DateTimeConverter.toNullableDateTime(value);
    }

    public ZonedDateTime getAsDateTime(String key) {
        return getAsDateTimeWithDefault(key, null);
    }

    public ZonedDateTime getAsDateTimeWithDefault(String key, ZonedDateTime defaultValue) {
        Object value = getAsObject(key);
        return DateTimeConverter.toDateTimeWithDefault(value, defaultValue);
    }

    public Duration getAsNullableDuration(String key) {
        Object value = getAsObject(key);
        return DurationConverter.toNullableDuration(value);
    }

    public Duration getAsDuration(String key) {
        return getAsDurationWithDefault(key, null);
    }

    public Duration getAsDurationWithDefault(String key, Duration defaultValue) {
        Object value = getAsObject(key);
        return DurationConverter.toDurationWithDefault(value, defaultValue);
    }

    public <T extends Enum<T>> T getAsNullableEnum(Class<T> type, String key) {
        Object value = getAsObject(key);
        return EnumConverter.toNullableEnum(type, value);
    }

    public <T extends Enum<T>> T getAsEnum(Class<T> type, String key) {
        return getAsEnumWithDefault(type, key, null);
    }

    public <T extends Enum<T>> T getAsEnumWithDefault(Class<T> type, String key, T defaultValue) {
        Object value = getAsObject(key);
        return EnumConverter.toEnumWithDefault(type, value, defaultValue);
    }
    
    public <T> T getAsNullableType(Class<T> type, String key) {
        Object value = getAsObject(key);
        return TypeConverter.toNullableType(type, value);
    }

    public <T> T getAsType(Class<T> type, String key) {
        return getAsTypeWithDefault(type, key, null);
    }

    public <T> T getAsTypeWithDefault(Class<T> type, String key, T defaultValue) {
        Object value = getAsObject(key);
        return TypeConverter.toTypeWithDefault(type, value, defaultValue);
    }

    public AnyValue getAsValue(String key) {
        Object value = getAsObject(key);
    	return new AnyValue(value);
    }

    public AnyValueArray getAsNullableArray(String key) {
        Object value = getAsObject(key);
    	return value != null ? AnyValueArray.fromValue(value) : null;
    }

    public AnyValueArray getAsArray(String key) {
        Object value = getAsObject(key);
    	return AnyValueArray.fromValue(value);
    }
    
    public AnyValueArray getAsArrayWithDefault(String key, AnyValueArray defaultValue) {
    	AnyValueArray result = getAsNullableArray(key);
    	return result != null ? result : defaultValue;
    }

    public AnyValueMap getAsNullableMap(String key) {
        Object value = getAsObject(key);
    	return value != null ? AnyValueMap.fromValue(value) : null;
    }

    public AnyValueMap getAsMap(String key) {
        Object value = getAsObject(key);
    	return AnyValueMap.fromValue(value);
    }

    public AnyValueMap getAsMapWithDefault(String key, AnyValueMap defaultValue) {
        AnyValueMap result = getAsNullableMap(key);
    	return result != null ? result: defaultValue;
    }
    
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		// Todo: User encoder
		for (Map.Entry<String, String> entry : this.entrySet()) {
			if (builder.length() > 0)
				builder.append(';');
			
			if (entry.getValue() != null)
				builder.append(entry.getKey()).append('=').append(entry.getValue());
			else
				builder.append(entry.getKey());
		}
		
		return builder.toString();
	}

    public Object clone() {
    	return new StringValueMap(this);
    }

    public static StringValueMap fromTuples(Object... tuples) {
        return StringValueMap.fromTuplesArray(tuples);
    }
    
    public static StringValueMap fromTuplesArray(Object[] tuples) {
    	StringValueMap result = new StringValueMap();
    	if (tuples == null || tuples.length == 0)
    		return result;
    	
        for (int index = 0; index < tuples.length; index += 2) {
            if (index + 1 >= tuples.length) break;

            String name = StringConverter.toString(tuples[index]);
            String value = StringConverter.toNullableString(tuples[index + 1]);

            result.put(name, value);
        }
        
    	return result;
    }

    public static StringValueMap fromString(String line) {
    	StringValueMap result = new StringValueMap();
		if (line == null || line.length() == 0) 
			return result;
		
		// Todo: User tokenizer / decoder
		String[] tokens = line.split(";", -1);
		
		for (String token : tokens) {
			if (token.length() == 0) continue;
			int index = token.indexOf('=');
			String key = index > 0 ? token.substring(0, index).trim() : token.trim();
			String val = index > 0 ? token.substring(index + 1).trim() : null;
			result.put(key, val);
		}
		
		return result;
    }

    public static StringValueMap fromMaps(Map<?,?>... maps)
    {
    	StringValueMap result = new StringValueMap();
    	if (maps != null && maps.length > 0) {
	    	for (Map<?,?> map : maps)
	    		result.append(map);
    	}
    	return result;
    }

}
