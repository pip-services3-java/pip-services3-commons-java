package org.pipservices.commons.random;

public class RandomBoolean {
    private static final java.util.Random _random = new java.util.Random();
	
    public static boolean chance(float chances, float maxChances) {
    	chances = chances >= 0 ? chances : 0;
    	maxChances = maxChances >= 0 ? maxChances : 0;
    	if (chances == 0 && maxChances == 0)
        	return false;
    	
        maxChances = Math.max(maxChances, chances);
        double start = (maxChances - chances) / 2;
        double end = start + chances;
        double hit = _random.nextDouble() * maxChances;
        return hit >= start && hit <= end;
    }

    public static boolean nextBoolean() {
        return _random.nextInt(100) < 50;
    }

}
