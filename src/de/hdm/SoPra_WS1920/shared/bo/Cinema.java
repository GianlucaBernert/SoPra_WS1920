package de.hdm.SoPra_WS1920.shared.bo;
import java.util.*;


/**
 * @author GianlucaBernert
 */
public class Cinema extends Ownership {

    /**
     * Variables of the class Group
     */
    private String name;
    private String city;
    private String postCode;
    private String street;
    private String streetNo;
    
    /**
     * Default constructor
     */
    public Cinema() {
    }

    
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
    public String getPostCode() {
       return postCode;
    }

    /**
     * @param postCode2 
     */
    public void setPostCode(String postCode2) {
        this.postCode = postCode2;
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
    
    /**
	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
	 */
	public String toString() {
		return "CinemaID #C" + this.getId();
	}

}