package com.hotelreservation.bookedroom.converter;

import com.hotelreservation.bookedroom.BookedRoom;
import com.hotelreservation.bookedroom.dto.BookedRoomDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookedRoomDtoToBookedRoomConverter implements Converter<BookedRoomDto, BookedRoom> {

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public BookedRoom convert(BookedRoomDto source) {

        return new BookedRoom(source.id(),
                source.checkOutDate(),
                source.checkInDate(),
                source.bookingConfirmationCode()
        );
    }
}
