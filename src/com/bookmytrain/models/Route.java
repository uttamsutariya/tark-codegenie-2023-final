package com.bookmytrain.models;

public class Route {

    private String source;
    private String destination;
    private int distance;

    public Route(String source, String destination, int distance) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }

    /**
     * getters
     */
    public String getRoute() {
        return getSource() + " " + getDestination();
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return source + "-" + destination;
    }
}
