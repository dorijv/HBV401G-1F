package hi.verkefni.vinnsla;

import java.time.LocalDateTime;

public class Booking {
    private String SSN;
    private String flightID;
    private LocalDateTime departureTime;
    private String lastName;
    private String firstName;
    private int rowNr;
    private char seatNr;
    private String confirmationNO;

    public Booking(String SSN, String flightID, LocalDateTime departureTime, String lastName,
                   String firstName, int rowNr, char seatNr, String confirmationNO) {
        this.SSN = SSN;
        this.flightID = flightID;
        this.departureTime = departureTime;
        this.lastName = lastName;
        this.firstName = firstName;
        this.rowNr = rowNr;
        this.seatNr = seatNr;
        this.confirmationNO = confirmationNO;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getRowNr() {
        return rowNr;
    }

    public void setRowNr(int rowNr) {
        this.rowNr = rowNr;
    }

    public char getSeatNr() {
        return seatNr;
    }

    public void setSeatNr(char seatNr) {
        this.seatNr = seatNr;
    }

    public String getConfirmationNO() {
        return confirmationNO;
    }

    public void setConfirmationNO(String confirmationNO) {
        this.confirmationNO = confirmationNO;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "SSN='" + SSN + '\'' +
                ", flightID='" + flightID + '\'' +
                ", departureTime=" + departureTime +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", rowNr=" + rowNr +
                ", seatNr=" + seatNr +
                ", confirmationNO=" + confirmationNO +
                '}';
    }
}
