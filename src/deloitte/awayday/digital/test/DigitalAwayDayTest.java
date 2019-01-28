package deloitte.awayday.digital.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import deloitte.awayday.digital.exception.DigitalAwaydayException;
import deloitte.awayday.digital.helper.DigitalAwayDayHelper;
import deloitte.awayday.digital.utils.DigitalAwayDayConstants;
import deloitte.awayday.digital.utils.DigitalAwayDayFileReader;

public class DigitalAwayDayTest {

	@Test
	public void loadTasks_ok() throws DigitalAwaydayException {
		String fileName = DigitalAwayDayConstants.FILE_NAME;
		DigitalAwayDayFileReader reader = new DigitalAwayDayFileReader();
		reader.getInputData(fileName);
	}

	@Test(expected = DigitalAwaydayException.class)
	public void loadTasks_bad_file() throws DigitalAwaydayException {
		String fileName = "abcd.txt";
		DigitalAwayDayFileReader reader = new DigitalAwayDayFileReader();
		reader.getInputData(fileName);

	}

	@Test(expected = DigitalAwaydayException.class)
	public void loadTasks_incorrect_data() throws DigitalAwaydayException {
		DigitalAwayDayFileReader reader = new DigitalAwayDayFileReader();
		// logs also printed for better debuging
		reader.getInputData(null);

	}

	@Test
	public void helperTest() {
		DigitalAwayDayHelper helper = new DigitalAwayDayHelper();
		DigitalAwayDayFileReader reader = new DigitalAwayDayFileReader();
		 try {
			assertEquals(2, helper.getTeamNumber(reader.getInputData(DigitalAwayDayConstants.FILE_NAME)));
			assertEquals(60,helper.getTimeFromTask("60min"));
			assertNotEquals(40,helper.getTimeFromTask("60min"));
		} catch (DigitalAwaydayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(helper.isInputCorrect(null));
		assertEquals(0, helper.getTeamNumber(null));
		assertEquals(0,helper.getTimeFromTask(null));

	}
	
		

	

}
