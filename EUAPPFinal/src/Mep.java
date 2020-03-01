
public class Mep {

    private String mepName;
    private String mepEmail;
    private String mepPhone;
    private Party mepParty;

    /**
     * Constructor for objects of class MEP
     *
     * @param mepName  Name of the MEP
     * @param mepEmail Email of the MEP
     * @param mepPhone Phone # of the MEP
     * @param mepParty Party the MEP belongs to
     */


    public Mep(String mepName, String mepEmail, String mepPhone, Party mepParty, PartyList partyList) {
        this.mepName = mepName;
        this.mepEmail = mepEmail;
        this.mepPhone = mepPhone;
        this.mepParty = mepParty;
    }


    /**
     * GETTERS & SETTERS
     */
    // Returns the MEP Name
    public String getMepName() {
        return mepName;
    }

    // Updates the MEP Name to the value passed as a parameter
    public void setMepName(String mepName) {
        this.mepName = mepName;
    }

    // Returns the MEP's Email
    public String getMepEmail() {
        return mepEmail;
    }

    // Updates the MEP's Email to the value passed as a parameter
    // !!Must contain an @ and .!!
    public void setMepEmail(String mepEmail) {
        this.mepEmail = mepEmail;
    }

    // Returns the MEP's Phone
    public String getMepPhone() {
        return mepPhone;
    }

    // Updates the MEP's Phone to the value passed as a parameter
    // !!Must contain only numbers!!
    public void setMepPhone(String mepPhone) {
        this.mepPhone = mepPhone;
    }

    // Returns the MEP's Party
    public Party getMepParty() {
        return mepParty;
    }

    // Updates the MEP's Party to the value passed as a parameter
    public void setMepParty(Party mepParty, PartyList partyList) {
        this.mepParty = mepParty;
    }

    /**
     * Builds a String representing a user friendly representation of the MEP state
     * @return Details of the MEP
     */
    public String toString()
    {
        return "Mep Name: " + mepName
                + ", Mep Email: " + mepEmail
                + ", Mep Phone Number: " + mepPhone
                + ", Mep's Current Party: " + mepParty;
    }
}
