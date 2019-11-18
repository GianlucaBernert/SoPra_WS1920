
import java.util.*;

/**
 * 
 */
public interface SurveyManagement extends RemoteService {

    /**
     * @param id 
     * @param firstName 
     * @param lastName 
     * @param email 
     * @param isAdmin 
     * @return
     */
    public Person createPerson(int id, String firstName, String lastName, String email, int isAdmin);

    /**
     * @param id 
     * @param name 
     * @return
     */
    public Group createGroup(int id, String name);

    /**
     * @param id 
     * @param startDate 
     * @param endDate 
     * @return
     */
    public Survey createSurvey(int id, DateTime startDate, DateTime endDate);

    /**
     * @param id 
     * @param votingWeight 
     * @return
     */
    public Vote createVote(int id, int votingWeight);

    /**
     * @param id 
     * @return
     */
    public SurveyEntry createSurveyEntry(int id);

    /**
     * @param person 
     * @return
     */
    public Void updatePerson(Person person);

    /**
     * @param group 
     * @return
     */
    public Void updateGroup(Group group);

    /**
     * @param survey 
     * @return
     */
    public Void updateSurvey(Survey survey);

    /**
     * @param vote 
     * @return
     */
    public Void updateVote(Vote vote);

    /**
     * @param surveyentry 
     * @return
     */
    public Void updateSurveyEntry(SurveyEntry surveyentry);

    /**
     * @param person 
     * @return
     */
    public Void deletePerson(Person person);

    /**
     * @param group 
     * @return
     */
    public Void deleteGroup(Group group);

    /**
     * @param survey 
     * @return
     */
    public Void deleteSurvey(Survey survey);

    /**
     * @param vote 
     * @return
     */
    public Void deleteVote(Vote vote);

    /**
     * @param surveyentry 
     * @return
     */
    public Void deleteSurveyEntry(SurveyEntry surveyentry);

    /**
     * @param firstName 
     * @return
     */
    public Person getPersonByFirstname(String firstName);

    /**
     * @param lastName 
     * @return
     */
    public Person getPersonByLastname(String lastName);

    /**
     * @param email 
     * @return
     */
    public Person getPersonByEmail(String email);

    /**
     * @param name 
     * @return
     */
    public Vector<Group> getGroupByName(String name);

    /**
     * @param startDate 
     * @return
     */
    public Vector<Survey> getSurveyByStartDate(DateTime startDate);

    /**
     * @param endDate 
     * @return
     */
    public Vector<Survey> getSurveyByEndDate(DateTime endDate);

    /**
     * @param votingWeight 
     * @return
     */
    public Vector<Vote> getVoteByVotingWeight(int votingWeight);

    /**
     * @param id 
     * @return
     */
    public Person getPersonById(int id);

    /**
     * @param id 
     * @return
     */
    public Group getGroupById(int id);

    /**
     * @param id 
     * @return
     */
    public Survey getSurveyById(Int id);

    /**
     * @param id 
     * @return
     */
    public Vote getVoteById(int id);

    /**
     * @param id 
     * @return
     */
    public SurveyEntry getSurveyEntryId(int id);

    /**
     * @param personFK 
     * @return
     */
    public Vector<Group> getGroupByPersonFK(int personFK);

    /**
     * @param groupFK 
     * @return
     */
    public Vector<Survey> getSurveyByGroupFK(int groupFK);

    /**
     * @param personFK 
     * @return
     */
    public Vector<Survey> getSurveyByPersonFK(int personFK);

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
     * @param surveyFK 
     * @return
     */
    public Vector<SurveyEntry> getSurveyEntryBySurveyFK(int surveyFK);

}