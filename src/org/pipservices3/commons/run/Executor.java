package org.pipservices3.commons.run;

import java.util.*;

import org.pipservices3.commons.errors.*;

/**
 * Helper class that executes components.
 * 
 * @see IExecutable
 */
public class Executor {
	/**
	 * Executes specific component.
	 * 
	 * To be executed components must implement IExecutable interface. If they don't
	 * the call to this method has no effect.
	 * 
	 * @param correlationId (optional) transaction id to trace execution through
	 *                      call chain.
	 * @param component     the component that is to be executed.
	 * @param args          execution arguments.
	 * @return execution result.
	 * @throws ApplicationException when errors occured.
	 * 
	 * @see IExecutable
	 * @see Parameters
	 */
	public static Object executeOne(String correlationId, Object component, Parameters args)
			throws ApplicationException {

		if (component instanceof IExecutable)
			return ((IExecutable) component).execute(correlationId, args);
		else
			return null;
	}

	/**
	 * Executes multiple components.
	 * 
	 * To be executed components must implement IExecutable interface. If they don't
	 * the call to this method has no effect.
	 * 
	 * @param correlationId (optional) transaction id to trace execution through
	 *                      call chain.
	 * @param components    a list of components that are to be executed.
	 * @param args          execution arguments.
	 * @return execution result.
	 * @throws ApplicationException when errors occured.
	 * 
	 * @see #executeOne(String, Object, Parameters)
	 * @see IExecutable
	 * @see Parameters
	 */
	public static List<Object> execute(String correlationId, Iterable<Object> components, Parameters args)
			throws ApplicationException {

		List<Object> results = new ArrayList<Object>();
		if (components == null)
			return results;

		for (Object component : components) {
			if (component instanceof IExecutable)
				results.add(executeOne(correlationId, component, args));
		}

		return results;
	}
}
