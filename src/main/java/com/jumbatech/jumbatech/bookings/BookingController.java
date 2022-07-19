package com.jumbatech.jumbatech.bookings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping
public class BookingController {
    @Autowired
    BookingBusinessLogic bookingBusinessLogic;

    @PostMapping("create/bookings")
    public ResponseEntity<?> createBooking(@RequestBody Bookings bookings){
        return bookingBusinessLogic.createBookings(bookings);
    }
    @GetMapping("get/all/bookings")
    public ResponseEntity<?> getAllBookings(){
      return  bookingBusinessLogic.getAllBookings();
    }
    @GetMapping("get/bookings/by/{id}")
    public ResponseEntity<?> GetBookingsById(@PathVariable String id){
        return  bookingBusinessLogic.GetBookingsById(id);
    }
    @PutMapping("update/bookings")
    public ResponseEntity<?> updateBookings(@RequestBody Bookings bookings){
        return bookingBusinessLogic.updateBookings(bookings);
    }
    @DeleteMapping("delete/bookings/{id}")
    public ResponseEntity<?> deleteBookings(@PathVariable String id){
        return bookingBusinessLogic.deleteBookings(id);
    }



}
