package com.codepath.eesho;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codepath.eesho.activities.AboutYourselfActivity;
import com.codepath.eesho.activities.HomeActivity;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

public class StartActivity extends Activity {
	Button btnSignUp;
	Button btnLogin;
	
	private static final Integer LOGIN_REQUEST = 80;
	
	private static final Integer SIGNUP_REQUEST = 100;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		/*
		ActivityRecognitionScan scan = new ActivityRecognitionScan(getApplicationContext());
		System.out.println(GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext()) == ConnectionResult.SUCCESS);
		scan.startActivityRecognitionScan();
		*/
		checkLogin();
		setViews();
	}
	
	private void checkLogin() {
		ParseUser user = ParseUser.getCurrentUser();
		if(user != null) {
			Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
			startActivity(intent);
		}
	}
	
	private void setViews() {
		btnSignUp = (Button) findViewById(R.id.btnSignUp);
		btnLogin = (Button) findViewById(R.id.btnLogin);
	}
	
	private void signup() {
		//Intent intent = new Intent(getApplicationContext(), UserGoalActivity.class);
		//startActivity(intent);

		ParseLoginBuilder builder = new ParseLoginBuilder(getApplicationContext());
		startActivityForResult(builder.build(), SIGNUP_REQUEST);
	}
	
	private void login() {
		ParseLoginBuilder builder = new ParseLoginBuilder(getApplicationContext());
		startActivityForResult(builder.build(), LOGIN_REQUEST);
	}
	
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
	}
	
	public void onClick(View view) {
		System.out.println("I came in this function");
		System.out.println(view.getId());
		
		switch(view.getId()) {
		case R.id.btnSignUp:
			signup();
			break;
		case R.id.btnLogin:
			login();
			break;
		}
	}
}
