package org.pipservices.commons.data;

import java.io.*;
import java.time.*;
import org.pipservices.commons.convert.*;

import com.fasterxml.jackson.annotation.*;

public class AnyValue implements Serializable, Cloneable {
	private static final long serialVersionUID = 8543060319681670938L;

	private Object _value;

    public AnyValue() {
        _value = null;
    }

    public AnyValue(Object value) {
    	if (value instanceof AnyValue)
    		_value = ((AnyValue)value)._value;
    	else
			_value = value;
    }

    public AnyValue(AnyValue value) {
        _value = value.getAsObject();
    }

    @JsonIgnore
    public TypeCode getTypeCode() {
    	return TypeConverter.toTypeCode(_value);
    }
    
    
    @JsonProperty("value")
    public Object getAsObject() {
        return _value;
    }

    public void setAsObject(Object value) {
    	if(value instanceof AnyValue)
    		_value = ((AnyValue)value)._value;
    	else
    		_value = value;
    }

    @JsonIgnore
    public String getAsNullableString() {
        return StringConverter.toNullableString(_value);
    }

    @JsonIgnore
    public String getAsString() {
        return getAsStringWithDefault(null);
    }

    @JsonIgnore
    public String getAsStringWithDefault(String defaultValue) {
        return StringConverter.toStringWithDefault(_value, defaultValue);
    }

    @JsonIgnore
    public Boolean getAsNullableBoolean() {
        return BooleanConverter.toNullableBoolean(_value);
    }

    @JsonIgnore
    public Boolean getAsBoolean() {
        return getAsBooleanWithDefault(false);
    }

    @JsonIgnore
    public boolean getAsBooleanWithDefault(boolean defaultValue) {
        return BooleanConverter.toBooleanWithDefault(_value, defaultValue);
    }

    @JsonIgnore
    public Integer getAsNullableInteger() {
        return IntegerConverter.toNullableInteger(_value);
    }

    @JsonIgnore
    public int getAsInteger() {
        return getAsIntegerWithDefault(0);
    }

    @JsonIgnore
    public int getAsIntegerWithDefault(int defaultValue) {
        return IntegerConverter.toIntegerWithDefault(_value, defaultValue);
    }

    @JsonIgnore
    public Long getAsNullableLong() {
        return LongConverter.toNullableLong(_value);
    }

    @JsonIgnore
    public long getAsLong() {
        return getAsLongWithDefault(0);
    }

    @JsonIgnore
    public long getAsLongWithDefault(long defaultValue) {
        return LongConverter.toLongWithDefault(_value, defaultValue);
    }

    @JsonIgnore
    public Float getAsNullableFloat() {
        return FloatConverter.toNullableFloat(_value);
    }

    @JsonIgnore
    public float getAsFloat() {
        return getAsFloatWithDefault(0);
    }

    @JsonIgnore
    public float getAsFloatWithDefault(float defaultValue) {
        return FloatConverter.toFloatWithDefault(_value, defaultValue);
    }

    @JsonIgnore
    public Double getAsNullableDouble() {
        return DoubleConverter.toNullableDouble(_value);
    }

    @JsonIgnore
    public double getAsDouble() {
        return getAsDoubleWithDefault(0);
    }

    @JsonIgnore
    public double getAsDoubleWithDefault(double defaultValue) {
        return DoubleConverter.toDoubleWithDefault(_value, defaultValue);
    }

    @JsonIgnore
    public ZonedDateTime getAsNullableDateTime() {
        return DateTimeConverter.toNullableDateTime(_value);
    }

    @JsonIgnore
    public ZonedDateTime getAsDateTime() {
        return getAsDateTimeWithDefault(null);
    }

    @JsonIgnore
    public ZonedDateTime getAsDateTimeWithDefault(ZonedDateTime defaultValue) {
        return DateTimeConverter.toDateTimeWithDefault(_value, defaultValue);
    }

    @JsonIgnore
    public Duration getAsNullableDuration() {
        return DurationConverter.toNullableDuration(_value);
    }

    @JsonIgnore
    public Duration getAsDuration() {
        return getAsDurationWithDefault(null);
    }

    @JsonIgnore
    public Duration getAsDurationWithDefault(Duration defaultValue) {
        return DurationConverter.toDurationWithDefault(_value, defaultValue);
    }

    @JsonIgnore
    public <T extends Enum<T>> T getAsNullableEnum(Class<T> type) {
        return EnumConverter.toNullableEnum(type, _value);
    }

    @JsonIgnore
    public <T extends Enum<T>> T getAsEnum(Class<T> type) {
        return getAsEnumWithDefault(type, null);
    }

    @JsonIgnore
    public <T extends Enum<T>> T getAsEnumWithDefault(Class<T> type, T defaultValue) {
        return EnumConverter.toEnumWithDefault(type, _value, defaultValue);
    }
    
    @JsonIgnore
    public <T> T getAsNullableType(Class<T> type) {
        return TypeConverter.toNullableType(type, _value);
    }

    @JsonIgnore
    public <T> T getAsType(Class<T> type) {
        return getAsTypeWithDefault(type, null);
    }

    @JsonIgnore
    public <T> T getAsTypeWithDefault(Class<T> type, T defaultValue) {
        return TypeConverter.toTypeWithDefault(type, _value, defaultValue);
    }

    @JsonIgnore
    public AnyValueArray getAsArray() {
    	return AnyValueArray.fromValue(_value);
    }

    @JsonIgnore
    public AnyValueMap getAsMap() {
    	return AnyValueMap.fromValue(_value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null && _value == null) return true;
        if (obj == null || _value == null) return false;

        if (obj instanceof AnyValue)
        	obj = ((AnyValue)obj)._value;
        
        if (_value == null && obj == null) return true;
        if (_value == null || obj == null) return false;        
        return _value.equals(obj);
    }

    public <T> boolean equalsAs(Class<T> type, Object obj) {
        if (obj == null && _value == null) return true;
        if (obj == null || _value == null) return false;

        if (obj instanceof AnyValue)
        	obj = ((AnyValue)obj)._value;

        T typedThisValue = TypeConverter.toType(type, _value);
        T typedValue = TypeConverter.toType(type, obj);
        
        if (typedThisValue == null && typedValue == null) return true;
        if (typedThisValue == null || typedValue == null) return false;        
        return typedThisValue.equals(typedValue);
    }

    public Object clone() {
    	return new AnyValue(_value);
    }
    
    @Override
    public String toString() {
        return StringConverter.toString(_value);
    }

    @Override
    public int hashCode() {
        return _value != null ? _value.hashCode(): 0;
    }
}
