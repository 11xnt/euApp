public class Party {

    private String partyName;
    private String partyLeader;
    private String partyGenre;
    private int numLocalReps;


    /**
     * Constructor for objects of class Party
     * @param partyName     Name of the Party
     * @param partyLeader   Name of the Leader of the Party
     * @param partyGenre    Party genre
     * @param numLocalReps  Number of local reps of the party
     */
    public Party(String partyName, String partyLeader, String partyGenre, int numLocalReps)
    {
        this.partyName = partyName;
        this.partyLeader = partyLeader;
        this.partyGenre = partyGenre;
        this.numLocalReps = numLocalReps;
    }


    /**
     *
     * GETTERS & SETTERS
     *
     */

    // Returns the party name
    public String getPartyName() {
        return partyName;
    }

    // Updates the Party Name to the value passed as a parameter
    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    // Returns the Party Leader
    public String getPartyLeader() {
        return partyLeader;
    }

    // Updates the Party Leader to the value passed as a parameter
    public void setPartyLeader(String partyLeader) {
        this.partyLeader = partyLeader;
    }

    // Returns the Party Genre
    public String getPartyGenre() {
        return partyGenre;
    }

    // Updates the Party Genre to the value passed as a parameter
    public void setPartyGenre(String partyGenre) {
        this.partyGenre = partyGenre;
    }

    // Returns the Number of Local Reps as an int
    public int getNumLocalReps() {
        return numLocalReps;
    }

    // Updates the Number of Local Reps to the value passed as a parameter
    public void setNumLocalReps(int numLocalReps) {
        this.numLocalReps = numLocalReps;
    }


    /**
     * Builds a String representing a user friendly representation of the MEP state
     * @return Details of the Party
     */

    public String toString()
    {
        return "Party Name: " + partyName
                + ", Party Leader: " + partyLeader
                + ", Party Genre: " + partyGenre
                + ", Number of Local Reps: " + numLocalReps;
    }

}
