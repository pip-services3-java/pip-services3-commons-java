package org.pipservices.commons.data;

/**
 * Interface for data objects that have human-readable name
 */
public interface INamed {
	/**
	 * Gets the object name
	 * @return the object name
	 */
	String getName();

	/**
	 * Sets the object name
	 * @param value a new object name
	 */
	void setName(String value);
}
