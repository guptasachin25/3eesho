package com.codepath.eesho.models;

import java.util.List;

import org.json.JSONArray;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

@ParseClassName("Goal")
public class Goal extends ParseObject{
	
	public Goal() {
		super();
	}
	
	public Goal(String isDone, String desc) {
		super();
		if (isDone.contains("true")) {
			setIsDone(true);
		} else {
			setIsDone(false);
		}
		setDescription(desc);
	}
	
	public Goal(boolean isDone, String desc) {
	    super();
	    setIsDone(isDone);
	    setDescription(desc);
	  }
	
	public void setIsDone(boolean isDone) {
	    put("done", isDone);
	}
	
	public void setDescription(String description) {
		put("goalDescription", description);
	}
	
	public String getId() {
		return getString("objectId");
	}
	
	public boolean isDone() {
	    return getBoolean("done");
	  }
	
	public String getGoalDescription() {
		return getString("goalDescription");
	}
}
