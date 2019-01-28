package deloitte.awayday.digital.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import deloitte.awayday.digital.beans.DigitalAwayDayActivityBean;
import deloitte.awayday.digital.exception.DigitalAwaydayException;


public class DigitalAwayDayEvent {
	Logger logger = Logger.getLogger(DigitalAwayDayEvent.class.getName());
	private LocalTime morningStartTime;
	private DiditalAwayDayEventBlock morningBlock;

	private LocalTime eveningStartTime;
	private DiditalAwayDayEventBlock eveningBlock;

	public LocalTime getMorningStartTime() {
		return morningStartTime;
	}
	public void setMorningStartTime(LocalTime morningStartTime) {
		this.morningStartTime = morningStartTime;
	}
	public DiditalAwayDayEventBlock getMorningBlock() {
		return morningBlock;
	}
	public void setMorningBlock(DiditalAwayDayEventBlock morningBlock) {
		this.morningBlock = morningBlock;
	}
	public LocalTime getEveningStartTime() {
		return eveningStartTime;
	}
	public void setEveningStartTime(LocalTime eveningStartTime) {
		this.eveningStartTime = eveningStartTime;
	}
	public DiditalAwayDayEventBlock getEveningBlock() {
		return eveningBlock;
	}
	public void setEveningBlock(DiditalAwayDayEventBlock eveningBlock) {
		this.eveningBlock = eveningBlock;
	}
	public DigitalAwayDayEvent(LocalTime morningStartTime, LocalTime morningEndTime, LocalTime eveningStartTime, LocalTime eveningEndTime,
			Integer eveningExtraTime  ) throws DigitalAwaydayException {
		this.checkDayProgram(morningStartTime, morningEndTime, eveningStartTime, eveningEndTime);

		this.morningStartTime = morningStartTime;
		this.eveningStartTime = eveningStartTime;
			this.morningBlock = new DiditalAwayDayEventBlock(getBlockSize(morningStartTime, morningEndTime));
			//Global variable is impacting the size of the event hence creating another class for evening block which contains extra time
			this.eveningBlock = new ExtraTimeBlock(getBlockSize(eveningStartTime, eveningEndTime), eveningExtraTime);
	}
	public boolean insertMorningTask(DigitalAwayDayActivityBean task) {
		
		if (task != null){
			task.setStartTime(this.morningStartTime.plusMinutes(morningBlock.getUsedSize()));
			return this.morningBlock.addIndividualActivity(task);
		}
		return false;
	}

	
	public boolean insertEveningTask(DigitalAwayDayActivityBean task) {
		
		if (task != null){
			task.setStartTime(this.eveningStartTime.plusMinutes(eveningBlock.getUsedSize()));
			return this.eveningBlock.addIndividualActivity(task);
		}
		return false;
	}

	
	private Integer getBlockSize(LocalTime start, LocalTime end) throws DigitalAwaydayException {

		Duration duration = Duration.between(start, end);
		if (duration.getSeconds() > 0) {
			return (int) (duration.getSeconds() / 60);
		}
		logger.log(Level.INFO, "In Method getBlockSize : End time must be after start time");
		throw new DigitalAwaydayException(" In Method getBlockSize : End time must be after start time");
		
	}

	
	public Integer getMinDuration() {

		return this.morningBlock.getAvailableSize() + this.eveningBlock.getAvailableSize() - this.eveningBlock.getExtraTime();
	}

	
	public Integer getMaxDuration() {

		return this.morningBlock.getAvailableSize() + this.eveningBlock.getAvailableSize();
	}

	private boolean checkDayProgram(LocalTime morningStartTime, LocalTime morningEndTime, LocalTime eveningStartTime,
			LocalTime eveningEndTime)  {

		if (morningStartTime != null && eveningStartTime != null && morningEndTime != null && eveningEndTime != null
				&& eveningEndTime.isAfter(morningStartTime)) {
			return true;
		} else {
			return false;
		}
	}





	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("");
		buffer.append(morningBlock);
		buffer.append(eveningBlock);
		return buffer.toString();
	}





}
		

