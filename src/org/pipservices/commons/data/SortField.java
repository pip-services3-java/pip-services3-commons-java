package org.pipservices.commons.data;

public class SortField {
	private String _name;
	private boolean _ascending = true;
	
	public SortField() {}
	
	public SortField(String name, boolean ascending) {
		_name = name;
		_ascending = ascending;
	}
	
	public String getName() { return _name; }
	public void setName(String value) { _name = value; }
	
	public boolean isAscending() { return _ascending; }
	public void setAscending(boolean value) { _ascending = value; }
}
