package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.Flight;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;


public class FlightController {
    private static final float MAX_PRICE = 2147483647; // Maximum
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private ArrayList<Flight> flightList = new ArrayList<>();

    public FlightController() {
    }

    // maxPrice, noOfTravelers, dagsetningu flugs og auðvitað hvaðan flugið á að koma
    public ArrayList<Flight> getFlight(String dep, String dest) {
        System.out.println("Ekki price!");
        return flightList;
    }

    public ArrayList<Flight> getFlight(String dep, String dest, float maxPrice) {
        System.out.println("price!");
        return flightList;
    }

    public ArrayList<Flight> getFlight(String dep, String dest, float maxPrice, int travelers) {

    }


    public ArrayList<Flight> getAllFlights() throws Exception {
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
}
