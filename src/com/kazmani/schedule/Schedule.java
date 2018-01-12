package com.kazmani.schedule;


import java.io.Serializable;

public class Schedule implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private String therapistId;/*the phoneNumber or email of the therapist*/
	private Period periodOfDay;
    private Day day;
    private String successMessage;
    private String errorMessage;
	/**
	 * @return the therapistId
	 */
	public String getTherapistId() {
		return therapistId;
	}
	/**
	 * @param therapistId the therapistId to set
	 */
	public void setTherapistId(String therapistId) {
		this.therapistId = therapistId;
	}
	/**
	 * @return the periodofDay
	 */
	public Period getPeriodofDay() {
		return periodOfDay;
	}
	/**
	 * @param periodofDay the periodofDay to set
	 */
	public void setPeriodofDay(Period periodofDay) {
		this.periodOfDay = periodofDay;
	}
	/**
	 * @return the day
	 */
	public Day getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(Day day) {
		this.day = day;
	}
	/**
	 * @return the periodOfDay
	 */
	public Period getPeriodOfDay() {
		return periodOfDay;
	}
	/**
	 * @param periodOfDay the periodOfDay to set
	 */
	public void setPeriodOfDay(Period periodOfDay) {
		this.periodOfDay = periodOfDay;
	}
	/**
	 * @return the message
	 */
	public String getSuccessMessage() {
		return successMessage;
	}
	/**
	 * @param message the message to set
	 */
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
    
    
    
}
