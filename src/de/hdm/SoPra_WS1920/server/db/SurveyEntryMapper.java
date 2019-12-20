package de.hdm.SoPra_WS1920.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.SoPra_WS1920.shared.bo.Screening;
import de.hdm.SoPra_WS1920.shared.bo.Survey;
import de.hdm.SoPra_WS1920.shared.bo.SurveyEntry;


/**
 * Mapper-Klasse, die <code>SurveyEntry</code>-Objekte auf relationale Datenbank abbildet.
 * Anhand von den Methoden können Objekte gesucht, erzeugt, bearbeitet und gelöscht werden. 
 * Objekte können in DB-Strukturen umgewandelt werden und DB-Strukturen in Objekte.
 * 
 * @author shila
 */
public class SurveyEntryMapper {

    /**
     * Die Klasse SurveyEntryMapper wird nur einmal instanziiert (Singleton-Eigenschaft).
     * Die folgende Variable ist durch den Bezeichner <code>static</code> nur einmal für 
     * alle Instanzen der Klasse vorhanden. Die einzige Instanz dieser Klasse wird darin gespeichert. 
     */
	private static SurveyEntryMapper surveyEntryMapper = null;
     
	/**
	 * Geschützter Konstruktor, der verhindert, dass mit dem new-Operator
	 * neue Instanzen der Klasse erstellt werden.
	 */
    protected SurveyEntryMapper() {
    }
    
    /**
     * Folgende statische Methode sichert die Singleton-Eigenschaft.
     * Es wird dafür gesorgt, dass nur eine einzige Instanz von
     * <code>SurveyEntryMapper</code> existiert.
     * SurveyEntryMapper wird durch den Aufruf dieser statischen Methode instanziiert, 
     * nicht durch den new-Operator.
     * Aufruf der Methode durch: <code>SurveyEntryMapper.surveyEntryMapper()</code>
     * 
     * @return <code>SurveyEntryMapper</code>-Objekt
     */
    public static SurveyEntryMapper surveyEntryMapper() {
    	if(surveyEntryMapper == null) {
    		surveyEntryMapper = new SurveyEntryMapper();		
    	}
    	return surveyEntryMapper;  	
    }
    
    /**
     * @param id (Primärschlüssel-Attribut)
     * @return SurveyEntry-Objekt, das dem übergebenen Schlüssel entspricht,
     * null bei nicht vorhandenem DB-Tupel
     */
    public SurveyEntry findSurveyEntryByID(int id) {
        Connection con = DBConnection.connection();
        
        try {
        	
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT * FROM surveyentry WHERE surveyentry.id= " + id);
        	
        	//Es wird geprüft, ob ein Ergebnis vorliegt
        	if(rs.next()) {
        		//Ergebnis-Tupel in Objekt umwandeln
        		SurveyEntry se = new SurveyEntry();
        		se.setSurveyFK(rs.getInt("surveyFK"));
        		se.setScreeningFK(rs.getInt("screeningFK"));
        		
        		return se;
        	}
        	
        } catch(SQLException e2) {
        	e2.printStackTrace();
        }
        
        return null;
        
    }


    /**
     * Einfügen eines <code>SurveyEntry</code>-Objekts in die DB.
     * Prüfung und ggf. Korrektur des Primärschlüssels
     * @param surveyEntry das zu speichernde Objekt
     * @return das übergebene Objekt, mit ggf. korrigierter <code>id</code>.
     */
    public SurveyEntry insertSurveyEntry(SurveyEntry se) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		Statement stmt = con.createStatement();

    		stmt.executeUpdate("INSERT INTO surveyentry(id, surveyFK, screeningFK)" +
    		"VALUES ('"
    		+ se.getId()
    		+ "','"
    		+ se.getSurveyFK()
    		+ "','"
    		+ se.getScreeningFK() + "')");
    		
    	}
    	catch(SQLException e2) {
    		e2.printStackTrace();
    	}
    	
    	 return se;
        
    }

    /**
     * @param surveyEntry 
     * @return
     */
     
    public SurveyEntry updateSurveyEntry(SurveyEntry se) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		con.setAutoCommit(false);
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("UPDATE surveyentry SET surveyFK='"+se.getSurveyFK() 
    			+"', screeningFK='"+se.getScreeningFK()
    			+"' WHERE id=" +se.getId());
    		
    		con.setAutoCommit(true);
    	}
    	
    	catch(SQLException e2) {
    		e2.printStackTrace();
    	}
        return se;
    }
    

    /**
     * @param surveyEntry 
     * @return
     */
    public void deleteSurveyEntry(SurveyEntry se) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("DELETE FROM surveyentry WHERE id= "+se.getId());
        }
        catch(SQLException e2) {
        	e2.printStackTrace();
        }
    }

    /**
     * Auslesen aller Umfrageeinträge durch Fremdschlüssel (screeningFK) gegebene Spielzeiten.
     * @param screeningFK 
     * @return
     */
    public Vector<SurveyEntry> findSurveyEntryByScreeningFK(int screeningFK) {
        Connection con = DBConnection.connection();
        Vector<SurveyEntry> result = new Vector<SurveyEntry>();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT * FROM surveyentry "
        			+ "WHERE screeningFK=" + screeningFK);
        	
        	while(rs.next()) {
        		SurveyEntry se = new SurveyEntry();
        		se.setSurveyFK(rs.getInt("surveyFK"));
        		se.setScreeningFK(rs.getInt("screeningFK"));
        		
        		result.addElement(se);
        	}
        	
        }
        catch(SQLException e2) {
        	e2.printStackTrace();
        }
        return result;
    }

    /**
     * Löschen eines Umfrageeintrags durch die Spielzeit
     * @param screeningFK 
     */
    public void deleteSurveyEntryByScreeningFK(int screeningFK) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		Statement stmt = con.createStatement();
    		
    		stmt.executeUpdate("DELETE FROM surveyentry "
    				+ "WHERE screeningFK=" + screeningFK);
    	}
    	catch(SQLException e2) {
    		e2.printStackTrace();
    	}
        
    }


    /**
     * Auslesen aller Umfrageeinträge durch Fremdschlüssel (surveyFK) gegebene Umfragen
     * @param surveyFK
     * @return Vektor mit Umfrageeintrag-Objekten
     */
    public Vector<SurveyEntry> findSurveyEntryBySurveyFK(int surveyFK) {
    	Connection con = DBConnection.connection();
    	Vector<SurveyEntry> result = new Vector<SurveyEntry>();
    	
    	try {
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT * FROM surveyentry WHERE surveyFK=" + surveyFK);
    		
    		while(rs.next()) {
    			SurveyEntry se = new SurveyEntry();
    			se.setScreeningFK(rs.getInt("screeningFK"));
    			se.setSurveyFK(rs.getInt("surveyFK"));
    			
    			result.addElement(se);
    		}
    	}
    	catch(SQLException e2) {
    		e2.printStackTrace();
    	}
    	return result;

    }
    

    /**
     * Löschen eines Umfrageeintrags durch (Fremdschlüssel) der Angabe der UmfrageID
     * @param surveyFK 
     */
    public void deleteSurveyEntryBySurveyFK(int surveyFK) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("DELETE FROM surveyentry" + "WHERE surveyFK=" + surveyFK);
        }
        catch(SQLException e2) {
        	e2.printStackTrace();
        }
    }

}