package de.hdm.SoPra_WS1920.shared.bo;

import java.util.*;

/**
 * @author GianlucaBernert
 * Klasse group, welche das Attribut Name direkt enthält
 * und die die Beziehung zwischen Person und einer GRuppe von der Klasse Ownership erbt, 
 * diese wiederum erbt SerialVersionUID, die ID und den Erstellzeitpunkt von BusinessObject.
 */
public class Group extends Ownership {

    /**
     * Variable der Klasse Gruppe
     */
    private String name;
    
    /**
     * Konstruktor der Klasse Gruppe, welcher beim Aufruf dieser eine Instanz seiner selbst erzeugt
     */
    public Group() {
    }
    
    /**
     * Methode um den Namen einer Gruppe auszugeben
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Methode um den Namen einer Gruppe zu setzen
     * @param String name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
   	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
   	 * @return String GroupId + name
   	 */
   	public String toString() {
   		return "GroupID #G" + this.getId() + " " + this.getName();
   	}

}