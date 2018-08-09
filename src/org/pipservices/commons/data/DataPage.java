package org.pipservices.commons.data;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

public class DataPage<T> {
	private Long _total;
	private List<T> _data;
	
	public DataPage() {}
	
	public DataPage(List<T> data) {
		this(data, null);
	}

	public DataPage(List<T> data, Long total) {
		_total = total;
		_data = data;
	}
	
    @JsonProperty("total")
	public Long getTotal() { return _total; }
	public void setTotal(Long value) { _total = value; }
	
    @JsonProperty("data")
	public List<T> getData() { return _data; }
	public void setData(List<T> value) { _data = value; }
}
