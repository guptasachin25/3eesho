package com.codepath.eesho.activities;

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

public class HomeActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		setupTabs();
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