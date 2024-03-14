package com.hotelreservation.bookedroom;

import com.hotelreservation.room.Room;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class BookedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long bookingId;
    @Column(name = "check_in")
    private LocalDate checkInDate;

    @Column(name = "check_out")
    private LocalDate checkOutDate;

    private String guestFullName;

    private String guestEmail;

    @Column(name = "adults")
    private int NumOfAdults;

    @Column(name = "children")
    private int NumOfChildren;

    @Column(name = "total_guest")
    private int totalNumOfGuest;

    @Column(name = "confirmation_Code")
    private String bookingConfirmationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;


    /** commentaire de base
     * Increment a value by delta and return the new value.
     *
     * @param  delta   the amount the value should be incremented by
     * @return         the post-incremented value
     */

    /**
     * calcul le nombre total des clients
     */
    public void calculateTotalNumberOfGuest(){
        this.totalNumOfGuest = this.NumOfAdults + NumOfChildren;
    }

    /** Ajoute le nombre d'adulte et appelle la fonction
     * </p>
     * calculateTotalNumberOfGuest , pour refaire le calcul total des clients
     *
     * @param numOfAdults  doit etre un nombre entier
     */
    public void setNumOfAdults(int numOfAdults) {
        NumOfAdults = numOfAdults;
        calculateTotalNumberOfGuest();
    }

    /** Ajoute le nombre d'enfants et appelle la fonction
     * </p>
     * calculateTotalNumberOfGuest , pour refaire le calcul total des clients
     *
     * @param numOfChildren  doit etre un nombre entier
     */
    public void setNumOfChildren(int numOfChildren) {
        NumOfChildren = numOfChildren;
        calculateTotalNumberOfGuest();
    }

    /** Ajoute le code de confirmation lors d'une réservation
     *
     * @param bookingConfirmationCode  doit etre une chaine de caracter
     */
    public void setBookingConfirmationCode(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
    }


    /*Getter setter*/

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getGuestFullName() {
        return guestFullName;
    }

    public void setGuestFullName(String guestFullName) {
        this.guestFullName = guestFullName;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public int getNumOfAdults() {
        return NumOfAdults;
    }

    public int getNumOfChildren() {
        return NumOfChildren;
    }

    public int getTotalNumOfGuest() {
        return totalNumOfGuest;
    }

    public void setTotalNumOfGuest(int totalNumOfGuest) {
        this.totalNumOfGuest = totalNumOfGuest;
    }

    public String getBookingConfirmationCode() {
        return bookingConfirmationCode;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
