package org.pipservices3.commons.commands;

import java.util.List;

import org.pipservices3.commons.run.*;

/**
 * An interface for Events, which are part of the Command design pattern. Events
 * allows to send asynchronous notifications to multiple subscribed listeners.
 * 
 * @see IEventListener
 */
public interface IEvent extends INotifiable {
	/**
	 * Gets the event name.
	 * 
	 * @return the name of the event.
	 */
	String getName();

	/**
	 * Gets all subscribed listeners.
	 * 
	 * @return a list of listeners.
	 */
	List<IEventListener> getListeners();

	/**
	 * Adds a listener to receive notifications for this event.
	 * 
	 * @param listener the listener reference to add.
	 */
	void addListener(IEventListener listener);

	/**
	 * Removes a listener, so that it no longer receives notifications for this
	 * event.
	 * 
	 * @param listener the listener reference to remove.
	 */
	void removeListener(IEventListener listener);
}