package de.hdm.SoPra_WS1920.server;

import java.util.*;

/**
 * @author MatthiasKling
 */
public class CinemaAdministrationImpl extends RemoteServiceServlet implements CinemaAdministration {

    /**
     * Default constructor
     */
    public CinemaAdministrationImpl() {
    }

    /**
     * 
     */
    private CinemaMapper cMapper;

    /**
     * 
     */
    private MovieMapper mMapper;

    /**
     * 
     */
    private ScreeningMapper sMapper;

    /**
     * 
     */
    public PersonMapper pMapper;

    /**
     * @param name 
     * @param cityName 
     * @param street 
     * @param postCode 
     * @return
     */
    public Cinema createCinema(String name, String cityName, String street, String postCode) {
        // TODO implement here
        return null;
    }

    /**
     * @param name 
     * @param genre 
     * @param description 
     * @return
     */
    public Movie createMovie(String name, String genre, String description) {
        // TODO implement here
        return null;
    }

    /**
     * @param screeningDateTime 
     * @param cinemaFK 
     * @param movieFK 
     * @return
     */
    public Screening createScreening(DateTime screeningDateTime, int cinemaFK, int movieFK) {
        // TODO implement here
        return null;
    }

    /**
     * @param cinema 
     * @return
     */
    public Void deleteCinema(Cinema cinema) {
        // TODO implement here
        return null;
    }

    /**
     * @param movie 
     * @return
     */
    public Void deleteMovie(Movie movie) {
        // TODO implement here
        return null;
    }

    /**
     * @param screening 
     * @return
     */
    public Void deleteScreening(Screening screening) {
        // TODO implement here
        return null;
    }

    /**
     * @param id 
     * @return
     */
    public Movie getCinemaById(int id) {
        // TODO implement here
        return null;
    }

    /**
     * @param name 
     * @return
     */
    public Vector<Cinema> getCinemasByName(String name) {
        // TODO implement here
        return null;
    }

    /**
     * @param cityName 
     * @return
     */
    public Vector<Cinema> getCinemasByCityName(String cityName) {
        // TODO implement here
        return null;
    }

    /**
     * @param id 
     * @return
     */
    public Movie getMovieById(int id) {
        // TODO implement here
        return null;
    }

    /**
     * @param name 
     * @return
     */
    public Vector<Movie> getMoviesByName(String name) {
        // TODO implement here
        return null;
    }

    /**
     * @param genre 
     * @return
     */
    public Vector<Movie> getMoviesByGenre(String genre) {
        // TODO implement here
        return null;
    }

    /**
     * @param id 
     * @return
     */
    public Screening getScreeningById(int id) {
        // TODO implement here
        return null;
    }

    /**
     * @param cinemaFK 
     * @return
     */
    public Vector<Screening> getScreeningByCinemaFK(int cinemaFK) {
        // TODO implement here
        return null;
    }

    /**
     * @param movieFK 
     * @return
     */
    public Vector<Screening> getScreeningByMovieFK(int movieFK) {
        // TODO implement here
        return null;
    }

