package org.pipservices.commons.run;

import org.pipservices.commons.errors.*;

/**
 * Helper class that triggers notification for components
 */
public class Notifier {
	/**
	 * Triggers notification for component that implement INotifiable interface. 
	 * @param correlationId a unique transaction id to trace calls across components
	 * @param components a list of components to be notified
	 * @param args a set of parameters to pass to notified components
	 */
	public static void notifyOne(
		String correlationId, Object component, Parameters args)
		throws ApplicationException {
		
		if (component instanceof INotifiable)
			((INotifiable)component).notify(correlationId, args);
	}

	/**
	 * Triggers notification for components that implement INotifiable interface. 
	 * @param correlationId a unique transaction id to trace calls across components
	 * @param components a list of components to be notified
	 * @param args a set of parameters to pass to notified components
	 */
	public static void notify(
		String correlationId, Iterable<Object> components, Parameters args)
		throws ApplicationException {
		
		if (components == null) return;
		
		for (Object component : components) 
			notifyOne(correlationId, component, args);
	}
}
