package org.pipservices.commons.errors;

public class ApplicationExceptionFactory {
	
    public ApplicationException create(ErrorDescription description) {
    	if (description == null)
    		throw new NullPointerException("Description cannot be null");
    	
    	ApplicationException error = null;
    	String category = description.getCategory();
    	String code = description.getCode();
    	String message = description.getMessage();
    	String correlationId = description.getCorrelationId();
    	
    	// Create well-known exception type based on error category
    	if (ErrorCategory.Unknown.equals(category))
    		error = new UnknownException(correlationId, code, message);
    	else if (ErrorCategory.Internal.equals(category))
    		error = new InternalException(correlationId, code, message);
    	else if (ErrorCategory.Misconfiguration.equals(category))
    		error = new ConfigException(correlationId, code, message);
    	else if (ErrorCategory.NoResponse.equals(category))
    		error = new ConnectionException(correlationId, code, message);
    	else if (ErrorCategory.FailedInvocation.equals(category))
    		error = new InvocationException(correlationId, code, message);
    	else if (ErrorCategory.FileError.equals(category))
    		error = new FileException(correlationId, code, message);
    	else if (ErrorCategory.BadRequest.equals(category))
    		error = new BadRequestException(correlationId, code, message);
    	else if (ErrorCategory.Unauthorized.equals(category))
    		error = new UnauthorizedException(correlationId, code, message);
    	else if (ErrorCategory.Conflict.equals(category))
    		error = new ConflictException(correlationId, code, message);
    	else if (ErrorCategory.NotFound.equals(category))
    		error = new NotFoundException(correlationId, code, message);
    	else if (ErrorCategory.InvalidState.equals(category))
    		error = new InvalidStateException(correlationId, code, message);
    	else if (ErrorCategory.Unsupported.equals(category))
    		error = new UnsupportedException(correlationId, code, message);
    	else {
    		error = new UnknownException();
    		error.setCategory(category);
    		error.setStatus(description.getStatus());
    	}
    	
    	// Fill error with details
    	error.setDetails(description.getDetails());
    	error.setCauseString(description.getCause());
    	error.setStackTraceString(description.getStackTrace());
    	
    	return error;
    }

}
