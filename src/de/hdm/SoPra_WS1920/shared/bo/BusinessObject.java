package de.hdm.SoPra_WS1920.shared.bo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author GianlucaBernert
 * superclass for all other BusinessObjects
 */
public class BusinessObject implements IsSerializable{

    /**
     * Variables of the class BuisnessObject
     */
	private static final long SerialVersionUID = 1L;
    private int id;
    private Timestamp creationTimestamp;
    
    /**
     * Default constructor
     */
    public BusinessObject() {
    }

    /**
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * @param id 
     * @return
     */
    public void setId(int id) {
        this.id = id;
        
    }
    
    /**
     * @return
     */
    public Timestamp getCreationTimestamp() {
    	return creationTimestamp;
    }
    
    /**
     * @param creationTimestamp
     * @return
     */
    public void setCreationTimestamp(Timestamp creationTimestamp) {
    	this.creationTimestamp = creationTimestamp;
    }
    
    /**
	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
	 */
	public String toString() {
		return "BOID #B" + this.getId();
	}
}