package com.codewithharshal.driveassist.service;

import com.codewithharshal.driveassist.model.CarRental;

public interface CarRentalService extends NearbyService<CarRental> {
//    CarRental findAvailableCar(String carType);
    CarRental findAvailableCar(String carType, Double userLat, Double userLon);

}