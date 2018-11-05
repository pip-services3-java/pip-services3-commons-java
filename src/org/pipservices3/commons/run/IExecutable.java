package org.pipservices3.commons.run;

import org.pipservices3.commons.errors.*;

/**
 * Interface for components that can be called to execute work.
 * <p>
 * ### Example ###
 * <pre>
 * {@code
 * class EchoComponent implements IExecutable {
 *   ...
 *   public void execute(String correlationId, Parameters args) {
 *     Object result = args.getAsObject("message");
 *   }
 * }
 * 
 * EchoComponent echo = new EchoComponent();
 * String message = "Test";
 * echo.execute("123", Parameters.fromTuples("message", message));
 * }
 * </pre>
 * @see Executor
 * @see INotifiable
 * @see Parameters
 */
public interface IExecutable {
	/**
	 * Executes component with arguments and receives execution result.
	 * 
	 * @param correlationId (optional) transaction id to trace execution through
	 *                      call chain.
	 * @param args          execution arguments.
	 * @return execution result.
	 * @throws ApplicationException when errors occured.
	 */
	Object execute(String correlationId, Parameters args) throws ApplicationException;
}
