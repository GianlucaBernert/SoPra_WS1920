package de.hdm.SoPra_WS1920.shared;


import java.util.*;

import org.apache.james.mime4j.field.datetime.DateTime;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Person;
import de.hdm.SoPra_WS1920.shared.bo.Screening;


/**
 * 
 */
public interface CinemaAdministrationAsync {

    /**
     * @param name 
     * @param cityName 
     * @param street 
     * @param streetNr 
     * @param postCode 
     * @param callback 
     * @return
     */
    public Void createCinema(String name, String cityName, String street, String streetNr, String postCode, AsyncCallback<Cinema> callback);

    /**
     * @param name 
     * @param genre 
     * @param description 
     * @param callback 
     * @return
     */
    public Void createMovie(String name, String genre, String description, AsyncCallback<Movie> callback);

    /**
     * @param screeningDatetime 
     * @param movieFK 
     * @param cinemaFK 
     * @param callback 
     * @return
     */
    public Void createScreening(DateTime screeningDatetime, int movieFK, int cinemaFK, AsyncCallback<Screening> callback);

    /**
     * @param cinema 
     * @param callback 
     * @return
     */
    public Void deleteCinema(Cinema cinema, AsyncCallback<Void> callback);

    /**
     * @param movie 
     * @param callback 
     * @return
     */
    public Void deleteMovie(Movie movie, AsyncCallback<Void> callback);

    /**
     * @param screening 
     * @param callback 
     * @return
     */
    public Void deleteScreening(Screening screening, AsyncCallback<Void> callback);

    /**
     * @param cityName 
     * @param callback 
     * @return
     */
    public Void getCinemaByCity(String cityName, AsyncCallback<Vector<Cinema>> callback);

    /**
     * @param id 
     * @param callback 
     * @return
     */
    public Void getCinemaById(int id, AsyncCallback<Cinema> callback);

    /**
     * @param name 
     * @param callback 
     * @return
     */
    public Void getCinemaByName(String name, AsyncCallback<Vector<Cinema>> callback);

    /**
     * @param genre 
     * @param callback 
     * @return
     */
    public Void getMovieByGenre(String genre, AsyncCallback<Vector<Movie>> callback);

    /**
     * @param id 
     * @param callback 
     * @return
     */
    public Void getMovieById(int id, AsyncCallback<Movie> callback);

    /**
     * @param name 
     * @param callback 
     * @return
     */
    public Void getMovieByName(String name, AsyncCallback<Vector<Movie>> callback);

    /**
     * @param cinemaFK 
     * @param callback 
     * @return
     */
    public Void getScreeningByCinemaFK(int cinemaFK, AsyncCallback<Vector<Screening>> callback);

    /**
     * @param id 
     * @param callback 
     * @return
     */
    public Void getScreeningById(int id, AsyncCallback<Screening> callback);

    /**
     * @param movieFK 
     * @param callback 
     * @return
     */
    public Void getScreeningByMovieFK(int movieFK, AsyncCallback<Vector<Screening>> callback);

    /**
     * @param screeningDateTime 
     * @param callback 
     * @return
     */
    public Void getScreeningByScreeningDateTime(DateTime screeningDateTime, AsyncCallback<Vector<Screening>> callback);

    /**
     * @param cinema 
     * @param callback 
     * @return
     */
    public Void updateCinema(Cinema cinema, AsyncCallback<Cinema> callback);

    /**
     * @param movie 
     * @param callback 
     * @return
     */
    public Void updateMovie(Movie movie, AsyncCallback<Movie> callback);

    /**
     * @param screening 
     * @param callback 
     * @return
     */
    public Void updateScreening(Screening screening, AsyncCallback<Screening> callback);

    /**
     * @param firstName 
     * @param lastName 
     * @param eMail 
     * @param isAdmin 
     * @param callback 
     * @return
     */
    public Void createPerson(String firstName, String lastName, String eMail, boolean isAdmin, AsyncCallback<Person> callback);

    /**
     * @param id 
     * @param callback 
     * @return
     */
    public Void getPersonById(int id, AsyncCallback<Person> callback);

    /**
     * @param person 
     * @param callback 
     * @return
     */
    public Void deletePerson(Person person, AsyncCallback<Void> callback);

    /**
     * @param firstName 
     * @param callback 
     * @return
     */
    public Void getPersonByFirstName(String firstName, AsyncCallback<Vector<Person>> callback);

    /**
     * @param lastName 
     * @param callback 
     * @return
     */
    public Void getPersonByLastName(String lastName, AsyncCallback<Vector<Person>> callback);

    /**
     * @param eMail 
     * @param callback 
     * @return
     */
    public Void getPersonByEMail(String eMail, AsyncCallback<Vector<Person>> callback);

    /**
     * @param person 
     * @param callback 
     * @return
     */
    public Void updatePerson(Person person, AsyncCallback<Person> callback);

}