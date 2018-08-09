package org.pipservices.commons.run;

import org.pipservices.commons.errors.*;

public interface ICleanable {
	void clear(String correlationId) throws ApplicationException;
}
