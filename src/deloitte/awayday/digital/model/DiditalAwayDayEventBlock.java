package deloitte.awayday.digital.model;

import java.util.ArrayList;
import java.util.List;

import deloitte.awayday.digital.beans.DigitalAwayDayActivityBean;
import deloitte.awayday.digital.exception.DigitalAwaydayException;



/*
 * 
 * This class deals with the creation of block which helps in finding the used minutes from the total duration of activities
 * 
 */
public class DiditalAwayDayEventBlock {

	private List<DigitalAwayDayActivityBean> activityList;

	private Integer size;
	
	private Integer extraTime ;

	
	public DiditalAwayDayEventBlock(Integer size)  throws DigitalAwaydayException {
		this.activityList = new ArrayList<DigitalAwayDayActivityBean>();
		this.size = size;
	}
	
	
	/*
	 * public DiditalAwayDayEventBlock(Integer size) { this.activityList = new
	 * ArrayList<DigitalAwayDayActivityBean>(); this.size = size; //this.extraTime
	 * =extraTime; }
	 */
	public List<DigitalAwayDayActivityBean> getActivity() {
		return activityList;
	}

	public void setActivity(List<DigitalAwayDayActivityBean> activity) {
		this.activityList = activity;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	
	public List<DigitalAwayDayActivityBean> getActivityList() {
		return activityList;
	}


	public void setActivityList(List<DigitalAwayDayActivityBean> activityList) {
		this.activityList = activityList;
	}


	public Integer getExtraTime() {
		return extraTime;
	}


	public void setExtraTime(Integer extraTime) {
		this.extraTime = extraTime;
	}
	
	public boolean addIndividualActivity(DigitalAwayDayActivityBean activity) {
		
		if (activity != null && activity.getActivityTime() <= this.getAvailableSize()) {
			this.activityList.add(activity);
			return true;
		}
		return false;
	}

	public Integer getAvailableSize() {
		return  this.size -activityList.stream().mapToInt(acty -> acty.getActivityTime()).sum()  ;
	}

	
	public Integer getUsedSize() {
		return activityList.stream().mapToInt(acty -> acty.getActivityTime()).sum();
	}
	
	/*
	 * public Integer getTotalTime() { return activityList.stream().mapToInt(acty ->
	 * acty.getActivityTime()).sum();
	 * 
	 * 
	 * }
	 */
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("");
		activityList.forEach(acty -> buffer.append(acty).append(System.getProperty("line.separator")));
		return buffer.toString();
	}
	
	
	
}
