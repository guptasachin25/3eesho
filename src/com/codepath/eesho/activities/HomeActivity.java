package com.codepath.eesho.activities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.eesho.R;
import com.codepath.eesho.fragments.ArticleFragment;
import com.codepath.eesho.fragments.DailyPlanFragment;
import com.codepath.eesho.fragments.MyTrainerFragment;
import com.codepath.eesho.fragments.UserDashBoardFragment;
import com.codepath.eesho.listeners.FragmentTabListener;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class HomeActivity extends FragmentActivity {

	JSONArray jsonArray = new JSONArray();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		setupTabs();
		
		/*
		 * the rest of this method was used to populate data 
		 */
//		JSONObject task1 = new JSONObject();
//		try {
//		    task1.put("task", "go for a walk");
//		    task1.put("done", false);
//
//		} catch (JSONException e) {
//		    // TODO Auto-generated catch block
//		    e.printStackTrace();
//		}
//		
//		JSONObject task2 = new JSONObject();
//		try {
//		    task2.put("task", "do 50 jumping jacks");
//		    task2.put("done", false);
//
//		} catch (JSONException e) {
//		    // TODO Auto-generated catch block
//		    e.printStackTrace();
//		}
//		
//
//		jsonArray.put(task1);
//		jsonArray.put(task2);
//
//		Plan goal = new Plan();
//		goal.put("monday_goals", jsonArray);
//		goal.put("username", "calren");
//		goal.saveInBackground();
		
//		ParseQuery<ParseObject> query = ParseQuery.getQuery("Plan");
//		 
//		// Retrieve the object by id
//		query.getInBackground("1VQCtDDDcC", new GetCallback<ParseObject>() {
//		  public void done(ParseObject gameScore, ParseException e) {
//		    if (e == null) {
//		      // Now let's update it with some new data. In this case, only cheatMode and score
//		      // will get sent to the Parse Cloud. playerName hasn't changed.
//		      gameScore.put("su_goals", jsonArray);
//		      gameScore.saveInBackground();
//		    }
//		  }
//		});
	}
	
	private void setupTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		
		Tab myDashboard = actionBar
				.newTab()
				.setText("MyDashBoard")
				//.setIcon(R.drawable.ic_mentions)
				.setTag("MyDashBoard")
				.setTabListener(
				    new FragmentTabListener<UserDashBoardFragment>(R.id.flHomeContainer, this, "MyDashboard",
				    		UserDashBoardFragment.class));
		
		Tab myTrainer = actionBar
				.newTab()
				.setText("MyTrainer")
				//.setIcon(R.drawable.ic_mentions)
				.setTag("MyTrainerFragment")
				.setTabListener(
				    new FragmentTabListener<MyTrainerFragment>(R.id.flHomeContainer, this, "MyTrainer",
				    		MyTrainerFragment.class));

		Tab myPlan = actionBar
			.newTab()
			.setText("MyPlan")
			//.setIcon(R.drawable.ic_home)
			.setTag("DailyPlanFragment")
			.setTabListener(
				new FragmentTabListener<DailyPlanFragment>(R.id.flHomeContainer, this, "MyPlan",
						DailyPlanFragment.class));
	
		

		Tab myArticle = actionBar
				.newTab()
				.setText("MyArticle")
				//.setIcon(R.drawable.ic_mentions)
				.setTag("MyArticleFragment")
				.setTabListener(
				    new FragmentTabListener<ArticleFragment>(R.id.flHomeContainer, this, "MyArticle",
				    		ArticleFragment.class));

		actionBar.addTab(myPlan);
		actionBar.addTab(myDashboard);
		actionBar.addTab(myTrainer);
		actionBar.addTab(myArticle);
		actionBar.selectTab(myPlan);
	}
	

	public void onProfileView(MenuItem mi) {
		Intent i = new Intent(HomeActivity.this, ProfileActivity.class);
		startActivity(i);
		
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.profile, menu);
		return true;
    }
}
