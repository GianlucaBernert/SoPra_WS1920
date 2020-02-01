package de.hdm.SoPra_WS1920.shared.bo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author GianlucaBernert
 * Klasse Survey, welche die Attribute groupFK, startDate und endDate direkt enth√§lt
 * und die die Beziehung zwischen Person und einer Umfrage von der Klasse Ownership erbt, 
 * diese wiederum erbt SerialVersionUID, die ID und den Erstellzeitpunkt von BusinessObject.
 */
public class Survey extends Ownership { 

    /**
     * Variablen der Klasse Survey
     */
    private int groupFK;
    
    private int status;
    
    private java.sql.Date startDate;
    
    private java.sql.Date endDate;
    
    private String selectedCity;
    
    private String movieName;
    
    

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
   	
   	/**
     * Methode um den Status einer Umfrage auszugeben
     * @return int status
     */

	public int getStatus() {
		return status;
	}
	
	/** 
     * Methode um den Status einer Umfrage zu setzen 
     * @return int status
     */

	public void setStatus(int status) {
		this.status = status;
	}
	
	/**
     * Methode um das StartDatum einer Umfrage auszugeben
     * @return Date startDate
     */
	
	public java.sql.Date getStartDate() {
		return startDate;
	}
	
	/**
     * Methode um das StartDatum einer Umfrage zu setzen
     * @return Date startDate
     */

	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
		
	}
	
	/**
     * Methode um das EndDatum einer Umfrage auszugeben
     * @return Date startDate
     */
	
	public java.sql.Date getEndDate() {
		return endDate;
	}
	
	/**
     * Methode um das EndDatum einer Umfrage zu setzen
     * @return Date startDate
     */

	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
		
		
	}
	
	/**
     * Methode um die ausgew‰hlte Stadt einer Umfrage auszugeben
     * @return selectedCity
     */
	
	public String getSelectedCity() {
		return selectedCity;
	}
	/**
     * Methode um die ausgew‰hlte Stadt einer Umfrage zu setzen
     * @return selectedCity
     */
	
	public void setSelectedCity(String selectedCity) {
		this.selectedCity = selectedCity;
	}
	
	/**
     * Methode um den Name des Films einer Umfrage auszugeben
     * @return movieName
     */
	
	public String getMovieName() {
		return movieName;
	}
	
	/**
     * Methode um den Name des Films einer Umfrage zu setzen
     * @return movieName
     */
	
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
}