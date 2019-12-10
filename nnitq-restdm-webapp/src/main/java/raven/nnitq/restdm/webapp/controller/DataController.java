package raven.nnitq.restdm.webapp.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import raven.nnitq.restdm.core.model.PagedData;
import raven.nnitq.restdm.core.service.DataService;

/**
 * @author Raven
 */
@Api
@RestController
@RequestMapping("/api/data/{object-name}")
public class DataController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataController.class);

    private final DataService dataSource;

    public DataController(final DataService dataSource) {
        this.dataSource = dataSource;
    }

    @ApiOperation("查询指定名称的对象集合")
    @GetMapping()
    public PagedData query(@PathVariable("object-name") String objectName,
                           @RequestParam(defaultValue = "0") int offset,
                           @RequestParam(defaultValue = "1000") int limit) {
        return this.dataSource.query(objectName, offset, limit);
    }

    @ApiOperation("查询指定名称的对象集合")
    @GetMapping("/{id}")
    public Map<String, Object> find(@PathVariable("object-name") String objectName,
                                    @PathVariable("id") String id) {
        return this.dataSource.find(objectName, id);
    }

}
