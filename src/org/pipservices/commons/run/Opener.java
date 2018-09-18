package org.pipservices.commons.run;

import org.pipservices.commons.errors.*;

/**
 * Helper class that opens components.
 * 
 * @see IOpenable
 */
public class Opener {
	/**
	 * Checks if specified component is opened.
	 * 
	 * To be checked components must implement IOpenable interface. If they don't
	 * the call to this method returns true.
	 * 
	 * @param component the component that is to be checked.
	 * @return true if component is opened and false otherwise.
	 * 
	 * @see IOpenable
	 */
	public static boolean isOpenOne(Object component) {
		if (component instanceof IOpenable)
			return ((IOpenable) component).isOpen();
		else
			return true;
	}

	/**
	 * Checks if all components are opened.
	 * 
	 * To be checked components must implement IOpenable interface. If they don't
	 * the call to this method returns true.
	 * 
	 * @param components a list of components that are to be checked.
	 * @return true if all components are opened and false if at least one component
	 *         is closed.
	 * 
	 * @see #isOpenOne(Object)
	 * @see IOpenable
	 */
	public static boolean isOpen(Iterable<Object> components) {
		if (components == null)
			return true;

		boolean result = true;
		for (Object component : components)
			result = result && isOpenOne(component);

		return result;
	}

	/**
	 * Opens specific component.
	 * 
	 * To be opened components must implement IOpenable interface. If they don't the
	 * call to this method has no effect.
	 * 
	 * @param correlationId (optional) transaction id to trace execution through
	 *                      call chain.
	 * @param component     the component that is to be opened.
	 * @throws ApplicationException when error or null no errors occured.
	 * 
	 * @see IOpenable
	 */
	public static void openOne(String correlationId, Object component) throws ApplicationException {

		if (component instanceof IOpenable)
			((IOpenable) component).open(correlationId);
	}

	/**
	 * Opens multiple components.
	 * 
	 * To be opened components must implement IOpenable interface. If they don't the
	 * call to this method has no effect.
	 * 
	 * @param correlationId (optional) transaction id to trace execution through
	 *                      call chain.
	 * @param components    the list of components that are to be closed.
	 * @throws ApplicationException when error or null no errors occured.
	 * 
	 * @see #openOne(String, Object)
	 * @see IOpenable
	 */
	public static void open(String correlationId, Iterable<Object> components) throws ApplicationException {

		if (components == null)
			return;

		for (Object component : components)
			openOne(correlationId, component);
	}
}
