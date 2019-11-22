package de.hdm.SoPra_WS1920.shared.bo;

import java.util.*;

/**
 * 
 */
public class Ownership extends BusinessObject {

    /**
     * Default constructor
     */
    public Ownership() {
    }

    /**
     * 
     */
    private int personFK;

   
    /**
     * @return personFK
     */
    public int getPersonFK() {
        return personFK;
    }

    /**
     * @param PersonFK 
     */
    public void setPersonFK(int PersonFK) {
        this.personFK = personFK;
    }

}