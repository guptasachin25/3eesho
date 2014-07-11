package com.codepath.eesho.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.codepath.eesho.R;
import com.codepath.eesho.models.AnonUser;

public class TimeCommitmentActivity extends Activity {
	RadioButton rBtnPerWeek;
	RadioButton rBtnPerDay;
	Button btnNext;
	EditText etNumHours;
	
	AnonUser anonUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.target_hours);
		anonUser = (AnonUser) getIntent().getSerializableExtra("anonUser");
		setViews();
	}
	
	private void setViews() {
		btnNext = (Button) findViewById(R.id.btnNext);
		rBtnPerDay = (RadioButton) findViewById(R.id.rBtnPerDay);
		rBtnPerWeek = (RadioButton) findViewById(R.id.rBtnPerWeek);
		etNumHours = (EditText) findViewById(R.id.etNumHours);
	}
	
	private void onSubmit() {
		if(etNumHours.getText() == null 
				|| etNumHours.getText().toString().equals("")
				|| anonUser.getTimeCommentmentUnit() == null) {
			String warningText = "First enter time commitment values";
			Toast.makeText(this, warningText, Toast.LENGTH_SHORT).show();
			return;
		}
		anonUser.setTimeCommitment(Long.parseLong(etNumHours.getText().toString()));
		Intent intent = new Intent(this, UserMetricsActivity.class);
		intent.putExtra("anonUser", anonUser);
		startActivity(intent);
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btnNext:
			onSubmit();
			break;
		case R.id.rBtnPerDay:
			anonUser.setTimeCommentmentUnit("day");
			break;
		case R.id.rBtnPerWeek:
			anonUser.setTimeCommentmentUnit("week");
			break;
		}
	}

}
