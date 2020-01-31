package de.hdm.SoPra_WS1920.shared;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.SoPra_WS1920.shared.bo.BusinessObject;
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
public interface SurveyManagementAsync {
	
	
	/**
     * Methode um ein BusinessObject zu erstellen
     * @param AsyncCallback<BusinessObject> callback
     * @return BusinessObject bo
     */
	public void createBusinessObject(AsyncCallback<BusinessObject> callback);
	
    /**
     * Methode um ein BusinessObject zu lÃ¶schen
     * @param BusinessObject bo
     * @param AsyncCallback<Void> callback
     */
	public void deleteBusinessObject(BusinessObject bo, AsyncCallback<Void> callback);
	
    /*
     * Methode um eine Ownership zu erstellen
     * @param int pFK
     * @param AsyncCallback<Ownership> callback
     * @return Ownership os
     */
	public void createOwnership(int pFK, AsyncCallback<Ownership> callback);
	
    /*
     * Methode um eine Ownership zu lÃ¶schen
     * @param Ownership os
     * @param AsyncCallback<Void> callback
     */
	public void deleteOwnership(Ownership os, AsyncCallback<Void> callback);

    /**
     * Methode um eine Person zu erstellen
     * @param String firstName 
     * @param String lastName 
     * @param String email 
     * @param AsyncCallback<Person> callback
     * @return Person p
     */
    public void createPerson(String firstName, String lastName, String eMail, AsyncCallback<Person> callback);
    
    /**
     * Methode um eine Person zu bearbeiten
     * @param Person p 
     * @param AsyncCallback<Void> callback
     */
    public void editPerson(Person p, AsyncCallback<Void> callback);

    void createSurvey(int gFK, int pFK, String city, String movieName, Date startDate, Date endDate, AsyncCallback<Survey> callback);
    
    /**
     * Methode um eine Umfrage zu bearbeiten
     * @param Survey s
     * @param AsyncCallback<Void> callback
     * @return Survey
     */
    public void editSurvey(Survey s, AsyncCallback<Void> callback);

    /*
     * Methode um eine Person zu aktualisieren
     * @param Person p
     * @param AsyncCallback<Person> callback
     * @return Person p
     */
    public void updatePerson(Person p, AsyncCallback<Person> callback);
    
    /**
     * Methode um eine Person zu lÃ¶schen
     * @param Person p
     * @param AsyncCallback<Void> callback
     */
    public void deletePerson(Person p, AsyncCallback<Void> callback);
    
    /**
     * Methode um eine Person anhand des Vornamens zu finden
     * @param String firstName
     * @param AsyncCallback<Vector<Person>> callback
     * @return Vector<Person>
     */
    public void getPersonByFirstname(String firstName, AsyncCallback<Vector<Person>> callback);

    /**
     * Methode um eine Person anhand des Nachnamens zu finden
     * @param String lastName
     * @param AsyncCallback<Vector<Person>> callback
     * @return Vector<Person>
     */
    public void getPersonByLastname(String lastName, AsyncCallback<Vector<Person>> callback);

    /**
     * Methode um eine Person anhand der E-Mail adresse zu finden
     * @param String eMail
     * @param AsyncCallback<Person> callback
     * @return Person
     */
    public void getPersonByEmail(String eMail, AsyncCallback<Person> callback);

    /**
     * Methode um eine Gruppe zu erstellen
     * @param String name
     * @param int pFK
     * @param AsyncCallback<Group> callback
     * @return Group g
     */
    public void createGroup(String name, int pFK, AsyncCallback<Group> callback);
    
    /**
     * Methode um eine Mitgliedschaft zu erstellen 
     * @param Group g
     * @param Person p
     * @param AsyncCallback<Void> callback
     */
    void createMembership(Group g, Person p, AsyncCallback<Membership> callback);
    
    /**
     * Methode um eine Mitgliedschaft zu lÃ¶schen
     * @param Group g
     * @param Person p
     * @param AsyncCallback<Void> callback
     */
    public void deleteMembership(int gFK, int pFK, AsyncCallback<Void> callback);
    
    /**
     * Methode um eine Gruppe zu bearbeiten
     * @param Group g
     * @param AsyncCallback<Void> callback
     */
    public void editGroup(Group g, AsyncCallback<Void> callback);
    
    /**
     * Methode um eine Gruppe zu aktualisieren
     * @param Group g
     * @param AsyncCallback<Group> callback
     * @return Grou g
     */
    public void updateGroup(Group g, AsyncCallback<Group> callback);

    /**
     * Methode um eine Gruppe zu lÃ¶schen
     * @param Group g 
     * @param AsyncCallback<Void> callback
     */
    public void deleteGroup(Group g, AsyncCallback<Void> callback);
    
