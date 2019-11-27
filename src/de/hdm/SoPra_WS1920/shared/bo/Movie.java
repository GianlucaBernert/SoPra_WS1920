package de.hdm.SoPra_WS1920.shared.bo;

import java.util.*;

/**
 * @author GianlucaBernert
 */
public class Movie extends Ownership {

    /**
     * Variables of the class Group
     */
    private String name;
    private String description;
    private String genre;

    /**
     * Default constructor
     */
    public Movie() {
    }
    
    /**
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return name
     */
    public String getName() {
       return name;
    }

    /**
     * @param description 
     */
    public void setDescription(String description) {
       this.description = description;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param genre 
     */
    public void setGenre(String genre) {
       this.genre = genre;
    }

    /**
     * @return
     */
    public String getGenre() {
        return genre;
    }
    
    /**
   	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
   	 */
   	public String toString() {
   		return "MovieID #M" + this.getId() + " " + this.getName();
   	}

}