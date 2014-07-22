package com.codepath.eesho.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.eesho.R;
import com.codepath.eesho.utils.Utils;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends Activity {
	EditText etEmailId;
	EditText etPassword;
	EditText etName;
	EditText etSex;
	Button btnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		getActionBar().hide();
		setViews();
	}
	
	private void setViews() {
		etEmailId = (EditText) findViewById(R.id.etUserName);
		etPassword = (EditText) findViewById(R.id.etPassword);
		etName = (EditText) findViewById(R.id.etName);
		etSex = (EditText) findViewById(R.id.etSex);	
		btnLogin = (Button) findViewById(R.id.btnLogin);
	}

	private void onSubmit() {
		ParseUser user = new ParseUser();
		user.setUsername(etEmailId.getText().toString());
		user.setPassword(etPassword.getText().toString());
		user.setEmail(etEmailId.getText().toString());
		user.put("name", etName.getText().toString());
		user.put("sex", etSex.getText().toString());

		user.signUpInBackground(new SignUpCallback() {
			@Override
			public void done(ParseException exception) {
				if(exception == null) {
					Utils.setPlan(ParseUser.getCurrentUser()); // Check whether user is available here
					Intent intent = new Intent(getApplicationContext(), AboutYourselfActivity.class);
					startActivity(intent);
				} 
			}
		});
	}

	private void selectSex() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Gender")
		.setPositiveButton("Female", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				etSex.setText("Female");
			}
		})
		.setNegativeButton("Male", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// Set gender to male
				etSex.setText("Male");
			}
		})
		.create()
		.show();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnLogin:
			onSubmit();
			break;
		case R.id.etSex:
			selectSex();
			break;
		default:
			break;
		}
		
	}
}
