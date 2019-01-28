package deloitte.awayday.digital.beans;

import java.time.LocalTime;

/*
 * 
 * This class is used for creating individual activity with is complete duration, its name and start time.
 */

public class DigitalAwayDayActivityBean {

	public String activityName;
	public Integer activityTime;
	public LocalTime startTime;
	
	public DigitalAwayDayActivityBean(String activityName, Integer activityTime) {
		this.activityTime = activityTime;
		this.activityName = activityName;
	}
	
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public Integer getActivityTime() {
		return activityTime;
	}
	public void setActivityTime(Integer activityTime) {
		this.activityTime = activityTime;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("");
		buffer
		.append(this.startTime + " ")
		.append((this.getStartTime().getHour() <9?"pm":"am"))
		.append(" : " + this.getActivityName() + " ")
		.append((this.activityTime == 15?"Sprint":this.activityTime+"min"));
		return buffer.toString();
		
	}
}