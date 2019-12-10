package raven.nnitq.restdm.core.service;

import java.util.Map;

import raven.nnitq.restdm.core.model.PagedData;

/**
 * @author Raven
 */
public interface DataService {

    Map<String, Object> find(String objectName, Object id);

    PagedData query(String objectName, int offset, int limit);

}
