package de.hdm.SoPra_WS1920.server.db;

import java.util.Vector;

import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Survey;

import java.sql.*;

/**
 * @author David Flattich
 * 
 * 
 * Mit Hilfe der MapperKlasse <code>GroupMapper</code> werden Group-Objekte auf eine relationale Datenbank abgebildet.
 * Durch das implementieren der Methoden k�nnen Group-Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden.
 * 
 */
public class GroupMapper {

	/**
     * Durch einem sogeannten <b>Singleton<b> kann die Klasse GroupMapper nur einmal instantiiert werden.
	 * Mit Hilfe von <code>static</code> wird dies umgesetzt.
     */
	
	private static GroupMapper groupMapper = null;
	

    /**
     * Ein gesch?tzter Konstruktor der weitere Instanzierungen von GroupMapper Objekten verhindert.
     */
    protected GroupMapper() {
    }

    /**
 	 * Stellt die Singeleton-Eigenschaft der Mapperklasse sicher
 	 * Sie daf?r sorgt, dass nur eine einzige Instanz von <code>GroupMapper</code> existiert.
 	 * @return Sie gibt den GroupMapper zur?ck.
 	 */
 
    
    public static GroupMapper groupMapper() {
        if(groupMapper == null) {
        	groupMapper = new GroupMapper();
        }
        return groupMapper;
    }

    /**
     * @param Group
     *           Prim?rschl?sselattribut (->DB)
	 * @return Group-Objekt, das dem ?bergebenen Schl?ssel entspricht, null bei
	 *         nicht vorhandenem DB
	 */
    
    /*
	 * =============================================================================================
	 * Beginn: Standard-Mapper-Methoden. Innerhalb dieses Bereichs werden alle Methoden aufgez�hlt, die
	 * in allen Mapper-Klassen existieren.
	 * 
	 */	
    
    /**
     * @param groupID 
     * @return
     */
    public Group findGroupByID(int groupID) {
    	Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM popcorns.group WHERE id= " + groupID);
			
			if(rs.next()) {
				Group g = new Group();
				g.setName(rs.getString("name"));
				return g;
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
        return null;
    }

    
    public Group insertGroup(Group group) {
    	Connection con = DBConnection.connection();

    	try {
    		con.setAutoCommit(false);
    	        
    		Statement stm1 = con.createStatement();

			stm1.executeUpdate("INSERT INTO popcorns.group (id, name) VALUES ('"
								+group.getId()
								+"', '"+group.getName()
								+"')");
		con.setAutoCommit(true);
    	}
			catch(SQLException exc) {
				exc.printStackTrace();
			
			}
        return group;
    }

    /**
	 * Methode, die das Updaten eines Group-Objekts in der Datenbank erm�glicht	
	 * @param group
	 */
    
    public Group updateGroup(Group group) {
    	Connection con = DBConnection.connection();

    	try {
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("UPDATE popcorns.group Set name='"+group.getName()
    				+"' Where id="+group.getId());
    	}
    		catch(SQLException exc) {
    			exc.printStackTrace();
    			}
        return null;
    }

    /**
	 * Methode, die das Loeschen eines Group-Objekts aus der Datenbank erm�glicht
	 * @param group
	 */
    
    public void deleteGroup(Group group) {
    	Connection con = DBConnection.connection();
    	
    	try {
			Statement stm1 = con.createStatement();
			
			stm1.executeUpdate("Delete from popcorns.group Where id = "+group.getId());
			
		}catch(SQLException e2) {
			e2.printStackTrace();
		}
    }

    /* Ende: Standard-Mapper-Methoden
 	 * ================================================================================================
 	 * Beginn: Foreign Key-Mapper-Methoden
 	 */
    public Vector<Group> findGroupByPersonFK(int personFK) {
    	Connection con = DBConnection.connection();
		Vector<Group> result = new Vector<Group>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT popcorns.group.id, popcorns.group.name FROM popcorns.group"
					+ " INNER JOIN membership ON membership.personFK=" + personFK);
		
			while (rs.next()) {
				Group g = new Group();
				g.setName(rs.getString("name"));
				result.add(g);
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
        return result;
    }

    /**
     * @param person 
     * @return
     */
    public void deleteGroupByPersonFK(int personFK) {
    	Connection con = DBConnection.connection();
		
		try {
			Statement stm1 = con.createStatement();
			stm1.executeUpdate("Delete popcorns.group FROM popcorns.group INNER JOIN businessownership" 
							+ "ON businessownership.personFK =" + personFK);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}        
    }
    

    /**
     * @param name 
     * @return
     */
    public Vector<Group> findGroupByName(String name) {
    	Connection con = DBConnection.connection();
		Vector<Group> result = new Vector<Group>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM popcorns.group Where name='" +name+"'");
		
			while (rs.next()) {
				Group g = new Group();
				g.setName(rs.getString("name"));
				result.add(g);
				
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
        return result;
    }

    
    /**
     * @param id 
     * @return
     */
    

}