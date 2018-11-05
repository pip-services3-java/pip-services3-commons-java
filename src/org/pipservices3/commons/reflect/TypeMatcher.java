package org.pipservices3.commons.reflect;

import java.time.*;
import java.util.*;

import org.pipservices3.commons.convert.TypeCode;
import org.pipservices3.commons.convert.TypeConverter;

/**
 * Helper class matches value types for equality.
 * <p>
 * This class has symmetric implementation across all languages supported
 * by Pip.Services toolkit and used to support dynamic data processing.
 * 
 * @see TypeCode
 */
public class TypeMatcher {

	/**
	 * Matches expected type to a type of a value. The expected type can be
	 * specified by a type, type name or TypeCode.
	 * 
	 * @param expectedType an expected type to match.
	 * @param actualValue  a value to match its type to the expected one.
	 * @return true if types are matching and false if they don't.
	 * 
	 * @see #matchType(Object, Class)
	 * @see #matchValueByName(String, Object) (for matching by types' string names)
	 */
	public static boolean matchValue(Object expectedType, Object actualValue) {
		if (expectedType == null)
			return true;
		if (actualValue == null)
			throw new NullPointerException("Actual value cannot be null");

		return matchType(expectedType, actualValue.getClass());
	}

	/**
	 * Matches expected type to an actual type. The types can be specified as types,
	 * type names or TypeCode.
	 * 
	 * @param expectedType an expected type to match.
	 * @param actualType   an actual type to match.
	 * @return true if types are matching and false if they don't.
	 * 
	 * @see #matchTypeByName(String, Class)
	 */
	public static boolean matchType(Object expectedType, Class<?> actualType) {
		if (expectedType == null)
			return true;
		if (actualType == null)
			throw new NullPointerException("Actual type cannot be null");

		if (expectedType instanceof Class<?>)
			return ((Class<?>) expectedType).isAssignableFrom(actualType);

		if (expectedType instanceof String)
			return matchTypeByName((String) expectedType, actualType);

		if (expectedType instanceof TypeCode)
			return TypeConverter.toTypeCode(actualType).equals(expectedType);

		return false;
	}

	/**
	 * Matches expected type to a type of a value.
	 * 
	 * @param expectedType an expected type name to match.
	 * @param actualValue  a value to match its type to the expected one.
	 * @return true if types are matching and false if they don't.
	 */
	public static boolean matchValueByName(String expectedType, Object actualValue) {
		if (expectedType == null)
			return true;
		if (actualValue == null)
			throw new NullPointerException("Actual value cannot be null");

		return matchTypeByName(expectedType, actualValue.getClass());
	}

	/**
	 * Matches expected type to an actual type.
	 * 
	 * @param expectedType an expected type name to match.
	 * @param actualType   an actual type to match defined by type code.
	 * @return true if types are matching and false if they don't.
	 */
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
			return Integer.class.isAssignableFrom(actualType) || Long.class.isAssignableFrom(actualType);
		} else if (expectedType.equals("long")) {
			return Long.class.isAssignableFrom(actualType);
		} else if (expectedType.equals("float")) {
			return Float.class.isAssignableFrom(actualType) || Double.class.isAssignableFrom(actualType);
		} else if (expectedType.equals("double")) {
			return Double.class.isAssignableFrom(actualType);
		} else if (expectedType.equals("string")) {
			return String.class.isAssignableFrom(actualType);
		} else if (expectedType.equals("bool") || expectedType.equals("boolean")) {
			return Boolean.class.isAssignableFrom(actualType);
		} else if (expectedType.equals("date") || expectedType.equals("datetime")) {
			return Date.class.isAssignableFrom(actualType) || Calendar.class.isAssignableFrom(actualType)
					|| ZonedDateTime.class.isAssignableFrom(actualType);
		} else if (expectedType.equals("timespan") || expectedType.equals("duration")) {
			return Integer.class.isAssignableFrom(actualType) || Long.class.isAssignableFrom(actualType)
					|| Float.class.isAssignableFrom(actualType) || Double.class.isAssignableFrom(actualType);
		} else if (expectedType.equals("enum")) {
			return Enum.class.isAssignableFrom(actualType);
		} else if (expectedType.equals("map") || expectedType.equals("dict") || expectedType.equals("dictionary")) {
			return Map.class.isAssignableFrom(actualType);
		} else if (expectedType.equals("array") || expectedType.equals("list")) {
			return actualType.isArray() || List.class.isAssignableFrom(actualType);
		} else if (expectedType.endsWith("[]")) {
			// Todo: Check subtype
			return actualType.isArray() || List.class.isAssignableFrom(actualType);
		} else
			return false;
	}
}
