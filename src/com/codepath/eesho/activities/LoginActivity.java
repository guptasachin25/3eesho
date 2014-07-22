package com.codepath.eesho.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.eesho.R;
import com.codepath.eesho.fragments.ForgotPasswordDialog;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class LoginActivity extends FragmentActivity {
	EditText etUserName;
	EditText etPassword;
	Button btnLogin;
	TextView tvSignup;
	Button tvForgotPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		getActionBar().hide();
		setViews();
	}

	private void setViews() {
		etUserName = (EditText) findViewById(R.id.etUserName);
		etPassword = (EditText) findViewById(R.id.etPassword);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		tvSignup = (TextView) findViewById(R.id.tvSignup);
		tvForgotPassword = (Button) findViewById(R.id.tvForgotPassword);
	}

	private void onSubmit() {
		ParseUser.logInInBackground(etUserName.getText().toString(), etPassword.getText().toString(), 
				new LogInCallback() {
			public void done(ParseUser user, ParseException e) {
				if (user != null) {
					Log.d("MyApp", "User is logged in");
					Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
					startActivity(intent);
				} else {
					Log.d("MyApp", "User is not logged in");
					Log.d("MyApp", e.getMessage());
				}
			}
		});
	}
	
	private void forgotPassword() {
		System.out.println("Forgot Password");
	    FragmentManager fm = getSupportFragmentManager();
	    ForgotPasswordDialog editNameDialog = ForgotPasswordDialog.newInstance("Forgot Password?");
	    editNameDialog.show(fm, "fragment_edit_name");
	    /*
		ParseUser.requestPasswordResetInBackground("myemail@example.com",
				new RequestPasswordResetCallback() {
			public void done(ParseException e) {
				if (e == null) {
					// An email was successfully sent with reset instructions.
				} else {
					// Something went wrong. Look at the ParseException to see what's up.
				}
			}
		});*/
	}
	
	private void signup() {
		Intent intent = new Intent(this, SignupActivity.class);
		startActivity(intent);
	}

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btnLogin:
			onSubmit();
			break;
		case R.id.tvSignup:
			signup();
			break;
		case R.id.tvForgotPassword:
			forgotPassword();
			break;
		}
	}
}