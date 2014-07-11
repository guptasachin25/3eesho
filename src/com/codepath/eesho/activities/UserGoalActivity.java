package com.codepath.eesho.activities;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codepath.eesho.R;
import com.codepath.eesho.models.AnonUser;

public class UserGoalActivity extends Activity {
	Button btnGainWeight;
	Button btnLoseWeight;
	Button btnRunMarathon;
	Button btnFitness;
	
	AnonUser anonUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_goal);
		setViews();
	}
	
	private void setViews() {
		btnGainWeight = (Button) findViewById(R.id.btnGainWeight);
		btnLoseWeight = (Button) findViewById(R.id.btnLoseWeight);
		btnRunMarathon = (Button) findViewById(R.id.btnRunMarathon);
		btnFitness = (Button) findViewById(R.id.btnFitness);
		anonUser = new AnonUser();
	}
	
	public void onBtnClick(View v) {
		switch(v.getId()) {
		case R.id.btnGainWeight:
			gainWeightTargetFn();
			break;
		case R.id.btnLoseWeight:
			loseWeightTargetFn();
			break;
		case R.id.btnRunMarathon:
			runMarathon();
			break;
		case R.id.btnFitness:
			btnFitness();
		}
	}

	private void gainWeightTargetFn() {
		anonUser.setGoal("Gain Weight");
		Intent intent = new Intent(this, WeightActivity.class);
		intent.putExtra("anonUser", anonUser);
		startActivity(intent);	
	}

	private void loseWeightTargetFn() {
		anonUser.setGoal("Lose Weight");
		Intent intent = new Intent(this, WeightActivity.class);
		intent.putExtra("anonUser", anonUser);
		startActivity(intent);
	}

	private void runMarathon() {
		anonUser.setGoal("Run Marathon");
		Intent intent = new Intent(this, UserTimeGoalActivity.class);
		intent.putExtra("anonUser", anonUser);
		startActivity(intent);		
	}

	private void btnFitness() {
		anonUser.setGoal("General Fitness");
		Intent intent = new Intent(this, TimeCommitmentActivity.class);
		intent.putExtra("anonUser", anonUser);
		startActivity(intent);		
	}
}
