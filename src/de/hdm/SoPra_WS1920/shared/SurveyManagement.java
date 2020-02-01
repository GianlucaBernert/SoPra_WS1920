package de.hdm.SoPra_WS1920.shared;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import org.apache.james.mime4j.field.datetime.DateTime;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

//import de.hdm.SoPra_WS1920.server.IllegalArgumentException;
import de.hdm.SoPra_WS1920.shared.bo.BusinessObject;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
import de.hdm.SoPra_WS1920.shared.bo.CinemaChain;
import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Membership;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Ownership;
import de.hdm.SoPra_WS1920.shared.bo.Person;
import de.hdm.SoPra_WS1920.shared.bo.Screening;
import de.hdm.SoPra_WS1920.shared.bo.Survey;
import de.hdm.SoPra_WS1920.shared.bo.SurveyEntry;
import de.hdm.SoPra_WS1920.shared.bo.Vote;


/**
 * @author GianlucaBernert
 */

@RemoteServiceRelativePath("surveyManagement")

public interface SurveyManagement extends RemoteService {
	

    /**
     * Methode um ein BusinessObject zu erstellen
     * @return BusinessObject bo
     */
	public BusinessObject createBusinessObject();
	
    /**
     * Methode um ein BusinessObject zu löschen
     * @param BusinessObject bo
     */
    public void deleteBusinessObject(BusinessObject bo);
    
    /*
     * Methode um eine Ownership zu erstellen
     * @param int pFK
     * @return Ownership os
     */
    public Ownership createOwnership(int pFK);

    /*
     * Methode um eine Ownership zu löschen
     * @param Ownership os
     */
    public void deleteOwnership(Ownership os);
    
    /**
     * Methode um eine Person zu erstellen
     * @param String firstName 
     * @param String lastName 
     * @param String email 
     * @return Person p
     */
    public Person createPerson(String firstName, String lastName, String eMail);
    
    /**
     * Methode um eine Person zu bearbeiten
     * @param Person p 
     */
    public void editPerson(Person p);
    
    /**
     * Methode um eine Person zu löschen
     * @param Person p
     */
    public void deletePerson(Person person);
    
    /**
     * Methode um eine Person anhand der ID zu finden
     * @param int id 
     * @return Person
     */
    public Person getPersonById(int id);
    
    /**
     * Methode um eine Person anhand des Vornamens zu finden
     * @param String firstName
     * @return Vector<Person>
     */
    public Vector<Person> getPersonByFirstname(String firstName);

    /**
     * Methode um eine Person anhand des Nachnamens zu finden
     * @param String lastName
     * @return Vector<Person>
     */
    public Vector<Person> getPersonByLastname(String lastName);

    /**
     * Methode um eine Person anhand der E-Mail adresse zu finden
     * @param String eMail
     * @return Person
     */
    public Person getPersonByEmail(String eMail);

    /**
     * Methode um eine Gruppe zu erstellen
     * @param String name
     * @param int pFK
     * @return Group g
     */
    public Group createGroup(String name, int pFK);
    
    /**
     * Methode um eine Mitgliedschaft zu erstellen 
     * @param Group g
     * @param Person p
     */
    public Membership createMembership(Group g, Person p);
    
    /**
     * Methode um eine Mitgliedschaft zu löschen
     * @param Group g
     * @param Person p
     */
    public void deleteMembership(int gFK, int pFK);
    
    /**
     * Methode um eine Gruppe zu bearbeiten
     * @param Group g
     */
    public void editGroup(Group g);
    
    /**
     * Methode um eine Gruppe zu löschen
     * @param Group g 
     */
    public void deleteGroup(Group g);
    
    /**
     * Methode um eine Gruppe anhand der ID zu finden
     * @param int id
     * @return Group
     */
    public Group getGroupById(int id);

    /**
     * Methode um eine Gruppe einer PersonFK anhand des Namen zu finden
     * @param int pFK
     * @param String groupName
     * @return Group
     */
    
