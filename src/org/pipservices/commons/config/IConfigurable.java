package org.pipservices.commons.config;

import org.pipservices.commons.errors.*;

/**
 * Interface for components that require configuration
 */
public interface IConfigurable {
	/**
	 * Sets component configuration
	 * @param config configuration parameters
	 * @throws ConfigException when configuration is wrong
	 */
	void configure(ConfigParams config) throws ConfigException;
}
