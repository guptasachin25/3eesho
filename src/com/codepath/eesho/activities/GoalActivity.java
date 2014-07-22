package com.codepath.eesho.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.codepath.eesho.R;
import com.parse.ParseUser;

public class GoalActivity extends Activity {
	
	EditText btnFitness;
	EditText btnRun;
	EditText btnLoseWeight;
	Button btnContinue;
	ImageView ivFitnessIcon;
	ImageView ivRunIcon;
	ImageView ivWeightLostIcon;
	
	EditText selected;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goal_new);
		setViews();
	}
	
	private void setViews() {
		btnFitness = (EditText) findViewById(R.id.btnFitness);
		btnRun = (EditText) findViewById(R.id.btnRun);
		btnLoseWeight = (EditText) findViewById(R.id.btnLoseWeight);
		btnContinue = (Button) findViewById(R.id.btnContinue);
		ivFitnessIcon = (ImageView) findViewById(R.id.ivLoseWeightIcon);
		ivRunIcon = (ImageView) findViewById(R.id.ivRunIcon);
		ivWeightLostIcon = (ImageView) findViewById(R.id.ivWeightIcon);
	}
	
	private void saveData(String target) {
		ParseUser currentUser = ParseUser.getCurrentUser();
		currentUser.put("target_type", target);
		currentUser.saveInBackground();
	}
	
	private void clickFitness() {
		saveData("General Fitness");
		Intent intent = new Intent(this, HomeActivity.class);
		intent.putExtra("referer", "SignUp");
		startActivity(intent);
	}
	
	private void clickRun() {
		saveData("Run");
		startActivity(new Intent(this, RunTargetActivity.class));
	}
	
	private void clickLoseWeight() {
		saveData("Lose Weight");
		startActivity(new Intent(this, WeightTargetActivity.class));
	}
	
	private void onSubmit() {
	
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
		case R.id.btnContinue:
			onSubmit();
			break;
		}
	}
}
