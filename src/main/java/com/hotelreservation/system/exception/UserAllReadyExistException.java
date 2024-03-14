package com.hotelreservation.system.exception;

public class UserAllReadyExistException extends RuntimeException{


    public UserAllReadyExistException(String email)
    {
        super("cet utilisatteur existe déja: "+ email);
    }
}
