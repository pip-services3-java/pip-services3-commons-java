package org.pipservices.commons.random;

import java.util.*;

public class DataReferences {
	
	private Map<String, List> _data = new HashMap<String, List>();
	
	public DataReferences() { }
	
	public <T> List<T> getAs() {
		for( String key : _data.keySet() ){
			if( _data.get(key).getClass() == List.class ){
				return (List<T>) _data.values();
			}
		}
        return null;
    }
	
	public List get(String name)
    {
        List data = null;
        data = _data.get(name);
        return data;
    }

    public void set(String name, List data)
    {
    	_data.put(name, data);
    }
}
