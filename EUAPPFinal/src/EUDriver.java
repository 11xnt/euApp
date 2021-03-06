import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class EUDriver {

    private Scanner input = new Scanner(System.in);
    private ArrayList<Country> euCountries;
    private PartyList partyList;

    public EUDriver() {
        euCountries = new ArrayList<Country>();
        runMenu();
    }

    public EUDriver(int i) {
        euCountries = new ArrayList<Country>();
    }

    public static void main(String[] args) {
        new EUDriver();
    }

    private int mainMenu() {
        System.out.println("EU Menu");
        System.out.println("--------------");
        System.out.println("  1) Add a country to EU");
        System.out.println("  2) Remove a country from the EU");
        System.out.println("  3) Update an EU country's information");
        System.out.println("  4) List all the EU Countries");
        System.out.println("--------------");
        System.out.println("Country Menu");
        System.out.println("  5) Add an MEP");
        System.out.println("  6) Remove an MEP");
        System.out.println("  7) Update the information on an MEP");
        System.out.println("  8) List all MEP's in this country");
        System.out.println("--------------");
        System.out.println("Party Menu");
        System.out.println("  9) Add a Party");
        System.out.println("  10) Remove a Party");
        System.out.println("  11) Update the Party information");
        System.out.println("  12) List all parties");
        System.out.println("--------------");
        System.out.println("Report Menu");
        System.out.println("  13) Print all the parties in the EU");
        System.out.println("  14) Calculate and print the party with the most local Representatives");
        System.out.println("  15) Calculate and print the party with the most MEP's");
        System.out.println("  16) List all the parties of a given Genre");
        System.out.println("  17) List all MEPs of a given party");
        System.out.println("--------------");
        System.out.println("  20) Save to XML");
        System.out.println("  21) Load from XML");
        System.out.println("--------------");
        System.out.println("  0) Exit");
        return ScannerInput.readNextInt("==>>");
    }

    private void runMenu() {
        int option = mainMenu();
        while (option != 0) {
            switch (option) {
                case 1:
                    addCountry();
                    break;
                case 2:
                    deleteCountry();
                    break;
                case 3:
                    upDateCountry();
                    break;
                case 4:
                    listCountries();
                    break;
                case 5:
                    addMEP();
                    break;
                case 6:
                    deleteMEP();
                    break;
                case 7:
                    upDateMEP();
                    break;
                case 8:
                    listMEPsOfCountry();
                    break;
                case 9:
                    addParty();
                    break;
                case 10:
                    deleteParty();
                    break;
                case 11:
                    updateParty();
                    break;
                case 12:
                    partyList.listOfParties();
                    break;
                case 13:
                    printListOfParties();
                    break;
                case 14:
                    printLargestParty();
                    break;
                case 15:
                    printMostMEPs();
                    break;
                case 16:
                    listPartyByGenre();
                    break;
                case 17:
                    listMEPsByPartyName();
                    break;
                case 20:
                    try {
                        saveCountries();
                    } catch (Exception e) {
                        System.err.println("Error writing to the file: " + e);
                    }
                    break;
                case 21:
                    try {
                        loadCountries();
                    } catch (Exception e) {
                        System.err.println("Error reading from the file: " + e);
                    }
                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }
            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();

            //display the main menu again
            option = mainMenu();
        }
        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye");
        System.exit(0);

    }

    /***********
     * EU MENU *
     ***********/

    /**
     * @return adds the country with the Meps into the euCountries ArrayList.
     * done!
     */
    private void addCountry() {
        System.out.print("Enter the Country Name:    ");
        String name = input.nextLine();
        int noMEPs = ScannerInput.readNextInt("Enter the number of Meps: ");

        euCountries.add(new Country(name,
                noMEPs));
    }

    /**
     * deleteCountry deletes the country the user chooses.
     * done
     */
    private void deleteCountry() {
        System.out.println(listCountries());
        if (getEuCountries().size() >= 0) {
            int index = ScannerInput.readNextInt("Enter the index of the product to delete ==> ");

            if (Utilities.validIndex(index, euCountries)) {
                getEuCountries().remove(index);
                System.out.println("Country deleted.");
            } else {
                System.out.println("There is no country for this index number");
            }
        }
    }

    public String listCountries() {
        if (euCountries.size() == 0) {
            return "No Countries";
        } else {
            String listOfCountries = "";
            for (int i = 0; i < euCountries.size(); i++) {
                listOfCountries = listOfCountries + i + ": " + euCountries.get(i) + "\n";
            }
            return listOfCountries;
        }
    }


    private void upDateCountry() {
        //lists the Countries
        System.out.println(listCountries());

        if (euCountries.size() > 0) {
            //only ask the user to choose a party if products exist
            int index = ScannerInput.readNextInt("Enter the index of the Country to update ==> ");

            if ((index >= 0) && (index < euCountries.size())) {
                System.out.print("Enter the Country Name:    ");
                String name = input.nextLine();
                int noMEPs = ScannerInput.readNextInt("Enter the number of Meps: ");

                Country country = euCountries.get(index);
                country.setName(name);
                country.setNoMEPs(noMEPs);
            } else {
                System.out.println("There is no Party for this index number");
            }
        }
    }

    /****************
     * Country MENU *
     ****************/

    private void addMEP() {
        //lists the Countries
        System.out.println(listCountries());

        if (!euCountries.isEmpty()) {
            //only ask the user to choose a party if products exist
            int index = ScannerInput.readNextInt("Enter the index of the Country to update ==> ");

            if (Utilities.validIndex(index, euCountries)) {
                System.out.print("Enter the MEP's Name:    ");
                String mepName = input.nextLine();
                System.out.print("Enter the MEP's Email:   ");
                String mepEmail = input.nextLine();
                System.out.print("Enter the MEP's Phone number:    ");
                String mepPhone = input.nextLine();
                System.out.print("Enter the MEP's Party:    ");
                String mepParty = input.nextLine();
                Party tempParty = Utilities.validParty(mepParty, partyList);
                euCountries.get(index).addMEP(new Mep(mepName, mepEmail, mepPhone, tempParty, partyList));
                }
            }
        else
     {
        System.out.println("No Country for this index number");
        }

}

    private void deleteMEP() {
        //lists the Countries
        System.out.println(listCountries());
        if (getEuCountries().size() != 0) {
            //user picks a country where the Mep is located in
            int index = ScannerInput.readNextInt("Enter the index of the Country of where the MEP is from ==> ");
            //if that country exists, then a list of the Meps in that country become listed
            if ((index >= 0) && (index < euCountries.size())) {
                System.out.println(euCountries.get(index).getMeps());

                int i = ScannerInput.readNextInt("Enter the index of the MEP you want to delete ==> ");
                if ((i >= 0) && (i < euCountries.get(index).getMeps().size())) {
                    euCountries.get(index).removeMep(i);
                    System.out.println("Mep deleted.");
                }
            } else {
                System.out.println("There is no Mep in this Country for this index number");
            }
        } else {
            System.out.println("Please enter a valid Country");
        }


    }

    private void upDateMEP() {

        if (!getEuCountries().isEmpty()) {
            //list the Countries and ask the user to choose the Mep to edit
            System.out.println(listCountries());
            //user picks a country where the Mep is located in
            int index = ScannerInput.readNextInt("Enter the index of the Country of where the MEP is from ==> ");

            if ((index >= 0) && (index < euCountries.size())) {
                System.out.println(euCountries.get(index).getMeps());

                int i = ScannerInput.readNextInt("Enter the index of the MEP you want to delete ==> ");
                if ((i >= 0) && (i < euCountries.get(index).getMeps().size())) {
                    //if the index number exists in the ArrayList, gather the new details from the user
                    input.nextLine();
                    System.out.print("Enter the MEP's Name:    ");
                    String mepName = input.nextLine();
                    System.out.print("Enter the MEP's Email:   ");
                    String mepEmail = input.nextLine();
                    System.out.print("Enter the MEP's Phone number:    ");
                    String mepPhone = input.nextLine();
                    System.out.print("Enter the MEP's Party:    ");
                    //retrieve the MEP from the ArrayList and update the details with the user input
                    String mepParty = input.nextLine();
                    Mep mep = euCountries.get(index).getMep(index);
                    mep.setMEPName(mepName);
                    mep.setMEPEmail(mepEmail);
                    mep.setMEPPhone(mepPhone);
                    Party tempParty = Utilities.validParty(mepParty,partyList);
                    mep.setMEPParty(tempParty, partyList);

                    } else {
                        System.out.println("There is no MEP for this index number");
                    }
                }
            }
        }

    /**
     * Lets a user type in a country they wish to see the Meps in
     * @return the MEPs of that country the user typed in
     */
        private String listMEPsOfCountry() {
            if (euCountries.size() == 0) {
                return "No Countries";
            } else {
                System.out.print("Enter a Country of MEPs ==> ");
                String countryName = input.nextLine();

                String listMEPsOfCountry = "";
                for(int i = 0; i < euCountries.size(); i++){
                    if (!euCountries.get(i).getMeps().isEmpty()) {
                        listMEPsOfCountry = listMEPsOfCountry + i + ": " + euCountries.get(i).listOfMEPs() + "\n";
                    }
                }

                return listMEPsOfCountry;
                }
            }


        public Country findCountry(String string) {
            for (Country c : euCountries) {
                if (string.matches(c.getName()))
                    return c;
            }
            return null;
        }


    /**************
     * Party MENU *
     **************/

    private void addParty() {
        System.out.print("Enter the Party's Name:    ");
        String partyName = input.nextLine();
        System.out.print("Enter the Party's Leader:   ");
        String partyLeader = input.nextLine();
        int numLocalReps = ScannerInput.readNextInt("Enter the number of local representatives the party has:    ");
        System.out.print("Enter the Party's Genre:   ");
        String partyGenre = input.nextLine();
        if (Utilities.validGenre(partyGenre) == partyGenre) {
            partyList.addParty(new Party(partyName, partyLeader, partyGenre, numLocalReps));
        }
    }


    private void deleteParty(){
        //lists the currently stored parties in the partyList object.
        System.out.println(partyList.listOfParties());

        if (partyList.getPartyList().size() > 0) {
            //only ask the user to choose the party to delete if party exist
            //Ask the user to enter the index of the party they wish to delete
            int index = ScannerInput.readNextInt("Enter the index of the party to delete ==> ");

            if (Utilities.validIndex(index, partyList.getPartyList())) {
                //if the index is valid, delete the party at the given index
                partyList.getPartyList().remove(index);
                System.out.println("Party deleted.");
            } else {
                System.out.println("There is no party for this index number");
            }
        }
    }

    private void updateParty() {
        //lists the currently stored parties in the partyList object.
        System.out.println(partyList.listOfParties());

        if (partyList.getPartyList().size() > 0) {
            //only ask the user to choose a party if products exist
            int index = ScannerInput.readNextInt("Enter the index of the party to update ==> ");

            if ((index >= 0) && (index < partyList.getPartyList().size())) {
                //if the index is valid, gather new details for each field from the user
                input.nextLine();
                System.out.print("Enter the Party Name: ");
                String partyName = input.nextLine();
                System.out.print("Enter the Party Leader: ");
                String partyLeader = input.nextLine();
                System.out.print("Enter the Party Genre: ");
                String partyGenre = input.nextLine();
                int numLocalReps = ScannerInput.readNextInt("Enter the Number of Local Reps ");

                Party party = partyList.getPartyList().get(index);
                party.setPartyName(partyName);
                party.setPartyLeader(partyLeader);
                party.setPartyGenre(partyGenre);
                party.setNumLocalReps(numLocalReps);
            } else {
                System.out.println("There is no Party for this index number");
            }
        }
    }


    /******************
     * Reporting Menu *
     ******************/

    private void printListOfParties()
    {
        System.out.println("The Current list of Parties are: ");
        System.out.println(partyList.listOfParties());
    }

    private void printLargestParty()
    {
        System.out.println("The Largest Party with the most Local Representatives is: " + partyList.largestParty());
    }

    private void printMostMEPs()
    {
        System.out.println("The Largest Party with the most Meps is: " + partyList.mostMEPs(euCountries));
    }

    private void listPartyByGenre()
    {
        System.out.print("Enter the Genre:    ");
        String genre = input.nextLine();

        System.out.println(partyList.listPartiesBySpecificGenre(genre));
    }

    private void listMEPsByPartyName()
    {
        //invites the user to enter a party name
        System.out.print("Enter the Party Name:    ");
        String party = input.nextLine();

        System.out.println(listMEPsBySpecificParty(party));
    }

    //list all the MEPs in the specific party the user has chosen
    public String listMEPsBySpecificParty(String party)
    {
        if (!Utilities.validParty(party, partyList).equals(party)){
            return "Invalid Party";
        }
        else {
            String listOfMEPsByParty = "";
            for (int i = 0; i < partyList.getPartyList().size(); i++) {
                if (party.contains(listOfMEPsByParty())){
                    listOfMEPsByParty = listOfMEPsByParty + i + ": " + products.get(i) + "\n";
                }
            }
            return listOfMEPsByParty;
        }

    }

    /******************
     * Save/Load Menu *
     ******************/

    private void saveCountries() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("countries.xml"));
        out.writeObject(euCountries);
        out.close();
    }

    private void loadCountries() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("countries.xml"));
        euCountries = (ArrayList<Country>) is.readObject();
        is.close();
    }




        /*********************
         * GETTERS & SETTERS *
         *********************/

        public ArrayList<Country> getEuCountries() {
            return euCountries;
        }

        public void setEuCountries(ArrayList<Country> euCountries) {
            this.euCountries = euCountries;
        }

        public PartyList getPartyList() {
            return partyList;
            }

        public void setPartyList(PartyList partyList) {
            this.partyList = partyList;
        }





}