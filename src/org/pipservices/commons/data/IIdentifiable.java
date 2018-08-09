package org.pipservices.commons.data;

/**
 * Interface for data objects that can be identified by an id
 */
public interface IIdentifiable<K> {
	/**
	 * Gets the object id
	 * @return object id
	 */
	K getId();

	/**
	 * Sets the object id
	 * @param value a new object id
	 */
	void setId(K value);
}
