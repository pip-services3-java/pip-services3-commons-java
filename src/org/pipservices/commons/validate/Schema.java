package org.pipservices.commons.validate;

import java.util.*;

import org.pipservices.commons.reflect.ObjectReader;
import org.pipservices.commons.reflect.TypeMatcher;

public class Schema {
    private boolean _required = false;
    private List<IValidationRule> _rules;

    public Schema() { }

    public Schema(boolean required, List<IValidationRule> rules) {
        _required = required;
        _rules = rules;
    }

    public boolean isRequired() { return _required; }
    public void setRequired(boolean value) { _required = value; }

    public List<IValidationRule> getRules() { return _rules; }
    public void setRules(List<IValidationRule> value) { _rules = value; } 

    public Schema makeRequired() {
        _required = true;
        return this;
    }

    public Schema makeOptional() {
        _required = false;
        return this;
    }

    public Schema withRule(IValidationRule rule) {
        _rules = _rules != null ? _rules : new ArrayList<IValidationRule>();
        _rules.add(rule);
        return this;
    }

    protected void performValidation(String path, Object value, List<ValidationResult> results) {
        String name = path != null ? path : "value";

        if (value == null) {
            // Check for required values
            if (_required)
                results.add(
                    new ValidationResult(
                        path,
                        ValidationResultType.Error,
                        "VALUE_IS_NULL",
                        name + " cannot be null",
                        "NOT NULL",
                        null
                    )
                );
        } else {
            value = ObjectReader.getValue(value);

            // Check validation rules
            if (_rules != null) {
                for (IValidationRule rule : _rules)
                    rule.validate(path, this, value, results);
            }
        }
    }

    protected void performTypeValidation(String path, Object type, Object value, List<ValidationResult> results) {
        // If type it not defined then skip
        if (type == null) return;

        // Perform validation against schema
        if (type instanceof Schema) {
            Schema schema = (Schema)type;
            schema.performValidation(path, value, results);
            return;
        }

        // If value is null then skip
        value = ObjectReader.getValue(value);
        if (value == null) return;

        String name = path != null ? path : "value";
        Class<?> valueType = value.getClass();

        // Match types
        if (TypeMatcher.matchType(type, valueType))
    		return;
        
        // Generate type mismatch error
        results.add(
            new ValidationResult(
                path,
                ValidationResultType.Error,
                "TYPE_MISMATCH",
                name + " type must be " + type + " but found " + valueType,
                type,
                valueType
            )
        );
    }

    public List<ValidationResult> validate(Object value) {
        List<ValidationResult> results = new ArrayList<ValidationResult>();
        performValidation("", value, results);
        return results;
    }

    public void validateAndThrowException(String correlationId, Object value, boolean strict) throws ValidationException {
        List<ValidationResult> results = validate(value);
        ValidationException.throwExceptionIfNeeded(correlationId, results, strict);
    }

    public void validateAndThrowException(String correlationId, Object value) throws ValidationException {
    	validateAndThrowException(correlationId, value, false);
    }
}
