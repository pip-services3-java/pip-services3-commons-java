package org.pipservices.commons.run;

import org.pipservices.commons.errors.*;

/**
 * Interface for components that require parameters
 */
public interface IParameterized {
	/**
	 * Sets component configuration parameters
	 * @param parameters configuration parameters
	 * @throws ConfigException when configuration is wrong
	 */
	void setParameters(Parameters parameters) throws ConfigException;
}
