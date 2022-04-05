package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.Flight;

public class FlightController {
    private int MAXN = 7500;
    public Flight[] flightList;

    public FlightController() {
        flightList = new Flight[MAXN];
    }

    public Flight[] getAllFlights(){
        // SELECT * FROM FLIGHTS;
        // Mögulega tæma flightListß
        // Hérna fylla upp flightList m. sql
        // SELECT -> Búa Til Flight hlut -> Láta flightHlut í flightList
        return flightList;
    }
}
