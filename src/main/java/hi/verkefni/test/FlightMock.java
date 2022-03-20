package hi.verkefni.test;

import java.util.ArrayList;

public class FlightMock {
    private ArrayList<Flight> flight;

    public Flight getFlight (int i ){
        return flight.get(i);
    }

    public FlightMock(){
        flight = new ArrayList<>();

        flight.add (new Flight(1000, "Reykjavík", "Akureyri", "31032022", "08:00"));
        flight.add (new Flight(1000, "Akureyri", "Reykjavík", "31032022", "18:00"));
        flight.add (new Flight(1000, "Reykjavík", "Egilsttaðir", "31032022", "10:00"));
    }
}
