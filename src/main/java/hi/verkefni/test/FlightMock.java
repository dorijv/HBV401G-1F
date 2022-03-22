/******************************************************************************
 *  Team Assignment #4: Software Tests
 *  Group   : TEAM 1F
 *
 *  Class   : FlightMock
 *  Desc    : A mock object to simulate storage component
 *            stores objects of type Flight in an ArrayList.
 *
 *  Members :   Email
 *  Alda    :   alda
 *  Ármann  :   ármann
 *  Halldór :   hjv6@hi.is
 *  Hrólfur :   hrolfur@hi.is
 *****************************************************************************/

package hi.verkefni.test;

import java.util.ArrayList;

public class FlightMock {
    private ArrayList<Flight> flights;

    /**
     * Vantar
     */
    public FlightMock() {
        flights = new ArrayList<>();
        // Breyta flightID
        flights.add( new Flight(1000, "Reykjavík", "Akureyri",
                "31032022", "08:00") );
        flights.add( new Flight(1000, "Akureyri", "Reykjavík",
                "31032022", "18:00") );
        flights.add( new Flight(1000, "Reykjavík", "Egilsstaðir",
                "31032022", "10:00") );
    }

    /**
     * Returns a specified Flight object, found at location i in arrayList
     * containing flights.
     *
     * @param i 0 <= i < length of arrayList<Flight>
     * @return flight Flight at specified location.
     */
    public Flight getFlight( int i ) {
        return flights.get(i);
    }

    /**
     * Loops through all flights in arrayList.
     * If Flight with specified departure and destination is found,
     * that flight object is returned. Otherwise null is returned.
     *
     * @param dep Departure
     * @param dest Destination
     * @return flug iff value is present, otherwise null.
     */
    public Flight getFlightRoute( String dep, String dest ) {
        for( Flight flug : flights ) {
            if( flug.getFlightDeparture().equals(dep) &&
                    flug.getFlightDestination().equals(dest) ) {
                return flug;
            }
        }
        return null;
    }
}
