package de.hdm.SoPra_WS1920.server.db;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.SoPra_WS1920.shared.bo.Survey;

/**
 *
 * Mapper-Klasse, die <code>Survey</code>-Objekte auf relationale Datenbank abbildet.
 * Anhand von den Methoden k�nnen Objekte gesucht, erzeugt, bearbeitet und gel�scht werden.
 * Objekte k�nnen in DB-Strukturen umgewandelt werden und DB-Strukturen in Objekte.
 * 
 * @author Shila Lotfi
 */ 
public class SurveyMapper { 
	
/**
 * Die Klasse SurveyMapper wird nur einmal instanziiert (Singleton-Eigenschaft).
 * Die folgende Variable ist durch den Bezeichner <code>static</code> nur einmal fÃ¯Â¿Â½r 
 * alle Instanzen der Klasse vorhanden. Die einzige Instanz dieser Klasse wird darin gespeichert.
 */
	
	private static SurveyMapper surveyMapper = null;
	
/**
 * GeschÃ¯Â¿Â½tzter Konstruktor, der verhindert, dass mit dem new-Operator
 * neue Instanzen der Klasse erstellt werden.
 */
	
	protected SurveyMapper() {
		
	}
	
/**
 * Folgende statische Methode sichert die Singleton-Eigenschaft.
 * Es wird daf�r gesorgt, dass nur eine einzige Instanz von
 * <code>SurveyMapper</code> existiert.
 * SurveyMapper wird durch den Aufruf dieser statischen Methode instanziiert, 
 * nicht durch den new-Operator.
 * Aufruf der Methode durch: <code>SurveyMapper.surveyMapper()</code>
 * 
 * @return <code>SurveyMapper</code>-Objekt
 */
 
	public static SurveyMapper surveyMapper() {
		if(surveyMapper == null) {
			surveyMapper = new SurveyMapper();
		}
		
		return surveyMapper;
	}
	
	/**
     * @param id (Prim�rschl�ssel-Attribut)
     * @return Survey-Objekt, das dem �bergebenen Schluessel entspricht, null
     * bei nicht vorhandenem DB-Tupel.
     */
	
	public Survey findSurveyByID(int id) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM survey WHERE id= " + id);
			
