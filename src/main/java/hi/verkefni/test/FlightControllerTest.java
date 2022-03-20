package hi.verkefni.test;

import static org.junit.Assert.*;

class FlightControllerTest {

    private FlightMock mockFlug;

    @Before
    public void setUp{

        mockFlug = new FlightMock();

    }

    @After
    public  void tearDown{

        mockFlug = null;

    }


    @Test
    public void testAmount{

        assertEquals("Reykjavík“, mockFlug.getFlight(0).getDeparture());

    }



}