package com.codepath.eesho.parse.models;

import com.parse.ParseClassName;
import com.parse.ParseUser;

@ParseClassName("User")
public class User extends ParseUser {
	public User() {
		super();
	}
	
	public User(String username, String picture) {
		super();
		setUserName(username);
		setProfileImage(picture);
	}
	
	public String getUsernname() {
	    return getString("username");
	}
	
	public void setUserName(String username) {
		put("username", username);
	}
	
	public String getPassword() {
		return getString("password");
	}
	
	public String getEmail() {
		return getString("email");
	}
	
	public boolean isExpert() {
		return getBoolean("isExpert");
	}
	
	/**
	 * @return weight in lbs.
	 */
	public Number getWeight() {
		return getNumber("weight");
	}
	
	/**
	 * Set weight of user in parse.
	 * @param weight - weight in lbs
	 */
	public void setWeight(Number weight) {
		put("weight", weight);
	}
	
	/**
	 * @return height in cms.
	 */
	public Number getHeightInCms() {
		return getNumber("height");
	}
	
	/**
	 * 
	 * @param height in cms.
	 */
	public void setHeight(Number height) {
		put("height", height);
	}
	
	public User getExpert() {
		return (User) getParseObject("expert_id");
	}
	
	public void setExpert(User expert) {
		put("expert_id", expert);
	}
	
	public String getSex() {
		return getString("sex");
	}
	
	public void setSex(String sex) {
		put("sex", sex);
	}
	
	public String getEthenticity() {
		return getString("ethenticity");
	}
	
	public void setEthenticity(String ethenticity) {
		put("ethenticity", ethenticity);
	}

	public String getDietHabits() {
		return getString("diet_habits");
	}
	
	public void setDietHabits(String diet) {
		put("diet_habits", diet);
	}
	
	public String getProfileImageUrl() {
		return getString("profile_image");
	}
	
	public void setProfileImage(String profileImage) {
		put("profile_image", profileImage);
	}

	public String getEmailId() {
		return getString("email_id");
	}
	
	public void setEmailId(String emailId) {
		put("email_id", emailId);
	}

	public String getobjectId() {
		// TODO Auto-generated method stub
		return getString("getObjectId()");
	}
}
