package de.hdm.SoPra_WS1920.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.Person;

/**
 * Mapper-Klasse, die <code>Cinema</code>-Objekte auf relationale Datenbank abbildet.
 * Anhand von den Methoden kï¿½nnen Objekte gesucht, erzeugt, bearbeitet und gelï¿½scht werden.
 * Objekte kï¿½nnen in DB-Strukturen umgewandelt werden und DB-Strukturen in Objekte.
 * 
 * @author shila
 */
public class CinemaMapper {

    /**
     * Die Klasse CinemaMapper wird nur einmal instanziiert (Singleton-Eigenschaft).
     * Die folgende Variable ist durch den Bezeichner <code>static</code> nur einmal fï¿½r 
     * alle Instanzen der Klasse vorhanden. Die einzige Instanz dieser Klasse wird darin gespeichert.
     */
     
	private static CinemaMapper cinemaMapper = null;

	/**
	 * Geschï¿½tzter Konstruktor, der verhindert, dass mit dem new-Operator
	 * neue Instanzen der Klasse erstellt werden.
	 */

	protected CinemaMapper() {
    }

    /**
     *Folgende statische Methode sichert die Singleton-Eigenschaft.
     * Es wird dafï¿½r gesorgt, dass nur eine einzige Instanz von
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
     * @param id (Primï¿½rschlï¿½ssel-Attribut)
     * @return Cinema-Objekt, das dem ï¿½bergebenen Schlï¿½ssel entspricht, null
     * bei nicht vorhandenem DB-Tupel.
     */
    public Cinema findCinemaByID(int id) {//Oder cinemaID?
    	//DB-Verbindung holen
    	Connection con = DBConnection.connection();
    	
    	try {
    		//Leeres SQL-Statement (JDBC) anlegen
    		Statement stmt = con.createStatement();
    		//Ausfï¿½llen des Statements und als Query an die DB schicken
    		ResultSet rs = stmt.executeQuery("SELECT * FROM cinema " + "WHERE id= " + id);
    		
    		//Da id Primï¿½rschlï¿½ssel ist, kann nur ein Tupel zurï¿½ckgegeben werden.
    		//Es wird geprï¿½ft, ob ein Ergebnis vorliegt.
    		if(rs.next()) {
    			//Ergebnis-Tupel in Objekt umwandeln
    			Cinema c = new Cinema();
    			c.setName(rs.getString("name"));
    			c.setCity(rs.getString("city"));
    			c.setZipCode(rs.getString("zipCode"));
    			c.setStreet(rs.getString("street"));
    			c.setStreetNo(rs.getString("streetNo"));
    			c.setCinemaChainFK(rs.getInt("cinemachainFK"));
    			c.setId(rs.getInt("id"));
    			
    			return c;
    		}
    	}
    	catch (SQLException e2) {
    		e2.printStackTrace();
    		
    		
    	}
        
    	return null;
        
    }

