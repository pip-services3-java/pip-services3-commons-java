package org.pipservices.commons.random;

import static org.junit.Assert.*;

import org.junit.Test;
import org.pipservices.commons.convert.*;

public class RandomDoubleTest {
    @Test
    public void testNextDouble() {
    	double value = RandomDouble.nextDouble(5);
        assertTrue(value < 5);
        assertTrue(value == DoubleConverter.toDouble(value));
        
    	value = RandomDouble.nextDouble(2,5);
    	assertTrue(value < 5 && value > 2);
        assertTrue(value == DoubleConverter.toDouble(value));
    }
    
    @Test
    public void testUpdateDouble() {
    	double value = RandomDouble.updateDouble(0, 5);
    	
        assertTrue(value <= 5 && value >= -5);
        assertTrue(value == DoubleConverter.toDouble(value));       	  
    }
}