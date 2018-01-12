package com.kazmani.schedule;

import java.sql.SQLException;

public interface ISchedule {

	public Schedule addNewSchedule(String therapistId, String day,String morning,String afternoon,String evening) throws SQLException;
	public Schedule deleteSchedule(String therapistId);
	public Schedule editSchedule(String therapistId);
}
