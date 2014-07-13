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
		activity1.setRepititions("5 miles");
		
		FitnessPlanSingleActivity activity2 = new FitnessPlanSingleActivity();
		activity2.setDescription("Pushups");
		activity2.setRepititions("20");
		activity2.setDuration(3L);
		
		FitnessPlanSingleActivity activity3 = new FitnessPlanSingleActivity();
		activity3.setDescription("Run");
		activity3.setDuration(20L);
		
		fitnessPlan.addActivity("Sunday", activity1);
		fitnessPlan.addActivity("Monday", activity2);
		fitnessPlan.addActivity("Tuesday", activity3);
		fitnessPlan.addActivity("Thrusday", activity1);
		fitnessPlan.addActivity("Friday", activity2);
		fitnessPlan.addActivity("Saturday", activity3);	
		return fitnessPlan;
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject object = new JSONObject();
		for(Entry<String, DailyActivity<FitnessPlanSingleActivity>> entry: activities.entrySet()) {
			JSONArray array = new JSONArray();
			for(FitnessPlanSingleActivity activity: entry.getValue().getActivityList()) {
				array.put(activity.toJSONObject());
			}
			object.put(entry.getKey(), array);
		}
		return object;
	}	
}