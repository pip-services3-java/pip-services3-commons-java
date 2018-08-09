package org.pipservices.commons.validate;

import java.util.*;

import org.pipservices.commons.reflect.*;

public class OnlyOneExistsRule implements IValidationRule {
    private final String[] _properties;

    public OnlyOneExistsRule(String... properties) {
        _properties = properties;
    }

    public void validate(String path, Schema schema, Object value, List<ValidationResult> results) {
        String name = path != null ? path : "value";
        List<String> found = new ArrayList<String>();

        for (String property : _properties) {
            Object propertyValue = ObjectReader.getProperty(value, property);
            if (propertyValue != null)
                found.add(property);
        }

        if (found.size() == 0) {
            results.add(
                new ValidationResult(
                    path,
                    ValidationResultType.Error,
                    "VALUE_NULL",
                    name + " must have at least one property from " + _properties,
                    _properties,
                    null
                )
            );
        } else if (found.size() > 1) {
            results.add(
                new ValidationResult(
                    path,
                    ValidationResultType.Error,
                    "VALUE_ONLY_ONE",
                    name + " must have only one property from " + _properties,
                    _properties,
                    found.toArray()
                )
            );
        }
    }
}
