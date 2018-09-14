package org.pipservices.commons.commands;

import org.pipservices.commons.run.*;

/**
 * An interface for listener objects that receive notifications on fired events.
 * <p>
 * ### Example ###
 * <pre>
 * {@code
 *  public class MyListener implements IEventListener {
 *     private void onEvent(String correlationId, IEvent event,Parameters args) {
 *       System.out.println("Fired event " + event.getName());
 *     }
 *  }
 * 
 *  Event event = new Event("myevent");
 *  event.addListener(new MyListener());
 *  event.notify("123", Parameters.fromTuples("param1", "ABC"));
 * 
 *  // Console output: Fired event myevent
 * }
 * </pre> 
 * @see IEvent
 * @see Event
 */
public interface IEventListener {
	/**
	 * A method called when events this listener is subscrubed to are fired.
	 * 
	 * @param event         a fired event
	 * @param correlationId optional transaction id to trace calls across
	 *                      components.
	 * @param args          event arguments.
	 */
	void onEvent(String correlationId, IEvent event, Parameters args);
}