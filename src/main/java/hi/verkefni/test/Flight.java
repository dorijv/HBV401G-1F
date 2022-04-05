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

import java.time.LocalDateTime;

public class Flight {

    private int flightID;
    private String flightDeparture;
    private String flightDestination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    /**
     * Constructs a Flight object with specified information.
     *
     * @param flightID Identifaction number
     * @param flightDeparture Departure
     * @param flightDestination Destination
     * @param departureTime Time of departure. [YYYY-MM-DDTHH:MM:SS]
     * @param arrivalTime Time of arrival. [YYYY-MM-DDTHH:MM:SS]
     */
    public Flight( int flightID, String flightDeparture, String flightDestination,
                   LocalDateTime departureTime, LocalDateTime arrivalTime ) {
        this.flightID = flightID;
        this.flightDeparture = flightDeparture;
        this.flightDestination = flightDestination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
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
}
