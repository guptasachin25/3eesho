package com.codepath.eesho.activities;

import com.codepath.eesho.R;
import com.codepath.eesho.fragments.UserProfileFragment;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class ProfileActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		// Make the user variable Of User type after we set up the database. 
		Long user = (long) 1234;
		setupWithUser(user);
	}

	private void setupWithUser(long user) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		UserProfileFragment fragment = UserProfileFragment.newInstance(user);
		ft.replace(R.id.frameLayoutProfile, fragment);
		ft.commit();
		
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.logout, menu);
		return true;
    }
}
