package raven.nnitq.restdm.core.sql;

import java.sql.*;
import java.util.List;
import javax.sql.DataSource;

import raven.nnitq.restdm.core.common.Guard;

/**
 * @author Raven
 */
public class SqlDataAccessor {

    private final DataSource dataSource;

    public SqlDataAccessor(final DataSource dataSource) {
        Guard.notNull(dataSource, "dataSource");
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }

    public <T> T executeQuery(final String sql, final ResultSetMapper<T> mapper) {
        try (Connection connection = this.dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            return mapper.map(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Could not execute query", e);
        }
    }

    public <T> T executeQuery(final String sql, final List<Object> params, final ResultSetMapper<T> mapper) {
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0, n = params.size(); i < n; i++) {
                statement.setObject(i + 1, params.get(i));
            }
            ResultSet resultSet = statement.executeQuery();
            return mapper.map(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Could not execute query", e);
        }
    }

}
