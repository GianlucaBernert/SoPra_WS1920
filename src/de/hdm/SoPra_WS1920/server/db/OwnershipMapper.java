package de.hdm.SoPra_WS1920.server.db;

import java.util.Vector;

import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Ownership;
import de.hdm.SoPra_WS1920.shared.bo.Person;
import java.sql.*;

/**
 * @author David Flattich
 * 
 * 
 * Mit Hilfe der MapperKlasse <code>OwnershipMapper</code> werden Group-Objekte auf eine relationale Datenbank abgebildet.
 * Durch das implementieren der Methoden können Ownership-Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden.
 * 
 */
public class OwnershipMapper {

	/**
     * Durch einem sogeannten <b>Singleton<b> kann die Klasse OwnershipMapper nur einmal instantiiert werden.
	 * Mit Hilfe von <code>static</code> wird dies umgesetzt.
     */
    private static OwnershipMapper ownershipMapper = null;

    /**
     * Ein gesch?tzter Konstruktor der weitere Instanzierungen von OwnsershipMapper Objekten verhindert.
     */
    protected OwnershipMapper() {
    }

    /**
 	 * Stellt die Singeleton-Eigenschaft der Mapperklasse sicher
 	 * Sie daf?r sorgt, dass nur eine einzige Instanz von <code>OwnershipMapper</code> existiert.
 	 * @return Sie gibt den OwnershipMapper zur?ck.
 	 */
    public static OwnershipMapper ownershipMapper() {
        if(ownershipMapper == null) {
        	ownershipMapper = new OwnershipMapper();
        }
        return ownershipMapper;
    }

    /**
     * @param Ownership
     *           Prim?rschl?sselattribut (->DB)
	 * @return Ownership-Objekt, das dem ?bergebenen Schl?ssel entspricht, null bei
	 *         nicht vorhandenem DB
	 *        
	 */
    
    /*
	 * =============================================================================================
	 * Beginn: Standard-Mapper-Methoden. Innerhalb dieses Bereichs werden alle Methoden aufgezählt, die
	 * in allen Mapper-Klassen existieren.
	 * 
	 */	
    public Ownership findOwnershipByID(int ownershipID) {
    	Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM popcorns.businessownership WHERE id= " + ownershipID);
			
			if(rs.next()) {
				Ownership o = new Ownership();
				o.setPersonFK(rs.getInt("personFK"));
				o.setId(rs.getInt("id"));
				return o;
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
        return null;
    }

    
    public Ownership insertOwnership(Ownership ownership) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		con.setAutoCommit(false);
    		
    		Statement stm1 = con.createStatement();
    		
    		stm1.executeUpdate("INSERT INTO businessownership (id, PersonFK) VALUES ('"
    				+ ownership.getId()
					+ "', '"+ownership.getPersonFK()
					+"')");
    	con.setAutoCommit(true);
    	}
        catch(SQLException exc) {
        	exc.printStackTrace();
        }
        return ownership;
    }

    /**
   	 * Methode, die das Updaten eines Ownership-Objekts in der Datenbank ermöglicht	
   	 * @param ownership
   	 */
    public Ownership updateOwnership(Ownership ownership) {
    	Connection con = DBConnection.connection();

    	try {
    		con.setAutoCommit(false);
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("UPDATE popcorns.businessownership Set personFK='"+ownership.getPersonFK()
    				+"' Where id="+ownership.getId());
    		con.setAutoCommit(true);
    	}
    		catch(SQLException exc) {
    			exc.printStackTrace();
    			}
        return null;
    }

    /**
	 * Methode, die das Loeschen eines Ownership-Objekts aus der Datenbank ermöglicht
	 * @param ownership
	 */
    public void deleteOwnership(Ownership ownership) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		Statement stm1 = con.createStatement();
    		stm1.executeUpdate("Delete from businessownership Where id = "+ownership.getId());
			
		}catch(SQLException e2) {
			e2.printStackTrace();
		}
    }

    /* Ende: Standard-Mapper-Methoden
 	 * ================================================================================================
 	 * Beginn: Foreign Key-Mapper-Methoden
 	 */
    public Vector<Ownership> findOwnershipByPersonFK(int personFK) {
    	Connection con = DBConnection.connection();
		Vector<Ownership> result = new Vector<Ownership>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM businessownership "
					+ "WHERE businessownership.personFK= '"+personFK+"'");
		
			while (rs.next()) {
				Ownership o = new Ownership();
				o.setPersonFK(rs.getInt("personFK"));
				o.setId(rs.getInt("id"));
				result.add(o);
				
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
        return result;
    }
}