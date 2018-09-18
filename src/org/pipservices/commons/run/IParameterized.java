package org.pipservices.commons.run;

import org.pipservices.commons.errors.*;

/**
 * Interface for components that require execution parameters.
 */
public interface IParameterized {
	/**
	 * Sets execution parameters.
	 * 
	 * @param parameters execution parameters.
	 * @throws ConfigException when configuration is wrong
	 */
	void setParameters(Parameters parameters) throws ConfigException;
}
