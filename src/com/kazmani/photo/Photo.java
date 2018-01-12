package com.kazmani.photo;

public class Photo {
	
	private int photoId;

	private String errorMessage;
	private String message;
	private String userPhoto;
	

	public int getPhotoId() {
		return photoId;
	}
	
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
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

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String successMessage) {
		this.message = successMessage;
	}

	/**
	 * @return the userPhoto
	 */
	public String getUserPhoto() {
		return userPhoto;
	}

	/**
	 * @param userPhoto the userPhoto to set
	 */
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

   
}
