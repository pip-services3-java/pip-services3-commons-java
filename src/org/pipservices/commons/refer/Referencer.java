package org.pipservices.commons.refer;

import org.pipservices.commons.errors.ConfigException;

/**
 * Helper class that assigns references to components
 */
public class Referencer {
	/**
	 * Assigns references to component that implement IReferenceable interface  
	 * @param references references to be assigned
	 * @param component a components to assign references
	 * @throws ConfigException 
	 */
	public static void setReferencesForOne(IReferences references, Object component) 
		throws ReferenceException, ConfigException {
		
		if (component instanceof IReferenceable)
			((IReferenceable)component).setReferences(references);
	}

	/**
	 * Assigns references to components that implement IReferenceable interface  
	 * @param references references to be assigned
	 * @param components a list of components to assign references
	 * @throws ConfigException 
	 */
	public static void setReferences(IReferences references, Iterable<Object> components) 
		throws ReferenceException, ConfigException {
		
		for (Object component : components)
			setReferencesForOne(references, component);
	}

	/**
	 * Clears references for component that implement IUnreferenceable interface 
	 * @param component a components to clear references
	 */
	public static void unsetReferencesForOne(Object component) {
		if (component instanceof IUnreferenceable)
			((IUnreferenceable)component).unsetReferences();
	}

	/**
	 * Clears references for components that implement IUnreferenceable interface 
	 * @param components a list of components to clear references
	 */
	public static void unsetReferences(Iterable<Object> components) {
		for (Object component : components)
			unsetReferencesForOne(component);
	}
}
