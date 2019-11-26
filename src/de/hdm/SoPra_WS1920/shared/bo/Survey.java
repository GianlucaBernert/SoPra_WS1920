package de.hdm.SoPra_WS1920.shared.bo;

import java.sql.Date;
import java.util.*;

/**
 * 
 */
public class Survey extends Ownership {

    /**
     * Default constructor
     */
    public Survey() {
    }

    /**
     * 
     */
    private int groupFK;

    /**
     * 
     */
    private Date endDate;

    
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
    public Date getEndDate() {
       return endDate;
    }

    /**
     * @param endDate 
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}