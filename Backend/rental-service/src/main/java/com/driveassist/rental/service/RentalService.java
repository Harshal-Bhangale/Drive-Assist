package com.driveassist.rental.service;

import com.driveassist.common.dto.PagedResponse;
import com.driveassist.common.service.LocationAwareService;
import com.driveassist.rental.dto.*;
import com.driveassist.rental.enums.CarType;

import java.util.List;

/**
 * Rental service interface — combines LocationAwareService for cars
 * and booking-specific operations (Interface Segregation).
 */
public interface RentalService extends LocationAwareService<CarRentalRequest, CarRentalResponse, Long> {

    // --- Car operations ---
    List<CarRentalResponse> findAvailable(double lat, double lon, CarType type, Double maxPrice, String sortBy);

    // --- Booking operations ---
    BookingResponse createBooking(BookingRequest request);

    BookingResponse getBooking(Long bookingId);

    BookingResponse cancelBooking(Long bookingId);

    List<BookingResponse> getUserBookings(Long userId);

    PagedResponse<BookingResponse> getUserBookingsPaged(Long userId, int page, int size);
}
