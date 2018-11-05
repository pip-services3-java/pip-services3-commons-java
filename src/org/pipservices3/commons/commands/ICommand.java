package org.pipservices3.commons.commands;

import java.util.*;

import org.pipservices3.commons.run.*;
import org.pipservices3.commons.validate.*;

/**
 * An interface for Commands, which are part of the Command design pattern. 
 * Each command wraps a method or function and allows to call them in uniform and safe manner.
 * 
 * @see Command
 * @see IExecutable
 * @see ICommandInterceptor
 * @see InterceptedCommand
 */
public interface ICommand extends IExecutable {
	/**
	 * Gets the command name.
	 * 
	 * @return the command name.
	 */
	String getName();

	/**
	 * Validates command arguments before execution using defined schema.
	 * 
	 * @param args the parameters (arguments) to validate.
	 * @return an array of ValidationResults.
	 * 
	 * @see Parameters
	 * @see ValidationResult
	 */
	List<ValidationResult> validate(Parameters args);
}
