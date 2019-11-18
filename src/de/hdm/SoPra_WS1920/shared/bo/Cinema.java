package de.hdm.SP1920.shared.bo;

import java.util.*;

/**
 * 
 */
public class Cinema extends Ownership {

    /**
     * Default constructor
     */
    public Cinema() {
    }

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String city;

    /**
     * 
     */
    private int postCode;

    /**
     * 
     */
    private String street;

    /**
     * 
     */
    private String streetNo;

    /**
     * 
     */
    public Person Organizer;

    /**
     * 
     */
    public Set<Screening> Playing Time;



    /**
     * @return
     */
    public String getName() {
        // TODO implement here
        return "";
    }

    /**
     * @param name 
     * @return
     */
    public void setName(String name) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String getCity() {
        // TODO implement here
        return "";
    }

    /**
     * @param city 
     * @return
     */
    public void setCity(String city) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public int getPostCode() {
        // TODO implement here
        return 0;
    }

    /**
     * @param postCode 
     * @return
     */
    public void setPostCode(int postCode) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String getStreet() {
        // TODO implement here
        return "";
    }

    /**
     * @param street 
     * @return
     */
    public void setStreet(String street) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String getStreetNo() {
        // TODO implement here
        return "";
    }

    /**
     * @param streetNo 
     * @return
     */
    public void setStreetNo(String streetNo) {
        // TODO implement here
        return null;
    }

}