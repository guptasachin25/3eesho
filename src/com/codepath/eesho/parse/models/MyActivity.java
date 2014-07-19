package com.codepath.eesho.parse.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Activity")
public class MyActivity extends ParseObject{
	String activityType;
	Long dimension;
	Long calories;
	
	public MyActivity() {
		super();
	}
	
	public MyActivity(ParseUser user, String type, Long dimension, Long calories, Long steps) {
		super();
		setUser(user);
		setActivityType(type);
		setDimension(dimension);
		setCalories(calories);
		setSteps(steps);
	}
	
	public String getActivityType() {
		return getString("activity_type");
	}

	public void setActivityType(String activityType) {
		put("activity_type", activityType);
	}

	public Number getDimension() {
		return getNumber("activity_dimensions");
	}

	public void setDimension(Long dimension) {
		put("activity_dimensions", dimension);
	}

	public Number getCalories() {
		return getNumber("calories");
	}

	public void setCalories(Long calories) {
		put("calories", calories);
	}

	public void setUser(ParseUser user) {
		put("user", user);
	}
	
	public void setSteps(Number steps) {
		put("steps", steps);
	}
	
	public Number getSteps() {
		return getNumber("steps");
	}
}
