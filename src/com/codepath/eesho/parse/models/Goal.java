package com.codepath.eesho.parse.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.codepath.eesho.models.DailyActivity;
import com.codepath.eesho.models.DietPlanSingleActivity;
import com.codepath.eesho.models.FitnessPlanSingleActivity;
import com.codepath.eesho.models.SingleActivity;
import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

@ParseClassName("Goal")
public class Goal extends ParseObject {
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
	
	public void setDayOfWeek(int dayOfWeek) {
		put ("dayOfWeek", dayOfWeek);
	}
	
	public void setUserName(String username) {
		put ("username", username);
	}
	
	public String getId() {
		return getString("objectId");
	}
	
	public boolean isDone() {
	    return getBoolean("done");
	}
	
	public String getGoalDescription() throws JSONException {
		JSONObject obj = getJSONObject("plan_object");
		JSONArray array = obj.getJSONArray("plan");
		if(obj.getString("plan") == "fitness") {
			return FitnessPlanSingleActivity.fromJSON(obj).toString();
		} else {
			return DietPlanSingleActivity.fromJSON(obj).toString();
		}
	}
	
	public DailyActivity<FitnessPlanSingleActivity> getDailyActivity() throws JSONException {
		return DailyActivity.fromJson(getJSONObject("plan_object"));
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
		            	try {
							resultGoals.add(new Goal(goals.get(i).isDone(), goals.get(i).getGoalDescription()));
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
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
		            	try {
							li.add(goals.get(i).getGoalDescription().toString());
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
		            	try {
							System.out.println(goals.get(i).getGoalDescription().toString());
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
		            }
		            
		        } else {
		            Log.d("item", "Error: " + e.getMessage());
		        }
		    }
		});
		
		return doneGoals;
	}
	
	public void setPlan(DailyActivity<FitnessPlanSingleActivity> plan) {
		try {
			put("plan_object", plan.toJson());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public void setDate(Date date) {
		put("date", date);
	}

	public void setWeekDay(String weekDay) {
		put("week_day", weekDay);
	}

	public void setUser(ParseUser currentUser) {
		put("user", currentUser);
	}

	public void resetDone(DailyActivity<FitnessPlanSingleActivity> dailyActivity, SingleActivity singleActivity) throws JSONException {
		dailyActivity.resetDone(singleActivity);
		System.out.println(dailyActivity.toJson());
		put("plan_object", dailyActivity.toJson());
	}
}
