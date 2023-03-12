package com.bookmytrain.models;

import java.util.*;

public class Train {

    private String train_number;
    private Map<String, List<Coach>> coaches; // <coach type, list of coaches of that type>
    private Route route;

    public Train(String train_number, Map<String, List<Coach>> coaches, Route route) {
        this.train_number = train_number;
        this.coaches = coaches;
        this.route = route;
    }

    // getters
    public Route getTrainRoute() {
        return route;
    }

    public Map<String, List<Coach>> getCoaches() {
        return this.coaches;
    }

    public List<Coach> getCoachesByType(String type) {
        return coaches.get(type);
    }

    @Override
    public String toString() {
        return "Train [" +
                "train_number='" + train_number + '\'' +
                ", coaches=" + coaches.keySet() +
                ", route=" + route +
                ']';
    }
}
