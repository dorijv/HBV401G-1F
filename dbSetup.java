/*
Forrit sem frumstillir gagnagrunn.

Það vantar að implementa schema.sql uppsetningu, ef 1F.db er ekki til.

Til að keyra:

Búa til sqlite3 gagnagrunn 1F.db
Keyra java -cp sqlite-jdbc-3.36.0.3.jar dbSetup.java 0 0

Forritið býr til handahófskennd flug með random gildum. Hægt er að breyta 
lista af áfangastöðum og lista af flugfélögum. Einnig er hægt að breyta gildinu á FASTI
sem segir til um hversu mörg flug verða búin til.

TODO:
Frumstilla schema
kommenta betur
???

*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class dbSetup {
	private static final int FASTI = 1000; // Breyta hér f. fjölda prufugagna
	private static LocalDateTime startTime;
	private static LocalDateTime endTime;
	private static String[] destinations; // Líka f. departureLocation
	private static String[] flugfelog;
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");


	private static void configureData() {
		destinations = new String[] {"Reykjavík", "Akureyri", "Oslo", "Copenhagen",
						"Malmö", "Katowice", "Gdansk", "Berlin", "New York",
						"California", "Kyiv", "Moscow", "Frankfurt", "Munich",
						"Rome", "London", "Vilnius", "Seattle", "Toronto", "Helsinki",
						"Chicago", "Manchester", "Warsaw", "Riga", "Amsterdam"};

		flugfelog = new String[] {"FI", "W", "LS", "AY", "BT", "OG"};
		return;
	}

	private static LocalDateTime genRandTime(){
		return LocalDateTime.now().plusMinutes((long)genRandNum(10, 10000)); // Fikta í tölum
	}

	private static int genRandNum(int low, int high){
		return (int)(Math.random() * (high + 1 - low)) + low;
	}


	public static void main( String[] args ) throws Exception {
		// Configurations
		Class.forName("org.sqlite.JDBC");
		configureData(); // Köllum á hjálparfall, snyrtilegt
		String dep, dest, flfelag, depTime, destTime;
		int fjoldiDest, fjoldiFF, destIndex, depIndex, flugfelogIndex, flugnr;

		// Óþarfi en skoðum
		boolean USE_AUTOCOMMIT = args[0].equals("autocommit");
		boolean USE_INDEX = args[1].equals("index");

		// Tenging
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:1F.db"); // breyta nafn á db f. keyrslu
			conn.setAutoCommit(USE_AUTOCOMMIT);

			// Getum búið til útfrá schema?
			// Paste-a yfir eða hægt að sækja úr schema.sql?

			// Henda í INSERT Skipanir
			Statement stmt = conn.createStatement();
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO FLIGHTS VALUES(?,?,?,?,?,?,?)");

			// Stilla f. Random
			fjoldiFF = flugfelog.length - 1;
			fjoldiDest = destinations.length - 1;

			for (int i = 0; i < FASTI; i++){
				destIndex = genRandNum(0, fjoldiDest);
				depIndex = genRandNum(0, fjoldiDest);
				flugfelogIndex = genRandNum(0, fjoldiFF);
				flugnr = genRandNum(100, 999);
				dep = destinations[depIndex];
				dest = destinations[destIndex];
				flfelag = flugfelog[flugfelogIndex] + flugnr;
				startTime = genRandTime();
				endTime = startTime.plusMinutes((long)genRandNum(60, 600));
				depTime = startTime.format(formatter);
				destTime = endTime.format(formatter);

				pstmt.clearParameters();
				pstmt.setString(1, flfelag);
				pstmt.setString(2, dest);
				pstmt.setString(3, dep);
				pstmt.setString(4, depTime);
				pstmt.setString(5, destTime);
				pstmt.setInt(6, genRandNum(0,100));
				pstmt.setFloat(7, genRandNum(10000, 1000000));
				pstmt.executeUpdate();
			}
			if (!USE_AUTOCOMMIT) conn.commit();
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
}


}