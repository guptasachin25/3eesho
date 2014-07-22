package com.codepath.eesho.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.codepath.eesho.R;
import com.parse.ParseUser;

public class AboutYourselfActivity extends Activity {
	EditText etWeight;
	EditText etHeight;
	EditText etActivityLevel;
	Button btnContinue;
	ImageView ivHeightIcon;
	ImageView ivWeightIcon;
	ImageView ivActivityIcon;

	String weightUnit = "lbs";
	String heightUnit = "cms";
	ParseUser currentUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yourself);
		getActionBar().hide();
		setViews();
		setupListeners();
		setFocusChangeListeners();
	}

	private void setViews() {
		etWeight = (EditText) findViewById(R.id.etWeight);
		etHeight = (EditText) findViewById(R.id.etHeight);
		etActivityLevel = (EditText) findViewById(R.id.etActivityLevel);
		btnContinue = (Button) findViewById(R.id.btnContinue);
		ivHeightIcon = (ImageView) findViewById(R.id.ivHeightIcon);
		ivWeightIcon = (ImageView) findViewById(R.id.ivWeightIcon);
		ivActivityIcon = (ImageView) findViewById(R.id.ivActivityIcon);
	}
	
	
	private void setWeightIcon() {
		ivWeightIcon.setImageResource(R.drawable.icon_weight_active);
	}
	
	private void offWeightIcon() {
		ivWeightIcon.setImageResource(R.drawable.icon_weight_inactive);
	}
	
	private void setHeighIcon() {
		ivHeightIcon.setImageResource(R.drawable.icon_height_active);
	}
	
	private void offHeightIcon() {
		ivHeightIcon.setImageResource(R.drawable.icon_height_inactive);
	}
	
	private void offActivityIcon() {
		ivActivityIcon.setImageResource(R.drawable.icon_activity_inactive);
	}
	
	private void setActivityIcon() {
		ivActivityIcon.setImageResource(R.drawable.icon_activity_active);
	}
	
	private void setFocusChangeListeners() {
		etHeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				EditText view = (EditText) v;
				if (!isNullorEmpty(view) && !view.getText().toString().contains(heightUnit)) {
					String height = ((EditText) v).getText().toString();					
					etHeight.setText(height + " " + heightUnit);
				}
			}
		});

		etWeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				EditText view = (EditText) v;
				if (!isNullorEmpty(view) && !view.getText().toString().contains(weightUnit)) {
					String weight = ((EditText) v).getText().toString();					
					etWeight.setText(weight + " " + weightUnit);
				}
			}
		});
	}
	
	private void checkContinueEnabled() {
		if(checkDataAvailable()) {
			btnContinue.setEnabled(true);
		} else {
			btnContinue.setEnabled(false);
		}
	}
	
	private boolean checkDataAvailable() {		
		if(etWeight.getText().toString().equals("") || 
		   etHeight.getText().toString().equals("") ||
		   etActivityLevel.getText().toString().equals("")) {
			return false;
		}
		return true;
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
	
	private void setupListeners() {
		etWeight.addTextChangedListener(new Watcher());
		etHeight.addTextChangedListener(new Watcher());
		etActivityLevel.addTextChangedListener(new Watcher());
	}
	
	private boolean isNullorEmpty(EditText et) {
		if(et.getText() == null || et.getText().toString().equals("")) {
			return true;
		}
		return false;
	}
	
	private void changeImages() {
		if(isNullorEmpty(etWeight)) {
			offWeightIcon();
		} else {
			setWeightIcon();
		}
		
		if(isNullorEmpty(etHeight)) {
			offHeightIcon();
		} else {
			setHeighIcon();
		} 
		
		if(isNullorEmpty(etActivityLevel)) {
			offActivityIcon();
		} else {
			setActivityIcon();
		}
	}
	
	private boolean checkValues() {
		if(isNullorEmpty(etHeight) || isNullorEmpty(etWeight) || isNullorEmpty(etHeight)) {
			return false;
		}
		return true;
	}

	private void changeColorOfHint() {
		if(etWeight.getText().toString().equals("")) {
			etWeight.setHintTextColor(Color.RED);
		}
	
		if(etHeight.getText().toString().equals("")) {
			etHeight.setHintTextColor(Color.RED);
		}
		
		if(etActivityLevel.getText().toString().equals("")) {
			etActivityLevel.setHintTextColor(Color.RED);
		}
	}

	private void saveData() {
		currentUser = ParseUser.getCurrentUser();		
		currentUser.put("weight", Long.parseLong(etWeight.getText().toString().replace(weightUnit, "").trim()));
		currentUser.put("height", Long.parseLong(etHeight.getText().toString().replace(heightUnit, "").trim()));
		currentUser.put("activity_level", etActivityLevel.getText().toString());
		currentUser.saveInBackground();
	}

	private void onSubmit() {
		System.out.println("On Submit");
		if(btnContinue.isEnabled()) {
			saveData();	
			Intent intent = new Intent(this, GoalActivity.class);
			startActivity(intent);
		}
	}
	
	private void onSubmitActivityLevel() {
		ivActivityIcon.setImageResource(R.drawable.ic_activity);
		final CharSequence[] items={"High Activity", "Medium Activity", "Low Activity"};

		int currentSelected = 1;
		if(etActivityLevel.getText() != null) {
			if("High Activity".equals(etActivityLevel.getText().toString()))
			{
				currentSelected = 0;
			}
			else if("Low Activity".equals(etActivityLevel.getText().toString()))
			{
				currentSelected = 2;
			}
		}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Set Daily Activity");

		builder.setPositiveButton("Okay",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				changeImages();
			}
		});

		builder.setSingleChoiceItems(items, currentSelected, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {		
				if("High Activity".equals(items[which]))
				{
					etActivityLevel.setText("High Activity");
				}
				else if("Medium Activity".equals(items[which]))
				{
					etActivityLevel.setText("Medium Activity");

				}
				else if("Low Activity".equals(items[which]))
				{
					etActivityLevel.setText("Low Activity");

				}
			}
		});
		
		builder.create();
		builder.show();
		
		if(isNullorEmpty(etActivityLevel)) {
			etActivityLevel.setText(items[currentSelected]);
		}
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btnContinue:
			onSubmit();
			break;
		//case R.id.etWeight:
		//	onSubmitWeight();
		//	break;
		//case R.id.etHeight:
		//	onSubmitHeight();
		//	break;
		case R.id.etActivityLevel:
			onSubmitActivityLevel();
			break;
		}
	}
}