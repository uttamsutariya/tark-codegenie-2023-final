package com.bookmytrain.models;

public class Ticket {

    private static long PNR = 100000001;
    private int total_fair;

    public Ticket(int total_fair) {
        this.total_fair = total_fair;
        PNR++; // increment pnr by 1 for next ticket
    }
}
