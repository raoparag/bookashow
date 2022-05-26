package com.jpmc.shows.controller;

import com.jpmc.shows.entity.Show;
import com.jpmc.shows.request.AddUpdateSeatsRequest;
import com.jpmc.shows.request.AddUpdateShowRequest;
import com.jpmc.shows.response.ShowResponse;
import com.jpmc.shows.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShowController {

    @Autowired
    private ShowService showService;

    @GetMapping("/shows")
    public List<ShowResponse> getShows() {
        return showService.getShows();
    }

    @PostMapping("/show/add")
    public List<ShowResponse> addShow(@RequestBody AddUpdateShowRequest addUpdateShowRequest) throws Exception {
        showService.addShow(addUpdateShowRequest);
        return showService.getShows();
    }

    @PostMapping("/show/update")
    public List<ShowResponse> updateShow(@RequestBody AddUpdateShowRequest addUpdateShowRequest) throws Exception {
        showService.updateShow(addUpdateShowRequest);
        return showService.getShows();
    }

    @PostMapping("/show/seats")
    public List<ShowResponse> updateSeats(@RequestBody AddUpdateSeatsRequest addUpdateSeatsRequest) throws Exception {
        showService.updateSeats(addUpdateSeatsRequest);
        return showService.getShows();
    }
}
