package com.kazmani.admin;

import com.kazmani.role.IRole;
import com.kazmani.therapist.Therapist;
import com.kazmani.therapist.TherapistDao;
import com.kazmani.user.User;

public class AdminDao implements IRole,IAdmin {

	
	@Override
	public Admin addNewAdmin(Admin adm) {
		// TODO Auto-generated method stub
		
		return adm;
	}
	
	@Override
	public Admin validateTherapist(String phoneOrEmail) {
		// TODO Auto-generated method stub
		String sql= "UPDATE THERAPIST_TB SET STATUS = ? where PHONE_NUMBER=? or EMAIL=?";
		return null;
	}

	@Override
	public Admin blockUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin unblockUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Admin AddNewAdmin(String username, String password, String email,
			String phoneNumber, int level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRole(String role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getRole() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
