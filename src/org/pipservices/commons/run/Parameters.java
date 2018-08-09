package org.pipservices.commons.run;

import java.util.*;

import org.pipservices.commons.config.*;
import org.pipservices.commons.convert.*;
import org.pipservices.commons.data.*;
import org.pipservices.commons.reflect.*;

import com.fasterxml.jackson.core.*;

/**
 * Parameters represent hierarchical map that uses simple keys and stores complex objects.
 * It allows hierarchical access to stored data using dot-notation.
 * 
 * All keys stored in the map are case-insensitive.
 */
public class Parameters extends AnyValueMap {
	private static final long serialVersionUID = 9145851165539523100L;

	public Parameters() {}

	public Parameters(Map<?, ?> map) {
		super(map);
	}

	@Override
	public Object get(String key) {
		if (key == null)
			return null;
		else if (key.indexOf('.') > 0)
			return RecursiveObjectReader.getProperty(this, key);
		else
			return super.get(key);
	}

	@Override
	public Object put(String key, Object value) {
		if (key == null)
			return null;
		else if (key.indexOf('.') > 0)
			RecursiveObjectWriter.setProperty(this, key, value);
		else
			super.put(key, value);
		return value;
    }

    public Parameters getAsNullableParameters(String key) {
        AnyValueMap value = getAsNullableMap(key);
    	return value != null ? new Parameters(value) : null;
    }

    public Parameters getAsParameters(String key) {
        AnyValueMap value = getAsMap(key);
    	return new Parameters(value);
    }

    public Parameters getAsParametersWithDefault(String key, Parameters defaultValue) {
        Parameters result = getAsNullableParameters(key);
    	return result != null ? result: defaultValue;
    }

	@Override
	public boolean containsKey(Object key) {
		return RecursiveObjectReader.hasProperty(this, key.toString());
	}
	
 	public Parameters override(Parameters parameters) {
        return override(parameters, false);
    }

 	public Parameters override(Parameters parameters, boolean recursive) {
 		Parameters result = new Parameters();
 		if (recursive) {
 			RecursiveObjectWriter.copyProperties(result, this);
 			RecursiveObjectWriter.copyProperties(result, parameters);
 		} else {
 			ObjectWriter.setProperties(result, this);
 			ObjectWriter.setProperties(result, parameters);
 		}
        return result;
    }

	public Parameters setDefaults(Parameters defaultParameters) {
        return setDefaults(defaultParameters, false);
	}

	public Parameters setDefaults(Parameters defaultParameters, boolean recursive) {
 		Parameters result = new Parameters();
 		if (recursive) {
 			RecursiveObjectWriter.copyProperties(result, defaultParameters);
 			RecursiveObjectWriter.copyProperties(result, this);
 		} else {
 			ObjectWriter.setProperties(result, defaultParameters);
 			ObjectWriter.setProperties(result, this);
 		}
        return result;
	}

    public void assignTo(Object value) {
        if (value == null || size() == 0) return;        
        RecursiveObjectWriter.copyProperties(value, this);
    }
        
    public Parameters pick(String... paths) {
    	Parameters result = new Parameters();
        for (String path : paths) {
            if (containsKey(path))
                result.put(path, get(path));
        }
        return result;
    }

    public Parameters omit(String... paths) {
    	Parameters result = new Parameters(this);        
        for (String path : paths)
            result.remove(path);
        return result;
    }
	
	public String toJson() throws JsonProcessingException {
		return JsonConverter.toJson(this);
	}
	
	public static Parameters fromTuples(Object... tuples) {
		AnyValueMap map = AnyValueMap.fromTuplesArray(tuples);
		return new Parameters(map);
	}
		
	public static Parameters mergeParams(Parameters... parameters) {
		AnyValueMap map = AnyValueMap.fromMaps(parameters);
		return new Parameters(map);
	}
	
	public static Parameters fromJson(String json) {
		Map<String, Object> map = JsonConverter.toNullableMap(json);
		return map != null ? new Parameters(map) : new Parameters();
	}
	
	public static Parameters fromConfig(ConfigParams config) {
		Parameters result = new Parameters();
		
		if (config == null || config.size() == 0)
			return result;
		
		for (Map.Entry<String, String> entry : config.entrySet()) {
			result.put(entry.getKey(), entry.getValue());
		}
		
		return result;
	}
}
