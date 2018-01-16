package com.kazmani.qualification;

import java.sql.SQLException;

public interface IQualification {

    public Qualification addQualification(Qualification q) throws SQLException;

    public Qualification deleteQualificationById(String phoneNumberOrEmail)
	    throws SQLException;

    public Qualification getQualificationById(String phoneNumberOrEmail);

    Qualification updateQualificationById(Qualification q);
}
