package org.pipservices.commons.random;

import java.util.*;

public class RandomLong {

	private static final java.util.Random _random = new java.util.Random();
	
	 public static long nextLong(long maxValue)
     {
         return _random.nextInt((int)maxValue);
     }

     public static long nextLong(long minValue, long maxValue)
     {
         if (maxValue - minValue <= 0)
             return minValue;

         return minValue + _random.nextInt((int)(maxValue - minValue));
     }

     public static long updateLong(long value)
     {
         return updateLong(value, 0);
     }

     public static long updateLong(long value, long range)
     {
         range = range == 0 ? (long)(0.1 * value) : range;
         long minValue = value - range;
         long maxValue = value + range;
         return nextLong(minValue, maxValue);
     }

     public static List<Long> sequence(long size)
     {
         return sequence(size, size);
     }

     public static List<Long> sequence(long min, long max)
     {
         long count = nextLong(min, max);

         List<Long> result = new ArrayList<Long>();
         for (long i = 0; i < count; i++)
             result.add(i);

         return result;
     }
}
