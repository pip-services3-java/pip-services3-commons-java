package org.pipservices.commons.config;

import org.pipservices.commons.errors.*;

/**
 * An interface to set configuration parameters to an object.
 * 
 * It can be added to any existing class by implementing a single Configure() method.
 * 
 * If you need to emphasis the fact that Configure() method can be called multiple times
 * to change object configuration in runtime, use IReconfigurable interface instead.
 * <p>
 * ### Example ###
 * <pre>
 * {@code
 * public class MyClass implements IConfigurable {
 *    private String _myParam = "default value";
 *         
 *    public void configure(ConfigParams config) {
 *      this._myParam = config.getAsStringWithDefault("options.param", myParam);
 *             ...
 *    }
 * }
 * }
 * </pre>
 * @see ConfigParams
 */
public interface IConfigurable {
	/**
	 * Configures object by passing configuration parameters.
	 * 
	 * @param config configuration parameters to be set.
	 * @throws ConfigException when configuration is wrong
	 */
	void configure(ConfigParams config) throws ConfigException;
}
