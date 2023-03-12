package com.bookmytrain.models;

import java.util.*;

public class Coach {
    private String coach_name;
    private String travel_class;
    private int coach_number;
    private int total_seats;
    private List<Seat> seats;

    public Coach(String coach_name, String travel_class, int coach_number, int total_seats) {
        this.travel_class = travel_class;
        this.coach_name = coach_name;
        this.coach_number = coach_number;
        this.total_seats = total_seats;
        seats = new ArrayList<>();

        for(int i = 0 ; i<total_seats ; i++) {
            seats.add(new Seat(coach_number, i+1));
        }
    }

    public List<Seat> getSeats() {
        return seats;
    }

}
