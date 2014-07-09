package com.codepath.eesho.activities;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.codepath.eesho.R;
import com.codepath.eesho.fragments.MyClientsFragment;
import com.codepath.eesho.fragments.MyFeedFragment;
import com.codepath.eesho.listeners.FragmentTabListener;

public class ExpertHomeActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expert_home);
		setupTabs();
	}
	
	private void setupTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		
		Tab myClients = actionBar
				.newTab()
				.setText("My Clients")
				//.setIcon(R.drawable.ic_mentions)
				.setTag("My Clients")
				.setTabListener(
				    new FragmentTabListener<MyClientsFragment>(R.id.flHomeContainer, this, "My Clients",
				    		MyClientsFragment.class));
		
		Tab myFeed = actionBar
				.newTab()
				.setText("My Feed")
				//.setIcon(R.drawable.ic_mentions)
				.setTag("My Feed")
				.setTabListener(
				    new FragmentTabListener<MyFeedFragment>(R.id.flHomeContainer, this, "My Feed",
				    		MyFeedFragment.class));
		

		actionBar.addTab(myClients);
		actionBar.addTab(myFeed);
		actionBar.selectTab(myClients);
	}
}
