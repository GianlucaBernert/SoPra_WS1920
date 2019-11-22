package de.hdm.SoPra_WS1920.shared.bo;

import java.util.*;

/**
 * 
 */
public class Movie extends Ownership {

    /**
     * Default constructor
     */
    public Movie() {
    }

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private String genre;

   
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

}