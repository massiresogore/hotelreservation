package com.hotelreservation.room;

import com.hotelreservation.bookedroom.BookedRoomService;
import com.hotelreservation.room.converter.RoomToRoomDtoConverter;
import com.hotelreservation.system.Result;
import com.hotelreservation.system.StatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomToRoomDtoConverter roomToRoomDtoConverter;
    private final RoomService roomService;
    private final BookedRoomService bookedRoomService;

    public RoomController(RoomToRoomDtoConverter roomToRoomDtoConverter, RoomService roomService, BookedRoomService bookedRoomService) {
        this.roomToRoomDtoConverter = roomToRoomDtoConverter;
        this.roomService = roomService;
        this.bookedRoomService = bookedRoomService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result addNewRoom(
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("roomType") String roomType,
            @RequestParam("roomPrice") BigDecimal roomPrice) throws SQLException, IOException {

        Room savedRoom = roomService.addNewRoom(photo, roomType, roomPrice);
        return new Result(
                true,
                StatusCode.SUCCESS,
                "create room success",
                this.roomToRoomDtoConverter.convert(savedRoom)

        );
    }
    @GetMapping("/types")
    public Result getRoomTypes() {
        return new Result(true, StatusCode.SUCCESS,"success",roomService.getAllRoomTypes());
    }

    /*@GetMapping
    public ResponseEntity<List<RoomResponse>> getAllRooms() throws SQLException {
        List<Room> rooms = roomService.getAllRooms();
        List<RoomResponse> roomResponses = new ArrayList<>();
        for (Room room : rooms) {
            byte[] photoBytes = roomService.getRoomPhotoByRoomId(room.getId());
            if (photoBytes != null && photoBytes.length > 0) {
                String base64Photo = Base64.encodeBase64String(photoBytes);
                RoomResponse roomResponse = getRoomResponse(room);
                roomResponse.setPhoto(base64Photo);
                roomResponses.add(roomResponse);
            }
        }
        return ResponseEntity.ok(roomResponses);
    }*/

    /*private RoomResponse getRoomResponse(Room room) {
        List<BookedRoom> bookings = this.getAllBookingsByRoomId(room.getId());
        List<BookingResponse> bookingInfo = bookings
                .stream()
                .map(booking -> new BookingResponse(booking.getBookingId(),
                        booking.getCheckInDate(),
                        booking.getCheckOutDate(), booking.getBookingConfirmationCode())).toList();

        byte[] photoBytes = null;
        Blob photoBlob = room.getPhoto();
        if (photoBlob != null) {
            try {
                photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());
            } catch (SQLException e) {
                throw new PhotoCanotRetriveException("Error retrieving photo");
            }
        }
        return new RoomResponse(room.getId(),
                room.getRoomType(), room.getRoomPrice(),
                room.isBooked(), photoBytes, bookingInfo);
    }
    private List<BookedRoom> getAllBookingsByRoomId(Long roomId) {
        return bookedRoomService.getAllBookingsByRoomId(roomId);
    }*/
}
