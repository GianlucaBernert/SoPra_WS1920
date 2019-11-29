package de.hdm.SoPra_WS1920.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import de.hdm.SoPra_WS1920.shared.bo.Cinema;

/**
 * Mapper-Klasse, die <code>Cinema</code>-Objekte auf relationale Datenbank abbildet.
 * Anhand von den Methoden können Objekte gesucht, erzeugt, bearbeitet und gelöscht werden.
 * Objekte können in DB-Strukturen umgewandelt werden und DB-Strukturen in Objekte.
 * 
 * @author shila
 */
public class CinemaMapper {

    /**
     * Die Klasse CinemaMapper wird nur einmal instanziiert (Singleton-Eigenschaft).
     * Die folgende Variable ist durch den Bezeichner <code>static</code> nur einmal für 
     * alle Instanzen der Klasse vorhanden. Die einzige Instanz dieser Klasse wird darin gespeichert.
     */
     
	private static CinemaMapper cinemaMapper = null;

	/**
	 * Geschützter Konstruktor, der verhindert, dass mit dem new-Operator
	 * neue Instanzen der Klasse erstellt werden.
	 */

	protected CinemaMapper() {
    }

    /**
     *Folgende statische Methode sichert die Singleton-Eigenschaft.
     * Es wird dafür gesorgt, dass nur eine einzige Instanz von
     * <code>CinemaMapper</code> existiert.
     * SurveyMapper wird durch den Aufruf dieser statischen Methode instanziiert, 
     * nicht durch den new-Operator.
     * Aufruf der Methode durch: <code>CinemaMapper.cinemaMapper()</code>
     * 
     * @return <code>CinemaMapper</code>-Objekt
     */
     
    public static CinemaMapper cinemaMapper() {
    	if(cinemaMapper == null) {
      	   cinemaMapper = new CinemaMapper();
         }
         return cinemaMapper;
    }
    
    /**
     * @param id (Primärschlüssel-Attribut)
     * @return Cinema-Objekt, das dem übergebenen Schlüssel entspricht, null
     * bei nicht vorhandenem DB-Tupel.
     */
    public Cinema findCinemaByID(int id) {//Oder cinemaID?
    	//DB-Verbindung holen
    	Connection con = DBConnection.connection();
    	
    	try {
    		//Leeres SQL-Statement (JDBC) anlegen
    		Statement stmt = con.createStatement();
    		//Ausfüllen des Statements und als Query an die DB schicken
    		ResultSet rs = stmt.executeQuery("SELECT * FROM cinema" + "WHERE cinema.bo_id=" + id);
    		
    		//Da id Primärschlüssel ist, kann nur ein Tupel zurückgegeben werden.
    		//Es wird geprüft, ob ein Ergebnis vorliegt.
    		if(rs.next()) {
    			//Ergebnis-Tupel in Objekt umwandeln
    			Cinema c = new Cinema();
    			c.setName(rs.getString("name"));
    			c.setCity(rs.getString("city"));
    			c.setPostCode(rs.getString("postCode"));
    			c.setStreet(rs.getString("street"));
    			c.setStreetNo(rs.getString("streetNo"));
    			
    			
    			return c;
    		}
    	}
    	catch (SQLException e2) {
    		e2.printStackTrace();
    		
    		
    	}
        
    	return null;
        
    }

    /**
     * Einfügen eines <code>Cinema</code>-Objekts in die DB.
     * Prüfung und ggf. Korrektur des Primärschlüssels
     * @param survey das zu speichernde Objekt
     * @return das übergebene Objekt, mit ggf. korrigierter <code>id</code>.
     */
    public Cinema insertCinema(Cinema c) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt1 = con.createStatement();
        	Statement stmt2 = con.createStatement();
        	Statement stmt3 = con.createStatement();
        	/**
        	 * Prüfen, was der momentan höchste Primärschlüsselwert ist
        	 */
        	
        	 //ResultSet rs = stmt3.executeQuery("SELECT MAX(bo_id) AS maxid" + "FROM cinema");
        	
        	//Einzeilige Rückgabe
        	//if(rs.next()) {
        		//c erhält den bisher maximalen, um 1 inkrementierten Primärschlüssel.
        		
