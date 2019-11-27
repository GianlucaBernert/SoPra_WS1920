package de.hdm.SoPra_WS1920.shared;

import java.sql.Timestamp;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Person;
import de.hdm.SoPra_WS1920.shared.bo.Survey;
import de.hdm.SoPra_WS1920.shared.bo.SurveyEntry;
import de.hdm.SoPra_WS1920.shared.bo.Vote;

/**
 * @author GianlucaBernert
 */

@RemoteServiceRelativePath("surveyManagement")

public interface SurveyManagement extends RemoteService {

    /**
     * Method to create a person
     * @param id 
     * @param firstName 
     * @param lastName 
     * @param email 
     * @param isAdmin 
     * @return
     */
    public Person createPerson(int id, String firstname, String lastname, String eMail, int isAdmin, Timestamp creationTimestamp);
    
    /**
     * @param person 
     * @return
     */
    public void deletePerson(Person person);
    
    /**
     * @param id 
     * @return
     */
    public Person getPersonById(int id);
    
    /**
     * @param firstName 
     * @return
     */
    public Vector<Person> getPersonByFirstname(String firstName);

    /**
     * @param lastName 
     * @return
     */
    public Vector<Person> getPersonByLastname(String lastName);

    /**
     * @param email 
     * @return
     */
    public Person getPersonByEmail(String email);

    /**
     * @param id 
     * @param name 
     * @return
     */
    public Group createGroup(int id, String name, int PersonFK, Timestamp creationTimestamp);
    
    /**
     * @param group 
     * @return
     */
    public void deleteGroup(Group group);
    
    /**
     * @param id 
     * @return
     */
    public Group getGroupById(int id);

    /**
     * @param personFK 
     * @return
     */
    public Vector<Group> getGroupByPersonFK(int personFK);

    /**
     * @param name 
     * @return
     */
    public Vector<Group> getGroupByName(String name);

    /**
     * @param id 
     * @param startDate 
     * @param endDate 
     * @return
     */
    public Survey createSurvey(int id, int GroupFK, int PersonFK, Timestamp startDate, Timestamp endDate, Timestamp creationTimestamp);
    
    /**
     * @param survey 
     * @return
     */
    public void deleteSurvey(Survey survey);
    
    /**
     * @param id 
     * @return
     */
    public Survey getSurveyById(int id);
    
    /**
     * @param personFK 
     * @return
     */
    public Vector<Survey> getSurveyByPersonFK(int personFK);
    
    /**
     * @param groupFK 
     * @return
     */
    public Vector<Survey> getSurveyByGroupFK(int groupFK);
    
    /**
     * @param surveyFK 
     * @return
     */
    public Vector<SurveyEntry> getSurveyEntryBySurveyFK(int surveyFK);
    
    /**
     * @param startDate 
     * @return
     */
    public Vector<Survey> getSurveyByStartDate(Timestamp startDate);

    /**
     * @param endDate 
     * @return
     */
    public Vector<Survey> getSurveyByEndDate(Timestamp endDate);

    /**
     * @param id 
     * @param votingWeight 
     * @return
     */
    public Vote createVote(int id, int votingWeight, int SurveyEntryFK, int PersonFK, Timestamp creationTimestamp);
    
    /**
     * @param vote 
     * @return
     */
    public void deleteVote(Vote vote);
    
    /**
     * @param id 
     * @return
     */
    public Vote getVoteById(int id);
    
    /**
     * @param personFK 
     * @return
     */
    public Vector<Vote> getVoteByPersonFK(int personFK);

    /**
     * @param surveyEntryFK 
     * @return
     */
    public Vector<Vote> getVoteBySurveyEntryFK(int surveyEntryFK);
    
    /**
     * @param votingWeight 
     * @return
     */
    public Vector<Vote> getVoteByVotingWeight(int votingWeight);

    /**
     * @param id 
     * @return
     */
    public SurveyEntry createSurveyEntry(int id, int screeningFK, int surveyFK, Timestamp creationTimestamp);
    
    /**
     * @param id 
     * @return
     */
    public SurveyEntry getSurveyEntryById(int id);
    
    

    /**
     * @param surveyentry 
     * @return
     */
    public void deleteSurveyEntry(SurveyEntry surveyentry);
   
}