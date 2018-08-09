package org.pipservices.commons.data;

import org.pipservices.commons.convert.*;

public class PagingParams {
	private Long _skip;
	private Long _take;
	private boolean _total;
	
    public PagingParams() { }

    public PagingParams(Object skip, Object take, Object total) {
        _skip = LongConverter.toNullableLong(skip);
        _take = LongConverter.toNullableLong(take);
        _total = BooleanConverter.toBooleanWithDefault(total, false);
    }
    
    public Long getSkip() { 
    	return _skip; 
	}
    
    public long getSkip(long minSkip) {
    	if (_skip == null) return minSkip;
    	if (_skip < minSkip) return minSkip;
    	return _skip; 
	}

    public Long getTake() {
    	return _take; 
	}

    public void setSkip(long value) {
    	_skip = value;
    }

    public long getTake(long maxTake) {
    	if (_take == null) return maxTake;
    	if (_take < 0) return 0;
    	if (_take > maxTake) return maxTake;
    	return _take; 
	}

    public void setTake(long value) {
    	_take = value;
    }
    
    public boolean hasTotal() { 
    	return _total; 
	}

    public void setTotal(boolean value) {
    	_total = value;
    }

	public static PagingParams fromValue(Object value) {
		if (value instanceof PagingParams)
			return (PagingParams)value;

		AnyValueMap map = AnyValueMap.fromValue(value);
		return PagingParams.fromMap(map);
	}
	
	public static PagingParams fromTuples(Object... tuples) {
		AnyValueMap map = AnyValueMap.fromTuples(tuples);
		return PagingParams.fromMap(map);
	}

	public static PagingParams fromMap(AnyValueMap map) {
		Long skip = map.getAsNullableLong("skip");
		Long take = map.getAsNullableLong("take");
        boolean total = map.getAsBooleanWithDefault("total", true);
		return new PagingParams(skip, take, total);
	}

	public static PagingParams fromMap(StringValueMap map) {
		Long skip = map.getAsNullableLong("skip");
		Long take = map.getAsNullableLong("take");
		boolean total = map.getAsBooleanWithDefault("total", true);
		return new PagingParams(skip, take, total);
	}
}
