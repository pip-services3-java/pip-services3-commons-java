package org.pipservices.commons.validate;

import java.util.*;

public class ValueComparisonRule implements IValidationRule {
    private String _operation;
    private Object _value;

    public ValueComparisonRule(String operation, Object value) {
        _operation = operation;
        _value = value;
    }

    public void validate(String path, Schema schema, Object value, List<ValidationResult> results) {
        String name = path != null ? path : "value";

        if (!ObjectComparator.compare(value, _operation, _value)) {
            results.add(
                new ValidationResult(
                    path,
                    ValidationResultType.Error,
                    "BAD_VALUE",
                    name + " must " + _operation + " " + _value + " but found " + value,
                    _operation + " " + _value,
                    value
                )
            );
        }
    }
}
