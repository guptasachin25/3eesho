package com.codepath.eesho.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.codepath.eesho.R;
import com.codepath.eesho.fragments.ForgotPasswordDialog;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends FragmentActivity {
	EditText etUserName;
	EditText etPassword;
	Button btnLogin;
	Button btnSignup;
	Button tvForgotPassword;
	ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		getActionBar().hide();
		setViews();
		addListeners();
	}

	private void setViews() {
		etUserName = (EditText) findViewById(R.id.etUserName);
		etPassword = (EditText) findViewById(R.id.etPassword);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnSignup = (Button) findViewById(R.id.btnSignup);
		tvForgotPassword = (Button) findViewById(R.id.tvForgotPassword);
		progressBar = (ProgressBar) findViewById(R.id.pbLoading);
	}

	private void onSubmit() {
		progressBar.setVisibility(ProgressBar.VISIBLE);
		ParseUser.logInInBackground(etUserName.getText().toString(), etPassword.getText().toString(), 
				new LogInCallback() {
			public void done(ParseUser user, ParseException e) {
				progressBar.setVisibility(ProgressBar.INVISIBLE);
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

	private void addListeners() {
		etPassword.addTextChangedListener(new Watcher());
		etUserName.addTextChangedListener(new Watcher());
	}
	
	private class Watcher implements TextWatcher {
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
		}

		@Override
		public void afterTextChanged(Editable s) {
			btnLogin.setEnabled(checkValues());
			btnLogin.refreshDrawableState();
		}
	}

	private boolean checkValues() {
		if(etUserName.getText() == null || etPassword.getText() == null
				|| etUserName.getText().toString().equals("")
				|| etPassword.getText().toString().equals("")) {
			return false;
		}
		return true;
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
		case R.id.btnSignup:
			signup();
			break;
		case R.id.tvForgotPassword:
			forgotPassword();
			break;
		}
	}
}