package ch.fhnw.AtcInt.KingOfTokyo.Server;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import ch.fhnw.AtcInt.KingOfTokyo.DatenAustausch.DatenAustausch;

/**
 * @code responsible for the communication between the login and the database
 * @author Arcsuta
 *
 */

public class ServerDatenbank {

	private static final String DB_NAME = "atcint";

	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";

	private static final String DB_SERVER = "127.0.0.1";
	private static final String DB_PORT = "3306";

	private static final String DBSTRING = "jdbc:mysql://" + DB_SERVER + ":"
			+ DB_PORT + "/" + DB_NAME + "?user=" + DB_USER + "&password="
			+ DB_PASSWORD;


	
	public static void SpielStartZeit() throws Exception {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			java.sql.Connection c = DriverManager.getConnection(DBSTRING);
			Statement stm = c.createStatement();
			stm.execute("INSERT INTO atcint.atcint_spiel(Spielbeginn) VALUES(CURRENT_TIMESTAMP());");
			stm.close();
		}

		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

	}

	public static int SpielIDfinden() throws Exception {

		int SpielIDfinden = 0;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			java.sql.Connection c = DriverManager.getConnection(DBSTRING);
			Statement stm = c.createStatement();

			ResultSet result = stm
					.executeQuery("SELECT SpielID FROM atcint.atcint_spiel WHERE Spielende = Spielbeginn;");

			while (result.next()) {
				int SpielID = result.getInt("SpielID");

				// System.out.println(SpielID);

				SpielIDfinden = SpielID;
			}
			stm.close();
		}

		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return SpielIDfinden;

	}

	public static void SpielEndZeit() throws Exception {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			java.sql.Connection c = DriverManager.getConnection(DBSTRING);
			Statement stm = c.createStatement();
			stm.execute("UPDATE atcint.atcint_spiel SET Spielende = NOW()WHERE spielID = '"
					+ SpielIDfinden() + "' ");
			stm.close();
		}

		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

	}

	/*
	 * public static void TabelleRunde() throws Exception {
	 * 
	 * try { Class.forName("com.mysql.jdbc.Driver").newInstance();
	 * java.sql.Connection c = DriverManager.getConnection(DBSTRING); Statement
	 * stm = c.createStatement();
	 * 
	 * stm.execute(
	 * "INSERT INTO atcint.atcint_runde(RundeID, FK_SpielID, FK_SpielerName, FK_WuerfelID_1, FK_WuerfelID_2, FK_WuerfelID_3, FK_WuerfelID_4, FK_WuerfelID_5, FK_WuerfelID_6) "
	 * + "VALUES( '" + getI_runde() + "' , '" + getI_spielID() + "' , '" +
	 * getS_spielername() + "', '" + getI_wuerfel1() + "' , '" + getI_wuerfel2()
	 * + "' , '" + getI_wuerfel3() + "' , '" + getI_wuerfel4() + "' , '" +
	 * getI_wuerfel5() + "' , '" + getI_wuerfel6() + "' );");
	 * 
	 * stm.close();
	 * 
	 * }
	 * 
	 * catch (Exception e) { System.out.println("Exception: " + e.getMessage());
	 * }
	 * 
	 * }
	 */
	public static void TabelleErgebnis(DatenAustausch d) throws Exception {

		int spielID = SpielIDfinden();
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			java.sql.Connection c = DriverManager.getConnection(DBSTRING);
			Statement stm = c.createStatement();
			for (int i = 0; i < 4; i++) {
				stm.execute("INSERT INTO atcint.atcint_ergebnis(FK_SpielID, FK_Runde, FK_SpielerName, Leben, Ruhmpunkte, Standort) "
						+ "VALUES( '"
						+ spielID
						+ "' , '"
						+ d.getwCounter()/3
						+ "' , '"
						+ d.getSpielerByID(i).getSpielerName()
						+ "' , '"
						+ d.getSpielerByID(i).getAnzahlLeben()
						+ "' , '"
						+ d.getSpielerByID(i).getAnzahlRuhmpunkte()
						+ "' , '"
						+ d.getSpielerByID(i).isAufTokyo()
						+ "' );");
			}
			stm.close();

		}

		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

	}

	
	// testMain
//	public static void main(String[] args) {
//		try {
//			SpielStartZeit();
//			SpielEndZeit();
//			TabelleErgebnis();
//			System.out.println(ListeHighScore());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}



}
