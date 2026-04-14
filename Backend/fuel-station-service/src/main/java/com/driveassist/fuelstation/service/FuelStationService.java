package com.driveassist.fuelstation.service;

import com.driveassist.common.service.LocationAwareService;
import com.driveassist.fuelstation.dto.FuelStationRequest;
import com.driveassist.fuelstation.dto.FuelStationResponse;
import com.driveassist.fuelstation.enums.FuelType;

import java.util.List;

/**
 * Fuel station service interface — extends LocationAwareService (OOP Interface Inheritance).
 * Inherits CRUD + findNearby + search from parent interfaces.
 */
public interface FuelStationService extends LocationAwareService<FuelStationRequest, FuelStationResponse, Long> {

    List<FuelStationResponse> findByType(FuelType fuelType);

    List<FuelStationResponse> findOpen24Hours();
}
