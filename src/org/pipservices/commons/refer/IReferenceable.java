package org.pipservices.commons.refer;

import org.pipservices.commons.errors.ConfigException;

/**
 * Interface for components that requires references to other components
 */
public interface IReferenceable {
	/**
	 * Sets references to other components. 
	 * Using locators the component can find required dependencies 
	 * @param references component references
	 * @throws ConfigException 
	 */
	void setReferences(IReferences references) throws ReferenceException, ConfigException;
}
