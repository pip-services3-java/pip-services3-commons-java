package org.pipservices.commons.run;

import org.pipservices.commons.errors.*;

/**
 * Helper class that closes components
 */
public class Closer {
	/**
	 * Closes a component that implement ICloseable interface
	 * @param correlationId a unique transaction id to trace calls across components
	 * @param component a components to be closed
	 */
	public static void closeOne(String correlationId, Object component) 
		throws ApplicationException {
		
		if (component instanceof IClosable)
			((IClosable)component).close(correlationId);
	}

	/**
	 * Closes components that implement ICloseable interface
	 * @param correlationId a unique transaction id to trace calls across components
	 * @param components a list of components to be closed
	 */
	public static void close(String correlationId, Iterable<Object> components) 
		throws ApplicationException {
		
		if (components == null) return;
		
		for (Object component : components)
			closeOne(correlationId, component);
	}
}
