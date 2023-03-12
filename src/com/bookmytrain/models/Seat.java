package com.bookmytrain.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Seat {
    private int coach_number;
    private int seat_number;
    private Map<String, List<SeatReservation>> seat_reservation; // <date, list of reserved seat for that date>

    public Seat(int coach_number, int seat_number) {
        this.coach_number = coach_number;
        this.seat_number = seat_number;
        this.seat_reservation = new HashMap<>();
    }

    public boolean isAvailable(String date, String from, String to) {

        List<SeatReservation> reservation_list = seat_reservation.get(date);

        // if map doesn't has entry for that date -> create entry and return true, bcz it has available seats

        if(!seat_reservation.containsKey(date)) {

            // create entry for that date
            seat_reservation.put(date, new ArrayList<>());

        }

        // some seats are reserved for that given date, check for seat availability for given route
        else {
            for(SeatReservation sr: reservation_list) {
                if(sr.getFrom().equals(from) && sr.getTo().equals(to)) {
                    // there is seat reserved for given route on the same date, return false
                    return false;
                }
            }

        }
        return true;
    }

    public Ticket reserveSeat(String date, String from, String to, Train t, String request) {

        List<SeatReservation> reservations = seat_reservation.get(date);
        reservations.add(new SeatReservation(date, from, to));

        // get total fair
        int total_fair = getTotalFair(request, t);
        Ticket ticket = new Ticket(total_fair);

        return ticket;
    }

    private static int getTotalFair(String request, Train t) {
        int fair = 0;

        String[] req = request.split(" ");
        String type = req[3];
        int number_of_passengers = Integer.parseInt(req[4]);
        int distance = t.getTrainRoute().getDistance();

        switch (type) {
            case "SL" -> fair = number_of_passengers * 1 * distance;
            case "3A" -> fair = number_of_passengers * 2 * distance;
            case "2A" -> fair = number_of_passengers * 3 * distance;
            case "1A" -> fair = number_of_passengers * 4 * distance;
        }

        return fair;
    }
}
