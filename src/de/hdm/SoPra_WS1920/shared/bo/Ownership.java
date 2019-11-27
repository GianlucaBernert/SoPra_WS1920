package de.hdm.SoPra_WS1920.shared.bo;

/**
 * @author GianlucaBernert
 */
public class Ownership extends BusinessObject {

    /**
     * Variables of the class Group
     */
    private int personFK;
    
    /**
     * Default constructor
     */
    public Ownership() {
    }
   
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
        this.personFK = PersonFK;
    }
    
    /**
   	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
   	 */
   	public String toString() {
   		return "OwnershipID #OS" + this.getId();
   	}

}