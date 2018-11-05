package org.pipservices3.commons.data;

import static org.junit.Assert.*;

import org.junit.*;
import org.pipservices3.commons.data.AnyValue;

public class AnyValueTest {
    @Test
    public void testGetAndSetAnyValue() {
        AnyValue value = new AnyValue();
        assertNull(value.getAsObject());

        value.setAsObject(1);
        assertEquals(1, value.getAsInteger());
        assertTrue(1.0 - value.getAsFloat() < 0.001);
        assertEquals("1", value.getAsString());
        //assertEquals(TimeSpan.FromMilliseconds(1), value.GetAsTimeSpan());
        //assertEquals(LogLevel.Fatal, value.GetAsEnum<LogLevel>());
    }

    @Test
    public void testEqualAnyValue() {
        AnyValue value = new AnyValue(1);

        assertTrue(value.equals(1));
        //assertTrue(value.equals(1.0));S
        assertTrue(value.equalsAs(String.class, "1"));
        //assertTrue(value.equals(TimeSpan.FromMilliseconds(1)));
        //assertTrue(value.equalsAsType<LogLevel>(LogLevel.Fatal));
    }
}
