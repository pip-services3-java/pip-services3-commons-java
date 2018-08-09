package org.pipservices.commons.data;

import java.time.*;
import java.util.*;

import org.pipservices.commons.convert.*;

public class AnyValueArray extends ArrayList<Object> implements Cloneable {
	private static final long serialVersionUID = 4856478344826232231L;

	public AnyValueArray() { }

    public AnyValueArray(Object[] values) {
    	append(values);
    }

    public AnyValueArray(Iterable<?> values) {
    	append(values);
    }

    public Object getAsObject() {
    	List<Object> result = new ArrayList<Object>();
    	for (Object element : this)
    		result.add(element);
    	return result;
    }
    
    public void setAsObject(Object value) {
    	clear();
    	List<Object> elements = ArrayConverter.toArray(value);
    	append(elements);
    }
    
    public void append(Iterable<?> elements) {
    	if (elements != null) {
            for (Object item : elements)            	
            	add(item);
    	}
    }

    public void append(Object[] elements) {
    	if (elements != null) {
            for (Object item : elements)            	
            	add(item);
    	}
    }

    public Object getAsObject(int index) {
    	return get(index);
    }

    public void setAsObject(int index, Object value) {    	
        set(index, value);
    }

//    @Override
//    public void add(Object value) {
//    	add(new AnyValue(value));
//    }
    
    public String getAsNullableString(int index) {
        Object value = getAsObject(index);
        return StringConverter.toNullableString(value);
    }

    public String getAsString(int index) {
        return getAsStringWithDefault(index, null);
    }

    public String getAsStringWithDefault(int index, String defaultValue) {
        Object value = getAsObject(index);
        return StringConverter.toStringWithDefault(value, defaultValue);
    }

    public Boolean getAsNullableBoolean(int index) {
        Object value = getAsObject(index);
        return BooleanConverter.toNullableBoolean(value);
    }

    public Boolean getAsBoolean(int index) {
        return getAsBooleanWithDefault(index, false);
    }

    public boolean getAsBooleanWithDefault(int index, boolean defaultValue) {
        Object value = getAsObject(index);
        return BooleanConverter.toBooleanWithDefault(value, defaultValue);
    }

    public Integer getAsNullableInteger(int index) {
        Object value = getAsObject(index);
        return IntegerConverter.toNullableInteger(value);
    }

    public int getAsInteger(int index) {
        return getAsIntegerWithDefault(index, 0);
    }

    public int getAsIntegerWithDefault(int index, int defaultValue) {
        Object value = getAsObject(index);
        return IntegerConverter.toIntegerWithDefault(value, defaultValue);
    }

    public Long getAsNullableLong(int index) {
        Object value = getAsObject(index);
        return LongConverter.toNullableLong(value);
    }

    public long getAsLong(int index) {
        return getAsLongWithDefault(index, 0);
    }

    public long getAsLongWithDefault(int index, long defaultValue) {
        Object value = getAsObject(index);
        return LongConverter.toLongWithDefault(value, defaultValue);
    }

    public Float getAsNullableFloat(int index) {
        Object value = getAsObject(index);
        return FloatConverter.toNullableFloat(value);
    }

    public float getAsFloat(int index) {
        return getAsFloatWithDefault(index, 0);
    }

    public float getAsFloatWithDefault(int index, float defaultValue) {
        Object value = getAsObject(index);
        return FloatConverter.toFloatWithDefault(value, defaultValue);
    }

    public Double getAsNullableDouble(int index) {
        Object value = getAsObject(index);
        return DoubleConverter.toNullableDouble(value);
    }

    public double getAsDouble(int index) {
        return getAsDoubleWithDefault(index, 0);
    }

    public double getAsDoubleWithDefault(int index, double defaultValue) {
        Object value = getAsObject(index);
        return DoubleConverter.toDoubleWithDefault(value, defaultValue);
    }

    public ZonedDateTime getAsNullableDateTime(int index) {
        Object value = getAsObject(index);
        return DateTimeConverter.toNullableDateTime(value);
    }

    public ZonedDateTime getAsDateTime(int index) {
        return getAsDateTimeWithDefault(index, null);
    }

    public ZonedDateTime getAsDateTimeWithDefault(int index, ZonedDateTime defaultValue) {
        Object value = getAsObject(index);
        return DateTimeConverter.toDateTimeWithDefault(value, defaultValue);
    }

