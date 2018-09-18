package org.pipservices.commons.run;

import org.pipservices.commons.errors.*;

/**
 * Helper class that closes previously opened components.
 * 
 * ICloseable
 */
public class Closer {
	/**
	 * Closes specific component.
	 * 
	 * To be closed components must implement ICloseable interface. If they don't
	 * the call to this method has no effect.
	 * 
	 * @param correlationId (optional) transaction id to trace execution through
	 *                      call chain.
	 * @param component     the component that is to be closed.
	 * @throws ApplicationException when error or null no errors occured.
	 * 
	 * @see IClosable
	 */
	public static void closeOne(String correlationId, Object component) throws ApplicationException {

		if (component instanceof IClosable)
			((IClosable) component).close(correlationId);
	}

	/**
	 * Closes multiple components.
	 * 
	 * To be closed components must implement ICloseable interface. If they
	 * don't the call to this method has no effect.
	 * 
	 * @param correlationId (optional) transaction id to trace execution through
	 *                      call chain.
	 * @param components    the list of components that are to be closed.
	 * @throws ApplicationException when error or null no errors occured.
	 * 
	 * @see #closeOne(String, Object)
	 * @see IClosable
	 */
	public static void close(String correlationId, Iterable<Object> components) throws ApplicationException {

		if (components == null)
			return;

		for (Object component : components)
			closeOne(correlationId, component);
	}
}
