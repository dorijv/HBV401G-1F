/******************************************************************************
 *  Team Assignment #4: Software Tests
 *  Group   : TEAM 1F
 *
 *  Class   : FlightControllerTest
 *  Desc    : A test fixture for our FlightController.
 *            Using JUnit to automate testing.
 *
 *  Members :   Email
 *  Alda    :   alda
 *  Ármann  :   ármann
 *  Halldór :   hjv6@hi.is
 *  Hrólfur :   hrolfurjul@gmail.com
 *****************************************************************************/

package hi.verkefni.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FlightControllerTest {

    private Flight firstFlight, secondFlight, thirdFlight;
    private FlightMock mockFlug;

    @Before
    public void setUp() {
        mockFlug = new FlightMock();
        firstFlight = mockFlug.getFlight(0);
        secondFlight = mockFlug.getFlight(1);
        thirdFlight = mockFlug.getFlight(2);
    }

    @After
    public void tearDown() {
        firstFlight = null;
        secondFlight = null;
        thirdFlight = null;
    }

    @Test
    public void testDeparture() {
        // Case 1: ???
        assertEquals("Reykjavík -> Akureyri", "Reykjavík",
                firstFlight.getFlightDeparture());
        assertEquals("Akureyri -> Reykjavík", "Akureyri",
                secondFlight.getFlightDeparture());
        assertEquals("Reykjavík -> Egilsstaðir", "Reykjavík",
                thirdFlight.getFlightDeparture());
    }

    @Test
    public void testDestination() {
        assertEquals("Reykjavík -> Akureyri", "Akureyri2",
                firstFlight.getFlightDestination());
        assertEquals("Akureyri -> Reykjavík", "Reykjavík",
                secondFlight.getFlightDestination());
        assertEquals("Reykjavík -> Egilsstaðir", "Egilsstaðir",
                thirdFlight.getFlightDestination());
    }

    @Test
    public void testRoute() {
        assertNotNull("Reykjavík -> Akureyri", mockFlug.getFlightRoute("Reykjavík", "Akureyri"));
        assertNull("New York -> Kyiv", mockFlug.getFlightRoute("New York", "Kyiv"));
    }

    // Bæta við fleirri testum, til dæmis date og tíma? Spyrja Marcelo
}