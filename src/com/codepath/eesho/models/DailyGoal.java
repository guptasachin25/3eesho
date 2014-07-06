package com.codepath.eesho.models;

import org.json.JSONArray;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Plan")
public class DailyGoal extends ParseObject{
	
	public DailyGoal() {
		super();
	}
	
	public String getUser() {
	    return getString("user");
	}
	
	// MM/DD/YYYY
	public String getGoalDate() {
		return getString("date");
	}
	
	/*
	 * returns an array of goals
	 * each goal will have a boolean value for weather it was completed or not
	 */
	public JSONArray getGoals() {
		return getJSONArray("goals");
	}

}
