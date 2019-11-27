package de.hdm.SoPra_WS1920.server.db;

import java.sql.Timestamp;
import java.util.Vector;

import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Person;
import de.hdm.SoPra_WS1920.shared.bo.Survey;

/**
 * 
 */
public class SurveyMapper {

    /**
     * Default constructor
     */
    public SurveyMapper() {
    }

    /**
     * 
     */
    public SurveyMapper surveyMapper;

    /**
     * 
     */
    protected void SurveyMapper() {
        // TODO implement here
    }

    /**
     * @return
     */
    public static SurveyMapper surveyMapper() {
        // TODO implement here
        return null;
    }

    /**
     * @param survey 
     * @return
     */
    public Survey insertSurvey(Survey survey) {
        // TODO implement here
        return null;
    }

    /**
     * @param survey 
     * @return
     */
    public Survey updateSurvey(Survey survey) {
        // TODO implement here
        return null;
    }

    /**
     * @param survey 
     * @return
     */
    public void deleteSurvey(Survey survey) {
        // TODO implement here
        return null;
    }

    /**
     * @param surveyID 
     * @return
     */
    public Survey findSurveyByID(int surveyID) {
        // TODO implement here
        return null;
    }

    /**
     * @param endDate 
     * @return
     */
    public Vector<Survey> findSurveyByEndDate(Timestamp endDate) {
        // TODO implement here
        return null;
    }

    /**
     * @param groupFK 
     * @return
     */
    public Vector<Survey> findSurveyByGroupFK(int groupFK) {
        // TODO implement here
        return null;
    }

    /**
     * @param group 
     * @return
     */
    public void deleteSurveyByGroupFK(Group group) {
        // TODO implement here
        return null;
    }

    /**
     * @param id 
     * @return
     */
    public Vector<Survey> findSurveyByPersonFK(int id) {
        // TODO implement here
        return null;
    }

    /**
     * @param person 
     * @return
     */
    public void deleteSurveyByPersonFK(Person person) {
        // TODO implement here
        return null;
    }

    /**
     * @param surveyentry 
     * @return
     */
    public Survey findSurveyBySurveyEntryFK(Surveyentry surveyentry) {
        // TODO implement here
        return null;
    }

    /**
     * @param surveyentry 
     * @return
     */
    public void deleteSurveyBySurveyEntryFK(Surveyentry surveyentry) {
        // TODO implement here
        return null;
    }

}