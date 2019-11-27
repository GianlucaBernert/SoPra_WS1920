package de.hdm.SoPra_WS1920.server.db;

import java.util.Vector;

import de.hdm.SoPra_WS1920.shared.bo.Screening;
import de.hdm.SoPra_WS1920.shared.bo.Survey;
import de.hdm.SoPra_WS1920.shared.bo.SurveyEntry;
import de.hdm.SoPra_WS1920.shared.bo.Vote;

/**
 * 
 */
public class SurveyEntryMapper {

    /**
     * Default constructor
     */
    public SurveyEntryMapper() {
    }

    /**
     * 
     */
    public SurveyEntryMapper surveyEntryMapper;

    /**
     * 
     */
    protected void SurveyEntryMapper() {
        // TODO implement here
    }

    /**
     * @return
     */
    public static SurveyEntryMapper surveyEntryMapper() {
        // TODO implement here
        return null;
    }

    /**
     * @param surveyEntry 
     * @return
     */
    public SurveyEntry insertSurveyEntry(SurveyEntry surveyEntry) {
        // TODO implement here
        return null;
    }

    /**
     * @param surveyEntry 
     * @return
     */
    public SurveyEntry updateSurveyEntry(SurveyEntry surveyEntry) {
        // TODO implement here
        return null;
    }

    /**
     * @param surveyEntry 
     * @return
     */
    public void deleteSurveyEntry(SurveyEntry surveyEntry) {
        // TODO implement here
        return null;
    }

    /**
     * @param surveyEntryID 
     * @return
     */
    public SurveyEntry findSurveyEntryByID(int surveyEntryID) {
        // TODO implement here
        return null;
    }

    /**
     * @param screening 
     * @return
     */
    public Vector<SurveyEntry> findSurveyEntryByScreeningFK(Screening screening) {
        // TODO implement here
        return null;
    }

    /**
     * @param screening 
     * @return
     */
    public void deleteSurveyEntryByScreeningFK(Screening screening) {
        // TODO implement here
        return null;
    }

    /**
     * @param vote 
     * @return
     */
    public Vector<SurveyEntry> findSurveyEntryByVoteFK(Vote vote) {
        // TODO implement here
        return null;
    }

    /**
     * @param vote 
     * @return
     */
    public void deleteSurveyEntryByVoteFK(Vote vote) {
        // TODO implement here
        return null;
    }

    /**
     * @param id 
     * @return
     */
    public Vector<SurveyEntry> findSurveyEntryBySurveyFK(int id) {
        // TODO implement here
        return null;
    }
    
    /**
     * @param survey 
     * @return
     */
    public Vector<SurveyEntry> findSurveyEntryByPersonFK(int id) {
        // TODO implement here
        return null;
    }

    /**
     * @param survey 
     * @return
     */
    public void deleteSurveyEntryBySurveyFK(Survey survey) {
        // TODO implement here
        return null;
    }

}