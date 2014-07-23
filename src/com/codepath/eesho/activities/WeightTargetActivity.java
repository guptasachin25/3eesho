package com.codepath.eesho.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.codepath.eesho.R;
import com.parse.ParseUser;

public class WeightTargetActivity extends Activity {

	EditText etTargetWeight;
	EditText etTargetTime;
	Button btnContinue;
	ImageView ivTimeTargetIcon;
	ImageView ivWeightTargetIcon;
	
	private String weightUnit = "lbs";
	private String timeUnit = "months";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weight_target_new);
		getActionBar().hide();
		setViews();
		setupListeners();
		setFocusChangeListeners();
	}

	private void setViews() {
		etTargetWeight = (EditText) findViewById(R.id.etWeightTarget);
		etTargetTime = (EditText) findViewById(R.id.etTargetTime);
		btnContinue = (Button) findViewById(R.id.btnContinue);
		ivTimeTargetIcon = (ImageView) findViewById(R.id.ivTargetTimeIcon);
		ivWeightTargetIcon = (ImageView) findViewById(R.id.ivTargetWeightIcon);
	}

	private void saveData() {
		ParseUser currentUser = ParseUser.getCurrentUser();
		currentUser.put("target_time", Long.parseLong(etTargetTime.getText().toString().replace(timeUnit, "").trim()));
		currentUser.put("target_weight", Long.parseLong(etTargetWeight.getText().toString().replace(weightUnit, "").trim()));
		currentUser.saveInBackground();
	}

	private void doSubmit() {
		saveData();
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		//overridePendingTransition(R.anim.right_in, R.anim.left_out);
	}

	public void onClick(View v) {
		if(v.getId() == R.id.btnContinue) {
			doSubmit();
		}
	}

	private class Watcher implements TextWatcher {
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {}

		@Override
		public void afterTextChanged(Editable s) {
			btnContinue.setEnabled(checkValues());
			btnContinue.refreshDrawableState();
			changeImages();
		}
	}

	private boolean checkValues() {
		if(isNullorEmpty(etTargetWeight) || isNullorEmpty(etTargetTime)) {
			return false;
		}
		return true;
	}

	private boolean isNullorEmpty(EditText et) {
		if(et.getText() == null || et.getText().toString().equals("")) {
			return true;
		}
		return false;
	}

	private void changeImages() {
		if(isNullorEmpty(etTargetWeight)) {
			offWeightIcon();
		} else {
			setWeightIcon();
		}

		if(isNullorEmpty(etTargetTime)) {
			offTimeIcon();
		} else {
			setTimeIcon();
		} 
	}

	private void setWeightIcon() {
		ivWeightTargetIcon.setImageResource(R.drawable.icon_loseweight_active);
	}

	private void offWeightIcon() {
		ivWeightTargetIcon.setImageResource(R.drawable.icon_loseweight_inactive);
	}


	private void setTimeIcon() {
		ivTimeTargetIcon.setImageResource(R.drawable.icon_time_active);
	}

	private void offTimeIcon() {
		ivTimeTargetIcon.setImageResource(R.drawable.icon_time_inactive);
	}


	private void setupListeners() {
		etTargetWeight.addTextChangedListener(new Watcher());
		etTargetTime.addTextChangedListener(new Watcher());
	}

	private void setFocusChangeListeners() {
		etTargetWeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				EditText view = (EditText) v;
				if (!isNullorEmpty(view) && !view.getText().toString().contains(weightUnit)) {
					String height = ((EditText) v).getText().toString();					
					etTargetWeight.setText(height + " " + weightUnit);
				}
			}
		});

		etTargetTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				EditText view = (EditText) v;
				if (!isNullorEmpty(view) && !view.getText().toString().contains(timeUnit)) {
					String weight = ((EditText) v).getText().toString();					
					etTargetTime.setText(weight + " " + timeUnit);
				}
			}
		});
	}
}
