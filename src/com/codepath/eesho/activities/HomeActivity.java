package com.codepath.eesho.activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.joda.time.DateTime;
import org.json.JSONArray;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.codepath.eesho.R;
import com.codepath.eesho.fragments.ActivityHistoryFragment;
import com.codepath.eesho.fragments.ProgressFragment;
import com.codepath.eesho.fragments.UserDashBoardFragment;
import com.codepath.eesho.parse.models.Goal;
import com.codepath.eesho.parse.models.Weight;
import com.codepath.eesho.utils.Utils;
import com.parse.CountCallback;
import com.parse.FindCallback;
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
		populateWeight();
		getPlanView();
	}
	
	public void populateWeight() {
		ParseQuery<Weight> query = ParseQuery.getQuery(Weight.class);
		query.whereEqualTo("user", ParseUser.getCurrentUser());
		
		query.findInBackground(new FindCallback<Weight>() {
			public void done(List<Weight> weights, ParseException e) {
				if (e == null && weights.size() < 1) {
					
					if (weights.size() < 1) {
						final Calendar calendar = Calendar.getInstance();
						SimpleDateFormat formatter = new SimpleDateFormat("MMM dd"); // 3-letter month name & 2-char day of month
						final String d = formatter.format(calendar.getTime());
						
						Weight weight = new Weight(125, d);
						weight.setOwner(ParseUser.getCurrentUser());
						weight.saveInBackground();	
						
						weight = new Weight(127, d);
						weight.setOwner(ParseUser.getCurrentUser());
						weight.saveInBackground();
						
						weight = new Weight(124, d);
						weight.setOwner(ParseUser.getCurrentUser());
						weight.saveInBackground();
						
						weight = new Weight(124, d);
						weight.setOwner(ParseUser.getCurrentUser());
						weight.saveInBackground();
						
						weight = new Weight(126, d);
						weight.setOwner(ParseUser.getCurrentUser());
						weight.saveInBackground();
						
						weight = new Weight(128, d);
						weight.setOwner(ParseUser.getCurrentUser());
						weight.saveInBackground();
						
						weight = new Weight(127, d);
						weight.setOwner(ParseUser.getCurrentUser());
						weight.saveInBackground();
					}

				} else {
					
				}
				
			}
		});
	}

	private void checkAndSetPlan() {
		ParseQuery<Goal> query = ParseQuery.getQuery(Goal.class);
		query.whereEqualTo("user", ParseUser.getCurrentUser());
		query.whereEqualTo("date", new DateTime().toDateMidnight().toDate());

		query.countInBackground(new CountCallback() {
			@Override
			public void done(int arg0, ParseException arg1) {
				Utils.setFitnessPlan(ParseUser.getCurrentUser());
			}
		});
	}

	private void getPlanView() {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		UserDashBoardFragment fragment = new UserDashBoardFragment();
		ft.replace(R.id.flHomeContainer, fragment, "dashboard");
		ft.commit();
	}

	@Override
	public void onBackPressed() {
		final UserDashBoardFragment fragment = (UserDashBoardFragment) getSupportFragmentManager().findFragmentByTag("dashboard");
		if (fragment.isVisible()) {
			// don't go anywhere if already on UserDashBoard
		} else {
			super.onBackPressed();
		}
	}

	public void onProfileView(MenuItem mi) {
		Intent i = new Intent(HomeActivity.this, ProfileActivity.class);
		i.putExtra("currentUserLoggedInfo", ParseUser.getCurrentUser().getString("name"));
		startActivity(i);
		//overridePendingTransition(R.anim.right_in, R.anim.left_out);
	}

	public void onSocialView(MenuItem mi) {
		Intent intent = new Intent(this, ShoutActivity.class);
		startActivity(intent);
		//overridePendingTransition(R.anim.right_in, R.anim.left_out);
		/*
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		WallFragment fragment = new WallFragment();
		ft.replace(R.id.flHomeContainer, fragment);
		ft.commit();*/
	}
	
	public void onPlanView(MenuItem mi) {
		getPlanView();
	}

	private void changeProfileImageIcon() {
		if(ParseUser.getCurrentUser() != null) {
			MenuItem profileImage = (MenuItem) findViewById(R.id.miProfile);
			if(profileImage != null) {
				if(ParseUser.getCurrentUser().getString("sex").equals("female")) {
					profileImage.setIcon(R.drawable.icon_profile_woman);
				} else {
					profileImage.setIcon(R.drawable.icon_profile_man);
				}
			}
		}
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

	public void onClickProfileLayout(View v) {
		Toast.makeText(this, "Set Target", Toast.LENGTH_SHORT);
	}
	
	public void onClickSetTarget(View v) {
		Toast.makeText(this, "Set Target", Toast.LENGTH_SHORT);
	}
}
