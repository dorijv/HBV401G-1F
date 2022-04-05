package hi.verkefni.vinnsla;

import java.time.LocalDateTime;

public class Flight {
    private String FlightID;
    private String departureLoc;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int availableSeats;
    private float price;

    public Flight(String FlightID, String departureLoc, String destination,
                  LocalDateTime departureTime, LocalDateTime arrivalTime,
                  int availableSeats, float price){
        this.FlightID = FlightID;
        this.departureLoc = departureLoc;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeats = availableSeats;
        this.price = price;
    }

    public String getFlightID() {
        return FlightID;
    }

    public String getDepartureLoc() {
        return departureLoc;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public float getPrice() {
        return price;
    }

    public String toString() {
        return FlightID + " " + departureLoc + " " + destination + " " + departureTime + " " + arrivalTime +
                " " + availableSeats + " " + price + " ";
    }
}