    public Group getGroupOfPersonByGroupName(int personFk ,String groupName);
    
    /**
     * Methode um eine Gruppe anhand des PersonFK zu finden
     * @param int pFK
     * @return Vector<Group>
     */
    
    public Vector<Group> getGroupByPersonFK(int pFK);

    /**
     * Methode um eine Gruppe anhand des Namens zu finden
     * @param String name
     * @return Vector<Group>
     */
    public Vector<Group> getGroupByName(String name);

    Survey createSurvey(int gFK, int pFK, String city, String movieName, java.sql.Date startDate,
			java.sql.Date endDate);
    
    /**
     * Methode um eine Umfrage zu bearbeiten
     * @param Survey s
     * @return Survey
     */
    public void editSurvey(Survey s);
    
    /**
     * Methode um eine Umfrage zu löschen
     * @param Survey s
     */
    public void deleteSurvey(Survey s);
    
    /**
     * Methode um eine Umfrage anhand der ID zu finden
     * @param int id
     * @return Survey
     */
    public Survey getSurveyById(int id);
    
    /**
     * Methode um eine Umfrage anhand des PersonFKs zu finden
     * @param int pFK
     * @return Vector<Survey>
     */
    public Vector<Survey> getSurveyByPersonFK(int pFK);
    
    /**
     * Methode um eine Umfrage anhand des GrouFKs zu finden
     * @param int gFK
     * @return Vector<Survey>
     */
    public Vector<Survey> getSurveyByGroupFK(int gFK);
    
    /**
     * Methode um den Umfrageeintrag anhand der SurveyFk zu finden
     * @param sFK 
     * @return Vector<SurveyEntry>
     */
    public Vector<SurveyEntry> getSurveyEntryBySurveyFK(int sFK);

    /**
     * Methode um ein Vote zu erstellen
     * @param int vw 
     * @param int seFK
     * @param int pFK
     * @return Vote v
     */
    public Vote createVote(int vw, int seFK, int pFK);
    
    /**
     * Methode um ein Vote zu bearbeiten
     * @param Vote v
     */
    public void editVote(Vote v);
    
    /**
     * Methode um ein Vote zu löschen
     * @param Vote v
     */
    public void deleteVote(Vote v);
    
    /**
     * Methode um ein Vote anhand der ID zu finden
     * @param int id
     * @return Vote 
     */
    public Vote getVoteById(int id);
    
    /**
     * Methode um ein Vote anhand des PersonFK zu finden
     * @param int pFK
     * @return Vector<Vote>
     */
    public Vector<Vote> getVoteByPersonFK(int pFK);

    /**
     * Methode um ein Vote anhand des SurveyEntryFKs zu finden
     * @param int seFK 
     * @return Vector<Vote>
     */
    public Vector<Vote> getVoteBySurveyEntryFK(int SEFK);
    
    /**
     * Methode um ein Vote anhand seiner Gewichtung zu finden
     * @param int vw
     * @return Vector<Vote>
     */
    public Vector<Vote> getVoteByVotingWeight(int vw);
    
    /**
     * Methode um die Anzahl der Votes zu Zählen
     * @param SurveyEntry se 
     * @return int v.size();
     */
    public int countVotes(SurveyEntry se);
    
    /**
     * Methode um einen Umfrageeintrag zu erstellen
     * @param int scFK
     * @param in sFK
     * @return SurveyEntry se
     */
    public SurveyEntry createSurveyEntry(int scFK, int sFK, int pFK);
    
    /**
     * Methode um einen Umfrageeintrag zu bearbeiten
     * @param SurveyEntry se
     */
    public void editSurveyEntry(SurveyEntry se);
    
    /**
     * Methode um einen Umfrageeintrag anhand der ID zu finden
     * @param int id
     * @return SurveyEntry
     */
    public SurveyEntry getSurveyEntryById(int id);
    
    /**
     * Methode um einen Umfrageeintrag zu löschen
     * @param SurveyEntry se
     */
    public void deleteSurveyEntry(SurveyEntry se);
    
