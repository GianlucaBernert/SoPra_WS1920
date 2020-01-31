package de.hdm.SoPra_WS1920.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;

import com.google.appengine.api.rdbms.AppEngineDriver;
import com.google.appengine.api.utils.SystemProperty;

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
	 * "jdbc:google:mysql://35.246.144.31:3306/popcorns"
	 * "jdbc:google:mysql://popcorns:europe-west3:popcorns/popcorns?user=root&password=SoPra1920&autoReconnect=true"
	 */
	
	private static String googleUrl = "jdbc:google:mysql://popcorns:europe-west3:popcorns/popcorns?user=root&password=SoPra1920&autoReconnect=true";
	private static String localUrl = "jdbc:mysql://35.246.144.31:3306/popcorns";
	
	private static String username = "root";
	private static String password = "SoPra1920";
	/**
	 * Methode zum erzeugen einer Verbindung zur Datenbank
	 */
	
	public static Connection connection() {
		String url = null;
		if (con == null) {
			try {
				
				DriverManager.registerDriver(new AppEngineDriver());
				if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
					// Load the class that provides the new
					// "jdbc:google:mysql://" prefix.
					// Class.forName("com.mysql.cj.jdbc.Driver");
					Class.forName("com.mysql.jdbc.GoogleDriver");
					url = googleUrl;
					con = DriverManager.getConnection(url);
				} else {
					// Local MySQL instance to use during development.
					Class.forName("com.mysql.jdbc.Driver");
					url = localUrl;
					con = DriverManager.getConnection(url, username, password);
				}
				
				

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