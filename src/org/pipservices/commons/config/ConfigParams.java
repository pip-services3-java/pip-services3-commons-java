package org.pipservices.commons.config;

import java.util.*;

import org.pipservices.commons.data.*;
import org.pipservices.commons.reflect.*;

/**
 * Map with configuration parameters that use complex keys with dot notation and simple string values.
 * 
 * Example of values, stored in the configuration parameters:
 * <ul>
 * <li>Section-1.Subsection-1-1.Key-1-1-1=123</li>
 * <li>Section-1.Subsection-1-2.Key-1-2-1="ABC"</li>
 * <li>Section-2.Subsection-1.Key-2-1-1="2016-09-16T00:00:00.00Z"</li>
 * </ul>
 *  
 * Configuration parameters support getting and adding sections from the map.
 * 
 * Also, configuration parameters may come in a form of parameterized string:
 * Key1=123;Key2=ABC;Key3=2016-09-16T00:00:00.00Z
 * 
 * All keys stored in the map are case-insensitive.
 */
public class ConfigParams extends StringValueMap {
	private static final long serialVersionUID = 671946442626850877L;

	public ConfigParams() {}

	public ConfigParams(Map<?, ?> values) {
		super(values);
	}

	public List<String> getSectionNames() {
		List<String> sections = new ArrayList<String>();
		
		for (Map.Entry<String, String> entry : this.entrySet()) {
			String key = entry.getKey();
			int pos = key.indexOf('.');
			if (pos > 0)
				key = key.substring(0, pos);

			// Perform case sensitive search
			boolean found = false;
			for (String section : sections) {
				if (section.equalsIgnoreCase(key)) {
					found = true;
					break;
				}
			}
				
			if (!found)
				sections.add(key);
		}
		
		return sections;
	}
	
	public ConfigParams getSection(String section) {
		ConfigParams result = new ConfigParams();
		String prefix = section + ".";
		
		for (Map.Entry<String, String> entry : this.entrySet()) {
			String key = entry.getKey();
			
			// Prevents exception on the next line
			if (key.length() < prefix.length())
				continue;
			
			// Perform case sensitive match
			String keyPrefix = key.substring(0, prefix.length());
			if (keyPrefix.equalsIgnoreCase(prefix)) {
				key = key.substring(prefix.length());
				result.put(key, entry.getValue());
			}
		}
		
		return result;
	}
		
	public void addSection(String section, ConfigParams sectionParams) {
		if (section == null)
			throw new NullPointerException("Section name cannot be null");

		
		if (sectionParams != null) {
			for (Map.Entry<String, String> entry : sectionParams.entrySet()) {
				String key = entry.getKey();
				
				if (key.length() > 0 && section.length() > 0)
					key = section + "." + key;
				else if (key.length() == 0)
					key = section;

				String value = entry.getValue();
				
				put(key, value);
			}
		}
	}
	
	public ConfigParams override(ConfigParams configParams) {
		StringValueMap map = StringValueMap.fromMaps(this, configParams);
		return new ConfigParams(map);
	}
	
	public ConfigParams setDefaults(ConfigParams defaultConfigParams) {
		StringValueMap map = StringValueMap.fromMaps(defaultConfigParams, this);
		return new ConfigParams(map);
	}

	public static ConfigParams fromValue(Object value) {
		Map<String, Object> map = RecursiveObjectReader.getProperties(value);
		return new ConfigParams(map);
	}
	
	public static ConfigParams fromTuples(Object... tuples) {
		StringValueMap map = StringValueMap.fromTuplesArray(tuples);
		return new ConfigParams(map);
	}
	
	public static ConfigParams fromString(String line) {
		StringValueMap map = StringValueMap.fromString(line);
		return new ConfigParams(map);
	}
	
	public static ConfigParams mergeConfigs(ConfigParams... configs) {
		StringValueMap map = StringValueMap.fromMaps(configs);
		return new ConfigParams(map);
	}
}
