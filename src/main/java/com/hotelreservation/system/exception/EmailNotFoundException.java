package com.hotelreservation.system.exception;

public class EmailNotFoundException extends RuntimeException{

    public EmailNotFoundException(String email)
    {
        super("L'utilisatteur avec cet adrresse email n'existe pas: "+email);
    }

}
