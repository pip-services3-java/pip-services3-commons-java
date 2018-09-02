package org.pipservices.commons.run;

import org.pipservices.commons.errors.*;

/**
 * Interface for components that require explicit opening
 */
public interface IOpenable extends IClosable {
	/**
	 * Checks if component is opened
	 * @returns <code>true</code> if component is opened and <false> otherwise.
	 */
	boolean isOpen();
;	
	/**
	 * Opens component, establishes connections to services
	 * @param correlationId a unique transaction id to trace calls across components
	 */
	void open(String correlationId) throws ApplicationException;
}
