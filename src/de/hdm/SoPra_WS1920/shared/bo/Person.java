package de.hdm.SP1920.shared.bo;

import java.util.*;

/**
 * 
 */
public class Person extends BusinessObject {

    /**
     * Default constructor
     */
    public Person() {
    }

    /**
     * 
     */
    private int id;

    /**
     * 
     */
    private String firstname;

    /**
     * 
     */
    private String lastname;

    /**
     * 
     */
    private String eMail;

    /**
     * 
     */
    private Boolean isAdmin = false;

    /**
     * 
     */
    public Set<Movie> Creation;


    /**
     * 
     */
    public Set<Cinema> Location;


    /**
     * 
     */
    public Set<Screening> Movie Event;



    /**
     * 
     */
    public Set<Survey> inquiry;

    /**
     * 
     */
    public Set<Vote> interest;



    /**
     * 
     */
    public Set<Ownership> CreationObject;

    /**
     * 
     */
    public Set<Group> team;

    /**
     * @return
     */
    public int getId() {
        // TODO implement here
        return 0;
    }

    /**
     * @param id 
     * @return
     */
    public void setId(int id) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String getFirstname() {
        // TODO implement here
        return "";
    }

    /**
     * @param firstname 
     * @return
     */
    public void setFirstname(String firstname) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String getLastname() {
        // TODO implement here
        return "";
    }

    /**
     * @param lastname 
     * @return
     */
    public void setLastname(String lastname) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String getEMail() {
        // TODO implement here
        return "";
    }

    /**
     * @param eMail 
     * @return
     */
    public void setEMail(String eMail) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Boolean getIsAdmin() {
        // TODO implement here
        return null;
    }

    /**
     * @param isAdmin 
     * @return
     */
    public void setIsAdmin(Boolean isAdmin) {
        // TODO implement here
        return null;
    }

}