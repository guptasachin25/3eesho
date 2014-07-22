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
import com.codepath.eesho.fragments.UserProfileFragment;
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
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		UserProfileFragment fragment = UserProfileFragment.newInstance(ParseUser.getCurrentUser().getString("name"));
		//UserSocialProfileFragment fragment = UserSocialProfileFragment.newInstance(ParseUser.getCurrentUser().getString("name"));
		Log.d("in profile activity", "user profile " + ParseUser.getCurrentUser() + " after");
		ft.replace(R.id.flHomeContainer, fragment);
		ft.addToBackStack(null);
		ft.commit();
		
		/*
		Intent i = new Intent(HomeActivity.this, ProfileActivity.class);
		i.putExtra("currentUserLoggedInfo", currentUser.getObjectId());
		Log.d("inHomeactivity", "user id is " + currentUser.getObjectId() + " before");
		startActivity(i);*/
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

	@SuppressWarnings("deprecation")
	public void onShout(MenuItem mi) {
		Toast.makeText(getApplicationContext(), "Shout!!!", Toast.LENGTH_SHORT).show();
		DateTime date = new DateTime();
		ParseQuery<Goal> query = ParseQuery.getQuery(Goal.class);
		query.whereEqualTo("user", ParseUser.getCurrentUser());
		query.whereEqualTo("date", date.toDateMidnight().toDate());

		query.getFirstInBackground(new GetCallback<Goal>() {
			public void done(Goal goal, ParseException e) {
				if (e == null) {
					try {
						StringBuilder builder = new StringBuilder();
						builder.append("<font color='#01DFD7'><b>" + ParseUser.getCurrentUser().getString("name") + 
								"</b></font>" +" did ");
						boolean flag = false;
						for(FitnessPlanSingleActivity activity: goal.getDailyActivity().getActivityList()) {
							if(activity.isDone()) {
								if(activity.getDescription().toLowerCase().contains("run")) {
									Messages message = new Messages();
									message.setMessage(String.format("<font color='#01DFD7'><b>%s</b></font> ran %d miles", 
											ParseUser.getCurrentUser().getString("name"), activity.getDistance()));

									message.setSender(ParseUser.getCurrentUser());
									message.saveInBackground();
									continue;
								} else if(activity.getDescription().toLowerCase().contains("walk")) {
									Messages message = new Messages();
									message.setMessage(String.format("<font color='#01DFD7'><b>%s</b></font> walked for %d minutes", 
											ParseUser.getCurrentUser().getString("name"), activity.getDuration()));
									message.setSender(ParseUser.getCurrentUser());
									message.saveInBackground();
									continue;
								} else {
									flag = true;
									builder.append(activity.getRepititions() * activity.getSets() + " " + 
											activity.getDescription() + ", ");
								}
							}
						}
						
						if(flag) {
							Messages message = new Messages();
							String m = builder.toString().trim();
							message.setMessage(m.substring(0, m.length()-1) + ".");
							message.setSender(ParseUser.getCurrentUser());
							message.saveInBackground();
						}

					} catch (JSONException e1) {
						e1.printStackTrace();
					}
				} else {
					Log.d("item", "Error: " + e.getMessage());
				}
			}
		});
		onSocialView(null);
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
	

	private void setupTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		/*
		Tab myDashboard = actionBar
				.newTab()
				.setText("MyDashBoard")
				//.setIcon(R.drawable.ic_mentions)
				.setTag("MyDashBoard")
				.setTabListener(
				    new FragmentTabListener<UserDashBoardFragment>(R.id.flHomeContainer, this, "MyDashboard",
				    		UserDashBoardFragment.class));
		 */
		/*
		Tab myTrainer = actionBar
				.newTab()
				.setText("MyTrainer")
				//.setIcon(R.drawable.ic_mentions)
				.setTag("MyTrainerFragment")
				.setTabListener(
				    new FragmentTabListener<MyTrainerFragment>(R.id.flHomeContainer, this, "MyTrainer",
				    		MyTrainerFragment.class));
		 */

		Tab myPlan = actionBar
				.newTab()
				.setText("My Plan")
				//.setIcon(R.drawable.ic_home)
				.setTag("DailyPlanFragment")
				.setTabListener(
						new FragmentTabListener<UserDashBoardFragment>(R.id.flHomeContainer, this, "MyPlan",
								UserDashBoardFragment.class));
		/*
		Tab myArticle = actionBar
				.newTab()
				.setText("MyArticle")
				//.setIcon(R.drawable.ic_mentions)
				.setTag("MyArticleFragment")
				.setTabListener(
				    new FragmentTabListener<ArticleFragment>(R.id.flHomeContainer, this, "MyArticle",
				    		ArticleFragment.class));
		 */
		Tab myWall = actionBar.newTab()
				.setText("Social")
				.setTag("MyWallFragment")
				.setTabListener(new FragmentTabListener<WallFragment>(R.id.flHomeContainer, this, "MyWall", WallFragment.class));

		actionBar.addTab(myPlan);
		//actionBar.addTab(myDashboard);
		//actionBar.addTab(myTrainer);
		//actionBar.addTab(myArticle);
		actionBar.addTab(myWall);
		actionBar.selectTab(myPlan);
        }

	public void openProgress(View v) {
		FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
		fts.replace(R.id.flHomeContainer, new ProgressFragment());	
		fts.addToBackStack(null);
		fts.commit();
	}
}
