package org.pipservices.commons.validate;

import java.util.*;

public class ExcludedRule implements IValidationRule {
    public Object[] _values;

    public ExcludedRule(Object... values) {
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

        if (found) {
            results.add(
                new ValidationResult(
                    path,
                    ValidationResultType.Error,
                    "VALUE_INCLUDED",
                    name + " must not be one of " + _values,
                    _values,
                    value
                )
            );
        }
    }
}
