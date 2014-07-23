package com.codepath.eesho.parse.models;

import java.util.Date;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Weight")
public class Weight extends ParseObject{
	
	public Weight() {
		super();
	}
	
	// date is MMM dd
	public Weight(int pounds, String date) {
		put ("Weight", pounds);
		put ("Date", date);
	}
	
	public String getId() {
		return getString("objectId");
	}
	
	public String getUser() {
	    return getString("user_id");
	}
	
	public int getWeight() {
		return getInt("Weight");
	}
	
	public Date getDate() {
		return getDate("createdAt");
	}
	
	public void setOwner(ParseUser currentUser) {
		put("user", currentUser);
	}
	
	

}
