package org.pipservices.commons.reflect;

import org.pipservices.commons.errors.*;

public class TypeDescriptor {
	private String _name;
	private String _library;
		
	public TypeDescriptor(String name, String library) {
		_name = name;
		_library = library;
	}
	
	public String getName() { return _name; }
	
	public String getLibrary() { return _library; }
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TypeDescriptor) {
			TypeDescriptor otherType = (TypeDescriptor)obj;
			if (this.getName() == null || otherType.getName() == null)
				return false;
			if (!this.getName().equals(otherType.getName()))
				return false;
			if (this.getLibrary() == null || otherType.getLibrary() == null
				|| this.getLibrary().equals(otherType.getLibrary()))
				return true;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(_name);
		if (_library != null)
			builder.append(',').append(_library);
		return builder.toString();
	}
	
	public static TypeDescriptor fromString(String value) throws ConfigException {
		if (value == null || value.length() == 0) 
			return null;
				
		String[] tokens = value.split(",");
		if (tokens.length == 1) {
			return new TypeDescriptor(tokens[0].trim(), null);
		} else if (tokens.length == 2) {
			return new TypeDescriptor(tokens[0].trim(), tokens[1].trim());		
		} else {
			throw (ConfigException) new ConfigException(
				null, "BAD_DESCRIPTOR", "Type descriptor " + value + " is in wrong format"
			).withDetails("descriptor", value);
		}			
	}

}
