/**
 * Flugbókanakerfi HBV401G Hópur 1F
 * Alda, Ármann, Halldór og Hrólfur
 */

package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.Flight;
import hi.verkefni.vinnsla.Booking;
import hi.verkefni.vinnsla.NotSecureHash;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FlightController {
    private static final float MAX_PRICE = 2147483647; // Maximum
    private static final int MIN_TRAVELLERS = 1; // Minimum
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private ArrayList<Flight> flightList = new ArrayList<>();
    private ArrayList<Booking> bookingList = new ArrayList<>();
    private static String[] destinations; // Líka f. departureLocation
    private static String[] flugfelog;

    /**
     * Smiður f. FlightController
     * Upphafsstillir gildi í lista flugfélaga og áfangastaða.
     */
    public FlightController() {
        destinations = new String[] {"Reykjavík", "Akureyri", "Vestmannaeyjar",
                "Kópavogur", "Ísafjörður", "Egilsstaðir",
                "Sauðárkrókur", "Keflavík", "Njarðvík", "Grindavík"};

        flugfelog = new String[] {"FI", "W", "IE", "AY", "BT", "OG", "PL"};
    }

    /**
     * Finna flug veisla
     *
     * @param dep String brottfarastaður
     * @param dest  String áfangastaður
     * @return ArrayList af Flight hlutum af viðeigandi flugum.
     *         ATH: ArrayList er tómur ef flug finnur ekki.
     * @throws Exception
     */
    public ArrayList<Flight> getFlight(String dep, String dest) throws Exception {
        return getFlight(dep, dest, MAX_PRICE, MIN_TRAVELLERS);
    }

    /**
     * Finna flug veisla
     *
     * @param dep String brottfarastaður
     * @param dest String áfangastaður
     * @param maxPrice Float hámarksverð
     * @return ArrayList af Flight hlutum af viðeigandi flugum.
     *         ATH: ArrayList er tómur ef flug finnur ekki.
     * @throws Exception
     */
    public ArrayList<Flight> getFlight(String dep, String dest, float maxPrice) throws Exception {
        return getFlight(dep, dest, maxPrice, MIN_TRAVELLERS);
    }

    /**
     * Finna flug veisla
     *
     * @param dep String brottfarastaður
     * @param dest String áfangastaður
     * @param travellers int lágmarks sætafjöldi
     * @return ArrayList af Flight hlutum af viðeigandi flugum.
     *         ATH: ArrayList er tómur ef flug finnst ekki.
     * @throws Exception
     */
    public ArrayList<Flight> getFlight(String dep, String dest, int travellers) throws Exception {
        return getFlight(dep, dest, MAX_PRICE, travellers);
    }

    /**
     * Finna flug veisla
     *
     * @param dep String Brottfararstaður
     * @param dest String Áfangastaður
     * @param maxPrice float Hámarks verð flugs
     * @param travellers int Lágmarksfjöldi lausra sæta.
     * @return ArrayList af Flight hlutum af viðeigandi flugum.
     *         ATH: ArrayList er tómur ef flug finnst ekki.
     * @throws Exception
     */
    public ArrayList<Flight> getFlight(String dep, String dest, float maxPrice, int travellers)
            throws Exception {
        // VINNSLA
        flightList.clear();

        if (maxPrice == 0.0) maxPrice = MAX_PRICE;

        Class.forName("org.sqlite.JDBC");

        // Tenging
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:1F.db"); // breyta nafn á db f. keyrslu
            Statement stmt = conn.createStatement();
            String ls = "SELECT * FROM FLIGHTS WHERE departureLoc LIKE ? AND destination LIKE ? " +
                    "AND price < ? AND availableSeats >= ? ORDER BY departureTime ASC";
            PreparedStatement pstmt = conn.prepareStatement(ls);
            pstmt.setString(1, dep);
            pstmt.setString(2, dest);
            pstmt.setFloat(3, maxPrice);
            pstmt.setInt(4, travellers);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                LocalDateTime temp1 = LocalDateTime.parse(rs.getString("arrivalTime"), formatter);
                LocalDateTime temp2 = LocalDateTime.parse(rs.getString("departureTime"), formatter);
                flightList.add(new Flight(rs.getString("FlightID"), rs.getString("departureLoc"),
                        rs.getString("destination"), temp2, temp1,
                        rs.getInt("availableSeats"), rs.getFloat("price")));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        for (Flight item : flightList) {
            System.out.println(item.toString());
        }

        return flightList;
    }

    /**
     * Fall sem skilar öllum flugum í gagnagrunninum.
     *
     * @return ArrayList af Flight hlutum af öllum flugum.
     *         ATH: ArrayList er tómur ef ekkert flug er í gagnagrunninum.
     * @throws Exception
     */
    public ArrayList<Flight> getAllFlights() throws Exception {
        flightList.clear();
        // SELECT * FROM FLIGHTS;
        // Mögulega tæma flightListß
        // Hérna fylla upp flightList m. sql
        // SELECT -> Búa Til Flight hlut -> Láta flightHlut í flightList
        // Configurations
        Class.forName("org.sqlite.JDBC");

        // Tenging
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:1F.db"); // breyta nafn á db f. keyrslu
            Statement stmt = conn.createStatement();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM FLIGHTS");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                LocalDateTime temp1 = LocalDateTime.parse(rs.getString("arrivalTime"), formatter);
                LocalDateTime temp2 = LocalDateTime.parse(rs.getString("departureTime"), formatter);
                flightList.add(new Flight(rs.getString("FlightID"), rs.getString("departureLoc"),
                        rs.getString("destination"), temp2, temp1,
                        rs.getInt("availableSeats"), rs.getFloat("price")));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println(flightList.get(0).getFlightID());
        for (Flight item : flightList) {
            System.out.println(item.toString());
        }
        return flightList;
    }

    /**
     * Fall sem skilar lista af áfangastöðum sem notaðir eru í gagnagrunninum
     * @return String[] áfangastaðir
     */
    public String[] getDestinations() {
        return destinations;
    }

    /**
     * Fall sem skilar lista af flugfélögum sem notuð eru í gagnagrunninum
     * @return String[] flugfélög
     */
    public String[] getFlugfelog() {
        return flugfelog;
    }

    /**
     * Fall sem býr til nýja bókun.
     *
     * @param SSN String kennitala viðskiptavinar
     * @param flightID String flugnúmer flugs
     * @param departureTime LocalDateTime á formi 'yyyy-MM-dd HH:mm:ss'
     * @param lastName String eftirnafn viðskiptavinar
     * @param firstName String fornafn viðskiptavinars
     * @return confirmationNO String staðfestingarnúmer
     *         Bókun bætist við í gagnagrunn.
     *         AvailableSeats í db lækkar um einn
     * @throws Exception
     */
    public String createBooking(String SSN, String flightID, LocalDateTime departureTime, String lastName,
                             String firstName) throws Exception {
        // Óvalið táknað 000#?
        return createBooking(SSN, flightID, departureTime, lastName, firstName, 000, '#');
    }

    /**
     * Fall sem býr til nýja bókun.
     *
     * @param SSN String kennitala viðskiptavinar
     * @param flightID String flugnúmer flugs
     * @param departureTime LocalDateTime á formi 'yyyy-MM-dd HH:mm:ss'
     * @param lastName String eftirnafn viðskiptavinar
     * @param firstName String fornafn viðskiptavinars
     * @return confirmationNO String staðfestingarnúmer
     *         Bókun bætist við í gagnagrunn.
     *         AvailableSeats í db lækkar um einn
     * @throws Exception
     */
    public String createBooking(String SSN, String flightID, LocalDateTime departureTime, String lastName,
                             String firstName, int rowNr, char seatNr) throws Exception {
        // Random hash function
        NotSecureHash hasher = new NotSecureHash();
        String confirmationNO = hasher.generateHash(12);
        int seats = 1;


        Class.forName("org.sqlite.JDBC");

        // Tenging
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:1F.db"); // breyta nafn á db f. keyrslu
            // Henda í INSERT Skipanir
            Statement stmt = conn.createStatement();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO BOOKINGS VALUES(?,?,?,?,?,?,?,?)");
            pstmt.clearParameters();
            pstmt.setString(1, SSN);
            pstmt.setString(2, flightID);
            pstmt.setString(3, departureTime.format(formatter));
            pstmt.setString(4, lastName);
            pstmt.setString(5, firstName);
            pstmt.setInt(6, rowNr);
            pstmt.setString(7, seatNr+"");
            pstmt.setString(8, confirmationNO);
            pstmt.executeUpdate();

            String update = "UPDATE Flights SET availableSeats = availableSeats-? WHERE FlightID LIKE ? AND departureTime LIKE ?";
            PreparedStatement pstmt2  = conn.prepareStatement(update);
            pstmt2.setInt(1, seats);
            pstmt2.setString(2, flightID);
            pstmt2.setString(3, departureTime.format(formatter));
            pstmt2.executeUpdate();


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return confirmationNO;
    }

    /**
     * Fall sem finnur bókanir í kerfi út frá kennitölu
     *
     * @param SSN String kennitala viðskiptavinar
     * @return ArrayList af Booking hlutum með viðeigandi bókunum
     * @throws Exception
     */
    public ArrayList<Booking> findBooking(String SSN) throws Exception {
        return findBooking(SSN, null);
    }

    /**
     * Fall sem finnur bókanir í kerfi útfrá kennitölu og flightID
     *
     * @param SSN String kennitala viðskiptavinar
     * @param FlightID String Flugnúmer flugs
     * @return ArrayList af Booking hlutum með viðeigandi bókunum
     * @throws Exception
     */
    public ArrayList<Booking> findBooking(String SSN, String FlightID) throws Exception {
        bookingList.clear();
        if (FlightID != null){
            // Select M/ FlightID
            Class.forName("org.sqlite.JDBC");

            // Tenging
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:1F.db"); // breyta nafn á db f. keyrslu
                Statement stmt = conn.createStatement();
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM BOOKINGS WHERE SSN LIKE ? AND FlightID LIKE ?");
                pstmt.setString(1, SSN);
                pstmt.setString(2,FlightID);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    LocalDateTime temp2 = LocalDateTime.parse(rs.getString("departureTime"), formatter);
                    bookingList.add(new Booking(rs.getString("SSN"), rs.getString("FlightID"),temp2,
                            rs.getString("lastName"), rs.getString("firstName"),
                            rs.getInt("row"), rs.getString("seat").charAt(0), rs.getString("confirmationNO")));
                }
                rs.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {
                try {
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
            System.out.println(bookingList.get(0).getFlightID());
            for (Booking item : bookingList) {
                System.out.println(item.toString());
            }

            return bookingList;
        }
        // SELECT * FROM FLIGHTS;
        // Mögulega tæma flightListß
        // Hérna fylla upp flightList m. sql
        // SELECT -> Búa Til Flight hlut -> Láta flightHlut í flightList
        // Configurations
        Class.forName("org.sqlite.JDBC");

        // Tenging
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:1F.db"); // breyta nafn á db f. keyrslu
            Statement stmt = conn.createStatement();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM BOOKINGS WHERE SSN LIKE ?");
            pstmt.setString(1, SSN);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                LocalDateTime temp2 = LocalDateTime.parse(rs.getString("departureTime"), formatter);
                bookingList.add(new Booking(rs.getString("SSN"), rs.getString("FlightID"),temp2,
                        rs.getString("lastName"), rs.getString("firstName"),
                        rs.getInt("row"), rs.getString("seat").charAt(0), rs.getString("confirmationNO")));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println(bookingList.get(0).getFlightID());
        for (Booking item : bookingList) {
            System.out.println(item.toString());
        }
        return bookingList;

    }
}
