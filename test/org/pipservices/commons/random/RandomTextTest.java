package org.pipservices.commons.random;

import static org.junit.Assert.*;

import org.junit.Test;

public class RandomTextTest {
    
    @Test
    public void testPhrase() {   
    	assertTrue(RandomText.phrase(-1, -2) == "");  
    	assertTrue(RandomText.phrase(-1, 0) == "");  
    	assertTrue(RandomText.phrase(-2, -1) == ""); 
    	
    	String text = RandomText.phrase(4, null);
    	assertTrue(text.length() >= 4 && text.length() <= 10 ); 
    	String text1 = RandomText.phrase(4, 10);
    	assertTrue(text1.length() >= 4); 
    }
    
    @Test
    public void testFullName() { 
    	String text = RandomText.fullName();
    	assertTrue(text.indexOf(" ") != -1); 
    }
    
    @Test
    public void testPhone() { 
    	String text = RandomText.phone();
    	assertTrue(text.indexOf("(") != -1); 
    	assertTrue(text.indexOf(")") != -1);
    	assertTrue(text.indexOf("-") != -1); 
    }
    
    @Test
    public void testEmail() { 
    	String text = RandomText.email();
    	assertTrue(text.indexOf("@") != -1); 
    	assertTrue(text.indexOf(".com") != -1);
    }
     
}