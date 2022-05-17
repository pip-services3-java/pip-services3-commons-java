package org.pipservices3.commons.run;

import org.pipservices3.commons.errors.*;

/**
 * Interface for components that can be asynchronously notified.
 * The notification may include optional argument that describe
 * the occured event.
 * <p>
 * ### Example ###
 * <pre>
 * {@code
 * class MyComponent implements INotifable {
 *   ...
 *   public void notify(String correlationId, Parameters args) {
 *     System.out.println("Occured event " + args.getAsString("event"));
 *   }
 * }
 *
 * MyComponent myComponent = new MyComponent();
 *
 * myComponent.notify("123", Parameters.fromTuples("event", "Test Event"));
 * }
 * </pre>
 *
 * @see Notifier
 * @see IExecutable
 */
public interface INotifiable {
    /**
     * Notifies the component about occured event.
     *
     * @param correlationId (optional) transaction id to trace execution through
     *                      call chain.
     * @param args          notification arguments.
     * @throws ApplicationException when errors occured.
     */
    void notify(String correlationId, Parameters args) throws ApplicationException;
}
