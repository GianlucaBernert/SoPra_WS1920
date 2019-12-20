package de.hdm.SoPra_WS1920.shared.bo;
import java.util.*;


/**
 * @author GianlucaBernert
 * Klasse cinema, welche die Attribute Name, Stadt, PLZ, Straße und Hausnummer direkt enthält
 * und die die Beziehung zwischen Person und einem Kino von der Klasse Ownership erbt, 
 * diese wiederum erbt SerialVersionUID, die ID und den Erstellzeitpunkt von BusinessObject.
 */
public class Cinema extends Ownership {

    /**
     * Variablen der Klasse Cinema
     */
    private String name;
    private String city;
    private String zipCode;
    private String street;
    private String streetNo;
    
    /**
     * Konstruktor der Klasse Cinema, welcher beim Aufruf dieser eine Instanz seiner selbst erzeugt
     */
    public Cinema() {
    }

    
    /**
     * Methode um den Namen eines Kinos zu setzen
     *@return String name
     */
    
    public String getName() {
        return name;
    }

    /**
     * Methode um den namen eines Kinos auszugeben
     * @param String name 
     */
    public void setName(String name) {
        this.name = name;
        
    }

    /**Methode um die Stadt eines Kinos auszugeben
     * @return String city
     */
    public String getCity() {
        return city;
    }

    /**
     * Methode um die Stadt eines Kinos zu setzen
     * @param String city 
     */
    public void setCity(String city) {
       this.city = city;
    }

    /**
     * Methode um die Postleitzahl eines Kinos auszugeben
     * @return String zipCode
     */
    public String getzipCode() {
       return zipCode;
    }

    /**
     * Methode um die Postleitzahl eines Kinos zu setzen
     * @param String zipCode 
     */
    public void setzipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Methode um die Straße eines Kinos auszugeben
     * @return String street
     */
    public String getStreet() {
       return street;
    }

    /**
     * Methode um die Straße eines Kinos zu setzen
     * @param String street 
     */
    public void setStreet(String street) {
       this.street = street;
    }

    /**
     * Methode um die Hausnummer eines Kinos auszugeben
     * @return String streetNo
     */
    public String getStreetNo() {
       return streetNo;
    }

    /**
     * Methode um die Hausnummer eiens Kinos zu setzen
     * @param String streetNo 
     */
    public void setStreetNo(String streetNo) {
       this.streetNo = streetNo;
    }
    
    /**
	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
	 * @retun CinemaID
	 */
	public String toString() {
		return "CinemaID #C" + this.getId();
	}

}