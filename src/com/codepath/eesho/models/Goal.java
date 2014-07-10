package com.codepath.eesho.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

@ParseClassName("Goal")
public class Goal extends ParseObject{
	static ParseQuery<Goal> query = ParseQuery.getQuery(Goal.class);
	ArrayList<Goal> resultGoals;
	static ArrayList<String> doneGoals = new ArrayList<String>();
	
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

	public int getDayOfWeek() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_WEEK); 
	}
	
	public ArrayList<Goal> getYesterdayGoals(String username) {
		int day = getDayOfWeek();
		if (day != 1) {
			return getGoals(day - 1, username);
		} else {
			return getGoals(7, username);
		}
	}
	
	public ArrayList<Goal> getTomorrowsGoals(String username) {
		int day = getDayOfWeek();
		if (day != 7) {
			return getGoals(day + 1, username);
		} else {
			return getGoals(1, username);
		}
	}
	
	public ArrayList<Goal> getTodaysGoals(String username) {
		return getGoals(getDayOfWeek(), username);
	}
	
	public ArrayList<Goal> getGoals(int i, String username) {
		query.whereEqualTo("username", username);
		query.whereEqualTo("dayOfWeek", Integer.toString(i));
		resultGoals = new ArrayList<Goal>();

		query.findInBackground(new FindCallback<Goal>() {
		    public void done(List<Goal> goals, ParseException e) {
		        if (e == null) {
		            for (int i = 0; i < goals.size(); i++) {
		            	resultGoals.add(new Goal(goals.get(i).isDone(), goals.get(i).getGoalDescription()));
		            }
		            
		        } else {
		            Log.d("item", "Error: " + e.getMessage());
		        }
		    }
		});
		
		return resultGoals;
	}
	
	public static ArrayList<String> getDoneTasks(int dayOfWeek, String username, final List<String> li) {
		query.whereEqualTo("username", username);
		query.whereEqualTo("dayOfWeek", dayOfWeek);
		query.whereEqualTo("done", true);
		
		query.findInBackground(new FindCallback<Goal>() {
		    public void done(List<Goal> goals, ParseException e) {
		        if (e == null) {
		            for (int i = 0; i < goals.size(); i++) {
		            	li.add(goals.get(i).getGoalDescription().toString());
		            	System.out.println(goals.get(i).getGoalDescription().toString());
		            }
		            
		        } else {
		            Log.d("item", "Error: " + e.getMessage());
		        }
		    }
		});
		
		return doneGoals;
	}
}
