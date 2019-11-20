package de.hdm.SoPra_WS1920.server.db;

import java.util.*;

/**
 * 
 */
public class GroupMapper {

    /**
     * Default constructor
     */
    public GroupMapper() {
    }

    /**
     * 
     */
    public GroupMapper groupMapper;

    /**
     * 
     */
    protected void GroupMapper() {
        // TODO implement here
    }

    /**
     * @return
     */
    public static GroupMapper groupMapper() {
        // TODO implement here
        return null;
    }

    /**
     * @param group 
     * @return
     */
    public Group insertGroup(Group group) {
        // TODO implement here
        return null;
    }

    /**
     * @param group 
     * @return
     */
    public Group updateGroup(Group group) {
        // TODO implement here
        return null;
    }

    /**
     * @param group 
     * @return
     */
    public void deleteGroup(Group group) {
        // TODO implement here
        return null;
    }

    /**
     * @param groupID 
     * @return
     */
    public Group findGroupByID(int groupID) {
        // TODO implement here
        return null;
    }

    /**
     * @param name 
     * @return
     */
    public Group findGroupByName(String name) {
        // TODO implement here
        return null;
    }

    /**
     * @param person 
     * @return
     */
    public vector<Group> findGroupByPersonFK(String person) {
        // TODO implement here
        return null;
    }

    /**
     * @param person 
     * @return
     */
    public void deleteGroupByPersonFK(String person) {
        // TODO implement here
        return null;
    }

    /**
     * @param survey 
     * @return
     */
    public vector<Survey> findGroupBySurveyFK(Survey survey) {
        // TODO implement here
        return null;
    }

    /**
     * @param survey 
     * @return
     */
    public void deleteGroupBySurveyFK(Survey survey) {
        // TODO implement here
        return null;
    }

}