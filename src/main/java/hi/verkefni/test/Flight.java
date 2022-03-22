/******************************************************************************
 *  Team Assignment #4: Software Tests
 *  Group   : TEAM 1F
 *
 *  Class   : Flight
 *  Desc    : A mock object designed to replicate our end object
 *            as precisely as possible.
 *            Disclaimer: May undergo changes in close future.
 *
 *  Members :   Email
 *  Alda    :   alda
 *  Ármann  :   ármann
 *  Halldór :   hjv6@hi.is
 *  Hrólfur :   hrolfur@hi.is
 *****************************************************************************/

package hi.verkefni.test;

public class Flight {

    private int flightID;
    private String flightDeparture;
    private String flightDestination;
    private String flightDate;
    private String flightTime;

    /**
     * Constructs a Flight object with specified information.
     *
     * @param flightID Identifaction number
     * @param flightDeparture Departure
     * @param flightDestination Destination
     * @param flightDate Date of flight
     * @param flightTime Time of flight
     */
    public Flight( int flightID, String flightDeparture, String flightDestination,
                   String flightDate, String flightTime ) {
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
