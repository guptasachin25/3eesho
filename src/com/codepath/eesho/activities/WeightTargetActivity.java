package com.codepath.eesho.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.eesho.R;
import com.parse.ParseUser;

public class WeightTargetActivity extends Activity {

	EditText etTargetWeight;
	EditText etTargetTime;
	Button btnContinue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weight_target);
		setViews();
	}
	
	private void setViews() {
		etTargetWeight = (EditText) findViewById(R.id.etTargetWeight);
		etTargetTime = (EditText) findViewById(R.id.etTargetTime);
		btnContinue = (Button) findViewById(R.id.btnContinue);
	}
	
	private void saveData() {
		ParseUser currentUser = ParseUser.getCurrentUser();
		System.out.println(etTargetTime.getText().toString());
		System.out.println(etTargetWeight.getText().toString());
		currentUser.put("target_time", Long.parseLong(etTargetTime.getText().toString()));
		currentUser.put("target_weight", Long.parseLong(etTargetWeight.getText().toString()));
		
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
