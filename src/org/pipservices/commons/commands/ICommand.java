package org.pipservices.commons.commands;

import java.util.*;

import org.pipservices.commons.run.*;
import org.pipservices.commons.validate.*;

/**
 * Interface for commands that execute functional operations.
 */
public interface ICommand extends IExecutable {
	/**
	 * Gets the command name.
	 * @return the command name
	 */
	String getName();
		
	/**
	 * Performs validation of the command arguments.
	 * @param args command arguments
	 * @return a list of validation results
	 */
	List<ValidationResult> validate(Parameters args);
}
