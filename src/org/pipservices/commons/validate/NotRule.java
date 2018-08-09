package org.pipservices.commons.validate;

import java.util.*;

public class NotRule implements IValidationRule {
    private IValidationRule _rule;

    public NotRule(IValidationRule rule) {
        _rule = rule;
    }

    public void validate(String path, Schema schema, Object value, List<ValidationResult> results) {
        if (_rule == null)
        	return;

        String name = path != null ? path : "value";
        List<ValidationResult> localResults = new ArrayList<ValidationResult>();
        _rule.validate(path, schema, value, localResults);
        if (localResults.size() > 0)
            return;

        results.add(
            new ValidationResult(
                path,
                ValidationResultType.Error,
                "NOT_FAILED",
                "Negative check for " + name + " failed",
                null,
                null
            )
        );
    }
}
