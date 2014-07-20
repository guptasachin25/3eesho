package com.codepath.eesho.models;

import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

public class DietPlanSingleActivity implements SingleActivity {
	private Long ID = new Random().nextLong();
	
	// Time of the day when this plan need to be followed
	String timestamp;
	
	// Description of the activity
	String description;
	
	Boolean done;
	
	String title;
	
	public DietPlanSingleActivity() {}
	
	public DietPlanSingleActivity(String description, String title, String timestamp) {
		this.title = title;
		this.timestamp = timestamp;
		this.description = description;
	}
	
	public boolean isDone() {
		return done;
	}
	
	public void resetDone() {
		done = !done;
	}
	
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setDone(boolean done) {
		this.done = done;
	} 
	
	@Override 
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(description);
		builder.append(" at ");
		builder.append("timestamp");
		return builder.toString();
	}
	
	public static DietPlanSingleActivity getPlan(String desc, String title, String timestamp) {
		return new DietPlanSingleActivity(desc, title, timestamp);
	}

	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("description", description);
		obj.put("timestamp", timestamp);
		obj.put("done", done);
		return obj;
	}

	public static Object fromJSON(JSONObject obj) {
		DietPlanSingleActivity activity = new DietPlanSingleActivity();
		try {
			activity.setDescription(obj.getString("description"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			activity.setTimestamp(obj.getString("timestamp"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			activity.setDone(obj.getBoolean("done"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return activity;
	}

	@Override
	public Long getId() {
		return ID;
	}

	@Override
	public Long getDisplayNumber() {
		// TODO Auto-generated method stub
		return null;
	}

}
