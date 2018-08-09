package org.pipservices.commons.random;

public class RandomFloat {
    private static final java.util.Random _random = new java.util.Random();

    public static float nextFloat(int max) {
        return (float)_random.nextDouble() * max;
    }

    public static float nextFloat(float min, float max) {
        return (float)(min + _random.nextDouble() * (max - min));
    }

    public static float updateFloat(float value) {
    	return updateFloat(value, 0);
    }

    public static float updateFloat(float value, float range) {
        range = range == 0 ? (float)(0.1 * value) : range;
        float min = value - range;
        float max = value + range;
        return nextFloat(min, max);
    }
    
}
