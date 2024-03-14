package com.hotelreservation.system.exception;

public class PhotoCanotRetriveException extends RuntimeException{


    public PhotoCanotRetriveException(String photo)
    {
        super("impossible retrouver la photo: "+ photo);
    }
}
