package org.pipservices.commons.commands;

import java.util.*;

import org.pipservices.commons.errors.*;
import org.pipservices.commons.run.*;
import org.pipservices.commons.validate.*;

/**
 * Interface for stackable command intercepters
 */
public interface ICommandInterceptor {
	/**
	 * Gets the command name. Intercepter can modify the name if needed
	 * @param command the intercepted command
	 * @return the command name
	 */
	String getName(ICommand command);
	
	/**
	 * Executes the command given specific arguments as an input.
	 * @param correlationId a unique correlation/transaction id
	 * @param command the intercepted command
	 * @param args map with command arguments
	 * @return execution result.
	 * @throws ApplicationException when execution fails for whatever reason.
	 */
	Object execute(String correlationId, ICommand command, Parameters args) throws ApplicationException;
	
	/**
	 * Performs validation of the command arguments.
	 * @param command the intercepted command
	 * @param args map with command arguments
	 * @return a list of validation results.
	 */
	List<ValidationResult> validate(ICommand command, Parameters args);
}
