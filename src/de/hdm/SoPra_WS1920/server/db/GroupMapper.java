package de.hdm.SoPra_WS1920.server.db;

import java.util.Vector;

import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Movie;

import java.sql.*;

/**
 * @author David Flattich
 * 
 * 
 * Mit Hilfe der MapperKlasse <code>GroupMapper</code> werden Group-Objekte auf eine relationale Datenbank abgebildet.
 * Durch das implementieren der Methoden können Group-Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden.
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
	 * Beginn: Standard-Mapper-Methoden. Innerhalb dieses Bereichs werden alle Methoden aufgezählt, die
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
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM popcorns.group " + "WHERE bo_id= " + groupID);
			
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
		
    		Statement stm1 = con.createStatement();
    		Statement stm2 = con.createStatement();
    		
    		stm1.executeUpdate("INSERT INTO businessobject (bo_id, creationTimeStamp) VALUES ('"
    							+group.getId()
    							+ "', '"+group.getCreationTimestamp()
    							+"')");
			stm2.executeUpdate("INSERT INTO popcorns.group (bo_id, name, creationTimeStamp) VALUES ('"
								+group.getId()
								+"', '"+group.getName()
								+"', '"+group.getCreationTimestamp()
								+"')");
		}
			catch(SQLException exc) {
				exc.printStackTrace();
			
			}
        return group;
    }

    /**
	 * Methode, die das Updaten eines Group-Objekts in der Datenbank ermöglicht	
	 * @param group
	 */
    
    public Group updateGroup(Group group) {
    	Connection con = DBConnection.connection();

    	try {
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("UPDATE popcorns.group Set name='"+group.getName()
    				+"' Where bo_id="+group.getId());
    	}
    		catch(SQLException exc) {
    			exc.printStackTrace();
    			}
        return null;
    }

    /**
	 * Methode, die das Loeschen eines Group-Objekts aus der Datenbank ermöglicht
	 * @param group
	 */
    
    public void deleteGroup(Group group) {
    	Connection con = DBConnection.connection();
    	
    	try {
			Statement stm1 = con.createStatement();
			Statement stm2 = con.createStatement();
			
			stm1.executeUpdate("Delete from popcorns.group Where bo_id = "+group.getId());
			stm2.executeUpdate("Delete from businessobject Where bo_id = "+group.getId());
			
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
			
			ResultSet rs = stmt.executeQuery("SELECT popcorns.group.bo_id, popcorns.group.name FROM popcorns.group"
					+ " INNER JOIN membership ON membership.personFK=" + personFK);
		
			while (rs.next()) {
				Group g = new Group();
				g.setName(rs.getString("name"));
				g.setPersonFK(rs.getInt("personFK"));
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
							+ "ON businessownership.personFK =" + personFK+
					"AND businessownership.bo_id = popcorns.group.bo_id");
				
						
		}
		catch (SQLException e) {
			e.printStackTrace();
		}        
    }
    
    /**
     * @param survey 
     * @return
     */
    /**public Vector<Survey> findGroupBySurveyFK(Survey survey) {
    	Connection con = DBConnection.connection();
		Vector<Survey> result = new Vector<Survey>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT group.bo_id, group.name"
					+ " FROM group INNER JOIN survey ON name='" +name+"'");
		
			while (rs.next()) {
				Movie m = new Movie();
				m.setName(rs.getString("name"));
				m.setGenre(rs.getString("genre"));
				m.setDescription(rs.getString("description"));
				result.add(m);
				
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
        return result;
    }
      Überhaupt nötig?
    /**
     * @param survey 
     * @return
     
    public void deleteGroupBySurveyFK(Survey survey) {
        // TODO implement here
        return null;
    }  
    Überhaupt nötig?
    */
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