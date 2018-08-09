package org.pipservices.commons.validate;

import java.util.*;

public class OrRule implements IValidationRule {
    private IValidationRule[] _rules;

    public OrRule(IValidationRule... rules) {
        _rules = rules;
    }

    public void validate(String path, Schema schema, Object value, List<ValidationResult> results) {
        if (_rules == null || _rules.length == 0)
        	return;

        List<ValidationResult> localResults = new ArrayList<ValidationResult>();

        for (IValidationRule rule : _rules) {
        	int resultsCount = localResults.size();
            rule.validate(path, schema, value, localResults);
        	if (resultsCount == localResults.size())
        		return;
        }

        results.addAll(localResults);
    }
}
