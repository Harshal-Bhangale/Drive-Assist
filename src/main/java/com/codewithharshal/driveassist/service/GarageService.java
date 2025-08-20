package com.codewithharshal.driveassist.service;

import com.codewithharshal.driveassist.model.Garage;

public interface GarageService extends NearbyService<Garage> {
    Garage findNearest(Double userLat, Double userLon);
}