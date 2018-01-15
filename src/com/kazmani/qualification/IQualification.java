package com.kazmani.qualification;

import java.sql.SQLException;

public interface IQualification {

	public Qualification addQualification(Qualification q) throws SQLException;
    public Qualification updateQualificationById(String phoneNumberOrEmail,Qualification q);
    public Qualification deleteQualificationById(String phoneNumberOrEmail)throws SQLException;
    public Qualification getQualificationById(String phoneNumberOrEmail);
}
