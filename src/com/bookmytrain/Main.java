package com.bookmytrain;

import com.bookmytrain.exceptions.SeatNotAvailableException;
import com.bookmytrain.exceptions.TrainNotAvailableException;
import com.bookmytrain.models.Coach;
import com.bookmytrain.models.Route;
import com.bookmytrain.models.Seat;
import com.bookmytrain.models.Train;
import com.bookmytrain.models.Ticket;

import java.util.*;

public class Main {

    private static int tickets_generated = 0;

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
                int[] check_for_train = checkIfTrainAvailable(request); // boolean, train index
                int train_index = check_for_train[1];

                if(check_for_train[0] == 0) {
                    throw new TrainNotAvailableException("No Trains Available");
                }

                // check for seat availability, if available then book it
                Ticket t = checkIfSeatsAvailableOrBook(request, train_index);
                if(t != null) ++tickets_generated;
                System.out.println((Ticket.getPNR() + tickets_generated) + " " + t.getTotal_fair());

            } catch (TrainNotAvailableException e) {
                System.out.println(e.getMessage());
            } catch (SeatNotAvailableException e) {
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
        Map<String, List<Coach>> coaches = getCoaches(inp_train_coaches);
        Route route = getTrainRoute(inp_train_route);

        Train train = new Train(train_number, coaches, route);

        train_list.add(train);
    }

    // extract train number
    private static String getTrainNumber(String train_route) {
        return train_route.split(" ")[0];
    }

    // extract coaches & return list of all coaches
    private static Map<String, List<Coach>> getCoaches(String train_coaches) {
        String[] default_string =  train_coaches.split(" ");

        Map<String, List<Coach>> coaches = new HashMap<>();

        List<Coach> s = new ArrayList<>();
        List<Coach> b = new ArrayList<>();
        List<Coach> a = new ArrayList<>();
        List<Coach> h = new ArrayList<>();

        for(int i = 1 ; i<default_string.length; i++) {
            String[] words = default_string[i].split("-");

            String coach = words[0];
            String coach_type = words[0].substring(0,1);
            int coach_number = Integer.parseInt(words[0].substring(1));
            int seats = Integer.parseInt(words[1]);

            // putting values into map
            if(coach_type.equals("S")) {
                s.add(new Coach(coach, "SL", coach_number, seats));
                coaches.put(coach_type, s);
            }
            else if (coach_type.equals("B")) {
                b.add(new Coach(coach, "3A", coach_number, seats));
                coaches.put(coach_type, b);
            }
            else if (coach_type.equals("A")) {
                a.add(new Coach(coach, "2A", coach_number, seats));
                coaches.put(coach_type, a);
            }
            else if (coach_type.equals("H")) {
                h.add(new Coach(coach, "1A", coach_number, seats));
                coaches.put(coach_type, h);
            }

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
    private static int[] checkIfTrainAvailable(String request) {
        String[] req = request.split(" ");
        String req_source = req[0];
        String req_dest = req[1];


        for(int i = 0 ; i<train_list.size() ; i++) {
            if(train_list.get(i).getTrainRoute().getRoute().equals(req_source + " " + req_dest)) {
                return new int[] {1, i};
            }
        }

        return new int[] {0, -1};
    }

    // checks if requested seats are available or not
    public static Ticket checkIfSeatsAvailableOrBook(String request, int train_index) throws SeatNotAvailableException {
        String[] req = request.split(" ");

        String date = req[2];

        String from = req[0];
        String to = req[1];

        String coach_type = req[3];

        Train requested_train = train_list.get(train_index);

        List<Coach> coachList = new ArrayList<>();

        switch (coach_type) {
            case "SL" -> coachList = requested_train.getCoachesByType("S");
            case "3A" -> coachList = requested_train.getCoachesByType("B");
            case "2A" -> coachList = requested_train.getCoachesByType("A");
            case "1A" -> coachList = requested_train.getCoachesByType("H");
        }

        Ticket t = new Ticket();

        for(Coach c: coachList) {
            List<Seat> seat_list = c.getSeats();
            for(Seat s: seat_list) {
                if(!s.isAvailable(date, from, to)) {
                    throw new SeatNotAvailableException("No Seats Available");
                }
                else {
                    // if available then reserve it
                    t = s.reserveSeat(date, from, to, train_list.get(train_index), request);
                }
            }
        }

        return t;
    }

}
