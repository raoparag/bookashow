package com.jpmc.shows.request;

import com.jpmc.shows.entity.Seat;
import lombok.Data;

import java.util.List;

@Data
public class AddUpdateSeatsRequest {
    private String userId;
    private int showId;
    private List<String> seatIds;
}
