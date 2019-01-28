package deloitte.awayday.digital.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import deloitte.awayday.digital.DigitalAwayDayApp;
import deloitte.awayday.digital.beans.DigitalAwayDayActivityBean;
import deloitte.awayday.digital.exception.DigitalAwaydayException;
import deloitte.awayday.digital.helper.DigitalAwayDayHelper;

import java.io.FileReader;

//This class is for reading the file.
public class DigitalAwayDayFileReader {
	Logger logger = Logger.getLogger(DigitalAwayDayApp.class.getName());
//changed the method signature with input so that it can be tested with 
	/*
	 * This method reads/validates input file
	 * 
	 * @Return List<DigitalAwayDayActivityBean>
	 */
	@SuppressWarnings("resource")
	public List<DigitalAwayDayActivityBean> getInputData(String fileName) throws DigitalAwaydayException {
		BufferedReader fileReader;
		Stream<String> fileLines;
		DigitalAwayDayHelper helper = new DigitalAwayDayHelper();
		Integer time =0;
		List<DigitalAwayDayActivityBean> digitalAwayDayActivityList = new ArrayList<DigitalAwayDayActivityBean>();
		try {
			if(fileName!=null) {
			fileReader = new BufferedReader(new FileReader(fileName));
			fileLines = fileReader.lines();
			Iterator<String> lines = fileLines.iterator();
			while (lines.hasNext()) {
				String line = lines.next();
				if (helper.isInputCorrect(line)) {
				  time = helper.getTimeFromTask(line.substring(line.lastIndexOf(" ") + 1));
					DigitalAwayDayActivityBean activity = new DigitalAwayDayActivityBean(line.substring(0, line.lastIndexOf(" ")), time);
					
					digitalAwayDayActivityList.add(activity);
				}
				else {
					logger.log(Level.INFO, "Invalid input provided ,please correct the file and run again");
					throw new DigitalAwaydayException("Invalid input provided ,please correct the file and run again");
				}
			}
			//collection.add(timeList);
		//	collection.add(digitalAwayDayActivityList);
			}
			else {
				logger.log(Level.SEVERE, "No File is passed in the input");
				throw	new DigitalAwaydayException("No File is passed in the input");
			}
		} catch (FileNotFoundException e) {

		logger.log(Level.SEVERE, "Problem Loading the input file");
			
		throw	new DigitalAwaydayException("File not loaded properly");
		}
		
		return digitalAwayDayActivityList;
	}

}
