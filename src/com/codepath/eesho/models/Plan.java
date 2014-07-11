package com.codepath.eesho.models;

import java.util.Calendar;

import org.json.JSONArray;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Plan")
public class Plan extends ParseObject{
	
	public Plan() {
		super();
	}
	
	public int getDayOfWeek() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_WEEK); 
	}
	
	public JSONArray getYesterdayGoals() {
		int day = getDayOfWeek();
		if (day != 1) {
			return getGoals(day - 1);
		} else {
			return getGoals(7);
		}
	}
	
	public JSONArray getTomorrowsGoals() {
		int day = getDayOfWeek();
		if (day != 7) {
			return getGoals(day + 1);
		} else {
			return getGoals(1);
		}
	}
	
	public JSONArray getTodaysGoals() {
		return getGoals(getDayOfWeek());
	}
	
	/*
	 * 1 - Sunday, 7 - Saturday
	 */
	public JSONArray getGoals(int i) {
		switch (i) {
		case 1:
			return getJSONArray("su_goals");
		case 2:
			return getJSONArray("monday_goals");
		case 3:
			return getJSONArray("tuesday_goals");
		case 4:
			return getJSONArray("wed_goals");
		case 5:
			return getJSONArray("th_goals");
		case 6:
			return getJSONArray("f_goals");
		case 7:
			return getJSONArray("sa_goals");
		}
		return null;
	}


}
