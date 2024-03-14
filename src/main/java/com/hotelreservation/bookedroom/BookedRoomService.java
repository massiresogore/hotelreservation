package com.hotelreservation.bookedroom;

import com.hotelreservation.room.Room;
import com.hotelreservation.room.RoomRepository;
import com.hotelreservation.system.Result;
import com.hotelreservation.system.StatusCode;
import com.hotelreservation.system.exception.InvalidBookingRequestException;
import com.hotelreservation.system.exception.ObjectNotFoundException;
import com.hotelreservation.system.exception.UnavailableRoomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookedRoomService {


    private final   BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    public BookedRoomService(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    public List<BookedRoom> getAllBookings(){
        return this.bookingRepository.findAll();
    }

    public List<BookedRoom> getBookingsByUserEmail(String email) {
        return this.bookingRepository.findByGuestEmail(email);
    }

    public void cancelBooking(Long bookingId) {
        BookedRoom bookedRoom = this.bookingRepository.findById(bookingId)
                        .orElseThrow(()->new ObjectNotFoundException("bookedRoom", bookingId));
        this.bookingRepository.deleteById(bookingId);
    }

    public List<BookedRoom> getAllBookingsByRoomId(Long roomId) {

        return this.bookingRepository.findByRoomId(roomId);
    }

    public String saveBooking(Long roomId, BookedRoom bookingRequest) {
        //Vérifie si la date darriver doit intervenir avant la date de départ
        if (bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())){
            throw new InvalidBookingRequestException(bookingRequest.getCheckOutDate(),bookingRequest.getCheckInDate());
        }
        Room room =  this.roomRepository.findById(roomId)
                .orElseThrow(()->new ObjectNotFoundException("room", roomId));
        List<BookedRoom> existingBookings = room.getBookings();
        boolean roomIsAvailable = this.roomIsAvailable(bookingRequest,existingBookings);
        if (roomIsAvailable){
            room.addBooking(bookingRequest);
            this.bookingRepository.save(bookingRequest);
        }else{
            throw  new UnavailableRoomException(bookingRequest.getCheckOutDate(),bookingRequest.getCheckInDate());
        }
        return bookingRequest.getBookingConfirmationCode();
    }

    private boolean roomIsAvailable(BookedRoom bookingRequest, List<BookedRoom> existingBookings) {
        return existingBookings.stream()
                .noneMatch(existingBooking ->
                        bookingRequest.getCheckInDate().equals(existingBooking.getCheckInDate())
                                || bookingRequest.getCheckOutDate().isBefore(existingBooking.getCheckOutDate())
                                || (bookingRequest.getCheckInDate().isAfter(existingBooking.getCheckInDate())
                                && bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckOutDate()))
                                || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())

                                && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckOutDate()))
                                || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())

                                && bookingRequest.getCheckOutDate().isAfter(existingBooking.getCheckOutDate()))

                                || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                                && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckInDate()))

                                || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                                && bookingRequest.getCheckOutDate().equals(bookingRequest.getCheckInDate()))
                );
    }



}
