package org.pipservices.commons.errors;

/**
 * Defines broad categories of application errors.
 */
public class ErrorCategory {
	/**
	 * Unknown or unexpected errors
	 */
	public final static String Unknown = "Unknown";

	/**
	 * Internal errors caused by programming mistakes
	 */
	public final static String Internal = "Internal";

	/**
	 * Errors related to mistakes in user-defined configuration
	 */
	public final static String Misconfiguration = "Misconfiguration";
	
	/**
	 * Errors related to operations called in wrong component state.
	 * For instance, business calls when component is not ready
	 */
	public final static String InvalidState = "InvalidState";
	
	/**
	 * Errors happened during connection to remote services.
	 * They can be related to misconfiguration, network issues
	 * or remote service itself 
	 */
	public final static String NoResponse = "NoResponse";

    /**
     * Errors returned by remote services or network
     * during call attempts
     */
	public final static String FailedInvocation = "FailedInvocation";

	/**
	 * Errors in read/write file operations
	 */
	public final static String FileError = "FileError";

	/**
	 * Errors due to improper user requests, like
	 * missing or wrong parameters 
	 */
	public final static String BadRequest = "BadRequest";
	
	/**
	 * Access errors caused by missing user identity
	 * or security permissions
	 */
	public final static String Unauthorized = "Unauthorized";

    /**
     * Error caused by attempt to access missing object
     */
	public final static String NotFound = "NotFound";
	
	/**
	 * Errors raised by conflict in object versions
	 * posted by user and stored on server.
	 */
	public final static String Conflict = "Conflict";	
	
	/**
	 * Errors caused by calls to unsupported 
	 * or not yet implemented functionality
	 */
	public final static String Unsupported = "Unsupported";
}
