package de.hdm.SoPra_WS1920.server.db;

import java.util.Vector;

import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Person;

import java.sql.*;

/**
 * @author David Flattich
 * 
 * 
 * Mit Hilfe der MapperKlasse <code>MembershipMapper</code> werden Membership-Objekte auf eine relationale Datenbank abgebildet.
 * Durch das implementieren der Methoden können Membership-Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden.
 * 
 */
public class MembershipMapper {

	/**
     * Durch einem sogeannten <b>Singleton<b> kann die Klasse MembershipMapper nur einmal instantiiert werden.
	 * Mit Hilfe von <code>static</code> wird dies umgesetzt.
     */
	
	private static MembershipMapper membershipMapper = null;
	

    /**
     * Ein gesch?tzter Konstruktor der weitere Instanzierungen von MembershipMapper Objekten verhindert.
     */
	
    protected MembershipMapper() {
    }

    /**
 	 * Stellt die Singeleton-Eigenschaft der Mapperklasse sicher
 	 * Sie daf?r sorgt, dass nur eine einzige Instanz von <code>MembershipMapper</code> existiert.
 	 * @return Sie gibt den MembershipMapper zur?ck.
 	 */
 
    
    public static MembershipMapper membershipMapper() {
        if(membershipMapper == null) {
        	membershipMapper = new MembershipMapper();
        }
        return membershipMapper;
    }

    /**
     * @param Membership
     *           Prim?rschl?sselattribut (->DB)
	 * @return Membership-Objekt, das dem ?bergebenen Schl?ssel entspricht, null bei
	 *         nicht vorhandenem DB
	 */
    
    /*
	 * =============================================================================================
	 * Beginn: Standard-Mapper-Methoden. Innerhalb dieses Bereichs werden alle Methoden aufgezählt, die
	 * in allen Mapper-Klassen existieren.
	 * 
	 */	
    

    
    public Group insertMembership(Group group, Person person) {
    	Connection con = DBConnection.connection();

    	try {
    		con.setAutoCommit(false);
    		Statement stm1 = con.createStatement();
    		
    		stm1.executeUpdate("INSERT INTO membership (groupFK, personFK) VALUES ('"
    							+group.getId()
    							+ "', '"+person.getId()
    							+"')");
    		con.setAutoCommit(true);
		}
			catch(SQLException exc) {
				exc.printStackTrace();
			
			}
        return group;
    }

    

    /**
	 * Methode, die das Loeschen eines Membership-Objekts aus der Datenbank ermöglicht
	 * @param membership
	 */
    
    public void deleteMembership(Group group, Person person) {
    	Connection con = DBConnection.connection();
    	
    	try {
			Statement stm1 = con.createStatement();
			
			stm1.executeUpdate("Delete from popcorns.membership Where groupFK = "+group.getId() 
			+"AND membership.personFK = "+person.getId());
			
		}catch(SQLException e2) {
			e2.printStackTrace();
		}
    }

}