package org.pipservices3.commons.data;

import java.util.*;

/**
 * Defines a field name and order used to sort query results.
 * <p>
 * ### Example ###
 * <pre>
 * {@code
 * FilterParams filter = FilterParams.fromTuples("type", "Type1");
 * PagingParams paging = new PagingParams(0, 100);
 * SortingParams sorting = new SortingParams(new SortField("create_time", true));
 *
 * myDataClient.getDataByFilter(filter, paging, sorting);
 * }
 * </pre>
 *
 * @see SortField
 */
public class SortParams extends ArrayList<SortField> {
    private static final long serialVersionUID = -4036913032335476957L;

    public SortParams() {
    }

    /**
     * Creates a new instance and initializes it with specified sort fields.
     *
     * @param fields a list of fields to sort by.
     */
    public SortParams(Iterable<SortField> fields) {
        if (fields != null) {
            for (SortField field : fields)
                add(field);
        }
    }
}
