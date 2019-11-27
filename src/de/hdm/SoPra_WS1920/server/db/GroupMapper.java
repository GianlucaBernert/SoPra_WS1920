package de.hdm.SoPra_WS1920.server.db;

import java.util.Vector;

import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Survey;

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
    public Vector<Group> findGroupByName(String name) {
        // TODO implement here
        return null;
    }

    /**
     * @param id 
     * @return
     */
    public Vector<Group> findGroupByPersonFK(int id) {
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
    public Vector<Survey> findGroupBySurveyFK(Survey survey) {
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