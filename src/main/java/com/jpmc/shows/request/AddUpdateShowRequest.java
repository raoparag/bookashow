package com.jpmc.shows.request;

import com.jpmc.shows.entity.Show;
import lombok.Data;

@Data
public class AddUpdateShowRequest {
    private String userId;
    private Show show;
}
