package org.pipservices.commons.reflect;

import java.lang.reflect.*;
import java.util.*;

import org.pipservices.commons.convert.*;

public class ObjectReader {
	
    public static Object getValue(Object obj) {
    	// Todo: just a blank implementation for compatibility
        return obj;
    }

    @SuppressWarnings("unchecked")
	public static boolean hasProperty(Object obj, String name) {
        if (obj == null || name == null) {
        	return false;
        } else  if (obj instanceof Map<?,?>) {
            Map<Object,Object> map = (Map<Object,Object>)obj;
            for (Object key : map.keySet()) {
                if (name.equalsIgnoreCase(key.toString()))
                    return true;
            }
            return false;
        } else if (obj instanceof List<?>) {
            Integer index = IntegerConverter.toNullableInteger(name);
            List<Object> list = (List<Object>)obj;
            return index != null && index.intValue() >= 0 && index.intValue() < list.size();
        } else if (obj.getClass().isArray()) {
            Integer index = IntegerConverter.toNullableInteger(name);
			int length = Array.getLength(obj);
            return index != null && index.intValue() >= 0 && index.intValue() < length;
        } else {
            return PropertyReflector.hasProperty(obj, name);
        }
    }

    @SuppressWarnings("unchecked")
	public static Object getProperty(Object obj, String name) {
        if (obj == null || name == null) {
        	return null;
        } else if (obj instanceof Map<?,?>) {
            Map<Object,Object> map = (Map<Object,Object>)obj;
            for (Object key : map.keySet()) {
                if (name.equalsIgnoreCase(key.toString()))
                    return map.get(key);
            }
            return null;
        } else if (obj instanceof List<?>) {
            Integer index = IntegerConverter.toNullableInteger(name);
            List<Object> list = (List<Object>)obj;
            return index != null && index.intValue() >= 0 && index.intValue() < list.size() 
        		? list.get(index.intValue()) : null;
        } else if (obj.getClass().isArray()) {
            Integer index = IntegerConverter.toNullableInteger(name);
			int length = Array.getLength(obj);
            return index != null && index.intValue() >= 0 && index.intValue() < length
        		? Array.get(obj, index.intValue()) : null;
        } else {
            return PropertyReflector.getProperty(obj, name);
        }
    }

    @SuppressWarnings("unchecked")
	public static List<String> getPropertyNames(Object obj) {
        List<String> properties = new ArrayList<String>();
        
        if (obj == null) {
        	// Do nothing
        } else if (obj instanceof Map<?,?>) {
            Map<Object,Object> map = (Map<Object,Object>)obj;
            for (Map.Entry<Object, Object> entry : map.entrySet()) 
                properties.add(entry.getKey().toString());
        } else if (obj instanceof List<?>) {
            List<Object> list = (List<Object>)obj;
            for (int index = 0; index < list.size(); index++)
            	properties.add(Integer.toString(index));
        } else if (obj.getClass().isArray()) {
			int length = Array.getLength(obj);
            for (int index = 0; index < length; index++)
            	properties.add(Integer.toString(index));
        } else {
            return PropertyReflector.getPropertyNames(obj);
        }

        return properties;
    }

    @SuppressWarnings("unchecked")
	public static Map<String, Object> getProperties(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        if (obj == null) {
        	// Do nothing
        } else if (obj instanceof Map<?,?>) {
            Map<Object,Object> mapObj = (Map<Object,Object>)obj;
            for (Map.Entry<Object, Object> entry : mapObj.entrySet()) 
                map.put(entry.getKey().toString(), entry.getValue());
        } else if (obj instanceof List<?>) {
            List<Object> list = (List<Object>)obj;
            for (int index = 0; index < list.size(); index++)
            	map.put(Integer.toString(index), list.get(index));
        } else if (obj.getClass().isArray()) {
			int length = Array.getLength(obj);
            for (int index = 0; index < length; index++)
            	map.put(Integer.toString(index), Array.get(obj, index));
        } else {
            return PropertyReflector.getProperties(obj);
        }

        return map;
    }
}
