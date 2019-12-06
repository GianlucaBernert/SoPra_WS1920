package de.hdm.SoPra_WS1920.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Person;
import de.hdm.SoPra_WS1920.shared.bo.Screening;
import de.hdm.SoPra_WS1920.shared.bo.Survey;

/**
*
* Mapper-Klasse, die <code>Screening</code>-Objekte auf relationale Datenbank abbildet.
* Anhand von den Methoden können Objekte gesucht, erzeugt, bearbeitet und gelöscht werden.
* Objekte können in DB-Strukturen umgewandelt werden und DB-Strukturen in Objekte.
* 
* @author shila
*/ 
public class ScreeningMapper {

	/**
	 * Die Klasse ScreeningMapper wird nur einmal instanziiert (Singleton-Eigenschaft).
	 * Die folgende Variable ist durch den Bezeichner <code>static</code> nur einmal für 
	 * alle Instanzen der Klasse vorhanden. Die einzige Instanz dieser Klasse wird darin gespeichert.
	 */
	
	private static ScreeningMapper screeningMapper = null;


	/**
	 * Geschützter Konstruktor, der verhindert, dass mit dem new-Operator
	 * neue Instanzen der Klasse erstellt werden.
	 */
   protected ScreeningMapper() {
	   
   }
   /**
    * Folgende statische Methode sichert die Singleton-Eigenschaft.
    * Es wird dafür gesorgt, dass nur eine einzige Instanz von
    * <code>ScreeningMapper</code> existiert.
    * ScreeningMapper wird durch den Aufruf dieser statischen Methode instanziiert, 
    * nicht durch den new-Operator.
    * Aufruf der Methode durch: <code>ScreeningMapper.screeningMapper()</code>
    * 
    * @return <code>ScreeningMapper</code>-Objekt
    */
   
   public static ScreeningMapper screeningMapper() {
	   if(screeningMapper == null) {
		   screeningMapper = new ScreeningMapper();
	   }
	   return screeningMapper;
   }

   /**
    * @param id (Primärschlüssel-Attribut)
    * @return Screening-Objekt, das dem übergebenen Schlüssel entspricht, null
    * bei nicht vorhandenem DB-Tupel.
    */
	
	public Screening findScreeningByID(int id) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM screening" + "WHERE bo_id=" + id);
			
