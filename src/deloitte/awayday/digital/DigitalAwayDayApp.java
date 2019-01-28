package deloitte.awayday.digital;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import deloitte.awayday.digital.beans.DigitalAwayDayActivityBean;
import deloitte.awayday.digital.exception.DigitalAwaydayException;
import deloitte.awayday.digital.helper.DigitalAwayDayHelper;
import deloitte.awayday.digital.model.DigitalAwayDay;
import deloitte.awayday.digital.utils.DigitalAwayDayConstants;
import deloitte.awayday.digital.utils.DigitalAwayDayFileReader;

public class DigitalAwayDayApp {
	static Logger logger = Logger.getLogger(DigitalAwayDayApp.class.getName());
	// Problem declaring all the class variables as static hence moving the file
	// loading to another class
	/*
	 * static BufferedReader fileReader; static Stream<String> fileLines;
	 */
	public static void main(String[] args) {
		List<DigitalAwayDayActivityBean> activityList = new ArrayList<DigitalAwayDayActivityBean>();
		Integer teams = 0;
		DigitalAwayDayFileReader fileRead = new DigitalAwayDayFileReader();
		DigitalAwayDayHelper helper = new DigitalAwayDayHelper();

		try {
			activityList = fileRead.getInputData(DigitalAwayDayConstants.FILE_NAME);
			teams = helper.getTeamNumber(activityList);
			LocalTime morningStartTime = LocalTime.of(9, 00);
			LocalTime morningEndTime = LocalTime.of(12, 00);
			LocalTime eveningStartTime = LocalTime.of(1, 00);
			LocalTime eveningEndTime = LocalTime.of(4, 00);
			DigitalAwayDay day = new DigitalAwayDay(morningStartTime, morningEndTime, eveningStartTime, eveningEndTime,
					DigitalAwayDayConstants.MINUTS_PER_HOUR, teams, activityList);
			day.addEvent(activityList);
			System.out.println("Deloitte Digital Away Day:");
			System.out.println(day);
		} catch (DigitalAwaydayException e) {
			logger.log(Level.INFO, "In main method error occured during Away Day Creation ");
			

		}
	}

}
