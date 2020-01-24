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
 * Durch das implementieren der Methoden k�nnen Vote-Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden.
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
  	 * Beginn: Standard-Mapper-Methoden. Innerhalb dieses Bereichs werden alle Methoden aufgez�hlt, die
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
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM vote WHERE id= " + voteID);
			
			if(rs.next()) {
				Vote v = new Vote();
				v.setVotingWeight(rs.getInt("votingWeight"));
				v.setSurveyEntryFK(rs.getInt("surveyentryFK"));
				v.setId(rs.getInt("id"));
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
    		Statement stm1 = con.createStatement();
    		
			stm1.executeUpdate("INSERT INTO vote (id, votingWeight, surveyentryFK) VALUES ('"
								+vote.getId()
								+"', '"+vote.getVotingWeight()
								+"', '"+vote.getSurveyEntryFK()
								+"')");
			
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
    				+"' Where id="+vote.getId());
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
			
			stm1.executeUpdate("Delete from vote Where id = "+vote.getId());
			
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
			
			ResultSet rs = stmt.executeQuery("SELECT vote.id, vote.votingWeight, vote.surveyEntryFK FROM vote "
					+ "INNER JOIN businessownership "
					+ "WHERE businessownership.id = vote.id "
					+ "AND businessownership.personFK = "+personFK);
		
			while (rs.next()) {
				Vote v = new Vote();
				v.setId(rs.getInt("id"));
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
 public void deleteVoteByPersonFK(int personFK) {
    	Connection con = DBConnection.connection();
		
		try {
			Statement stm1 = con.createStatement();
			stm1.executeUpdate("Delete vote FROM vote INNER JOIN businessownership "
					+ "ON businessownership.personFK =" + personFK+
					"AND businessownership.id = popcorns.vote.id");
				
						
		}
		catch (SQLException e) {
			e.printStackTrace();
		}        
    }
 

    /**
     * @param id 
     * @return
     */
    public Vector<Vote> findVoteBySurveyEntryFK(int surveyEntryFK) {
    	Connection con = DBConnection.connection();
		Vector<Vote> result = new Vector<Vote>();
		
		try {
			Statement stmt = con.createStatement();
			
//			ResultSet rs = stmt.executeQuery("SELECT * FROM vote Where surveyEntryFK =" +surveyEntryFK);
			ResultSet rs = stmt.executeQuery("Select vote.id, votingWeight, surveyentryFK, surveyFK, screeningFK, personFK from vote "
					+ "inner join surveyentry on vote.surveyentryFK = surveyentry.id "
					+ "inner join businessownership on vote.id=businessownership.id where surveyEntryFK="+ surveyEntryFK);
		
			while (rs.next()) {
				Vote v = new Vote();
				v.setId(rs.getInt("id"));
				v.setVotingWeight(rs.getInt("votingWeight"));
				v.setSurveyEntryFK(rs.getInt("surveyEntryFK"));
				v.setPersonFK(rs.getInt("personFK"));
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
    public Vector<Vote> findVoteByVotingWeight(int vw) {
    	Connection con = DBConnection.connection();
		Vector<Vote> result = new Vector<Vote>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM vote Where votingWeight = " + vw);
		
			while (rs.next()) {
				Vote v = new Vote();
				v.setId(rs.getInt("id"));
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


}