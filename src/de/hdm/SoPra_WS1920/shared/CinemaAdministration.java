package de.hdm.SoPra_WS1920.shared;


import java.util.*;

/**
 * 
 */
public interface CinemaAdministration extends RemoteService {

    /**
     * @param name 
     * @param cityName 
     * @param street 
     * @param streetNr 
     * @param postCode 
     * @return
     */
    public Cinema createCinema(String name, String cityName, String street, String streetNr, String postCode);

    /**
     * @param name 
     * @param genre 
     * @param description 
     * @return
     */
    public Movie createMovie(String name, String genre, String description);

    /**
     * @param screeningDatetime 
     * @param movieFK 
     * @param cinemaFK 
     * @return
     */
    public Screening createScreening(DateTime screeningDatetime, int movieFK, int cinemaFK);

    /**
     * @param cinema 
     * @return
     */
    public Void deleteCinema(Cinema cinema);

    /**
     * @param movie 
     * @return
     */
    public Void deleteMovie(Movie movie);

    /**
     * @param screening 
     * @return
     */
    public Void deleteScreening(Screening screening);

    /**
     * @param cityName 
     * @return
     */
    public Vector<Cinema> getCinemaByCity(String cityName);

    /**
     * @param id 
     * @return
     */
    public Cinema getCinemaById(int id);

    /**
     * @param name 
     * @return
     */
    public Vector<Cinema> getCinemaByName(String name);

    /**
     * @param genre 
     * @return
     */
    public Vector<Movie> getMovieByGenre(String genre);

    /**
     * @param id 
     * @return
     */
    public Movie getMovieById(int id);

    /**
     * @param name 
     * @return
     */
    public Vector<Movie> getMoviesByName(String name);

    /**
     * @param cinemaFK 
     * @return
     */
    public Vector<Screening> getScreeningByCinemaFK(int cinemaFK);

    /**
     * @param id 
     * @return
     */
    public Screening getScreeningById(int id);

    /**
     * @param movieFK 
     * @return
     */
    public Vector<Screening> getScreeningByMovieFK(int movieFK);

    /**
     * @param screeningDateTime 
     * @return
     */
    public Vector<Screening> getScreeningByScreeningDateTime(DateTime screeningDateTime);

    /**
     * @param cinema 
     * @return
     */
    public Cinema updateCinema(Cinema cinema);

    /**
     * @param movie 
     * @return
     */
    public Movie updateMovie(Movie movie);

    /**
     * @param screening 
     * @return
     */
    public Screening updateScreening(Screening screening);

    /**
     * @param firstName 
     * @param lastName 
     * @param eMail 
     * @param isAdmin 
     * @return
     */
    public Person createPerson(String firstName, String lastName, String eMail, boolean isAdmin);

    /**
     * @param id 
     * @return
     */
    public Person getPersonById(int id);

    /**
     * @param person 
     * @return
     */
    public Void deletePerson(Person person);

    /**
     * @param firstName 
     * @return
     */
    public Person getPersonByFirstName(String firstName);

    /**
     * @param eMail 
     * @return
     */
    public Person getPersonByEMail(String eMail);

    /**
     * @param lastName 
     * @return
     */
    public Person getPersonByLastName(String lastName);

    /**
     * @param person 
     * @return
     */
    public Person updatePerson(Person person);

}