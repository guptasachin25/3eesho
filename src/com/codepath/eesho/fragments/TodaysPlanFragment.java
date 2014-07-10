package com.codepath.eesho.fragments;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.eesho.R;
import com.codepath.eesho.adapters.GoalArrayAdapter;
import com.codepath.eesho.models.Goal;
import com.codepath.eesho.models.Plan;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

@SuppressLint("ValidFragment")
public class TodaysPlanFragment extends Fragment{

	private ArrayList<Goal> goals;
	private GoalArrayAdapter aGoals;
	ListView lvGoals;
	private int day;
	
	public TodaysPlanFragment(int i) {
		day = i;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_todaygoals_list, container, false);
		lvGoals = (ListView) v.findViewById(R.id.lvGoals);
		lvGoals.setAdapter(aGoals);
		
		return v;
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ParseQuery<Plan> query = ParseQuery.getQuery(Plan.class);
		// Define our query conditions
		query.whereEqualTo("username", "calren");
				
		goals = new ArrayList<Goal>();
		aGoals = new GoalArrayAdapter(getActivity(), goals);
		if (day == 1) {
			query.findInBackground(new FindCallback<Plan>() {
			    public void done(List<Plan> plan, ParseException e) {
			        if (e == null) {
			            // Access the array of results here
			            JSONArray first = plan.get(0).getTodaysGoals();
			            for (int i = 0; i < first.length(); i++) {
			            	try {
			            		goals.add(new Goal(first.getJSONObject(i).get("done").toString(),
			            				first.getJSONObject(i).get("task").toString()));
							} catch (JSONException e1) {
								e1.printStackTrace();
							}
			            }
			            aGoals.notifyDataSetChanged();
			            
			        } else {
			            Log.d("item", "Error: " + e.getMessage());
			        }
			    }
			});

		} else if (day == 0) {
			query.findInBackground(new FindCallback<Plan>() {
			    public void done(List<Plan> plan, ParseException e) {
			        if (e == null) {
			            // Access the array of results here
			            JSONArray first = plan.get(0).getYesterdayGoals();
			            for (int i = 0; i < first.length(); i++) {
			            	try {
			            		goals.add(new Goal(first.getJSONObject(i).get("done").toString(),
			            				first.getJSONObject(i).get("task").toString()));
							} catch (JSONException e1) {
								e1.printStackTrace();
							}
			            }
			            aGoals.notifyDataSetChanged();
			            
			        } else {
			            Log.d("item", "Error: " + e.getMessage());
			        }
			    }
			});
		} else if (day == 2) {
			query.findInBackground(new FindCallback<Plan>() {
			    public void done(List<Plan> plan, ParseException e) {
			        if (e == null) {
			            // Access the array of results here
			            JSONArray first = plan.get(0).getTomorrowsGoals();
			            for (int i = 0; i < first.length(); i++) {
			            	try {
			            		goals.add(new Goal(first.getJSONObject(i).get("done").toString(),
			            				first.getJSONObject(i).get("task").toString()));
							} catch (JSONException e1) {
								e1.printStackTrace();
							}
			            }
			            aGoals.notifyDataSetChanged();
			            
			        } else {
			            Log.d("item", "Error: " + e.getMessage());
			        }
			    }
			});
		}
		
	}
	
	public void addAll(ArrayList<Goal> goals) {
		aGoals.addAll(goals);
	}
	
}
