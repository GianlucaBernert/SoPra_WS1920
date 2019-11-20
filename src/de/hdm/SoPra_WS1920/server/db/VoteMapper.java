package de.hdm.SoPra_WS1920.server.db;

import java.util.*;

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
     * @param person 
     * @return
     */
    public vector<Vote> findVoteByPersonFK(String person) {
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
     * @param surveyentry 
     * @return
     */
    public vector<Vote> findVoteBySurveyEntryFK(SurveyEntry surveyentry) {
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

}