    /**
     * Methode um eine Gruppe anhand des Namens zu finden
     * @param String name
     * @param AsyncCallback<Vector<Group>> callback
     * @return Vector<Group>
     */
    public void getGroupByName(String name, AsyncCallback<Vector<Group>> callback);


    /**
     * Methode um eine Person anhand der ID zu finden
     * @param int id 
     * @param AsyncCallback<Person> callback
     * @return Person
     */
    public void getPersonById(int id, AsyncCallback<Person> callback);

    /**
     * Methode um eine Gruppe anhand der ID zu finden
     * @param int id
     * @param AsyncCallback<Group> callback
     * @return Group
     */
    public void getGroupById(int id, AsyncCallback<Group> callback);

    /**
     * Methode um eine Gruppe anhand des PersonFK zu finden
     * @param int pFK
     * @param AsyncCallback<Vector<Group>> callback
     * @return Vector<Group>
     */
    public void getGroupByPersonFK(int pFK, AsyncCallback<Vector<Group>> callback);
    
    /**
	 * Methode um eine Umfrage zu aktualisieren
	 * @param Survey s
	 * @param AsyncCallback<Survey> callback
	 * @return Survey s
	 */
    public void updateSurvey(Survey s, AsyncCallback<Survey> callback);
    
    /**
     * Methode um eine Umfrage zu lÃ¶schen
     * @param Survey s
     * @param AsyncCallback<Void> callback
     */
    public void deleteSurvey(Survey s, AsyncCallback<Void> callback);
       
    /**
     * Methode um eine Umfrage anhand der ID zu finden
     * @param int id
     * @param AsyncCallback<Survey> callback
     * @return Survey
     */
    public void getSurveyById(int id, AsyncCallback<Survey> callback);
    
    /**
     * Methode um eine Umfrage anhand des GrouFKs zu finden
     * @param int gFK
     * @param AsyncCallback<Vector<Survey>> callback
     * @return Vector<Survey>
     */
    public void getSurveyByGroupFK(int gFK, AsyncCallback<Vector<Survey>> callback);

    /**
     * Methode um eine Umfrage anhand des PersonFKs zu finden
     * @param int pFK
     * @param AsyncCallback<Vector<Survey>> callback
     * @return Vector<Survey>
     */
    public void getSurveyByPersonFK(int pFK, AsyncCallback<Vector<Survey>> callback);

    /**
     * Methode um ein Vote zu erstellen
     * @param int vw 
     * @param int seFK
     * @param int pFK
     * @param AsyncCallback<Vote> callback
     * @return Vote v
     */
    public void createVote(int vw, int seFK, int pFK, AsyncCallback<Vote> callback);
    
    /**
     * Methode um ein Vote zu bearbeiten
     * @param Vote v
     * @param AsyncCallback<Void> callback
     */
    public void editVote(Vote v, AsyncCallback<Void> callback);
    
	/**
	 * Methode um ein Vote zu aktualisieren
	 * @param Vote v
	 * @param AsyncCallback<Vote> callback
	 * @return Vote v
	 */
    public void updateVote(Vote v, AsyncCallback<Vote> callback);
    
    /**
     * Methode um ein Vote zu lÃ¶schen
     * @param Vote v
     * @param AsyncCallback<Void> callback
     */
    public void deleteVote(Vote v, AsyncCallback<Void> callback);
    
    /**
     * Methode um ein Vote anhand seiner Gewichtung zu finden
     * @param int vw
     * @param AsyncCallback<Vector<Vote>> callback
     * @return Vector<Vote>
     */
    public void getVoteByVotingWeight(int vw, AsyncCallback<Vector<Vote>> callback);
    
    /**
     * Methode um ein Vote anhand der ID zu finden
     * @param int id
     * @param AsyncCallback<Vote> callback
     * @return Vote 
     */
    public void getVoteById(int id, AsyncCallback<Vote> callback);
    
    /**
     * Methode um die Anzahl der Votes zu ZÃ¤hlen
     * @param SurveyEntry se 
     * @param AsyncCallback<Integer> callback
     * @return int v.size();
     */
    public void countVotes(SurveyEntry se, AsyncCallback<Integer> callback);
    
    /**
     * Methode um ein Vote anhand des PersonFK zu finden
     * @param int pFK
     * @param AsyncCallback<Vector<Vote>> callback
     * @return Vector<Vote>
     */
    public void getVoteByPersonFK(int pFK, AsyncCallback<Vector<Vote>> callback);

    /**
     * Methode um ein Vote anhand des SurveyEntryFKs zu finden
     * @param int seFK 
     * @param AsyncCallback<Vector<Vote>> callback
     * @return Vector<Vote>
     */
    public void getVoteBySurveyEntryFK(int seFK, AsyncCallback<Vector<Vote>> callback);

