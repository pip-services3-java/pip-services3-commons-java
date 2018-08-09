package org.pipservices.commons.commands;

import java.util.*;

import org.pipservices.commons.data.*;
import org.pipservices.commons.errors.*;
import org.pipservices.commons.run.*;
import org.pipservices.commons.validate.*;

/**
 * Handles command registration and execution.
 * Enables intercepters to control or modify command behavior 
 */
public class CommandSet {
	private List<ICommand> _commands = new ArrayList<ICommand>();
	private Map<String, ICommand> _commandsByName = new HashMap<String, ICommand>();
	private List<IEvent> _events = new ArrayList<IEvent>();
    private Map<String, IEvent> _eventsByName = new HashMap<String, IEvent>();
	private List<ICommandInterceptor> _intercepters = new ArrayList<ICommandInterceptor>();
	
	/**
	 * Create a command set instance.
	 */
	public CommandSet() {}
	
	/**
	 * Get all supported commands
	 * @return a list with all commands supported by the component. 
	 */
	public List<ICommand> getCommands() {
		return _commands;
	}
	
	/**
	 * Get all supported events
	 * @return a list with all supported events by the component.
	 */
	public List<IEvent> getEvents() {
		return _events;
	}
	
	/**
	 * Find a specific command by its name.
	 * @param command the command name.
	 * @return an object with command name.
	 */
	public ICommand findCommand(String command) {
		return _commandsByName.get(command);
	}

	/**
	 * Find a specific event by its name.
	 * @param event the event name.
	 * @return an object with command name.
	 */
	public IEvent findEvent(String event) {
		return _eventsByName.get(event);
	}

	/**
	 * Builds execution chain including all intercepters
	 * and the specified command.
	 * @param command the command to build a chain.
	 */
	private void buildCommandChain(ICommand command) {
		ICommand next = command;
		for (int i = _intercepters.size() - 1; i >= 0; i--)
			next = new InterceptedCommand(_intercepters.get(i), next);

		_commandsByName.put(next.getName(), next);
	}

	/**
	 * Rebuilds execution chain for all registered commands.
	 * This method is typically called when intercepters are changed.
	 * Because of that it is more efficient to register intercepters
	 * before registering commands (typically it will be done in abstract classes).
	 * However, that performance penalty will be only once during creation time. 
	 */
	private void rebuildAllCommandChains() {
		_commandsByName.clear();
		for (ICommand command : _commands)
			buildCommandChain(command);
	}
	
	/**
	 * Adds a command to the command set.
	 * @param command a command instance to be added
	 */
	public void addCommand(ICommand command) {
		_commands.add(command);
		buildCommandChain(command);
	}

	/**
	 * Adds a list of commands to the command set
	 * @param commands a list of commands to be added
	 */
	public void addCommands(List<ICommand> commands) {
		for (ICommand command : commands)
			addCommand(command);
	}

	/**
	 * Adds an event to the command set.
	 * @param event an event instance to be added
	 */
    public void addEvent(IEvent event) {
        _events.add(event);
        _eventsByName.put(event.getName(), event);
    }

    /**
     * Adds a list of event to the command set
     * @param events a list of events to be added
     */
    public void addEvents(List<IEvent> events) {
        for (IEvent event : events) {
            addEvent(event);
        }
    }

    /**
	 * Adds commands and events from another command set to this one
	 * @param commandSet a commands set to add commands from
	 */
	public void addCommandSet(CommandSet commandSet) {
		for (ICommand command : commandSet.getCommands()) {
			addCommand(command);
		}
		
		for (IEvent event : commandSet.getEvents()) {
			addEvent(event);
		}
	}
	
	/**
	 * Adds intercepter to the command set.
	 * @param intercepter an intercepter instance to be added.
	 */
	public void addIntercepter(ICommandInterceptor intercepter) {
		_intercepters.add(intercepter);
		rebuildAllCommandChains();
	}
	
	/**
	 * Execute command by its name with specified arguments.
	 * @param correlationId a unique correlation/transaction id
	 * @param command the command name.
	 * @param args a list of command arguments.
	 * @return the execution result.
	 * @throws MicroserviceError when execution fails for any reason.
	 */
	public Object execute(String correlationId, String command, Parameters args) throws ApplicationException {
		// Get command and throw error if it doesn't exist
		ICommand cref = findCommand(command);
		if (cref == null) {
			throw new BadRequestException(
				correlationId, 
				"CMD_NOT_FOUND", 
				"Requested command does not exist"
			)
			.withDetails("command", command);
		}

		// Generate correlationId if it doesn't exist
		// Use short ids for now
		if (correlationId == null)
			correlationId = IdGenerator.nextShort();
		
		// Validate command arguments before execution and throw the 1st found error
		List<ValidationResult> results = cref.validate(args);
		ValidationException.throwExceptionIfNeeded(correlationId, results, false);
				
		// Execute the command.
		return cref.execute(correlationId, args);
	}
	
	/**
	 * Validates command arguments.
	 * @param command the command name.
	 * @param args a list of command arguments.
	 * @return a list with validation results
	 */
	public List<ValidationResult> validate(String command, Parameters args) {
		ICommand cref = findCommand(command);
		if (cref == null) {
			List<ValidationResult> results = new ArrayList<ValidationResult>();
			results.add(
				new ValidationResult(null, ValidationResultType.Error,
					"CMD_NOT_FOUND", "Requested command does not exist", null, null)
			);
			return results;
		}
		return cref.validate(args);
	}

	/**
	 * Adds listener to all events.
	 * @param listener a listener to be added
	 */
    public void addListener(IEventListener listener) {
        for (IEvent event : _events) {
            event.addListener(listener);
        }
    }

    /**
     * Remove listener to all events.
     * @param listener a listener to be removed
     */
    public void removeListener(IEventListener listener) {
        for (IEvent event : _events) {
            event.removeListener(listener);
        }
    }

    /**
     * Notifies all listeners about the event.
     * @param correlationId a unique correlation/transaction id
     * @param event an event name
     * @param value an event value
     */
    public void notify(String correlationId, String event, Parameters value) throws ApplicationException {
        IEvent e = findEvent(event);
        if (e != null) {
            e.notify(correlationId, value);
        }
    }
	
}
