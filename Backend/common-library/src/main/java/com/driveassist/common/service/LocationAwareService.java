package com.driveassist.common.service;

import java.util.List;

/**
 * Extends CrudService with location-based search — Interface Inheritance.
 * Used by services dealing with geo-located entities (fuel stations, garages, rentals).
 *
 * @param <REQ>  Request DTO type
 * @param <RES>  Response DTO type
 * @param <ID>   Entity ID type
 */
public interface LocationAwareService<REQ, RES, ID> extends CrudService<REQ, RES, ID> {

    List<RES> findNearby(double lat, double lon, double radiusKm, String sortBy);

    List<RES> search(String keyword);
}
