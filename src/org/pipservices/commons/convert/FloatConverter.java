package org.pipservices.commons.convert;

public class FloatConverter {

	public static Float toNullableFloat(Object value) {
    	Double result = DoubleConverter.toNullableDouble(value);
    	return result != null ? (float)((double)result) : null;
    }

    public static float toFloat(Object value) {
    	return toFloatWithDefault(value, 0);
    }
    
    public static float toFloatWithDefault(Object value, float defaultValue) {
    	Float result = toNullableFloat(value);
    	return result != null ? (float)result : defaultValue;
    }

}
