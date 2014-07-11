package com.codepath.eesho.fragments;

import java.util.ArrayList;
import java.util.Calendar;
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
		
		ParseQuery<Goal> query = ParseQuery.getQuery(Goal.class);
		/*
		 * TODO
		 * this should be pulling for whichever user is logged in, based on username
		 */
		query.whereEqualTo("username", "caren");
				
		goals = new ArrayList<Goal>();
		aGoals = new GoalArrayAdapter(getActivity(), goals);
		
		Calendar calendar = Calendar.getInstance();
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);		
		
		System.out.println("on tab: " + day);
		System.out.println("day of week: " + dayOfWeek);
		
		
		if (day == 1) {
			query.whereEqualTo("dayOfWeek", dayOfWeek);
		} else if (day == 0) {
			if (day == 1) {
				query.whereEqualTo("dayOfWeek", 7);
			} else {
				query.whereEqualTo("dayOfWeek", dayOfWeek-1);
			}
		} else if (day == 2) {
			if (day == 7) {
				query.whereEqualTo("dayOfWeek", 1);
			} else {
				query.whereEqualTo("dayOfWeek", dayOfWeek + 1);
			}
		}
		
		query.findInBackground(new FindCallback<Goal>() {
		    public void done(List<Goal> resultGoal, ParseException e) {
		        if (e == null) {
		        	for (int i = 0; i < resultGoal.size(); i++) {
		        		goals.add(new Goal(resultGoal.get(i).isDone(), resultGoal.get(i).getGoalDescription()));
		            }
		            
		            aGoals.notifyDataSetChanged();
		            
		        } else {
		            Log.d("item", "Error: " + e.getMessage());
		        }
		    }
		});
		
	}
	
	public void addAll(ArrayList<Goal> goals) {
		aGoals.addAll(goals);
	}
	
}
