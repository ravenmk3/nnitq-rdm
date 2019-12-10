package raven.nnitq.restdm.core.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Raven
 */
@FunctionalInterface
public interface ResultSetMapper<T> {

    T map(ResultSet resultSet) throws SQLException;

}
