package org.pipservices.commons.validate;

import java.util.*;

import org.pipservices.commons.reflect.*;

public class PropertiesComparisonRule implements IValidationRule {
    private String _property1;
    private String _property2;
    private String _operation;

    public PropertiesComparisonRule(String property1, String operation, String property2) {
        _property1 = property1;
        _operation = operation;
        _property2 = property2;
    }

    public void validate(String path, Schema schema, Object value, List<ValidationResult> results) {
        String name = path != null ? path : "value";
        Object value1 = ObjectReader.getProperty(value, _property1);
        Object value2 = ObjectReader.getProperty(value, _property2);

        if (!ObjectComparator.compare(value1, _operation, value2)) {
            results.add(
                new ValidationResult(
                    path,
                    ValidationResultType.Error,
                    "PROPERTIES_NOT_MATCH",
                    name + " must have " + _property1 + " " + _operation + " " + _property2,
                    value2,
                    value1
                )
            );
        }
    }
}