    /**
     * Einfï¿½gen eines <code>Cinema</code>-Objekts in die DB.
     * Prï¿½fung und ggf. Korrektur des Primï¿½rschlï¿½ssels
     * @param survey das zu speichernde Objekt
     * @return das ï¿½bergebene Objekt, mit ggf. korrigierter <code>id</code>.
     */
    public Cinema insertCinema(Cinema c) {
        Connection con = DBConnection.connection();
        
        try {
        	con.setAutoCommit(false);
        	Statement stmt = con.createStatement();
        	
        		//Tatsï¿½chliche Einfï¿½geoperation eines Cinema-Objekts
        		stmt.executeUpdate("INSERT INTO cinema(id, name, zipCode, city, street, streetNo, cinemachainFK)" 
        		+ "VALUES ('" 
        		+ c.getId() 
        		+ "','" 
        		+ c.getName()
        		+ "','"
        		+ c.getZipCode()
        		+ "','"
        		+ c.getCity() 
        		+ "','"
        		+ c.getStreet()
        		+ "','" 
        		+ c.getStreetNo() 
        		+ "','"
        		+ c.getCinemaChainFK()
        		 + "')");
        	
        
        	con.setAutoCommit(true);
    	}
        catch(SQLException e2) {
        	e2.printStackTrace();
        }
        /**
         * Rï¿½ckgabe des evtl. korrigierten Kinos
         */
        return c;
    }
    	  

    
    /**
     * Ein Objekt wird wiederholt in die DB geschrieben.
     * 
     * @param c, das Objekt, das in die DB geschrieben werden soll
     * @return das Objekt, das als Parameter ï¿½bergeben wird -> c
     */
    public Cinema updateCinema(Cinema c) {
        Connection con = DBConnection.connection();
        
        try {
        	con.setAutoCommit(false);
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("UPDATE cinema SET name= '" 
        	+ c.getName()
        	+ "', city='"+ c.getCity()
        	+ "', zipCode='" + c.getZipCode()  
        	+ "', street='" + c.getStreet() 
        	+ "', streetNo='" + c.getStreetNo()
        	+ "', cinemachainFK='" + c.getCinemaChainFK()
        	+ "' WHERE id=" + c.getId());
        	
        	con.setAutoCommit(true);
        }
        catch(SQLException e2) {
        e2.printStackTrace();
       
    }
        return c;
    }
    
    /**
     * Lï¿½schen von Daten eines <code>Cinema</code>-Objekts aus der Datenbank
     * @param c, das zu lï¿½schende Objekt. 
     */
    public void deleteCinema(Cinema c) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		Statement stmt = con.createStatement();
    		
