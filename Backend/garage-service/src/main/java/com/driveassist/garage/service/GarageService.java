package com.driveassist.garage.service;

import com.driveassist.common.service.LocationAwareService;
import com.driveassist.garage.dto.GarageRequest;
import com.driveassist.garage.dto.GarageResponse;
import com.driveassist.garage.enums.GarageSpecialization;

import java.util.List;

/**
 * Garage service interface — extends LocationAwareService (OOP Interface Inheritance).
 */
public interface GarageService extends LocationAwareService<GarageRequest, GarageResponse, Long> {

    List<GarageResponse> findBySpecialization(GarageSpecialization specialization);

    List<GarageResponse> findEmergencyGarages();
}
