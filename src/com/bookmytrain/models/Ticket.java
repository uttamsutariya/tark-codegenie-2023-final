package com.bookmytrain.models;

public class Ticket {

    private static final long DEFAULT_PNR = 100000000;
    private static long PNR = DEFAULT_PNR;
    private int total_fair;

    public Ticket() { }

    public Ticket(int total_fair) {
        this.total_fair = total_fair;
    }

    public static long getPNR() {
        return PNR;
    }

    public int getTotal_fair() {
        return total_fair;
    }
}
