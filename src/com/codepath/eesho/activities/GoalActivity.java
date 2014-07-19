package com.codepath.eesho.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codepath.eesho.R;
import com.parse.ParseUser;

public class GoalActivity extends Activity {
	
	Button btnFitness;
	Button btnRun;
	Button btnLoseWeight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goal);
		setViews();
	}
	
	private void setViews() {
		btnFitness = (Button) findViewById(R.id.btnFitness);
		btnRun = (Button) findViewById(R.id.btnRun);
		btnLoseWeight = (Button) findViewById(R.id.btnLoseWeight);
	}
	
	private void saveData(String target) {
		ParseUser currentUser = ParseUser.getCurrentUser();
		currentUser.put("target_type", target);
		currentUser.saveInBackground();
	}
	
	private void clickFitness() {
		saveData("General Fitness");
		startActivity(new Intent(this, HomeActivity.class));
	}
	
	private void clickRun() {
		saveData("Run");
		startActivity(new Intent(this, RunTargetActivity.class));
	}
	
	private void clickLoseWeight() {
		saveData("Lose Weight");
		startActivity(new Intent(this, WeightTargetActivity.class));
	}
	
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btnFitness:
			clickFitness();
			break;
		case R.id.btnRun:
			clickRun();
			break;
		case R.id.btnLoseWeight:
			clickLoseWeight();
			break;
		}
	}
}
