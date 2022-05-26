package com.jpmc.shows.entity;

import com.jpmc.shows.constants.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Seat {
    private String seatId;
    private int showId;
    private Status status;
}
