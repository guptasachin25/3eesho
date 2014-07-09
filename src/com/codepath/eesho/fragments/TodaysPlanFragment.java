package com.codepath.eesho.fragments;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.codepath.eesho.R;
import com.codepath.eesho.adapters.GoalArrayAdapter;
import com.codepath.eesho.models.Goal;

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
				
		goals = new ArrayList<Goal>();
		aGoals = new GoalArrayAdapter(getActivity(), goals);
		if (day == 1) {
			goals.add(new Goal(true, "eat mcdonalds"));
			goals.add(new Goal(false, "don't eat mcdonalds"));
		} else if (day == 0) {
			goals.add(new Goal(false, "I failed"));
		} else if (day == 2) {
			goals.add(new Goal(false, "relax"));
		}
		
//		addAll(goals);
	}
	
	public void addAll(ArrayList<Goal> goals) {
		aGoals.addAll(goals);
	}
	
}
