package org.pipservices.commons.random;

import java.util.*;

public class RandomArray {
    public static <T> T pick(T[] values) {
        if (values == null || values.length == 0)
            return null;

        return values[RandomInteger.nextInteger(values.length)];
    }

    public static <T> T pick(List<T> values) {
        if (values == null || values.size() == 0)
            return null;
        	
        int index = RandomInteger.nextInteger(values.size());
        return values.get(index);
    }
}
