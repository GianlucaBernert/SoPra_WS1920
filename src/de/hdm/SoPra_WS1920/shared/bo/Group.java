package de.hdm.SoPra_WS1920.shared.bo;

import java.util.*;

/**
 * @author GianlucaBernert
 */
public class Group extends Ownership {

    /**
     * Variables of the class Group
     */
    private String name;
    
    /**
     * Default constructor
     */
    public Group() {
    }
    
    /**
     * @return name
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
   	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
   	 */
   	public String toString() {
   		return "GroupID #G" + this.getId() + " " + this.getName();
   	}

}