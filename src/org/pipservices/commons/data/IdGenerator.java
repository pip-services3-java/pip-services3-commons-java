package org.pipservices.commons.data;

import java.util.*;

public class IdGenerator {
    public static String nextShort() {
        return new Long((long)Math.floor(100000000 + Math.random() * 899999999)).toString();
    }

    public static String nextLong() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }	
}
