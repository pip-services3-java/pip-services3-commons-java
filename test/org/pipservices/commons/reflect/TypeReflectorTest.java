package org.pipservices.commons.reflect;

import org.junit.*;

import static org.junit.Assert.*;

public class TypeReflectorTest {
	@Test
	public void testGetType() {
		Class<?> type = TypeReflector.getType("org.pipservices.commons.convert.TypeCode");
		assertNotNull(type);

		type = TypeReflector.getType("org.pipservices.commons.convert.TypeCode", "pip-services-commons");
		assertNotNull(type);
	}
	
	@Test
	public void testCreateInstance() throws Exception {
		Object value = TypeReflector.createInstance("org.pipservices.commons.reflect.TestClass");
		assertNotNull(value);
	}
}
