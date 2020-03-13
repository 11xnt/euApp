import java.util.ArrayList;

public class Country {

    private String name;
    private ArrayList<Mep> meps;
    private int noMEPs;

    public Country(String name, int noMEPs) {
        this.name = name;
        this.noMEPs = noMEPs;
        meps = new ArrayList<Mep>();
    }




    public void addMEP(Mep mep){
        meps.add(mep);
    }

    public boolean removeMep(int i)
    {
        if (Utilities.validIndex(i, meps)) {
            return true;
        } else {
            return false;
        }
    }

    public int numberOfMEPs()
    {
        if(meps.size() != 0){
            int numberOfMeps = 0;
            for (int i = 0; i < meps.size(); i++){
                numberOfMeps = numberOfMeps + meps.size();
            }
            return numberOfMeps;
        }
        else{
            return 0;
        }
    }

    public String listOfMEPs()
    {
        if (getName().isEmpty()){
            return "No MEPs are currently in this Country.";
        }
        else{
            String listOfMeps = "";
            for (int i = 0; i < meps.size(); i++){
                listOfMeps = listOfMeps + i + ": " + meps.get(i) + "\n";
            }
            return listOfMeps;
        }
    }

    /**
     *
     * @param party
     * @return The list of Meps currently added to this country that are members of the supplied party.
     */
    public String listOfMEPsByParty(Party party)
    {
        //checks if the size of Meps of the country are empty.
        if (meps.size() == 0){
            return "No MEPs are currently in this party within this Country.";
        }
        else{
            String listOfMepsByParty = "";
            for (int i = 0; i < meps.size(); i++){
                listOfMepsByParty = listOfMepsByParty + i + ": " + meps.get(i) + "\n";
            }
            return listOfMepsByParty;
        }
    }

    public int noOfMEPsByParty(Party party)
    {
        //checks if the size of Meps of the country are empty.
        if (meps.size() == 0){
            return 0;
        }
        else{
            for (int i = 0; i < meps.size(); i++){
                noOfMEPsByParty = noOfMEPsByParty + i;
            }
            return noOfMEPsByParty;
        }
    }











    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public ArrayList<Mep> getMeps() {
        return meps;
    }

    public void setMeps(ArrayList<Mep> mep) {
        this.meps = meps;
    }

    public Mep getMep(int i) {
        return meps.get(i);
    }

    public void setMep(ArrayList<Mep> mep){
        this.meps = mep;
    }

    public int getNoMeps(){
        return noMeps;
    }

    public void setNoMeps(int i){
        this.noMeps = noMeps;
    }






    /**
     *
     * toString to return the Country's relative data in the console.
     *
     */
    public String toString()
    {
        return "Country Name: " + name
                + ", Meps : " + meps
                + ", Max number of Meps: " + noMeps;
    }



}
