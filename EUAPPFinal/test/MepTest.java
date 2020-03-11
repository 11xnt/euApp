import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MepTest {

    private Mep mepOne, mepTwo, mepThree;
    private PartyList partyListPopulated, partyListEmpty;
    private Party labourParty, fineGaelParty, fiannafailParty, sinnFeinParty, greenParty;
    @Before
    public void setUp() throws Exception {

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
        //name on boundary 30 chars, valid email, valid phone (leading zero)
        mepOne = new Mep("012345678901234567890123456789",
                "mep1@eu.com",
                "01234567", labourParty, partyListPopulated);

        partyListEmpty = new PartyList();
    }

    @After
    public void tearDown() throws Exception {
        mepOne = mepTwo = mepThree = null;
    }

    @Test
    public void testPartyList() {
        assertNotNull(partyListPopulated);
        assertEquals(4, partyListPopulated.getPartyList().size());
    }
    @Test
    public void validDataInConstructorAccepted() {
        //name on boundary 30 chars, valid email, valid phone (leading zero)
        assertEquals("012345678901234567890123456789", mepOne.getMEPName());
        assertEquals("mep1@eu.com", mepOne.getMEPEmail());
        assertEquals("01234567", mepOne.getMEPPhone());
        assertEquals(labourParty, Utilities.validParty(mepOne.getMEPParty().getPartyName(), partyListPopulated));

        //name under boundary (29 chars), valid email, valid phone (trailing zero)
        mepTwo = new Mep("01234567890123456789012345678",
                "mep2@eu.ie",
                "12345670", labourParty, partyListPopulated);
        assertEquals("01234567890123456789012345678", mepTwo.getMEPName());
        assertEquals("mep2@eu.ie", mepTwo.getMEPEmail());
        assertEquals("12345670", mepTwo.getMEPPhone());
        assertEquals(labourParty, Utilities.validParty(mepOne.getMEPParty().getPartyName(), partyListPopulated));

    }

    @Test
    public void inValidDataInConstructorDefaultsAssigned() {
        //name (0 chars), invalid email (missing .), invalid phone (contains chars)
        mepTwo = new Mep("",
                "mep2eu.ie",
                "PH:12345670", greenParty, partyListPopulated);
        assertEquals("", mepTwo.getMEPName());
        assertEquals("invalid format email", mepTwo.getMEPEmail().toLowerCase());
        assertEquals("unknown", mepTwo.getMEPPhone().toLowerCase());
       // System.out.println(mepTwo.getMEPParty().getPartyName());
        assertNull( mepTwo.getMEPParty());

        //name over boundary 31 chars, invalid email (missing @), invalid phone (contains -)
        mepThree = new Mep("0123456789012345678901234567890",
                "mep1eu@com",
                "01-234567", labourParty, partyListPopulated);
        assertEquals("012345678901234567890123456789", mepThree.getMEPName());
        assertEquals("invalid format email", mepThree.getMEPEmail().toLowerCase());
        assertEquals("unknown", mepThree.getMEPPhone().toLowerCase());
    }

    @Test
    public void setMEPName() {
        //name on boundary 30 chars, valid email, valid phone (leading zero)
        assertEquals("012345678901234567890123456789", mepOne.getMEPName());

        mepOne.setMEPName("01234567890123456789012345678");   //29 chars
        assertEquals("01234567890123456789012345678", mepOne.getMEPName());

        mepOne.setMEPName("012345678901234567890123456789");  //30 chars
        assertEquals("012345678901234567890123456789", mepOne.getMEPName());

        mepOne.setMEPName("0123456789012345678901234567890"); //31 chars
        assertEquals("012345678901234567890123456789", mepOne.getMEPName());
    }

    @Test
    public void setMEPEmail() {
        //name on boundary 30 chars, valid email, valid phone (leading zero)
        assertEquals("mep1@eu.com", mepOne.getMEPEmail());

        //no @
        mepOne.setMEPEmail("mep1eu.com");
        assertEquals("mep1@eu.com", mepOne.getMEPEmail());

        //no .
        mepOne.setMEPEmail("mep1@eu.com");
        assertEquals("mep1@eu.com", mepOne.getMEPEmail());

        //valid
        mepOne.setMEPEmail("mep1@eu.com");
        assertEquals("mep1@eu.com", mepOne.getMEPEmail());
    }

    @Test
    public void setMEPPhone() {
        //name on boundary 30 chars, valid email, valid phone (leading zero)
        assertEquals("01234567", mepOne.getMEPPhone());
        //contains -
        mepOne.setMEPPhone("01-234567");
        assertEquals("01234567", mepOne.getMEPPhone());

        //contains chars
        mepOne.setMEPPhone("PH: 01234567");
        assertEquals("01234567", mepOne.getMEPPhone());

        //valid
        mepOne.setMEPPhone("019122347");
        assertEquals("019122347", mepOne.getMEPPhone());
    }
    @Test
    public void setMEPParty() {
        assertEquals(labourParty, mepOne.getMEPParty());
        mepOne.setMEPParty("Fianna Fail", partyListPopulated );
        assertEquals(fiannafailParty, mepOne.getMEPParty());
        mepOne.setMEPParty("Green Party", partyListPopulated);
        assertEquals(fiannafailParty, mepOne.getMEPParty());           //no change - Green PArty not added to partyList
        mepOne.setMEPParty("no party at all", partyListEmpty);    //test off empty list
        assertEquals(fiannafailParty, mepOne.getMEPParty());

    }

    @Test
    public void toStringUsesAllFields() {
        assertThat(mepOne.toString().contains("012345678901234567890123456789"), is(true));
        assertThat(mepOne.toString().contains("mep1@eu.com"), is(true));
        assertThat(mepOne.toString().contains("01234567"), is(true));
    }
}