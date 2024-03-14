package com.hotelreservation.system.exception;


import java.time.LocalDate;

public class InvalidBookingRequestException extends RuntimeException{

    public InvalidBookingRequestException(LocalDate checkOutDate,LocalDate checkInDate)
    {
        super("La date d'arrivée: "+checkInDate+" doit intervenir avant la date de départ:Invalid date: "+checkOutDate);
    }
}
