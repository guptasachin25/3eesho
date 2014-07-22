package com.codepath.eesho.parse.models;

import java.util.Date;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Weight")
public class Weight extends ParseObject{
	
	public Weight() {
		super();
	}
	
	public Weight(int pounds, Date d) {
		
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
	
	

}
