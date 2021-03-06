package ch.fhnw.AtcInt.KingOfTokyo.Login;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @code responsible for the communication between the login and the database
 * @author Marianne
 *
 */

public class DBZugriff {
	
	private static final String DB_NAME = "atcint";

	private static final String DB_USER = "root";

	private static final String DB_PASSWORD = "1234";

	private static final String DB_SERVER = "127.0.0.1";
	private static final String DB_PORT = "3306";

	private static final String DBSTRING = "jdbc:mysql://" + DB_SERVER + ":"
			+ DB_PORT + "/" + DB_NAME + "?user=" + DB_USER + "&password="
			+ DB_PASSWORD;

	private String m_SpielerName = "";
	private String m_SpielerPasswort = "";
	
	public String SpielerName(){
		return m_SpielerName;
	}
	
	/* 
	 * Method SpielernameAvailable makes a connection to the database and checks
	 * if the string username is still available or not 
	 */
	public static boolean SpielernameAvailable(String username) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		java.sql.Connection c = DriverManager.getConnection(DBSTRING);
		Statement stm = c.createStatement();
		ResultSet rs = stm.executeQuery("SELECT * FROM atcint_spieler WHERE SpielerName = '"+username+"'");
		while (rs.next()) {
			return false;
			}
		
		stm.close();
		return true;
		}
	
	/*
	 * Method AddBenutzer creates a new user in the database after checking if the password and username
	 * are long enough. In case of an error, there will be an exception and a message on the console.
	 */

	public static DBZugriff AddBenutzer(String username, String password) throws Exception{
		DBZugriff benutzer = null;	
		
		if(username.length()<3)
			throw new Exception("Username muss mindestens 3 Zeichen haben");
		
		if(password.length()<4)
			throw new Exception("Passwort muss mindestens 4 Zeichen haben");
		
		try {
		      Class.forName("com.mysql.jdbc.Driver").newInstance();
		      java.sql.Connection c = DriverManager.getConnection(DBSTRING);
		      Statement stm = c.createStatement();
		      stm.execute("INSERT INTO atcint_spieler(SpielerName,SpielerPasswort) VALUES('"+username+"','"+ password +"');");
		      stm.close();
		      
		      benutzer = new DBZugriff();
		      benutzer.m_SpielerName = username;
		      benutzer.m_SpielerPasswort = password;
		    }
		
		catch (Exception e) {
			System.out.println("Exception: "+e.getMessage());
			}
		
		return benutzer;
		}
	
	/*
	 * Method Find search in the database for the combination of username and password.
	 * If it finds something within the database it creates a new instance of Benutzer and puts the value SpielerName
	 * to it. If the statement doesn't find any result, an exception is thrown and its text is showed on the console.
	 */
	public static DBZugriff Find(String username, String password){
		   try {
		      Class.forName("com.mysql.jdbc.Driver").newInstance();
		      java.sql.Connection c = DriverManager.getConnection(DBSTRING);
		      Statement stm = c.createStatement();
		      ResultSet rs = stm.executeQuery("SELECT * FROM atcint_spieler WHERE SpielerName = '"+username+"' AND SpielerPasswort = '"+ password +"'");
		      while (rs.next()) {
		    	  DBZugriff benutzer = new DBZugriff();
		    	  benutzer.m_SpielerName = rs.getString("SpielerName");
		    	  return benutzer;
		      }
		      
		      stm.close();
		      }
		   
		   catch (Exception e) {
		       System.out.println("Exception: "+e.getMessage());
		    }
		   
		   return null;
		   }
	
	
	public static String ListeHighScore() throws Exception {

		String listeHighScore = "";
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
				listeHighScore = listeHighScore + SpielerName + " "
						+ Punktestand + "\n";

			}
			stm.close();

		}

		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return listeHighScore;

	}

	}
