package com.codepath.eesho.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.eesho.R;
import com.codepath.eesho.models.AnonUser;

public class WeightActivity extends Activity {
	
	EditText etWeight;
	Button btnLb;
	Button btnKg;
	
	AnonUser anonUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weight_target);
		Intent intent = getIntent();
		anonUser = (AnonUser) intent.getSerializableExtra("anonUser");
		setViews();
	}
	
	private void setViews() {
		etWeight = (EditText) findViewById(R.id.etWeight);
		btnLb = (Button) findViewById(R.id.btnLb);
		btnKg = (Button) findViewById(R.id.btnKg);
	}
	
	private void onSubmit() {
		if(anonUser.getWeightTargetUnit() == null 
				|| etWeight.getText() == null 
				|| etWeight.getText().toString().equals("")) {
			String warningText = "Enter value of weight and target units before submitting";
			Toast.makeText(getApplicationContext(), warningText, Toast.LENGTH_SHORT).show();
			return;
		}
		anonUser.setWeightTarget(Long.parseLong(etWeight.getText().toString()));
		Intent intent = new Intent(this, UserTimeGoalActivity.class);
		intent.putExtra("anonUser", anonUser);
		startActivity(intent);		
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btnLb:
			anonUser.setWeightTargetUnit("KG");
			break;
		case R.id.btnKg:
			anonUser.setWeightTargetUnit("KG");
			break;
		case R.id.btnNext:
			onSubmit();
		}
	}
}
