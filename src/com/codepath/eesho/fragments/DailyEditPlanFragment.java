package com.codepath.eesho.fragments;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.codepath.eesho.R;
import com.codepath.eesho.adapters.GoalArrayAdapter;
import com.codepath.eesho.models.Goal;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

@SuppressLint("ValidFragment")
public class DailyEditPlanFragment extends Fragment{
	private ArrayList<Goal> goals;
	private GoalArrayAdapter aGoals;
	ListView lvGoals;
	Button btnAdd;
	EditText etNewGoal;
	
	private int day;
	
	public DailyEditPlanFragment(int i) {
		day = i;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ParseQuery<Goal> query = ParseQuery.getQuery(Goal.class);
		// Define our query conditions
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
	
	@Override
	public View onCreateView(LayoutInflater inflater,
		 ViewGroup container,  Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_daily_edit_plan, container, false);
		
		lvGoals = (ListView) v.findViewById(R.id.lvGoals);
		lvGoals.setAdapter(aGoals);
		btnAdd = (Button) v.findViewById(R.id.btnAdd);
		etNewGoal = (EditText) v.findViewById(R.id.etNewGoal);
		
		btnAdd.setOnClickListener(new OnClickListener(){
			@Override
            public void onClick(View v)
            {
				goals.add(new Goal(false, etNewGoal.getText().toString()));
				aGoals.notifyDataSetChanged();
				etNewGoal.setText("");
				etNewGoal.setHint("Add a new goal");
            } 
		}); 
		
		return v;
	}

}