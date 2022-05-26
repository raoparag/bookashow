package com.jpmc.shows.repository;

import com.jpmc.shows.entity.Show;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository @Getter @Setter
public class ShowsRepo {
    private List<Show> shows = new ArrayList<>();
}
