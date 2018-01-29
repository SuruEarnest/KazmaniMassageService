package com.kazmani.admin;

import com.kazmani.therapist.Therapist; 
import com.kazmani.user.User;

/**
 * An interface to specify the operations that an admin must implement
 * @author DELL
 *
 */
public interface IAdmin {
    
	public Admin blockUser(User user);
	public Admin unblockUser(User user);
	public Admin addNewAdmin(Admin adm);
	public Admin validateTherapist(String phoneOrEmail);
	public Admin validateTherapist(Therapist thr);
	public Admin payTherapistById(double amount,String therapistPhoneNumber);
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @param level this describes the level of authority/privileges,1,2,3-highest
	 * @return
	 */
	public Admin AddNewAdmin(String username,String password,String email,String phoneNumber,int level);
	
}
