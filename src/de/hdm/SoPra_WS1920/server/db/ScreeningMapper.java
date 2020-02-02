package de.hdm.SoPra_WS1920.server.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Vector;

import de.hdm.SoPra_WS1920.shared.bo.Screening;

/**
*
* Mapper-Klasse, die <code>Screening</code>-Objekte auf relationale Datenbank abbildet.
* Anhand von den Methoden k�nnen Objekte gesucht, erzeugt, bearbeitet und gel�scht werden.
* Objekte k�nnen in DB-Strukturen umgewandelt werden und DB-Strukturen in Objekte.
* 
* @author shila
*/ 
public class ScreeningMapper { 

	/**
	 * Die Klasse ScreeningMapper wird nur einmal instanziiert (Singleton-Eigenschaft).
	 * Die folgende Variable ist durch den Bezeichner <code>static</code> nur einmal f�r 
	 * alle Instanzen der Klasse vorhanden. Die einzige Instanz dieser Klasse wird darin gespeichert.
	 */
	
	private static ScreeningMapper screeningMapper = null;


	/**
	 * Gesch�tzter Konstruktor, der verhindert, dass mit dem new-Operator
	 * neue Instanzen der Klasse erstellt werden.
	 */
   protected ScreeningMapper() {
	   
   }
   /**
    * Folgende statische Methode sichert die Singleton-Eigenschaft.
    * Es wird daf�r gesorgt, dass nur eine einzige Instanz von
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
    * @param id (Prim�rschl�ssel-Attribut)
    * @return Screening-Objekt, das dem �bergebenen Schl�ssel entspricht, null
    * bei nicht vorhandenem DB-Tupel.
    */
	
	public Screening findScreeningByID(int id) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM screening WHERE id=" +id);
			
