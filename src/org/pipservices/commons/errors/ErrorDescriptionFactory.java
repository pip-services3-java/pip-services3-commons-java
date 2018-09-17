package org.pipservices.commons.errors;

/**
 * Factory to create serializeable ErrorDescription from ApplicationException
 * or from arbitrary errors.
 * 
 * The ErrorDescriptions are used to pass errors through the wire between microservices
 * implemented in different languages. They allow to restore exceptions on the receiving side
 * close to the original type and preserve additional information.
 * 
 * @see ErrorDescription
 * @see ApplicationException
 */
public class ErrorDescriptionFactory {

	/**
     * Creates a serializable ErrorDescription from error object.
     * 
	 * @param ex  	an error object
	 * @return a serializeable ErrorDescription object that describes the error.
	 */
	public static ErrorDescription create(ApplicationException ex) {
    	ErrorDescription description = new ErrorDescription();
    	description.setCategory(ex.getCategory());
    	description.setStatus(ex.getStatus());
    	description.setCode(ex.getCode());
    	description.setMessage(ex.getMessage());
    	description.setDetails(ex.getDetails());
    	description.setCorrelationId(ex.getCorrelationId());    	
    	description.setCause(ex.getCauseString());    	
    	description.setStackTrace(ex.getStackTraceString());    	
    	return description;
    }

	/**
     * Creates a serializable ErrorDescription from throwable object with unknown error category. 
     * 
     * @param ex				an error object
	 * @param correlationId  	(optional) a unique transaction id to trace execution through call chain.
	 * @return a serializeable ErrorDescription object that describes the error.
	 */
    public static ErrorDescription create(Throwable ex, String correlationId) {
    	ErrorDescription description = new ErrorDescription();
    	description.setType(ex.getClass().getCanonicalName());
    	description.setCategory(ErrorCategory.Unknown);
    	description.setStatus(500);
    	description.setCode("Unknown");
    	description.setMessage(ex.getMessage());
    	
    	Throwable t = ex.getCause();    	
    	description.setCause(t != null ? t.toString() : null);
    	
    	StackTraceElement[] ste = ex.getStackTrace();
    	StringBuilder builder = new StringBuilder();
    	if (ste != null) {
    		for (int i = 0; i < ste.length; i++) {
    			if (builder.length() > 0)
    				builder.append(" ");
				builder.append(ste[i].toString());
    		}
    	}    			    	
    	description.setStackTrace(builder.toString()); 	
    	description.setCorrelationId(correlationId);
    	
    	return description;
    }    

}
