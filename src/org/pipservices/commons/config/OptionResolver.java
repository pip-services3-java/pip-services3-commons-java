package org.pipservices.commons.config;

public class OptionResolver {	
    public static ConfigParams resolve(ConfigParams config, boolean configAsDefault) {
    	ConfigParams options = config.getSection("options");
    	
    	if (options.size() == 0 && configAsDefault)
    		options = config;
    	
    	return options;
    }

    public static ConfigParams resolve(ConfigParams config) {
    	return resolve(config, true);
    }
}
