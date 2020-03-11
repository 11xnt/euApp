import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import java.util.ArrayList;


public class EUDriverTest {
        private EUDriver euDriver;
        private PartyList partyListPopulated, partyListEmpty;
        private Party labourParty, fineGaelParty, fiannafailParty, sinnFeinParty, greenParty;
        private Mep mepOne, mepTwo, mepThree, mepFour, mepFive, ukmepOne, ukmepTwo;
        private  Country ireland, uk;

        @Before
        public void setup() {
           euDriver  = new EUDriver(1);

            labourParty = new Party("Labour", "Brendan Howlin", "left", 23);
            fineGaelParty = new Party("Fine Gael", "Leo Varadker", "right", 60);
            fiannafailParty = new Party("Fianna Fail", "Michael Martin", "centre", 45);
            sinnFeinParty = new Party("Sinn Fein", "Mary-Lou MacDonald", "left", 45);
            greenParty = new Party("Green Party", "Eamon Ryan", "left", 20);

            partyListPopulated = new PartyList();
            partyListPopulated.addParty(labourParty);
            partyListPopulated.addParty(fineGaelParty);
            partyListPopulated.addParty(fiannafailParty);
            partyListPopulated.addParty(sinnFeinParty);

            ireland = new Country("Ireland", 11);
            uk = new Country("United Kingdom", 71);
            //MEP Test Data
            mepOne = new Mep("Ciaran Cuffe", "ciaran.cuffe@ep.europa.eu", "232523", greenParty, partyListPopulated);
            mepTwo = new Mep("Frances Fitzgerald", "frances.fitzgerald@ep.europa.eu", "4323453", fineGaelParty,partyListPopulated);
            mepThree = new Mep("Matt Carthy", "matt.carthy@ep.europa.eu", "2325654", labourParty, partyListPopulated);
            mepFour = new Mep("Billy Kelleher", "billy.kelleher@ep.europa.eu", "874422", fiannafailParty, partyListPopulated);
            mepFive = new Mep("Sean Kelly", "sean.kelly@ep.europa.eu", "874322", fineGaelParty, partyListPopulated);
            //Setting up a Populated List

            euDriver.setPartyList(partyListPopulated);

            //Setting up an Empty List
            partyListEmpty = new PartyList();

        }
        @Test
        public void listMEPsbyPartyName() {

            ukmepOne = new Mep("Rory Palmer", "office@rorypalmer.org", "922 9717", labourParty, partyListPopulated);
            ukmepTwo = new Mep("Jackie Jones", "office@jackiejones.org", "922 9718", labourParty, partyListPopulated);
            //Party Test Data



            ireland.addMEP(mepOne);
            ireland.addMEP(mepTwo);
            ireland.addMEP(mepThree);
            ireland.addMEP(mepFour);
            ireland.addMEP(mepFive);
            uk = new Country("United Kingdom", 71);
            uk.addMEP(ukmepOne);
            uk.addMEP(ukmepTwo);

            ArrayList<Country> newCountries = new ArrayList<Country>();
            newCountries.add(ireland);
            newCountries.add(uk);
            euDriver.setEuCountries(newCountries);


            //listing an empty list
           assertNotNull(Utilities.validParty("Labour", partyListPopulated));
           assertTrue(euDriver.listMEPsbySpecificParty("anything").contains("There are no"));
           assertEquals(3, euDriver.noMEPSBySpecificParty("Labour"));
        }


    @Test
    public void setPartyList() {
        assertEquals(4, euDriver.getPartyList().numberOfParties());
        PartyList newPartyList = new PartyList();
        newPartyList.addParty(greenParty);
        newPartyList.addParty(fiannafailParty);

        euDriver.setPartyList(newPartyList);
        assertEquals(2, euDriver.getPartyList().numberOfParties());
    }

    @Test
    public void setEuCountries() {

        ArrayList<Country> euCountries = new ArrayList<Country>();

        euCountries.add(ireland);
        euCountries.add(uk);
        euDriver.setEuCountries(euCountries);
        assertEquals(2, euDriver.getEuCountries().size());
        euDriver.setEuCountries(new ArrayList<Country>());
        assertEquals(0, euDriver.getEuCountries().size());
        ArrayList<Country> newDawn = new ArrayList<Country>();
        newDawn.add(uk);
        euDriver.setEuCountries(newDawn);
        assertEquals(1, euDriver.getEuCountries().size());
        assertTrue(euDriver.listCountries().contains("United Kingdom"));
    }

    @Test
    public void findCountry() {
        ArrayList<Country> euCountries = new ArrayList<Country>();

        euCountries.add(ireland);
        euCountries.add(uk);
        euDriver.setEuCountries(euCountries);

        assertEquals("Ireland", euDriver.findCountry("Ireland").getName());
        assertNull(euDriver.findCountry("France"));
    }

    @Test
    public void listCountries() {
        ArrayList<Country> euCountries = new ArrayList<Country>();

        euCountries.add(ireland);
        euCountries.add(uk);
        euDriver.setEuCountries(euCountries);
        assertTrue(euDriver.listCountries().contains("Ireland"));
        assertTrue(euDriver.listCountries().contains("United Kingdom"));
        assertFalse(euDriver.listCountries().contains("France"));
    }

}