    		stmt.executeUpdate("DELETE FROM cinema WHERE id= " + c.getId());
    		
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
        	ResultSet rs = stmt.executeQuery("SELECT * FROM cinema WHERE name='" + name + "'");
        	//Fï¿½r jeden Eintrag im Suchergebnis wird ein Cinema-Objekt erstellt
        	while(rs.next()) {
        		Cinema c = new Cinema();
        		c.setName(rs.getString("name"));
        		c.setCity(rs.getString("city"));
        		c.setZipCode(rs.getString("zipCode"));
        		c.setStreet(rs.getString("street"));
        		c.setStreetNo(rs.getString("streetNo"));
        		c.setCinemaChainFK(rs.getInt("cinemachainFK"));
        		c.setId(rs.getInt("id"));
        		
        		//Hinzufï¿½gen des neuen Objekts zum Ergebnisvektor
        		result.addElement(c);
        	}
        }
        	catch(SQLException e2) {
        		e2.printStackTrace();
        	}
        	//Rï¿½ckgabe des Ergebnisvektors
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
        	ResultSet rs = stmt.executeQuery("SELECT * FROM cinema WHERE city='" + city+"'");
        	//Fï¿½r jeden Eintrag im Suchergebnis wird ein Cinema-Objekt erstellt
        	while(rs.next()) {
        		Cinema c = new Cinema();
        		c.setName(rs.getString("name"));
        		c.setCity(rs.getString("city"));
        		c.setZipCode(rs.getString("zipCode"));
        		c.setStreet(rs.getString("street"));
        		c.setStreetNo(rs.getString("streetNo"));
        		c.setCinemaChainFK(rs.getInt("cinemachainFK"));
        		c.setId(rs.getInt("id"));
        		
        		//Hinzufï¿½gen des neuen Objekts zum Ergebnisvektor
        		result.addElement(c);
        	}
        } catch(SQLException e2) {
        	e2.printStackTrace();
        }
        //Rï¿½ckgabe des Ergebnisvektors
        return result;
    }

    /**
     * Auslesen der Cinema-Objekte mit gegebener Postleitzahl
     * @param postcode 
     * @return Vektor mit Cinema-Objekten
     */
    public Vector<Cinema> findCinemaByZipCode(String zipCode) {
    	Connection con = DBConnection.connection();
    	Vector<Cinema> result = new Vector<Cinema>();
    	
    	try {
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT * FROM cinema WHERE zipCode= '"+ zipCode+"'");
    		
    		//Fï¿½r jeden Eintrag im Suchergebnis wird ein Cinema-Objekt erstellt
    		while(rs.next()) {
    			Cinema c = new Cinema();
    			c.setName(rs.getString("name"));
        		c.setCity(rs.getString("city"));
        		c.setZipCode(rs.getString("zipCode"));
        		c.setStreet(rs.getString("street"));
        		c.setStreetNo(rs.getString("streetNo"));
        		c.setCinemaChainFK(rs.getInt("cinemachainFK"));
        		c.setId(rs.getInt("id"));
    			
    			
    			//Hinzufï¿½gen des Objekts zum Ergebnisvektor
    			result.addElement(c);
    		}
    	} catch(SQLException e2) {
    		e2.printStackTrace();
    	}
    	//Rï¿½ckgabe des Ergebnisvektors
    	return result;
        
    }
    
    /**
	 * Methode, die alle Cinemas die zu einer CinemaChain gehören zurückgibt
	 * @param int cinemachainFK
	 * @return Vektor mit Cinema-Objekten
	 */
    public Vector<Cinema> findCinemaByCinemaChainFK(int cinemachainFK) {
    	Connection con = DBConnection.connection();
    	Vector<Cinema> result = new Vector<Cinema>();
    	
    	try {
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT * FROM cinema WHERE cinema.cinemachainFK=" + cinemachainFK);
    		
    		while(rs.next()) {
    			Cinema c = new Cinema();
    			c.setName(rs.getString("name"));
        		c.setCity(rs.getString("city"));
        		c.setZipCode(rs.getString("zipCode"));
        		c.setStreet(rs.getString("street"));
        		c.setStreetNo(rs.getString("streetNo"));
        		c.setCinemaChainFK(rs.getInt("cinemachainFK"));
        		c.setId(rs.getInt("id"));
    			
    			result.addElement(c);
    		}
    	}
    	catch(SQLException e2) {
    		e2.printStackTrace();
    	}
    	return result;
    }

    /**
     * Methode, die ein Cinema anhand des Namens löscht
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
	 * Methode, die ein Cinema anhand des CinemaChainFK's löscht
	 * @param int cinemachainFK
	 */
    public void deleteCinemaByCinemachainFK(int cinemachainFK) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("DELETE FROM cinema" + "WHERE cinema.cinemachainFK=" + cinemachainFK);
    	}
    	catch(SQLException e2) {
    		e2.printStackTrace();
    	}
    }

    /**
     * Methode, die alle Cinemas eines Erstellers/Erstellerin zurückgibt
     * @param person 
     * @return Vektor Cinema
     */
    public Vector<Cinema> findCinemaByPersonFK(int personFK) {
        Connection con = DBConnection.connection();
        Vector<Cinema> result = new Vector<Cinema>();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT cinema.id, cinema.name, cinema.city, "
        			+ "cinema.zipCode, cinema.street, cinema.streetNo, cinema.cinemachainFK FROM cinema "
        			+ "INNER JOIN popcorns.businessownership ON cinema.id=businessownership.id "
        			+ "AND businessownership.personFK='" + personFK+"'");
        	
        	//Fï¿½r jeden Eintrag im Suchergebnis wird ein Cinema-Objekt zugeordnet
        	while(rs.next()) {
        		Cinema c = new Cinema();
        		c.setName(rs.getString("name"));
        		c.setCity(rs.getString("city"));
        		c.setZipCode(rs.getString("zipCode"));
        		c.setStreet(rs.getString("street"));
        		c.setStreetNo(rs.getString("streetNo"));
        		c.setCinemaChainFK(rs.getInt("cinemachainFK"));
        		c.setId(rs.getInt("id"));
        		
        		
        		//Hinzufï¿½gen des neuen Objekts zum Ergebnisvektor
        		result.addElement(c);
        	}
        }
        catch(SQLException e2) {
        	e2.printStackTrace();
        }
        return result;
        
    }

}