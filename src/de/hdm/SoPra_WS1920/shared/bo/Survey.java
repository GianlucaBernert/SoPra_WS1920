package de.hdm.SoPra_WS1920.shared.bo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

/**
 * 
 */
public class Survey extends Ownership {

    /**
     * Variables of the class Group
     */
    private int groupFK;
    private Timestamp startDate;
    private Timestamp endDate;

    /**
     * Default constructor
     */
    public Survey() {
    }

    /**
     * @return group
     */
    public int getGroupFK() {
        return groupFK;
    }

    /** 
     * @return groupFK
     */
    public void setGroupFK(int groupFK) {
        this.groupFK = groupFK;
    }

    /**
     * @return
     */
    public Timestamp getEndDate() {
       return endDate;
    }

    /**
     * @param endDate 
     */
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    /**
   	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
   	 */
   	public String toString() {
   		return "SurveyID #S" + this.getId();
   	}

	/**
	 * @return the startDate
	 */
	public Timestamp getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
}