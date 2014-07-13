package com.codepath.eesho.activities;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.codepath.eesho.R;
import com.codepath.eesho.models.AnonUser;
import com.codepath.eesho.models.WeeklyFitnessPlan;
import com.codepath.eesho.parse.models.Goal;
import com.codepath.eesho.parse.models.Plan;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.ui.ParseLoginBuilder;

public class UserMetricsActivity extends Activity {
	EditText etWeight;
	EditText etHeight;
	Switch swWeightUnit;
	Switch swHeightUnit;
	Switch swEatingHabits;

	Button btnLowActivity;
	Button btnMediumActivity;
	Button btnHighActivity;
	Button btnNext;

	AnonUser anonUser;
	ParseUser currentUser;

	@SuppressLint("UseSparseArrays")
	public static Map<Integer, String> weekDayMap = new HashMap<Integer, String>();
	static {
		weekDayMap.put(Calendar.MONDAY, "Monday");
		weekDayMap.put(Calendar.TUESDAY, "Tuesday");
		weekDayMap.put(Calendar.WEDNESDAY, "Wednesday");
		weekDayMap.put(Calendar.THURSDAY, "Thrusday");
		weekDayMap.put(Calendar.FRIDAY, "Friday");
		weekDayMap.put(Calendar.SATURDAY, "Saturday");
		weekDayMap.put(Calendar.SUNDAY, "Sunday");		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_metrics);
		anonUser = (AnonUser) getIntent().getSerializableExtra("anonUser");
		setViews();
	}

	private void setViews() {
		btnLowActivity = (Button) findViewById(R.id.btnLowActivity);
		btnMediumActivity = (Button) findViewById(R.id.btnMediumActivity);
		btnHighActivity = (Button) findViewById(R.id.btnHighActivity);

		btnNext = (Button) findViewById(R.id.btnDone);

		etWeight = (EditText) findViewById(R.id.etWeight); 
		etHeight = (EditText) findViewById(R.id.etHeight);
		swWeightUnit = (Switch) findViewById(R.id.swWeightUnit);
		swHeightUnit = (Switch) findViewById(R.id.swHeightUnit);
		swEatingHabits = (Switch) findViewById(R.id.swEatingHabits);
	}

	private boolean checkDataAvailable() {
		if(anonUser.getActivity() == null 
				|| etWeight.getText() == null 
				|| etHeight.getText() == null
				|| etWeight.getText().toString().equals("")
				|| etHeight.getText().toString().equals("")) {
			return false;
		}
		return true;
	}

	private String getWeightUnit() {
		return swWeightUnit.isPressed() ? "LB" : "KG";
	}

	private String getHeightUnit() {
		return swHeightUnit.isPressed() ? "INCH" : "CM";
	}

	private String getFoodHabits() {
		return swEatingHabits.isPressed() ? "VEG" : "NonVEG";
	}

	private void onSubmit() {
		if(!checkDataAvailable()) {
			String warningText = "First enter current metrics values";
			Toast.makeText(this, warningText, Toast.LENGTH_SHORT).show();
			return;
		}

		anonUser.setCurrentWeight(Long.parseLong(etWeight.getText().toString()));
		anonUser.setHeight(Long.parseLong(etHeight.getText().toString()));
		anonUser.setCurrentWeightUnit(getWeightUnit());
		anonUser.setHeightUnit(getHeightUnit());
		anonUser.setFoodHabits(getFoodHabits());

		ParseLoginBuilder builder = new ParseLoginBuilder(getApplicationContext());
		startActivityForResult(builder.build(), 80);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// REQUEST_CODE is defined above
		currentUser = ParseUser.getCurrentUser();
		if (resultCode == RESULT_OK && requestCode == 80) {
			// Extract name value from result extras
			String name = currentUser.getString("name");
			if(name == null) {
				name = currentUser.toString();
			}
			saveData(currentUser);
			Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();
			Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
			startActivity(intent);
		}
	}

	private void saveData(ParseUser user) {
		// Save user 
		user.put("weight", anonUser.getCurrentWeight());
		user.put("height", anonUser.getHeight());
		user.put("diet_habit", anonUser.getFoodHabits());
		user.put("activity_level", anonUser.getActivity());
		user.saveInBackground();

		// Save plan 
		Plan newPlan = new Plan();
		newPlan.setUser(ParseUser.getCurrentUser());
		newPlan.setPlanType("fitness");
		try {
			newPlan.setPlanDesc(WeeklyFitnessPlan.getDefaultPlan().toJSON());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		newPlan.saveInBackground(new SaveCallback() {

			@Override
			public void done(ParseException arg0) {
				//System.out.println(arg0);
			}
		});

		// Save default plan week data in Goal table with date
		final WeeklyFitnessPlan plan = WeeklyFitnessPlan.getDefaultPlan();

		/*
		for(int i = 0; i < 7; i++) {
			final DateTime date = new DateTime().plusDays(i);
			ParseQuery<Goal> query = ParseQuery.getQuery(Goal.class);
			query.whereEqualTo("user", ParseUser.getCurrentUser());
			query.whereEqualTo("date", date.toDateMidnight().toDate());
			
			query.getFirstInBackground(new GetCallback<Goal>() {
				@Override
				public void done(Goal goal, ParseException exception) {
					if(exception != null) {	
						if(goal == null) {
							System.out.println("Goal is null");
							goal = new Goal();
						}
						goal.setDate(date.toDateMidnight().toDate());
						goal.setWeekDay(weekDayMap.get(date.getDayOfWeek()));
						goal.setPlan(plan.getPlan(date));
						goal.setUser(currentUser);
						goal.saveInBackground();
					} else {
						System.out.println(exception);
					}
				}
			});
		}*/		
	}

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btnLowActivity:
			anonUser.setActivity("low");
			break;
		case R.id.btnHighActivity:
			anonUser.setActivity("high");
			break;
		case R.id.btnMediumActivity:
			anonUser.setActivity("medium");
			break;
		case R.id.btnDone:
			onSubmit();
			break;
		}
	}

}

