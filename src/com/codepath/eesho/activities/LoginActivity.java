package com.codepath.eesho.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.eesho.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends Activity {
	EditText etUserName;
	EditText etPassword;
	Button btnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		setViews();
	}

	private void setViews() {
		etUserName = (EditText) findViewById(R.id.etUserName);
		etPassword = (EditText) findViewById(R.id.etPassword);
		btnLogin = (Button) findViewById(R.id.btnLogin);
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

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btnLogin:
			onSubmit();
			break;
		}
	}
}
