package com.kazmani.therapist;

import java.sql.SQLException;
import java.util.ArrayList;

import com.kazmani.address.Address;
import com.kazmani.address.Location;



public interface ITherapist {

	// NOTE:uniqueId can be either email or phoneNumber
	
	// public Therapist getQualification();
	
	
	public Therapist addNewTherapist(Therapist therapist) throws SQLException;

	public Therapist checkStatus(String uniqueId/* email or phoneNumber */)
			throws SQLException;
	
	public Therapist editBioData(Therapist therapist);
	
	public Therapist editTherapistDetails(Therapist therapist);
	
	public Therapist getTherapistById(String uniqueId);

	public Therapist editProfileText(String uniqueId, String profileText)
			throws SQLException;
	
	public ArrayList<Therapist> getAllTherapistsInLocation(Location location,Address address);
	
	

}
