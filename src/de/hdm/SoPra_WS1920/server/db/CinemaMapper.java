package de.hdm.SoPra_WS1920.server.db;

import java.util.Vector;

import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.Person;

/**
 * 
 */
public class CinemaMapper {

    /**
     * Default constructor
     */
    public CinemaMapper() {
    }

    /**
     * 
     */
    public CinemaMapper cinemaMapper;

    /**
     * 
     */
    protected void CinemaMapper() {
        // TODO implement here
    }

    /**
     * @return
     */
    public static CinemaMapper cineMapper() {
        // TODO implement here
        return null;
    }

    /**
     * @param cinema 
     * @return
     */
    public Cinema insertCinema(Cinema cinema) {
        // TODO implement here
        return null;
    }

    /**
     * @param cinema 
     * @return
     */
    public Cinema updateCinema(Cinema cinema) {
        // TODO implement here
        return null;
    }

    /**
     * @param cinema 
     * @return
     */
    public void deleteCinema(Cinema cinema) {
        // TODO implement here
        return null;
    }

    /**
     * @param cinemaID 
     * @return
     */
    public Cinema findCinemaByID(int cinemaID) {
        // TODO implement here
        return null;
    }

    /**
     * @param name 
     * @return
     */
    public Vector<Cinema> findCinemaByName(String name) {
        // TODO implement here
        return null;
    }

    /**
     * @param city 
     * @return
     */
    public Vector<Cinema> findCinemaByCity(String city) {
        // TODO implement here
        return null;
    }

    /**
     * @param postcode 
     * @return
     */
    public Vector<Cinema> findByPostcode(int postcode) {
        // TODO implement here
        return null;
    }

    /**
     * @param name 
     * @return
     */
    public void deleteCinemaByName(String name) {
        // TODO implement here
        return null;
    }

    /**
     * @param person 
     * @return
     */
    public void deleteByPersonFK(Person person) {
        // TODO implement here
        return null;
    }

    /**
     * @param id 
     * @return
     */
    public Vector<Cinema> findCinemaByPersonFK(int id) {
        // TODO implement here
        return null;
    }

}