        		//c.setId(rs.getInt("maxid") + 1);
        		
        		stmt1 = con.createStatement();
        		stmt2 = con.createStatement();
        		stmt3 = con.createStatement();
        		//Business-Object
        		stmt1.executeUpdate("INSERT INTO businessobject(bo_id, creationTimeStamp)"
        		+ "VALUES ('"
        		+ c.getId()
        		+ "','"
        		+ c.getCreationTimeStamp() + "')");
        		//Ownership
        		stmt2.executeUpdate("INSERT INTO businessownership(bo_id, creationTimeStamp, personFK"
        		+ "VALUES ('"
        		+ c.getId()
        		+ "','"
        		+ c.getCreationTimeStamp()
        		+ "','"
        		+ c.getPersonFK() + "')");
        		//Tatsächliche Einfügeoperation eines Cinema-Objekts
        		stmt2.executeUpdate("INSERT INTO cinema(bo_id, name, city, postCode, street, streetNo, creationTimeStamp)" 
        		+ "VALUES ('" 
        		+ c.getId() 
        		+ "','" 
        		+ c.getName()
        		+ "','"
        		+ c.getCity() 
        		+ "','"
        		+ c.getPostCode()
        		+ "','"
        		+ c.getStreet()
        		+ "','" 
        		+ c.getStreetNo() 
        		+ "','"
        		+ c.getCreationTimeStamp() + "')");
        	
        }
        catch(SQLException e2) {
        	e2.printStackTrace();
        }
        /**
         * Rückgabe des evtl. korrigierten Kinos
         */
        return c;
    }
    	  

    
    /**
     * Ein Objekt wird wiederholt in die DB geschrieben.
     * 
     * @param c, das Objekt, das in die DB geschrieben werden soll
     * @return das Objekt, das als Parameter übergeben wird -> c
     */
    public Cinema updateCinema(Cinema c) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("UPDATE cinema" + "SET name=\"" + c.getName()
        	+ "\", " + "city=\"" + c.getCity() + "\", " + "postCode=\"" + c.getPostCode()  
        			+ "\", " + "street=\"" + c.getStreet() + "\", " + "streetNo=\"" + c.getStreetNo() + "\", " 
        	+ "WHERE bo_id=" + c.getId());
        	
        }
        catch(SQLException e2) {
        e2.printStackTrace();
       
    }
        return c;
    }
    
    /**
     * Löschen von Daten eines <code>Cinema</code>-Objekts aus der Datenbank
     * @param c, das zu löschende Objekt 
     */
    public void deleteCinema(Cinema c) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		Statement stmt1 = con.createStatement();
    		Statement stmt2 = con.createStatement();
    		Statement stmt3 = con.createStatement();
    		
    		stmt1.executeUpdate("DELETE FROM cinema" + "WHERE bo_id=" + c.getId());
    		//Ownership löschen anschließend
    		stmt2.executeUpdate("DELETE FROM businessownership WHERE bo_id=" + c.getId());
    		//Businessobject löschen
    		stmt3.executeUpdate("DELETE FROM businessobject WHERE bo_id=" + c.getId());
    		
    	}
    	catch(SQLException e2) {
    		e2.printStackTrace();
    	}
        
        
    }


    /**
     * Auslesen der Cinema-Objekte mit gegebenem Namen
     * @param name
     * 
     * @return Vektor mit Cinema-Objekten
     */
    public Vector<Cinema> findCinemaByName(String name) {
        Connection con = DBConnection.connection();
        Vector<Cinema> result = new Vector<Cinema>();
        
        try {
        	Statement stmt = con.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT * FROM cinema" 
        	+ "WHERE name= '" + name + "'");
        	//Für jeden Eintrag im Suchergebnis wird ein Cinema-Objekt erstellt
        	while(rs.next()) {
        		Cinema c = new Cinema();
        		c.setName(rs.getString("name"));
        		c.setCity(rs.getString("city"));
        		c.setPostCode(rs.getString("postCode"));
        		c.setStreet(rs.getString("street"));
        		c.setStreetNo(rs.getString("streetNo"));
        		
        		//Hinzufügen des neuen Objekts zum Ergebnisvektor
        		result.addElement(c);
        	}
        }
        	catch(SQLException e2) {
        		e2.printStackTrace();
        	}
        	//Rückgabe des Ergebnisvektors
        	return result;
        }

    /**
     * Auslesen der Cinema-Objekte mit gegebener Stadt
     * @param city 
     * @return Vektor mit Cinema-Objekten
     */
    public Vector<Cinema> findCinemaByCity(String city) {
        Connection con = DBConnection.connection();
        Vector<Cinema> result = new Vector<Cinema>();
        
        try {
        	Statement stmt = con.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT * FROM cinema" + "WHERE city= '" + city + "'");
        	//Für jeden Eintrag im Suchergebnis wird ein Cinema-Objekt erstellt
        	while(rs.next()) {
        		Cinema c = new Cinema();
        		c.setName(rs.getString("name"));
        		c.setCity(rs.getString("city"));
        		c.setPostCode(rs.getString("postCode"));
        		c.setStreet(rs.getString("street"));
        		c.setStreetNo(rs.getString("streetNo"));
        		
        		//Hinzufügen des neuen Objekts zum Ergebnisvektor
        		result.addElement(c);
        	}
        } catch(SQLException e2) {
        	e2.printStackTrace();
        }
        //Rückgabe des Ergebnisvektors
        return result;
    }

    /**
     * Auslesen der Cinema-Objekte mit gegebener Postleitzahl
     * @param postcode 
     * @return Vektor mit Cinema-Objekten
     */
    public Vector<Cinema> findByPostCode(String postCode) {
    	Connection con = DBConnection.connection();
    	Vector<Cinema> result = new Vector<Cinema>();
    	
    	try {
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT bo_id, name, city, postCode, street, streetNo, creationTimeStamp"
    				+ "FROM cinema" + "WHERE postCode= '" + postCode + "'");
    		
    		//Für jeden Eintrag im Suchergebnis wird ein Cinema-Objekt erstellt
    		while(rs.next()) {
    			Cinema c = new Cinema();
    			c.setName(rs.getString("name"));
    			c.setCity(rs.getString("city"));
    			c.setPostCode(rs.getString("postCode"));
    			c.setStreetNo(rs.getString("streetNo"));
    			
    			
    			//Hinzufügen des Objekts zum Ergebnisvektor
    			result.addElement(c);
    		}
    	} catch(SQLException e2) {
    		e2.printStackTrace();
    	}
    	//Rückgabe des Ergebnisvektors
    	return result;
        
    }

    /**
     * @param name
     */
    public void deleteCinemaByName(String name) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		Statement stmt = con.createStatement();
    		//SET SQL_SAFE_UPDATES = 0; als Einstellung in MySQL notwendig (in Query Editor eintippen)
    		stmt.executeUpdate("DELETE FROM cinema" + "WHERE name= '" + name + "'");
    		
    	}
    	catch(SQLException e2) {
    		e2.printStackTrace();
    	}
        
    }

    /**
     * @param person 
     * @return
     */
    public Vector<Cinema> findCinemaByPersonFK(int id) {
        Connection con = DBConnection.connection();
        Vector<Cinema> result = new Vector<Cinema>();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT cinema.bo_id, cinema.name, cinema.city, "
        			+ "cinema.postCode, cinema.street, cinema.streetNo, businessownership.personFK" +
        			"FROM  cinema INNER JOIN businessownership" + 
        			"ON cinema.bo_id = businessownership.bo_id AND businessownership.personFK= '" + id + "'");
        	
        	//Für jeden Eintrag im Suchergebnis wird ein Cinema-Objekt zugeordnet
        	while(rs.next()) {
        		Cinema c = new Cinema();
        		c.setName(rs.getString("name"));
        		c.setCity(rs.getString("city"));
        		c.setPostCode(rs.getString("postCode"));
        		c.setStreet(rs.getString("street"));
        		c.setStreetNo(rs.getString("streetNo"));
        		
        		
        		
        		//Hinzufügen des neuen Objekts zum Ergebnisvektor
        		result.addElement(c);
        	}
        }
        catch(SQLException e2) {
        	e2.printStackTrace();
        }
        return result;
        
    }

}