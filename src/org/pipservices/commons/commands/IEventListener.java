package org.pipservices.commons.commands;

import org.pipservices.commons.run.*;

/**
 * Listener for command events
 */
public interface IEventListener {
	/**
	 * Notifies that event occurred.
	 * @param event event reference
	 * @param correlationId a unique correlation/transaction id
	 * @param value event arguments
	 */
    void onEvent(String correlationId, IEvent event, Parameters value);
}