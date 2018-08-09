package org.pipservices.commons.commands;

import java.util.*;

import org.pipservices.commons.errors.*;
import org.pipservices.commons.run.*;
import org.pipservices.commons.validate.*;

/**
 * Interceptor wrapper to turn it into stackable command
 */
public class InterceptedCommand implements ICommand {
	private ICommandInterceptor _intercepter;
	private ICommand _next;
	
	/**
	 * Creates instance of intercepted command by chaining
	 * intercepter with the next intercepter in the chain 
	 * or command at the end of the chain.
	 * @param intercepter the intercepter reference.
	 * @param next the next intercepter or command in the chain.
	 */
	public InterceptedCommand(ICommandInterceptor intercepter, ICommand next) {
		_intercepter = intercepter;
		_next = next;
	}
	
	/**
	 * Gets the command name.
	 * @return the command name
	 */
	public String getName() {
		return _intercepter.getName(_next);
	}
	
	/**
	 * Executes the command given specific arguments as an input.
	 * @param correlationId a unique correlation/transaction id
	 * @param args command arguments
	 * @return execution result.
	 * @throws ApplicationException when execution fails for whatever reason.
	 */
	public Object execute(String correlationId, Parameters args) throws ApplicationException {
		return _intercepter.execute(correlationId, _next, args);
	}
	
	/**
	 * Performs validation of the command arguments.
	 * @param args command arguments
	 * @return a list of validation results
	 */
	public List<ValidationResult> validate(Parameters args) {
		return _intercepter.validate(_next, args);
	}
}
