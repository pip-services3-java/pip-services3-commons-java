package org.pipservices.commons.reflect;

import java.lang.reflect.*;
import java.util.*;

public class PropertyReflector {
	
	private static boolean matchField(Field field, String name) {
		int mod = field.getModifiers();
    	return field.getName().equalsIgnoreCase(name)
			&& Modifier.isPublic(mod) && !Modifier.isStatic(mod);
	}

	private static boolean matchPropertyGetter(Method method, String name) {
    	// Skip "special" fields
    	if (method.getName().equals("getClass"))
    		return false;

    	int mod = method.getModifiers();
    	return method.getName().equalsIgnoreCase(name)
			&& Modifier.isPublic(mod) && !Modifier.isStatic(mod)
			&& !Modifier.isAbstract(mod)
			&& method.getParameterCount() == 0
			&& method.getReturnType() != null;
	}

	private static boolean matchPropertySetter(Method method, String name) {
    	int mod = method.getModifiers();
    	return method.getName().equalsIgnoreCase(name)
			&& Modifier.isPublic(mod) && !Modifier.isStatic(mod)
			&& !Modifier.isAbstract(mod)
			&& method.getParameterCount() == 1
			/*&& method.getReturnType() == null*/;
	}

	public static boolean hasProperty(Object obj, String name) {
		if (obj == null)
			throw new NullPointerException("Object cannot be null");
		if (name == null)
			throw new NullPointerException("Property name cannot be null");
		
        Class<?> objClass = obj.getClass();

        // Search in fields
        for (Field field : objClass.getFields()) {
        	if (matchField(field, name))
        		return true;
        }
		
        // Search in properties
        name = "get" + name;
        for (Method method : objClass.getMethods()) {
        	if (matchPropertyGetter(method, name))
        		return true;
        }

        return false;
	}

	public static Object getProperty(Object obj, String name) {
		if (obj == null)
			throw new NullPointerException("Object cannot be null");
		if (name == null)
			throw new NullPointerException("Property name cannot be null");
		
        Class<?> objClass = obj.getClass();

        // Search in fields
        for (Field field : objClass.getFields()) {
        	try {
	        	if (matchField(field, name))
	        		return field.get(obj);
        	} catch (Throwable t) {
        		// Ignore exceptions
        	}
        }
		
        // Search in properties
        name = "get" + name;
        for (Method method : objClass.getMethods()) {
        	try {
	        	if (matchPropertyGetter(method, name))
	        		return method.invoke(obj);
        	} catch (Throwable t) {
        		// Ignore exceptions
        	}
        }

        return null;
	}
	
	public static List<String> getPropertyNames(Object obj) {
		List<String> properties = new ArrayList<String>();
		
        Class<?> objClass = obj.getClass();

        // Get all properties
        for (Field field : objClass.getFields()) {
        	if (matchField(field, field.getName()))
        		properties.add(field.getName());
        }
		
        // Get all properties
        for (Method method : objClass.getMethods()) {
        	if (method.getName().startsWith("get") && matchPropertyGetter(method, method.getName())) {
        		String name = method.getName().substring(3);
        		name = name.substring(0, 1).toLowerCase() + name.substring(1);
        		properties.add(name);
        	}        		
        }
        
		return properties;
	}

	public static Map<String, Object> getProperties(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		
        Class<?> objClass = obj.getClass();

        // Get all fields
        for (Field field : objClass.getFields()) {
        	try {
	        	if (matchField(field, field.getName())) {
	        		String name = field.getName();
	        		Object value = field.get(obj);
	        		map.put(name, value);
	        	}
        	} catch (Throwable t) {
        		// Ignore exception
        	}
        }
		
        // Get all properties
        for (Method method : objClass.getMethods()) {
        	try {
            	if (method.getName().startsWith("get") && matchPropertyGetter(method, method.getName())) {
	        		String name = method.getName().substring(3);
	        		name = name.substring(0, 1).toLowerCase() + name.substring(1);
	        		Object value = method.invoke(obj);
	        		map.put(name, value);
	        	}        		
        	} catch (Throwable t) {
        		// Ignore exception
        	}
        }
        
		return map;
	}
	
	public static void setProperty(Object obj, String name, Object value) {
		if (obj == null)
			throw new NullPointerException("Object cannot be null");
		if (name == null)
			throw new NullPointerException("Property name cannot be null");

        Class<?> objClass = obj.getClass();

        for (Field field : objClass.getFields()) {
	    	try {        		
	            if (matchField(field, name)) { 
	        		field.set(obj, value);
	        		return;
	            }
	    	} catch (Exception ex) {
	    		// Ignore exception
	    	}
        }

        name = "set" + name;
        for (Method method : objClass.getMethods()) {
	    	try {
	        	if (matchPropertySetter(method, name)) { 
	        		method.invoke(obj, value);
	        		return;
		        }
	    	} catch (Exception ex) {
	    		// Ignore exception
	    	}
		}
	}
	
	public static void setProperties(Object obj, Map<String, Object> values) {
		if (values == null || values.size() == 0) return;
		
		for (Map.Entry<String, Object> entry : values.entrySet()) {
			setProperty(obj, entry.getKey(), entry.getValue());
		}
	}
}
