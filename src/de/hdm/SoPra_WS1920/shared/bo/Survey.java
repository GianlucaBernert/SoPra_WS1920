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
    private Timestamp startDate;
    private Timestamp endDate;

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
     * Methode um das end Datum einer Umfrage auszugeben
     * @return Timestamp endDate
     */
    public Date getEndDate() {
       return endDate;
    }

    /**
     * Methode um das end Datum einer Umfrage zu setzen
     * @param Timestamp endDate 
     */
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

	/**
	 * Methode um das start Datum einer Umfrage auszugeben
	 * @return Timestamp startDate
	 */
	public Timestamp getStartDate() {
		return startDate;
	}

	/**
	 * Methode um das start Datum einer Umfrage zu setzen
	 * @param Timesrtamp startDate
	 */
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	
	/**
   	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
   	 * @return String SurveyID
   	 */
   	public String toString() {
   		return "SurveyID #S" + this.getId();
   	}
}