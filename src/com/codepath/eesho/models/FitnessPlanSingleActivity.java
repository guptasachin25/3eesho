package com.codepath.eesho.models;

import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

public class FitnessPlanSingleActivity implements SingleActivity {
	private Long ID = new Random().nextLong();

	String description;
	
	// Duration in minutes
	Long duration = 0L; 

	String repititions = null;
	
	boolean done = false;
	
	public boolean isDone() {
		return done;
	}
	
	public void resetDone() {
		done = !done;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getRepititions() {
		return repititions;
	}

	public void setRepititions(String repititions) {
		this.repititions = repititions;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if(repititions != null) {
			builder.append(repititions.toString());	
		}
		
		builder.append(description);
		
		if(duration > 0) {
			builder.append(duration.toString());
			builder.append(" minutes");
		}
		return builder.toString();
	}
	
	@Override
	public JSONObject toJSONObject() {
		JSONObject object = new JSONObject();
		try {
			object.put("description", description);	
		} catch (Exception e) {
			
		}
		
		try {
			object.put("duration", duration);
		} catch (JSONException e) {
		}
		try {
			object.put("repititions", repititions);
		} catch (JSONException e) {
		}
		try {
			object.put("done", done);
		} catch (JSONException e) {
		}
		return object;
	}
	
	public static FitnessPlanSingleActivity fromJSON(JSONObject object) {
		System.out.println(object.toString());
		FitnessPlanSingleActivity plan = new FitnessPlanSingleActivity();
		try {
			plan.setDescription(object.getString("description"));
		} catch (JSONException e) {}
		try {
			plan.setDuration(object.getLong("duration"));
		} catch (JSONException e) {}
		try {
			plan.setRepititions(object.getString("repititions"));
		} catch (JSONException e) {}
		try {
			plan.setDone(object.getBoolean("done"));
		} catch (JSONException e) {}
		return plan;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
	
	@Override
	public Long getId() {
		return ID;
	}
}
