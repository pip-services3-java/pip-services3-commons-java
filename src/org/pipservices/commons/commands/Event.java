package org.pipservices.commons.commands;

import java.util.*;

import org.pipservices.commons.errors.*;
import org.pipservices.commons.run.*;

/**
 * Events to receive notifications on command execution results and failures.
 */
public class Event implements IEvent {
    private String _name;
    private List<IEventListener> _listeners = new ArrayList<IEventListener>();

    /**
     * Creates and initializes instance of the event.
     * @param name name of the event
     */
    public Event(String name) {
        if (name == null)
            throw new NullPointerException("Event name is not set");

        _name = name;
    }

    /**
     * Gets the event name
     */
    public String getName() {
    	return _name;    	
    }

    /**
     * Gets listeners that receive notifications for that event
     */
    public List<IEventListener> getListeners() {
    	return _listeners;
    }

    /**
     * Adds listener to receive notifications
     * @param listener a listener reference to be added
     */
    public void addListener(IEventListener listener) {
        _listeners.add(listener);
    }

    /**
     * Remove listener for event notifications
     * @param listener a listener to be removed
     */
    public void removeListener(IEventListener listener) {
        _listeners.remove(listener);
    }

    /**
     * Notifies all listeners about the event.
     * @param correlationId a unique correlation/transaction id
     * @param value an event value
     */
    public void notify(String correlationId, Parameters value) throws ApplicationException {
        for (IEventListener listener : _listeners) {
            try {
                listener.onEvent(correlationId, this, value);
            } catch (Exception ex) {
            	throw new InvocationException(     
            		correlationId,
                    "EXEC_FAILED",
                    "Rasing event " + _name + " failed: " + ex
                )
    			.withDetails("event", _name)
                .wrap(ex);
            }
        }
    }
}
