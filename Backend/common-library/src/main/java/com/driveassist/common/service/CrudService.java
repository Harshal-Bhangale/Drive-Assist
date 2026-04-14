package com.driveassist.common.service;

import com.driveassist.common.dto.PagedResponse;

/**
 * Generic CRUD service interface — OOP Abstraction via generics.
 * All domain service interfaces extend this, inheriting standard CRUD operations.
 *
 * @param <REQ>  Request DTO type
 * @param <RES>  Response DTO type
 * @param <ID>   Entity ID type
 */
public interface CrudService<REQ, RES, ID> {

    RES create(REQ request);

    RES getById(ID id);

    PagedResponse<RES> getAll(int page, int size, String sortBy);

    RES update(ID id, REQ request);

    void delete(ID id);
}
