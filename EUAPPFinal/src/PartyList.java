import java.util.ArrayList;

/**
 * The responsibility for this class is to manage a single PartyList.
 */
public class PartyList {

    private ArrayList<Party> parties;

    public PartyList() {
        parties = new ArrayList<Party>();
    }

    public void addParty(Party party) {
        parties.add(party);
    }

    public boolean removeParty(int index) {
        if (Utilities.validIndex(index, parties)) {
            parties.remove(index);
            return true;
        } else {
            return false;
        }
    }

    public String listOfParties() {
        if (parties.size() == 0){
            return "No parties";
        }
        else{
            String listOfParties = "";
            for (int i = 0; i < parties.size(); i++){
                listOfParties = listOfParties + i + ": " + parties.get(i) + "\n";
            }
            return listOfParties;
        }
    }

    public int numberOfParties()
    {
        if (parties.size() != 0) {
            int numberOfParties = 0;
            for (int i = 0; i <= parties.size(); i++) {
                numberOfParties = numberOfParties + parties.size();
            }
            return numberOfParties;
        } else {
            return 0;
        }
    }


    //calculates the largest party with the most local representatives.
    public Party largestParty() {
        if (!parties.isEmpty()) {
            Party largestParty = parties.get(0);
            for (Party party : parties) {
                while (party.getNumLocalReps() > largestParty.getNumLocalReps())
                    largestParty = party;
            }
            return largestParty;
        }
        else {
            return null;
        }
    }


    //calculates the party with the most MEPs.
    public Party mostMEPs(ArrayList<Country> euCountries)
    {
        if(!parties.isEmpty())
        {
            Country mostMEPs = euCountries.get(0);
            for (Country country : euCountries)
            {
                if (country.listOfMEPsByParty() > mostMEPs.getMeps())
                    mostMEPs = party;
            }
            return party.
        }
    }

    public String listPartiesBySpecificGenre(String genre)
    {
        if (parties.size() == 0){
            return "No parties";
        }
        else
        {
            String listPartiesBySpecificGenre = "";
            for (int i = 0; i < parties.size(); i++)
            {
                if(getParty(i).getPartyGenre().equalsIgnoreCase(genre)) {
                    listPartiesBySpecificGenre = listPartiesBySpecificGenre + i + ": " + getParty(i) + "\n";
                }

            }
            return listPartiesBySpecificGenre;
        }
    }




                          /*********************
                           * GETTERS & SETTERS *
                           ********************
                           * @return*/

    public ArrayList<Party> getPartyList(){
        return parties;
    }

    public void setParties(Party party){
        this.parties = parties;
    }

    public Party getParty(int index)
    {
        if(!getPartyList().isEmpty()){
            return getPartyList().get(index);
        }
        else {
            return null;
        }
    }








    public String toString()
    {
        return "List of parties: " + parties;
    }









}