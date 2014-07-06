package com.codepath.eesho.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Goal")
public class Goal extends ParseObject{
	
	public Goal() {
		super();
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
