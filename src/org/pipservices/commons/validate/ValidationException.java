package org.pipservices.commons.validate;

import java.util.*;

import org.pipservices.commons.errors.*;

public class ValidationException extends BadRequestException {
	private static final long serialVersionUID = -1459801864235223845L;
	
	public ValidationException(String correlationId, List<ValidationResult> results) {
		this(correlationId, composeMessage(results));
		this.withDetails("results", results);
	}

	public ValidationException(String correlationId, String message) {
		super(correlationId, "INVALID_DATA", message);		
	}

    public static String composeMessage(List<ValidationResult> results) {
        StringBuilder builder = new StringBuilder();
        builder.append("Validation failed");

        if (results != null && results.size() > 0) {
            boolean first = true;
            for (ValidationResult result : results) {
                if (result.getType() != ValidationResultType.Information) {
                    if (!first) builder.append(": ");
                    else builder.append(", ");
                    builder.append(result.getMessage());
                    first = false;
                }
            }
        }

        return builder.toString();
    }

    public static ValidationException fromResults(
    	String correlationId, List<ValidationResult> results, boolean strict
	) throws ValidationException {
        boolean hasErrors = false;
        for (ValidationResult result : results) {
        	if (result.getType() == ValidationResultType.Error)
        		hasErrors = true;
        	if (strict && result.getType() == ValidationResultType.Warning)
        		hasErrors = true;
        }

        return hasErrors ? new ValidationException(correlationId, results) : null;
    }

    public static void throwExceptionIfNeeded(
    	String correlationId, List<ValidationResult> results, boolean strict
	) throws ValidationException {
    	ValidationException ex = fromResults(correlationId, results, strict);
    	if (ex != null) throw ex;
    }
    
}
