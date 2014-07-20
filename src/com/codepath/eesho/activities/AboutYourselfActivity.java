package com.codepath.eesho.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.eesho.R;
import com.parse.ParseUser;

public class AboutYourselfActivity extends Activity {
	EditText etWeight;
	EditText etHeight;
	EditText etActivityLevel;
	Button btnContinue;

	String weightUnit = "lbs";
	String heightUnit = "cms";
	ParseUser currentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yourself);
		setViews();
		setTextChangeListeners();
	}

	private void setViews() {
		etWeight = (EditText) findViewById(R.id.etWeight);
		etHeight = (EditText) findViewById(R.id.etHeight);
		etActivityLevel = (EditText) findViewById(R.id.etActivityLevel);
		btnContinue = (Button) findViewById(R.id.btnContinue);

	}

	private void setTextChangeListeners() {
		etHeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				EditText view = (EditText) v;

				if (view.getText() != null && !view.getText().toString().equals("") && !view.getText().toString().contains(heightUnit)) {
					String height = ((EditText) v).getText().toString();					
					etHeight.setText(height + " " + heightUnit);
				}
			}
		});

		etWeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				EditText view = (EditText) v;
				if (view.getText() != null && !view.getText().toString().equals("") && !view.getText().toString().contains(weightUnit)) {
					String weight = ((EditText) v).getText().toString();					
					etWeight.setText(weight + " " + weightUnit);
				}
			}
		});
	}

	private boolean checkDataAvailable() {		
		if(etWeight.getText().toString().equals("") || 
		   etHeight.getText().toString().equals("") ||
		   etActivityLevel.getText().toString().equals("")) {
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
		if(!checkDataAvailable()) {
			changeColorOfHint();
			return;
		}
		//saveData();
		
		Intent intent = new Intent(this, GoalActivity.class);
		startActivity(intent);	
	}

	private void onSubmitHeight() {
		etHeight.setText("");
	}

	private void onSubmitWeight() {
		etWeight.setText("");
	}

	private void onSubmitActivityLevel() {
		System.out.println("We came in this activity level");
		final CharSequence[] items={"High Activity", "Medium Activity", "Low Activity"};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Set Daily Activity");

		builder.setPositiveButton("Okay",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				System.out.println(whichButton);
				//etActivityLevel.setText(items[whichButton]);
			}
		});

		builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
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
	}

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btnContinue:
			onSubmit();
			break;
		case R.id.etWeight:
			onSubmitWeight();
			break;
		case R.id.etHeight:
			onSubmitHeight();
			break;
		case R.id.etActivityLevel:
			onSubmitActivityLevel();
			break;
		}
	}

}
