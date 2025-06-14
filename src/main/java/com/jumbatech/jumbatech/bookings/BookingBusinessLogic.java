package com.jumbatech.jumbatech.bookings;

import com.jumbatech.jumbatech.dto.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class BookingBusinessLogic {
    @Autowired
    BookingsRepository bookingsRepository;

    public ResponseEntity<?> createBookings(Bookings bookings){
        try {
            bookings.setId(UUID.randomUUID().toString());
            bookingsRepository.save(bookings);
            RestResponse restResponse= new RestResponse();
            restResponse.setStatus("200");
            restResponse.setMessage("Booking created successfully");
            return ResponseEntity.status(HttpStatus.OK).body(restResponse);

        }catch (Exception e){
            e.printStackTrace();
            RestResponse restResponse= new RestResponse();
            restResponse.setStatus("201");
            restResponse.setMessage("Something went wrong while create bookings");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
        }
    }
    public ResponseEntity<?> getAllBookings(){
        try{
            RestResponse restResponse= new RestResponse();
            restResponse.setMessage("Get all bookings successfully");
            restResponse.setStatus("200");
            return ResponseEntity.status(HttpStatus.OK).body(bookingsRepository.findAll());

        }catch(Exception e){
           e.printStackTrace();
            RestResponse restResponse= new RestResponse();
            restResponse.setMessage("Error occured while getting all bookings");
            restResponse.setStatus("200");
            return ResponseEntity.status(HttpStatus.OK).body(restResponse);
        }
    }

    public ResponseEntity<?> GetBookingsById(String id){
        try {
            Optional<Bookings> ById=bookingsRepository.findById(id);
            if(ById.isPresent()){
               return ResponseEntity.status(HttpStatus.OK).body(ById);
            }else{
                RestResponse restResponse= new RestResponse();
                restResponse.setStatus("201");
                restResponse.setMessage("Failed to get Bookings by id");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
            }

        }catch (Exception e){
            e.printStackTrace();
            RestResponse restResponse= new RestResponse();
            restResponse.setStatus("400");
            restResponse.setMessage("Failed to get Bookings by id");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
        }
    }
    public ResponseEntity<?> updateBookings(Bookings bookings){
        try{
            Optional<Bookings> ById=bookingsRepository.findById(bookings.getId());
            if (ById.isPresent()){
                ById.get().setId(bookings.getId());
                ById.get().setName(bookings.getName());
                ById.get().setSurname(bookings.getSurname());
                ById.get().setAddress(bookings.getAddress());
                ById.get().setPhoneNumber(bookings.getPhoneNumber());
                ById.get().setDate(bookings.getDate());
                bookingsRepository.save(ById.get());

                RestResponse restResponse= new RestResponse();
                restResponse.setStatus("200");
                restResponse.setMessage("Bookings updated successfully");
                return ResponseEntity.status(HttpStatus.OK).body(restResponse);

            }else {
                RestResponse restResponse= new RestResponse();
                restResponse.setStatus("201");
                restResponse.setMessage("Failed  updated bookings");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
            }

        }catch (Exception e){
            e.printStackTrace();
            RestResponse restResponse= new RestResponse();
            restResponse.setStatus("400");
            restResponse.setMessage("Error Occurred while updating bookings");
            return ResponseEntity.status(HttpStatus.OK).body(restResponse);
        }
    }
    public ResponseEntity<?> deleteBookings(String id){
        try{
            bookingsRepository.deleteById(id);
            RestResponse restResponse= new RestResponse();
            restResponse.setStatus("200");
            restResponse.setMessage("Bookings deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(restResponse);

        }catch (Exception e){
            e.printStackTrace();
            RestResponse restResponse= new RestResponse();
            restResponse.setStatus("400");
            restResponse.setMessage("Error Occurred while deleting bookings");
            return ResponseEntity.status(HttpStatus.OK).body(restResponse);
        }
    }
}
