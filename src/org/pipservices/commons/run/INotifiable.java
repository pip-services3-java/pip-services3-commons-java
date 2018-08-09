package org.pipservices.commons.run;

import org.pipservices.commons.errors.*;

/**
 * Interface for components that support parameterized one-way notification 
 */
public interface INotifiable {
	/**
	 * Executes a unit of work with given parameters
	 * @param correlationId a unique transaction id to trace calls across components
	 * @param args a set of parameters for execution
	 */
	void notify(String correlationId, Parameters args) throws ApplicationException;
}
