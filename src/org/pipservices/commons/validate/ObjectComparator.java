package org.pipservices.commons.validate;

import org.pipservices.commons.convert.*;

public class ObjectComparator {
    public static boolean compare(Object value1, String operation, Object value2) {
    	if (operation == null) return false;
    	
        operation = operation.toUpperCase();

        if (operation.equals("=") || operation.equals("==") || operation.equals("EQ"))
            return areEqual(value1, value2);
        if (operation.equals("!=") || operation.equals("<>") || operation.equals("NE"))
            return areNotEqual(value1, value2);
        if (operation.equals("<") || operation.equals("LT"))
            return less(value1, value2);
        if (operation.equals("<=") || operation.equals("LE"))
            return areEqual(value1, value2) || less(value1, value2);
        if (operation.equals(">") || operation.equals("GT"))
            return more(value1, value2);
        if (operation.equals(">=") || operation.equals("GE"))
            return areEqual(value1, value2) || more(value1, value2);
        if (operation.equals("LIKE"))
            return match(value1, value2);

        return true;
    }

    public static boolean areEqual(Object value1, Object value2) {
    	if (value1 == null && value2 == null)
            return true;
        if (value1 == null || value2 == null)
            return false;
        return value1.equals(value2);
    }

    public static boolean areNotEqual(Object value1, Object value2) {
        return !areEqual(value1, value2);
    }

    public static boolean less(Object value1, Object value2) {
    	Double number1 = DoubleConverter.toNullableDouble(value1);
        Double number2 = DoubleConverter.toNullableDouble(value2);

        if (number1 == null || number2 == null)
            return false;

        return number1.doubleValue() < number2.doubleValue();
    }

    public static boolean more(Object value1, Object value2) {
        Double number1 = DoubleConverter.toNullableDouble(value1);
        Double number2 = DoubleConverter.toNullableDouble(value2);

        if (number1 == null || number2 == null)
            return false;

        return number1.doubleValue() > number2.doubleValue();
    }

    public static boolean match(Object value1, Object value2) {
        if (value1 == null && value2 == null)
            return true;
        if (value1 == null || value2 == null)
            return false;

        String string1 = value1.toString();
        String string2 = value2.toString();
        return string1.matches(string2);
    }
}
