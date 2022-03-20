package hi.verkefni.test;

import static org.junit.Assert.*;

class FlightControllerTest {

    private Flight Flug;
    private FlightMock mockFlug;

    @Before
    public void setUp(){

        mockFlug = new FlightMock();
        Flug = new Flight();

    }

    @After
    public  void tearDown(){

        Flug = null;

    }


    @Test
    public void testAmount(){

        assertEquals("Reykjavík", Flug.getFlightID(), Flug.getFlightDeparture()) ;

    }



}