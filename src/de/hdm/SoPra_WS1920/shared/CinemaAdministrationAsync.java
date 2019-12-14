package de.hdm.SoPra_WS1920.shared;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

import org.apache.james.mime4j.field.datetime.DateTime;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.CinemaChain;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Ownership;
import de.hdm.SoPra_WS1920.shared.bo.Person;
import de.hdm.SoPra_WS1920.shared.bo.Screening;
import de.hdm.SoPra_WS1920.shared.bo.SurveyEntry;
import de.hdm.SoPra_WS1920.shared.bo.Vote;


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
    void createCinema(String name, String cityName, String street, String streetNr, String postCode, int personFK, Timestamp creationTimestamp, AsyncCallback<Cinema> callback);

    /**
     * @param name 
     * @param genre 
     * @param description 
     * @param callback 
     * @return
     */
    void createMovie(String name, String genre, String description,int personFK, Timestamp creationTimestamp, AsyncCallback<Movie> callback);

    /**
     * @param screeningDatetime 
     * @param movieFK 
     * @param cinemaFK 
     * @param callback 
     * @return
     */
    void createScreening(Timestamp screeningDateTime, int cinemaFK, int movieFK, int PersonFK, Timestamp creationTimestamp, AsyncCallback<Screening> callback);

    /**
     * @param cinema 
     * @param callback 
     * @return
     */
    void deleteCinema(Cinema cinema, AsyncCallback<Void> callback);

    /**
     * @param movie 
     * @param callback 
     * @return
     */
    void deleteMovie(Movie movie, AsyncCallback<Void> callback);

    /**
     * @param screening 
     * @param callback 
     * @return
     */
    void deleteScreening(Screening screening, AsyncCallback<Void> callback);

    /**
     * @param cityName 
     * @param callback 
     * @return
     */
    void getCinemaByCity(String cityName, AsyncCallback<Vector<Cinema>> callback);

    /**
     * @param id 
     * @param callback 
     * @return
     */
    void getCinemaById(int id, AsyncCallback<Cinema> callback);

    /**
     * @param name 
     * @param callback 
     * @return
     */
    void getCinemaByName(String name, AsyncCallback<Vector<Cinema>> callback);

    /**
     * @param genre 
     * @param callback 
     * @return
     */
    void getMovieByGenre(String genre, AsyncCallback<Vector<Movie>> callback);

    /**
     * @param id 
     * @param callback 
     * @return
     */
    void getMovieById(int id, AsyncCallback<Movie> callback);

    /**
     * @param name 
     * @param callback 
     * @return
     */
    void getMoviesByName(String name, AsyncCallback<Vector<Movie>> callback);

    /**
     * @param cinemaFK 
     * @param callback 
     * @return
     */
    void getScreeningByCinemaFK(int cinemaFK, AsyncCallback<Vector<Screening>> callback);

    /**
     * @param id 
     * @param callback 
     * @return
     */
    void getScreeningById(int id, AsyncCallback<Screening> callback);

    /**
     * @param movieFK 
     * @param callback 
     * @return
     */
    void getScreeningByMovieFK(int movieFK, AsyncCallback<Vector<Screening>> callback);

    /**
     * @param screeningDateTime 
     * @param callback 
     * @return
     */
    void getScreeningByScreeningDateTime(Timestamp screeningDateTime, AsyncCallback<Vector<Screening>> callback);

    /**
     * @param cinema 
     * @param callback 
     * @return
     */
    void updateCinema(Cinema cinema, AsyncCallback<Cinema> callback);

    /**
     * @param movie 
     * @param callback 
     * @return
     */
    void updateMovie(Movie movie, AsyncCallback<Movie> callback);

    /**
     * @param screening 
     * @param callback 
     * @return
     */
    void updateScreening(Screening screening, AsyncCallback<Screening> callback);

    /**
     * @param firstName 
     * @param lastName 
     * @param eMail 
     * @param isAdmin 
     * @param callback 
     * @return
     */
    void createPerson(String firstName, String lastName, String eMail, Timestamp creationTimestamp, AsyncCallback<Person> callback);

    /**
     * @param id 
     * @param callback 
     * @return
     */
    void getPersonById(int id, AsyncCallback<Person> callback);

    /**
     * @param person 
     * @param callback 
     * @return
     */
    void deletePerson(Person person, AsyncCallback<Void> callback);

    /**
     * @param firstName 
     * @param callback 
     * @return
     */
    void getPersonByFirstName(String firstName, AsyncCallback<Vector<Person>> callback);

    /**
     * @param lastName 
     * @param callback 
     * @return
     */
    void getPersonByLastName(String lastName, AsyncCallback<Vector<Person>> callback);

    /**
     * @param eMail 
     * @param callback 
     * @return
     */
    void getPersonByEMail(String eMail, AsyncCallback<Person> callback);

    /**
     * @param person 
     * @param callback 
     * @return
     */
    void updatePerson(Person person, AsyncCallback<Person> callback);

    void deleteVote(Vote vote, AsyncCallback<Void> callback);
    
    void deleteSurveyEntry(SurveyEntry surveyEntry, AsyncCallback<Void> callback );
    
    void getVotesBySurveyEntryFK(int SurveyEntryFK, AsyncCallback<Vector<Vote>> callback);
    
    void getSurveyEntryByScreeningFK(int ScreeningFK, AsyncCallback<Vector<SurveyEntry>> callback);
    
    void findCinemasByCinemaChainID(CinemaChain cc, AsyncCallback<Vector<Cinema>> callback);
    
    void findCinemasByPersonFK(int personFK, AsyncCallback<Vector<Cinema>> callback);
    
    void findCinemaChainById(int id, AsyncCallback<CinemaChain> callback);
    
    void findCinemaChainByPersonFK(int personFK, AsyncCallback<Vector<CinemaChain>> callback);
    
    void findCinemaChainByName(String name, AsyncCallback<CinemaChain> callback);
    
    void deleteCinemaChain(CinemaChain cc, AsyncCallback<Void> callback);
    
    void updateCinemaChain(CinemaChain cc, AsyncCallback<CinemaChain> callback);
    
    void createCinemaChain(Cinema c, String name, Timestamp creationTimestamp, int personFK, AsyncCallback<CinemaChain> callback);
    
    void getOwnershipsbyPersonFK(int personFK, AsyncCallback<Vector<Ownership>> callback);
    
    void findOwnership(int id, AsyncCallback<Ownership> callback);
    
    void deleteOwnership(Ownership ownership, AsyncCallback<Void> callback);
    
    void createOwnership(int personFK, Timestamp creationTimestamp, int id, AsyncCallback<Ownership> callback);
    
}