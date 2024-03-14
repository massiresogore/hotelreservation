package com.hotelreservation.bookedroom;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booked")
public class BookedController {
    private final BookedRoomService bookedRoomService;

    public BookedController(BookedRoomService bookedRoomService) {
        this.bookedRoomService = bookedRoomService;
    }





}
