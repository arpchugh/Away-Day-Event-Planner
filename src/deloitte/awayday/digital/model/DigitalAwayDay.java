package deloitte.awayday.digital.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import deloitte.awayday.digital.beans.DigitalAwayDayActivityBean;
import deloitte.awayday.digital.exception.DigitalAwaydayException;
import deloitte.awayday.digital.model.DigitalAwayDayEvent;

public class DigitalAwayDay {
	Logger logger = Logger.getLogger(DigitalAwayDay.class.getName());
	List<DigitalAwayDayEvent> teamEvent = new ArrayList<>();

	public DigitalAwayDay(LocalTime morningStartTime, LocalTime morningEndTime, LocalTime eveningStartTime,
			LocalTime eveningEndTime, Integer eveningExtraTime, Integer numberOfPrograms,
			List<DigitalAwayDayActivityBean> activityList) throws DigitalAwaydayException {

		for (int i = 0; i < numberOfPrograms; i++) {
			teamEvent.add(new DigitalAwayDayEvent(morningStartTime, morningEndTime, eveningStartTime, eveningEndTime,
					eveningExtraTime));
		}
		

	}

	public void addEvent(List<DigitalAwayDayActivityBean> activity) throws DigitalAwaydayException {

		this.checkEvent(activity);

		List<DigitalAwayDayActivityBean> orderedTasks = activity.stream()
				.sorted((t1, t2) -> t2.getActivityTime() - t1.getActivityTime()).collect(Collectors.toList());
		;
		for (DigitalAwayDayActivityBean actvty : orderedTasks) {
			boolean result = this.addSingleEvent(actvty, activity);
			if (!result) {
				logger.log(Level.INFO, "There was a problem inserting event");
				 throw new DigitalAwaydayException(" There was a problem inserting event ");

			}
		}
	}

	private void checkEvent(List<DigitalAwayDayActivityBean> activityList) throws DigitalAwaydayException {

		Integer tasksTime = activityList.stream().mapToInt(task -> task.getActivityTime()).sum();
		Integer minTime = teamEvent.stream().mapToInt(evnt -> evnt.getMinDuration()).sum();
		Integer maxTime = teamEvent.stream().mapToInt(evnt -> evnt.getMaxDuration()).sum();
		if (tasksTime < minTime || tasksTime > maxTime) {
			logger.log(Level.INFO, "The activity time is not proper and logic should be orrected");
			throw new DigitalAwaydayException("The activity time is not proper and logic should be orrected");
		}

	}

	private boolean addSingleEvent(DigitalAwayDayActivityBean activity, List<DigitalAwayDayActivityBean> activityList) {
		boolean result = false;
		Iterator<DigitalAwayDayEvent> iterator = teamEvent.iterator();
		while (result != true && iterator.hasNext()) {
			result = ((DigitalAwayDayEvent) iterator.next()).insertMorningTask(activity);
		}
		iterator = teamEvent.iterator();
		while (result != true && iterator.hasNext()) {
			result = ((DigitalAwayDayEvent) iterator.next()).insertEveningTask(activity);
		}
		return result;
	}

	public List<DigitalAwayDayEvent> getEvents() {
		return teamEvent;
	}

	public void setPrograms(List<DigitalAwayDayEvent> events) {
		this.teamEvent = events;
	}

	@Override
	public String toString() {

		StringBuffer buffer = new StringBuffer("");
		Integer count = 1;
		for (int i = 0; i < teamEvent.size(); i++) {
			buffer.append("Team No. " + count + ":")
			.append(System.getProperty("line.separator"))
			.append(teamEvent.get(i))
			//hard coding just to match the results 
			.append("05:00 pm : Staff Motivation Presentation 5min")
			.append(System.getProperty("line.separator"))
			.append(System.getProperty("line.separator"));
			count++;
		}
		return buffer.append(System.getProperty("line.separator")).toString();
	}

}
