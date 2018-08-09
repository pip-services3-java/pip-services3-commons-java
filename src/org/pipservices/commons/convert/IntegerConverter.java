package org.pipservices.commons.convert;

public class IntegerConverter {

	public static Integer toNullableInteger(Object value) {
    	Long result = LongConverter.toNullableLong(value);
    	return result != null ? (int)((long)result) : null;
    }

    public static int toInteger(Object value) {
    	return toIntegerWithDefault(value, 0);
    }
    
    public static int toIntegerWithDefault(Object value, int defaultValue) {
    	Integer result = toNullableInteger(value);
    	return result != null ? (int)result : defaultValue;
    }

}
