package org.pipservices.commons.validate;

import java.util.*;

public class AndRule implements IValidationRule {
    private IValidationRule[] _rules;

    public AndRule(IValidationRule... rules) {
        _rules = rules;
    }
    
    public void validate(String path, Schema schema, Object value, List<ValidationResult> results) {
        if (_rules == null) return;

        for (IValidationRule rule : _rules)
            rule.validate(path, schema, value, results);
    }

}
