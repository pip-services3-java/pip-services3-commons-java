package org.pipservices3.commons.run;

import org.pipservices3.commons.errors.*;

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
