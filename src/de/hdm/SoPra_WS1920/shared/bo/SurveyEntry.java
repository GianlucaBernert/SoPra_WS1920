
package de.hdm.SoPra_WS1920.shared.bo;

import java.util.*;

/**
 * 
 */
public class SurveyEntry extends BusinessObject {

    /**
     * Variables of the class Group
     */
    private int screeningFK;
    private int surveyFK;
    private int personFK;

    /**
     * Default constructor
     */
    public SurveyEntry() {
    }
   
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
    public void setSurveyFK(int surveyFK) {
     this.surveyFK = surveyFK;
    }
    
    /**
	 * @return the personFK
	 */
	public int getPersonFK() {
		return personFK;
	}

	/**
	 * @param personFK the personFK to set
	 */
	public void setPersonFK(int personFK) {
		this.personFK = personFK;
	}

	/**
   	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
   	 */
   	public String toString() {
   		return "SurveyEntryID #SE" + this.getId();
   	}

}