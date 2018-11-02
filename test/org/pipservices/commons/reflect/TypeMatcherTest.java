package org.pipservices.commons.reflect;

import org.junit.*;

import static org.junit.Assert.*;

import java.time.ZonedDateTime;
import java.util.*;

public class TypeMatcherTest {
	@Test
	public void matchInteger() {
		assertTrue(TypeMatcher.matchValueByName("int", 123));
		assertTrue(TypeMatcher.matchValueByName("Integer", 123));
		assertTrue(TypeMatcher.matchValue(Integer.class, 123));
	}
	
	@Test
	public void matchBoolean() {
		assertTrue(TypeMatcher.matchValueByName("bool", true));
		assertTrue(TypeMatcher.matchValueByName("Boolean", true));
		assertTrue(TypeMatcher.matchValue(Boolean.class, true));
	}

	@Test
	public void matchDouble() {
		assertTrue(TypeMatcher.matchValueByName("double", 123.456));
		assertTrue(TypeMatcher.matchValueByName("Double", 123.456));
		assertTrue(TypeMatcher.matchValue(Double.class, 123.456));
	}

	@Test
	public void matchLong() {
		assertTrue(TypeMatcher.matchValueByName("long", 123L));
		assertTrue(TypeMatcher.matchValue(Long.class, 123L));
	}

	@Test
	public void matchFloat() {
		assertTrue(TypeMatcher.matchValueByName("float", 123.456f));
		assertTrue(TypeMatcher.matchValueByName("Float", 123.456f));
		assertTrue(TypeMatcher.matchValue(Float.class, 123.456f));
	}

	@Test
	public void matchString() {
		assertTrue(TypeMatcher.matchValueByName("string", "ABC"));
		assertTrue(TypeMatcher.matchValue(String.class, "ABC"));
	}

	@Test
	public void matchDateTime() {
		assertTrue(TypeMatcher.matchValueByName("date", new Date()));
		assertTrue(TypeMatcher.matchValueByName("DateTime", ZonedDateTime.now()));
		assertTrue(TypeMatcher.matchValue(Date.class, new Date()));
	}

	@Test
	public void matchDuration() {
		assertTrue(TypeMatcher.matchValueByName("duration", 123));
		assertTrue(TypeMatcher.matchValueByName("TimeSpan", 123));
	}

	@Test
	public void matchMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		assertTrue(TypeMatcher.matchValueByName("map", map));
		assertTrue(TypeMatcher.matchValueByName("dict", map));
		assertTrue(TypeMatcher.matchValueByName("Dictionary", map));
		assertTrue(TypeMatcher.matchValue(Map.class, map));
	}

	@Test
	public void matchArray() {
		List<Object> list = new ArrayList<Object>();
		assertTrue(TypeMatcher.matchValueByName("list", list));
		assertTrue(TypeMatcher.matchValueByName("array", list));
		assertTrue(TypeMatcher.matchValueByName("object[]", list));
		assertTrue(TypeMatcher.matchValue(List.class, list));

		int[] array = new int[0];
		assertTrue(TypeMatcher.matchValueByName("list", array));
		assertTrue(TypeMatcher.matchValueByName("array", array));
		assertTrue(TypeMatcher.matchValueByName("object[]", array));
	}
}
