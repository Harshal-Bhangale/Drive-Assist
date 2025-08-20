package com.codewithharshal.driveassist.service;

import java.util.List;
// NearbyService (Base Interface for OOP abstraction)

public interface NearbyService<T> {
    List<T> getAll();
    T getById(Long id);
    T save(T entity);
    void delete(Long id);
}
/*
 - This is generic → works for FuelStation, Garage, CarRental
 - OOP: Abstraction + Reusability
 */