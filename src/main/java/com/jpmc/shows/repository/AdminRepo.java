package com.jpmc.shows.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Repository @Getter @Setter
public class AdminRepo {
    private Set<String> adminIds = new HashSet<>();

    @PostConstruct
    private void init() {
        adminIds.add("a1");
        adminIds.add("a2");
    }
}
