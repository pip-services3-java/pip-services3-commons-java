package org.pipservices.commons.data;

import java.time.*;

/**
 * Interface for data objects that can track their changes including logical deletion 
 */
public interface ITrackable {
	/**
	 * Gets the time when the object was created
	 * @return UTC date and time then object was created
	 */
	ZonedDateTime getCreateTime();

	/**
	 * Sets a time when the object was created
	 * @param value UTC date and time then object was created
	 */
	void setCreateTime(ZonedDateTime value);

	/**
	 * Gets the last time when the object was changed (created, updated or deleted)
	 * @return UTC date and time when the last change occurred
	 */
	ZonedDateTime getLastChangeTime();

	/**
	 * Sets the last time when the object was changed (created, updated or deleted)
	 * @param value UTC date and time when the last change occurred
	 */
	void setLastChangeTime(ZonedDateTime value);

	/**
	 * Gets the logical deletion flag
	 * @return <code>true</code> if the object was deleted and <code>false</code> if the object is still active
	 */
	boolean isDeleted();

	/**
	 * Sets the logical deletion flag
	 * @param <code>true</code> if the object was deleted and <code>false</code> if the object is still active
	 */
	void setDeleted(boolean value);
}
