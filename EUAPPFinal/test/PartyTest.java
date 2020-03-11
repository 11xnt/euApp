import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PartyTest {

    private Party  partyOne, partyTwo;



    @Before
    public void setUp() throws Exception {

        //name on boundary 30 chars, valid genre, valid song length, valid MEP
        //Genre must be:  "RIGHT", "LEFT", "EXTREME RIGHT", "EXTREME LEFT", "CENTRE", or "UNCATEGORISED"
       partyOne = new Party("012345678901234567890123456789-123",
                "012345678901234567890123456789-123", "riGHT", 163);
    }

    @After
    public void tearDown() throws Exception {
        partyOne = partyTwo = null;
    }

    @Test
    public void validDataInConstructorAccepted() {
        //name on boundary 30 chars, valid genre, number of MEPs
        //Genre must be: "RIGHT", "LEFT", "EXTREME RIGHT", "EXTREME LEFT", "CENTRE", or "UNCATEGORISED"
        assertEquals("012345678901234567890123456789", partyOne.getPartyName());
        assertEquals("RIGHT", partyOne.getPartyGenre());


        //valid genre, non-negative number of reps
        partyTwo = new Party("01234567890123456789012345678", "partyleader", "cenTRE",  10);
        assertEquals("CENTRE", partyTwo.getPartyGenre());
        assertEquals(10, partyTwo.getNumLocalReps());

        //valid genre, neg no of reps
        partyTwo = new Party("01234567890123456789012345678",
                "partyleader", "extreme right", -1);
        assertEquals("EXTREME RIGHT", partyTwo.getPartyGenre());
        assertEquals(0, partyTwo.getNumLocalReps());

        //valid genre, valid num reps
        partyTwo = new Party("01234567890123456789012345678",
                "partyleader", "left", 1199);
        assertEquals("LEFT", partyTwo.getPartyGenre());
        assertEquals(1199,  partyTwo.getNumLocalReps());



        //valid genre
        partyTwo = new Party("01234567890123456789012345678",
                "partyleader", "extreme left", 1200);
        assertEquals("EXTREME LEFT", partyTwo.getPartyGenre());
        assertEquals(1200, partyTwo.getNumLocalReps());
    }

    @Test
    public void inValidDataInConstructorDefaultsAssigned() {
        //name (0 chars), invalid genre, invalid  local reps
        partyTwo = new Party("",
                "", "Jazz",
                -1);
        assertEquals("", partyTwo.getPartyName());
        assertEquals("unknown", partyTwo.getPartyGenre().toLowerCase());
        assertEquals(0, partyTwo.getNumLocalReps());
      
        //name (31 chars), leader (>30 chars) invalid genre, invalid local reps
        partyTwo = new Party("012345678901234567890123456789-0",
                "012345678901234567890123456789-0", "Indie",
                -1);
        assertEquals("012345678901234567890123456789", partyTwo.getPartyName());
        assertEquals("012345678901234567890123456789", partyTwo.getPartyLeader());
        assertEquals("unknown", partyTwo.getPartyGenre());
        assertEquals(0, partyTwo.getNumLocalReps());
        
    }

    @Test
    public void setPartyName() {

        //name on boundary 30 chars
        assertEquals("012345678901234567890123456789", partyOne.getPartyName());

        partyOne.setPartyName("01234567890123456789012345678");   //29 chars
        assertEquals("01234567890123456789012345678", partyOne.getPartyName());

        partyOne.setPartyName("012345678901234567890123456789");  //30 chars
        assertEquals("012345678901234567890123456789", partyOne.getPartyName());

        partyOne.setPartyName("0123456789012345678901234567890"); //31 chars
        assertEquals("012345678901234567890123456789", partyOne.getPartyName());
    }

    @Test
    public void setNumLocalReps() {
        //valid song length set
        assertEquals(163, partyOne.getNumLocalReps());

        partyOne.setNumLocalReps(-1);     //-1 (lower boundary)
        assertEquals(163, partyOne.getNumLocalReps());
        partyOne.setNumLocalReps(0);     //0 (lower boundary + 1)
        assertEquals(0, partyOne.getNumLocalReps());


        partyOne.setNumLocalReps(1200);     //1200 (valid data)
        assertEquals(1200, partyOne.getNumLocalReps());
    }

    @Test
    public void setPartyGenre() {//allow change is spelling of genre is correct whether or not case is UPPER
        assertEquals("RIGHT", partyOne.getPartyGenre());

        //valid values - genre must be:  "RIGHT", "LEFT", "EXTREME RIGHT", "EXTREME LEFT", "CENTRE", or "UNCATEGORISED"
        //setter to allow change if genre correct or correct with incorrect case (e.g. leFT), otherwise remain unchanged.
        partyOne.setPartyGenre("left");
        assertEquals("LEFT", partyOne.getPartyGenre());
        partyOne.setPartyGenre("extreme right");
        assertEquals("EXTREME RIGHT", partyOne.getPartyGenre());
        partyOne.setPartyGenre("EXTREME LEFT");
        assertEquals("EXTREME LEFT", partyOne.getPartyGenre());
        partyOne.setPartyGenre("centRE");
        assertEquals("CENTRE", partyOne.getPartyGenre());
        partyOne.setPartyGenre("uncategorISED");
        assertEquals("UNCATEGORISED", partyOne.getPartyGenre());

        //invalid values
        partyOne.setPartyGenre("WACKY");
        assertEquals("UNCATEGORISED", partyOne.getPartyGenre());  //remain unchanged

        partyOne.setPartyGenre("leFT");
        assertEquals("LEFT", partyOne.getPartyGenre());
    }



    @Test
    public void toStringUsesAllFields() {
        assertThat(partyOne.toString().contains("012345678901234567890123456789"), is(true));
        assertThat(partyOne.toString().contains("012345678901234567890123456789"), is(true));
        assertThat(partyOne.toString().contains("RIGHT"), is(true));
        assertThat(partyOne.toString().contains("163"), is(true));

    }
}