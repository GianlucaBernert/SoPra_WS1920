package de.hdm.SoPra_WS1920.shared.bo;

import java.util.*;

/**
 * @author GianlucaBernert
 */
public class Person extends BusinessObject {

	/**
     * Variables of the class Person
     */
    private String firstname;
    private String lastname;
    private String eMail;
    private boolean isAdmin;
    
    /**
     * Default constructor
     */
    public Person() {
    }

    /**
     * @return String firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param String firstname 
     */
    public void setFirstname(String firstname) {
       this.firstname = firstname;
    }

    /**
     * @return String lastname
     */
    public String getLastname() {
       return lastname;
    }

    /**
     * @param String lastname 
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return String email
     */
    public String getEMail() {
        return eMail;
    }

    /**
     * @param String eMail 
     */
    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    /**
     * @return boolean isAdmin
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * @param boolean isAdmin 
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    /**
	 * @return String of id + firstname + lastname
	 */
	public String toString() {
		return "PersonID #P" + super.getId() + " " + this.firstname + " " + this.lastname;
	}

}