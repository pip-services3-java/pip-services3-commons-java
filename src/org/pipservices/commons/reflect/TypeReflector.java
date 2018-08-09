package org.pipservices.commons.reflect;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;

import org.pipservices.commons.errors.*;

public class TypeReflector {

	public static Class<?> getType(String name, String library) {
		try {
	        // Load module
	        if (library != null && !library.isEmpty()) {
	        	URL moduleUrl = new File(library).toURI().toURL();
	        	URLClassLoader child = new URLClassLoader(
	    			new URL[] { moduleUrl }, ClassLoader.getSystemClassLoader());
	            return Class.forName(name, true, child);        	
	        } else {	        
		        return Class.forName(name);		
	        }
	    } catch (Exception ex) {
	    	return null;
	    }
	}

	public static Class<?> getType(String name) {
		return getType(name, null);
	}

	public static Class<?> getTypeByDescriptor(TypeDescriptor type) {
		if (type == null)
			throw new NullPointerException("Type descriptor cannot be null");
			
		return getType(type.getName(), type.getLibrary());
	}

	public static Object createInstanceByType(Class<?> type, Object... args) throws Exception {
		if (args.length == 0) {
	    	Constructor<?> constructor = type.getConstructor();
	    	return constructor.newInstance();
		} else {
			throw new UnsupportedException(null, "NOT_SUPPORTED", "Constructors with paratemeters are not supported");
		}		
	}

	public static Object createInstance(String name, String library, Object... args) throws Exception {
		Class<?> type = getType(name, library);		
		if (type == null)
			throw new NotFoundException(null, "TYPE_NOT_FOUND", "Type " + name + "," + library + " was not found")
				.withDetails("type", name).withDetails("library", library);
		
		return createInstanceByType(type, args);
	}

	public static Object createInstance(String name, Object... args) throws Exception {
		return createInstance(name, (String)null, args);
	}

	public static Object createInstanceByDescriptor(TypeDescriptor type, Object... args) throws Exception {
		if (type == null)
			throw new NullPointerException("Type descriptor cannot be null");

		return createInstance(type.getName(), type.getLibrary(), args);
	}

}
