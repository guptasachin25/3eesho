package com.codepath.eesho.fragments;

import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codepath.eesho.R;
import com.codepath.eesho.parse.models.MyActivity;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ActivityHistoryFragment extends Fragment{
	
	ProgressBar pbMiles;
	ProgressBar pbSitups;
	ProgressBar pbCrunches;
	ProgressBar pbPushups;
	ProgressBar pbLunges;
	ProgressBar pbShoulder;
	
	TextView tvMiles;
	TextView tvSitups;
	TextView tvCrunches;
	TextView tvPushups;
	TextView tvLunges;
	TextView tvShoulder;
	
	Button bWeek;
	Button bMonth;
	Button bAll;
	
	// keeps track of which history is currently displayed
	// 1 - week, 2 - month, 3 - all
	int currentlyClickedBtn = 1;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_activity_history, container, false);
		
		bWeek = (Button) v.findViewById(R.id.btWeek);
		bMonth = (Button) v.findViewById(R.id.btMonth);
		bAll = (Button) v.findViewById(R.id.btEverything);
		
		pbMiles = (ProgressBar) v.findViewById(R.id.pbMiles);
		pbSitups = (ProgressBar) v.findViewById(R.id.pbSitups);
		pbCrunches = (ProgressBar) v.findViewById(R.id.pbCrunches);
		pbPushups = (ProgressBar) v.findViewById(R.id.pbPushups);
		pbLunges = (ProgressBar) v.findViewById(R.id.pbLunges);
		pbShoulder = (ProgressBar) v.findViewById(R.id.pbShoulder);
		
		tvMiles = (TextView) v.findViewById(R.id.tvMilesCount);
		tvSitups = (TextView) v.findViewById(R.id.tvSitupsCount);
		tvCrunches = (TextView) v.findViewById(R.id.tvCrunchesCount);
		tvPushups = (TextView) v.findViewById(R.id.tvPushupsCount);
		tvLunges = (TextView) v.findViewById(R.id.tvLungesCount);
		tvShoulder = (TextView) v.findViewById(R.id.tvShoulderCount);

		setUpWeekBars();
		
		bMonth.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	bMonth.setTextColor(Color.parseColor("#FFFFFF"));
		        bMonth.setBackgroundResource(R.drawable.history_button);
		        if (currentlyClickedBtn == 1) {
		        	setToUnclick(bWeek);
		        } else {
		        	setToUnclick(bAll);
		        }
		        currentlyClickedBtn = 2;
		        setUpMonthBars();
		    }
		});
		
		bAll.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	bAll.setTextColor(Color.parseColor("#FFFFFF"));
		        bAll.setBackgroundResource(R.drawable.history_button);
		        if (currentlyClickedBtn == 1) {
		        	setToUnclick(bWeek);
		        } else {
		        	setToUnclick(bMonth);
		        }
		        currentlyClickedBtn = 3;
		        setUpAllBars();
		    }
		});
		
		bWeek.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	bWeek.setTextColor(Color.parseColor("#FFFFFF"));
		        bWeek.setBackgroundResource(R.drawable.history_button);
		        if (currentlyClickedBtn == 2) {
		        	setToUnclick(bMonth);
		        } else {
		        	setToUnclick(bAll);
		        }
		        currentlyClickedBtn = 1;
		        setUpWeekBars();
		    }
		});
		
		return v;
	}
	
	public void setToUnclick(Button b) {
		b.setBackgroundResource(R.drawable.history_button_unclicked);
		b.setTextColor(Color.parseColor("#a8a8a8"));
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public void setBar (ProgressBar b, TextView tv, int max, int progress) {
		b.setMax(max);
		b.setProgress(progress);
		tv.setText(Integer.toString(progress));
	}
	
	public void setUpWeekBars() {
		
		getActivityWeek("Run", pbMiles, tvMiles, 25);
		getActivityWeek("Pushups", pbPushups, tvPushups, 100);
		// formerly crunches
		getActivityWeek("Inch Worm", pbCrunches, tvCrunches, 50);
		getActivityWeek("Shoulder", pbShoulder, tvShoulder, 100);
		//formerly situps
		getActivityWeek("Chest Presses", pbSitups, tvSitups, 100);
		getActivityWeek("Lunges", pbLunges, tvLunges, 100);
		
	}
	
	public void getActivityWeek(String activity, final ProgressBar pb, final TextView tv, final int max) {
		ParseQuery<MyActivity> query = ParseQuery.getQuery(MyActivity.class);
		query.whereEqualTo("user", ParseUser.getCurrentUser());
		query.whereEqualTo("activity_type", activity);
		
		query.findInBackground(new FindCallback<MyActivity>() {
			public void done(List<MyActivity> activity, ParseException e) {
				int count = 0;
				if (e == null) {
					for (int i = 0; i < activity.size(); i++) {
						count = count + activity.get(i).getInt("activity_dimensions");
					}
					setBar(pb, tv, max, count);
				} else {
					Log.d("item", "Error: " + e.getMessage());
				}
			}
		});

	}
	
	public void setUpMonthBars() {
		getActivityWeek("Run", pbMiles, tvMiles, 100);
		getActivityWeek("Pushups", pbPushups, tvPushups, 400);
		// formerly crunches
		getActivityWeek("Inch Worm", pbCrunches, tvCrunches, 200);
		getActivityWeek("Shoulder", pbShoulder, tvShoulder, 400);
		//formerly situps
		getActivityWeek("Chest Presses", pbSitups, tvSitups, 400);
		getActivityWeek("Lunges", pbLunges, tvLunges, 400);
	}
	
	public void setUpAllBars() {
		getActivityWeek("Run", pbMiles, tvMiles, 300);
		getActivityWeek("Pushups", pbPushups, tvPushups, 1200);
		// formerly crunches
		getActivityWeek("Inch Worm", pbCrunches, tvCrunches, 600);
		getActivityWeek("Shoulder", pbShoulder, tvShoulder, 1200);
		//formerly situps
		getActivityWeek("Chest Presses", pbSitups, tvSitups, 1200);
		getActivityWeek("Lunges", pbLunges, tvLunges, 1200);
	}

}
