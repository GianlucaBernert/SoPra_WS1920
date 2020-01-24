package de.hdm.SoPra_WS1920.shared;


import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

import org.apache.james.mime4j.field.datetime.DateTime;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.SoPra_WS1920.shared.bo.BusinessObject;
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
    void createCinema(String name, String cityName, String street, String streetNr, String zipCode, int ccFK, int personFK, AsyncCallback<Cinema> callback);

    /**
     * @param name 
     * @param genre 
     * @param description 
     * @param callback 
     * @return
     */
    void createMovie(String name, String genre, String description,int personFK, AsyncCallback<Movie> callback);

    /**
     * @param screeningDatetime 
     * @param movieFK 
     * @param cinemaFK 
     * @param callback 
     * @return
     */
    void createScreening(Date screeningDate, Time screeningTime, int cinemaFK, int movieFK, int PersonFK, AsyncCallback<Screening> callback);

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
     * 
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
    //void getScreeningByScreeningDateTime(Timestamp screeningDateTime, AsyncCallback<Vector<Screening>> callback);

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
    void createPerson(String firstName, String lastName, String eMail, AsyncCallback<Person> callback);

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
    
    void getCinemasByCinemaChainFK(CinemaChain cc, AsyncCallback<Vector<Cinema>> callback);
    
    void getCinemasByPersonFK(int personFK, AsyncCallback<Vector<Cinema>> callback);
    
    void getCinemaChainById(int id, AsyncCallback<CinemaChain> callback);
    
    void getCinemaChainByPersonFK(int personFK, AsyncCallback<Vector<CinemaChain>> callback);
    
    void getCinemaChainByName(String name, AsyncCallback<Vector<CinemaChain>> callback);
    
    void deleteCinemaChain(CinemaChain cc, AsyncCallback<Void> callback);
    
    void updateCinemaChain(CinemaChain cc, AsyncCallback<CinemaChain> callback);
    
    void createCinemaChain(String name, int personFK, AsyncCallback<CinemaChain> callback);
    
    void getOwnershipsbyPersonFK(int personFK, AsyncCallback<Vector<Ownership>> callback);
    
    void getOwnership(int id, AsyncCallback<Ownership> callback);
    
    void deleteOwnership(Ownership ownership, AsyncCallback<Void> callback);
    
    void createOwnership(int personFK, AsyncCallback<Ownership> callback);
    
    void deleteBusinessObject(BusinessObject bo, AsyncCallback<Void> callback);
    
    void createBusinessObject(AsyncCallback<BusinessObject> callback);
    
    void getScreeningByScreeningDateTime(Date screeningDateTime, Time screeningTime, AsyncCallback<Vector<Screening>> callback);
    
    void getScreeningByScreeningDate(Date screeningDate, AsyncCallback<Vector<Screening>> callback);
    
    void getScreeningsByPersonFK(int personFK, AsyncCallback<Vector<Screening>> callback);

	void getAllMovies(AsyncCallback<Vector<Movie>> callback);
	
	void getScreeningByScreeningTime(Time screeningTime, AsyncCallback<Vector<Screening>> callback);

	void searchMovie(String text, AsyncCallback<Vector<Movie>> callback);

	void searchCinema(int personFk, String text, AsyncCallback<Vector<Cinema>> callback);

	void searchCinemaChain(int personFk, String text, AsyncCallback<Vector<CinemaChain>> callback);

	void searchScreening(int personFk, String text, AsyncCallback<Vector<Screening>> callback);
	
	void getScreeningsforSurveyCreation(Movie movie, String city, Date startDate, Date endDate, AsyncCallback<Vector<Screening>> callback);

	void getCinemaByScreeningFK(int screeningFK, AsyncCallback<Cinema> callback);
    
}