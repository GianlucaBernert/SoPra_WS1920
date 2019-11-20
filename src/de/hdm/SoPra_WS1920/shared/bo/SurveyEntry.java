package de.hdm.SoPra_WS1920.shared.bo;

import java.util.*;

/**
 * 
 */
public class SurveyEntry extends BusinessObject {

    /**
     * Default constructor
     */
    public SurveyEntry() {
    }

    /**
     * 
     */
    private int screeningFK;

    /**
     * 
     */
    private int surveyFK;

    /**
     * 
     */
    public Screening Survey Content;

    /**
     * 
     */
    public Survey optionlist;

    /**
     * 
     */
    public Set<Vote> preference;




    /**
     * @return
     */
    public int getScreeningFK() {
        // TODO implement here
        return 0;
    }

    /**
     * @param screeningFK 
     * @return
     */
    public void setScreeningFK(int screeningFK) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public int getSurveyFK() {
        // TODO implement here
        return 0;
    }

    /**
     * @param survexFK 
     * @return
     */
    public void setSurveyFK(int survexFK) {
        // TODO implement here
        return null;
    }

}