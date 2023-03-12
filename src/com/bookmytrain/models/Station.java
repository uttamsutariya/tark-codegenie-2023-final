package com.bookmytrain.models;

public class Station {
    private int distance_from_source;
    private String name;

    public Station(int distance_from_source, String name) {
        this.distance_from_source = distance_from_source;
        this.name = name;
    }

    public int getDistance_from_source() {
        return distance_from_source;
    }

    public String getName() {
        return name;
    }
}
