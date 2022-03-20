package hi.verkefni.test;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.Before;
import static org.junit.Assert.*;

class FlightControllerTest {

    private Flight flug;
    private FlightMock mockFlug;

    @Before
    public void setUp(){

        mockFlug = new FlightMock();
        flug = mockFlug.getFlight(0);

    }

    @After
    public  void tearDown(){

        flug = null;

    }


    @Test
    public void testAmount(){

        assertEquals("Reykjavík", flug.getFlightID(), flug.getFlightDeparture()) ;

    }



}