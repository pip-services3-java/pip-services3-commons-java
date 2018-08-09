package org.pipservices.commons.validate;

import java.util.*;

import org.pipservices.commons.reflect.*;

public class AtLeastOneExistsRule implements IValidationRule {
    private String[] _properties;

    public AtLeastOneExistsRule(String... properties) {
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
        }
    }
}