			if(rs.next()) {
				
				Screening sc = new Screening();
				sc.setId(rs.getInt("id"));
				sc.setScreeningDate(rs.getDate("screeningDate"));
				sc.setScreeningTime(rs.getTime("screeningTime"));
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
     * Einf�gen eines <code>Screening</code>-Objekts in die DB.
     * Pr�fung und ggf. Korrektur des Prim�rschl�ssels
     * @param screening das zu speichernde Objekt.
     * @return das �bergebene Objekt, mit ggf. korrigierter <code>id</code>.
     */
	
	public Screening insertScreening(Screening sc) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("INSERT INTO screening(id, screeningDate, screeningTime, movieFK, cinemaFK)"
					+ "VALUES ('"
					+ sc.getId()
					+ "','"
					+ sc.getScreeningDate()
					+ "','"
					+ sc.getScreeningTime()
					+ "','"
					+ sc.getMovieFK()
					+ "','"
					+ sc.getCinemaFK() + "')");
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
     * @return das Objekt, das als Parameter �bergeben wird -> sc
     */
    public Screening updateScreening(Screening sc) {
        Connection con = DBConnection.connection();
        
        try {
        	con.setAutoCommit(false);
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("UPDATE screening SET screeningDate='"+sc.getScreeningDate() 
        	+  "', screeningTime='" + sc.getScreeningTime()
        	+ "', movieFK='"+sc.getMovieFK()
        	+ "', cinemaFK='"+sc.getCinemaFK()
        	+"' WHERE id="+sc.getId());
        	
        	con.setAutoCommit(true);
        	
        }
        catch(SQLException e2) {
        e2.printStackTrace();
       
    }
        return sc;
    }

    /**
     * L�schen von Daten eines <code>Screening</code>-Objekts aus der Datenbank
     * @param sc, das zu l�schende Objekt 
     */
    public void deleteScreening(Screening sc) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		Statement stmt = con.createStatement();
    		
    		stmt.executeUpdate("DELETE FROM screening WHERE id= " + sc.getId());
    		
    	}
    	catch(SQLException e2) {
    		e2.printStackTrace();
    	}
        
        
    }
    
    /**
	 * Methode, die Screenings f�r das Erstellen einer Umfrage bereitstellt.
	 * @param startDate, endDate, int movieFK, String City
	 * @return Vektor Screening
	 */
    public Vector<Screening> findScreeningForSurveyCreation(Date startDate, Date endDate, int movieFK, String c) {
    	Connection con = DBConnection.connection();
    	Vector<Screening> result = new Vector<Screening>();
    	
    	try {
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT screening.id, screening.screeningDate, screening.screeningTime, screening.cinemaFK, screening.movieFK FROM screening "
    				+"INNER JOIN cinema " 
    				+"On screening.cinemaFK = cinema.id "
    				+"WHERE cinema.city= '" + c 
    				+"' AND screeningDate BETWEEN '"+startDate
    				+"' AND '" + endDate
    				+"' AND movieFK= "+ movieFK);
    		
    		while(rs.next()) {
    			Screening sc = new Screening();
    			sc.setId(rs.getInt("id"));
    			sc.setScreeningDate(rs.getDate("screeningDate"));
    			sc.setScreeningTime(rs.getTime("screeningTime"));
    			sc.setMovieFK(rs.getInt("movieFK"));
    			sc.setCinemaFK(rs.getInt("cinemaFK"));
    			
    			result.addElement(sc);
    		}
    	}
    	catch(SQLException e2) {
    		e2.printStackTrace();
    	}
    	return result;
    }

    /**
     * Auslesen der Screening-Objekte mit vorgegebenen Spielzeiten
     * @param screeningDate, screeningTime
     * @return Vektor mit Screening-Objekten
     */
    public Vector<Screening> findScreeningByScreeningDateTime(Date screeningDate, Time screeningTime) {
    	Connection con = DBConnection.connection();
        Vector<Screening> result = new Vector<Screening>();
        
        try {
        	Statement stmt = con.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT * FROM screening "
        			+ "WHERE screeningDate= '" + screeningDate + "AND screeningTime='" + screeningTime + "'");
        	//F�r jeden Eintrag im Suchergebnis wird ein Cinema-Objekt erstellt
        	while(rs.next()) {
        		Screening sc = new Screening();
        		sc.setId(rs.getInt("id"));
        		sc.setScreeningDate(rs.getDate("screeningDate"));
        		sc.setScreeningTime(rs.getTime("screeningTime"));
        		sc.setMovieFK(rs.getInt("movieFK"));
        		sc.setCinemaFK(rs.getInt("cinemaFK")); 
        		
        		//Hinzuf�gen des neuen Objekts zum Ergebnisvektor
        		result.addElement(sc);
        	}
        }
        	catch(SQLException e2) {
        		e2.printStackTrace();
        	}
        	//R�ckgabe des Ergebnisvektors
        	return result;
    }
    
    /**
     * Auslesen der Screening-Objekte mit vorgegebenen Spielzeiten
     * @param screeningDate
     * @return Vektor mit Screening-Objekten
     */
    public Vector<Screening> findScreeningByScreeningDate(Date screeningDate) {
    	Connection con = DBConnection.connection();
        Vector<Screening> result = new Vector<Screening>();
        
        try {
        	Statement stmt = con.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT * FROM screening "
        			+ "WHERE screeningDate= '" + screeningDate + "'");
        	//F�r jeden Eintrag im Suchergebnis wird ein Screening-Objekt erstellt
        	while(rs.next()) {
        		Screening sc = new Screening();
        		sc.setId(rs.getInt("id"));
        		sc.setScreeningDate(rs.getDate("screeningDate"));
        		sc.setScreeningTime(rs.getTime("screeningTime"));
        		sc.setMovieFK(rs.getInt("movieFK"));
        		sc.setCinemaFK(rs.getInt("cinemaFK")); 
        		
        		//Hinzuf�gen des neuen Objekts zum Ergebnisvektor
        		result.addElement(sc);
        	}
        }
        	catch(SQLException e2) {
        		e2.printStackTrace();
        	}
        	//R�ckgabe des Ergebnisvektors
        	return result;
    }
    
    /**
     * Auslesen der Screening-Objekte mit vorgegebenen Spielzeiten
     * @param screeningTime
     * @return Vektor mit Screening-Objekten
     */
    public Vector<Screening> findScreeningByScreeningTime(Time screeningTime) {
    	Connection con = DBConnection.connection();
        Vector<Screening> result = new Vector<Screening>();
        
        try {
        	Statement stmt = con.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT * FROM screening "
        			+ "WHERE screeningTime= '" + screeningTime + "'");
        	//F�r jeden Eintrag im Suchergebnis wird ein Cinema-Objekt erstellt
        	while(rs.next()) {
        		Screening sc = new Screening();
        		sc.setId(rs.getInt("id"));
        		sc.setScreeningDate(rs.getDate("screeningDate"));
        		sc.setScreeningTime(rs.getTime("screeningTime"));
        		sc.setMovieFK(rs.getInt("movieFK"));
        		sc.setCinemaFK(rs.getInt("cinemaFK")); 
        		
        		//Hinzuf�gen des neuen Objekts zum Ergebnisvektor
        		result.addElement(sc);
        	}
        }
        	catch(SQLException e2) {
        		e2.printStackTrace();
        	}
        	//R�ckgabe des Ergebnisvektors
        	return result;
    }
    
    
    /**
	 * Methode, die das Loeschen eines Screening-Objekts aus der Datenbank erm�glicht
	 * @param ownership
	 */
    public void deleteByScreeningDate(Date screeningDate) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("DELETE FROM screening" + "WHERE screeningDate=" + screeningDate);
    	}
    	catch(SQLException e2) {
    		e2.printStackTrace();
    	}
    }
    
    /**
	 * Methode, die das Loeschen eines Screening-Objekts aus der Datenbank anhand der screeningTime erm�glicht
	 * @param Time screeningTime
	 */
    public void deleteByScreeningTime(Time screeningTime) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("DELETE FROM screening" + "WHERE screeningTime=" + screeningTime);
    	}
    	catch(SQLException e2) {
    		e2.printStackTrace();
    	}
    }
    
    /**
	 * Methode, die das Loeschen eines Screening-Objekts aus der Datenbank anhand der screeningTime und  erm�glicht
	 * @param Time screeningTime, Date screeningDate
	 */
    public void deleteByScreeningDateTime(Date screeningDate, Time screeningTime) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("DELETE FROM screening" + "WHERE screeningTime=" + screeningTime
    					+ "AND screeningDate='" + screeningDate + "'");
    	}
    	catch(SQLException e2) {
    		e2.printStackTrace();
    	}
    }
    

    /**
     * Auslesen der Screening-Objekte mit gegebenem MovieFK (Fremdschl�ssel)
     * @param movieFK 
     * @return Vektor mit Screening-Objekten
     */
    public Vector<Screening> findScreeningByMovieFK(int movieFK) {
    	Connection con = DBConnection.connection();
    	Vector<Screening> result = new Vector<Screening>();
    	
    	try {
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT * FROM screening "
    				+ "WHERE movieFK= " + movieFK);
    		
    		//F�r jeden Eintrag im Suchergebnis wird ein Cinema-Objekt erstellt
    		while(rs.next()) {
    			Screening sc = new Screening();
    			sc.setId(rs.getInt("id"));
    			sc.setScreeningDate(rs.getDate("screeningDate"));
    			sc.setScreeningTime(rs.getTime("screeningTime"));
    			sc.setMovieFK(rs.getInt("movieFK"));
    			sc.setCinemaFK(rs.getInt("cinemaFK"));
    			
    			
    			//Hinzuf�gen des Objekts zum Ergebnisvektor
    			result.addElement(sc);
    		}
    	} catch(SQLException e2) {
    		e2.printStackTrace();
    	}
    	//R�ckgabe des Ergebnisvektors
    	return result;
    }

    /**
     * L�schen eines Screening-Objekts durch den MovieFK (Fremdschl�ssel)
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
     * Auslesen der Screening-Objekte mit vorgegebenem CinemaFK (Fremdschl�ssel)
     * @param cinemaFK 
     * @return Vektor mit Screening-Objekten
     */
    public Vector<Screening> findScreeningByCinemaFK(int cinemaFK) {
    	Connection con = DBConnection.connection();
    	Vector<Screening> result = new Vector<Screening>();
    	
    	try {
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT * FROM screening "
    				+ "WHERE screening.cinemaFK=" + cinemaFK);
    		
    		//F�r jeden Eintrag im Suchergebnis wird ein Cinema-Objekt erstellt
    		while(rs.next()) {
    			Screening sc = new Screening();
    			sc.setId(rs.getInt("id"));
    			sc.setScreeningDate(rs.getDate("screeningDate"));
    			sc.setScreeningTime(rs.getTime("screeningTime"));
    			sc.setMovieFK(rs.getInt("movieFK"));
    			sc.setCinemaFK(rs.getInt("cinemaFK"));
    			
    			
    			//Hinzuf�gen des Objekts zum Ergebnisvektor
    			result.addElement(sc);
    		}
    	} catch(SQLException e2) {
    		e2.printStackTrace();
    	}
    	//R�ckgabe des Ergebnisvektors
    	return result;
    }

    /**
     * L�schen eines Screening-Objekts durch den CinemaFK
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
        	
        	ResultSet rs = stmt.executeQuery("SELECT screening.id, screening.screeningDate, screening.screeningTime, screening.movieFK, screening.cinemaFK "
        			+ "FROM screening INNER JOIN popcorns.businessownership "
        			+ "ON screening.id = businessownership.id AND businessownership.personFK= '" + personFK+"'");
        	
        	//F�r jeden Eintrag im Suchergebnis wird ein Cinema-Objekt zugeordnet
        	while(rs.next()) {
        		Screening sc = new Screening();
        		sc.setId(rs.getInt("id"));
        		sc.setScreeningDate(rs.getDate("screeningDate"));
        		sc.setScreeningTime(rs.getTime("screeningTime"));
        		sc.setMovieFK(rs.getInt("movieFK"));
        		sc.setCinemaFK(rs.getInt("cinemaFK"));
        		
        		
        		
        		//Hinzuf�gen des neuen Objekts zum Ergebnisvektor
        		result.addElement(sc);
        	}
        }
        catch(SQLException e2) {
        	e2.printStackTrace();
        }
        return result;
        
    }


}