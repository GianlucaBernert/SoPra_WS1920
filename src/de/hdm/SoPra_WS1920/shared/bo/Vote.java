package de.hdm.SoPra_WS1920.shared.bo;

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
     * @return
     */
    public int getVotingWeight() {
        return votingWeight;
    }

    /**
     * @param votingWeight 
     */
    public void setVotingWeight(int votingWeight) {
       this.votingWeight = votingWeight;
    }

    /**
     * @return surveyEntryFK
     */
    public int getSurveyEntryFK() {
        return surveyEntryFK;
    }

    /**
     * @param surveyEntryFK 
     */
    public void setSurveyEntryID(int surveyEntryFK) {
        this.surveyEntryFK = surveyEntryFK;
    }

}