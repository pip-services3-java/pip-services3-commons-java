package org.pipservices.commons.refer;

import org.pipservices.commons.errors.*;

/**
 * Locator type that most often used in PipServices toolkit.
 * It locates components using several fields:
 * - Group: a package or just named group of components like "pip-services"
 * - Type: logical component type that defines it's contract like "persistence"
 * - Kind: physical implementation type like "mongodb"
 * - Name: unique component name like "default"
 * - Version: version of the component contract like "1.0"
 * 
 * The locator matching can be done by all or only few selected fields. 
 * The fields that shall be excluded from the matching must be set to "*" or null.
 * That approach allows to implement many interesting scenarios. For instance:
 * - Locate all loggers (match by type and version)
 * - Locate persistence components for a microservice (match by group and type)
 * - Locate specific component by its name (match by name)
 * 
 * ### Example ###
 * 
 * Descriptor locator1 = new Descriptor("mygroup", "connector", "aws", "default", "1.0");
 * Descriptor locator2 = Descriptor.fromString("mygroup:connector:*:*:1.0");
 * 
 * locator1.match(locator2);		// Result: true
 * locator1.equal(locator2);		// Result: true
 * locator1.exactMatch(locator2);	// Result: false
 */
public class Descriptor {
	private String _group;
	private String _type;
	private String _kind;
	private String _name;
	private String _version;

	/**
	 * Creates a new instance of the descriptor.
	 * 
	 * @param group   a logical component group
	 * @param type    a logical component type or contract
	 * @param kind    a component implementation type
	 * @param name    a unique component name
	 * @param version a component implementation version
	 */
	public Descriptor(String group, String type, String kind, String name, String version) {
		if ("*".equals(group))
			group = null;
		if ("*".equals(type))
			type = null;
		if ("*".equals(kind))
			kind = null;
		if ("*".equals(name))
			name = null;
		if ("*".equals(version))
			version = null;

		_group = group;
		_type = type;
		_kind = kind;
		_name = name;
		_version = version;
	}

	/**
	 * Gets the component's logical group.
	 * 
	 * @return the component's logical group
	 */
	public String getGroup() {
		return _group;
	}

	/**
	 * Gets the component's logical type.
	 * 
	 * @return the component's logical type.
	 */
	public String getType() {
		return _type;
	}

	/**
	 * Gets the component's implementation type.
	 * 
	 * @return the component's implementation type.
	 */
	public String getKind() {
		return _kind;
	}

	/**
	 * Gets the unique component's name.
	 * 
	 * @return the unique component's name.
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Gets the component's implementation version.
	 * 
	 * @return the component's implementation version.
	 */
	public String getVersion() {
		return _version;
	}

	private boolean matchField(String field1, String field2) {
		return field1 == null || field2 == null || field1.equals(field2);
	}

	/**
	 * Partially matches this descriptor to another descriptor. Fields that contain
	 * "*" or null are excluded from the match.
	 * 
	 * @param descriptor the descriptor to match this one against.
	 * @return true if descriptors match and false otherwise
	 * 
	 * @see #exactMatch(Descriptor)
	 */
	public boolean match(Descriptor descriptor) {
		return matchField(_group, descriptor.getGroup()) && matchField(_type, descriptor.getType())
				&& matchField(_kind, descriptor.getKind()) && matchField(_name, descriptor.getName())
				&& matchField(_version, descriptor.getVersion());
	}

	private boolean exactMatchField(String field1, String field2) {
		if (field1 == null && field2 == null)
			return true;
		if (field1 == null || field2 == null)
			return false;
		return field1.equals(field2);
	}

	/**
	 * Matches this descriptor to another descriptor by all fields. No exceptions
	 * are made.
	 * 
	 * @param descriptor the descriptor to match this one against.
	 * @return true if descriptors match and false otherwise.
	 * 
	 * @see #match(Descriptor)
	 */
	public boolean exactMatch(Descriptor descriptor) {
		return exactMatchField(_group, descriptor.getGroup()) && exactMatchField(_type, descriptor.getType())
				&& exactMatchField(_kind, descriptor.getKind()) && exactMatchField(_name, descriptor.getName())
				&& exactMatchField(_version, descriptor.getVersion());
	}

	/**
	 * Checks whether all descriptor fields are set. If descriptor has at least one
	 * "*" or null field it is considered "incomplete",
	 * 
	 * @return true if all descriptor fields are defined and false otherwise.
	 */
	public boolean isComplete() {
		return _group != null && _type != null && _kind != null && _name != null && _version != null;
	}

	/**
	 * Compares this descriptor to a value. If value is a Descriptor it tries to
	 * match them, otherwise the method returns false.
	 * 
	 * @param value the value to match against this descriptor.
	 * @return true if the value is matching descriptor and false otherwise.
	 * 
	 * @see #match(Descriptor)
	 */
	@Override
	public boolean equals(Object value) {
		if (value instanceof Descriptor)
			return match((Descriptor) value);
		return false;
	}

	/**
	 * Gets a string representation of the object. The result is a colon-separated
	 * list of descriptor fields as "mygroup:connector:aws:default:1.0"
	 * 
	 * @return a string representation of the object.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(_group != null ? _group : "*").append(":").append(_type != null ? _type : "*").append(":")
				.append(_kind != null ? _kind : "*").append(":").append(_name != null ? _name : "*").append(":")
				.append(_version != null ? _version : "*");
		return builder.toString();
	}

	/**
	 * Parses colon-separated list of descriptor fields and returns them as a
	 * Descriptor.
	 * 
	 * @param value colon-separated descriptor fields to initialize Descriptor.
	 * @return a newly created Descriptor.
	 * @throws ConfigException if the descriptor string is of a wrong format.
	 */
	public static Descriptor fromString(String value) throws ConfigException {
		if (value == null || value.length() == 0)
			return null;

		String[] tokens = value.split(":");
		if (tokens.length != 5) {
			throw (ConfigException) new ConfigException(null, "BAD_DESCRIPTOR",
					"Descriptor " + value + " is in wrong format").withDetails("descriptor", value);
		}

		return new Descriptor(tokens[0].trim(), tokens[1].trim(), tokens[2].trim(), tokens[3].trim(), tokens[4].trim());
	}
}
