package com.hotelreservation.room;

import com.hotelreservation.bookedroom.BookedRoom;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String roomType;
    private BigDecimal roomPrice;
    private boolean isBooked = false;
    @Lob
    private Blob photo;

    @OneToMany(mappedBy="room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BookedRoom> bookings;

    public Room(Long id, String roomType, BigDecimal roomPrice, boolean isBooked, Blob photo, List<BookedRoom> bookings) {
        this.id = id;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.isBooked = isBooked;
        this.photo = photo;
        this.bookings = bookings;
    } public Room(Long id, String roomType, BigDecimal roomPrice, boolean isBooked, List<BookedRoom> bookings) {
        this.id = id;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.isBooked = isBooked;
        this.bookings = bookings;
    }

    /**
     * On initialise le tableau des réservation lors de l'instantation de l'objet
     */
    public Room() {
        this.bookings = new ArrayList<>();
    }

    /**
     * Ajout d'une nouvelle réservation
     *
     * @param booking , doit etre une instance de bookedRoom
     */
    public void addBooking(BookedRoom booking){
        if (bookings == null){
            bookings = new ArrayList<>();
        }
        this.bookings.add(booking);
        booking.setRoom(this);
        this.isBooked = true;
        String bookingCode = RandomStringUtils.randomNumeric(10);
        booking.setBookingConfirmationCode(bookingCode);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public BigDecimal getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(BigDecimal roomPrice) {
        this.roomPrice = roomPrice;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    public List<BookedRoom> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookedRoom> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomType='" + roomType + '\'' +
                ", roomPrice=" + roomPrice +
                ", isBooked=" + isBooked +
                ", photo=" + photo +
                ", bookings=" + bookings +
                '}';
    }
}
