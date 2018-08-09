package org.pipservices.commons.run;

import org.pipservices.commons.errors.*;

/**
 * Helper class that cleans components
 */
public class Cleaner {
	/**
	 * Cleans component that implement ICleanable interface
	 * @param correlationId a unique transaction id to trace calls across components
	 * @param component a components to be cleaned
	 */
	public static void clearOne(String correlationId, Object component) 
		throws ApplicationException {
		
		if (component instanceof ICleanable)
			((ICleanable)component).clear(correlationId);
	}

	/**
	 * Cleans components that implement ICleanable interface
	 * @param correlationId a unique transaction id to trace calls across components
	 * @param components a list of components to be cleaned
	 */
	public static void clear(String correlationId, Iterable<Object> components) 
		throws ApplicationException {
		
		if (components == null) return;
		
		for (Object component : components)
			clearOne(correlationId, component);
	}
}
