package com.codepath.eesho.models;

import org.json.JSONArray;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Plan")
public class Plan extends ParseObject{
	
	public Plan() {
		super();
	}
	
	public String getUser() {
	    return getString("user");
	  }
	
	public String getExpert() {
		return getString("expert");
	}
	
	/*
	 * returns an array of daily goals
	 */
	public JSONArray getDailyGoals() {
		return getJSONArray("daily_goals");
	}

}
