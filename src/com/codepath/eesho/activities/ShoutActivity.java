package com.codepath.eesho.activities;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.codepath.eesho.R;
import com.codepath.eesho.fragments.DailyWallFragment;
import com.codepath.eesho.fragments.LikesWallFragment;
import com.codepath.eesho.listeners.FragmentTabListener;
import com.codepath.eesho.parse.models.User;
import com.parse.ParseQuery;

public class ShoutActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shout);
		setupTabs();
	}
	
	private void setupTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		
		Tab all = actionBar
				.newTab()
				.setText("@Shouts")
				.setTag("DailyPlanFragment")
				.setTabListener(
						new FragmentTabListener<DailyWallFragment>(R.id.flShoutContainer, this, "@All",
								DailyWallFragment.class));
		
		Tab likes = actionBar.newTab()
				.setText("@Likes")
				.setTag("MyWallFragment")
				.setTabListener(new FragmentTabListener<LikesWallFragment>(R.id.flShoutContainer, this, "@Likes", 
						LikesWallFragment.class));
		
		actionBar.addTab(all);
		actionBar.addTab(likes);
		actionBar.selectTab(all);
	}
}
