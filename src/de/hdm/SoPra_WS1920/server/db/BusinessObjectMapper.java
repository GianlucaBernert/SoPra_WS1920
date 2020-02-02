package de.hdm.SoPra_WS1920.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.hdm.SoPra_WS1920.shared.bo.BusinessObject;

/**
 * @author David Flattich
 * 
 * 
 * Mit Hilfe der MapperKlasse <code>BusinessObjectMapper</code> werden BusinessObject-Objekte auf eine relationale Datenbank abgebildet.
 * Durch das implementieren der Methoden k�nnen BusinessObject-Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden.
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
	 *         nicht vorhandenem DB.
	 *        
	 */
    
    /*
	 * =============================================================================================
	 * Beginn: Standard-Mapper-Methoden. Innerhalb dieses Bereichs werden alle Methoden aufgez�hlt, die
	 * in allen Mapper-Klassen existieren.
	 */	
    
    /**
	 * Methode, die ein BusinessObject anhand der ID zur�ckgibt
	 * @param int ID
	 * @return businessobject
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


    /**
	 * Methode, die das Einf�gen eines BusinessObjects in die Datenbank erm�glicht
	 * @param businessobject
	 * @return businessobject
	 */
    
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
	 * Methode, die das Loeschen eines BusinessObject-Objekts aus der Datenbank erm�glicht
	 * @param businessobject
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