    public Duration getAsNullableDuration(int index) {
        Object value = getAsObject(index);
        return DurationConverter.toNullableDuration(value);
    }

    public Duration getAsDuration(int index) {
        return getAsDurationWithDefault(index, null);
    }

    public Duration getAsDurationWithDefault(int index, Duration defaultValue) {
        Object value = getAsObject(index);
        return DurationConverter.toDurationWithDefault(value, defaultValue);
    }

    public <T extends Enum<T>> T getAsNullableEnum(Class<T> type, int index) {
        Object value = getAsObject(index);
        return EnumConverter.toNullableEnum(type, value);
    }

    public <T extends Enum<T>> T getAsEnum(Class<T> type, int index) {
        return getAsEnumWithDefault(type, index, null);
    }

    public <T extends Enum<T>> T getAsEnumWithDefault(Class<T> type, int index, T defaultValue) {
        Object value = getAsObject(index);
        return EnumConverter.toEnumWithDefault(type, value, defaultValue);
    }
    
    public <T> T getAsNullableType(Class<T> type, int index) {
        Object value = getAsObject(index);
        return TypeConverter.toNullableType(type, value);
    }

    public <T> T getAsType(Class<T> type, int index) {
        return getAsTypeWithDefault(type, index, null);
    }

    public <T> T getAsTypeWithDefault(Class<T> type, int index, T defaultValue) {
        Object value = getAsObject(index);
        return TypeConverter.toTypeWithDefault(type, value, defaultValue);
    }

    public AnyValue getAsValue(int index) {
        Object value = getAsObject(index);
    	return new AnyValue(value);
    }

    public AnyValueArray getAsNullableArray(int index) {
        Object value = getAsObject(index);
    	return value != null ? AnyValueArray.fromValue(value) : null;
    }

    public AnyValueArray getAsArray(int index) {
        Object value = getAsObject(index);
    	return AnyValueArray.fromValue(value);
    }
    
    public AnyValueArray getAsArrayWithDefault(int index, AnyValueArray defaultValue) {
    	AnyValueArray result = getAsNullableArray(index);
    	return result != null ? result : defaultValue;
    }

    public AnyValueMap getAsNullableMap(int index) {
        Object value = getAsObject(index);
    	return value != null ? AnyValueMap.fromValue(value) : null;
    }

    public AnyValueMap getAsMap(int index) {
        Object value = getAsObject(index);
    	return AnyValueMap.fromValue(value);
    }

    public AnyValueMap getAsMapWithDefault(int index, AnyValueMap defaultValue) {
        AnyValueMap result = getAsNullableMap(index);
    	return result != null ? AnyValueMap.fromValue(result): defaultValue;
    }

    public boolean contains(Object value) {
        for (Object element : this) {
            if (value == null && element == null)
            	return true;
            if (value == null || element == null)
            	continue;
            if (value.equals(element))
                return true;
        }

        return false;
    }

    public <T> boolean containsAsType(Class<T> type, Object value) {
        T typedValue = TypeConverter.toType(type, value);

        for (Object thisValue : this) {
            T thisTypedValue = TypeConverter.toNullableType(type, thisValue);

            if (typedValue == null && thisTypedValue == null)
                return true;
            if (typedValue == null || thisTypedValue == null)
            	continue;
            if (typedValue.equals(thisTypedValue))
            	return true;
        }

        return false;
    }

    public Object clone() {
    	return new AnyValueArray(this);
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < size(); index++) {
            if (index > 0)
                builder.append(',');
            builder.append(getAsStringWithDefault(index, ""));
        }
        return builder.toString();
    }
    
    public static AnyValueArray fromValues(Object... values) {
    	return new AnyValueArray(values);
    }
    
    public static AnyValueArray fromValue(Object value) {
    	List<Object> values = ArrayConverter.toNullableArray(value);
    	return new AnyValueArray(values);
    }
    
    public static AnyValueArray fromString(String values, String separator, boolean removeDuplicates) {
    	AnyValueArray result = new AnyValueArray();
        
    	if (values == null || values.length() == 0)
        	return result;
        
        String[] items = values.split(separator, -1);
        for (String item : items) {
        	if ((item != null && item.length() > 0) || removeDuplicates == false)
        		result.add(item != null ? new AnyValue(item) : null);
        }
        
        return result;
    }

    public static AnyValueArray fromString(String values, String separator) {
    	return AnyValueArray.fromString(values, separator, false);    
	}
}
