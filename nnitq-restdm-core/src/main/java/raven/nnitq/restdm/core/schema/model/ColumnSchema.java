package raven.nnitq.restdm.core.schema.model;

import java.util.StringJoiner;

/**
 * @author Raven
 */
public class ColumnSchema {

    private String name;
    private String dataType;
    private boolean isPrimaryKey;
    // TODO 补充其它列属性

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(final String dataType) {
        this.dataType = dataType;
    }

    public boolean getIsPrimaryKey() {
        return isPrimaryKey;
    }

    public void setIsPrimaryKey(final boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ColumnSchema.class.getSimpleName() + "[", "]")
            .add("name='" + name + "'")
            .add("dataType='" + dataType + "'")
            .add("isPrimaryKey=" + isPrimaryKey)
            .toString();
    }

}
