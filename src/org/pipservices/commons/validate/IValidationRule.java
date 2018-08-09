package org.pipservices.commons.validate;

import java.util.*;

public interface IValidationRule {
    void validate(String path, Schema schema, Object value, List<ValidationResult> results);
}
