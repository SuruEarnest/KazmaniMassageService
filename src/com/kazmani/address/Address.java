package com.kazmani.address;

public class Address {

	private String addressType;// hotel or home or office
	private String userId;
	private String state;
	private String localGovt;
	private String description;
	private String country;
	private Location location;

	public Address() {

	}

	public Address(String addressType, String description, String localGovt,
			String state, String country) {
		super();
		this.addressType = addressType;
		this.state = state;
		this.country = country;
		this.localGovt = localGovt;
		this.description = description;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLocalGovt() {
		return localGovt;
	}

	public void setLocalGovt(String localGovt) {
		this.localGovt = localGovt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCountry(String country) {
		// TODO Auto-generated method stub
		this.country = country;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	public Location getLocation() {
	    return location;
	}

	public void setLocation(Location location) {
	    this.location = location;
	}

}
