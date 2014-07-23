package com.codepath.eesho.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codepath.eesho.R;
import com.parse.ParseUser;

public class GoalActivity extends Activity {

	TextView btnFitness;
	TextView btnRun;
	TextView btnLoseWeight;
	Button btnContinue;
	ImageView ivFitnessIcon;
	ImageView ivRunIcon;
	ImageView ivWeightLostIcon;
	ProgressBar progressBar;
	
	boolean selected = false;
	int selectedValue = -1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goal_new);
		getActionBar().hide();
		setViews();
	}

	private void setViews() {
		btnFitness = (TextView) findViewById(R.id.btnFitness);
		btnRun = (TextView) findViewById(R.id.btnRun);
		btnLoseWeight = (TextView) findViewById(R.id.btnLoseWeight);
		btnContinue = (Button) findViewById(R.id.btnContinue);
		ivFitnessIcon = (ImageView) findViewById(R.id.ivGeneralFitness);
		ivRunIcon = (ImageView) findViewById(R.id.ivRunIcon);
		ivWeightLostIcon = (ImageView) findViewById(R.id.ivLoseWeightIcon);
		progressBar = (ProgressBar) findViewById(R.id.pbLoading);
	}

	private void saveData(String target) {
		ParseUser currentUser = ParseUser.getCurrentUser();
		currentUser.put("target_type", target);
		currentUser.saveInBackground();
	}

	private void clickFitness() {
		saveData("General Fitness");
		progressBar.setVisibility(ProgressBar.VISIBLE);
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		//overridePendingTransition(R.anim.right_in, R.anim.left_out);
	}

	private void clickRun() {
		saveData("Run");
		startActivity(new Intent(this, RunTargetActivity.class));
		//overridePendingTransition(R.anim.right_in, R.anim.left_out);
	}

	private void clickLoseWeight() {
		saveData("Lose Weight");
		startActivity(new Intent(this, WeightTargetActivity.class));
		//overridePendingTransition(R.anim.right_in, R.anim.left_out);
	}


	private void enableContinueButton() {
		if(selected) {
			btnContinue.setEnabled(true);
		} else {
			btnContinue.setEnabled(false);
		}
	}

	private void onSubmit() {
		if(btnContinue.isEnabled()) {
			if(selectedValue == 1) {
				clickLoseWeight();
			} else if(selectedValue == 2) {
				clickRun();
			} else {
				clickFitness();
			}
		}
	}

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btnFitness:
			selectFitness();
			enableContinueButton();
			break;
		case R.id.btnRun:
			selectRun();
			enableContinueButton();
			break;
		case R.id.btnLoseWeight:
			selectLoseWeight();
			enableContinueButton();
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
		selectedValue = 1;
		selected = true;
		setWeightImage();
		offFitnessImage();
		offRunImage();
	}

	private void selectRun() {
		selectedValue = 2;
		selected = true;
		offWeightImage();
		setRunImage();
		offFitnessImage();
	}

	private void selectFitness() {
		selectedValue = 3;
		selected = true;
		offWeightImage();
		offRunImage();
		setFitnessImage();
	}
}
