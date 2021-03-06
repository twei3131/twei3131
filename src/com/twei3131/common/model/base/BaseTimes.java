package com.twei3131.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseTimes<M extends BaseTimes<M>> extends Model<M> implements IBean {

	public void setDays(java.lang.Integer days) {
		set("days", days);
	}

	public java.lang.Integer getDays() {
		return get("days");
	}

	public void setStartTime(java.util.Date startTime) {
		set("startTime", startTime);
	}

	public java.util.Date getStartTime() {
		return get("startTime");
	}

	public void setEndTime(java.util.Date endTime) {
		set("endTime", endTime);
	}

	public java.util.Date getEndTime() {
		return get("endTime");
	}

	public void setTimesId(java.lang.Integer timesId) {
		set("timesId", timesId);
	}

	public java.lang.Integer getTimesId() {
		return get("timesId");
	}

}
