package com.codepath.eesho.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.eesho.R;
import com.codepath.eesho.adapters.GoalArrayAdapter;
import com.codepath.eesho.models.Goal;

public class DailyPlanFragment extends Fragment{

	private ArrayList<Goal> goals;
	private GoalArrayAdapter aGoals;
	ListView lvGoals;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_goals_list, container, false);
		lvGoals = (ListView) v.findViewById(R.id.lvGoals);
		lvGoals.setAdapter(aGoals);
		
		return v;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		goals = new ArrayList<Goal>();
		aGoals = new GoalArrayAdapter(getActivity(), goals);
		goals.add(new Goal(true, "eat mcdonalds"));
		goals.add(new Goal(false, "don't eat mcdonalds"));
		
//		addAll(goals);
	}
	
	public void addAll(ArrayList<Goal> goals) {
		aGoals.addAll(goals);
	}

}
