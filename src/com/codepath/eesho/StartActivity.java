package com.codepath.eesho;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.codepath.eesho.activities.AboutYourselfActivity;
import com.codepath.eesho.activities.HomeActivity;
import com.codepath.eesho.activities.LoginActivity;
import com.codepath.eesho.activities.SignupActivity;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class StartActivity extends Activity {
	Button btnLogin;
	Button btnFacebook;

	public static final Integer LOGIN_REQUEST = 80;
	public static final Integer SIGNUP_REQUEST = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		getActionBar().hide();
		setViews();

		/*
		ActivityRecognitionScan scan = new ActivityRecognitionScan(getApplicationContext());
		System.out.println(GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext()) == ConnectionResult.SUCCESS);
		scan.startActivityRecognitionScan();
		 */

		checkLogin(false);
	}

	private void checkLogin(boolean newUser) {
		ParseUser user = ParseUser.getCurrentUser();
		if(user != null) {
			if(!newUser) {
				Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
				startActivity(intent);
			} else {
				Intent intent = new Intent(getApplicationContext(), AboutYourselfActivity.class);
				startActivity(intent);
			}
		}
	}

	private void setViews() {
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnFacebook = (Button) findViewById(R.id.btnFacebook);
	}

	private void signup() {
		//Intent intent = new Intent(getApplicationContext(), UserGoalActivity.class);
		//startActivity(intent);
		//ParseLoginBuilder builder = new ParseLoginBuilder(getApplicationContext());
		//startActivityForResult(builder.build(), SIGNUP_REQUEST);
		Intent intent = new Intent(this, SignupActivity.class);
		startActivity(intent);
	}

	private void login() {
		//ParseLoginBuilder builder = new ParseLoginBuilder(getApplicationContext());
		//startActivityForResult(builder.build(), LOGIN_REQUEST);
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}

	/*
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// REQUEST_CODE is defined above
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (resultCode == RESULT_OK) {
			if(requestCode == LOGIN_REQUEST) {
				// Extract name value from result extras
				String name = currentUser.getString("name");
				if(name == null) {
					name = currentUser.toString();
				}
				Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
				checkLogin();
			} else if(requestCode == SIGNUP_REQUEST) {
				Intent intent = new Intent(getApplicationContext(), AboutYourselfActivity.class);
				startActivity(intent);
			}
		}
	}*/

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
	}

	private void facebookLogin() {
		ParseFacebookUtils.logIn(this, new LogInCallback() {
			@Override
			public void done(ParseUser user, ParseException exception) {
				if (user == null) {
					Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
				} else if (user.isNew()) {
					Log.d("MyApp", "User signed up and logged in through Facebook!");
					makeMeRequest(ParseFacebookUtils.getSession());
					checkLogin(true);
				} else {
					Log.d("MyApp", "User logged in through Facebook!");
					makeMeRequest(ParseFacebookUtils.getSession());
					checkLogin(false);
				}
			}				
		});
	}

	private void makeMeRequest(final Session session) {
		// Make an API call to get user data and define a 
		// new callback to handle the response.
		Request request = Request.newMeRequest(session, 
				new Request.GraphUserCallback() {
			@Override
			public void onCompleted(GraphUser user, Response response) {
				// If the response is successful
				if (session == Session.getActiveSession()) {
					if (user != null) {
						// Set the id for the ProfilePictureView
						// view that in turn displays the profile picture.
						System.out.println(user.toString());
						ParseUser parseUser = ParseUser.getCurrentUser();
						parseUser.put("name", user.getName());
						parseUser.put("sex", user.getProperty("gender"));
						parseUser.put("facebook_id", user.getId());
						parseUser.saveInBackground(new SaveCallback() {

							@Override
							public void done(ParseException arg0) {
								if(arg0 != null) {
									Log.d("FACEBOOK", arg0.toString());
								}
							}
						});
						//profilePictureView.setProfileId(user.getId());
						// Set the Textview's text to the user's name.
						//userNameView.setText(user.getName());
					}
				}
				if (response.getError() != null) {
					// Handle errors, will do so later.
				}
			}
		});
		request.executeAsync();
	} 

	public void onClick(View view) {
		switch(view.getId()) {
		case R.id.btnLogin:
			login();
			break;
		case R.id.btnFacebook:
			facebookLogin();
		}
	}
}
