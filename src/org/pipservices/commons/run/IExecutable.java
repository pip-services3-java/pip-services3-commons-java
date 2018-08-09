package org.pipservices.commons.run;

import org.pipservices.commons.errors.*;

/**
 * Interface for components that support parameterized execution that returns a result
 */
public interface IExecutable {
	/**
	 * Executes a unit of work with given parameters
	 * @param correlationId a unique transaction id to trace calls across components
	 * @param args a set of parameters for execution
	 * @return execution result
	 */
	Object execute(String correlationId, Parameters args) throws ApplicationException;
}
