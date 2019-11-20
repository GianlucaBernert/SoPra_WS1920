
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
     * @return screeningFK
     */
    public int getScreeningFK() {
        return screeningFK;
    }

    /**
     * @param screeningFK 
     */
    public void setScreeningFK(int screeningFK) {
        this.screeningFK = screeningFK;
    }

    /**
     * @return surveyFK
     */
    public int getSurveyFK() {
       return surveyFK;
    }

    /**
     * @param survexFK 
     */
    public void setSurveyFK(int survexFK) {
     this.surveyFK = surveyFK;
    }

}