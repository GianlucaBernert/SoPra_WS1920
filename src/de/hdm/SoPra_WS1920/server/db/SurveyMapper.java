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
 * @author shila
 */ 
public class SurveyMapper {
	
/**
 * Die Klasse SurveyMapper wird nur einmal instanziiert (Singleton-Eigenschaft).
 * Die folgende Variable ist durch den Bezeichner <code>static</code> nur einmal f�r 
 * alle Instanzen der Klasse vorhanden. Die einzige Instanz dieser Klasse wird darin gespeichert.
 */
	
	private static SurveyMapper surveyMapper = null;
	
/**
 * Gesch�tzter Konstruktor, der verhindert, dass mit dem new-Operator
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
     * @return Survey-Objekt, das dem �bergebenen Schl�ssel entspricht, null
     * bei nicht vorhandenem DB-Tupel.
     */
	
	public Survey findSurveyByID(int id) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM survey" + "WHERE id=" + id);
			
			if(rs.next()) {
				
				Survey s = new Survey();
				s.setStartDate(rs.getTimestamp("startDate"));
				s.setEndDate(rs.getTimestamp("endDate"));
				s.setGroupFK(rs.getInt("groupFK"));
				
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
			
				stmt.executeUpdate("INSERT INTO survey(id, startDate, endDate, groupFK)"
					+ "VALUES ('"
					+ s.getId()
					+ "','"
					+ s.getStartDate()
					+ "','"
					+ s.getEndDate() + "')");
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
        	con.setAutoCommit(true);
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("UPDATE survey" + "SET startDate=\'" + s.getStartDate()
        	+ "\", " + "endDate=\'" + s.getEndDate() +  "\", " + "groupFK=\'" + s.getGroupFK() + "\", " 
        	+ "WHERE id=" + s.getId());
        	con.setAutoCommit(false);
        }
        catch(SQLException e2) {
        e2.printStackTrace();
       
    }
        return s;
    }
    
    /**
     * L�schen von Daten eines <code>Survey</code>-Objekts aus der Datenbank
     * @param s, das zu l�schende Objekt 
     */
    public void deleteSurvey(Survey s) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		Statement stmt = con.createStatement();
    		
    		stmt.executeUpdate("DELETE FROM survey" + "WHERE id=" + s.getId());
    		
    	}
    	catch(SQLException e2) {
    		e2.printStackTrace();
    	}
        
        
    }
    
    /**
     * Auslesen der Survey-Objekte mit gegebenem Beginn
     * @param startDate
     * 
     * @return Vektor mit Survey-Objekten
     */
    public Vector<Survey> findSurveyByStartDate(Date startDate) {
        Connection con = DBConnection.connection();
        Vector<Survey> result = new Vector<Survey>();
        
        try {
        	Statement stmt = con.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT * FROM survey" 
        	+ "WHERE startDate= '" + startDate + "'");
        	//F�r jeden Eintrag im Suchergebnis wird ein Cinema-Objekt erstellt
        	while(rs.next()) {
        		Survey s = new Survey();
        		s.setStartDate(rs.getTimestamp("startDate"));
        		s.setEndDate(rs.getTimestamp("endDate"));
        		s.setGroupFK(rs.getInt("groupFK"));
        		
        		//Hinzuf�gen des neuen Objekts zum Ergebnisvektor
        		result.addElement(s);
        	}
        }
        	catch(SQLException e2) {
        		e2.printStackTrace();
        	}
        	//R�ckgabe des Ergebnisvektors
        	return result;
        }
    
    /**
     * Auslesen der Survey-Objekte mit gegebenem Ende 
     * @param endDate 
     * @return Vektor mit Survey-Objekten
     */
    public Vector<Survey> findSurveyByEndDate(Date endDate) {
        Connection con = DBConnection.connection();
        Vector<Survey> result = new Vector<Survey>();
        
        try {
        	Statement stmt = con.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT * FROM survey" + "WHERE endDate= '" + endDate + "'");
        	//F�r jeden Eintrag im Suchergebnis wird ein Survey-Objekt erstellt
        	while(rs.next()) {
        		Survey s = new Survey();
        		s.setStartDate(rs.getTimestamp("startDate"));
        		s.setEndDate(rs.getTimestamp("endDate"));
        		s.setGroupFK(rs.getInt("groupFK"));
        		
        		//Hinzuf�gen des neuen Objekts zum Ergebnisvektor
        		result.addElement(s);
        	}
        } catch(SQLException e2) {
        	e2.printStackTrace();
        }
        //R�ckgabe des Ergebnisvektors
        return result;
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
    		ResultSet rs = stmt.executeQuery("SELECT * FROM survey"
    				 + "WHERE survey.groupFK=" + groupFK);
    		
    		//F�r jeden Eintrag im Suchergebnis wird ein Survey-Objekt erstellt
    		while(rs.next()) {
    			Survey s = new Survey();
    			s.setStartDate(rs.getTimestamp("startDate"));
    			s.setEndDate(rs.getTimestamp("endDate"));
    			s.setGroupFK(rs.getInt("groupFK"));
    			
    			
    			//Hinzuf�gen des Objekts zum Ergebnisvektor
    			result.addElement(s);
    		}
    	} catch(SQLException e2) {
    		e2.printStackTrace();
    	}
    	//R�ckgabe des Ergebnisvektors
    	return result;
        
    }
    
    /**
     * L�schen einer Umfrage durch den GroupFK(Fremdschl�ssel)
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
     * @param personFK 
     * @return Vektor mit Survey-Objekten eines Erstellers
     */
    public Vector<Survey> findSurveyByPersonFK(int personFK) {
        Connection con = DBConnection.connection();
        Vector<Survey> result = new Vector<Survey>();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT survey.startDate, survey.endDate, survey.groupFK" +
        			"FROM  survey INNER JOIN pocorns.businessownership" + 
        			"ON survey.id = businessownership.id AND businessownership.personFK= '" + personFK);
        	
        	//F�r jeden Eintrag im Suchergebnis wird ein Survey-Objekt zugeordnet
        	while(rs.next()) {
        		Survey s = new Survey();
        		s.setStartDate(rs.getTimestamp("startDate"));
        		s.setEndDate(rs.getTimestamp("endDate"));
        		s.setGroupFK(rs.getInt("groupFK"));
        		
        		
        		
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