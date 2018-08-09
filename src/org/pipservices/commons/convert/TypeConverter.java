package org.pipservices.commons.convert;

import java.time.*;
import java.util.*;

public class TypeConverter {
	private static Class<?> _booleanType = Boolean.class;
	private static Class<?> _integerType = Integer.class;
	private static Class<?> _longType = Long.class;
	private static Class<?> _stringType = String.class;
	private static Class<?> _floatType = Float.class;
	private static Class<?> _doubleType = Double.class;
	private static Class<?> _dateTimeType = ZonedDateTime.class;
	private static Class<?> _durationType = Duration.class;
	private static Class<?> _enumType = Enum.class;
	private static Class<?> _listType = List.class;
	private static Class<?> _mapType = Map.class;
		
	public static TypeCode toTypeCode(Class<?> type) {
		if (type == null)
			return TypeCode.Unknown;
		else if (type.isArray())
			return TypeCode.Array;
		else if (type.isEnum()) 
			return TypeCode.Enum;		
		else if (type.isPrimitive()) {
			if (_booleanType.isAssignableFrom(type))
				return TypeCode.Boolean;
			if (_doubleType.isAssignableFrom(type))
				return TypeCode.Double;
			if (_floatType.isAssignableFrom(type))
				return TypeCode.Float;
			if (_longType.isAssignableFrom(type))
				return TypeCode.Long;
			if (_integerType.isAssignableFrom(type))
				return TypeCode.Integer;
		} 
		else {
			if (_booleanType.isAssignableFrom(type))
				return TypeCode.Boolean;
			if (_doubleType.isAssignableFrom(type))
				return TypeCode.Double;
			if (_floatType.isAssignableFrom(type))
				return TypeCode.Float;
			if (_longType.isAssignableFrom(type))
				return TypeCode.Long;
			if (_integerType.isAssignableFrom(type))
				return TypeCode.Integer;
			if (_stringType.isAssignableFrom(type))
				return TypeCode.String;
			if (_dateTimeType.isAssignableFrom(type))
				return TypeCode.DateTime;
			if (_durationType.isAssignableFrom(type))
				return TypeCode.Duration;
			if (_mapType.isAssignableFrom(type))
				return TypeCode.Map;
			if (_listType.isAssignableFrom(type))
				return TypeCode.Array;
			if (_enumType.isAssignableFrom(type))
				return TypeCode.Enum;
		}
			
		return TypeCode.Object;
	}
	
	public static TypeCode toTypeCode(Object value) {
		if (value == null)
			return TypeCode.Unknown;
		
		return toTypeCode(value.getClass());
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T toNullableType(Class<T> type, Object value) {
    	TypeCode resultType = toTypeCode(type);

    	if (value == null) return null;
    	if (type.isInstance(value)) return (T)value;
    	
    	// Convert to known types
    	if (resultType == TypeCode.String)
			return type.cast(StringConverter.toNullableString(value));
    	else if (resultType == TypeCode.Integer)
			return type.cast(IntegerConverter.toNullableInteger(value));
    	else if (resultType == TypeCode.Long)
			return type.cast(LongConverter.toNullableLong(value));
    	else if (resultType == TypeCode.Float)
			return type.cast(FloatConverter.toNullableFloat(value));
    	else if (resultType == TypeCode.Double)
			return type.cast(DoubleConverter.toNullableDouble(value));
    	else if (resultType == TypeCode.Duration)
			return type.cast(DurationConverter.toNullableDuration(value));
    	else if (resultType == TypeCode.DateTime)
			return type.cast(DateTimeConverter.toNullableDateTime(value));
    	else if (resultType == TypeCode.Array)
			return type.cast(ArrayConverter.toNullableArray(value));
    	else if (resultType == TypeCode.Map)
			return type.cast(MapConverter.toNullableMap(value));

    	// Convert to unknown type
    	try {
    		return type.cast(value);
    	} catch (Throwable t) {
    		return null;
    	}
    }

    public static <T> T toType(Class<T> type, Object value) {
    	// Convert to the specified type
    	T result = toNullableType(type, value);
    	if (result != null) return result;

    	// Define and return default value based on type
    	TypeCode resultType = toTypeCode(type);
    	if (resultType == TypeCode.String)
			return null;
    	else if (resultType == TypeCode.Integer)
			return type.cast((int)0);
    	else if (resultType == TypeCode.Long)
			return type.cast((long)0);
    	else if (resultType == TypeCode.Float)
			return type.cast((float)0);
    	else if (resultType == TypeCode.Double)
			return type.cast((double)0);    	
    	else 
    		return null;
    }
    
    public static <T> T toTypeWithDefault(Class<T> type, Object value, T defaultValue) {
    	T result = toNullableType(type, value);
    	return result != null ? result : defaultValue;
    }
	
}
