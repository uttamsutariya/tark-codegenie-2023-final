package com.bookmytrain.models;

import com.bookmytrain.exceptions.SeatNotAvailableException;
import com.bookmytrain.exceptions.TrainNotAvailableException;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // get the train input
    private static Scanner scanner = new Scanner(System.in);

    // list to contain train details
    private static ArrayList<Train> train_list = new ArrayList<>();

    public static void main(String[] args) {

        // reading input
        int number_of_trains = scanner.nextInt();

        // flushing console to read string
        scanner.nextLine();

        while (number_of_trains != 0) {
            readTrainDetails();
            number_of_trains--;
        }

        // after reading train -> read booking request
        while(true) {
            String request = scanner.nextLine();
            try {

                // check for train availability for that route
                if(!checkIfTrainAvailable(request)) {
                    throw new TrainNotAvailableException("No Trains Available");
                }

                // check for seat availability
                if(!checkIfSeatsAvailable(request)) {
                    throw new SeatNotAvailableException("No Seats Available");
                }


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * train input handlers
     */
    private static void readTrainDetails() {
        String inp_train_route = scanner.nextLine();
        String inp_train_coaches = scanner.nextLine();

        // extracting & getting details to create train
        String train_number = getTrainNumber(inp_train_route);
        ArrayList<Coach> coaches = getCoaches(inp_train_coaches);
        Route route = getTrainRoute(inp_train_route);

        Train train = new Train(train_number, coaches, route);

        train_list.add(train);
    }

    // extract train number
    private static String getTrainNumber(String train_route) {
        return train_route.split(" ")[0];
    }

    // extract coaches & return list of all coaches
    private static ArrayList<Coach> getCoaches(String train_coaches) {
        String[] default_string =  train_coaches.split(" ");

        ArrayList<Coach> coaches = new ArrayList<>();

        for(int i = 1 ; i<default_string.length ; i++) {

            String[] default_string_words = default_string[i].split("-");

            String coach_type = default_string_words[0];
            int seats_in_coach = Integer.parseInt(default_string_words[1]);

            // creating & adding coach to the list
            coaches.add(new Coach(coach_type, seats_in_coach));
        }

        return coaches;
    }

    private static Route getTrainRoute(String train_route) {
        String source = getSource(train_route);
        String destination = getDestination(train_route);
        int distance = getDistance(source, destination);

        return new Route(source, destination, distance);
    }

    // extract source from route
    private static String getSource(String train_route) {
        return train_route.split(" ")[1];
    }

    // extract destination from route
    private static String getDestination(String train_route) {
        return train_route.split(" ")[2];
    }

    // extract distance
    private static int getDistance(String source, String destination) {
        int start = Integer.parseInt(source.split("-")[1]);
        int end = Integer.parseInt(destination.split("-")[1]);

        return end - start;
    }

    /**
     * request handlers
     */

    // checks if requested train is available or not
    private static boolean checkIfTrainAvailable(String request) {
        String[] req = request.split(" ");
        String req_source = req[0];
        String req_dest = req[1];

        boolean isAvailable = false;

        for(Train t : train_list) {
            if(t.getTrainRoute().getRoute().equals(req_source + " " + req_dest)) {
                isAvailable = true;
            }
        }

        return isAvailable;
    }

    // checks if requested seats are available or not
    public static boolean checkIfSeatsAvailable(String request) {
        return false;
    }
}
