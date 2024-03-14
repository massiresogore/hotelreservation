package com.hotelreservation.room.converter;

import com.hotelreservation.bookedroom.BookedRoom;
import com.hotelreservation.bookedroom.converter.BookedRoomToBookedRoomDtoConverter;
import com.hotelreservation.bookedroom.dto.BookedRoomDto;
import com.hotelreservation.room.Room;
import com.hotelreservation.room.dto.RoomDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Component
public class RoomToRoomDtoConverter implements Converter<Room, RoomDto> {
    private final BookedRoomToBookedRoomDtoConverter bookedRoomToBookedRoomDtoConverter;

    public RoomToRoomDtoConverter(BookedRoomToBookedRoomDtoConverter bookedRoomToBookedRoomDtoConverter) {
        this.bookedRoomToBookedRoomDtoConverter = bookedRoomToBookedRoomDtoConverter;
    }

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public RoomDto convert(Room source) {
        //Convert BookedRoom en BookedRoomDto
        Collection<BookedRoom> bookedRoomCollection  = source.getBookings();
        List<BookedRoomDto> bookedRoomDtoCollection = bookedRoomCollection.stream().map(bookedRoomToBookedRoomDtoConverter::convert).toList();

            return new RoomDto(
                    source.getId(),
                    source.getRoomType(),
                    source.getRoomPrice(),
                    source.isBooked(),
                    //source.getPhoto().getBytes(1,(int) source.getPhoto().length()),
                    bookedRoomDtoCollection
            );


    }
}
