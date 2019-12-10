package raven.nnitq.restdm.core.sql.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import raven.nnitq.restdm.core.sql.ResultSetMapper;

/**
 * @author Raven
 */
public class ListAndMapResultSetMapper implements ResultSetMapper<List<Map<String, Object>>> {

    public static final ListAndMapResultSetMapper INSTANCE = new ListAndMapResultSetMapper();

    @Override
    public List<Map<String, Object>> map(final ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> items = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        while (resultSet.next()) {
            Map<String, Object> item = new HashMap<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                item.put(metaData.getColumnName(i), resultSet.getObject(i));
            }
            items.add(item);
        }
        return items;
    }

}
