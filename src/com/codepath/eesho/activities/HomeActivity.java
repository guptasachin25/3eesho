package com.codepath.eesho.activities;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.codepath.eesho.R;
import com.codepath.eesho.fragments.ActivityHistoryFragment;
import com.codepath.eesho.fragments.ProgressFragment;
import com.codepath.eesho.fragments.UserDashBoardFragment;
import com.codepath.eesho.fragments.UserSocialProfileFragment;
import com.codepath.eesho.fragments.WallFragment;
import com.codepath.eesho.listeners.FragmentTabListener;
import com.codepath.eesho.models.FitnessPlanSingleActivity;
import com.codepath.eesho.parse.models.Goal;
import com.codepath.eesho.parse.models.Messages;
import com.codepath.eesho.utils.Utils;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class HomeActivity extends FragmentActivity {
	JSONArray jsonArray = new JSONArray();
	ParseUser currentUser;
	String referer = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		checkAndSetPlan();
		getPlanView();
	}
	
	private void checkAndSetPlan() {
		ParseQuery<Goal> query = ParseQuery.getQuery(Goal.class);
		query.whereEqualTo("user", ParseUser.getCurrentUser());
		query.whereEqualTo("date", new DateTime().toDateMidnight().toDate());

		try {
			if(query.count() == 0) {
				Utils.setFitnessPlan(ParseUser.getCurrentUser());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void getPlanView() {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		UserDashBoardFragment fragment = new UserDashBoardFragment();
		ft.replace(R.id.flHomeContainer, fragment);
		ft.commit();
	}
	
	public void onProfileView(MenuItem mi) {
		/*FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		//UserProfileFragment fragment = UserProfileFragment.newInstance(ParseUser.getCurrentUser().getString("name"));
		UserSocialProfileFragment fragment = UserSocialProfileFragment.newInstance(ParseUser.getCurrentUser().getString("name"));
		Log.d("in profile activity", "user profile " + ParseUser.getCurrentUser() + " after");
		ft.replace(R.id.flHomeContainer, fragment);
		ft.addToBackStack(null);
		ft.commit();
		*/
		
		Intent i = new Intent(HomeActivity.this, ProfileActivity.class);
		i.putExtra("currentUserLoggedInfo", ParseUser.getCurrentUser().getString("name"));
		startActivity(i);
	}

	public void onSocialView(MenuItem mi) {
		Intent intent = new Intent(this, ShoutActivity.class);
		startActivity(intent);
		/*
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		WallFragment fragment = new WallFragment();
		ft.replace(R.id.flHomeContainer, fragment);
		ft.commit();*/
	}
	
	public void onPlanView(MenuItem mi) {
		getPlanView();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

	public void goToActivityHistory(View v) {
		FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
		fts.replace(R.id.flHomeContainer, new ActivityHistoryFragment());	
		fts.addToBackStack(null);
		fts.commit();
	}

	public void openProgress(View v) {
		FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
		fts.replace(R.id.flHomeContainer, new ProgressFragment());	
		fts.addToBackStack(null);
		fts.commit();
	}
}
