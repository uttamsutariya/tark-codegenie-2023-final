package com.bookmytrain.models;

import java.util.ArrayList;

public class Coach {
    private String type;
    private int number;
    private int number_of_seats;
    private ArrayList<Seat> seat_list;
    private int available_seats;
    private int booked_seats;

    public Coach(String type, int number_of_seats) {
        this.type = type;
        this.number_of_seats = number_of_seats;
        this.seat_list = new ArrayList<>();

        for (int i = 0; i < number_of_seats; i++) {
            seat_list.add(new Seat());
        }

        available_seats = number_of_seats;
    }

    @Override
    public String toString() {
        return type + number_of_seats;
    }
}
