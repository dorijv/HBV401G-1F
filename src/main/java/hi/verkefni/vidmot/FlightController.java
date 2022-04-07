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
    private static String[] destinations; // Líka f. departureLocation
    private static String[] flugfelog;

    public FlightController() {
        destinations = new String[] {"Reykjavík", "Akureyri", "Vestmannaeyjar",
                "Kópavogur", "Ísafjörður", "Egilsstaðir",
                "Sauðárkrókur", "Keflavík", "Njarðvík", "Grindavík"};

        flugfelog = new String[] {"FI", "W", "IE", "AY", "BT", "OG", "PL"};
    }

    // maxPrice, noOfTravelers, dagsetningu flugs og auðvitað hvaðan flugið á að koma
    public ArrayList<Flight> getFlight(String dep, String dest) throws Exception {
        return getFlight(dep, dest, MAX_PRICE, MIN_TRAVELLERS);
    }

    public ArrayList<Flight> getFlight(String dep, String dest, float maxPrice) throws Exception {
        return getFlight(dep, dest, maxPrice, MIN_TRAVELLERS);
    }

    public ArrayList<Flight> getFlight(String dep, String dest, int travellers) throws Exception {
        return getFlight(dep, dest, MAX_PRICE, travellers);
    }

    public ArrayList<Flight> getFlight(String dep, String dest, float maxPrice, int travellers)
            throws Exception {
        // VINNSLA
        flightList.clear();

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

    public String[] getDestinations() {
        return destinations;
    }

    public String[] getFlugfelog() {
        return flugfelog;
    }

    public String createBooking(String SSN, String flightID, LocalDateTime departureTime, String lastName,
                             String firstName) throws Exception {
        // Óvalið táknað 000#?
        return createBooking(SSN, flightID, departureTime, lastName, firstName, 000, '#');
    }
    // Fjöldi seats eða hvert sæti ein bókun?
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

            String update = ("UPDATE Flights SET availableSeats = availableSeats-? WHERE FlightID LIKE ? AND departureTime LIKE ?");
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
}
