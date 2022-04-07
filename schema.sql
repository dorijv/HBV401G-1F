DROP TABLE IF EXISTS Flights CASCADE;
DROP TABLE IF EXISTS Bookings CASCADE;

CREATE TABLE Flights (
    FlightID VARCHAR(6),
    departureLoc VARCHAR(25),
    destination VARCHAR(25),
    departureTime DATETIME,
    arrivalTime DATETIME,
    availableSeats INTEGER,
    price FLOAT,
    PRIMARY KEY(FlightID, departureTime)
);

CREATE TABLE Bookings (
    SSN VARCHAR(10),
    FlightID CHAR(6),
    departureTime DATETIME,
    lastName VARCHAR(30),
    firstName VARCHAR(30),
    row INTEGER,
    seat CHAR(1),
    confirmationNO VARCHAR(12),
    FOREIGN KEY(FlightId, departureTime) REFERENCES Flights(FlightID, departureTime),
    PRIMARY KEY(SSN, FlightId, departureTime)
);
