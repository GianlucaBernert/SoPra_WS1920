package de.hdm.SoPra_WS1920.server.db;

import java.util.Vector;

import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Person;

/**
 * 
 */
public class PersonMapper {

    /**
     * Default constructor
     */
    public PersonMapper() {
    }

    /**
     * 
     */
    public PersonMapper personMapper;

    /**
     * 
     */
    protected void PersonMapper() {
        // TODO implement here
    }

    /**
     * @return
     */
    public static PersonMapper personMapper() {
        // TODO implement here
        return null;
    }

    /**
     * @param person 
     * @return
     */
    public Person insertPerson(Person person) {
        // TODO implement here
        return null;
    }

    /**
     * @param person 
     * @return
     */
    public Person updatePerson(Person person) {
        // TODO implement here
        return null;
    }

    /**
     * @param person 
     * @return
     */
    public void deletePerson(Person person) {
        // TODO implement here
        return null;
    }

    /**
     * @param personID 
     * @return
     */
    public Person findPersonByID(int personID) {
        // TODO implement here
        return null;
    }

    /**
     * @param firstname 
     * @return
     */
    public Vector<Person> findPersonByFirstname(String firstname) {
        // TODO implement here
        return null;
    }

    /**
     * @param lastname 
     * @return
     */
    public Vector<Person> findPersonByLastname(String lastname) {
        // TODO implement here
        return null;
    }

    /**
     * @param email 
     * @return
     */
    public Person findPersonByEmail(String email) {
        // TODO implement here
        return null;
    }

    /**
     * @param group 
     * @return
     */
    public Vector<Person> findPersonByGroupFK(Group group) {
        // TODO implement here
        return null;
    }

    /**
     * @param group 
     * @return
     */
    public void deletePersonByGroupFK(Group group) {
        // TODO implement here
        return null;
    }

    /**
     * @param isAdmin 
     * @return
     */
    public Vector<Person> findPersonByIsAdmin(boolean isAdmin) {
        // TODO implement here
        return null;
    }

}