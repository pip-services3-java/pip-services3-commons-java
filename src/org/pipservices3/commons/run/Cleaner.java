package org.pipservices3.commons.run;

import org.pipservices3.commons.errors.*;

/**
 * Helper class that cleans stored object state.
 * 
 * @see ICleanable
 */
public class Cleaner {
	/**
	 * Clears state of specific component.
	 * 
	 * To be cleaned state components must implement ICleanable interface. If they
	 * don't the call to this method has no effect.
	 * 
	 * @param correlationId (optional) transaction id to trace execution through
	 *                      call chain.
	 * @param component     the component that is to be cleaned.
	 * @throws ApplicationException when errors occured.
	 * 
	 * @see ICleanable
	 */
	public static void clearOne(String correlationId, Object component) throws ApplicationException {

		if (component instanceof ICleanable)
			((ICleanable) component).clear(correlationId);
	}

	/**
	 * Clears state of multiple components.
	 * 
	 * To be cleaned state components must implement ICleanable interface. If they
	 * don't the call to this method has no effect.
	 * 
	 * @param correlationId (optional) transaction id to trace execution through
	 *                      call chain.
	 * @param components    the list of components that are to be cleaned.
	 * 
	 * @throws ApplicationException when errors occured.
	 * 
	 * @see #clearOne(String, Object)
	 * @see ICleanable
	 */
	public static void clear(String correlationId, Iterable<Object> components) throws ApplicationException {

		if (components == null)
			return;

		for (Object component : components)
			clearOne(correlationId, component);
	}
}
