package org.pipservices.commons.data;

import java.util.*;

public class FilterParams extends StringValueMap {
	private static final long serialVersionUID = -5476176704133599595L;

	public FilterParams() {}
	
	public FilterParams(Map<?, ?> map) {
		append(map);
	}
		
	public static FilterParams fromTuples(Object... tuples) {
		StringValueMap map = StringValueMap.fromTuplesArray(tuples);
		return new FilterParams(map);
	}

	public static FilterParams fromString(String line) {
		StringValueMap map = StringValueMap.fromString(line);
		return new FilterParams(map);
	}
	
    public static FilterParams fromValue(Object value)
    {
        if (value instanceof FilterParams)
            return (FilterParams)value;

        AnyValueMap map = AnyValueMap.fromValue(value);
        return new FilterParams(map);
    }

}
