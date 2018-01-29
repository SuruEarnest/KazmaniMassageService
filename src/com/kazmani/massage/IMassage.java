package com.kazmani.massage;

import java.sql.Date;  
import java.sql.SQLException;
import java.util.ArrayList;

public interface IMassage {

	public Massage AddNewMassage(String massageType,String massageDescription,String adminId,Date dateTime) throws SQLException;
	public Massage getMassageById(String massageId);
	public Massage editMassageById(String massageId, String adminId,String massageType,String massageDesc) throws SQLException;
    public ArrayList<Massage> getMassageList() throws SQLException;
}
