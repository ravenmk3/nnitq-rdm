package raven.nnitq.restdm.core.schema.mysql;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import raven.nnitq.restdm.core.schema.SchemaProvider;
import raven.nnitq.restdm.core.schema.model.ColumnSchema;
import raven.nnitq.restdm.core.schema.model.TableSchema;
import raven.nnitq.restdm.core.sql.SqlDataAccessor;

/**
 * @author Raven
 */
@Component
public class MySqlSchemaProvider implements SchemaProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySqlSchemaProvider.class);

    @Override
    public List<TableSchema> getTables(final DataSource dataSource) {
        SqlDataAccessor dao = new SqlDataAccessor(dataSource);
        String sql = "select database();";
        String dbName = dao.executeQuery(sql, (rs) -> {
            rs.next();
            return rs.getNString(1);
        });
        LOGGER.info("Current database: {}", dbName);

        List<TableSchema> tables = this.fetchTables(dao, dbName);
        tables.forEach(t -> this.fillColumns(dao, dbName, t));
        return tables;
    }

    private List<TableSchema> fetchTables(final SqlDataAccessor dao, final String dbName) {
        String sql = "select * from information_schema.TABLES where TABLE_SCHEMA = ?;";
        List<Object> params = Lists.newArrayList(dbName);
        return dao.executeQuery(sql, params, (rs) -> {
            List<TableSchema> list = new ArrayList<>();
            while (rs.next()) {
                TableSchema item = new TableSchema();
                item.setName(rs.getNString("TABLE_NAME"));
                item.setComment(rs.getNString("TABLE_COMMENT"));
                list.add(item);
            }
            return list;
        });
    }

    private void fillColumns(final SqlDataAccessor dao, final String dbName, final TableSchema table) {
        String sql = "select * from information_schema.COLUMNS where TABLE_SCHEMA = ? and table_name = ?;";
        List<Object> params = Lists.newArrayList(dbName, table.getName());
        List<ColumnSchema> columns = dao.executeQuery(sql, params, (rs) -> {
            List<ColumnSchema> list = new ArrayList<>();
            while (rs.next()) {
                ColumnSchema item = new ColumnSchema();
                item.setName(rs.getNString("COLUMN_NAME"));
                item.setDataType(rs.getNString("DATA_TYPE"));
                item.setIsPrimaryKey(rs.getNString("COLUMN_KEY").toUpperCase().contains("PRI"));
                // TODO 读取其它列属性
                list.add(item);
            }
            return list;
        });
        table.setColumns(columns);
    }

}
