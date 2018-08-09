package org.pipservices.commons.reflect;

import java.lang.reflect.*;
import java.util.*;

public class MethodReflector {
	
	private static boolean matchMethod(Method method, String name) {
    	int mod = method.getModifiers();
    	return method.getName().equalsIgnoreCase(name)
			&& Modifier.isPublic(mod) && !Modifier.isStatic(mod)
			&& !Modifier.isAbstract(mod);
	}

	public static boolean hasMethod(Object obj, String name) {
		if (obj == null)
			throw new NullPointerException("Object cannot be null");
		if (name == null)
			throw new NullPointerException("Method name cannot be null");
		
        Class<?> objClass = obj.getClass();

        for (Method method : objClass.getMethods()) {
        	if (matchMethod(method, name))
        		return true;
        }

        return false;
	}

	public static Object invokeMethod(Object obj, String name, Object... args) {
		if (obj == null)
			throw new NullPointerException("Object cannot be null");
		if (name == null)
			throw new NullPointerException("Method name cannot be null");
		
        Class<?> objClass = obj.getClass();

        for (Method method : objClass.getMethods()) {
        	try {
	        	if (matchMethod(method, name))
	        		return method.invoke(obj, args);
        	} catch (Throwable t) {
        		// Ignore exceptions
        	}
        }

        return null;
	}

	public static List<String> getMethodNames(Object obj) {
		List<String> methods = new ArrayList<String>();
		
        Class<?> objClass = obj.getClass();

        for (Method method : objClass.getMethods()) {
        	if (matchMethod(method, method.getName())) {
        		methods.add(method.getName());
        	}        		
        }
        
		return methods;
	}

}
