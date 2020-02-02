package de.hdm.SoPra_WS1920.shared.bo;

/**
 * @author GianlucaBernert
 * Klasse Vote, welche die Attribute votingWeight, surveyEntryFK und personFK direkt enthält
 * und die die Beziehung zwischen Person und einem Voting von der Klasse Ownership erbt, 
 * diese wiederum erbt SerialVersionUID, die ID und den Erstellzeitpunkt von BusinessObject.
 */
public class Vote extends Ownership {

    /**
     * Variablen der Klasse VOte
     */
    private int votingWeight;
    private int surveyEntryFK;
    

    /**
     * Konstruktor der Klasse Vote, welcher beim Aufruf dieser eine Instanz seiner selbst erzeugt
     */
    public Vote() {
    }

    /**
     * Methode um die Gewichtung eines Votings auszugeben
     * @return int votingWeight
     */
    public int getVotingWeight() {
        return votingWeight;
    }

    /**
     * Methode um die Gewichtung eines VOtings zu setzen
     * @param int votingWeight 
     */
    public void setVotingWeight(int votingWeight) {
       this.votingWeight = votingWeight;
    }



    /**
     * Methode um den surveyEntryFK eines Votings auszugeben
     * @return int surveyEntryFK
     */
    public int getSurveyEntryFK() {
        return surveyEntryFK;
    }

    /**
     * Methode um den surveyEntryFK eines Votings zu setzen
     * @param int surveyEntryFK 
     */
    public void setSurveyEntryFK(int surveyEntryFK) {
        this.surveyEntryFK = surveyEntryFK;
    }
    /**
   	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
   	 * @return String VoteID
   	 */
   	public String toString() {
   		return "VoteID #V" + this.getId();
   	}

}