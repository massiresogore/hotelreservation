package com.hotelreservation.room.converter;

import com.hotelreservation.bookedroom.BookedRoom;
import com.hotelreservation.bookedroom.converter.BookedRoomDtoToBookedRoomConverter;
import com.hotelreservation.bookedroom.dto.BookedRoomDto;
import com.hotelreservation.room.Room;
import com.hotelreservation.room.dto.RoomDto;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Component
public class RoomDtoToRoomConverter implements Converter<RoomDto, Room> {
    private final BookedRoomDtoToBookedRoomConverter bookedRoomDtoToBookedRoomConverter;

    public RoomDtoToRoomConverter(BookedRoomDtoToBookedRoomConverter bookedRoomDtoToBookedRoomConverter) {
        this.bookedRoomDtoToBookedRoomConverter = bookedRoomDtoToBookedRoomConverter;
    }

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public Room convert(RoomDto source) {
        //Convert BookedRoom en BookedRoomDto
        Collection<BookedRoomDto> bookedRoomDtoList  =  source.bookings();
        List<BookedRoom> bookedRoomList = bookedRoomDtoList.stream().map(bookedRoomDtoToBookedRoomConverter::convert).toList();

            return  new Room(source.id(),
                    source.roomType(),
                    source.roomPrice(),
                    source.isBooked(),
                   // new SerialBlob(source.photoBytes()),
                    bookedRoomList
            );


    }
}
