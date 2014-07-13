package com.codepath.eesho.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codepath.eesho.R;
import com.codepath.eesho.models.AnonUser;

public class TimeCommitmentActivity extends Activity {
	
	Button btn30MinPerDay;
	Button btn1HourPerDay;
	Button btn5HoursPerWeek;
	Button btn10HoursPerWeek;
	Button btn15HoursPerWeek;
	
	AnonUser anonUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.target_hours);
		anonUser = (AnonUser) getIntent().getSerializableExtra("anonUser");
		setViews();
	}
	
	private void setViews() {
		btn30MinPerDay = (Button) findViewById(R.id.btn30MinPerDay);
		btn1HourPerDay = (Button) findViewById(R.id.btn1HourPerDay);
		btn5HoursPerWeek = (Button) findViewById(R.id.btn5HoursPerWeek);
		btn10HoursPerWeek = (Button) findViewById(R.id.btn10HoursPerWeek);
		btn15HoursPerWeek = (Button) findViewById(R.id.btn15HoursPerWeek);
	}
	
	private void onSubmit() {
		Intent intent = new Intent(this, UserMetricsActivity.class);
		intent.putExtra("anonUser", anonUser);
		startActivity(intent);
	}
	
	private void setData(long value, String unit) {
		anonUser.setTimeCommentmentUnit(unit);
		anonUser.setTimeCommitment(value);
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btn30MinPerDay:
			setData(30, "Day");
			onSubmit();
			break;
		case R.id.btn1HourPerDay:
			setData(60, "Day");
			onSubmit();
			break;
		case R.id.btn15HoursPerWeek:
			setData(900, "Week");
			onSubmit();
			break;
		case R.id.btn5HoursPerWeek:
			setData(300, "Week");
			onSubmit();
			break;
		case R.id.btn10HoursPerWeek:
			setData(600, "Week");
			onSubmit();
			break;
		}
	}

}
