package de.hdm.SoPra_WS1920.shared.bo;

/**
 * @author Yesin Soufi
 * Klasse cinema, welche die Attribute Name
 * und Klasse Ownership erbt
 */

public class CinemaChain extends Ownership{
	
	/**
     * Variablen der Klasse Cinema
     */
	private String name;
	
	/**
     * Konstruktor der Klasse CinemaChain, welcher beim Aufruf dieser eine Instanz seiner selbst erzeugt
     */
	public CinemaChain() {
		
	}
	
	/**
     * Methode um den Namen eines Kinokette zu setzen
     *@return String name
     */

	public String getName() {
		return name;
	}
	
	/**
     * Methode um den Namen einer Kinokette auszugeben
     * @param String name 
     */

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
	 * @retun CinemaChainID
	 */
	
	public String toString() {
   		return "CinemaChainID #CC" + this.getId() + " " + this.getName();
   	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CinemaChain) {
			CinemaChain cH= (CinemaChain) obj;
			if((this.getId()== cH.getId())){				
				return true;	
			}
			else {
				return false;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = this.getId();
		return result;
	}

}
