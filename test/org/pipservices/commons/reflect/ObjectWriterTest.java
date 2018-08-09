package org.pipservices.commons.reflect;

import org.junit.*;
import org.pipservices.commons.data.*;

import static org.junit.Assert.*;

import java.time.ZonedDateTime;

public class ObjectWriterTest {

	@Test
	public void testSetObjectProperty() {
		TestClass obj = new TestClass();

		ObjectWriter.setProperty(obj, "privateField", "XYZ");
		
		ObjectWriter.setProperty(obj, "publicField", "AAAA");
		assertEquals("AAAA", obj.publicField);
		
		ZonedDateTime now = ZonedDateTime.now();
		ObjectWriter.setProperty(obj, "PublicProp", now);
		assertEquals(now, obj.getPublicProp());

		ObjectWriter.setProperty(obj, "PublicProp", "BBBB");
		assertEquals(now, obj.getPublicProp());
	}
	
	@Test
	public void testSetMapProperty() {
		AnyValueMap map = AnyValueMap.fromTuples(
			"key1", 123,
			"key2", "ABC"
		);

		ObjectWriter.setProperty(map, "key3", "AAAA");
		assertEquals("AAAA", map.get("key3"));
		
		ObjectWriter.setProperty(map, "Key1", 5555);
		assertEquals(5555, map.get("Key1"));
		
		ObjectWriter.setProperty(map, "Key2", "BBBB");
		assertEquals("BBBB", map.get("Key2"));
	}

	@Test
	public void testSetArrayProperty() {
		AnyValueArray list = AnyValueArray.fromValues(123, "ABC");

		ObjectWriter.setProperty(list, "3", "AAAA");
		assertEquals(4, list.size());
		assertEquals("AAAA", list.get(3));
		
		ObjectWriter.setProperty(list, "0", 1111);
		assertEquals(1111, list.get(0));
		
		ObjectWriter.setProperty(list, "1", "BBBB");
		assertEquals("BBBB", list.get(1));

		Object[] array = new Object[] { 123, "ABC" };
		
		ObjectWriter.setProperty(array, "3", "AAAA");
		assertEquals(2, array.length);
		
		ObjectWriter.setProperty(array, "0", 1111);
		assertEquals(1111, array[0]);
		
		ObjectWriter.setProperty(array, "1", "BBBB");
		assertEquals("BBBB", array[1]);
	}
	
}
