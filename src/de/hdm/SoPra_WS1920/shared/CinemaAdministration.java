package de.hdm.SoPra_WS1920.shared;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

import org.apache.james.mime4j.field.datetime.DateTime;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

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
@RemoteServiceRelativePath("cinemaAdministration")
public interface CinemaAdministration extends RemoteService {

    /**
     * @param name 
     * @param cityName 
     * @param street 
     * @param streetNr 
     * @param postCode 
     * @return
     */
    Cinema createCinema(String name, String cityName, String street, String streetNr, String zipCode, int ccFK, int personFK);

    /**
     * @param name 
     * @param genre 
     * @param description 
     * @return
     */                       
    Movie createMovie(String name, String genre, String description, int personFK);

    /**
     * @param screeningDatetime 
     * @param movieFK 
     * @param cinemaFK 
     * @return
     */
    Screening createScreening(Date screeningDate, Time screeningTime, int cinemaFK, int movieFK, int personFK);

    /**
     * @param cinema 
     * @return
     */
    void deleteCinema(Cinema cinema);

    /**
     * @param movie 
     * @return
     */
    void deleteMovie(Movie movie);

    /**
     * @param screening 
     * @return
     */
    void deleteScreening(Screening screening);

    /**
     * @param cityName 
     * @return
     */
    Vector<Cinema> getCinemaByCity(String cityName);

    /**
     * @param id 
     * @return
     */
    Cinema getCinemaById(int id);

    /**
     * @param name 
     * @return
     */
    Vector<Cinema> getCinemaByName(String name);

    /**
     * @param genre 
     * @return
     */
    Vector<Movie> getMovieByGenre(String genre);

    /**
     * @param id 
     * @return
     */
    Movie getMovieById(int id);

    /**
     * @param name 
     * @return
     */
    Vector<Movie> getMoviesByName(String name);

    /**
     * @param cinemaFK 
     * @return
     */
    Vector<Screening> getScreeningByCinemaFK(int cinemaFK);

    /**
     * @param id 
     * @return
     */
    Screening getScreeningById(int id);

    /**
     * @param movieFK 
     * @return
     */
    Vector<Screening> getScreeningByMovieFK(int movieFK);

    /**
     * @param screeningDateTime 
     * @return
     */
    //Vector<Screening> getScreeningByScreeningDateTime(Timestamp screeningDateTime);

    /**
     * @param cinema 
     * @return
     */
    Cinema updateCinema(Cinema cinema);

    /**
     * @param movie 
     * @return
     */
    Movie updateMovie(Movie movie);

    /**
     * @param screening 
     * @return
     */
    Screening updateScreening(Screening screening);

    /**
     * @param firstName 
     * @param lastName 
     * @param eMail 
     * @param isAdmin 
     * @return
     */
    Person createPerson(String firstName, String lastName, String eMail);

    /**
     * @param id 
     * @return
     */
    Person getPersonById(int id);

    /**
     * @param person 
     * @return
     */
    void deletePerson(Person person);

    /**
     * @param firstName 
     * @return
     */
    Vector <Person> getPersonByFirstName(String firstName);

    /**
     * @param eMail 
     * @return
     */
    Person getPersonByEMail(String eMail);

    /**
     * @param lastName 
     * @return
     */
    Vector<Person> getPersonByLastName(String lastName);

    /**
     * @param person 
     * @return
     */
    Person updatePerson(Person person);

	void deleteVote(Vote vote);

	void deleteSurveyEntry(SurveyEntry surveyEntry);

	Vector<Vote> getVotesBySurveyEntryFK(int SurveyEntryFK);

	Vector<SurveyEntry> getSurveyEntryByScreeningFK(int ScreeningFK);

	Vector<Cinema> findCinemasByCinemaChainFK(CinemaChain cc);

	Vector<Cinema> findCinemasByPersonFK(int personFK);

	CinemaChain findCinemaChainById(int id);

	Vector<CinemaChain> findCinemaChainByPersonFK(int personFK);

	Vector<CinemaChain> findCinemaChainByName(String name);

	void deleteCinemaChain(CinemaChain cc);

	CinemaChain updateCinemaChain(CinemaChain cc);

	CinemaChain createCinemaChain(String name, int personFK);

	Vector<Ownership> getOwnershipsbyPersonFK(int personFK);

	Ownership findOwnership(int id);

	void deleteOwnership(Ownership ownership);

	Ownership createOwnership(int personFK);

	void deleteBusinessObject(BusinessObject bo);

	BusinessObject createBusinessObject();

	Vector<Screening> getScreeningByScreeningTime(Time screeningTime);

	Vector<Screening> getScreeningByScreeningDate(Date screeningDate);

}