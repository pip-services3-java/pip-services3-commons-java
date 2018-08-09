package org.pipservices.commons.run;

import java.util.*;

import org.pipservices.commons.errors.*;

/**
 * Helper class that triggers execution for components
 */
public class Executor {
	/**
	 * Triggers execution for component that implement IExecutable interface. 
	 * @param correlationId a unique transaction id to trace calls across components
	 * @param component a components to be executed
	 * @param args a set of parameters to pass to executed components
	 * @return execution results
	 */
	public static Object executeOne(
		String correlationId, Object component, Parameters args)
		throws ApplicationException {
		
		if (component instanceof IExecutable)
			return ((IExecutable)component).execute(correlationId, args);
		else return null;
	}

	/**
	 * Triggers execution for components that implement IExecutable interfaces. 
	 * @param correlationId a unique transaction id to trace calls across components
	 * @param components a list of components to be executed
	 * @param args a set of parameters to pass to executed components
	 * @return execution results
	 */
	public static List<Object> execute(
		String correlationId, Iterable<Object> components, Parameters args)
		throws ApplicationException {
		
		List<Object> results = new ArrayList<Object>();				
		if (components == null) return results;
		
		for (Object component : components) { 
			if (component instanceof IExecutable)
				results.add(executeOne(correlationId, component, args));
		}
		
		return results;
	}
}
