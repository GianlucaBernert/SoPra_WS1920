package de.hdm.SoPra_WS1920.shared.bo;

import java.sql.Timestamp;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author GianlucaBernert
 * Klasse BusinessObject die als Superklasse aller anderen Klassen im .bo Package fungiert, mit den Attributen
 * SerialVersionUID, id und dem Erstellzeitpunkt und das Interface IsSerializable implementiert 
 */
public class BusinessObject implements IsSerializable{

    /**
     * Variablen der Klasse BusinessObject
     */
	private static final long SerialVersionUID = 1L;
    private int id;
    private Timestamp creationTimestamp;
    
    /**
     * Konstruktor der Klasse BusinessObject, welcher beim Aufruf dieser eine Instanz seiner selbst erzeugt
     */
    public BusinessObject() {
    }

    /**
     * Methode um die ID eines BusinessObjects auszugeben
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * Methode um die ID eines BusinessObjects zu setzen
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
        
    }
    
    /**
     * Methode um den Ersetellzeitpunkt eines BusinessObjects auszubegen
     * @return Timestamp creationTimestamp
     */
    public Timestamp getCreationTimestamp() {
    	return creationTimestamp;
    }
    
    /**
     * methode um den Erstellzeitpunkt eines BusinessObjects zu setzen
     * @param Timestamp creationTimestamp
     */
    public void setCreationTimestamp(Timestamp time) {
    	this.creationTimestamp = time;
    }
    
    /**
	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
	 * @return String id
	 */
	public String toString() {
		return "BOID #B" + this.getId();
	}
}