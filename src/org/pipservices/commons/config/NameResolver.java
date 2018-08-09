package org.pipservices.commons.config;

import org.pipservices.commons.refer.*;

public class NameResolver {
	
    public static String resolve(ConfigParams config, String defaultName) {
        // If name is not defined get is from name property
        String name = config.getAsNullableString("name");
        name = name != null ? name : config.getAsNullableString("id");

        // Or get name from descriptor
        if (name == null) {
            String descriptorStr = config.getAsNullableString("descriptor");
            try {
	            Descriptor descriptor = Descriptor.fromString(descriptorStr);
	            name = descriptor != null ? descriptor.getName() : null;
            } catch (Exception ex) {
            	// Ignore...
            }
        }

        return name != null ? name : defaultName;
    }

    public static String resolve(ConfigParams config) {
    	return resolve(config, null);
    }
}
