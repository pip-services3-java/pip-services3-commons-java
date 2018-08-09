package org.pipservices.commons.refer;

import org.pipservices.commons.errors.*;

/**
 * Component descriptor used to find a component by its descriptive elements:
 * <ul>
 * <li> logical group: package or other logical group of components like 'pip-services-storage-blocks'
 * <li> component type: identifies component interface like 'controller', 'services' or 'cache'
 * <li> component kind: identifies component implementation like 'memory', 'file' or 'mongodb', ...
 * <li> component name: identifies component internal content, ...
 * <li> implementation version: '1.0', '1.5' or '10.4'
 * </ul>
 */
public class Descriptor {
	private String _group;
	private String _type;
	private String _kind;
	private String _name;
	private String _version;
	
	/**
	 * Creates instance of a component locator
	 * @param group - logical group: 'pip-services-runtime', 'pip-services-logging' 
	 * @param type - external type: 'cache', 'services' or 'controllers'
	 * @param kind - internal implementation: 'memory', 'file' or 'memcached' 
	 * @param name - internal content
	 * @param version - implementation version: '1.0'. '1.5' or '10.4'
	 */
	public Descriptor(String group, String type, String kind, String name, String version) {
		if ("*".equals(group)) group = null;
		if ("*".equals(type)) type = null;
		if ("*".equals(kind)) kind = null;
		if ("*".equals(name)) name = null;
		if ("*".equals(version)) version = null;
		
		_group = group;
		_type = type;
		_kind = kind;
		_name = name;
		_version = version;
	}

	/**
	 * Gets a component group
	 * @return a component group
	 */
	public String getGroup() { 
		return _group; 
	}
	
	/**
	 * Gets a component type
	 * @return a component type
	 */
	public String getType() { 
		return _type; 
	}
	
	/**
	 * Gets a component kind
	 * @return a component kind
	 */
	public String getKind() { 
		return _kind; 
	}

	/**
	 * Gets a component name
	 * @return a component name
	 */
	public String getName() { 
		return _name; 
	}
	
	/**
	 * Gets an implementation version
	 * @return an implementation version
	 */
	public String getVersion() { 
		return _version; 
	}

	private boolean matchField(String field1, String field2) {
		return field1 == null 
			|| field2 == null
			|| field1.equals(field2);
	}

	/**
	 * Matches this descriptor to another descriptor
	 * All '*' or null descriptor elements match to any other value.
	 * Specific values must match exactly.
	 * 
	 * @param descriptor - another descriptor to match this one.
	 * @return <b>true</b> if descriptors match or <b>false</b> otherwise. 
	 */
	public boolean match(Descriptor descriptor) {
		return matchField(_group, descriptor.getGroup())
			&& matchField(_type, descriptor.getType())
			&& matchField(_kind, descriptor.getKind())
			&& matchField(_name, descriptor.getName())
			&& matchField(_version, descriptor.getVersion());
	}
	
	private boolean exactMatchField(String field1, String field2) {
		if (field1 == null && field2 == null)
			return true;
		if (field1 == null || field2 == null)
			return false;
		return field1.equals(field2);
	}
	
	public boolean exactMatch(Descriptor descriptor) {
		return exactMatchField(_group, descriptor.getGroup())
			&& exactMatchField(_type, descriptor.getType())
			&& exactMatchField(_kind, descriptor.getKind())
			&& exactMatchField(_name, descriptor.getName())
			&& exactMatchField(_version, descriptor.getVersion());
	}
	
	public boolean isComplete() {
		return _group != null && _type != null && _kind != null
			&& _name != null && _version != null;
	}
	
	@Override
	public boolean equals(Object value) {
		if (value instanceof Descriptor)
			return match((Descriptor)value);
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(_group != null ? _group : "*")
			.append(":").append(_type != null ? _type : "*")
			.append(":").append(_kind != null ? _kind : "*")
			.append(":").append(_name != null ? _name : "*")
			.append(":").append(_version != null ? _version : "*");
		return builder.toString();
	}
	
	public static Descriptor fromString(String value) throws ConfigException {
		if (value == null || value.length() == 0) 
			return null;
				
		String[] tokens = value.split(":");
		if (tokens.length != 5) {
			throw (ConfigException) new ConfigException(
				null, "BAD_DESCRIPTOR", "Descriptor " + value + " is in wrong format"
			).withDetails("descriptor", value);
		}
			
		return new Descriptor(tokens[0].trim(), tokens[1].trim(), tokens[2].trim(), tokens[3].trim(), tokens[4].trim());		
	}
}