    /**
     * Methode um eine Person zu aktualisieren
     * @param Person p
     * @return Person p
     */
    public Person updatePerson(Person p);
    
    /**
     * Methode um eine Gruppe zu aktualisieren
     * @param Group g
     * @return Grou g
     */
    public Group updateGroup(Group g);	
    		
    /**
	 * Methode um eine Umfrage zu aktualisieren
	 * @param Survey s
	 * @return Survey s
	 */
    public Survey updateSurvey(Survey s);
    
	/**
	 * Methode um ein Vote zu aktualisieren
	 * @param Vote v
	 * @return Vote v
	 */
    public Vote updateVote(Vote v);
    
	/**
	 * Methode um einen Umfrageeintrag zu aktualisieren
	 * @param SurveyEntry se
	 * @return SurveyEntry se
	 */
    public SurveyEntry updateSurveyEntry(SurveyEntry se);
   
    /**
     * Methode um die Anzahl von Gruppenmitgliedern zu erhalten
     * @param gFK
     * return
     */
    public Vector<Membership> getGroupMembersOfGroup(int gFK);
    
    /**
     * Methode um den Film einer Umfrage zu erhalten
     * @param sFK 
     * return movie
     */
    public Movie getMovieBySurveyFK(int sFK);
    
    /**
     * Methode um alle Voters einer Survey zur�ckzugeben
     * @param surveyFK
     * return vector person
     */
    public Vector<Person> getVotedPersonsOfSurvey(int surveyFK);
    
    /**
     * Methode um alle Memberships einer Group zur�ckzugeben
     * @param group
     * return vector membership
     */
    public Vector<Membership> getMembershipsOfGroup(Group group);
    
    /**
     * Methode um alle Personen zur�ckzugeben
     * return vector person
     */
    public Vector<Person> getAllPersons();
    
    /**
     * Methode um den Film einer Suche zu erhalten
     * @param text
     * return movie
     */
    
    Vector<Movie> searchMovie(String text);
    
    /**
     * Methode um den Film einer Umfrage zu erhalten
     * @param text
     * return group
     */
    
    Vector<Group> searchGroup(String text);
    
    /**
     * Methode um den Film einer Umfrage zu erhalten
     * @param time
     * return survey
     */
    
//    Vector<Survey> searchSurvey(Timestamp time); 
    
    /**
     * Methode um den Namen des Films zu erhalten
     * @param name
     * return movie
     */
    
    Vector<Movie> getMoviesByName(String name);
    
    /**
     * Methode um den Namen des Films zu erhalten
     * @param genre
     * return movie
     */
    
    Vector<Movie> getMoviesByGenre(String genre);
    
    /**
     * Methode um den Namen des Films zu erhalten
     * @param text
     * return person
     */

	Vector<Person> searchPerson(String text);

	Screening getScreeningById(int id);

	Movie getMovieById(int id);

	Vector<SurveyEntry> getSurveyEntryByScreeningFK(int screeningFK);

	Vector<Movie> getMovieByGenre(String genre);

	Cinema getCinemaById(int id);

	Vector<Vote> getVotesBySurveyEntryFK(int surveyEntryFK);

	Vector<Movie> getAllMovies();

	Cinema getCinemaByScreeningFK(int screeningFK);

	Vector<Screening> getScreeningsforSurveyCreation(Movie movie, String city, java.sql.Date startDate,
			java.sql.Date endDate);

	Vector<Group> getGroupsByMemberships(int personFK);

	Vector<Survey> getSurveyToShow(int personFK) ;

	void deleteScreening(Screening screening);

	void deleteCinema(Cinema cinema);

	Vector<Screening> getScreeningByCinemaFK(int cinemaFK);

	void deleteMovie(Movie movie);

	Vector<Screening> getScreeningByMovieFK(int movieFK);

	void deleteCinemaChain(CinemaChain cc);

	Vector<Cinema> getCinemasByCinemaChainFK(CinemaChain cc) throws IllegalArgumentException;
	
	

	
}