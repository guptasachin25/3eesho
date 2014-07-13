package com.codepath.eesho.fragments;

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

public class DailyPlanFragment extends Fragment{

	ListView lvGoals;
	FragmentPagerAdapter adapterViewPager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_goals_list, container, false);
		
		ViewPager vpPager = (ViewPager) v.findViewById(R.id.vpPager);
		adapterViewPager = new MyPagerAdapter(getChildFragmentManager());
		vpPager.setAdapter(adapterViewPager);
		vpPager.setCurrentItem(1);
		
		return v;
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
	                return new DayPlanFragment(0);
	            case 1: 
	                return new DayPlanFragment(1);
	            case 2: 
	                return new DayPlanFragment(2);
	            default:
	            	return new DayPlanFragment(1);
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
	        	
	        	return "TODAY";
	        }
	        
	    }
}
