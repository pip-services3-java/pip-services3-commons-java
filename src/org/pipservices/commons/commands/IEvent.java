package org.pipservices.commons.commands;

import java.util.List;

import org.pipservices.commons.run.*;

/**
 * Interface for command events.
 */
public interface IEvent extends INotifiable {
	/**
	 * Gets the event name
	 */
    String getName();

    /**
     * Get listeners that receive notifications for that event
     */
    List<IEventListener> getListeners();

    /**
     * Adds listener to receive notifications
     * @param listener a listener reference to be added
     */
    void addListener(IEventListener listener);

    /**
     * Removes listener for event notifications.
     * @param listener a listener reference to be removed
     */
    void removeListener(IEventListener listener);
}