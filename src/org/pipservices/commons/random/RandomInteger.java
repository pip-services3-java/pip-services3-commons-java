package org.pipservices.commons.random;

import java.util.*;

public class RandomInteger {
    private static final java.util.Random _random = new java.util.Random();

    public static int nextInteger(int max) {
        return _random.nextInt(max);
    }

    public static int nextInteger(int min, int max) {
    	if (max - min <= 0)
    		return min;
    	
        return min + _random.nextInt(max - min);
    }

    public static int updateInteger(int value) {
    	return updateInteger(value, 0);
    }

	public static int updateInteger(int value, int range) {		
        range = range == 0 ? (int)(0.1 * value) : range;
        int min = value - range;
        int max = value + range;
        return nextInteger(min, max);
    }

    public static List<Integer> sequence(int size) {
        return sequence(size, size);
    }

    public static List<Integer> sequence(int min, int max) {
        int count = nextInteger(min, max);

        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < count; i++) 
            result.add(i);
        
        return result;
    }
}
