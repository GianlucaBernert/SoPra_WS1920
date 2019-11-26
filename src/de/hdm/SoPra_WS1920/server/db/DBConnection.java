package de.hdm.SoPra_WS1920.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;

/**
 * @author Yesin Soufi
 
 * 
 * Wird von allen Mappern benutzt um die
 * Verbindung zur Datenbank zu erstellen
 **/
 
public class DBConnection {
	
	/**
	 * Sie speichert die einzige Instanz dieser Klasse. Sie ist durch static nur
	 * einmal für alle sämtlichen Instanzen dieser Klasse vorhanden
	 */
	
	private static Connection con = null;

	/**
	 * Die URL, mit deren Hilfe die Datenbank angesprochen wird
	 * 
	 */
	
	private static String googleUrl = "";
	private static String localUrl = "jdbc:mysql://localhost/YOURNAME?user=root&password=PASSWORD&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	/**
	 * Methode zum erzeugen einer Verbindung zur Datenbank
	 */
	
	public static Connection connection() {
		
		if (con == null) {
			try {

				Class.forName("com.mysql.cj.jdbc.Driver"); 
				con = DriverManager.getConnection(localUrl);
				

				Statement stmt = con.createStatement();
				System.out.println("DB Connection!");
				

			} catch (Exception e) {
				con = null;
				e.printStackTrace();
			}
		}

		// Zurückgegeben der Verbindung
		return con;
	}
    

}