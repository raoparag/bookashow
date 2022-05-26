package com.jpmc.shows.service;

import com.jpmc.shows.constants.Status;
import com.jpmc.shows.entity.Seat;
import com.jpmc.shows.entity.Show;
import com.jpmc.shows.repository.AdminRepo;
import com.jpmc.shows.repository.SeatsRepo;
import com.jpmc.shows.repository.ShowsRepo;
import com.jpmc.shows.request.AddUpdateSeatsRequest;
import com.jpmc.shows.request.AddUpdateShowRequest;
import com.jpmc.shows.response.ShowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShowService {

    @Autowired
    private ShowsRepo showsRepo;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private SeatsRepo seatsRepo;

    public List<ShowResponse> getShows() {
        List<ShowResponse> showResponseList = new ArrayList<>();
        List<Show> allShows = showsRepo.getShows();
        for (Show show : allShows) {
            ShowResponse showResponse = new ShowResponse();
            showResponse.setShowId(show.getShowId());
            showResponse.setShowName(show.getShowName());
            long showCapacity = seatsRepo.getSeats().stream().filter(seat -> seat.getShowId() == show.getShowId()).count();
            showResponse.setShowCapacity(showCapacity);
            showResponse.setSeats(seatsRepo.getSeats().stream().filter(seat -> seat.getShowId() == show.getShowId()).collect(Collectors.toList()));
            showResponseList.add(showResponse);
        }

        return showResponseList;
    }

    public void addShow(AddUpdateShowRequest addUpdateShowRequest) throws Exception {
        boolean isAdminUser = validateAdminUser(addUpdateShowRequest.getUserId());
        if (!isAdminUser)
            throw new Exception("Error: Only Admins can add shows");
        boolean showExists = checkForExistingShows(addUpdateShowRequest.getShow().getShowId());
        if (showExists)
            throw new Exception("Error: Show ID already exists");
        showsRepo.getShows().add(addUpdateShowRequest.getShow());
    }

    private boolean checkForExistingShows(int showId) {
        return showsRepo.getShows().stream().anyMatch(existingShow -> existingShow.getShowId() == showId);
    }

    private boolean validateAdminUser(String userId) {
        return adminRepo.getAdminIds().contains(userId);
    }


    public void updateShow(AddUpdateShowRequest addUpdateShowRequest) throws Exception {
        boolean isAdminUser = validateAdminUser(addUpdateShowRequest.getUserId());
        if (!isAdminUser)
            throw new Exception("Error: Only Admins can update shows");
        boolean showExists = checkForExistingShows(addUpdateShowRequest.getShow().getShowId());
        if (!showExists)
            showsRepo.getShows().add(addUpdateShowRequest.getShow());
        else {
            Show searchShow = null;
            List<Show> allShows = showsRepo.getShows();
            for (Show thisShow : allShows) {
                if (thisShow.getShowId() == addUpdateShowRequest.getShow().getShowId()) {
                    searchShow = thisShow;
                }
            }
            searchShow.setShowName(addUpdateShowRequest.getShow().getShowName());
        }
    }

    public void updateSeats(AddUpdateSeatsRequest addUpdateSeatsRequest) throws Exception {
        boolean isAdminUser = validateAdminUser(addUpdateSeatsRequest.getUserId());
        if (!isAdminUser)
            throw new Exception("Error: Only Admins can add/update seats");
        boolean showExists = checkForExistingShows(addUpdateSeatsRequest.getShowId());
        if (!showExists)
            throw new Exception("Error: Show ID does not exist");
        Set<Seat> seats = seatsRepo.getSeats().stream().filter(seat -> seat.getShowId() == addUpdateSeatsRequest.getShowId()).collect(Collectors.toSet());
        boolean anySeatNotAvailable = seats.stream().anyMatch(seat -> seat.getStatus() != Status.AVAILABLE);
        if (anySeatNotAvailable)
            throw new Exception("Error: Some of the seats are in the process of Booking or already Booked");
        seatsRepo.getSeats().removeIf(seat -> seat.getShowId() == addUpdateSeatsRequest.getShowId());
        for (String seatId : addUpdateSeatsRequest.getSeatIds()) {
            seatsRepo.getSeats().add(new Seat(seatId, addUpdateSeatsRequest.getShowId(), Status.AVAILABLE));
        }
    }
}
