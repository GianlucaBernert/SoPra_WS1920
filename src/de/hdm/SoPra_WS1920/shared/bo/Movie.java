package de.hdm.SoPra_WS1920.shared.bo;

/**
 * @author GianlucaBernert
 * Klasse Movie, welche die Attribute Name, Beschreibung und Genre direkt enthält 
 * und die SerialVersionUID, die ID und den Erstellzeitpunkt von BusinessObject vererbt bekommt
 */
public class Movie extends BusinessObject {

    /**
     * Variablen der Klasse Movie 
     */
    private String name;
    private String description;
    private String genre;

    /**
     * Konstruktor der Klasse Movie, welcher beim Aufruf dieser eine Instanz seiner selbst erzeugt
     */
    public Movie() {
    }
    
    /**
     * Methode um den Namen eines Films zu setzen
     * @param String name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Methode um den Namen eines FIlms auszugeben
     * @return String name
     */
    public String getName() {
       return name;
    }

    /**
     * Methode um die Beschreibung eines Films zu setzen
     * @param String description 
     */
    public void setDescription(String description) {
       this.description = description;
    }

    /**
     * Methode um die Beschreibung eines FIlms auszugeben
     * @return String description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Methode um das Genre eines Films zu setzen
     * @param String genre 
     */
    public void setGenre(String genre) {
       this.genre = genre;
    }

    /**
     * Methode um das Genre eines FIlms auszugeben
     * @return String genre
     */
    public String getGenre() {
        return genre;
    }
    
    /**
   	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
   	 * @return String MovieId + Name
   	 */
   	public String toString() {
   		return "MovieID #M" + this.getId() + " " + this.getName();
   	}
   	
   	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Movie) {
			Movie m = (Movie) obj;
			if((this.getId()== m.getId())){				
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