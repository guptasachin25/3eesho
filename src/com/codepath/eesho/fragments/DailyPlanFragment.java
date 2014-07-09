package com.codepath.eesho.fragments;

import java.util.ArrayList;

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

public class DailyPlanFragment extends Fragment{

	private ArrayList<Goal> goals;
	private GoalArrayAdapter aGoals;
	ListView lvGoals;
	FragmentPagerAdapter adapterViewPager;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_goals_list, container, false);
//		lvGoals = (ListView) v.findViewById(R.id.lvGoals);
//		lvGoals.setAdapter(aGoals);
		
		ViewPager vpPager = (ViewPager) v.findViewById(R.id.vpPager);
		adapterViewPager = new MyPagerAdapter(getFragmentManager());
		vpPager.setAdapter(adapterViewPager);
		vpPager.setCurrentItem(1);
		
		return v;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		goals = new ArrayList<Goal>();
//		aGoals = new GoalArrayAdapter(getActivity(), goals);
//		goals.add(new Goal(true, "eat mcdonalds"));
//		goals.add(new Goal(false, "don't eat mcdonalds"));
		
//		addAll(goals);
	}
	
	public void addAll(ArrayList<Goal> goals) {
		aGoals.addAll(goals);
	}
	
	public static class MyPagerAdapter extends FragmentPagerAdapter {
		private static int NUM_ITEMS = 3;
			
	        public MyPagerAdapter(FragmentManager fragmentManager) {
	            super(fragmentManager);
	        }
	        
	        // Returns total number of pages
	        @Override
	        public int getCount() {
	            return NUM_ITEMS;
	        }
	 
	        // Returns the fragment to display for that page
	        @Override
	        public Fragment getItem(int position) {
	            switch (position) {
	            case 0: 
	                return new TodaysPlanFragment();
	            case 1: 
	                return new TodaysPlanFragment();
	            case 2: 
	                return new TodaysPlanFragment();
	            default:
	            	return null;
	            }
	        }
	        
	        // Returns the page title for the top indicator
	        @Override
	        public CharSequence getPageTitle(int position) {
	        	switch (position) {
	        	case 0:
	        		return "YESTERDAY";
	        	case 1:
	        		return "TODAY";
	        	case 2:
	        		return "TOMORROW";
	        		
	        	}
	        	
	        	return "";
	        }
	        
	    }

}
