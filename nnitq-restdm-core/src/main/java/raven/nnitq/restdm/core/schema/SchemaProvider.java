package raven.nnitq.restdm.core.schema;

import java.util.List;
import javax.sql.DataSource;

import raven.nnitq.restdm.core.schema.model.TableSchema;

/**
 * @author Raven
 */
public interface SchemaProvider {

    List<TableSchema> getTables(DataSource dataSource);

}
