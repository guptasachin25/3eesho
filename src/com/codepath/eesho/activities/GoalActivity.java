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
			selectFitness();
			break;
		case R.id.btnRun:
			selectRun();
			break;
		case R.id.btnLoseWeight:
			selectLoseWeight();
			break;
		case R.id.btnContinue:
			onSubmit();
			break;
		}
	}
	
	private void setWeightImage() {
		btnLoseWeight.setText(btnLoseWeight.getHint().toString());
		ivWeightLostIcon.setImageResource(R.drawable.icon_loseweight_active);
	}
	
	private void offWeightImage() {
		btnLoseWeight.setText("");
		ivWeightLostIcon.setImageResource(R.drawable.icon_loseweight_inactive);
	}
	
	private void setRunImage() {
		btnRun.setText(btnRun.getHint().toString());
		ivRunIcon.setImageResource(R.drawable.icon_run_active);
	}
	
	private void offRunImage() {
		btnRun.setText("");
		ivRunIcon.setImageResource(R.drawable.icon_run_inactive);

	}
	
	private void setFitnessImage() {
		btnFitness.setText(btnFitness.getHint().toString());
		ivFitnessIcon.setImageResource(R.drawable.icon_generalhealth_active);
	}
	
	private void offFitnessImage() {
		btnFitness.setText("");
		ivFitnessIcon.setImageResource(R.drawable.icon_generalhealth_inactive);
	}

	private void selectLoseWeight() {
		setWeightImage();
		offFitnessImage();
		offRunImage();
	}

	private void selectRun() {
		offWeightImage();
		setRunImage();
		offFitnessImage();
	}

	private void selectFitness() {
		btnFitness.setText(btnFitness.getHint().toString());
		offWeightImage();
		offRunImage();
		setFitnessImage();
	}
}
