package com.codepath.eesho.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.codepath.eesho.R;
import com.codepath.eesho.models.AnonUser;

public class UserTimeGoalActivity extends Activity {
	EditText etTargetTime;
	Button btnSubmit;
	Spinner spTimeUnits;
	
	AnonUser anonUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_time_goal_activity);
		anonUser = (AnonUser) getIntent().getSerializableExtra("anonUser");
		setViews();
	}
	
	private void setViews() {
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		etTargetTime = (EditText) findViewById(R.id.etTargetTime);
		spTimeUnits = (Spinner) findViewById(R.id.spTimeUnits);
	}
	
	private void onSubmit() {
		anonUser.setGoalCommitmentTimeUnit(spTimeUnits.getSelectedItem().toString());
		if(etTargetTime.getText() == null 
				|| etTargetTime.getText().toString().equals("")
				|| anonUser.getGoalCommitmentTimeUnit() == null) {
			String warningText = "Please enter Goal time and units";
			Toast.makeText(this, warningText, Toast.LENGTH_SHORT).show();
			return;
		}
		anonUser.setGoalCommitment(Long.parseLong(etTargetTime.getText().toString()));
		
		Intent intent = new Intent(this, TimeCommitmentActivity.class);
		intent.putExtra("anonUser", anonUser);
		startActivity(intent);
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btnSubmit:
			onSubmit();
		
		}
	}
	
	
}
