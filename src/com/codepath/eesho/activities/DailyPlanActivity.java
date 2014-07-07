package com.codepath.eesho.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.eesho.activities.ProfileActivity;
import com.codepath.eesho.R;
import com.codepath.eesho.fragments.DailyPlanFragment;

public class DailyPlanActivity extends FragmentActivity {

	FragmentPagerAdapter adapterViewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daily_plan);
		
		ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
		adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
		vpPager.setAdapter(adapterViewPager);
		vpPager.setCurrentItem(1);
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
	            case 0: // Fragment # 0 - This will show FirstFragment
	                return new DailyPlanFragment();
	            case 1: // Fragment # 0 - This will show FirstFragment different title
	                return new DailyPlanFragment();
	            case 2: // Fragment # 1 - This will show SecondFragment
	                return new DailyPlanFragment();
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
