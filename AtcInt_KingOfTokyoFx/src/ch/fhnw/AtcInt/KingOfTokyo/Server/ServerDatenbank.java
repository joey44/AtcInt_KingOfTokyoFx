package ch.fhnw.AtcInt.KingOfTokyo.Server;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @code responsible for the communication between the login and the database
 * @author Arcsuta
 *
 */

public class ServerDatenbank {

	private static final String DB_NAME = "atcint";

	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "1234";

	private static final String DB_SERVER = "127.0.0.1";
	private static final String DB_PORT = "3306";

	private static final String DBSTRING = "jdbc:mysql://" + DB_SERVER + ":"
			+ DB_PORT + "/" + DB_NAME + "?user=" + DB_USER + "&password="
			+ DB_PASSWORD;

	private static int i_runde = 15;
	private static String s_spielername = "Linda";
	private static int i_wuerfel1 = 5;
	private static int i_wuerfel2 = 5;
	private static int i_wuerfel3 = 5;
	private static int i_wuerfel4 = 5;
	private static int i_wuerfel5 = 5;
	private static int i_wuerfel6 = 5;
	private static int i_anzahlLeben = 0;
	private static int i_anzahlRuhmpunkte = 0;
	private static boolean b_standort;

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

				//System.out.println(SpielID);

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
	public static void TabelleErgebnis() throws Exception {

		try {
			for(int i=0; i<4; i++){
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			java.sql.Connection c = DriverManager.getConnection(DBSTRING);
			Statement stm = c.createStatement();

			stm.execute("INSERT INTO atcint.atcint_ergebnis(FK_SpielID, FK_Runde, FK_SpielerName, Leben, Ruhmpunkte, Standort) "
					+ "VALUES( '"
					+ SpielIDfinden()
					+ "' , '"
					+ getI_runde()
					+ "' , '"
					+ getS_spielername()
					+ "' , '"
					+ getI_anzahlLeben()
					+ "' , '"
					+ getI_anzahlRuhmpunkte()
					+ "' , '" + isB_standort() + "' );");

			stm.close();

		}}

		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

	}

	public static void ListeHighScore() throws Exception {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			java.sql.Connection c = DriverManager.getConnection(DBSTRING);
			Statement stm = c.createStatement();

			ResultSet result = stm
					.executeQuery("SELECT e.FK_SpielerName, SUM(e.Ruhmpunkte) AS Punktestand FROM atcint.atcint_ergebnis e "
							+ "GROUP BY e.FK_SpielerName "
							+ "ORDER BY Punktestand DESC LIMIT 10;");
			// result.next();

			while (result.next()) {
				String SpielerName = result.getString("e.FK_SpielerName");
				int Punktestand = result.getInt("Punktestand");
				String name = SpielerName + " " + Punktestand;
				System.out.println(name);

			}
			stm.close();

		}

		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

	}

	public static void main(String[] args) {
		try {
			TabelleErgebnis();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int getI_runde() {
		return i_runde;
	}

	public void setI_runde(int i_runde) {
		ServerDatenbank.i_runde = i_runde;
	}

	public static String getS_spielername() {
		return s_spielername;
	}

	public void setS_spielername(String s_spielername) {
		ServerDatenbank.s_spielername = s_spielername;
	}

	public static int getI_wuerfel1() {
		return i_wuerfel1;
	}

	public void setI_wuerfel1(int i_wuerfel1) {
		ServerDatenbank.i_wuerfel1 = i_wuerfel1;
	}

	public static int getI_wuerfel2() {
		return i_wuerfel2;
	}

	public void setI_wuerfel2(int i_wuerfel2) {
		ServerDatenbank.i_wuerfel2 = i_wuerfel2;
	}

	public static int getI_wuerfel3() {
		return i_wuerfel3;
	}

	public void setI_wuerfel3(int i_wuerfel3) {
		ServerDatenbank.i_wuerfel3 = i_wuerfel3;
	}

	public static int getI_wuerfel4() {
		return i_wuerfel4;
	}

	public void setI_wuerfel4(int i_wuerfel4) {
		ServerDatenbank.i_wuerfel4 = i_wuerfel4;
	}

	public static int getI_wuerfel5() {
		return i_wuerfel5;
	}

	public void setI_wuerfel5(int i_wuerfel5) {
		ServerDatenbank.i_wuerfel5 = i_wuerfel5;
	}

	public static int getI_wuerfel6() {
		return i_wuerfel6;
	}

	public void setI_wuerfel6(int i_wuerfel6) {
		ServerDatenbank.i_wuerfel6 = i_wuerfel6;
	}

	public static int getI_anzahlLeben() {
		return i_anzahlLeben;
	}

	public void setI_anzahlLeben(int i_anzahlLeben) {
		ServerDatenbank.i_anzahlLeben = i_anzahlLeben;
	}

	public static int getI_anzahlRuhmpunkte() {
		return i_anzahlRuhmpunkte;
	}

	public void setI_anzahlRuhmpunkte(int i_anzahlRuhmpunkte) {
		ServerDatenbank.i_anzahlRuhmpunkte = i_anzahlRuhmpunkte;
	}

	public static boolean isB_standort() {
		return b_standort;
	}

	public void setB_standort(boolean b_standort) {
		ServerDatenbank.b_standort = b_standort;
	}

}
