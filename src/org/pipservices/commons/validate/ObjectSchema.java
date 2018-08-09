package org.pipservices.commons.validate;

import java.util.*;

import org.pipservices.commons.reflect.*;

public class ObjectSchema extends Schema {
    private List<PropertySchema> _properties;
    private boolean _allowUndefined = false;

    public ObjectSchema() { }

    public List<PropertySchema> getProperties() { return _properties; }
    public void setProperties(List<PropertySchema> value) { _properties = value; }

    public boolean isUndefinedAllowed() { return _allowUndefined; }
    public void setUndefinedAllower(boolean value) { _allowUndefined = value; }

    public ObjectSchema allowUndefined(boolean value) {
        _allowUndefined = value;
        return this;
    }

    public ObjectSchema withProperty(PropertySchema schema) {
        _properties = _properties != null ? _properties : new ArrayList<PropertySchema>();
        _properties.add(schema);
        return this;
    }

    public ObjectSchema withRequiredProperty(String name, Object type, IValidationRule... rules) {
        _properties = _properties != null ? _properties : new ArrayList<PropertySchema>();
        PropertySchema schema = new PropertySchema(name, type);
        schema.setRules(Arrays.asList(rules));
        schema.makeRequired();
        return withProperty(schema);
    }

    public ObjectSchema withOptionalProperty(String name, Object type, IValidationRule... rules) {
        _properties = _properties != null ? _properties : new ArrayList<PropertySchema>();
        PropertySchema schema = new PropertySchema(name, type);
        schema.setRules(Arrays.asList(rules));
        schema.makeOptional();
        return withProperty(schema);
    }

    @Override
    protected void performValidation(String path, Object value, List<ValidationResult> results) {
        super.performValidation(path, value, results);

        if (value == null) return;

        String name = path != null ? path : "value";
        Map<String, Object> properties = ObjectReader.getProperties(value);

        // Process defined properties
        if (_properties != null) {
            for (PropertySchema propertySchema : _properties) {
                String processedName = null;

                for (Map.Entry<String, Object> entry : properties.entrySet()) {
                    String propertyName = entry.getKey();
                    Object propertyValue = entry.getValue();
                    // Find properties case insensitive
                    if (propertyName.equalsIgnoreCase(propertySchema.getName())) {
                        propertySchema.performValidation(path, propertyValue, results);
                        processedName = propertyName;
                        break;
                    }
                }

                if (processedName == null)
                    propertySchema.performValidation(path, null, results);
                else
                    properties.remove(processedName);
            }
        }

        // Process unexpected properties
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            String propertyPath = path == null || path.length() == 0
                ? entry.getKey() : path + "." + entry.getKey();

            results.add(
                new ValidationResult(
                    propertyPath,
                    ValidationResultType.Warning,
                    "UNEXPECTED_PROPERTY",
                    name + " contains unexpected property " + entry.getKey(),
                    null,
                    entry.getKey()
                )
            );
        }
    }
}
