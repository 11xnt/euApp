import org.junit.After;
        import org.junit.Before;
        import org.junit.Test;

        import java.util.ArrayList;

        import static org.hamcrest.CoreMatchers.instanceOf;
        import static org.hamcrest.CoreMatchers.is;
        import static org.junit.Assert.*;

public class PartyListTest {
    private EUDriver euDriver;
    private PartyList partyListPopulated, partyListEmpty;
    private Party labourParty, fineGaelParty, fiannafailParty, sinnFeinParty, greenParty;
    private Mep mepOne, mepTwo, mepThree, mepFour, mepFive, ukmepOne, ukmepTwo;
    private  Country ireland, uk;
    @Before
    public void setup() {
        labourParty = new Party("Labour", "Brendan Howlin", "left", 23);
        fineGaelParty = new Party("Fine Gael", "Leo Varadker", "right", 60);
        fiannafailParty = new Party("Fianna Fail", "Michael Martin", "centre", 45);
        sinnFeinParty = new Party("Sinn Fein", "Mary-Lou MacDonald", "left", 45);
        greenParty = new Party("Green Party", "Eamon Ryan", "left", 20);
        //MEP Test Data


        //Setting up a Populated List
        partyListPopulated = new PartyList();
        partyListPopulated.addParty(labourParty);
        partyListPopulated.addParty(fineGaelParty);
        partyListPopulated.addParty(fiannafailParty);
        partyListPopulated.addParty(sinnFeinParty);

        mepOne = new Mep("Ciaran Cuffe", "ciaran.cuffe@ep.europa.eu", "232523", greenParty,partyListPopulated);
        mepTwo = new Mep("Frances Fitzgerald", "frances.fitzgerald@ep.europa.eu", "4323453", fineGaelParty,partyListPopulated);
        mepThree = new Mep("Matt Carthy", "matt.carthy@ep.europa.eu", "2325654", sinnFeinParty,partyListPopulated);
        mepFour = new Mep("Billy Kelleher", "billy.kelleher@ep.europa.eu", "874422", fiannafailParty,partyListPopulated);
        mepFive = new Mep("Sean Kelly", "sean.kelly@ep.europa.eu", "874322", fineGaelParty,partyListPopulated);
        //Setting up an Empty List
        partyListEmpty = new PartyList();

        ireland = new Country("Ireland", 11);
        uk = new Country("United Kingdom", 71);

        euDriver = new EUDriver(1);
    }
    @Test
    public void getPartyList() {
        assertEquals(4, partyListPopulated.getPartyList().size());
        assertEquals(labourParty, partyListPopulated.getParty(0));
    }

    @After
    public void teardown() {
        partyListPopulated = partyListEmpty = null;
        labourParty = fineGaelParty = fiannafailParty = sinnFeinParty = greenParty = null;
        mepOne = mepTwo = mepThree = mepFour = null;
    }

    @Test
    public void addParty() {
        //adding to populated list
        assertEquals(4, partyListPopulated.numberOfParties());
        partyListPopulated.addParty(labourParty);
        assertEquals(partyListPopulated.getParty(4), labourParty);
        assertEquals(5, partyListPopulated.numberOfParties());

        //adding to empty list
        assertEquals(0, partyListEmpty.numberOfParties());
        partyListEmpty.addParty(labourParty);
        assertEquals(partyListEmpty.getParty(0), labourParty);
        assertEquals(1, partyListEmpty.numberOfParties());
    }

    @Test
    public void getPartyVerifyIndexes() {
        //retrieving an existing Party from populated list
        assertEquals(4, partyListPopulated.numberOfParties());
        assertThat(partyListPopulated.getParty(2), is(instanceOf(Party.class)));
        assertThat(partyListPopulated.getParty(2).getPartyName(), is("Fianna Fail"));

        //retrieving a non-existant MEP from populated list
        assertNull(partyListPopulated.getParty(4));
        assertNull(partyListPopulated.getParty(-1));

        //retrieving from empty list
        assertEquals(4, partyListPopulated.getPartyList().size());

    }

    @Test
    public void removePartyVerifyIndexes() {
        //removing an existing Party from populated list
        assertEquals(4, partyListPopulated.numberOfParties());
        partyListPopulated.removeParty(2);
        assertEquals(3, partyListPopulated.numberOfParties());

        //removing non-existent Party from populated list
        partyListPopulated.removeParty(3);
        assertEquals(3, partyListPopulated.numberOfParties());
        partyListPopulated.removeParty(-1);
        assertEquals(3, partyListPopulated.numberOfParties());

        //attempting to remove party from empty list
        partyListEmpty.removeParty(0);
        assertEquals(0, partyListEmpty.numberOfParties());
    }

    @Test
    public void listOfMEPs() {
        //listing an empty list
        assertThat(partyListEmpty.listOfParties().contains("no parties in the list"), is(true));

        //listing a populated list
        assertTrue(partyListPopulated.listOfParties().contains("Brendan Howlin"));
        assertTrue(partyListPopulated.listOfParties().contains("Leo Varadker"));
        assertTrue(partyListPopulated.listOfParties().contains("Michael Martin"));
        assertTrue(partyListPopulated.listOfParties().contains("Mary-Lou MacDonald"));
        assertTrue(partyListPopulated.listOfParties().contains("0:"));
        assertTrue(partyListPopulated.listOfParties().contains("1:"));
        assertTrue(partyListPopulated.listOfParties().contains("2:"));
        assertTrue(partyListPopulated.listOfParties().contains("3:"));
    }

    @Test
    public void listPartiesBySpecificGenre() {
        //listing an empty list
        assertTrue(partyListEmpty.listPartiesBySpecificGenre("anything").contains("no parties"));

        //listing a populated list with an existing genre
        assertTrue(partyListPopulated.listPartiesBySpecificGenre("right").contains("Leo Varadker"));
        assertTrue(partyListPopulated.listPartiesBySpecificGenre("Left").contains("Mary-Lou MacDonald"));

        //listing a populated list that has no parties for the selected genre
        assertTrue(partyListPopulated.listPartiesBySpecificGenre("EXTREME RIGHT").contains("no parties with the genre"));
        assertTrue(partyListPopulated.listPartiesBySpecificGenre("EXTREME LEFT").contains("no parties with the genre"));
    }

    @Test
    public void largestParty() {
        //over an empty list
        assertNull(partyListEmpty.largestParty());

        //over a populated list
        assertThat(partyListPopulated.largestParty().getPartyName(), is("Fine Gael"));
    }

    @Test
    public void mostMEPs() {

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

        assertEquals("Labour", partyListPopulated.mostMEPs(newCountries).getPartyName());
        newCountries.get(1).addMEP(mepTwo);
        newCountries.get(1).addMEP(mepFive);
        assertEquals("Fine Gael", partyListPopulated.mostMEPs(newCountries).getPartyName());



    }
}