			if(rs.next()) {
				
				Survey s = new Survey();
				s.setId(rs.getInt("id"));
				s.setStartDate(rs.getDate("startDate"));
				s.setEndDate(rs.getDate("endDate"));
				s.setGroupFK(rs.getInt("groupFK"));
				s.setSelectedCity(rs.getString("selectedCity"));
				s.setMovieName(rs.getString("movieName"));
				s.setStatus(rs.getInt("isActive"));
				
				return s;
			}
			
		}
		catch(SQLException e2) {
			e2.printStackTrace();
		}
		return null;
	}
	
	/**
     * Einf�gen eines <code>Survey</code>-Objekts in die DB.
     * Pr�fung und ggf. Korrektur des Prim�rschl�ssels
     * @param survey das zu speichernde Objekt
     * @return das �bergebene Objekt, mit ggf. korrigierter <code>id</code>.
     */
	
	public Survey insertSurvey(Survey s) {
		Connection con = DBConnection.connection();
		
		try {
		
			Statement stmt = con.createStatement();
			
				stmt.executeUpdate("INSERT INTO survey(id, startDate, endDate, isActive, selectedCity, movieName, groupFK) "
					+ "VALUES ('"
					+ s.getId()	
					+ "','"
					+ s.getStartDate()
					+ "','"
					+ s.getEndDate()
					+ "','"
					+ s.getStatus()
					+ "','"
					+ s.getSelectedCity()
					+ "','"
					+ s.getMovieName()
					+ "','"
					+ s.getGroupFK()
					+ "')");
		}
		catch(SQLException e2) {
			e2.printStackTrace();
		
		}
		
		return s;
	}
	
	/**
     * Ein Objekt wird wiederholt in die DB geschrieben.
     * 
     * @param s, das Objekt, das in die DB geschrieben werden soll
     * @return das Objekt, das als Parameter �bergeben wird -> s
     */
    public Survey updateSurvey(Survey s) {
        Connection con = DBConnection.connection();
        
        try {
        	con.setAutoCommit(false);
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("UPDATE survey SET groupFK='" + s.getGroupFK()
        	+"', isActive='"+s.getStatus() + "', startDate='" + s.getStartDate()
        	+ "', endDate='" + s.getEndDate() + "', selectedCity='" + s.getSelectedCity()
        	+ "', movieName='" + s.getMovieName()
        	+ "' WHERE id=" + s.getId());
        	con.setAutoCommit(true);
        }
        catch(SQLException e2) {
        e2.printStackTrace();
       
    }
        return s;
    }
    
    /**
     * L�schenn von Daten eines <code>Survey</code>-Objekts aus der Datenbank
     * @param s, das zu l�schende Objekt 
     */
    public void deleteSurvey(Survey s) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		Statement stmt = con.createStatement();
    		
    		stmt.executeUpdate("DELETE FROM survey WHERE id= "+s.getId());
    		
    	}
    	catch(SQLException e2) {
    		e2.printStackTrace();
    	}
        
        
    }
    
    /**
	 * Methode, die eine Umfrage anhand des Startdatums zur�ckgibt.
	 * @param Date startDate
	 * @return Survey
	 */
    public Survey findSurveyByStartDate(java.sql.Date startDate) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT * FROM survey WHERE startDate='" + startDate + "'");
    		
    		if(rs.next()) {
    			Survey s = new Survey();
    			s.setId(rs.getInt("id"));
    			s.setStartDate(rs.getDate("startDate"));
    			s.setEndDate(rs.getDate("endDate"));
    			s.setGroupFK(rs.getInt("groupFK"));
    			s.setSelectedCity(rs.getString("selectedCity"));
    			s.setMovieName(rs.getString("movieName"));
    			s.setStatus(rs.getInt("isActive"));
    			
    			return s;
    		}
    	}
    	catch(SQLException e2) {
    		e2.printStackTrace();
    	}
    	return null;
    }
    
    /**
	 * Methode, die eine Umfrage anhand des Enddatums zur�ckgibt.
	 * @param Date endDate
	 * @return Survey
	 */
    public Survey findSurveyByEndDate(java.sql.Date endDate) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT * FROM survey WHERE endDate='" + endDate + "'");
    		
    		if(rs.next()) {
    			Survey s = new Survey();
    			s.setId(rs.getInt("id"));
    			s.setStartDate(rs.getDate("startDate"));
    			s.setEndDate(rs.getDate("endDate"));
    			s.setGroupFK(rs.getInt("groupFK"));
    			s.setSelectedCity(rs.getString("selectedCity"));
    			s.setMovieName(rs.getString("movieName"));
    			s.setStatus(rs.getInt("isActive"));
    			
    			return s;
    		}
    		
    		
    	}catch(SQLException e2) {
			e2.printStackTrace();
		}
    	return null;
    }
    
    /**
	 * Methode, die eine Umfrage anhand der ausgew�hlten Stadt vom Erstellzeitpunkt zur�ckgibt.
	 * @param String selected City
	 * @return Survey
	 */
    public Survey findSelectedCityOfSurvey(String selectedCity) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT *  FROM survey WHERE selectedCity='" + selectedCity + "'");
    		
    		if(rs.next()) {
    			Survey s = new Survey();
    			s.setId(rs.getInt("id"));
    			s.setStartDate(rs.getDate("startDate"));
    			s.setEndDate(rs.getDate("endDate"));
    			s.setGroupFK(rs.getInt("groupFK"));
    			s.setSelectedCity(rs.getString("selectedCity"));
    			s.setMovieName(rs.getString("movieName"));
    			s.setStatus(rs.getInt("isActive"));
    			
    			return s;
    		}
    	}
    	catch(SQLException e2)
    	{
    		e2.printStackTrace();
    		}
    	return null;
    	}
    
    
    /**
     * Auslesen der Survey-Objekte mit gegebener GroupFK (Fremdschl�ssel)
     * @param groupFK
     * @return Vektor mit Survey-Objekten
     */
    public Vector<Survey> findSurveyByGroupFK(int groupFK) {
    	Connection con = DBConnection.connection();
    	Vector<Survey> result = new Vector<Survey>();
    	
    	try {
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT survey.id, groupFK, isActive, startDate, endDate, selectedCity, movieName, personFK FROM survey " 
    				+"INNER JOIN businessownership "
    				+"WHERE businessownership.id = survey.id "
    				+"And groupFK = " + groupFK);
    		
    		//Für jeden Eintrag im Suchergebnis wird ein Survey-Objekt erstellt
    		while(rs.next()) {
    			Survey s = new Survey();
    			s.setId(rs.getInt("id"));
    			s.setStartDate(rs.getDate("startDate"));
    			s.setEndDate(rs.getDate("endDate"));
    			s.setGroupFK(rs.getInt("groupFK"));
    			s.setSelectedCity(rs.getString("selectedCity"));
    			s.setMovieName(rs.getString("movieName"));
    			s.setStatus(rs.getInt("isActive"));
    			s.setPersonFK(rs.getInt("personFK"));
    			
    			//Hinzuf�gen des Objekts zum Ergebnisvektor
    			result.addElement(s);
    		}
    	} catch(SQLException e2) {
    		e2.printStackTrace();
    	}
    	//Rückgabe des Ergebnisvektors
    	return result;
        
    }
    
    /**
     * Auslesen der Survey-Objekte mit gegebenem Status
     * @param isActive
     * @return Vektor mit Survey-Objekten
     */
    public Vector<Survey> findSurveyByIsActive(int isActive) {
    	Connection con = DBConnection.connection();
    	Vector<Survey> result = new Vector<Survey>();
    	
    	try {
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT * FROM survey "
    				+ "WHERE survey.isActive=" + isActive);
    		
    		//Für jeden Eintrag im Suchergebnis wird ein Survey-Objekt erstellt
    		while(rs.next()) {
    			Survey s = new Survey();
    			s.setId(rs.getInt("id"));
    			s.setStartDate(rs.getDate("startDate"));
    			s.setEndDate(rs.getDate("endDate"));
    			s.setGroupFK(rs.getInt("groupFK"));
    			s.setSelectedCity(rs.getString("selectedCity"));
    			s.setMovieName(rs.getString("movieName"));
    			s.setStatus(rs.getInt("isActive"));
    			
    			
    			//Hinzuf�gen des Objekts zum Ergebnisvektor
    			result.addElement(s);
    		}
    	} catch(SQLException e2) {
    		e2.printStackTrace();
    	}
    	//Rückgabe des Ergebnisvektors
    	return result;
        
    }
    
    /**
	 * Methode, die eine Umfrage anhand des zum ERstellzeitpunkt ausgew�hlten Movies zur�ckgibt.
	 * @param String movieName
	 * @return Survey
	 */
    public Survey findMovieNameOfSurvey(String movieName) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT * FROM survey WHERE movieName='" + movieName + "'");
    		
    		if(rs.next()) {
    			Survey s = new Survey();
    			s.setId(rs.getInt("id"));
    			s.setStartDate(rs.getDate("startDate"));
    			s.setEndDate(rs.getDate("endDate"));
    			s.setGroupFK(rs.getInt("groupFK"));
    			s.setSelectedCity(rs.getString("selectedCity"));
    			s.setMovieName(rs.getString("movieName"));
    			s.setStatus(rs.getInt("isActive"));
    			
    			return s; 
    		}
    	}
    	catch(SQLException e2) {
    		e2.printStackTrace();
    	}
    	return null;
    }
    /**
     * L�schen einer Umfrage durch den GroupFK(Fremdschlüssel)
     * @param groupFK
     */
    
    public void deleteSurveyByGroupFK(int groupFK) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("DELETE FROM survey" + "WHERE survey.groupFK=" + groupFK);
    	}
    	catch(SQLException e2) {
    		e2.printStackTrace();
    	}
    }
    
    
    
    /**
     * Methode, die alle Umfragen des Erstellers zur�ckgibt.
     * @param personFK 
     * @return Vektor mit Survey-Objekten eines Erstellers
     */
     public Vector<Survey> findSurveyByPersonFK(int personFK) {
        Connection con = DBConnection.connection();
        Vector<Survey> result = new Vector<Survey>();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT survey.id, survey.startDate, survey.endDate, survey.groupFK, survey.selectedCity, survey.isActive, survey.movieName, businessownership.personFK "
        			+ "FROM survey INNER JOIN popcorns.businessownership "
        			+ "ON survey.id = businessownership.id AND businessownership.personFK= '" + personFK + "'");
        	
        	//F�r jeden Eintrag im Suchergebnis wird ein Survey-Objekt zugeordnet
        	while(rs.next()) {
        		Survey s = new Survey();
        		s.setId(rs.getInt("id"));
        		s.setStartDate(rs.getDate("startDate"));
        		s.setEndDate(rs.getDate("endDate"));
        		s.setGroupFK(rs.getInt("groupFK"));
        		s.setSelectedCity(rs.getString("selectedCity"));
        		s.setStatus(rs.getInt("isActive"));
        		s.setMovieName(rs.getString("movieName"));
        		s.setPersonFK(rs.getInt("personFK"));
        		
        		//Hinzuf�gen des neuen Objekts zum Ergebnisvektor
        		result.addElement(s);
        	}
        }
        catch(SQLException e2) {
        	e2.printStackTrace();
        }
        return result;
        
    }
    
    

}