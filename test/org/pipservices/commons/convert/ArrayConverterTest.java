package org.pipservices.commons.convert;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

public class ArrayConverterTest {

	@Test
    public void testToNullableArray() {
        assertNull(ArrayConverter.toNullableArray(null));
        
        assertTrue(ArrayConverter.toNullableArray(2).size() == 1);
        assertTrue(ArrayConverter.toNullableArray(2).getClass().getName() == "java.util.ArrayList");
        
        List<Integer> array = new ArrayList<Integer>();
        array.add(1);
		array.add(2);
		assertTrue(ArrayConverter.toNullableArray(array).size() == 2);
        assertTrue(ArrayConverter.toNullableArray(array).getClass().getName() == "java.util.ArrayList");
        
        String[] stringArray = {"ab", "cd"};
        assertTrue(ArrayConverter.toNullableArray(stringArray).getClass().getName() == "java.util.ArrayList");
        
        List<Object> value = ArrayConverter.listToArray("123,456");
        assertTrue(value.size() == 2);
        assertEquals("123", value.get(0));
        assertEquals("456", value.get(1));
    }

}
