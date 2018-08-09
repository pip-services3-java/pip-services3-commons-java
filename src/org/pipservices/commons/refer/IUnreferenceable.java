package org.pipservices.commons.refer;

/**
 * Interface for components that require clear of references to other components
 */
public interface IUnreferenceable {
	/**
	 * Unsets previously set references to other components. 
	 */
	void unsetReferences();
}
