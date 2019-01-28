package deloitte.awayday.digital.helper;

import java.util.List;
import java.util.regex.Pattern;

import deloitte.awayday.digital.beans.DigitalAwayDayActivityBean;
import deloitte.awayday.digital.utils.DigitalAwayDayConstants;

//this class helps in defining the methods which do the validations or manipulations with the data
public class DigitalAwayDayHelper {
/*
 * @return true if task matches the required pattern generated using assumed set of characters
 */
	public boolean isInputCorrect(String taskLine) {
		if (taskLine!=null && Pattern.matches(DigitalAwayDayConstants.REGEX, taskLine))
			return true;

		return false;
	}

	public int getTimeFromTask(String taskLine) {
	
       int time = 0;
		if(taskLine!=null) {
		if ("Sprint".equals(taskLine))
			time=  15;
		else
			time = Integer.valueOf(taskLine.substring(0, taskLine.length() - 3));
		}
		return time;
	

	}
	
/*
 * @returns the number of teams which will be created when tasks are passed 
 * 
 */
	public int getTeamNumber(List<DigitalAwayDayActivityBean> activities) {
		Integer totalTime = 0;
		int team = 0;
		if(activities!=null) {
		for(DigitalAwayDayActivityBean activity : activities) {
			totalTime =		 totalTime+ activity.getActivityTime() ;
		}
		team=  totalTime / DigitalAwayDayConstants.MINUTS_PER_HOUR / DigitalAwayDayConstants.AVAIL_SLOT_MORN_EVE;
		}
		return team;
	}

	
	
}