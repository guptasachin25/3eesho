package com.codepath.eesho.models;

import java.util.Locale;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

public class FitnessPlanSingleActivity implements SingleActivity {
	private Long ID = new Random().nextLong();

	public static enum Exercise {
		Inchworm,
		TuckJump,
		BearCrawl,
		PlyometricPushUp,
		StairClimbwithBicepCurl,
		MountainClimber,
		ProneWalkout,
		Burpees,
		Plank,
		Plank_to_Push_Up,
		WallSit,
		Lunge,
		ClockLunge,
		Lunge_to_Row,
		LungeJump,
		CurtsyLunge,
		Squat,
		PistolSquat,
		SquatReachandJump,
		WarmupRun,
		Run,
		Rowing,
		CrossTrain,
		StepUp
	}

	Exercise exercise;
	
	String description;

	// Duration always in minutes
	Long duration = 0L; 

	Long repititions = 0L;

	// Only applicable in case of running
	Long distance = 0L;

	// Applicable in case of sets.
	Long sets = 0L;

	boolean done = false;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
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

	public Long getRepititions() {
		return repititions;
	}

	public void setRepititions(Long repititions) {
		this.repititions = repititions;
	}

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}

	public Long getSets() {
		return sets;
	}

	public void setSets(Long sets) {
		this.sets = sets;
	}

	public boolean isDone() {
		return done;
	}
	
	public void setDone(boolean done) {
		this.done = done;
	}

	private boolean hasDuration() {
		return duration != 0L;
	}

	private boolean hasRepititions() {
		return repititions != 0L;
	}
	
	private boolean hasDistance() {
		return distance != 0L;
	}
	@Override
	public Long getId() {
		return ID;
	}

	@Override
	public void resetDone() {
		done = !done;
	}
	
	// To text for exercise.
	@Override
	public String toString() {
		String value = null;
		
		if(hasDistance()) {
			value = String.format(Locale.ENGLISH, "%s for %d miles", description, distance);
		} else if(hasRepititions()) {
			if(sets != 0L) {
				value = String.format(Locale.ENGLISH, "%s (%d sets)", description, sets);
			} else {
				value = description;
			}
		} else if(hasDuration()) {
			value = String.format(Locale.ENGLISH, "%s for %d minutes", description, duration);
		}
		return value;
	}
	
	/*
	 * Function to return number to be shown on dashboard.
	 */
	public Long getDisplayNumber() {
		if(hasDistance()) {
			return distance;
		} else if(hasDuration()) {
			return duration;
		} else if(hasRepititions()) {
			return repititions;
		}

		return 0L;
	}


	@Override
	public JSONObject toJSONObject() {
		JSONObject object = new JSONObject();
		
		try {
			object.put("description", description);	
		} catch (Exception e) {}

		try {
			object.put("duration", duration);
		} catch (JSONException e) {}
		
		try {
			object.put("repititions", repititions);
		} catch (JSONException e) {}
		
		try {
			object.put("done", done);
		} catch (JSONException e) {}
		
		try {
			object.put("sets", sets);
		} catch (JSONException e) {}
		
		try {
			object.put("distance", distance);
		} catch(JSONException e) {}
		
		try {
			object.put("exercise", exercise);
		} catch(JSONException e) {}
		
		try {
			object.put("ID", ID);
		} catch (JSONException e) {}
		
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
			plan.setRepititions(object.getLong("repititions"));
		} catch (JSONException e) {}
		catch (NumberFormatException e) {}
		
		try {
			plan.setDistance(object.getLong("distance"));
		} catch (JSONException e) {}
		catch (NumberFormatException e) {}
		
		try {
			plan.setSets(object.getLong("sets"));
		} catch (JSONException e) {}
		
		try {
			plan.setDone(object.getBoolean("done"));
		} catch (JSONException e) {}
		return plan;
	}
}
