package de.hdm.SoPra_WS1920.shared.bo;
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
     *@return name
     */
    
    public String getName() {
        return name;
    }

    /**
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
        
    }

    /**
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city 
     */
    public void setCity(String city) {
       this.city = city;
    }

    /**
     * @return
     */
    public int getPostCode() {
       return postCode;
    }

    /**
     * @param postCode 
     */
    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    /**
     * @return street
     */
    public String getStreet() {
       return street;
    }

    /**
     * @param street 
     */
    public void setStreet(String street) {
       this.street = street;
    }

    /**
     * @return streetNo
     */
    public String getStreetNo() {
       return streetNo;
    }

    /**
     * @param streetNo 
     */
    public void setStreetNo(String streetNo) {
       this.streetNo = streetNo;
    }

}