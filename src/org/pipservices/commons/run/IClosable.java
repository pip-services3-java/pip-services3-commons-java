package org.pipservices.commons.run;

import org.pipservices.commons.errors.*;

/**
 * Interface for components that require explicit closure
 */
public interface IClosable {
	/**
	 * Closes component, disconnects it from services, disposes resources
	 * @param correlationId a unique transaction id to trace calls across components
	 */
	void close(String correlationId) throws ApplicationException;
}
