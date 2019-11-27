package de.hdm.SoPra_WS1920.server.db;

import java.util.Vector;

import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Person;

/**
 * 
 */
public class MovieMapper {

    /**
     * Default constructor
     */
    public MovieMapper() {
    }

    /**
     * 
     */
    public MovieMapper movieMapper;

    /**
     * 
     */
    protected void MovieMapper() {
        // TODO implement here
    }

    /**
     * @return
     */
    public static MovieMapper moviemapper() {
        // TODO implement here
        return null;
    }

    /**
     * @param movie 
     * @return
     */
    public Movie insertMovie(Movie movie) {
        // TODO implement here
        return null;
    }

    /**
     * @param movie 
     * @return
     */
    public Movie updateMovie(Movie movie) {
        // TODO implement here
        return null;
    }

    /**
     * @param movie 
     * @return
     */
    public void deleteMovie(Movie movie) {
        // TODO implement here
        return null;
    }

    /**
     * @param movieID 
     * @return
     */
    public Movie findMovieByID(int movieID) {
        // TODO implement here
        return null;
    }

    /**
     * @param name 
     * @return
     */
    public Vector<Movie> findMovieByName(String name) {
        // TODO implement here
        return null;
    }

    /**
     * @param name 
     * @return
     */
    public void deleteMovieByName(String name) {
        // TODO implement here
        return null;
    }

    /**
     * @param genre 
     * @return
     */
    public Vector<Movie> findMovieByGenre(String genre) {
        // TODO implement here
        return null;
    }

    /**
     * @param id 
     * @return
     */
    public Vector<Movie> findMovieByPersonFK(int id) {
        // TODO implement here
        return null;
    }

    /**
     * @param person 
     * @return
     */
    public void deleteMovieByPersonFK(Person person) {
        // TODO implement here
        return null;
    }

}