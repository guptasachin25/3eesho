package com.codepath.eesho.models;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codepath.eesho.activities.UserMetricsActivity;

public class WeeklyFitnessPlan {
	Map<String, DailyActivity<FitnessPlanSingleActivity>> activities;
	
	public WeeklyFitnessPlan() {
		activities = new HashMap<String, DailyActivity<FitnessPlanSingleActivity>>();
	}
	
	public void addActivity(String weekDay, FitnessPlanSingleActivity activity) {
		if(activities.containsKey(weekDay.toLowerCase(Locale.ENGLISH))) {
			DailyActivity<FitnessPlanSingleActivity> list = activities.get(weekDay.toLowerCase(Locale.ENGLISH));
			list.addActivity(activity);
			activities.put(weekDay.toLowerCase(Locale.ENGLISH), list);
		} else {
			DailyActivity<FitnessPlanSingleActivity> list = new DailyActivity<FitnessPlanSingleActivity>();
			list.addActivity(activity);
			activities.put(weekDay.toLowerCase(Locale.ENGLISH), list);
		}
	}
		
	public Map<String, DailyActivity<FitnessPlanSingleActivity>> getActivities() {
		return activities;
	}
	
	public DailyActivity<FitnessPlanSingleActivity> getActivity(String weekDay) {
		return activities.containsKey(weekDay.toLowerCase(Locale.ENGLISH)) 
				? activities.get(weekDay.toLowerCase(Locale.ENGLISH))
				: DailyActivity.getEmptyActivity();
	}
	
	public DailyActivity<FitnessPlanSingleActivity> getPlan(DateTime datetime) {
		return getActivity(UserMetricsActivity.weekDayMap.get(datetime.getDayOfWeek()));
	}
	
	public static WeeklyFitnessPlan getDefaultPlan() {
		WeeklyFitnessPlan fitnessPlan = new WeeklyFitnessPlan();
		
		FitnessPlanSingleActivity activity1 = new FitnessPlanSingleActivity();
		activity1.setDescription("Run");
		activity1.setDistance(5L);
		
		FitnessPlanSingleActivity activity2 = new FitnessPlanSingleActivity();
		activity2.setDescription("Pushups");
		activity2.setRepititions(20L);
		activity2.setDuration(3L);
		
		FitnessPlanSingleActivity activity3 = new FitnessPlanSingleActivity();
		activity3.setDescription("Run");
		activity3.setDuration(20L);
		
		FitnessPlanSingleActivity activity4 = new FitnessPlanSingleActivity();
		activity4.setDescription("Walk");
		activity4.setDuration(30L);
		
		FitnessPlanSingleActivity activity5 = new FitnessPlanSingleActivity();
		activity5.setDescription("Lunges 3 Sets");
		activity5.setRepititions(20L);
		
		FitnessPlanSingleActivity activity6 = new FitnessPlanSingleActivity();
		activity6.setDescription("Shoulders 4 Sets");
		activity6.setRepititions(20L);
		
		FitnessPlanSingleActivity activity7 = new FitnessPlanSingleActivity();
		activity7.setDescription("Chest presses");
		activity7.setRepititions(20L);
		
		fitnessPlan.addActivity("Sunday", activity1);
		fitnessPlan.addActivity("Sunday", activity4);
		fitnessPlan.addActivity("Sunday", activity6);
		fitnessPlan.addActivity("Monday", activity1);
		fitnessPlan.addActivity("Monday", activity2);
		fitnessPlan.addActivity("Monday", activity3);
		fitnessPlan.addActivity("Tuesday", activity3);
		fitnessPlan.addActivity("Thrusday", activity1);
		fitnessPlan.addActivity("Friday", activity2);
		fitnessPlan.addActivity("Saturday", activity3);	
		fitnessPlan.addActivity("Saturday", activity6);	
		fitnessPlan.addActivity("Saturday", activity7);	
		
		return fitnessPlan;
	}
	
	public static WeeklyFitnessPlan getDefaultPlan2() {
		WeeklyFitnessPlan fitnessPlan = new WeeklyFitnessPlan();
		
		FitnessPlanSingleActivity activity1 = new FitnessPlanSingleActivity();
		activity1.setDescription("Run");
		activity1.setDistance(5L);
		
		FitnessPlanSingleActivity activity2 = new FitnessPlanSingleActivity();
		activity2.setDescription("Pushups");
		activity2.setRepititions(20L);
		activity2.setSets(2L);
		
		FitnessPlanSingleActivity activity3 = new FitnessPlanSingleActivity();
		activity3.setDescription("Inch Worm");
		activity3.setRepititions(10L);
		activity3.setSets(2L);
		
		FitnessPlanSingleActivity activity4 = new FitnessPlanSingleActivity();
		activity4.setDescription("Walk");
		activity4.setDuration(30L);
		
		FitnessPlanSingleActivity activity5 = new FitnessPlanSingleActivity();
		activity5.setDescription("Lunges");
		activity5.setSets(2L);
		activity5.setRepititions(20L);
		
		FitnessPlanSingleActivity activity6 = new FitnessPlanSingleActivity();
		activity6.setDescription("Shoulders");
		activity6.setRepititions(20L);
		activity6.setSets(2L);
		
		FitnessPlanSingleActivity activity7 = new FitnessPlanSingleActivity();
		activity7.setDescription("Chest presses");
		activity7.setRepititions(20L);
		activity7.setSets(2L);
		
		fitnessPlan.addActivity("Sunday", activity1);
		fitnessPlan.addActivity("Sunday", activity4);
		fitnessPlan.addActivity("Sunday", activity6);
		
		fitnessPlan.addActivity("Monday", activity1);
		fitnessPlan.addActivity("Monday", activity2);
		fitnessPlan.addActivity("Monday", activity3);
		
		fitnessPlan.addActivity("Tuesday", activity3);
		fitnessPlan.addActivity("Tuesday", activity2);
		fitnessPlan.addActivity("Tuesday", activity7);
		
		fitnessPlan.addActivity("Wednesday", activity7);
		fitnessPlan.addActivity("Wednesday", activity6);
		fitnessPlan.addActivity("Wednesday", activity5);
		
		fitnessPlan.addActivity("Thrusday", activity1);
		fitnessPlan.addActivity("Thrusday", activity2);
		fitnessPlan.addActivity("Thrusday", activity6);
		
		fitnessPlan.addActivity("Friday", activity2);
		fitnessPlan.addActivity("Friday", activity5);
		fitnessPlan.addActivity("Friday", activity7);
		
		fitnessPlan.addActivity("Saturday", activity3);	
		fitnessPlan.addActivity("Saturday", activity6);	
		fitnessPlan.addActivity("Saturday", activity7);
		return fitnessPlan;
	}
	
	public JSONObject toJSON() {
		JSONObject object = new JSONObject();
		for(Entry<String, DailyActivity<FitnessPlanSingleActivity>> entry: activities.entrySet()) {
			JSONArray array = new JSONArray();
			for(FitnessPlanSingleActivity activity: entry.getValue().getActivityList()) {
				array.put(activity.toJSONObject());
			}
			try {
				object.put(entry.getKey(), array);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return object;
	}	
}
