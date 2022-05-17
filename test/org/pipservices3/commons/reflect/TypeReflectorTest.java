package org.pipservices3.commons.reflect;

import org.junit.*;

import static org.junit.Assert.*;

public class TypeReflectorTest {
    @Test
    public void testGetType() {
        Class<?> type = TypeReflector.getType("org.pipservices3.commons.convert.TypeCode");
        assertNotNull(type);

        type = TypeReflector.getType("org.pipservices3.commons.convert.TypeCode", "pip-services3-commons");
        assertNotNull(type);
    }

    @Test
    public void testCreateInstance() throws Exception {
        Object value = TypeReflector.createInstance("org.pipservices3.commons.reflect.TestClass");
        assertNotNull(value);
    }
}
