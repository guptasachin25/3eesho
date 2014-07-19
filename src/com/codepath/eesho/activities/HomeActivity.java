package com.codepath.eesho.activities;

import org.json.JSONArray;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.codepath.eesho.R;
import com.codepath.eesho.fragments.ActivityHistoryFragment;
import com.codepath.eesho.fragments.UserDashBoardFragment;
import com.codepath.eesho.fragments.WallFragment;
import com.codepath.eesho.listeners.FragmentTabListener;
import com.codepath.eesho.parse.models.Messages;
import com.parse.ParseUser;


public class HomeActivity extends FragmentActivity {
	JSONArray jsonArray = new JSONArray();
	ParseUser currentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		getCurrentUser();
	}
	
	private void getCurrentUser() {
		currentUser = ParseUser.getCurrentUser();
		if (currentUser != null) {
			setupTabs();
		} else {
		  // show the signup or login screen
		}		
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
			.setText("MyPlan")
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
				.setText("My Wall")
				.setTag("MyWallFragment")
				.setTabListener(new FragmentTabListener<WallFragment>(R.id.flHomeContainer, this, "MyWall", WallFragment.class));
		

		actionBar.addTab(myPlan);
		//actionBar.addTab(myDashboard);
		//actionBar.addTab(myTrainer);
		//actionBar.addTab(myArticle);
		actionBar.addTab(myWall);
		actionBar.selectTab(myPlan);
	}
	
	public void onProfileView(MenuItem mi) {
		Intent i = new Intent(HomeActivity.this, ProfileActivity.class);
		i.putExtra("currentUserLoggedInfo", currentUser.getObjectId());
		Log.d("inHomeactivity", "user id is " + currentUser.getObjectId() + " before");
		startActivity(i);
	}
	
	private String getMessage() {
		return "Shout";
	}
	
	public void onShout(MenuItem mi) {
		Toast.makeText(getApplicationContext(), "Shout!!!", Toast.LENGTH_SHORT).show();
		Messages message = new Messages();
		message.setMessage(getMessage());
		message.setSender(ParseUser.getCurrentUser());
		message.saveInBackground();
		
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
}
