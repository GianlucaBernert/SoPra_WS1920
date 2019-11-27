package de.hdm.SoPra_WS1920.server.db;

import java.util.Vector;

import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Person;
import de.hdm.SoPra_WS1920.shared.bo.Screening;

/**
 * 
 */
public class ScreeningMapper {

    /**
     * Default constructor
     */
    public ScreeningMapper() {
    }

    /**
     * 
     */
    public ScreeningMapper screeningMapper;

    /**
     * 
     */
    protected void ScreeningMapper() {
        // TODO implement here
    }

    /**
     * @return
     */
    public static ScreeningMapper screeningMapper() {
        // TODO implement here
        return null;
    }

    /**
     * @param screening 
     * @return
     */
    public Screening insertScreening(Screening screening) {
        // TODO implement here
        return null;
    }

    /**
     * @param screening 
     * @return
     */
    public Screening updateScreening(Screening screening) {
        // TODO implement here
        return null;
    }

    /**
     * @param screening 
     * @return
     */
    public void deleteScreening(Screening screening) {
        // TODO implement here
        return null;
    }

    /**
     * @param screeningID 
     * @return
     */
    public Screening findScreeningByID(int screeningID) {
        // TODO implement here
        return null;
    }

    /**
     * @param DateTime 
     * @return
     */
    public Vector<Screening> findScreeningByScreeningDateTime(String DateTime) {
        // TODO implement here
        return null;
    }

    /**
     * @param DateTime 
     * @return
     */
    public void deleteScreeningDateTime(String DateTime) {
        // TODO implement here
        return null;
    }

    /**
     * @param movie 
     * @return
     */
    public Vector<Screening> findScreeningByMovieFK(Movie movie) {
        // TODO implement here
        return null;
    }

    /**
     * @param movie 
     * @return
     */
    public void deleteScreeningByMovieFK(Movie movie) {
        // TODO implement here
        return null;
    }

    /**
     * @param cinema 
     * @return
     */
    public vector<Screening> findScreeningByCinemaFK(Cinema cinema) {
        // TODO implement here
        return null;
    }

    /**
     * @param cinema 
     * @return
     */
    public void deleteScreeningByCinemaFK(Cinema cinema) {
        // TODO implement here
        return null;
    }

    /**
     * @param person 
     * @return
     */
    public Vector<Screening> findScreeningByPersonFK(int id) {
        // TODO implement here
        return null;
    }

    /**
     * @param person 
     * @return
     */
    public void deleteScreeningByPersonFK(Person person) {
        // TODO implement here
        return null;
    }

}