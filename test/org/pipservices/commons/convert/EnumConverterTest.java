package org.pipservices.commons.convert;

import static org.junit.Assert.*;

import org.junit.*;

public class EnumConverterTest {
	
	@Test
    public void testToEnum() {
        assertEquals(LogLevel.None, EnumConverter.toEnum(LogLevel.class, "ABC"));
        assertEquals(LogLevel.Fatal, EnumConverter.toEnum(LogLevel.class, 1));
        assertEquals(LogLevel.Fatal, EnumConverter.toEnum(LogLevel.class, LogLevel.Fatal));
        assertEquals(LogLevel.Fatal, EnumConverter.toEnum(LogLevel.class, "Fatal"));
    }
	
}