    /**
     * Methode um einen Umfrageeintrag zu erstellen
     * @param int scFK
     * @param in sFK
     * @param AsyncCallback<SurveyEntry> callback
     * @return SurveyEntry se
     */
    public void createSurveyEntry(int scFK, int sFK, int pFK, AsyncCallback<SurveyEntry> callback);

	/**
	 * Methode um einen Umfrageeintrag zu aktualisieren
	 * @param SurveyEntry se
	 * @param AsyncCallback<SurveyEntry> callback
	 * @return SurveyEntry se
	 */
    public void updateSurveyEntry(SurveyEntry se, AsyncCallback<SurveyEntry> callback);

    /**
     * Methode um einen Umfrageeintrag zu lÃ¶schen
     * @param SurveyEntry se
     * @param AsyncCallback<Void> callback
     */
    public void deleteSurveyEntry(SurveyEntry se, AsyncCallback<Void> callback);
    
    /**
     * Methode um einen Umfrageeintrag anhand der ID zu finden
     * @param int id
     * @param AsyncCallback<SurveyEntry> callback
     * @return SurveyEntry
     */
    public void getSurveyEntryById(int id, AsyncCallback<SurveyEntry> callback);

    /**
     * Methode um den Umfrageeintrag anhand der SurveyFk zu finden
     * @param sFK 
     * @param AsyncCallback<Vector<SurveyEntry>> callback
     * @return Vector<SurveyEntry>
     */
    public void getSurveyEntryBySurveyFK(int sFK, AsyncCallback<Vector<SurveyEntry>> callback);
    
    /**
     * Methode um einen Umfrageeintrag zu bearbeiten
     * @param SurveyEntry se
     * @param AsyncCallback<Void> callback
     */
    public void editSurveyEntry(SurveyEntry se, AsyncCallback<Void> callback);
    
    /**
     * Methode um die Memberships einer Gruppe zu erhalten
     * @param gFK
     * @param AsyncCallback 
     * return Vector Membership
     */
    public void getGroupMembersOfGroup(int sFK, AsyncCallback<Vector<Membership>> callback);
    
    /**
     * Methode um den Film einer Umfrage zu erhalten
     * @param sFK
     * @param AsyncCallback 
     * return movie
     */
    public void getMovieBySurveyFK(int sFK, AsyncCallback<Movie> callback);

    /**
     * Methode um alle Voters einer Survey zurückzugeben
     * @param surveyFK
     * @param AsyncCallback
     * return vector person
     */
    public void getVotedPersonsOfSurvey(int surveyFK, AsyncCallback<Vector<Person>> callback);

    /**
     * Methode um alle Memberships einer Gruppe zurückzugeben
     * @param group
     * @param AsyncCallback
     * return vector membership
     */
    public void getMembershipsOfGroup(Group group, AsyncCallback<Vector<Membership>> callback);
    
    /**
     * Methode um alle Persons zurückzugeben
     * @param AsyncCallback
     * return vector person
     */
    public void getAllPersons(AsyncCallback<Vector<Person>> callback);
    /**
     * Methode um den Film einer Umfrage zu erhalten
     * @param text
     * @param AsyncCallback 
     * return movie
     */
    
	void searchMovie(String text, AsyncCallback<Vector<Movie>> callback);
	
	/**
     * Methode um den Film einer Umfrage zu erhalten
     * @param text
     * @param AsyncCallback 
     * return group
     */
	
	void searchGroup(String text, AsyncCallback<Vector<Group>> callback);
	
	/**
     * Methode um den Film einer Umfrage zu erhalten
     * @param time
     * @param AsyncCallback 
     * return survey
     */
	
//	void searchSurvey(Timestamp time, AsyncCallback<Vector<Survey>> callback);

	/**
     * Methode um den Film einer Umfrage zu erhalten
     * @param name
     * @param AsyncCallback 
     * return movie
     */
	
	void getMoviesByName(String name, AsyncCallback<Vector<Movie>> callback);
	
	/**
     * Methode um den Film einer Umfrage zu erhalten
     * @param genre
     * @param AsyncCallback 
     * return movie
     */
	
	void getMoviesByGenre(String genre, AsyncCallback<Vector<Movie>> callback);
	
	/**
     * Methode um den Film einer Umfrage zu erhalten
     * @param text
     * @param AsyncCallback 
     * return person
     */

	void searchPerson(String text, AsyncCallback<Vector<Person>> callback);

	void getScreeningById(int id, AsyncCallback<Screening> callback);

	void getMovieById(int id, AsyncCallback<Movie> callback);

	void getGroupOfPersonByGroupName(int personFk, String groupName, AsyncCallback<Group> callback);
    

}