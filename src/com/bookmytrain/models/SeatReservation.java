package com.bookmytrain.models;

public class SeatReservation {
    private String date;
    private String  from;
    private String to;

    public SeatReservation(String date, String from, String to) {
        this.date = date;
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
