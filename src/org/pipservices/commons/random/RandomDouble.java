package org.pipservices.commons.random;

public class RandomDouble {
    private static final java.util.Random _random = new java.util.Random();

	public static double nextDouble(double max) {
        return _random.nextDouble() * max;
    }

    public static double nextDouble(double min, double max) {
        return min + _random.nextDouble() * (max - min);
    }

    public static double updateDouble(double value) {
    	return updateDouble(value, 0);
    }

	public static double updateDouble(double value, double range) {
        range = range == 0 ? 0.1 * value : range;
        double min = value - range;
        double max = value + range;
        return nextDouble(min, max);
    }
}
