package org.pipservices.commons.validate;

import java.util.*;

public class IncludedRule implements IValidationRule {
    public Object[] _values;

    public IncludedRule(Object... values) {
        _values = values;
    }

    public void validate(String path, Schema schema, Object value, List<ValidationResult> results) {
        String name = path != null ? path : "value";
        boolean found = false;

        for (Object thisValue : _values) {
            if (thisValue != null && thisValue.equals(value)) {
                found = true;
                break;
            }
        }

        if (!found) {
            results.add(
                new ValidationResult(
                    path,
                    ValidationResultType.Error,
                    "VALUE_NOT_INCLUDED",
                    name + " must be one of " + _values,
                    _values,
                    value
                )
            );
        }
    }
}
