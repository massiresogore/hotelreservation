package com.hotelreservation.system.exception;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(String ObjectName, Long id)
    {
        super("Could not Find "+ObjectName+" with Id:"+id+ ":(");
    }

}
