package com.codepath.eesho.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.eesho.R;
import com.parse.ParseUser;

public class RunTargetActivity extends Activity {
	
	EditText etTargetDistance;
	EditText etTargetTime;
	Button btnContinue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_run_target);
		setViews();
	}
	
	private void setViews() {
		etTargetDistance = (EditText) findViewById(R.id.etTargetDistance);
		etTargetTime = (EditText) findViewById(R.id.etTargetTime);
		btnContinue = (Button) findViewById(R.id.btnContinue);
	}
	
	private void saveData() {
		ParseUser currentUser = ParseUser.getCurrentUser();
		currentUser.put("target_time", Long.parseLong(etTargetTime.getText().toString()));
		currentUser.put("target_run_distance", Long.parseLong(etTargetDistance.getText().toString()));
		currentUser.saveInBackground();
	}
	
	private void doSubmit() {
		saveData();
		Intent intent = new Intent(this, HomeActivity.class);
		intent.putExtra("referer", "SignUp");
		startActivity(intent);
	}
	
	public void onClick(View v) {
		if(v.getId() == R.id.btnContinue) {
			doSubmit();
		}
	}
	
}