    /**
     * @param screeningDateTime 
     * @return
     */
    public Vector<Screening> getScreeningsByScreeningDateTime(DateTime screeningDateTime) {
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
     * @param movie 
     * @return
     */
    public Movie updateMovie(Movie movie) {
        // TODO implement here
        return null;
    }

    /**
     * @param screening
     */
    public void updateScreening(Screening screening) {
        // TODO implement here
    }

    /**
     * @param firstName 
     * @param lastName 
     * @param eMail 
     * @param isAdmin 
     * @return
     */
    public Person createPerson(String firstName, String lastName, String eMail, boolean isAdmin) {
        // TODO implement here
        return null;
    }

    /**
     * @param id 
     * @return
     */
    public Person getPersonById(int id) {
        // TODO implement here
        return null;
    }

    /**
     * @param firstName 
     * @return
     */
    public Person getPersonByFirstName(String firstName) {
        // TODO implement here
        return null;
    }

    /**
     * @param lastName 
     * @return
     */
    public Person getPersonByLastName(String lastName) {
        // TODO implement here
        return null;
    }

    /**
     * @param eMail 
     * @return
     */
    public Person getPersonByeMail(String eMail) {
        // TODO implement here
        return null;
    }

    /**
     * @param person 
     * @return
     */
    public Void deletePerson(Person person) {
        // TODO implement here
        return null;
    }

    /**
     * @param person 
     * @return
     */
    public Person updatePerson(Person person) {
        // TODO implement here
        return null;
    }

    /**
     * @param name 
     * @param cityName 
     * @param street 
     * @param streetNr 
     * @param postCode 
     * @return
     */
    public Cinema createCinema(String name, String cityName, String street, String streetNr, String postCode) {
        // TODO implement here
        return null;
    }

    /**
     * @param name 
     * @param genre 
     * @param description 
     * @return
     */
    public Movie createMovie(String name, String genre, String description) {
        // TODO implement here
        return null;
    }

    /**
     * @param screeningDatetime 
     * @param movieFK 
     * @param cinemaFK 
     * @return
     */
    public Screening createScreening(DateTime screeningDatetime, int movieFK, int cinemaFK) {
        // TODO implement here
        return null;
    }

    /**
     * @param cinema 
     * @return
     */
    public Void deleteCinema(Cinema cinema) {
        // TODO implement here
        return null;
    }

    /**
     * @param movie 
     * @return
     */
    public Void deleteMovie(Movie movie) {
        // TODO implement here
        return null;
    }

    /**
     * @param screening 
     * @return
     */
    public Void deleteScreening(Screening screening) {
        // TODO implement here
        return null;
    }

    /**
     * @param cityName 
     * @return
     */
    public Vector<Cinema> getCinemaByCity(String cityName) {
        // TODO implement here
        return null;
    }

    /**
     * @param id 
     * @return
     */
    public Cinema getCinemaById(int id) {
        // TODO implement here
        return null;
    }

    /**
     * @param name 
     * @return
     */
    public Vector<Cinema> getCinemaByName(String name) {
        // TODO implement here
        return null;
    }

    /**
     * @param genre 
     * @return
     */
    public Vector<Movie> getMovieByGenre(String genre) {
        // TODO implement here
        return null;
    }

    /**
     * @param id 
     * @return
     */
    public Movie getMovieById(int id) {
        // TODO implement here
        return null;
    }

    /**
     * @param name 
     * @return
     */
    public Vector<Movie> getMoviesByName(String name) {
        // TODO implement here
        return null;
    }

    /**
     * @param cinemaFK 
     * @return
     */
    public Vector<Screening> getScreeningByCinemaFK(int cinemaFK) {
        // TODO implement here
        return null;
    }

    /**
     * @param id 
     * @return
     */
    public Screening getScreeningById(int id) {
        // TODO implement here
        return null;
    }

    /**
     * @param movieFK 
     * @return
     */
    public Vector<Screening> getScreeningByMovieFK(int movieFK) {
        // TODO implement here
        return null;
    }

    /**
     * @param screeningDateTime 
     * @return
     */
    public Vector<Screening> getScreeningByScreeningDateTime(DateTime screeningDateTime) {
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
     * @param movie 
     * @return
     */
    public Movie updateMovie(Movie movie) {
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
     * @param firstName 
     * @param lastName 
     * @param eMail 
     * @param isAdmin 
     * @return
     */
    public Person createPerson(String firstName, String lastName, String eMail, boolean isAdmin) {
        // TODO implement here
        return null;
    }

    /**
     * @param id 
     * @return
     */
    public Person getPersonById(int id) {
        // TODO implement here
        return null;
    }

    /**
     * @param person 
     * @return
     */
    public Void deletePerson(Person person) {
        // TODO implement here
        return null;
    }

    /**
     * @param firstName 
     * @return
     */
    public Person getPersonByFirstName(String firstName) {
        // TODO implement here
        return null;
    }

    /**
     * @param eMail 
     * @return
     */
    public Person getPersonByEMail(String eMail) {
        // TODO implement here
        return null;
    }

    /**
     * @param lastName 
     * @return
     */
    public Person getPersonByLastName(String lastName) {
        // TODO implement here
        return null;
    }

    /**
     * @param person 
     * @return
     */
    public Person updatePerson(Person person) {
        // TODO implement here
        return null;
    }

}