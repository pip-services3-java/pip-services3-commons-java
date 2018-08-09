package org.pipservices.commons.convert;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;

public class JsonConverter {
	private static ObjectMapper _mapper = new ObjectMapper();
	private static TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {};
	
	static {
		_mapper.findAndRegisterModules();
		//_mapper.registerModule(new JavaTimeModule());
		_mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}
	
	public static <T> T fromJson(Class<T> type, String value) throws JsonMappingException, JsonParseException, IOException {
		if (value == null) return null;
		return _mapper.readValue(value, type);
	}
	
	public static String toJson(Object value) throws JsonProcessingException {
		if (value == null) return null;
		return _mapper.writeValueAsString(value);
	}
    
	public static Map<String, Object> toNullableMap(String value) {
    	if (value == null) return null;

    	try {
			Map<String, Object> map = _mapper.readValue((String)value, typeRef);
			return RecursiveMapConverter.toNullableMap(map);
		} catch (Exception ex) {
			return null;
		}
    }

	public static Map<String, Object> toMap(String value) {
    	Map<String, Object> result = toNullableMap(value);
    	return result != null ? result : new HashMap<String, Object>();
    }

	public static Map<String, Object> toMapWithDefault(String value, Map<String, Object> defaultValue) {
    	Map<String, Object> result = toNullableMap(value);
    	return result != null ? result : defaultValue;
    }
	
}
