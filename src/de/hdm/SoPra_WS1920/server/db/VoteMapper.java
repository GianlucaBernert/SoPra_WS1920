package de.hdm.SoPra_WS1920.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.SurveyEntry;
import de.hdm.SoPra_WS1920.shared.bo.Vote;

/**
 * @author David Flattich
 * 
 * 
 * Mit Hilfe der MapperKlasse <code>VoteMapper</code> werden Movie-Objekte auf eine relationale Datenbank abgebildet.
 * Durch das implementieren der Methoden können Vote-Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden.
 * 
 */
public class VoteMapper {

	/**
     * Durch einem sogeannten <b>Singleton<b> kann die Klasse VoteMapper nur einmal instantiiert werden.
	 * Mit Hilfe von <code>static</code> wird dies umgesetzt.
     */
   
    private static VoteMapper voteMapper = null;

    /**
     * Ein gesch?tzter Konstruktor der weitere Instanzierungen von VoteMapper Objekten verhindert.
     */
    protected VoteMapper() {
    }

    /**
	 * Stellt die Singeleton-Eigenschaft der Mapperklasse sicher
	 * Sie daf?r sorgt, dass nur eine einzige Instanz von <code>VoteMapper</code> existiert.
	 * @return Sie gibt den VoteMapper zur?ck.
	 */ 
    public static VoteMapper voteMapper() {
        if (voteMapper == null) {
        	voteMapper = new VoteMapper();
        }
        return voteMapper;
    }

    /**
     * @param Vote
     *           Prim?rschl?sselattribut (->DB)
	 * @return Vote-Objekt, das dem ?bergebenen Schl?ssel entspricht, null bei
	 *         nicht vorhandenem DB
	 */
    
    /*
  	 * =============================================================================================
  	 * Beginn: Standard-Mapper-Methoden. Innerhalb dieses Bereichs werden alle Methoden aufgezählt, die
  	 * in allen Mapper-Klassen existieren.
  	 * 
  	 */	
    
    /**
     * @param voteID 
     * @return
     */
    public Vote findVoteByID(int voteID) {
    	Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM vote " + "WHERE bo_id= " + voteID);
			
			if(rs.next()) {
				Vote v = new Vote();
				v.setVotingWeight(rs.getInt("votingWeight"));
				v.setSurveyEntryFK(rs.getInt("surveyentryFK"));
				return v;
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
        return null;
    }

    
    
    
    public Vote insertVote(Vote vote) {
    	Connection con = DBConnection.connection();

    	try {
    		Statement stmt = con.createStatement();
        	
    	    ResultSet rs = stmt.executeQuery("SELECT MAX(bo_id) AS maxid "
    	          + "FROM businessobject ");

    	      if (rs.next()) {
    	     
    	        vote.setId(rs.getInt("maxid") + 1);
		
    		Statement stm1 = con.createStatement();
    		Statement stm2 = con.createStatement();
    		
    		stm1.executeUpdate("INSERT INTO businessobject (bo_id, creationTimeStamp) VALUES ('"
    							+vote.getId()
								+"', '"+vote.getCreationTimestamp()
								+"')");
			stm2.executeUpdate("INSERT INTO vote (bo_id, votingWeight, surveyentryFK, creationTimeStamp) VALUES ('"
								+vote.getId()
								+"', '"+vote.getVotingWeight()
								+"', '"+vote.getSurveyEntryFK()
								+"', '"+vote.getCreationTimestamp()
								+"')");
			
		}
    	}
			catch(SQLException exc) {
				exc.printStackTrace();
			
			}
        return vote;
    }

    /**
     * @param vote 
     * @return
     */
    public Vote updateVote(Vote vote) {
    	Connection con = DBConnection.connection();

    	try {
    	
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("UPDATE vote Set votingWeight='"+vote.getVotingWeight()
    				+"' Where bo_id="+vote.getId());
    	}
    		catch(SQLException exc) {
    			exc.printStackTrace();
    			}
        return null;
    }

    /**
     * @param vote 
     * @return
     */
    public void deleteVote(Vote vote) {
    	Connection con = DBConnection.connection();
    	
    	try {
			Statement stm1 = con.createStatement();
			Statement stm2 = con.createStatement();
			
			stm1.executeUpdate("Delete from vote Where bo_id = "+vote.getId());
			stm2.executeUpdate("Delete from businessobject Where bo_id = "+vote.getId());
			
		}catch(SQLException e2) {
			e2.printStackTrace();
		}
    }

  
    /**
     * @param personFK 
     * @return
     */
    public Vector<Vote> findVoteByPersonFK(int personFK) {
    	Connection con = DBConnection.connection();
		Vector<Vote> result = new Vector<Vote>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT vote.bo_id, vote.votingWeight, vote.surveyEntryFK FROM vote INNER JOIN businessownership" + 
					"WHERE businessownership.bo_id = vote.bo_id AND businessownership.personFK = "+personFK);
		
			while (rs.next()) {
				Vote v = new Vote();
				v.setVotingWeight(rs.getInt("votingWeight"));
				v.setSurveyEntryFK(rs.getInt("surveyEntryFK"));
				result.add(v);
				
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
    /**public void deleteVoteByPersonFK(int personFK) {
    	Connection con = DBConnection.connection();
		
		try {
			Statement stm1 = con.createStatement();
			stm1.executeUpdate("Delete vote FROM vote INNER JOIN businessownership" 
							+ "ON businessownership.personFK =" + personFK+
					"AND businessownership.bo_id = popcorns.vote.bo_id");
				
						
		}
		catch (SQLException e) {
			e.printStackTrace();
		}        
    }
    Umsetzung überhaupt nötig?
    		*/

    /**
     * @param id 
     * @return
     */
    public Vector<Vote> findVoteBySurveyEntryFK(int surveyEntryFK) {
    	Connection con = DBConnection.connection();
		Vector<Vote> result = new Vector<Vote>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM vote Where surveyEntryFK =" +surveyEntryFK);
		
			while (rs.next()) {
				Vote v = new Vote();
				v.setVotingWeight(rs.getInt("votingWeight"));
				v.setSurveyEntryFK(rs.getInt("surveyEntryFK"));
				result.add(v);
				
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
        return result;
    }

    /**
     * @param surveyentry 
     * @return
     */
    /**public void deleteVoteBySurveyEntryFK(Surveyentry surveyentry) {
        // TODO implement here
        return null;
    }
    Umsetzung überhaupt mötig?
    */
    /**
     * @param surveyentry 
     * @return
     */
    public Vector<Vote> findVoteByVotingWeight(int vw) {
    	Connection con = DBConnection.connection();
		Vector<Vote> result = new Vector<Vote>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM vote Where votingWeight = " + vw);
		
			while (rs.next()) {
				Vote v = new Vote();
				v.setVotingWeight(rs.getInt("votingWeight"));
				v.setSurveyEntryFK(rs.getInt("surveyEntryFK"));
				result.add(v);
				
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
        return result;
    }

    /**
     * @param surveyentry 
     * @return
     */
    /**public void deleteVoteByVotingWeight(int vw) {
        // TODO implement here
        return null;
    }
    Umsetzung überhaupt nötig?
    */
}