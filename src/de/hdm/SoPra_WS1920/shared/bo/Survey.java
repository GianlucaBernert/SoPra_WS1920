package de.hdm.SoPra_WS1920.shared.bo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author GianlucaBernert
 * Klasse Survey, welche die Attribute groupFK, startDate und endDate direkt enthält
 * und die die Beziehung zwischen Person und einer Umfrage von der Klasse Ownership erbt, 
 * diese wiederum erbt SerialVersionUID, die ID und den Erstellzeitpunkt von BusinessObject.
 */
public class Survey extends Ownership { 

    /**
     * Variablen der Klasse Survey
     */
    private int groupFK;

    /**
     * Konstruktor der Klasse Survey, welcher beim Aufruf dieser eine Instanz seiner selbst erzeugt
     */
    public Survey() {
    }

    /**
     * Methode um den groupFK einer Umfrage auszugeben
     * @return int group
     */
    public int getGroupFK() {
        return groupFK;
    }

    /** 
     * Methode um den groupFK einer Umfrage zu setzen 
     * @return int groupFK
     */
    public void setGroupFK(int groupFK) {
        this.groupFK = groupFK;
    }
	
	/**
   	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
   	 * @return String SurveyID
   	 */
   	public String toString() {
   		return "SurveyID #S" + this.getId();
   	}
}