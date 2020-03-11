import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CountryTest {
    private Mep mepOne, mepTwo, mepThree;
    private PartyList partyListPopulated;
    private Party labourParty, fineGaelParty, fiannafailParty, sinnFeinParty, greenParty;
    private Country validData1, validData2,   inValidData1,   inValidData2,  ireland, uk;
    @Before
    public void setUp() throws Exception {

        ireland = new Country("Ireland", 11);
        validData1 = new Country("012345678901234567890123456789", 10);    //012345678901234567890123456789-123
        validData2 = new Country("", 0);

        inValidData1 = new Country("012345678901234567890123456789-1234", 10);
        inValidData2 = new Country("012345678901234567890123456789", -1);

        labourParty = new Party("Labour", "Brendan Howlin", "left", 23);
        fineGaelParty = new Party("Fine Gael", "Leo Varadker", "right", 60);
        fiannafailParty = new Party("Fianna Fail", "Michael Martin", "centre", 45);
        sinnFeinParty = new Party("Sinn Fein", "Mary-Lou MacDonald", "left", 45);
        greenParty = new Party("Green Party", "Eamon Ryan", "left", 20);

        //Setting up a Populated List

        partyListPopulated = new PartyList();
        partyListPopulated.addParty(labourParty);
        partyListPopulated.addParty(fineGaelParty);
        partyListPopulated.addParty(fiannafailParty);
        partyListPopulated.addParty(sinnFeinParty);

        mepOne = new Mep("Ciaran Cuffe", "ciaran.cuffe@ep.europa.eu", "232523", greenParty,partyListPopulated);
        mepTwo = new Mep("Frances Fitzgerald", "frances.fitzgerald@ep.europa.eu", "4323453", fineGaelParty,partyListPopulated);
        mepThree = new Mep("Sean Kelly", "sean.kelly@ep.europa.eu", "874322", fineGaelParty, partyListPopulated);

    }

    @After
    public void tearDown() throws Exception {
        ireland = validData1 = validData2 = null;
    }
    @Test
    public void validDataInConstructorAccepted() {
        assertEquals("012345678901234567890123456789", validData1.getName());
        assertEquals(10, validData1.getNoMEPs());
        // at lower boundary
        assertEquals("", validData2.getName());
        assertEquals(0, validData2.getNoMEPs());
        assertNotNull(validData1.getMeps());
    }
    @Test
    public void inValidDataInConstructorDefaultsAssigned() {
        assertEquals("012345678901234567890123456789", inValidData1.getName());  //name >30
        assertEquals(10, inValidData1.getNoMEPs());  //valid
        assertEquals("012345678901234567890123456789", inValidData2.getName());  //name valid
        assertEquals(0, inValidData2.getNoMEPs());  //neg value


    }
    @Test
   public void addMEP() {
        assertEquals(0, ireland.getMeps().size());
        ireland.addMEP(mepOne);
       assertEquals(1, ireland.getMeps().size());
        assertEquals("Ciaran Cuffe", ireland.getMeps().get(0).getMEPName());
        assertNull(ireland.getMeps().get(0).getMEPParty());
        ireland.addMEP(mepTwo);
        assertEquals(2, ireland.getMeps().size());


       assertEquals(fineGaelParty, ireland.getMeps().get(1).getMEPParty());
       //testing max number of meps
       uk = new Country("United Kingdom", 0);
       uk.addMEP(mepOne);
       assertEquals(0, uk.getMeps().size());
       uk.setNoMEPs(1);
       uk.addMEP(mepOne);
       uk.addMEP(mepTwo);
       assertEquals(1, uk.getMeps().size());
       assertEquals( "Ciaran Cuffe" , ireland.getMEP(0).getMEPName());

    }

    @Test
    public void removeMEP() {
        ireland.addMEP((mepOne));
        ireland.addMEP((mepTwo));
        ireland.addMEP((mepThree));
        assertEquals(3, ireland.numberOfMEPs());
        assertEquals( "Sean Kelly" , ireland.getMEP(2).getMEPName());
        ireland.removeMEP(2);
        assertEquals(2, ireland.numberOfMEPs());
        assertEquals( "Frances Fitzgerald" , ireland.getMEP(1).getMEPName());

        ireland.removeMEP(-1);
        assertEquals(2, ireland.numberOfMEPs());
        ireland.removeMEP(2);
        assertEquals(2, ireland.numberOfMEPs());

    }

    @Test
    public void listOfMEPs() {
        ireland.addMEP((mepOne));
        ireland.addMEP((mepTwo));
        ireland.addMEP((mepThree));
        assertEquals(3, ireland.numberOfMEPs());
        assertTrue(ireland.listOfMEPs().contains("Ciaran Cuffe"));
        assertTrue(ireland.listOfMEPs().contains("Frances Fitzgerald"));
        assertTrue(ireland.listOfMEPs().contains("Sean Kelly"));

    }

    @Test
    public void listOfMEPsByParty() {
        ireland.addMEP(mepTwo);
        ireland.addMEP(mepTwo);
        assertEquals(2, ireland.noOfMEPsByParty(fineGaelParty));
        assertTrue(ireland.listOfMEPsByParty(fineGaelParty).contains("Frances"));
        ireland.addMEP(mepThree);
        assertEquals(3, ireland.noOfMEPsByParty(fineGaelParty));
        assertTrue(ireland.listOfMEPsByParty(fineGaelParty).contains("Sean"));
        ireland.addMEP(mepOne);
        assertEquals(3, ireland.noOfMEPsByParty(fineGaelParty));
        assertEquals(0, ireland.noOfMEPsByParty(greenParty));

    }

    @Test
    public void setNoMEPs() {
        assertEquals(10, validData1.getNoMEPs());

        validData1.setNoMEPs(20);
        assertEquals(20, validData1.getNoMEPs());

        validData1.setNoMEPs(-1);
        assertEquals(20, validData1.getNoMEPs());

    }


    @Test
    public void testSetName() {
        assertEquals("012345678901234567890123456789", validData1.getName());

        validData1.setName("new name");
        assertEquals("new name", validData1.getName());

        validData1.setName("012345678901234567890123456789-1234");
        assertEquals("new name", validData1.getName());
    }

    @Test
    public void setMeps() {
        ireland.addMEP(mepOne);
        ireland.addMEP(mepOne);

        assertEquals(2, ireland.numberOfMEPs());
        ArrayList<Mep> meps = new ArrayList<Mep>();
        meps.add(mepTwo);
        meps.add(mepThree);
        meps.add(mepTwo);

        ireland.setMeps(meps);
        assertEquals(3, ireland.numberOfMEPs());
        assertEquals(mepTwo, ireland.getMeps().get(0));
        assertEquals(mepThree, ireland.getMeps().get(1));
        assertEquals(mepTwo, ireland.getMeps().get(2));

        ireland.setNoMEPs(3);
        assertEquals(3, ireland.getNoMEPs());
        meps.add( new Mep("Mairead McGuinness", "mairead@eu.eu","123", fineGaelParty, partyListPopulated ));
        assertEquals(3, ireland.getNoMEPs());
    }

    @Test
    public void testSetNoMEPs() {
        assertEquals(11, ireland.getNoMEPs());
        ireland.setNoMEPs(20);
        assertEquals(20, ireland.getNoMEPs());
        ireland.setNoMEPs(-1);
        assertEquals(20, ireland.getNoMEPs());

    }
}