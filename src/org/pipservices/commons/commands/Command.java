package org.pipservices.commons.commands;

import java.util.*;

import org.pipservices.commons.errors.*;
import org.pipservices.commons.run.*;
import org.pipservices.commons.validate.*;

/**
 * Represents a command that implements a command pattern
 */
public class Command implements ICommand {
	private String _name;
	private Schema _schema;
	private IExecutable _function;
	
	/**
	 * Creates a command instance
	 * @param name the name of the command
	 * @param schema a validation schema for command arguments
	 * @param function an execution function to be wrapped into this command.
	 */
	public Command(String name, Schema schema, IExecutable function) {
		if (name == null)
			throw new NullPointerException("Command name is not set");
		if (function == null)
			throw new NullPointerException("Command function is not set");
		
		_name = name;
		_schema = schema;
		_function = function;
	}

	/**
	 * Gets the command name.
	 * @return the command name
	 */
	@Override
	public String getName() {
		return _name;
	}

	/**
	 * Executes the command given specific arguments as an input.
	 * @param correlationId a unique correlation/transaction id
	 * @param args command arguments
	 * @return execution result.
	 * @throws ApplicationException when execution fails for whatever reason.
	 */
	@Override
	public Object execute(String correlationId, Parameters args) throws ApplicationException {
		// Validate arguments
		if (_schema != null)
			_schema.validateAndThrowException(correlationId, args);
		
		// Call the function
		try {
			return _function.execute(correlationId, args);
		}
		// Intercept unhandled errors
		catch (Throwable ex) {
			throw new InvocationException(
				correlationId, 
				"EXEC_FAILED", 
				"Execution " + _name + " failed: " + ex
			)
			.withDetails("command", _name)
			.wrap(ex);
		}
	}

	/**
	 * Performs validation of the command arguments.
	 * @param args command arguments
	 * @return a list with validation results
	 */
	@Override
	public List<ValidationResult> validate(Parameters args) {
		// When schema is not defined, then skip validation
		if (_schema != null) 
			return _schema.validate(args);
		
		// ToDo: Complete implementation
		return new ArrayList<ValidationResult>();
	}
}
