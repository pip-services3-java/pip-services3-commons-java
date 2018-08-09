package org.pipservices.commons.data;

import java.util.*;

public class SortParams extends ArrayList<SortField> {
	private static final long serialVersionUID = -4036913032335476957L;

	public SortParams() {}
	
	public SortParams(Iterable<SortField> fields) {
		if (fields != null) {
			for (SortField field : fields)
				add(field);
		}
	}
}
