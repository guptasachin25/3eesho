package com.codepath.eesho.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
	Button btnSignup;
	Button btnSignin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		getActionBar().hide();
		setViews();
		setUpListeners();
	}
	
	private void setViews() {
		etEmailId = (EditText) findViewById(R.id.etUserName);
		etPassword = (EditText) findViewById(R.id.etPassword);
		etName = (EditText) findViewById(R.id.etName);
		etSex = (EditText) findViewById(R.id.etSex);	
		btnSignup = (Button) findViewById(R.id.btnSignup);
		btnSignin = (Button) findViewById(R.id.btnSignin);
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
				} else {
					System.out.println(exception.getMessage());
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
	
	private void signin() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
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
			checkValues();
			btnSignup.setEnabled(checkValues());
			btnSignup.refreshDrawableState();
		}
	}
	
	private void setUpListeners() {
		etEmailId.addTextChangedListener(new Watcher());
		etPassword.addTextChangedListener(new Watcher());
		etSex.addTextChangedListener(new Watcher());
		etName.addTextChangedListener(new Watcher());
	}
	
	private boolean checkValues() {
		if(etEmailId.getText() == null || etEmailId.getText().toString().equals("") ||
				etPassword.getText() == null || etPassword.getText().toString().equals("") ||
				etSex.getText() == null || etSex.getText().toString().equals("") ||
				etName.getText() == null || etName.getText().toString().equals("")) {
			return false;
		}
		return true;
	}


	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSignup:
			onSubmit();
			break;
		case R.id.etSex:
			selectSex();
			break;
		case R.id.btnSignin:
			signin();
		default:
			break;
		}
		
	}
}
