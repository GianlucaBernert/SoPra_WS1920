package de.hdm.SoPra_WS1920.server.db;

import java.util.Vector;

import de.hdm.SoPra_WS1920.shared.bo.SurveyEntry;
import de.hdm.SoPra_WS1920.shared.bo.Vote;

/**
 * 
 */
public class VoteMapper {

    /**
     * Default constructor
     */
    public VoteMapper() {
    }

    /**
     * 
     */
    public VoteMapper voteMapper;

    /**
     * 
     */
    protected void VoteMapper() {
        // TODO implement here
    }

    /**
     * @return
     */
    public static VoteMapper voteMapper() {
        // TODO implement here
        return null;
    }

    /**
     * @param vote 
     * @return
     */
    public Vote insertVote(Vote vote) {
        // TODO implement here
        return null;
    }

    /**
     * @param vote 
     * @return
     */
    public Vote updateVote(Vote vote) {
        // TODO implement here
        return null;
    }

    /**
     * @param vote 
     * @return
     */
    public void deleteVote(Vote vote) {
        // TODO implement here
        return null;
    }

    /**
     * @param voteID 
     * @return
     */
    public Vote findVoteByID(int voteID) {
        // TODO implement here
        return null;
    }

    /**
     * @param personFK 
     * @return
     */
    public Vector<Vote> findVoteByPersonFK(int personFK) {
        // TODO implement here
        return null;
    }

    /**
     * @param person 
     * @return
     */
    public void deleteVoteByPersonFK(String person) {
        // TODO implement here
        return null;
    }

    /**
     * @param id 
     * @return
     */
    public Vector<Vote> findVoteBySurveyEntryFK(int id) {
        // TODO implement here
        return null;
    }

    /**
     * @param surveyentry 
     * @return
     */
    public void deleteVoteBySurveyEntryFK(Surveyentry surveyentry) {
        // TODO implement here
        return null;
    }
    
    /**
     * @param surveyentry 
     * @return
     */
    public Vector<Vote> findVoteByVotingWeight(int vw) {
        // TODO implement here
        return null;
    }

    /**
     * @param surveyentry 
     * @return
     */
    public void deleteVoteByVotingWeight(int vw) {
        // TODO implement here
        return null;
    }
}