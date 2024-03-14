package com.hotelreservation.system.exception;

import java.time.LocalDate;

public class UnavailableRoomException extends RuntimeException{

    public UnavailableRoomException(LocalDate checkOutDate, LocalDate checkInDate)
    {
        super("Désolé, cette chambre n'est pas disponible pour les dates sélectionnées: date entrée: "+checkInDate+" départ date: "+checkOutDate);
    }
}
