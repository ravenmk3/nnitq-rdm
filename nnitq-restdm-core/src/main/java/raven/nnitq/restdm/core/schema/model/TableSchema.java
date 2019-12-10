package raven.nnitq.restdm.core.schema.model;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author Raven
 */
public class TableSchema {

    private String name;
    private List<ColumnSchema> columns;
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<ColumnSchema> getColumns() {
        return columns;
    }

    public void setColumns(final List<ColumnSchema> columns) {
        this.columns = columns;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TableSchema.class.getSimpleName() + "[", "]")
            .add("name='" + name + "'")
            .add("columns=" + columns)
            .add("comment='" + comment + "'")
            .toString();
    }

}
