package com.driveassist.rental.repository;

import com.driveassist.rental.entity.Booking;
import com.driveassist.rental.enums.BookingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserId(Long userId);

    Page<Booking> findByUserId(Long userId, Pageable pageable);

    List<Booking> findByUserIdAndStatus(Long userId, BookingStatus status);

    List<Booking> findByCarIdAndStatusIn(Long carId, List<BookingStatus> statuses);
}
