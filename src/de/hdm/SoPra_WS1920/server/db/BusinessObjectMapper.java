package de.hdm.SoPra_WS1920.server.db;

import java.util.Vector;

import de.hdm.SoPra_WS1920.shared.bo.BusinessObject;
import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Ownership;
import de.hdm.SoPra_WS1920.shared.bo.Person;
import java.sql.*;

/**
 * @author David Flattich
 * 
 * 
 * Mit Hilfe der MapperKlasse <code>BusinessObjectMapper</code> werden BusinessObject-Objekte auf eine relationale Datenbank abgebildet.
 * Durch das implementieren der Methoden können BusinessObject-Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden.
 * 
 */
public class BusinessObjectMapper {

	/**
     * Durch einem sogeannten <b>Singleton<b> kann die Klasse BusinessObjectMapper nur einmal instantiiert werden.
	 * Mit Hilfe von <code>static</code> wird dies umgesetzt.
     */
    private static BusinessObjectMapper businessObjectMapper = null;

    /**
     * Ein gesch?tzter Konstruktor der weitere Instanzierungen von BusinessObjectMapper Objekten verhindert.
     */
    protected BusinessObjectMapper() {
    }

    /**
 	 * Stellt die Singeleton-Eigenschaft der Mapperklasse sicher
 	 * Sie daf?r sorgt, dass nur eine einzige Instanz von <code>BusinessObjectMapper</code> existiert.
 	 * @return Sie gibt den BusinessObjectMapper zur?ck.
 	 */
    public static BusinessObjectMapper businessObjectMapper() {
        if(businessObjectMapper == null) {
        	businessObjectMapper = new BusinessObjectMapper();
        }
        return businessObjectMapper;
    }

    /**
     * @param BusinessObject
     *           Prim?rschl?sselattribut (->DB)
	 * @return BusinessObject-Objekt, das dem ?bergebenen Schl?ssel entspricht, null bei
	 *         nicht vorhandenem DB
	 *        
	 */
    
    /*
	 * =============================================================================================
	 * Beginn: Standard-Mapper-Methoden. Innerhalb dieses Bereichs werden alle Methoden aufgezählt, die
	 * in allen Mapper-Klassen existieren.
	 * 
	 */	
    public BusinessObject findBusinessObjectByID(int BOID) {
    	Connection con = DBConnection.connection();
		
		try {
		
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM popcorns.businessobject " + "WHERE id= " + BOID);
			
			if(rs.next()) {
				BusinessObject o = new BusinessObject();
				o.setCreationTimestamp(rs.getTimestamp("CreationTimestamp"));
				o.setId(rs.getInt("id"));
				return o;
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
        return null;
    }

    //Methode, die das Einfügen eines BusinessObjects in die Datenbank ermöglicht
    
    public BusinessObject insertBusinessObject(BusinessObject BO) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		con.setAutoCommit(false);
    		
    		Statement stmt = con.createStatement();
        	
    	    ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
    	          + "FROM businessobject ");

    	    if (rs.next()) {
    	     
    	    BO.setId(rs.getInt("maxid") + 1);
    		Statement stm1 = con.createStatement();
    		
    		stm1.executeUpdate("INSERT INTO businessobject (id, CreationTimestamp) VALUES ('"
    				+ BO.getId()
					+ "', '"+BO.getCreationTimestamp()
					+"')");
    		
    	}con.setAutoCommit(true);
    	}
        catch(SQLException exc) {
        	exc.printStackTrace();
        }
        return BO;
    }

   
    

    /**
	 * Methode, die das Loeschen eines BusinessObject-Objekts aus der Datenbank ermöglicht
	 * @param ownership
	 */
    public void deleteBusinessObject(BusinessObject businessobject) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		Statement stm1 = con.createStatement();
    		stm1.executeUpdate("Delete from businessobject Where id = "+businessobject.getId());
			
		}catch(SQLException e2) {
			e2.printStackTrace();
		}
    }
}