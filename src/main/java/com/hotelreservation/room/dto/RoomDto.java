package com.hotelreservation.room.dto;

import com.hotelreservation.bookedroom.BookedRoom;
import com.hotelreservation.bookedroom.dto.BookedRoomDto;

import java.math.BigDecimal;
import java.util.List;

public record RoomDto(Long id,
                      String roomType,
                      BigDecimal roomPrice,
                      boolean isBooked,
                      byte[] photoBytes,
                      List<BookedRoomDto> bookings) {
}
