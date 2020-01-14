package de.hdm.SoPra_WS1920.shared.bo;

import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.*;

/**
 * @author GianlucaBernert
 * Klasse Screening, welche die Attribute cinemaFK, movieFK, date und time direkt enthält
 * und die die Beziehung zwischen Person und einem Screening von der Klasse Ownership erbt, 
 * diese wiederum erbt SerialVersionUID, die ID und den Erstellzeitpunkt von BusinessObject.
 */
public class Screening extends Ownership {

    /**
     * Variablen der Klasse Screening
     */
    private int cinemaFK;
    private int movieFK;
    private Date screeningDate;
    private Time screeningTime;

    /**
     * Konstruktor der Klasse Screening, welcher beim Aufruf dieser eine Instanz seiner selbst erzeugt
     */
    public Screening() {
    }
  
    /**
     * Methode um den cinemaFK einer Vorführung auszugeben
     * @return int cinemaFK
     */
    public int getCinemaFK() {
        return cinemaFK;
    }

    /**
     * Methode um den cinemaFK einer Vorführung zu setzen
     * @param int cinemaFK 
     */
    public void setCinemaFK(int cinemaFK) {
        this.cinemaFK = cinemaFK;
    }

    /**
     * Methode um den movieFK einer Vorführung auszugeben
     * @return int movieFK
     */
    public int getMovieFK() {
        return movieFK;
    }

    /**
     * Methode um den movieFK einer Vorführung zu setzen
     * @param int movieFK 
     */
    public void setMovieFK(int movieFK) {
       this.movieFK = movieFK;
    }

    /**
   	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
   	 * @return String ScreeningID
   	 */
   	public String toString() {
   		return "ScreeningID #SC" + this.getId();
   	}
   	/**
   	 * Methode um den Zeitpunkt einer Vorf�hrung auszugeben
   	 * @return Date screeningDate
   	 */

	public Date getScreeningDate() {
		return screeningDate;
	}

   	/**
   	 * Methode um den Zeitpunkt einer Vorf�hrung zu setzen
   	 * @param Date screeningDate
   	 */	
	public void setScreeningDate(Date screeningDate) { 
		this.screeningDate = screeningDate;
	}
	
	public Time getScreeningTime() {
		return screeningTime;
	}
	
	public void setScreeningTime(Time screeningTime) {
		this.screeningTime = screeningTime;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Screening) {
			Screening s = (Screening) obj;
			if((this.getId()== s.getId())){				
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