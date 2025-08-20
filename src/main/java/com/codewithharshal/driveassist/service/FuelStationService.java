package com.codewithharshal.driveassist.service;

import com.codewithharshal.driveassist.model.FuelStation;

public interface FuelStationService extends NearbyService<FuelStation> {
    // extra business logic specific to fuel stations
    FuelStation findNearest(Double userLat, Double userLon);
}