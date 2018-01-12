package com.kazmani.schedule;

import java.sql.Connection;         
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kazmani.config.DbConnector;

public class ScheduleDao implements ISchedule {

	private Connection conn = null;
	private Schedule schd = new Schedule();
	private DbConnector connector = new DbConnector();
	private PreparedStatement pst = null;
	
	@Override
	public Schedule addNewSchedule(String therapistId, String day,String morning,String afternoon,String evening) throws SQLException {
	
		try {
			
			conn = connector.makeConnection();
			conn.setAutoCommit(false);
			String sql = "INSERT into schedule_tb(THERAPIST_ID,DAY,MORNING,AFTERNOON,EVENING) values(?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1,therapistId);
			pst.setString(2, day);
			pst.setString(3, morning);
			pst.setString(4,afternoon);
			pst.setString(5,evening);
			pst.executeUpdate();
			conn.commit();
			schd.setSuccessMessage("Schdule setup successful!");
			return schd;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			conn.rollback();
			schd.setErrorMessage("Failed:"+e.getMessage());
			e.printStackTrace();
			return schd;
		}
		finally{
			
			connector.release(pst);
			connector.release(conn);
		}
		
		
	}

	@Override
	public Schedule deleteSchedule(String therapistId) {

		String sql = "";
		return null;
	}

	@Override
	public Schedule editSchedule(String therapistId) {

		return null;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}


}
