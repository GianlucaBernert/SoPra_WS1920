package de.hdm.SP1920.shared.bo;

import java.util.*;

/**
 * 
 */
public class Vote extends Ownership {

    /**
     * Default constructor
     */
    public Vote() {
    }

    /**
     * 
     */
    private int votingWeight;

    /**
     * 
     */
    private int surveyEntryFK;


    /**
     * 
     */
    public Person participant;

    /**
     * 
     */
    public SurveyEntry Survey subject;


    /**
     * @return
     */
    public int getVotingWeight() {
        // TODO implement here
        return 0;
    }

    /**
     * @param votingWeight 
     * @return
     */
    public void setVotingWeight(int votingWeight) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public int getSurveyEntryFK() {
        // TODO implement here
        return 0;
    }

    /**
     * @param surveyEntryFK 
     * @return
     */
    public void setSurveyEntryID(int surveyEntryFK) {
        // TODO implement here
        return null;
    }

}