package de.hdm.SoPra_WS1920.shared.bo;

import java.util.*;

/**
 * 
 */
public class Vote extends Ownership {

    /**
     * Variables of the class Group
     */
    private int votingWeight;
    private int surveyEntryFK;
    private int PersonFK;
    

    /**
     * Default constructor
     */
    public Vote() {
    }

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
     * @return PersonFK
     */
    public int getpersonFK() {
        return PersonFK;
    }

    /**
     * @param PersonFK 
     */
    public void setPersonFK(int PersonFK) {
        this.PersonFK = PersonFK;
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
    public void setSurveyEntryFK(int surveyEntryFK) {
        this.surveyEntryFK = surveyEntryFK;
    }
    /**
   	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
   	 */
   	public String toString() {
   		return "VoteID #V" + this.getId();
   	}

}