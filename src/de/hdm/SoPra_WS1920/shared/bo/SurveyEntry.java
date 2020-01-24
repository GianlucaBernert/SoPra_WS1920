
package de.hdm.SoPra_WS1920.shared.bo;

import java.util.*;

/**
 * @author GianlucaBernert
 * Klasse SurveyEntry, welche die Attribute screeningFK, surveyFK, personFK direkt enthält
 * sie erbt von der Klasse BusinessObject die SerialVersionUID, die id und den Erstellzeitpunkt
 */
public class SurveyEntry extends BusinessObject {

    /**
     * Variablen der Klasse SurveyEntry
     */
    private int screeningFK;
    private int surveyFK;
    private int personFK;

    /**
     * Konstruktor der Klasse SurveyEntry, welcher beim Aufruf dieser eine Instanz seiner selbst erzeugt
     */
    public SurveyEntry() {
    }
   
    /**
     * Methode um den screeningFK eines Umfrageeintrags auszugeben
     * @return int screeningFK
     */
    public int getScreeningFK() {
        return screeningFK;
    }

    /**
     * Methode um den screeningFK eines Umfrageeintrags zu setzen 
     * @param int screeningFK 
     */
    public void setScreeningFK(int screeningFK) {
        this.screeningFK = screeningFK;
    }

    /**
     * Methode um den surveyFK eines Umfrageeintrags auszugeben
     * @return int surveyFK
     */
    public int getSurveyFK() {
       return surveyFK;
    }

    /**
     * Methode um den surveyFK eines Umfrageeintrags zu setzen 
     * @param int survexFK 
     */
    public void setSurveyFK(int surveyFK) {
     this.surveyFK = surveyFK;
    }
    
    /**
     * Methode um den personFK eines Umfrageeintrags auszugeben
	 * @return int personFK
	 */
	public int getPersonFK() {
		return personFK;
	}

	/**
	 * Methode um den personFK eines Umfrageeintrags zusetzen
	 * @param int personFK
	 */
	public void setPersonFK(int personFK) {
		this.personFK = personFK;
	}

	/**
   	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
   	 * @return String SurveyEntryID
   	 */
   	public String toString() {
   		return "SurveyEntryID #SE" + this.getId();
   	}
   	
   	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SurveyEntry) {
			SurveyEntry sE = (SurveyEntry) obj;
			if((this.getId()== sE.getId())){				
				return true;	
			}
			else {
				return false;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = this.getId();
		return result;
	}

}