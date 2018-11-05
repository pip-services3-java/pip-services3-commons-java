package org.pipservices3.commons.convert;

import static org.junit.Assert.*;

import org.junit.*;

public class DurationConverterTest {

	@Test
    public void testToNullableDuration() {
        assertNull(DurationConverter.toNullableDuration(null));
        assertTrue(DurationConverter.toNullableDuration((int)6000).getSeconds() == 6);
        assertTrue(DurationConverter.toNullableDuration((short)6000).getSeconds() == 6);
        assertTrue(DurationConverter.toNullableDuration(6000.5).getSeconds() == 6);
        assertTrue(DurationConverter.toNullableDuration(-600).getSeconds() == -1);
        assertTrue(DurationConverter.toNullableDuration(0).getSeconds() == 0);
    }
	
	@Test
    public void testToDateTime() {
        assertNull(DurationConverter.toDuration(null));
        assertTrue(DurationConverter.toDuration((int)6000).getSeconds() == 6);
        assertTrue(DurationConverter.toDuration((short)6000).getSeconds() == 6);
        assertTrue(DurationConverter.toDuration(6000.5).getSeconds() == 6);
        assertTrue(DurationConverter.toDuration(-600).getSeconds() == -1);
        assertTrue(DurationConverter.toDuration(0).getSeconds() == 0);
    }

}
