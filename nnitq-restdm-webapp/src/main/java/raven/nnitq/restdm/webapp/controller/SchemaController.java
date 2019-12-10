package raven.nnitq.restdm.webapp.controller;

import java.util.List;
import javax.sql.DataSource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raven.nnitq.restdm.core.schema.SchemaProvider;
import raven.nnitq.restdm.core.schema.model.TableSchema;

/**
 * @author Raven
 */
@Profile({ "local", "dev", "test" })
@Api
@RestController
@RequestMapping("/api/schema")
public class SchemaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchemaController.class);

    private final SchemaProvider schemaProvider;
    private final DataSource dataSource;

    public SchemaController(final SchemaProvider schemaProvider, final DataSource dataSource) {
        this.schemaProvider = schemaProvider;
        this.dataSource = dataSource;
    }

    @ApiOperation("获取所有数据表结构")
    @GetMapping("/tables")
    public List<TableSchema> getTables() {
        return this.schemaProvider.getTables(dataSource);
    }

}
