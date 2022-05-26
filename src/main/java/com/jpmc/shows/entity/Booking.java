package com.jpmc.shows.entity;

import com.jpmc.shows.constants.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class Booking {
    private int bookingId;
    private String userId;
    private List<Seat> seats;
    private Status status;
}
