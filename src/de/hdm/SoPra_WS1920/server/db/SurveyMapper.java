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
 * Anhand von den Methoden können Objekte gesucht, erzeugt, bearbeitet und gelöscht werden.
 * Objekte können in DB-Strukturen umgewandelt werden und DB-Strukturen in Objekte.
 * 
 * @author Shila Lotfi
 */ 
public class SurveyMapper { 
	
/**
 * Die Klasse SurveyMapper wird nur einmal instanziiert (Singleton-Eigenschaft).
 * Die folgende Variable ist durch den Bezeichner <code>static</code> nur einmal fÃƒÂ¯Ã‚Â¿Ã‚Â½r 
 * alle Instanzen der Klasse vorhanden. Die einzige Instanz dieser Klasse wird darin gespeichert.
 */
	
	private static SurveyMapper surveyMapper = null;
	
/**
 * GeschÃƒÂ¯Ã‚Â¿Ã‚Â½tzter Konstruktor, der verhindert, dass mit dem new-Operator
 * neue Instanzen der Klasse erstellt werden.
 */
	
	protected SurveyMapper() {
		
	}
	
/**
 * Folgende statische Methode sichert die Singleton-Eigenschaft.
 * Es wird dafür gesorgt, dass nur eine einzige Instanz von
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
     * @param id (Primärschlüssel-Attribut)
     * @return Survey-Objekt, das dem übergebenen Schluessel entspricht, null
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
     * Einfügen eines <code>Survey</code>-Objekts in die DB.
     * Prüfung und ggf. Korrektur des Primärschlüssels
     * @param survey das zu speichernde Objekt
     * @return das übergebene Objekt, mit ggf. korrigierter <code>id</code>.
     */
	
	public Survey insertSurvey(Survey s) {
		Connection con = DBConnection.connection();
		
		try {
		
			Statement stmt = con.createStatement();
			
				stmt.executeUpdate("INSERT INTO survey(id, groupFK, startDate, endDate)"
					+ "VALUES ('"
					+ s.getId()	
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
     * @return das Objekt, das als Parameter übergeben wird -> s
     */
    public Survey updateSurvey(Survey s) {
        Connection con = DBConnection.connection();
        
        try {
        	con.setAutoCommit(false);
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("UPDATE survey SET groupFK='" + s.getGroupFK()
        	+ "' WHERE id=" + s.getId());
        	con.setAutoCommit(true);
        }
        catch(SQLException e2) {
        e2.printStackTrace();
       
    }
        return s;
    }
    
    /**
     * Löschenn von Daten eines <code>Survey</code>-Objekts aus der Datenbank
     * @param s, das zu löschende Objekt 
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
     * Auslesen der Survey-Objekte mit gegebener GroupFK (Fremdschlüssel)
     * @param groupFK
     * @return Vektor mit Survey-Objekten
     */
    public Vector<Survey> findSurveyByGroupFK(int groupFK) {
    	Connection con = DBConnection.connection();
    	Vector<Survey> result = new Vector<Survey>();
    	
    	try {
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT * FROM survey "
    				+ "WHERE survey.groupFK=" + groupFK);
    		
    		//FÃ¼r jeden Eintrag im Suchergebnis wird ein Survey-Objekt erstellt
    		while(rs.next()) {
    			Survey s = new Survey();
    			s.setId(rs.getInt("id"));
    			s.setGroupFK(rs.getInt("groupFK"));
    			
    			
    			//Hinzufügen des Objekts zum Ergebnisvektor
    			result.addElement(s);
    		}
    	} catch(SQLException e2) {
    		e2.printStackTrace();
    	}
    	//RÃ¼ckgabe des Ergebnisvektors
    	return result;
        
    }
    
    /**
     * Löschen einer Umfrage durch den GroupFK(FremdschlÃ¼ssel)
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
        	
        	ResultSet rs = stmt.executeQuery("SELECT survey.id, survey.startDate, survey.endDate, survey.groupFK "
        			+ "FROM survey INNER JOIN popcorns.businessownership "
        			+ "ON survey.id = businessownership.id AND businessownership.personFK= '" + personFK + "'");
        	
        	//Für jeden Eintrag im Suchergebnis wird ein Survey-Objekt zugeordnet
        	while(rs.next()) {
        		Survey s = new Survey();
        		s.setId(rs.getInt("id"));
        		s.setGroupFK(rs.getInt("groupFK"));
        		
        		
        		
        		//Hinzufügen des neuen Objekts zum Ergebnisvektor
        		result.addElement(s);
        	}
        }
        catch(SQLException e2) {
        	e2.printStackTrace();
        }
        return result;
        
    }
    
    

}