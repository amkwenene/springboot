package com.jumbatech.jumbatech.bookings;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingsRepository extends JpaRepository<Bookings, String> {

    Optional<Bookings> findById(String id);
}