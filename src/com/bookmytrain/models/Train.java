package com.bookmytrain.models;

import java.util.ArrayList;

public class Train {

    private String train_number;
    private ArrayList<Coach> coaches;
    private Route route;

    public Train(String train_number, ArrayList<Coach> coaches, Route route) {
        this.train_number = train_number;
        this.coaches = coaches;
        this.route = route;
    }

    // getters
    public Route getTrainRoute() {
        return route;
    }

    @Override
    public String toString() {
        return "Train [" +
                "train_number='" + train_number + '\'' +
                ", coaches=" + coaches +
                ", route=" + route +
                ']';
    }
}
