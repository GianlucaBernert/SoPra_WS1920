package de.hdm.SP1920.server.db;

import java.util.*;

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
    public Survey findSurveyByEndDate(Datetime endDate) {
        // TODO implement here
        return null;
    }

    /**
     * @param group 
     * @return
     */
    public vector<Survey> findSurveyByGroupFK(Group group) {
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
     * @param person 
     * @return
     */
    public vector<Survey> findSurveyByPersonFK(Person person) {
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