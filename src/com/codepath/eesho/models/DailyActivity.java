package com.codepath.eesho.models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DailyActivity<T extends SingleActivity> {
	List<T> activities;
	
	public DailyActivity() {
		activities = new ArrayList<T>();
	}
	
	public void addActivity(T singleActivity) {
		activities.add(singleActivity);
	}

	public List<T> getActivityList() {
		return activities;
	}
	
	public JSONObject toJson() throws JSONException {
		JSONArray array = new JSONArray();
		for(T activity: activities) {
			array.put(activity.toJSONObject());
		}
		return new JSONObject().put("plan", array);
	}
	
	public static DailyActivity<FitnessPlanSingleActivity> fromJson(JSONObject obj) throws JSONException {
		JSONArray array = obj.getJSONArray("plan");
		DailyActivity<FitnessPlanSingleActivity> dailyAcitivity = new DailyActivity<FitnessPlanSingleActivity>();
		for(int i = 0; i< array.length(); i ++) {
			dailyAcitivity.addActivity(FitnessPlanSingleActivity.fromJSON(array.getJSONObject(i)));
		}
		return dailyAcitivity;
	}

	public static DailyActivity<FitnessPlanSingleActivity> getEmptyActivity() {
		return new DailyActivity<FitnessPlanSingleActivity>();
	}
	
	public void resetDone(SingleActivity singleActivity) {
		for(int i = 0; i< activities.size(); i++) {
			if(singleActivity.getId() == activities.get(i).getId()) {
				activities.get(i).resetDone();
			}
		}
	}
}
