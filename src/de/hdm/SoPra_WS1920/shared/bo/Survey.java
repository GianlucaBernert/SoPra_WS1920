package de.hdm.SP1920.shared.bo;

import java.util.*;

/**
 * 
 */
public class Survey extends Ownership {

    /**
     * Default constructor
     */
    public Survey() {
    }

    /**
     * 
     */
    private int groupFK;

    /**
     * 
     */
    private Datetime endDate;

    /**
     * 
     */
    public Group valid;

    /**
     * 
     */
    public Set<SurveyEntry> vote object;



    /**
     * 
     */
    public Person creator;


    /**
     * @return
     */
    public int getGroupFK() {
        // TODO implement here
        return 0;
    }

    /**
     * @param groupFK 
     * @return
     */
    public void setGroupFK(int groupFK) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Datetime getEndDate() {
        // TODO implement here
        return null;
    }

    /**
     * @param endDate 
     * @return
     */
    public void setEndDate(Datetime endDate) {
        // TODO implement here
        return null;
    }

}