package raven.nnitq.restdm.core.service.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import raven.nnitq.restdm.core.common.Guard;
import raven.nnitq.restdm.core.model.PagedData;
import raven.nnitq.restdm.core.schema.SchemaProvider;
import raven.nnitq.restdm.core.schema.model.TableSchema;
import raven.nnitq.restdm.core.service.DataService;
import raven.nnitq.restdm.core.sql.SqlDataAccessor;
import raven.nnitq.restdm.core.sql.mapper.ListAndMapResultSetMapper;

/**
 * @author Raven
 */
@Service
public class DemoDataService implements DataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoDataService.class);

    private final DataSource dataSource;
    private final SchemaProvider schemaProvider;
    private final SqlDataAccessor dao;
    private final Map<String, TableSchema> registry;

    public DemoDataService(final DataSource dataSource, final SchemaProvider schemaProvider) {
        this.dataSource = Guard.notNull(dataSource, "dataSource");
        this.schemaProvider = Guard.notNull(schemaProvider, "schemaProvider");
        this.dao = new SqlDataAccessor(dataSource);
        this.registry = new HashMap<>();
        this.init();
    }

    private void init() {
        List<TableSchema> tables = this.schemaProvider.getTables(this.dataSource);
        tables.forEach(t -> registry.put(t.getName(), t));
    }

    @Override
    public Map<String, Object> find(final String objectName, final Object id) {
        // TODO 实现 DemoDataService.find 方法
        throw new UnsupportedOperationException("DemoDataService.find 未实现");
    }

    @Override
    public PagedData query(final String objectName, final int offset, final int limit) {
        TableSchema table = this.registry.get(objectName);
        if (table == null) {
            throw new IllegalStateException("对象名称无效：" + objectName);
        }

        String sql = String.format("select count(*) from `%s`;", table.getName());
        int total = dao.executeQuery(sql, rs -> {
            rs.next();
            return rs.getInt(1);
        });
        LOGGER.debug("Execute SQL: {}", sql);

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select ");
        for (int i = 0, n = table.getColumns().size(), j = n - 1; i < n; i++) {
            sqlBuilder.append('`');
            sqlBuilder.append(table.getColumns().get(i).getName());
            sqlBuilder.append('`');
            if (i < j) {
                sqlBuilder.append(", ");
            }
        }
        sqlBuilder.append(" from `");
        sqlBuilder.append(table.getName());
        sqlBuilder.append("` limit ");
        sqlBuilder.append(offset);
        sqlBuilder.append(",");
        sqlBuilder.append(limit);
        sqlBuilder.append(";");
        sql = sqlBuilder.toString();

        List<Map<String, Object>> rows = this.dao.executeQuery(sql, ListAndMapResultSetMapper.INSTANCE);
        LOGGER.debug("Execute SQL: {}", sql);

        PagedData page = new PagedData();
        page.setTotal(total);
        page.setOffset(offset);
        page.setLimit(limit);
        page.setItems(rows);
        return page;
    }

}