			if(rs.next()) {
				
				Screening sc = new Screening();
				sc.setScreeningdayTime(rs.getTimestamp("screeningdayTime"));
				sc.setMovieFK(rs.getInt("movieFK"));
				sc.setCinemaFK(rs.getInt("cinemaFK"));
				
				return sc;
			}
			
		}
		catch(SQLException e2) {
			e2.printStackTrace();
		}
		return null;
	}
	
	/**
     * Einfügen eines <code>Screening</code>-Objekts in die DB.
     * Prüfung und ggf. Korrektur des Primärschlüssels
     * @param screening das zu speichernde Objekt
     * @return das übergebene Objekt, mit ggf. korrigierter <code>id</code>.
     */
	
	public Screening insertScreening(Screening sc) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt1 = con.createStatement();
			Statement stmt2 = con.createStatement();
			
			stmt1.executeUpdate("INSERT INTO businessobject(bo_id, creationTimeStamp)"
					+ "VALUES ('"
					+ sc.getId()
					+ "','"
					+ sc.getCreationTimestamp() + "')");
			
			stmt2.executeUpdate("INSERT INTO screening(bo_id, screeningdayTime, movieFK, cinemaFK, creationTimeStamp)"
					+ "VALUES ('"
					+ sc.getId()
					+ "','"
					+ sc.getScreeningdayTime()
					+ "','"
					+ sc.getMovieFK()
					+ "','"
					+ sc.getCinemaFK() 
					+ "','"
					+ sc.getCreationTimestamp() + "')");
		}
		catch(SQLException e2) {
			e2.printStackTrace();
		}
		return sc;
	}
	/**
     * Ein Objekt wird wiederholt in die DB geschrieben.
     * 
     * @param sc, das Objekt, das in die DB geschrieben werden soll
     * @return das Objekt, das als Parameter übergeben wird -> sc
     */
    public Screening updateSurvey(Screening sc) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("UPDATE screening" + "SET screeningdayTime=\'" + sc.getScreeningdayTime()
        	+ "\", " + "movieFK=\'" + sc.getMovieFK() +  "\", " + "cinemaFK=\'" + sc.getCinemaFK() + "\", " 
        	+ "WHERE bo_id=" + sc.getId());
        	
        }
        catch(SQLException e2) {
        e2.printStackTrace();
       
    }
        return sc;
    }

    /**
     * Löschen von Daten eines <code>Screening</code>-Objekts aus der Datenbank
     * @param sc, das zu löschende Objekt 
     */
    public void deleteScreening(Screening sc) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		Statement stmt1 = con.createStatement();
    		Statement stmt2 = con.createStatement();;
    		
    		stmt1.executeUpdate("DELETE FROM screening" + "WHERE bo_id=" + sc.getId());
    		//Businessobject löschen
    		stmt2.executeUpdate("DELETE FROM businessobject WHERE bo_id=" + sc.getId());
    		
    	}
    	catch(SQLException e2) {
    		e2.printStackTrace();
    	}
        
        
    }

    /**
     * Auslesen der Screening-Objekte mit vorgegebenen Spielzeiten
     * @param screeningdayTime 
     * @return Vektor mit Screening-Objekten
     */
    public Vector<Screening> findScreeningByScreeningdayTime(Timestamp screeningdayTime) {
    	Connection con = DBConnection.connection();
        Vector<Screening> result = new Vector<Screening>();
        
        try {
        	Statement stmt = con.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT * FROM survey" 
        	+ "WHERE screeningdayTime= '" + screeningdayTime + "'");
        	//Für jeden Eintrag im Suchergebnis wird ein Cinema-Objekt erstellt
        	while(rs.next()) {
        		Screening sc = new Screening();
        		sc.setScreeningdayTime(rs.getTimestamp("screeningdayTime"));
        		sc.setMovieFK(rs.getInt("movieFK"));
        		sc.setCinemaFK(rs.getInt("cinemaFK"));
        		
        		//Hinzufügen des neuen Objekts zum Ergebnisvektor
        		result.addElement(sc);
        	}
        }
        	catch(SQLException e2) {
        		e2.printStackTrace();
        	}
        	//Rückgabe des Ergebnisvektors
        	return result;
    }
    
    public void deleteByScreeningdayTime(Timestamp screeningdayTime) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("DELETE FROM screening" + "WHERE screeningdayTime=" + screeningdayTime);
    	}
    	catch(SQLException e2) {
    		e2.printStackTrace();
    	}
    }
    

    /**
     * Auslesen der Screening-Objekte mit gegebenem MovieFK (Fremdschlüssel)
     * @param movieFK 
     * @return Vektor mit Screening-Objekten
     */
    public Vector<Screening> findScreeningByMovieFK(int movieFK) {
    	Connection con = DBConnection.connection();
    	Vector<Screening> result = new Vector<Screening>();
    	
    	try {
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT * FROM screening"
    				 + "WHERE screening.movieFK=" + movieFK);
    		
    		//Für jeden Eintrag im Suchergebnis wird ein Cinema-Objekt erstellt
    		while(rs.next()) {
    			Screening sc = new Screening();
    			sc.setScreeningdayTime(rs.getTimestamp("screeningdayTime"));
    			sc.setMovieFK(rs.getInt("movieFK"));
    			sc.setCinemaFK(rs.getInt("cinemaFK"));
    			
    			
    			//Hinzufügen des Objekts zum Ergebnisvektor
    			result.addElement(sc);
    		}
    	} catch(SQLException e2) {
    		e2.printStackTrace();
    	}
    	//Rückgabe des Ergebnisvektors
    	return result;
    }

    /**
     * Löschen eines Screening-Objekts durch den MovieFK (Fremdschlüssel)
     * @param movieFK
     */
    public void deleteScreeningByMovieFK(int movieFK) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("DELETE FROM screening" + "WHERE screening.movieFK=" + movieFK);
    	}
    	catch(SQLException e2) {
    		e2.printStackTrace();
    	}
        
    }

    /**
     * Auslesen der Screening-Objekte mit vorgegebenem CinemaFK (Fremdschlüssel)
     * @param cinemaFK 
     * @return Vektor mit Screening-Objekten
     */
    public Vector<Screening> findScreeningByCinemaFK(int cinemaFK) {
    	Connection con = DBConnection.connection();
    	Vector<Screening> result = new Vector<Screening>();
    	
    	try {
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT * FROM screening"
    				 + "WHERE screening.cinemaFK=" + cinemaFK);
    		
    		//Für jeden Eintrag im Suchergebnis wird ein Cinema-Objekt erstellt
    		while(rs.next()) {
    			Screening sc = new Screening();
    			sc.setScreeningdayTime(rs.getTimestamp("screeningdayTime"));
    			sc.setMovieFK(rs.getInt("movieFK"));
    			sc.setCinemaFK(rs.getInt("cinemaFK"));
    			
    			
    			//Hinzufügen des Objekts zum Ergebnisvektor
    			result.addElement(sc);
    		}
    	} catch(SQLException e2) {
    		e2.printStackTrace();
    	}
    	//Rückgabe des Ergebnisvektors
    	return result;
    }

    /**
     * Löschen eines Screening-Objekts durch den CinemaFK
     * @param cinemaFK 
     */
    public void deleteScreeningByCinemaFK(int cinemaFK) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	stmt.executeUpdate("DELETE FROM screening" + "WHERE screening.cinemaFK=" + cinemaFK);
        }
        catch(SQLException e2) {
        	e2.printStackTrace();
        }
    }

    /**
     * Auslesen der Screening-Objekte durch den Ersteller (Owner)
     * @param personFK
     * @return Vektor mit Screening-Objekten
     */
    public Vector<Screening> findScreeningByPersonFK(int personFK) {
    	Connection con = DBConnection.connection();
        Vector<Screening> result = new Vector<Screening>();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT screening.screeningdayTime, screening.movieFK, screening.cinemaFK" +
        			"FROM  screening INNER JOIN pocorns.businessownership" + 
        			"ON screening.bo_id = businessownership.bo_id AND businessownership.personFK= '" + personFK);
        	
        	//Für jeden Eintrag im Suchergebnis wird ein Cinema-Objekt zugeordnet
        	while(rs.next()) {
        		Screening sc = new Screening();
        		sc.setScreeningdayTime(rs.getTimestamp("screeningdayTime"));
        		sc.setMovieFK(rs.getInt("movieFK"));
        		sc.setCinemaFK(rs.getInt("cinemaFK"));
        		
        		
        		
        		//Hinzufügen des neuen Objekts zum Ergebnisvektor
        		result.addElement(sc);
        	}
        }
        catch(SQLException e2) {
        	e2.printStackTrace();
        }
        return result;
        
    }


}