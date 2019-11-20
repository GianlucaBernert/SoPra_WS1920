package de.hdm.SoPra_WS1920.shared.bo;

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
    private int isAdmin;

   
    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname 
     */
    public void setFirstname(String firstname) {
       this.firstname = firstname;
    }

    /**
     * @return lastname
     */
    public String getLastname() {
       return lastname;
    }

    /**
     * @param lastname 
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return email
     */
    public String getEMail() {
        return eMail;
    }

    /**
     * @param eMail 
     */
    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    /**
     * @return isAdmin
     */
    public int getIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin 
     */
    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

}