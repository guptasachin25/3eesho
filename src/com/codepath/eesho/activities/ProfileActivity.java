package com.codepath.eesho.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.eesho.R;
import com.codepath.eesho.fragments.UserProfileFragment;
import com.parse.ParseUser;

public class ProfileActivity extends FragmentActivity {
String user_id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		user_id = getIntent().getExtras().getString("currentUserLoggedInfo");
		setupWithUser(user_id);
	}

	private void setupWithUser(String user_id) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		UserProfileFragment fragment = UserProfileFragment.newInstance(user_id);
		Log.d("inprofileactivity", "user profile " + user_id + " after");
		ft.replace(R.id.frameLayoutProfile, fragment);
		ft.commit();
	}
	
	public void onLogout(MenuItem mi){
		ParseUser.logOut();
		Log.d("logout", "user is now loged out");
		//Intent i = new Intent(this, LoginActivity.class);
		  //startActivity(i);
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.logout, menu);
		return true;
    }
}
