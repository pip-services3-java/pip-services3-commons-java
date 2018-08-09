package org.pipservices.commons.random;

public class RandomString {
    private static final String _digits = "01234956789";
    private static final String _symbols = "_,.:-/.[].{},#-!,$=%.+^.&*-() ";
    private static final String _alphaLower = "abcdefghijklmnopqrstuvwxyz";
    private static final String _alphaUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String _alpha = _alphaUpper + _alphaLower;
    private static final String _chars = _alpha + _digits + _symbols;

    public static char pickChar(String values) {
        if (values == null || values.length() == 0)
            return '\0';

        int index = RandomInteger.nextInteger(values.length());
        return values.charAt(index);
    }

    public static String pick(String[] values) {
    	if (values == null || values.length == 0)
            return "";

        int index = RandomInteger.nextInteger(values.length);
        return values[index];
    }

    public static String distort(String value) {
        value = value.toLowerCase();

        if (RandomBoolean.chance(1, 5))
            value = value.substring(0, 1).toUpperCase() + value.substring(1);

        if (RandomBoolean.chance(1, 3))
            value = value + pickChar(_symbols);

        return value;
    }

    public static char nextAlphaChar() {
    	int index = RandomInteger.nextInteger(_alpha.length());
        return _alpha.charAt(index);
    }

    public static String nextString(int min, int max) {
        StringBuilder result = new StringBuilder();
        
        int length = RandomInteger.nextInteger(min, max);
        for(int i = 0; i < length; i++) {
        	int index = RandomInteger.nextInteger(_chars.length());
            result.append(_chars.charAt(index));
        }

        return result.toString();
    }
}
