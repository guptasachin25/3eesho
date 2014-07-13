package com.codepath.eesho.activities;

import com.codepath.eesho.R;
import com.codepath.eesho.fragments.UserProfileFragment;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.codepath.eesho.parse.models.User;



import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class ProfileActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		// Make the user variable Of User type after we set up the database.
		ParseQuery<User> query = ParseQuery.getQuery(User.class);
		query.getInBackground("ApsFqMAfrE", new GetCallback<User>() {
			
			@Override
			public void done(User user, ParseException e) {
				// TODO Auto-generated method stub
				if(e == null){
					//access the data using get method
					setupWithUser(user);
					Log.d("inprofileactivity", "user profile " + user.getUsernname() + " before");
					
				}else {
					//Toast.makeText(getCallingActivity(), "Cannot get the user data information from parse", Toast.LENGTH_SHORT);
				}
			}
		});
		//Long user = (long) 1234;
		
	}

	private void setupWithUser(User user) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		UserProfileFragment fragment = UserProfileFragment.newInstance(user.getobjectId());
		Log.d("inprofileactivity", "user profile " + user.getobjectId() + " after");
		ft.replace(R.id.frameLayoutProfile, fragment);
		ft.commit();
		
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.logout, menu);
		return true;
    }
}
