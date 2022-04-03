/******************************************************************************
 *  Team Assignment #4: Software Tests
 *  Group   : TEAM 1F
 *
 *  Class   : FlightControllerTest
 *  Desc    : A test fixture for our FlightController.
 *            Using JUnit to automate testing.
 *
 *  Members :   Email
 *  Alda    :   agp15@hi.is
 *  Ármann  :   ars90@hi.is
 *  Halldór :   hjv6@hi.is
 *  Hrólfur :   hrolfur@hi.is
 *****************************************************************************/

package hi.verkefni.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FlightControllerTest {

    private FlightController flightController;

    @Before
    public void setUp() {
        flightController = new FlightController();
    }

    @After
    public void tearDown() {
        flightController = null;
    }

    @Test
    public void searchReturnsResultList() {
        assertNotNull(flightController.getFlight("Reykjavík", "Akureyri", 27032022, 29032022));
    }

    @Test
    public void searchReturnsEmptyList() {
        Assert.assertEquals(List.of(), flightController.getFlight("Reykjavík", "Reykjavík", 27032022, 29032022));
    }

    @Test
    void testExpectedException() {
        ApplicationException thrown = Assertions.assertThrows(ApplicationException.class, () -> {
            flightController.getFlight("Reykjavík", 5379417, 27032022, 29032022);
        });
    }
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