package org.pipservices.commons.run;

import org.pipservices.commons.errors.*;

/**
 * Helper class that opens a collection of components 
 */
public class Opener {
	/**
	 * Checks if component that implement IOpenable interface is opened
	 * @param component a component to be checked
	 */
	public static boolean isOpenedOne(Object component) {
		if (component instanceof IOpenable)
			return ((IOpenable)component).isOpened();
		else
			return true;
	}	

	/**
	 * Checks if components that implement IOpenable interface are opened
	 * @param components a list of components to be checked
	 */
	public static boolean isOpened(Iterable<Object> components) {
		if (components == null) return true;
		
		boolean result = true;
		for (Object component : components)
			result = result && isOpenedOne(component);
		
		return result;
	}

	/**
	 * Opens a component that implement IOpenable interface
	 * @param correlationId a unique transaction id to trace calls across components
	 * @param component a component to be opened
	 */
	public static void openOne(String correlationId, Object component) 
		throws ApplicationException {
		
		if (component instanceof IOpenable)
			((IOpenable)component).open(correlationId);
	}	

	/**
	 * Opens component that implement IOpenable interface
	 * @param correlationId a unique transaction id to trace calls across components
	 * @param components a list of components to be opened
	 */
	public static void open(String correlationId, Iterable<Object> components) 
		throws ApplicationException {
		
		if (components == null) return;
		
		for (Object component : components)
			openOne(correlationId, component);
	}
}
