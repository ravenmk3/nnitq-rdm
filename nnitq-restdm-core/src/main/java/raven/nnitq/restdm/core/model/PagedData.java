package raven.nnitq.restdm.core.model;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author Raven
 */
public class PagedData {

    private int total;
    private int offset;
    private int limit;
    private List<Map<String, Object>> items;

    public int getTotal() {
        return total;
    }

    public void setTotal(final int total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(final int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(final int limit) {
        this.limit = limit;
    }

    public List<Map<String, Object>> getItems() {
        return items;
    }

    public void setItems(final List<Map<String, Object>> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PagedData.class.getSimpleName() + "[", "]")
            .add("total=" + total)
            .add("offset=" + offset)
            .add("limit=" + limit)
            .add("items=" + items)
            .toString();
    }

}
