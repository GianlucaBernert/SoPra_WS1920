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
    private int isAdmin;
    
    /**
     * Default constructor
     */
    public Person() {
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
    
    /**
	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
	 */
	public String toString() {
		return "PersonID #P" + super.getId() + " " + this.firstname + " " + this.lastname;
	}

}