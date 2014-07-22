package com.codepath.eesho.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.eesho.R;
import com.codepath.eesho.StartActivity;
import com.codepath.eesho.fragments.UserProfileFragment;
import com.codepath.eesho.fragments.UserSocialProfileFragment;
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

	private void setupWithUser(String user_name) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		UserSocialProfileFragment fragment = UserSocialProfileFragment.newInstance(user_name);
		Log.d("in profile activity", "user profile " + user_id + " after");
		ft.replace(R.id.frameLayoutProfile, fragment);
		ft.commit();
	}
	
	public void onLogout(MenuItem mi){
		ParseUser.logOut();
		Log.d("logout", "user is now loged out");
		Intent i = new Intent(this, StartActivity.class);
		  startActivity(i);
	}
	
	/*public void onEditProfile(MenuItem mi){
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		UserProfileFragment fragment = UserProfileFragment.newInstance(ParseUser.getCurrentUser().getString("name"));
		ft.replace(R.id.frameLayoutProfile, fragment);
		ft.commit();
	}*/
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   // handle item selection
	   switch (item.getItemId()) {
	      case R.id.miEditProfile:
	    	  FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
	  		UserProfileFragment fragment = UserProfileFragment.newInstance(ParseUser.getCurrentUser().getString("name"));
	  		ft.replace(R.id.frameLayoutProfile, fragment);
	  		ft.commit();
	  		item.setVisible(false);
	         return true;
	      case R.id.miLogout:
	    	  ParseUser.logOut();
	  		Log.d("logout", "user is now loged out");
	  		Intent i = new Intent(this, StartActivity.class);
	  		  startActivity(i);
	  		  return true;
	      default:
	         return super.onOptionsItemSelected(item);
	   }
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.logout, menu);
		return true;
    } 
	
}
