package org.pipservices.commons.run;

import org.pipservices.commons.errors.*;

/**
 * Helper class that notifies components.
 * 
 * @see INotifiable
 */
public class Notifier {
	/**
	 * Notifies specific component.
	 * 
	 * To be notiied components must implement INotifiable interface. If they don't
	 * the call to this method has no effect.
	 * 
	 * @param correlationId (optional) transaction id to trace execution through
	 *                      call chain.
	 * @param component     the component that is to be notified.
	 * @param args          notifiation arguments.
	 * @throws ApplicationException when errors occured.
	 * 
	 * @see INotifiable
	 */
	public static void notifyOne(String correlationId, Object component, Parameters args) throws ApplicationException {

		if (component instanceof INotifiable)
			((INotifiable) component).notify(correlationId, args);
	}

	/**
	 * Notifies multiple components.
	 * 
	 * To be notified components must implement INotifiable interface. If they don't
	 * the call to this method has no effect.
	 * 
	 * @param correlationId (optional) transaction id to trace execution through
	 *                      call chain.
	 * @param components    a list of components that are to be notified.
	 * @param args          notification arguments.
	 * @throws ApplicationException when errors occured.
	 * 
	 * @see #notifyOne(String, Object, Parameters)
	 * @see INotifiable
	 */
	public static void notify(String correlationId, Iterable<Object> components, Parameters args)
			throws ApplicationException {

		if (components == null)
			return;

		for (Object component : components)
			notifyOne(correlationId, component, args);
	}
}
