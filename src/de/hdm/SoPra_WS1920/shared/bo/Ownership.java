package de.hdm.SoPra_WS1920.shared.bo;

/**
 * @author GianlucaBernert
 * Klasse Ownership die als Superklasse einiger Klassen im .bo package fungiert,
 * Sie selbst hat lediglich die Variable personFK mit der die Besitz Beziehung 
 * eines Person zu einem der BusinessObjects dargestellt wird. Im weiteren erbt 
 * sie von der Klasse BusinessObject die SerialVersionUID, die id und den Erstellzeitpunkt
 */
public class Ownership extends BusinessObject {

    /**
     * Variablen der Klasse Ownership
     */
    private int personFK;
    
    /**
     * Konstruktor der Klasse Ownership, welcher beim Aufruf dieser eine Instanz seiner selbst erzeugt
     */
    public Ownership() {
    }
   
    /**
     * Methode um den personFK einer Ownership auszugeben
     * @return int personFK
     */
    public int getPersonFK() {
        return personFK;
    }

    /**
     * Methode um den personFK einer Ownership zu setzen
     * @param int PersonFK 
     */
    public void setPersonFK(int PersonFK) {
        this.personFK = PersonFK;
    }
    
    /**
   	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
   	 * @return String OwnershipId
   	 */
   	public String toString() {
   		return "OwnershipID #OS" + this.getId();
   	}

}