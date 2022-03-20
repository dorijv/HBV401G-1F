package hi.verkefni.test;

public class Flight {



    private int flightID;
    private String flightDeparture;
    private String flightDestination;
    private String flightDate;
    private String flightTime;

    public Flight(int flightID, String flightDeparture, String flightDestination, String flightDate, String flightTime){
        this.flightID = flightID;
        this.flightDeparture = flightDeparture;
        this.flightDestination = flightDestination;
        this.flightDate = flightDate;
        this.flightTime = flightTime;
    }
    public int getFlightID() {
        return flightID;
    }

    public String getFlightDeparture() {
        return flightDeparture;
    }

    public String getFlightDestination() {
        return flightDestination;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public String getFlightTime() {
        return flightTime;
    }
}
