package com.jpmc.shows.response;

import com.jpmc.shows.entity.Seat;
import com.jpmc.shows.entity.Show;
import lombok.*;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowResponse extends Show {
    private long showCapacity;
    private List<Seat> seats;
}
