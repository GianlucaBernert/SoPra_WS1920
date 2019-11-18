package de.hdm.SP1920.shared.bo;

import java.util.*;

/**
 * 
 */
public class Screening extends Ownership {

    /**
     * Default constructor
     */
    public Screening() {
    }

    /**
     * 
     */
    private int cinemaFK;

    /**
     * 
     */
    private int movieFK;

    /**
     * 
     */
    private Date date;

    /**
     * 
     */
    private Time time;

    /**
     * 
     */
    public Movie Content;

    /**
     * 
     */
    public Set<SurveyEntry> Voting Option;

    /**
     * 
     */
    public Cinema Screening location;

    /**
     * 
     */
    public Person Promoter;






    /**
     * @return
     */
    public int getCinemaFK() {
        // TODO implement here
        return 0;
    }

    /**
     * @param cinemaFK 
     * @return
     */
    public void setCinemaFK(int cinemaFK) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public int getMovieFK() {
        // TODO implement here
        return 0;
    }

    /**
     * @param movieFK 
     * @return
     */
    public void setMovieFK(int movieFK) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Date getDate() {
        // TODO implement here
        return null;
    }

    /**
     * @param date 
     * @return
     */
    public void setDate(Date date) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Time getTime() {
        // TODO implement here
        return null;
    }

    /**
     * @param time 
     * @return
     */
    public void setTime(Time time) {
        // TODO implement here
        return null;
    }

}