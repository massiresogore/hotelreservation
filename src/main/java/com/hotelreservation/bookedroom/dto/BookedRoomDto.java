package com.hotelreservation.bookedroom.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record BookedRoomDto(Long id,
                            @PastOrPresent
                        LocalDate checkInDate,

                            @PastOrPresent
                        LocalDate checkOutDate,
                            @NotEmpty
                        String bookingConfirmationCode) {
}
