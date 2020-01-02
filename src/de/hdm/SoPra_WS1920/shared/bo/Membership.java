/**
 * 
 */
package de.hdm.SoPra_WS1920.shared.bo;

/**
 * @author GianlucaBernert
 * Klasse membership, welche die Attribute Person und Gruppe direkt enthält
 * und die Beziehung zwischen Person und einer Gruppe von der Klasse Ownership erbt, 
 * diese wiederum erbt SerialVersionUID, die ID und den Erstellzeitpunkt von BusinessObject.
 */
public class Membership extends Ownership{
	
	/**
	 * Variable der Klasse Membership
	 */
	private int pFK;
	private int gFK;
	
	/**
	 * Konstruktor der Klasse Gruppe, welche beim Aufruf dieser eine Instanz seiner selbst erzeugt
	 */
	public Membership() {
		
	}
	
	/**
	 * Methode um die Gruppe einer Membership zu setzen
	 * @param Group g
	 */
	public void setGroupFK(int gFK) {
		this.gFK = gFK;
	}
	
	/**
	 * Methode um die Gruppe einer Membership auszugeben
	 * @return Person p
	 */
	public int getGroupFK() {
		return gFK;
	}
	
	/**
	 * Methode um die Person einer Membership zu setzen
	 * @param Person p
	 */
	public void setPersonFK(int pFK) {
		this.pFK = pFK;
	}
	
	/**
	 * Methode um die Person einer Membership auszugeben
	 * @return Person p
	 */
	public int getPersonFK() {
		return pFK;
	}
	
	/**
	 * Methode um eine textuelle Darstellung des jeweiligen INstanz zu erzeugen
	 * @return
	 */
	public String toString() {
		return "MembershipID #Me" + this.getId() + " GroupFK " + this.getGroupFK() + " PersonFK " + this.getPersonFK();
	}

}
