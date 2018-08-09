package org.pipservices.commons.random;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class RandomIntegerTest {
    @Test
    public void testNextInteger() {
    	float value = RandomInteger.nextInteger(5);
        assertTrue(value <= 5);
        
    	value = RandomInteger.nextInteger(2,5);
    	assertTrue(value <= 5 && value >= 2);
    }
    
    @Test
    public void testUpdateInteger() {
    	float value = RandomInteger.updateInteger(0, 5);
        assertTrue(value <= 5 && value >= -5);
        
        value = RandomInteger.updateInteger(5, 0);  
        
        value = RandomInteger.updateInteger(0);
        assertTrue(value == 0);
    }
    
    @Test
    public void testSequence() {
        List<Integer> list = new ArrayList<Integer>();
    	list = RandomInteger.sequence(1,5);
        assertTrue(list.size() <= 5 && list.size() >= 1);
        
        list = RandomInteger.sequence(-1, 0);
        assertTrue(list.size() == 0);
        
        list = RandomInteger.sequence(-1, -4);
        assertTrue(list.size() == 0);
        
        list = RandomInteger.sequence(4, 4);
        assertTrue(list.size() == 4);
        
        list = RandomInteger.sequence(5); 
        assertTrue(list.size() == 5);
    }    
}