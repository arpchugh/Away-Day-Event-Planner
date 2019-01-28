package deloitte.awayday.digital.model;

import deloitte.awayday.digital.exception.DigitalAwaydayException;

public class ExtraTimeBlock extends DiditalAwayDayEventBlock {

	private Integer extraTime;

	public ExtraTimeBlock(Integer size, Integer extraTime) throws DigitalAwaydayException {
		super(size);
		this.extraTime = extraTime;
	}
	
	
	@Override
	public Integer getAvailableSize() {
		return super.getAvailableSize() + this.extraTime;
	}

	public Integer getExtraTime() {
		return extraTime;
	}

	public void setExtraTime(Integer extraTime) {
		this.extraTime = extraTime;
	}

}

