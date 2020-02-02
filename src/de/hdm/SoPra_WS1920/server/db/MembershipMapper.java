package de.hdm.SoPra_WS1920.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.SoPra_WS1920.shared.bo.Membership;

/**
 * @author David Flattich
 * 
 * 
 * Mit Hilfe der MapperKlasse <code>MembershipMapper</code> werden Membership-Objekte auf eine relationale Datenbank abgebildet.
 * Durch das implementieren der Methoden k�nnen Membership-Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden.
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
	 * Beginn: Standard-Mapper-Methoden. Innerhalb dieses Bereichs werden alle Methoden aufgez�hlt, die
	 * in allen Mapper-Klassen existieren.
	 * 
	 */	
    
    /**
     * Methode, die ein Membership Objekt in der Datenbank speichert
     * @param Membership me
     * @return Membership me
     */
    
    public Membership insertMembership(Membership me) {
    	Connection con = DBConnection.connection();

    	try {
    		con.setAutoCommit(false);
    		Statement stm1 = con.createStatement();
    		
    		stm1.executeUpdate("INSERT INTO membership (groupFK, personFK) VALUES ('"
    							+me.getGroupFK()
    							+ "', '"+me.getPersonFK()
    							+"')");
    		con.setAutoCommit(true);
		}
			catch(SQLException exc) {
				exc.printStackTrace();
			
			}
        return me;
    }
    /**
     * Methode, die alle Memberships einer Person zur�ckgibt.
     * @param int personFK
     * @return Vektor Membership
     */
    public Vector<Membership> findMembershipByPersonFK(int pFK) {
        Connection con = DBConnection.connection();
        Vector<Membership> result = new Vector<Membership>();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT * FROM membership "
        			+ "WHERE personFK=" + pFK);
        	
        	while(rs.next()) {
        		Membership me = new Membership();
//        		me.setId(rs.getInt("id"));
        		me.setGroupFK(rs.getInt("groupFK"));
        		me.setPersonFK(rs.getInt("personFK"));
        		
        		result.addElement(me);
        	}
        	
        }
        catch(SQLException e2) {
        	e2.printStackTrace();
        }
        return result;
    }
    /**
     * Methode, die alle Memberships einer Gruppe zur�ckgibt.
     * @param int groupID 
     * @return Vektor Membership
     */
    public Vector<Membership> findMembershipByGroupFK(int gFK) {
        Connection con = DBConnection.connection();
        Vector<Membership> result = new Vector<Membership>();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT * FROM membership "
        			+ "WHERE groupFK=" + gFK);
        	
        	while(rs.next()) {
        		Membership me = new Membership();
//        		me.setId(rs.getInt("id"));
        		me.setGroupFK(rs.getInt("groupFK"));
        		me.setPersonFK(rs.getInt("personFK"));
        		
        		result.addElement(me);
        	}
        	
        }
        catch(SQLException e2) {
        	e2.printStackTrace();
        }
        return result;
    }


    

    /**
	 * Methode, die das Loeschen eines Membership-Objekts aus der Datenbank erm�glicht
	 * @param membership
	 */
    
    public void deleteMembership(int gFK, int pFK) {
    	Connection con = DBConnection.connection();
    	
    	try {
			Statement stm1 = con.createStatement();
			
			stm1.executeUpdate("Delete from popcorns.membership Where groupFK = "+gFK 
			+" AND membership.personFK= "+pFK);
			
		}catch(SQLException e2) {
			e2.printStackTrace();
		}
    }
    
    

}