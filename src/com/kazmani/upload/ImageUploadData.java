/**
 * 
 *@author Udofa N Michael 
 */
package com.kazmani.upload;

import java.io.Serializable; 
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "signdata")
public class ImageUploadData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ImageId;
	private String ErrorMSg;
	private String SuccessMsg;
	private String UserImg;
	private String UserSign;
	private int RimNo;
	
	public int getRimNo() {
		return RimNo;
	}
	public void setRimNo(int rimNo) {
		RimNo = rimNo;
	}
	public String getErrorMSg() {
		return ErrorMSg;
	}
	public void setErrorMSg(String errorMSg) {
		ErrorMSg = errorMSg;
	}
	public String getSuccessMsg() {
		return SuccessMsg;
	}
	public void setSuccessMsg(String successMsg) {
		SuccessMsg = successMsg;
	}
	
	public int getImageId() {
		return ImageId;
	}
	public void setImageId(int imageId) {
		ImageId = imageId;
	}
	
	public String getUserImg() {
		return UserImg;
	}
	public void setUserImg(String userImg) {
		UserImg = userImg;
	}	
	
	public String getUserSign() {
		return UserSign;
	}
	public void setUserSign(String userSign) {
		UserSign = userSign;
	}


	public ImageUploadData(){
		
	}
	
	public ImageUploadData(int ImageId, String userImg, String userSign, String ShortError, String SuccessMsg, int RimNo){
			
	}
	
}
